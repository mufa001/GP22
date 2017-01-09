/*
 * MathSymbolAction.java
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

package gp.math.jahuwaldt.jatex.latexeditor;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;

/**
 * An action that provides an icon from the application resources and
 * transfers the specified LaTeX command to the action listener.
 * 
 * <p>  Modified by:  Joseph A. Huwaldt  </p>
 *
 * @author Jess Thrysoee, 2006
 * @version May 14, 2013
 */
public class MathSymbolAction extends AbstractAction {

    private static final Logger log = Logger.getLogger(MathSymbolAction.class.getName());
    private final LatexEditorPane editorPane;
    private final String latexCommand;
    private final int caretOffset;
    public ResizableIcon icon;

    /**
     * Create a MathSymbolAction that controls the display of math symbol menu and button items,
     * and passes the specified latex command to the specified editor when the menu item or
     * button are clicked.
     * 
     * @param editorPane    The LaTeX editor to receive the latex command.
     * @param label         The label for this action.
     * @param latexCommand  The LaTeX command to pass to the editor.
     * @param iconResource  The path to the icon resource for this action.
     */
    public MathSymbolAction(LatexEditorPane editorPane, String label, String latexCommand, String iconResource) {
        this(editorPane, label, latexCommand, iconResource, -1);
    }

    /**
     * Create a MathSymbolAction that controls the display of math symbol menu and button items,
     * and passes the specified latex command to the specified editor when the menu item or
     * button are clicked.  This constructor also takes a caret offset which indicates how many
     * characters from the start of the latexCommand where the caret in the editor should be positioned.
     * 
     * @param editorPane    The LaTeX editor to receive the latex command.
     * @param label         The label for this action.
     * @param latexCommand  The LaTeX command to pass to the editor.
     * @param iconResource  The path to the icon resource for this action.
     * @param caretOffset   The position, relative to the start of the latexCommand where the caret
     *                      should be positioned in the editor upon completion of this action.
     *                      Pass -1 to not change the caret position.
     */
    public MathSymbolAction(LatexEditorPane editorPane, String label, String latexCommand, String iconResource, int caretOffset) {
        super(label);

        this.editorPane = editorPane;
        this.latexCommand = latexCommand;
        if (iconResource != null) {
            this.icon = new ResizableIcon(iconResource);
            putValue(Action.SMALL_ICON, icon);
        }
        this.caretOffset = caretOffset;
    }

    /**
     * Update editor with
     * <code>latexCommand</code> when invoked.
     *
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        //  Insert the latex command.
        editorPane.replaceSelection(latexCommand);

        //  Set a custom caret position if desired.
        if (caretOffset >= 0) {
            int caret = editorPane.getCaretPosition();
            caret -= latexCommand.length() - caretOffset;
            editorPane.setCaretPosition(caret);
        }

        //  Have the editor request the focus.
        editorPane.requestFocusInWindow();
    }

    /**
     * Icon which is meant to be resized to the max width of its siblings in the JMenu.
     * This is so the menu item can be aligned even if their icons have different widths.
     */
    public static final class ResizableIcon extends ImageIcon {

        private Image image;
        private int width;
        private int height;

        /**
         *
         */
        public ResizableIcon(String iconResource) {
            if (iconResource != null) {
                loadImage(iconResource);
            }
        }

        @Override
        public void paintIcon(Component c, Graphics g, int x_ignored, int y) {
            if (image != null) {
                // center image
                int x = (width - image.getWidth(null)) / 2 + 4;
                //y = (height - image.getHeight(null))/2;
                // Paint a PNG having translucency information.
                g.drawImage(image, x, y, null);
            }
        }

        /**
         * Get icon from resource.
         */
        public void loadImage(String resource) {
            InputStream in = getClass().getClassLoader().getResourceAsStream(resource);
            if (in == null) {
                log.log(Level.WARNING, "{0} not found.", resource);
            } else {
                try {
                    try {
                        byte[] buffer = new byte[in.available()];
                        in.read(buffer);
                        image = Toolkit.getDefaultToolkit().createImage(buffer);

                        setImage(image);

                        width = image.getWidth(null);
                        height = image.getHeight(null);
                    } finally {
                        in.close();
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }

        /**
         * Gets the width of the icon.
         *
         * @return the width in pixels of this icon
         */
        public void setIconWidth(int width) {
            this.width = width;
        }

        /**
         * Gets the width of the icon.
         *
         * @return the width in pixels of this icon
         */
        @Override
        public int getIconWidth() {
            return width;
        }

        /**
         * Gets the height of the icon.
         *
         * @return the height in pixels of this icon
         */
        @Override
        public int getIconHeight() {
            return height;
        }

        /**
         * Gets the height of the icon.
         *
         * @return the height of the icon
         */
        public int getAccessibleIconHeight() {
            return height;
        }

        /**
         * Gets the width of the icon.
         *
         * @return the width of the icon
         */
        public int getAccessibleIconWidth() {
            return width;
        }
    }
}
