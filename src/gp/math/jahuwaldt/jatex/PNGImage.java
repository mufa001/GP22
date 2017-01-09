/*
 * PNGImage.java
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

package gp.math.jahuwaldt.jatex;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import javax.imageio.IIOException;
import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.ImageWriter;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.metadata.IIOMetadataNode;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.stream.ImageOutputStream;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.bootstrap.DOMImplementationRegistry;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSOutput;
import org.w3c.dom.ls.LSSerializer;

/**
 * An object that represents a PNG image with associated meta-data representing
 * the LaTeX code for the formula rendered.
 * 
 * <p>  Modified by:  Joseph A. Huwaldt  </p>
 *
 * @author Jess Thrysoee, 2006
 * @version January 9, 2015
 */
public class PNGImage {

    private static final boolean DEBUG = false;
    private static final String LATEX_PREAMBLE = "LaTeXPreamble";
    private static final String LATEX_POSTAMBLE = "LaTeXPostamble";
    private static final String LATEX_DVIPNGOPTIONS = "LaTexDviPngOptions";
    private static final String LATEX_EQUATION = "LaTeXEquation";
    private static final String LATEX_POINTSIZE = "LaTeXPointSize";
    private IIOImage mImage;
    private IIOMetadata mMetadata;
    private Node mRootNode;
    private Node mTEXtNode;
    private File mTempFile;

    /**
     * Creates a new instance of PNGImage.
     * 
     * @param file A reference to the PNG format image file to read in.
     * @throws java.io.IOException if there are any problems reading from the
     * file.
     */
    public PNGImage(File file) throws IOException {
        this(new BufferedInputStream(new FileInputStream(file)));
    }

    /**
     * Creates a new instance of PNGImage.
     * The output stream is closed automatically by this method upon completion.
     * 
     * @param is A stream for reading from a PNG image.
     * @throws java.io.IOException if there are any problems reading from the
     * stream.
     */
    public PNGImage(InputStream is) throws IOException {
        this(ImageIO.createImageInputStream(is));
    }

    /**
     * Creates a new instance of PNGImage.
     * The output stream is closed automatically by this method upon completion.
     * 
     * @param iin A stream for reading from a PNG image.
     * @throws java.io.IOException if there are any problems reading from the
     * stream.
     */
    public PNGImage(ImageInputStream iin) throws IOException {

        ImageReader reader = null;
        Iterator readers = ImageIO.getImageReadersByFormatName("png");

        if (readers.hasNext()) {
            try {
                reader = (ImageReader)readers.next();

                reader.setInput(iin);

                mImage = reader.readAll(0, null);

                mMetadata = mImage.getMetadata();
                mRootNode = mMetadata.getAsTree(mMetadata.getNativeMetadataFormatName());
                
                if (DEBUG)
                    printMetadata(mRootNode);

                mTEXtNode = gettEXt(mRootNode);
            } finally {
                if (iin != null)
                    iin.close();
                if (reader != null)
                    reader.dispose();
            }
        } else {
            throw new IOException("No PNG reader could be found.");
        }
    }

    /**
     * Write out the PNG with meta-data to the specified file.
     * 
     * @param file The file to write the PNG data to.
     * @throws java.io.IOException if there is any problem writing to the file.
     */
    public void writeTo(File file) throws IOException {
        writeTo(ImageIO.createImageOutputStream(file));
    }

    /**
     * Write out the PNG with meta-data to the specified image output stream.
     * The output stream is closed automatically by this method upon completion.
     * 
     * @param iout The output stream to write the PNG data to.
     * @throws java.io.IOException if there is any problem writing to the stream.
     */
    public void writeTo(ImageOutputStream iout) throws IOException {
        ImageWriter writer = null;
        Iterator writers = ImageIO.getImageWritersByFormatName("png");

        if (writers.hasNext()) {
            try {
                writer = (ImageWriter) writers.next();
                writer.setOutput(iout);

                mMetadata.setFromTree(mMetadata.getNativeMetadataFormatName(), mRootNode);

                if (DEBUG)
                    printMetadata(mMetadata.getAsTree(mMetadata.getNativeMetadataFormatName()));

                writer.write(mImage);
            } finally {
                if (iout != null)
                    iout.close();
                if (writer != null)
                    writer.dispose();
            }
        }
    }

    /**
     * Get the Java image from this PNGImage object. Use for display.
     * 
     * @return The Java BufferedImage object representing this PNG image.
     */
    public BufferedImage getBufferedImage() {
        return (BufferedImage) mImage.getRenderedImage();
    }

