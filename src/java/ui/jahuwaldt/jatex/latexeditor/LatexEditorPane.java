/*
 * LatexEditorPane.java
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

package java.ui.jahuwaldt.jatex.latexeditor;

import java.ui.jahuwaldt.jatex.latexeditor.symbolsmenu.AmsAccentMenu;
import java.ui.jahuwaldt.jatex.latexeditor.symbolsmenu.AmsArrowMenu;
import java.ui.jahuwaldt.jatex.latexeditor.symbolsmenu.AmsBinaryOperatorMenu;
import java.ui.jahuwaldt.jatex.latexeditor.symbolsmenu.AmsDelimiterMenu;
import java.ui.jahuwaldt.jatex.latexeditor.symbolsmenu.AmsHebrewMenu;
import java.ui.jahuwaldt.jatex.latexeditor.symbolsmenu.AmsMiscellaneousMenu;
import java.ui.jahuwaldt.jatex.latexeditor.symbolsmenu.AmsNegatedArrowMenu;
import java.ui.jahuwaldt.jatex.latexeditor.symbolsmenu.AmsNegatedRelationOneMenu;
import java.ui.jahuwaldt.jatex.latexeditor.symbolsmenu.AmsNegatedRelationTwoMenu;
import java.ui.jahuwaldt.jatex.latexeditor.symbolsmenu.AmsRelationOneMenu;
import java.ui.jahuwaldt.jatex.latexeditor.symbolsmenu.AmsRelationTwoMenu;
import java.ui.jahuwaldt.jatex.latexeditor.symbolsmenu.AmsSpecialMenu;
import java.ui.jahuwaldt.jatex.latexeditor.symbolsmenu.LatexAccentMenu;
import java.ui.jahuwaldt.jatex.latexeditor.symbolsmenu.LatexArrowMenu;
import java.ui.jahuwaldt.jatex.latexeditor.symbolsmenu.LatexBinaryOperatorMenu;
import java.ui.jahuwaldt.jatex.latexeditor.symbolsmenu.LatexConstructMenu;
import java.ui.jahuwaldt.jatex.latexeditor.symbolsmenu.LatexDelimiterMenu;
import java.ui.jahuwaldt.jatex.latexeditor.symbolsmenu.LatexGreekLowercaseMenu;
import java.ui.jahuwaldt.jatex.latexeditor.symbolsmenu.LatexGreekUppercaseMenu;
import java.ui.jahuwaldt.jatex.latexeditor.symbolsmenu.LatexLogLikeMenu;
import java.ui.jahuwaldt.jatex.latexeditor.symbolsmenu.LatexMiscellaneousMenu;
import java.ui.jahuwaldt.jatex.latexeditor.symbolsmenu.LatexPunctuationMenu;
import java.ui.jahuwaldt.jatex.latexeditor.symbolsmenu.LatexRelationMenu;
import java.ui.jahuwaldt.jatex.latexeditor.symbolsmenu.LatexVariableSizeMenu;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.JEditorPane;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.KeyStroke;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoManager;


/**
 * A JEditorPane for editing LaTeX code.
 * 
 * <p>  Modified by:  Joseph A. Huwaldt  </p>
 *
 * @author Jess Thrysoee, 2006
 * @version May 14, 2013
 */
public class LatexEditorPane extends JEditorPane {
    /**
     * The resource bundle for this class.
     */
    private final ResourceBundle resBundle;

    private static final Logger log = Logger.getLogger(LatexEditorPane.class.getName());
    // Listener for the edits on the current document.
    private final UndoableEditListener undoHandler = new UndoHandler();
    // UndoManager that we add edits to.
    private final UndoManager undo = new UndoManager();
    private UndoAction undoAction;
    private RedoAction redoAction;
    private JPopupMenu popup;

    /**
     * Creates a new instance of LatexEditorPane
     */
    public LatexEditorPane(ResourceBundle resBundle) {
        this.resBundle = resBundle;
        setContentType("text/latex");
        setEditorKit(new LatexEditorKit());

        // Add this as a listener for undoable edits.
        getDocument().addUndoableEditListener(undoHandler);

        setDragEnabled(true);
                
        createActions();
        createPopupMenu();
        MouseListener popupListener = new PopupListener();
        addMouseListener(popupListener);
        
        setPreferredSize(new Dimension(350,100));
    }

    /**
     * Create the pop-up menu
     */
    private void createPopupMenu() {
        popup = new JPopupMenu();

        // Undo
        popup.add(undoActionTo(new JMenuItem()));
        // Redo
        popup.add(redoActionTo(new JMenuItem()));

        popup.addSeparator();

        // Cut
        popup.add(cutActionTo(new JMenuItem()));
        // Copy
        popup.add(copyActionTo(new JMenuItem()));
        // Paste
        popup.add(pasteActionTo(new JMenuItem()));
    }

    public void addSymbolsMenu() {
        symbolsMenuTo(popup);
    }

    /**
     * Add Math symbols menus to the pop-up menu
     */
    public void symbolsMenuTo(JPopupMenu menu) {
        menu.addSeparator();
        menu.add(getSymbolMenu());
    }

    public void symbolsMenuTo(JMenu menu, int menuIndex) {
        menu.insertSeparator(menuIndex);
        menu.insert(getSymbolMenu(), ++menuIndex);
    }

