/*
 * RunLatex.java
 *
 * Copyright (C) 2006 Jess Thrysoee
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package java.ui.jahuwaldt.jatex;
    
import java.ui.jahuwaldt.io.FileUtils;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Execute Latex and dvipng on the equation latex file.
 *
 * <p>  Modified by:  Joseph A. Huwaldt  </p>
 *
 * @author Jess Thrysoee, 2006
 * @version March 30, 2014
 */
public class RunLatex {

    private static File tmpDir = new File(System.getProperty("java.io.tmpdir"));
    static {
        // Woudld be better to have a directory in the system tmpdir that can be deleted
        //  automatically when the program exits.
        try {
            tmpDir = FileUtils.createTempDirectory("JaTeX");
            tmpDir.deleteOnExit();
        } catch (IOException e) { }
    }
    private final ResourceBundle resBundle;
    private final AppPreferences preferences;
    private final ProcessBuilder processBuilder;
    private InputHandler inputHandler;

    /**
     * Creates a new instance of RunLatex
     */
    public RunLatex(ResourceBundle resBundle, AppPreferences preferences) {
        this.resBundle = resBundle;
        this.preferences = preferences;
        this.processBuilder = new ProcessBuilder();
    }

    /**
     * Run the native latex and dvipng programs returning the resulting PNG image.
     * 
     * @param input  The user input LaTeX equation code to be rendered.
     * @return A PNGImage containing the rendering of the LaTeX code.
     * @throws IOException if there are any file problems or problems with running latex or dvipng.
     * @throws InterruptedException if the thread is interrupted.
     */
    public PNGImage exec(String input) throws IOException, InterruptedException {
        PNGImage pngImage = null;

        //  Create a LaTeX input file.
        File latexFile = writeLatexFile(input);
        String basePath = latexFile.getPath().replaceAll("\\.tex$", "");

        try {
            //  Run latex.
            if (runLatex(latexFile) == 0) {
                File dviFile = new File(basePath + ".dvi");
                String pngFilePath = basePath + ".png";

                if (!dviFile.exists()) {
                    throw new IOException(resBundle.getString("dviFileNotFound"));
                }

                //  Run dvipng
                if (runDvipng(dviFile, pngFilePath) != 0) {
                    throw new IOException(resBundle.getString("dvipngErrorPrefix") + " " + inputHandler.getText());
                }

                //  Get the resulting PNG image.
                pngImage = new PNGImage(new File(pngFilePath));
            } else {
                throw new IOException(resBundle.getString("latexErrorPrefix") + " " + inputHandler.getText());
            }
        } finally {
            deleteTempFiles(basePath);
        }

        return pngImage;
    }

    /**
     * Method that writes out the LaTeX file to be run through latex.
     * 
     * @param input  The user input LaTeX equation code to be rendered.
     * @return A file reference to the LaTeX input file.
     * @throws IOException if there are any problems working with the files.
     */
    private File writeLatexFile(String input) throws IOException {
        BufferedWriter out = null;
        File latexFile = File.createTempFile("JaTeX", ".tex", tmpDir);

        try {
            out = new BufferedWriter(new FileWriter(latexFile));
            out.write("\\nonstopmode\n");
            out.write(preferences.getLatexPreamble().trim());
            out.newLine();
            out.write(input.trim());
            out.newLine();
            out.write(preferences.getLatexPostamble().trim());
        } finally {
            if (out != null)
                out.close();
        }

        return latexFile;
    }

    /**
     * Run the latex code using the supplied input file.
     * 
     * @param latexFile  The LaTeX input file to process.
     * @return The exit value of the latex process. By convention, 0 indicates normal termination.
     * @throws IOException if there are any problems.
     * @throws InterruptedException if the thread is interrupted
     */
    private int runLatex(File latexFile) throws IOException, InterruptedException {
        List<String> cmd = new ArrayList<String>();
        cmd.add(preferences.getLatexApplication());
        cmd.add(latexFile.getPath());

        return runProcessBuilder(cmd);
    }

    /**
     * Run the dvipng code using the supplied DVI file and the desired PNG file path.
     * 
     * @param dviFile The DVI file to be processed into a PNG.
     * @param pngFilePath The desired path to the output PNG file.
     * @return The exit value of the dvipng process. By convention, 0 indicates normal termination.
     * @throws IOException if there are any problems.
     * @throws InterruptedException if the thread is interrupted
     */
    private int runDvipng(File dviFile, String pngFilePath) throws IOException, InterruptedException {
        List<String> cmd = new ArrayList<String>();
        cmd.add(preferences.getDviPngApplication());
        cmd.addAll(preferences.getDviPngOptionsList());
        
        //  Get the point size and if != 10 use the dpi option to scale the output.
        int ptSize = preferences.getPointSize();
        if (ptSize != 10) {
            float dpi = ptSize*72.27F/10;   //  Default dpi = 72.27; default point size = 10 PaperStructure
            cmd.add("-D");
            cmd.add(Float.toString(dpi));
        }
        
        cmd.add("-o");
        cmd.add(pngFilePath);
        cmd.add(dviFile.getPath());

        return runProcessBuilder(cmd);
    }

    /**
     * Run an external process using a ProcessBuilder.
     * 
     * @param command The list of commands to be run.
     * @return The exit value of the process. By convention, 0 indicates normal termination.
     * @throws IOException if there are any problems.
     * @throws InterruptedException if the thread is interrupted
     */
    private int runProcessBuilder(List<String> command) throws IOException, InterruptedException {
        processBuilder.command(command);
        processBuilder.directory(tmpDir);
        processBuilder.redirectErrorStream(true);

        Process process = processBuilder.start();
        inputHandler = new InputHandler(process.getInputStream());

        inputHandler.start();
        int exitValue = process.waitFor();
        inputHandler.join(3000);   // wait for all input to be read

        return exitValue;
    }

    /**
     * Delete the temporary files created when running the latex processes.
     * 
     * @param basePath Path to the directory holding the temporary files.
     */
    private static void deleteTempFiles(String basePath) {
        String[] extensions = {".tex", ".log", ".dvi", ".aux", ".png"};
        for (String extension : extensions) {
            (new File(basePath + extension)).delete();
        }
    }

    private static class InputHandler extends Thread {

        private final InputStream is;
        private final StringBuffer text;
        private IOException ioex;

        InputHandler(InputStream is) {
            this.is = is;
            this.text = new StringBuffer();
        }

        public String getText() throws IOException {
            synchronized (text) {
                if (ioex != null) {
                    throw ioex;
                }

                return text.toString();
            }
        }

        @Override
        public void run() {
            BufferedReader buf = new BufferedReader(new InputStreamReader(is));
            String line;
            try {
                try {
                    synchronized (text) {
                        while ((line = buf.readLine()) != null) {
                            text.append(line).append("\n");
                        }
                    }
                } finally {
                    buf.close();
                }
            } catch (IOException ex) {
                ioex = ex;
            }
        }
    }
}
