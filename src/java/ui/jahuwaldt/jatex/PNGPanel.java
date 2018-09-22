/*
 * PNGPanel.java
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

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.TransferHandler;
import javax.swing.UIManager;

/**
 * A JPanel that displays a PNGImage object.
 * 
 * <p>  Modified by:  Joseph A. Huwaldt  </p>
 *
 * @author Jess Thrysoee, 2006
 * @version January 9, 2015
 */
public class PNGPanel extends JPanel {

    private static final Logger log = Logger.getLogger(PNGPanel.class.getName());
    private PNGMetaDataHandler pngMetaDataHandler;
    private PNGImage pngImage;
    private Image image;
    private JPopupMenu popup;

    /**
     * Creates a new instance of a PNGPanel
     */
    public PNGPanel() {
        setBackground(UIManager.getColor("Label.background"));

        setTransferHandler(new PNGTransferHandler());

        //Add the cut/copy/paste key bindings to the input map.
        //Note that this step is redundant if you are installing
        //menu accelerators that cause these actions to be invoked.
        //DragPictureDemo does not use menu accelerators and, since
        //the default value of installInputMapBindings is true,
        //the bindings are installed.  DragPictureDemo2 does use
        //menu accelerators and so calls setInstallInputMapBindings
        //with a value of false.  Your program would do one or the
        //other, but not both.
//        if (installInputMapBindings) {
//            InputMap imap = this.getInputMap();
//            imap.put(KeyStroke.getKeyStroke("ctrl X"), TransferHandler.getCutAction().getValue(Action.NAME));
//            imap.put(KeyStroke.getKeyStroke("ctrl C"), TransferHandler.getCopyAction().getValue(Action.NAME));
//            imap.put(KeyStroke.getKeyStroke("ctrl V"), TransferHandler.getPasteAction().getValue(Action.NAME));
//        }
//
//        //Add the cut/copy/paste actions to the action map.
//        //This step is necessary because the menu's action listener looks for these actions to fire.
//        ActionMap map = this.getActionMap();
//        map.put(TransferHandler.getCutAction().getValue(Action.NAME), TransferHandler.getCutAction());
//        map.put(TransferHandler.getCopyAction().getValue(Action.NAME), TransferHandler.getCopyAction());
//        map.put(TransferHandler.getPasteAction().getValue(Action.NAME), TransferHandler.getPasteAction());


        createPopupMenu();

        DragSourceListener listener = new DragSourceListener();
        addMouseListener(listener);
        addMouseMotionListener(listener);
        
        setPreferredSize(new Dimension(350,100));
    }
	
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (image != null) {
            // center image
            int x = (getSize().width - image.getWidth(this)) / 2;
            int y = (getSize().height - image.getHeight(this)) / 2;
            // Paint a PNG having translucency information.
            g.drawImage(image, x, y, null);
        }
    }

    /**
     * Return the image associated with this panel or null if there is none.
     * 
     * @return the image associated with this panel or null.
     */
    public PNGImage getPNGImage() {
        return pngImage;
    }

    /**
     * Set the image associated with this panel.
     * 
     * @param pngImage The PNG image to associate with this panel.
     * @throws IOException If there is any problem turning the PNGImage object
     * into an on-screen image.
     */
    public void setPNGImage(PNGImage pngImage) throws IOException {
        if (pngImage != null) {
            this.pngImage = pngImage;
            setImage(pngImage.getBufferedImage());
        }
    }

    private void setImage(Image image) {
        this.image = image;

        setPreferredSize(new Dimension(image.getWidth(this), image.getHeight(this)));
        repaint();
        revalidate();
    }

    /**
     * Set the PNG meta data handler used by this panel to ensure that the GUI
     * stays in synch with the displayed equation after a drag-n-drop and file
     * open.
     * 
     * @param pngMetaDataHandler The PNG meta data handler to use.
     */
    public void setPNGMetaDataHandler(PNGMetaDataHandler pngMetaDataHandler) {
        this.pngMetaDataHandler = pngMetaDataHandler;
    }

    /**
     * Updates the GUI to be consistent with the meta data in the supplied
     * PNGImage.
     * 
     * @param pngImage The PNG image to make the GUI consistent with.
     * @return True if the meta-data was successfully read in.
     */
    public boolean updateMetaData(PNGImage pngImage) {
        return pngMetaDataHandler.updateMetaData(pngImage);
    }

    /**
     * Create the pop-up menu
     */
    private void createPopupMenu() {
        popup = new JPopupMenu();

        // Copy
        popup.add(copyActionTo(new JMenuItem()));
        // Paste
        popup.add(pasteActionTo(new JMenuItem()));
    }

    private JMenuItem copyActionTo(JMenuItem menuItem) {
        menuItem.setAction(TransferHandler.getCopyAction());
        menuItem.setText("Copy");
//        menuItem.setMnemonic(KeyEvent.VK_C);
//        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        return menuItem;
    }

    private JMenuItem pasteActionTo(JMenuItem menuItem) {
        menuItem.setAction(TransferHandler.getPasteAction());
        menuItem.setText("Paste");
//        menuItem.setMnemonic(KeyEvent.VK_P);
//        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        return menuItem;
    }

    /**
     * DragSourceListener -- allows you to start a drag on the PNGPanel.
     */
    private class DragSourceListener extends MouseAdapter {

        private MouseEvent firstMouseEvent = null;

        @Override
        public void mousePressed(MouseEvent e) {
            if (pngImage == null)
                return;

            if (isButton1(e)) {
                firstMouseEvent = e;
                e.consume();
            }
            showPopup(e);
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            if (pngImage == null)
                return;

            if (isButton1(e)) {
                firstMouseEvent = null;
            }

            showPopup(e);
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            if (pngImage == null)
                return;

            if (firstMouseEvent != null) {
                e.consume();

                int dx = Math.abs(e.getX() - firstMouseEvent.getX());
                int dy = Math.abs(e.getY() - firstMouseEvent.getY());
                //Arbitrarily define a 5-pixel shift as the official beginning of a drag.
                if (dx > 5 || dy > 5) {
                    //This is a drag, not a click.
                    JComponent c = (JComponent) e.getSource();
                    TransferHandler handler = c.getTransferHandler();
                    //Tell the transfer handler to initiate the drag.
                    handler.exportAsDrag(c, firstMouseEvent, TransferHandler.COPY);
                    firstMouseEvent = null;
                }
            }
        }

        private boolean isButton1(MouseEvent e) {
            return (e.getModifiers() & InputEvent.BUTTON1_MASK) == InputEvent.BUTTON1_MASK;
        }

        private void showPopup(MouseEvent e) {
            if (e.isPopupTrigger()) {
                popup.show(e.getComponent(), e.getX(), e.getY());
            }
        }
    }
}
