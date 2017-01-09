/*
 * PNGMetaDataHandler.java
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

import gp.math.jahuwaldt.jatex.latexeditor.LatexEditorPane;
import gp.math.jahuwaldt.swing.AppUtilities;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.ResourceBundle;
import javax.imageio.IIOException;
import javax.swing.JOptionPane;

/**
 * Handle the updating of meta data on PNGImage objects created from file open menu or
 * drag-and-drop.
 *
 * <p>  Modified by:  Joseph A. Huwaldt  </p>
 *
 * @author Jess Thrysoee, 2006
 * @version January 9, 2015
 */
public class PNGMetaDataHandler {

    private final MainWindow parent;
    private final AppPreferences prefs;
    private final PNGPanel pngPanel;
    private final LatexEditorPane latexEditorPane;

    /**
     * Creates a new instance of PNGMetaDataHandler.
     * 
     * @param parent The application window that is the parent of this handler.
     * @param pngPanel The PNGPanel that contains the PNG image this meta-data
     * handler is associated with.
     * @param latexEditorPane The LatexEditorPane where the equation is being edited.
     * @param prefs The preferences for this application.
     */
    public PNGMetaDataHandler(MainWindow parent, PNGPanel pngPanel, LatexEditorPane latexEditorPane, AppPreferences prefs) {
        this.parent = parent;
        this.pngPanel = pngPanel;
        this.latexEditorPane = latexEditorPane;
        this.prefs = prefs;
    }

    /**
     * Update the preferences and current equation in this application based on
     * the meta data found in the specified PNG image file.
     * 
     * @param file The PNG image file to have the meta data read in from.
     * @return Returns true if everything worked and false if the meta data was not read.
     */
    public boolean updateMetaData(File file) {
        boolean res = false;

        try {
            PNGImage pngImage = new PNGImage(file);
            res = updateMetaData(pngImage);
        } catch (FileNotFoundException ex) {
            ResourceBundle resBundle = parent.getResourceBundle();
            JOptionPane.showMessageDialog(parent, MessageFormat.format(resBundle.getString("fileNotFoundMsg"),file.getPath()),
                    resBundle.getString("ioErrorTitle"), JOptionPane.ERROR_MESSAGE);
        } catch (IOException ioex) {
            ResourceBundle resBundle = parent.getResourceBundle();
            AppUtilities.showException(parent, resBundle.getString("unexpectedTitle"),
                    resBundle.getString("unexpectedMsg"), ioex);
        }

        return res;
    }

    /**
     * Update the preferences and current equation in this application based on
     * the meta data found in the specified PNGImage object.
     * 
     * @param pngImage The PNGImage object to pull the meta data from.
     * @return Returns true if everything worked and false if the meta data was not read.
     */
    public boolean updateMetaData(PNGImage pngImage) {

        boolean res = false;

        try {
            prefs.setLatexPreamble(pngImage.getLatexPreamble());
            prefs.setLatexPostamble(pngImage.getLatexPostamble());
            prefs.setDviPngOptions(pngImage.getDvipngOptions());
            prefs.setPointSize(pngImage.getPointSize());

            pngPanel.setPNGImage(pngImage);
            latexEditorPane.setText(pngImage.getLatexEquation());
            res = true;

        } catch (IIOException iioex) {
            ResourceBundle resBundle = parent.getResourceBundle();
            JOptionPane.showMessageDialog(parent, resBundle.getString("unrecognizedImgFormatMsg"),
                   resBundle.getString("unrecognizedImgFormatTitle"), JOptionPane.ERROR_MESSAGE);
        } catch (IOException ioex) {
            ResourceBundle resBundle = parent.getResourceBundle();
            AppUtilities.showException(parent, resBundle.getString("ioErrorTitle"),
                    resBundle.getString("unexpectedMsg"), ioex);
        }

        return res;
    }
}