    /**
     * Get this PNGImage as a byte array.
     * 
     * @return This PNG image as an encoded byte-array.
     * @throws java.io.IOException if there is any problem creating the byte-array.
     */
    public byte[] getBytes() throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageOutputStream ios = ImageIO.createImageOutputStream(bos);
        writeTo(ios);
        return bos.toByteArray();
    }

    /**
     * Get this PNGImage as a File.  This writes out a file containing this
     * object's PNG image data and returns a reference to it.
     * 
     * @return A reference to a file containing this PNG image.
     * @throws java.io.IOException if there is any problem creating the PNG file
     * from the image data in this PNGImage object.
     */
    public synchronized File getFile() throws IOException {
        // Remember to call removeTempFile() to remove the file if it becomes stale, e.g. in settEXtEntry().
        
        if (mTempFile == null || !mTempFile.exists()) {
            mTempFile = File.createTempFile("JaTeX", ".png");
            writeTo(mTempFile);
        }

        return mTempFile;
    }

    /**
     * Set the LaTeX pre-amble stored in this PNG's meta data.
     * 
     * @param s A String representing this PNG's LaTeX pre-amble.
     */
    public void setLatexPreamble(String s) {
        settEXtEntry(LATEX_PREAMBLE, s);
    }

    /**
     * Return the LaTeX pre-amble stored with this PNG's meta data.
     * 
     * @return This PNG's LaTeX pre-amble.
     * @throws IIOException If any problem occurs retrieving the meta data.
     */
    public String getLatexPreamble() throws IIOException {
        String value;
        try {
            //  Try a JaTex generated equation.
            value = gettEXtEntry(LATEX_PREAMBLE);
        } catch (IIOException e) {
            //  Try a Laeqed generated equation.
            value = gettEXtEntry("LaeqedPreamble");
        }
        return value;
    }

    /**
     * Set the LaTeX post-amble stored in this PNG's meta data.
     * 
     * @param s A String representing this PNG's LaTeX post-amble.
     */
    public void setLatexPostamble(String s) {
        settEXtEntry(LATEX_POSTAMBLE, s);
    }

    /**
     * Return the LaTeX post-amble stored with this PNG's meta data.
     * 
     * @return This PNG's LaTeX post-amble.
     * @throws IIOException If any problem occurs retrieving the meta data.
     */
    public String getLatexPostamble() throws IIOException {
        String value;
        try {
            //  Try a JaTex generated equation.
            value = gettEXtEntry(LATEX_POSTAMBLE);
        } catch (IIOException e) {
            //  Try a Laeqed generated equation.
            value = gettEXtEntry("LaeqedPostamble");
        }
        return value;
    }

    /**
     * Set the DVIPNG command line options stored in this PNG's meta data.
     * 
     * @param s A String representing this PNG's DVIPNG command line options.
     */
    public void setDvipngOptions(String s) {
        settEXtEntry(LATEX_DVIPNGOPTIONS, s);
    }

    /**
     * Return the DVIPNG command line options stored with this PNG's meta data.
     * 
     * @return This PNG's DVIPNG command line options.
     * @throws IIOException If any problem occurs retrieving the meta data.
     */
    public String getDvipngOptions() throws IIOException {
        String value;
        try {
            //  Try a JaTex generated equation.
            value = gettEXtEntry(LATEX_DVIPNGOPTIONS);
        } catch (IIOException e) {
            //  Try a Laeqed generated equation.
            value = gettEXtEntry("LaeqedDvipngOptions");
        }
        return value;
    }

    /**
     * Set the equation text point size stored in this PNG's meta data.
     * 
     * @param size This PNG's equation text point size.
     */
    public void setPointSize(int size) {
        settEXtEntry(LATEX_POINTSIZE, Integer.toString(size));
    }

    /**
     * Return the equation text point size stored with this PNG's meta data.
     * 
     * @return This PNG's equation text point size.
     * @throws IIOException If any problem occurs retrieving the meta data.
     */
    public int getPointSize() throws IIOException {
        try {
            String str = gettEXtEntry(LATEX_POINTSIZE);
            return Integer.parseInt(str);
        } catch (IIOException e) {
            //  Just use the default of 10 pt
            return 10;
        } catch (NumberFormatException e) {
            //  Just use the default of 10 pt
            return 10;
        }
    }

    /**
     * Set the equation LaTeX code stored in this PNG's meta data.
     * 
     * @param s This PNG's equation LaTeX code.
     */
    public void setLatexEquation(String s) {
        settEXtEntry(LATEX_EQUATION, s);
    }

    /**
     * Return the equation LaTeX code stored with this PNG's meta data.
     * 
     * @return This PNG's equation LaTeX code.
     * @throws IIOException If any problem occurs retrieving the meta data.
     */
    public String getLatexEquation() throws IIOException {
        String value;
        try {
            //  Try a JaTex generated equation.
            value = gettEXtEntry(LATEX_EQUATION);
        } catch (IIOException e) {
            //  Try a Laeqed generated equation.
            value = gettEXtEntry("LaeqedEquation");
        }
        return value;
        
    }

    /**
     * Method that sets the specified tEXtEntry key to the specified value
     * in the PNG meta data.
     * 
     * @param keyword  Keyword indicating the entry in the meta data to be set.
     * @param value The value to store in the meta data with the specified keyword.
     */
    private void settEXtEntry(String keyword, String value) {
        final String tEXtEntry = "tEXtEntry";

        boolean updated = false;
        Node tEXtEntryNode;

        NodeList nodeList = mTEXtNode.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            if (tEXtEntry.equals(nodeList.item(i).getNodeName())) {
                tEXtEntryNode = nodeList.item(i);

                NamedNodeMap map = tEXtEntryNode.getAttributes();
                Node keywordNode = map.getNamedItem("keyword");

                if (keyword.equals(keywordNode.getNodeValue())) {
                    map.getNamedItem("value").setNodeValue(value);
                    updated = true;
                    break;
                }
            }
        }

        if (!updated) {
            IIOMetadataNode newtEXtEntryNode = new IIOMetadataNode(tEXtEntry);
            newtEXtEntryNode.setAttribute("keyword", keyword);
            newtEXtEntryNode.setAttribute("value", value);
            mTEXtNode.appendChild(newtEXtEntryNode);
        }


        removeTempFile();   // cached file is now stale.
    }

    /**
     * Method that returns the String associated with the specified tEXtEntry
     * key.
     * 
     * @param keyword The key to return the meta-data for.
     * @return The meta-data String associated with the specified keyword in
     * this PNG's meta-data.
     * @throws IIOException if there is any problem reading the meta-data.
     */
    private String gettEXtEntry(String keyword) throws IIOException {
        final String tEXtEntry = "tEXtEntry";
        Node tEXtEntryNode;
        String tEXtEntryValue = null;

        NodeList nodeList = mTEXtNode.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            if (tEXtEntry.equals(nodeList.item(i).getNodeName())) {
                tEXtEntryNode = nodeList.item(i);

                NamedNodeMap map = tEXtEntryNode.getAttributes();
                Node keywordNode = map.getNamedItem("keyword");

                if (keyword.equals(keywordNode.getNodeValue())) {
                    tEXtEntryValue = map.getNamedItem("value").getNodeValue();
                    break;
                }
            }
        }

        if (tEXtEntryValue == null) {
            throw new IIOException("tEXtEntry with keyword '" + keyword + "' not found!");
        }

        return tEXtEntryValue;
    }

    /**
     * Return a reference to the root tEXt node in the PNG image meta-data.
     * 
     * @param rootNode The root node for all PNG meta-data.
     * @return The node that is the root of all tEXt data in the PNG file.
     */
    private Node gettEXt(Node rootNode) {
        final String tEXt = "tEXt";
        Node tEXtNode = null;

        NodeList nodeList = rootNode.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i++) {
            if (tEXt.equals(nodeList.item(i).getNodeName())) {
                tEXtNode = nodeList.item(i);
                break;
            }
        }
        if (tEXtNode == null) {
            tEXtNode = new IIOMetadataNode(tEXt);
            rootNode.appendChild(tEXtNode);
        }

        return tEXtNode;
    }

    /**
     * Call when cached mTempFile is stale, e.g. in settEXtEntry().
     */
    private void removeTempFile() {
        if (mTempFile != null) {
            mTempFile.delete();
        }
        mTempFile = null;
    }

    /**
     * Print the data at the specified node as a debugging approach.
     * 
     * @param node The node to have it's data printed.
     */
    private void printMetadata(Node node) {
        try {
            DOMImplementationRegistry registry = DOMImplementationRegistry.newInstance();
            DOMImplementationLS impl = (DOMImplementationLS) registry.getDOMImplementation("LS");

            LSSerializer writer = impl.createLSSerializer();
            LSOutput output = impl.createLSOutput();
            writer.getDomConfig().setParameter("format-pretty-print", Boolean.TRUE);
            output.setByteStream(System.out);
            writer.write(node, output);
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
