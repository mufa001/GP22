/*
 * PNGTransferHandler.java
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

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.TransferHandler;


/**
 * Drag'n'Drop/Clipboard TransferHandler for JaTeX PNG images.
 *
 * Modeled from this article http://java.sun.com/developer/technicalArticles/releases/data/index.html
 *
 * <p>  Modified by:  Joseph A. Huwaldt  </p>
 *
 * @author Jess Thrysoee, 2006
 * @version January 9, 2015
 */
@SuppressWarnings("StaticNonFinalUsedInInitialization")
class PNGTransferHandler extends TransferHandler implements Transferable {

    private static final Logger log = Logger.getLogger(PNGTransferHandler.class.getName());
    private PNGImage mImage;
    public static DataFlavor[] FLAVORS;
    private static DataFlavor mFlavorPNG;
    private static final DataFlavor mFlavorFileList = DataFlavor.javaFileListFlavor;
    private static DataFlavor mFlavorURI;

    static {
        try {
            mFlavorPNG = new DataFlavor("image/png; class=java.io.InputStream");
            mFlavorURI = new DataFlavor("text/uri-list; class=java.lang.String");
            FLAVORS = new DataFlavor[]{mFlavorPNG, mFlavorFileList, mFlavorURI};
        } catch (ClassNotFoundException e) { /* can't happen */ }
    }

    /**
     * Returns true if the component can support one of the data flavors, false otherwise.
     *
     * DROP,PASTE
     *
     * @param c
     * @param flavors
     * @return
     */
    @Override
    public boolean canImport(JComponent c, DataFlavor[] flavors) {
        for (DataFlavor flavor : flavors) {
            for (DataFlavor supportedFlavor : FLAVORS) {
                if (supportedFlavor.equals(flavor)) {
                    log.log(Level.FINE, "canImport:{0}", flavor.getMimeType());
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Returns true if the component supports getting one of the data flavors from the
     * Transferable object, and successfully gets it, false otherwise. This is your paste
     * operation. Again, the handler gets the data from the clipboard, you just have to
     * get it from the Transferable.
     *
     * DROP,PASTE
     *
     * @param c
     * @param t
     * @return
     */
    @Override
    public boolean importData(JComponent c, Transferable t) {
        PNGPanel pngPanel = (PNGPanel) c;

        if (!canImport(pngPanel, t.getTransferDataFlavors())) {
            printFlavors("importData !canImport:", t.getTransferDataFlavors());
            return false;
        }


        printFlavors("importData:", t.getTransferDataFlavors());

        try {
            InputStream in = null;

            if (t.isDataFlavorSupported(mFlavorPNG)) {
                log.fine("png-image: iostream");
                in = (InputStream) t.getTransferData(mFlavorPNG);

            } else if (t.isDataFlavorSupported(mFlavorFileList)) {
                // Windows (e.g. File Explorer)
                List files = (List) t.getTransferData(mFlavorFileList);

                log.log(Level.FINE, "file-list: {0}", files);

                if (!files.isEmpty()) {
                    File file = (File) files.get(0);
                    if (file.exists()) {
                        in = new FileInputStream(file);
                    }
                }

            } else if (t.isDataFlavorSupported(mFlavorURI)) {
                // Linux (e.g. konqueror)
                String uriList = (String) t.getTransferData(mFlavorURI);
                uriList = removeNul(uriList);   // handle nul terminated string
                String[] uris = uriList.split("\r\n");

                log.log(Level.FINE, "uri-list: {0}", Arrays.asList(uris));

                if (uris.length > 0) {
                    try {
                        URI uri = new URI(uris[0]);
                        in = uri.toURL().openStream();
                    } catch (URISyntaxException ex) {
                        ex.printStackTrace();
                    }
                }
            }

            if (in != null) {
                mImage = new PNGImage(in);

                if (pngPanel.updateMetaData(mImage)) {
                    pngPanel.setPNGImage(mImage);
                    return true;
                }
            }
        } catch (UnsupportedFlavorException ignored) {
        } catch (IOException ignored) {
        }

        return false;
    }

    /**
     * Returns the supported operations.
     *
     * There are four constants in the TransferHandler class for the operations: COPY,
     * COPY_OR_MOVE, MOVE, and NONE.
     *
     * DRAG, CUT, COPY
     *
     * @param c
     * @return
     */
    @Override
    public int getSourceActions(JComponent c) {
        return TransferHandler.COPY;
    }

    /**
     * Here, you need to Save a reference to the data to be transferred, and return the
     * TransferHandler (this). The component represents where the data is coming from.
     * This is your copy operation. The handler does the actual copy to the clipboard at
     * the appropriate time.
     *
     * DRAG, CUT, COPY
     *
     * @param c
     * @return
     */
    @Override
    protected Transferable createTransferable(JComponent c) {
        // cleanup
        mImage = null;

        if (c instanceof PNGPanel) {
            mImage = ((PNGPanel) c).getPNGImage();
            if (mImage != null) {
                return this;
            }
        }
        return null;
    }

    // Transferable,
    @Override
    public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
        if (mFlavorPNG.equals(flavor)) {
            return new ByteArrayInputStream(mImage.getBytes());
        } else if (mFlavorFileList.equals(flavor)) {
            List<File> fileList = new ArrayList<File>(1);
            fileList.add(mImage.getFile());
            return fileList;
        } else if (mFlavorURI.equals(flavor)) {
            return mImage.getFile().toURI().toString() + "\r\n";
        }

        throw new UnsupportedFlavorException(flavor);
    }

    @Override
    public DataFlavor[] getTransferDataFlavors() {
        return FLAVORS;
    }

    @Override
    public boolean isDataFlavorSupported(DataFlavor flavor) {
        return Arrays.asList(FLAVORS).contains(flavor);
    }

    private static String removeNul(String s) {
        String res = null;

        if (s.charAt(s.length() - 1) == '\0') {
            res = s.substring(0, s.length() - 1);
        }

        return res;
    }

    public static void printFlavors(String prefix, DataFlavor[] flavors) {
        StringBuilder buf = new StringBuilder();

        buf.append(prefix).append("\n");
        for (DataFlavor i : flavors) {
            buf.append("   [").append(i.getMimeType()).append("]\n");
        }

        log.fine("");
        log.fine(buf.toString());
        log.fine("");
    }
}