    private JMenu getSymbolMenu() {

        JMenu symbolMenu = new JMenu(resBundle.getString("insertSymbolLabel"));


        symbolMenu.add(new LatexGreekUppercaseMenu(this));
        symbolMenu.add(new LatexGreekLowercaseMenu(this));

        symbolMenu.add(new LatexBinaryOperatorMenu(this));
        symbolMenu.add(new LatexRelationMenu(this));
        symbolMenu.add(new LatexArrowMenu(this));
        symbolMenu.add(new LatexPunctuationMenu(this));
        symbolMenu.add(new LatexMiscellaneousMenu(this));
        symbolMenu.add(new LatexVariableSizeMenu(this));
        symbolMenu.add(new LatexLogLikeMenu(this));
        symbolMenu.add(new LatexDelimiterMenu(this));
        symbolMenu.add(new LatexConstructMenu(this));
        symbolMenu.add(new LatexAccentMenu(this));

        JMenu amsMenu = new JMenu(resBundle.getString("AmsSymbolLabel"));
        symbolMenu.add(amsMenu);

        amsMenu.add(new AmsHebrewMenu(this));
        amsMenu.add(new AmsArrowMenu(this));
        amsMenu.add(new AmsNegatedArrowMenu(this));

        amsMenu.add(new AmsRelationOneMenu(this));
        amsMenu.add(new AmsRelationTwoMenu(this));
        amsMenu.add(new AmsNegatedRelationOneMenu(this));
        amsMenu.add(new AmsNegatedRelationTwoMenu(this));

        amsMenu.add(new AmsBinaryOperatorMenu(this));
        amsMenu.add(new AmsMiscellaneousMenu(this));
        amsMenu.add(new AmsAccentMenu(this));
        amsMenu.add(new AmsDelimiterMenu(this));
        amsMenu.add(new AmsSpecialMenu(this));

        return symbolMenu;
    }

    /**
     * PopupListener
     */
    class PopupListener extends MouseAdapter {

        @Override
        public void mousePressed(MouseEvent e) {
            showPopup(e);
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            showPopup(e);
        }

        private void showPopup(MouseEvent e) {
            if (e.isPopupTrigger()) {
                popup.show(e.getComponent(), e.getX(), e.getY());
            }
        }
    }

    /**
     * Only insert text if it is different from the old text. This is done to prevent
     * superflous undo events. Not a viable solution if the texts get large.
     */
    @Override
    public void setText(String t) {
        String oldText = getText();
        if (oldText != null && !oldText.equals(t)) {
            super.setText(t);
        }
    }

    /**
     * Resets the undo manager.
     */
    protected void resetUndoManager() {
        undo.discardAllEdits();
        undoAction.update();
        redoAction.update();
    }

    /**
     * Undo Action
     */
    class UndoAction extends AbstractAction {

        public UndoAction() {
            super(resBundle.getString("undoItemText"));
            setEnabled(false);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                undo.undo();
            } catch (CannotUndoException ex) {
                log.log(Level.WARNING, "Unable to undo: {0}", ex);
                ex.printStackTrace();
            }
            update();
            redoAction.update();
        }

        protected void update() {
            if (undo.canUndo())
                setEnabled(true);
            else
                setEnabled(false);
        }
    }

    /**
     * Redo Action
     */
    class RedoAction extends AbstractAction {

        public RedoAction() {
            super(resBundle.getString("redoItemText"));
            setEnabled(false);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                undo.redo();
            } catch (CannotRedoException ex) {
                log.log(Level.WARNING, "Unable to redo: {0}", ex);
                ex.printStackTrace();
            }
            update();
            undoAction.update();
        }

        protected void update() {
            if (undo.canRedo())
                setEnabled(true);
            else
                setEnabled(false);
        }
    }

    /**
     * UndoHandler
     */
    class UndoHandler implements UndoableEditListener {

        /**
         * Messaged when the Document has created an edit, the edit is added to
         * <code>undo</code>, an instance of UndoManager.
         */
        @Override
        public void undoableEditHappened(UndoableEditEvent e) {
            undo.addEdit(e.getEdit());
            undoAction.update();
            redoAction.update();
        }
    }

    /**
     * Create Actions
     */
    private void createActions() {
        undoAction = new UndoAction();
        getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_Z, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()), "Undo");
        getActionMap().put(resBundle.getString("undoItemText"), undoAction);

        redoAction = new RedoAction();
        getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_Y, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()), "Redo");
        getActionMap().put(resBundle.getString("redoItemText"), redoAction);
    }

    public JMenuItem undoActionTo(JMenuItem menuItem) {
        menuItem.setAction(undoAction);
        menuItem.setMnemonic(KeyEvent.VK_U);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        return menuItem;
    }

    public JMenuItem redoActionTo(JMenuItem menuItem) {
        menuItem.setAction(redoAction);
        menuItem.setMnemonic(KeyEvent.VK_R);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        return menuItem;
    }

    public JMenuItem cutActionTo(JMenuItem menuItem) {
        menuItem.setAction(new LatexEditorKit.CutAction());
        menuItem.setText(resBundle.getString("cutItemText"));
        menuItem.setMnemonic(KeyEvent.VK_T);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        return menuItem;
    }

    public JMenuItem copyActionTo(JMenuItem menuItem) {
        menuItem.setAction(new LatexEditorKit.CopyAction());
        menuItem.setText(resBundle.getString("copyItemText"));
        menuItem.setMnemonic(KeyEvent.VK_C);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        return menuItem;
    }

    public JMenuItem pasteActionTo(JMenuItem menuItem) {
        menuItem.setAction(new LatexEditorKit.PasteAction());
        menuItem.setText(resBundle.getString("pasteItemText"));
        menuItem.setMnemonic(KeyEvent.VK_P);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
        return menuItem;
    }

    /**
     * always return true to prevent a horizontal scrollbar
     */
    @Override
    public boolean getScrollableTracksViewportWidth() {
        return true;
    }
}
