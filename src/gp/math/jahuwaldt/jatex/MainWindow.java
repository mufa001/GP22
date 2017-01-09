/*
 *  MainWindow  -- The main application window for the JaTeX program.
 *
 *  Copyright (C) 2013-2015, Joseph A. Huwaldt
 *
 *  This program is free software; you can redistribute it and/or
 *  modify it under the terms of the GNU General Public License
 *  as published by the Free Software Foundation; either version 2
 *  of the License, or (at your option) any later version.
 *  
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program; if not, write to the Free Software
 *  Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 *  Or visit:  http://www.gnu.org/licenses/gpl.html
 */
package gp.math.jahuwaldt.jatex;


//import gp.mcqseter;
import gp.math.jahuwaldt.jatex.latexeditor.LatexEditorPane;
import gp.math.jahuwaldt.jatex.latexeditor.MathSymbolButton;
import gp.math.jahuwaldt.swing.AppUtilities;
import gp.math.jahuwaldt.swing.MDIApplication;
import gp.math.jahuwaldt.swing.MainApp;
import gp.math.jahuwaldt.swing.Preferences;
import gp.math.jahuwaldt.swing.QuitListener;
import gp.math.jahuwaldt.swing.SpringUtilities;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetAdapter;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;

import java.io.*;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.KeyStroke;
import javax.swing.SpringLayout;
import javax.swing.SwingWorker;
import javax.swing.WindowConstants;
import net.roydesign.app.AboutJMenuItem;
import net.roydesign.app.PreferencesJMenuItem;
import net.roydesign.app.QuitJMenuItem;


/**
 * Main window for the JaTeX program. Most of the program code is based here.
 * 
 * <p> Modified by: Joseph A. Huwaldt </p>
 * 
 * @author Joseph A. Huwaldt Date: May 10, 2013
 * @version January 9, 2015
 */
public class MainWindow extends JFrame implements MainApp{

    //  Reference to the main application object for this program.
    //public final MainApp app;
    
    //  The application preferences.
    private AppPreferences prefs;
    
    //	The LaTeX editor pane
    private LatexEditorPane _latexEditorPane;
    
    //  The PNG image pane.
    private PNGPanel _pngPanel;
    
    //  The PNG meta-data handler.
    private PNGMetaDataHandler _pngMetaDataHandler;
    
    //  The object that manages running the native latex and dvipng processes.
    private RunLatex _nativeRuntime;
    
    //  The list containing the font size selection.
    private JComboBox _fontSizeList;
    
    //  Some menu items we need.
    private JMenu _editMenu;
    private JMenuItem _undoMenuItem;
    private JMenuItem _redoMenuItem;
    private JMenuItem _cutMenuItem;
    private JMenuItem _copyMenuItem;
    private JMenuItem _pasteMenuItem;
    
    public static final ResourceBundle RESBUNDLE = ResourceBundle.getBundle("gp.math.appStrings", Locale.getDefault());
    

    private MDIApplication guiApp;

    /**
     * The user preferences for this program.
     * Constructor for our application window.
     *
     * @param application A reference to the application that created this
     * window.
     * @throws java.lang.NoSuchMethodException if an internal method is absent.
     */
    @SuppressWarnings("LeakingThisInConstructor")
    public JTextArea textArea = null;
    public JTextField textField = null;
    public JTextPane textPaneEssay = null;
    public JTextArea textAreaStruct = null;
    public int curPos;
    
    public MainWindow(JTextField textField,JTextArea textArea,JTextPane textPaneEssay,JTextArea textAreaEssay,int curPos) throws NoSuchMethodException {
    	
    	 
    	
        super(RESBUNDLE.getString("appName") /*+ ", "
                + application.getResourceBundle().getString("version") + " "
                + application.getResourceBundle().getString("appVersion")*/);

        //  Have the window dispose of itself when it closes.
        this.textField = textField;
        this.textArea = textArea;
        this.textPaneEssay = textPaneEssay;
        this.textAreaStruct = textAreaStruct;
        this.curPos = curPos;
        this.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        try {
			guiApp = new AppGUI(RESBUNDLE, this);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        //	Load in the user preferences.
        prefs = new AppPreferences(this);

        // Check the configuration.
        boolean setPrefs = prefs.hasLatex();

        //  Save a reference to the application instance.
        //app = application;
        prefs = (AppPreferences) this.getPreferences();

        //  Define the objects we need.
        _pngPanel = new PNGPanel();
        _latexEditorPane = new LatexEditorPane(this.getResourceBundle());
        _pngMetaDataHandler = new PNGMetaDataHandler(this, _pngPanel, _latexEditorPane, prefs);
        _pngPanel.setPNGMetaDataHandler(_pngMetaDataHandler);

        _latexEditorPane.setFont(new java.awt.Font("Monospaced", 0, 12));
        _latexEditorPane.setAutoscrolls(false);

        //  Define the object that runs the native processes.
        _nativeRuntime = new RunLatex(this.getResourceBundle(), prefs);

        // Layout the display of this window.
        layoutDisplay();

        //	Set up the menu bar.
        JMenuBar menuBar = createMenuBar();
        this.setJMenuBar(menuBar);

        //  Add editorPane actions to the main menu
        _latexEditorPane.undoActionTo(_undoMenuItem);
        _latexEditorPane.redoActionTo(_redoMenuItem);
        _latexEditorPane.cutActionTo(_cutMenuItem);
        _latexEditorPane.copyActionTo(_copyMenuItem);
        _latexEditorPane.pasteActionTo(_pasteMenuItem);
        _latexEditorPane.addSymbolsMenu();
        _latexEditorPane.symbolsMenuTo(_editMenu, 6);

        //	Add this window to the Window menu.
        this.getGUIApplication().addWindow(this);

        //	Register a quit listener so that the user can save unsaved changes before the application quits.
        this.getGUIApplication().addQuitListener(new QuitListener() {
            @Override
            public boolean quit() {
                return handleQuit();
            }
        });

        //  Size the window.
        this.pack();

        //  Position the window so that each new window can be seen.
        AppUtilities.positionWindow(this, getWidth(), getHeight());

        //  Add support for drag and drop to this image topPanel.
        DropTarget droptarget = new DropTarget(_pngPanel, DnDConstants.ACTION_COPY, new FileDTListener(), true);

        //  Have the editor field request the focus.
        _latexEditorPane.requestFocusInWindow();
    }
    
    /*public MainWindow(MainApp app) throws NoSuchMethodException {
    	
    }*/


    //-----------------------------------------------------------------------------------
    /**
     * A class that listens for Drag & Drop events that contain files. When a
     * drop event is received, this class reads in any PNG files that were
     * dropped.
     */
    private class FileDTListener extends DropTargetAdapter {

        /**
         * Called when a drag operation is ongoing, while the mouse pointer is
         * still over the operable part of the drop site for the
         * <code>DropTarget</code> registered with this listener. This
         * implementation will accept only COPY drop actions for
         * javaFileListFlavor flavors.
         */
        @Override
        public void dragOver(DropTargetDragEvent ev) {
            dropTargetDrag(ev);
        }

        /**
         * Called if the user has modified the current drop gesture. This
         * implementation will accept only COPY drop actions for
         * javaFileListFlavor flavors.
         */
        @Override
        public void dropActionChanged(DropTargetDragEvent ev) {
            dropTargetDrag(ev);
        }

        private void dropTargetDrag(DropTargetDragEvent ev) {
            //	Accept only file list compatible data.
            if (ev.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) {
                ev.acceptDrag(DnDConstants.ACTION_COPY);	//	Accept only a COPY type of transfer.
            } else {
                ev.rejectDrag();
            }
        }

        /**
         * Called when the drag operation has terminated with a drop on the
         * operable part of the drop site for the <code>DropTarget</code>
         * registered with this listener. This implementation will process the
         * drop event, extract the file list and load the first PNG file found
         * in the list.
         */
        @Override
        public void drop(DropTargetDropEvent ev) {

            //	Accept only COPY drops.
            ev.acceptDrop(DnDConstants.ACTION_COPY);

            try {
                ev.getSource();
                List<File> files = (List<File>) ev.getTransferable().getTransferData(DataFlavor.javaFileListFlavor);

                //	Process the files, one at a time.
                for (File theFile : files) {

                    //	Is the file a real file (and not a directory)?
                    if (theFile.isFile()) {
                        //  Is the file a PNG image file?
                        PNGImage mImage;
                        try {
                            mImage = new PNGImage(theFile);

                            //  We have a valid PNG image.
                            if (_pngPanel.updateMetaData(mImage)) {
                                _pngPanel.setPNGImage(mImage);
                                break;
                            }

                        } catch (Exception e) {
                            //  Not a properly formatted PNG image file so continue.
                        }
                    }
                }

            } catch (UnsupportedFlavorException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            ev.dropComplete(true);
        }
    }
	
    /**
     * Sets the title for this frame to the specified string. Also notifies the
     * main application class so that the "Windows" menu gets updated too.
     *
     * @param title The title to be displayed in the frame's border. A null
     * value is treated as an empty string, "".
     */
    @Override
    public void setTitle(String title) {
        super.setTitle(title);
        this.getGUIApplication().windowTitleChanged(this);
    }

    /**
     * Layouts the contents of this main application window.
     *
     * @param theData The time line data being plotted.
     */
    private void layoutDisplay() throws NoSuchMethodException {
        ResourceBundle resBundle = this.getResourceBundle();

        // Layout the main application window's user interface.
        //  Create a center topPanel that shows the equation text, the rendered equation, and a palette of options.
        JPanel centerPanel = new JPanel(new SpringLayout());
        centerPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(5, 10, 5, 10),
                BorderFactory.createEtchedBorder())
        );

        //  A palette with a lot of options.
        JTabbedPane palette = new JTabbedPane(JTabbedPane.TOP);
        palette.setFont(new java.awt.Font("SansSerif", 0, 10));
        centerPanel.add(palette);
        palette.add(resBundle.getString("commonPaletteLabel"), createCommonPalette());
        palette.add(resBundle.getString("greekPaletteLabel"), createGreekPalette());
        palette.add(resBundle.getString("binaryPaletteLabel"), createBinaryPalette());
        palette.add(resBundle.getString("relationPaletteLabel"), createRelationPalette());
        palette.add(resBundle.getString("arrowsPaletteLabel"), createArrowsPalette());
        palette.add(resBundle.getString("miscPaletteLabel"), createMiscPalette());
        palette.add(resBundle.getString("functionsPaletteLabel"), createFunctionsPalette());
        palette.add(resBundle.getString("accentsPaletteLabel"), createAccentsPalette());

        //  The editor pane.
        JScrollPane editorScrollPane = new JScrollPane(_latexEditorPane);
        editorScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        editorScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        //  A split pane with the editor on top and the rendering on the bottom.
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, true, editorScrollPane, _pngPanel);
        splitPane.setDividerLocation(0.5);
        centerPanel.add(splitPane);

        //	Configure the spring layout for the topPanel.
        SpringUtilities.makeCompactGrid(centerPanel, 2, 1, 0, 0, 5, 5);

        //	Create a button panel at the bottom.
        Box bottomBtnPanel = Box.createHorizontalBox();
        bottomBtnPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));

        //  Create a font size combo-box
        Box comboBox = Box.createHorizontalBox();
        bottomBtnPanel.add(comboBox);
        comboBox.add(new JLabel(resBundle.getString("fontSizeLabel")));
        Integer[] fontSizes = {5, 9, 10, 11, 12, 13, 14, 18, 24, 36, 48, 64, 72, 96, 144, 288};
        _fontSizeList = new JComboBox(fontSizes);
        updateFontPointSizeDisplay();
        _fontSizeList.setMaximumSize(_fontSizeList.getPreferredSize());

        //  Change the point size preferences if the user changes the selection.
        _fontSizeList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox cb = (JComboBox) e.getSource();
                int ptSize = (Integer) cb.getSelectedItem();
                prefs.setPointSize(ptSize);
            }
        });

        //  Change the combo-box display if the preferences are changed elsewhere.
        prefs.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            @Override
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                if ("PointSize".equals(evt.getPropertyName())) {
                    updateFontPointSizeDisplay();
                }
            }
        });

        comboBox.add(_fontSizeList);
        comboBox.add(new JLabel(resBundle.getString("fontSizeUnit")));
        comboBox.add(Box.createGlue());
		
		
			
        //	Create a Typeset button.
        JButton btn = new JButton(resBundle.getString("typesetBtnText"));
        btn.addActionListener(AppUtilities.getActionListenerForMethod(this, "handleTypeset"));
		
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.setToolTipText(resBundle.getString("typesetBtnToolTipText"));
        btn.setEnabled(true);
        //this.getRootPane().setDefaultButton(btn);   //  Make this the default button.
        bottomBtnPanel.add(Box.createGlue());
        bottomBtnPanel.add(btn);
		
		
		JButton donebtn = new JButton(resBundle.getString("doneBtnText"));
        donebtn.addActionListener(AppUtilities.getActionListenerForMethod(this, "handleDone"));
        donebtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        donebtn.setToolTipText(resBundle.getString("doneBtnToolTipText"));
        donebtn.setEnabled(true);
        this.getRootPane().setDefaultButton(donebtn);   //  Make this the default button.
        bottomBtnPanel.add(donebtn);

        // Layout this window's content pane.
        Container cp = this.getContentPane();
        cp.add(centerPanel, BorderLayout.CENTER);
        cp.add(bottomBtnPanel, BorderLayout.SOUTH);
    }

    /**
     * Method that updates the font point size combo-box to display the
     * currently stored point size.
     */
    private void updateFontPointSizeDisplay() {
        int ptSize = prefs.getPointSize();
        if (ptSize == (Integer) _fontSizeList.getSelectedItem()) {
            return; //  Already selected.
        }
        int numSizes = _fontSizeList.getItemCount();
        for (int i = 0; i < numSizes; ++i) {
            int item = (Integer) _fontSizeList.getItemAt(i);
            if (item == ptSize) {
                _fontSizeList.setSelectedItem(item);
                break;
            }
        }
    }

    /**
     * Method that creates a palette of options for common equation elements.
     */
    private Container createCommonPalette() {
        ResourceBundle resBundle = this.getResourceBundle();
        String imgPath = resBundle.getString("btnImagePath");
        
        Box panel = Box.createVerticalBox();
        JPanel topPanel = new JPanel(new SpringLayout());
        panel.add(topPanel);
        
        topPanel.add(new MathSymbolButton(_latexEditorPane, "^{}", resBundle.getString("superDesc"), imgPath+"super.png", 2));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "_{}", resBundle.getString("subDesc"), imgPath+"sub.png", 2));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\frac{}{}", resBundle.getString("fracDesc"), imgPath+"fraction.png", 6));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\sqrt{}", resBundle.getString("sqrtDesc"), imgPath+"root.png", 6));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\sqrt[]{}", resBundle.getString("rootnDesc"), imgPath+"rootn.png", 6));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\ldots", resBundle.getString("ldotsDesc"), imgPath+"lowEllipsis.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\cdots", resBundle.getString("cdotsDesc"), imgPath+"centeredEllipsis.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\vdots", resBundle.getString("vdotsDesc"), imgPath+"verticalDots.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\ddots", resBundle.getString("ddotsDesc"), imgPath+"diagonalDots.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\sum", resBundle.getString("sumDesc"), imgPath+"sum.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\prod", resBundle.getString("prodDesc"), imgPath+"prod.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\partial", resBundle.getString("partialDesc"), imgPath+"partial.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\int", resBundle.getString("intDesc"), imgPath+"int.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\oint", resBundle.getString("ointDesc"), imgPath+"oint.png"));
        topPanel.add(createButtonPlaceHolder());
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\overline{}", resBundle.getString("overlineDesc"), imgPath+"overline.png", 10));
        
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\left(", resBundle.getString("lParenDesc"), imgPath+"leftParenthesis.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\right)", resBundle.getString("rParenDesc"), imgPath+"rightParenthesis.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\left(\\right)", resBundle.getString("coupleParenDesc"), imgPath+"coupleParenthesis.png", 6));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\left[", resBundle.getString("lBracketDesc"), imgPath+"leftBracket.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\right]", resBundle.getString("rBracketDesc"), imgPath+"rightBracket.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\left[\\right]", resBundle.getString("coupleBracketDesc"), imgPath+"coupleBracket.png", 6));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\left\\{", resBundle.getString("lBraceDesc"), imgPath+"leftBrace.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\right\\}", resBundle.getString("rBraceDesc"), imgPath+"rightBrace.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\left\\{\\right\\}", resBundle.getString("coupleBraceDesc"), imgPath+"coupleBrace.png", 7));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\langle", resBundle.getString("langleDesc"), imgPath+"leftAngle.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\rangle", resBundle.getString("rangleDesc"), imgPath+"rightAngle.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\langle \\rangle", resBundle.getString("copuleAngleDesc"), imgPath+"coupleAngle.png", 8));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\left|", resBundle.getString("lvbarDesc"), imgPath+"leftvbar.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\right|", resBundle.getString("rvbarDesc"), imgPath+"rightvbar.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\left| \\right|", resBundle.getString("coupleVbarDesc"), imgPath+"couplevbar.png", 7));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\underline{}", resBundle.getString("underlineDesc"), imgPath+"underline.png", 11));
        
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\bigcap", resBundle.getString("bigcapDesc"), imgPath+"bigcap.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\bigcup", resBundle.getString("bigcupDesc"), imgPath+"bigcup.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\bigsqcup", resBundle.getString("bigsqcupDesc"), imgPath+"bigsqcup.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\bigvee", resBundle.getString("bigveeDesc"), imgPath+"bigvee.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\bigwedge", resBundle.getString("bigwedgeDesc"), imgPath+"bigwedge.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\bigodot", resBundle.getString("bigodotDesc"), imgPath+"bigodot.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\bigotimes", resBundle.getString("bigotimesDesc"), imgPath+"bigotimes.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\bigoplus", resBundle.getString("bigoplusDesc"), imgPath+"bigoplus.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\biguplus", resBundle.getString("biguplusDesc"), imgPath+"biguplus.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\lfloor", resBundle.getString("lfloorDesc"), imgPath+"lfloor.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\lceil", resBundle.getString("lceilDesc"), imgPath+"lceil.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\rfloor", resBundle.getString("rfloorDesc"), imgPath+"rfloor.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\rceil", resBundle.getString("rceilDesc"), imgPath+"rceil.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\overbrace{}", resBundle.getString("overbraceDesc"), imgPath+"overbrace.png", 11));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\underbrace{}", resBundle.getString("underbraceDesc"), imgPath+"underbrace.png", 12));
        topPanel.add(createButtonPlaceHolder());
        
        //	Configure the spring layout for the topPanel.
        SpringUtilities.makeCompactGrid(topPanel, 3, 16, 0, 0, 0, 0);
        
        panel.add(Box.createGlue());
        
        JPanel botPanel = new JPanel(new SpringLayout());
        panel.add(botPanel);
        botPanel.add(new MathSymbolButton(_latexEditorPane, "\\,", resBundle.getString("thinSpcDesc"), null));
        botPanel.add(new MathSymbolButton(_latexEditorPane, "\\!", resBundle.getString("negThinSpcDesc"), null));
        botPanel.add(new MathSymbolButton(_latexEditorPane, "\\:", resBundle.getString("mediumSpcDesc"), null));
        botPanel.add(new MathSymbolButton(_latexEditorPane, "\\;", resBundle.getString("thickSpcDesc"), null));
        botPanel.add(new MathSymbolButton(_latexEditorPane, "\\ ", resBundle.getString("wordSpcDesc"), null));
        botPanel.add(new MathSymbolButton(_latexEditorPane, "\\stackrel{}{}", resBundle.getString("stackingDesc"), null, 10));
        int num = botPanel.getComponentCount();
        for (int i=0; i < num; ++i) {
            JButton btn = (JButton)botPanel.getComponent(i);
            btn.setFont(new java.awt.Font("SansSerif", 0, 10));
            btn.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(2,2,2,2),
                BorderFactory.createRaisedBevelBorder()));
        }
        
        //	Configure the spring layout for the botPanel.
        SpringUtilities.makeCompactGrid(botPanel, 1, 6, 0, 0, 2, 2);

        Box output = Box.createHorizontalBox();
        output.setOpaque(true);
        output.add(panel);
        output.add(Box.createGlue());
        
        return output;
    }
    
    /**
     * Method that creates a palette of options for Greek letters.
     */
    private Container createGreekPalette() {
        ResourceBundle resBundle = this.getResourceBundle();
        String imgPath = resBundle.getString("btnImagePath");
        
        Box panel = Box.createVerticalBox();
        JPanel topPanel = new JPanel(new SpringLayout());
        panel.add(topPanel);
        
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\alpha", resBundle.getString("alphaDesc"), imgPath+"alpha.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\beta", resBundle.getString("betaDesc"), imgPath+"beta.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\gamma", resBundle.getString("gammaDesc"), imgPath+"gamma.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\delta", resBundle.getString("deltaDesc"), imgPath+"delta.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\epsilon", resBundle.getString("epsilonDesc"), imgPath+"epsilon.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\varepsilon", resBundle.getString("altEpsDesc"), imgPath+"varepsilon.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\zeta", resBundle.getString("zetaDesc"), imgPath+"zeta.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\eta", resBundle.getString("etaDesc"), imgPath+"eta.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\theta", resBundle.getString("thetaDesc"), imgPath+"theta.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\vartheta", resBundle.getString("altThetaDesc"), imgPath+"vartheta.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\iota", resBundle.getString("iotaDesc"), imgPath+"iota.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\kappa", resBundle.getString("kappaDesc"), imgPath+"kappa.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\lambda", resBundle.getString("lambdaDesc"), imgPath+"lambda.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\mu", resBundle.getString("muDesc"), imgPath+"mu.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\nu", resBundle.getString("nuDesc"), imgPath+"nu.png"));
        
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\xi", resBundle.getString("xiDesc"), imgPath+"xi.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "o", resBundle.getString("omicronDesc"), imgPath+"omicron.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\pi", resBundle.getString("piDesc"), imgPath+"pi.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\varpi", resBundle.getString("altPiDesc"), imgPath+"varpi.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\rho", resBundle.getString("rhoDesc"), imgPath+"rho.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\varrho", resBundle.getString("altRhoDesc"), imgPath+"varrho.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\sigma", resBundle.getString("sigDesc"), imgPath+"sigma.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\varsigma", resBundle.getString("altSigDesc"), imgPath+"varsigma.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\tau", resBundle.getString("tauDesc"), imgPath+"tau.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\upsilon", resBundle.getString("upsilonDesc"), imgPath+"upsilon.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\phi", resBundle.getString("phiDesc"), imgPath+"phi.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\varphi", resBundle.getString("altPhiDesc"), imgPath+"varphi.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\chi", resBundle.getString("chiDesc"), imgPath+"chi.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\psi", resBundle.getString("psiDesc"), imgPath+"psi.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\omega", resBundle.getString("omegaDesc"), imgPath+"omega.png"));
        
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\Gamma", resBundle.getString("caseGammaDesc"), imgPath+"caseGamma.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\Delta", resBundle.getString("caseDeltaDesc"), imgPath+"caseDelta.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\Theta", resBundle.getString("caseThetaDesc"), imgPath+"caseTheta.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\Lambda", resBundle.getString("caseLambdaDesc"), imgPath+"caseLambda.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\Xi", resBundle.getString("caseXiDesc"), imgPath+"caseXi.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\Pi", resBundle.getString("casePiDesc"), imgPath+"casePi.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\Sigma", resBundle.getString("caseSigmaDesc"), imgPath+"caseSigma.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\Upsilon", resBundle.getString("caseUpsilonDesc"), imgPath+"caseUpsilon.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\Phi", resBundle.getString("casePhiDesc"), imgPath+"casePhi.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\Psi", resBundle.getString("casePsiDesc"), imgPath+"casePsi.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\Omega", resBundle.getString("caseOmegaDesc"), imgPath+"caseOmega.png"));
        for (int i=0; i < 4; ++i)
            topPanel.add(createButtonPlaceHolder());
        
        //	Configure the spring layout for the topPanel.
        SpringUtilities.makeCompactGrid(topPanel, 3, 15, 0, 0, 2, 2);

        panel.add(Box.createGlue());

        Box botPanel = Box.createHorizontalBox();
        panel.add(botPanel);
        botPanel.add(Box.createGlue());
        JButton btn = new MathSymbolButton(_latexEditorPane, "\\mathcal{}", resBundle.getString("calligraphicLabel"),
                resBundle.getString("calligraphicToolTipText"), null, 9);
        btn.setFont(new java.awt.Font("SansSerif", 0, 10));
        btn.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(2, 2, 2, 2),
                BorderFactory.createRaisedBevelBorder()));
        botPanel.add(btn);
        botPanel.add(Box.createGlue());

        Box output = Box.createHorizontalBox();
        output.setOpaque(true);
        output.add(panel);
        output.add(Box.createGlue());

        return output;
    }
    
    /**
     * Method that creates a palette of options for binary equation elements.
     */
    private Container createBinaryPalette() {
        ResourceBundle resBundle = this.getResourceBundle();
        String imgPath = resBundle.getString("btnImagePath");
        
        Box panel = Box.createVerticalBox();
        JPanel topPanel = new JPanel(new SpringLayout());
        panel.add(topPanel);
        
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\pm", "plus or minus", imgPath+"pm.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\mp", "minus or plus", imgPath+"mp.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\times", "times multiplication", imgPath+"times.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\div", "division", imgPath+"div.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\ast", "asterix", imgPath+"ast.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\star", "star", imgPath+"star.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\circ", "circle", imgPath+"circ.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\bullet", "bullet or solid circle", imgPath+"bullet.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\cdot", "centered dot, multiplication", imgPath+"cdot.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\cap", "cap, intersection", imgPath+"cap.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\cup", "cup, union", imgPath+"cup.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\uplus", "multiset union", imgPath+"uplus.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\sqcap", "square cap, intersection", imgPath+"sqcap.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\sqcup", "square cup, union", imgPath+"sqcup.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\vee", "V operator, logical OR", imgPath+"vee.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\wedge", "wedge, logical AND", imgPath+"wedge.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\dagger", "dagger", imgPath+"dagger.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\ddagger", "double dagger", imgPath+"ddagger.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\setminus", "set minus", imgPath+"setminus.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\wr", "wreath product", imgPath+"wr.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\diamond", "diamond", imgPath+"diamond.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\bigtriangleup", "big triangle up", imgPath+"bigtriangleup.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\bigtriangledown", "big triangle down", imgPath+"bigtriangledown.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\triangleleft", "left pointing triangle", imgPath+"triangleleft.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\triangleright", "right pointing triangle", imgPath+"triangleright.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\lhd", "normal subgroup of", imgPath+"lhd.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\rhd", "contains as normal subgroup", imgPath+"rhd.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\unlhd", "normal subgroup of or equals to", imgPath+"unlhd.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\unrhd", "contains as normal subgoup or equal to", imgPath+"unrhd.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\oplus", "circled plus", imgPath+"oplus.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\ominus", "circled minus", imgPath+"ominus.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\otimes", "circled times", imgPath+"otimes.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\oslash", "circled slash", imgPath+"oslash.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\odot", "circled dot operator", imgPath+"odot.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\bigcirc", "big circle", imgPath+"bigcirc.png"));
        topPanel.add(createButtonPlaceHolder());
        
        //	Configure the spring layout for the topPanel.
        SpringUtilities.makeCompactGrid(topPanel, 3, 12, 0, 0, 4, 4);
        
        panel.add(Box.createGlue());
        
        Box botPanel = Box.createHorizontalBox();
        panel.add(botPanel);
        botPanel.add(Box.createGlue());
        JButton btn = new MathSymbolButton(_latexEditorPane, "\\neg", resBundle.getString("negLabel"),
                resBundle.getString("negToolTipText"), null);
        btn.setFont(new java.awt.Font("SansSerif", 0, 10));
        btn.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createEmptyBorder(2,2,2,2),
            BorderFactory.createRaisedBevelBorder()));
        botPanel.add(btn);
        botPanel.add(Box.createGlue());
        
        Box output = Box.createHorizontalBox();
        output.setOpaque(true);
        output.add(panel);
        output.add(Box.createGlue());
        
        
        return output;
    }
    
    /**
     * Method that creates a palette of options for relation equation elements.
     */
    private Container createRelationPalette() {
        ResourceBundle resBundle = this.getResourceBundle();
        String imgPath = resBundle.getString("btnImagePath");
        
        Box panel = Box.createVerticalBox();
        JPanel topPanel = new JPanel(new SpringLayout());
        panel.add(topPanel);
        
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\leq", "less than or equal to", imgPath+"leq.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\geq", "greater than or equal to", imgPath+"geq.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\prec", "preceeds", imgPath+"prec.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\succ", "succeeds", imgPath+"succ.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\preceq", "precedes or equals", imgPath+"preceq.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\succeq", "succeeds or equals", imgPath+"succeq.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\ll", "much less than", imgPath+"ll.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\gg", "much greater than", imgPath+"gg.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\subset", "subset of", imgPath+"subset.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\supset", "superset of", imgPath+"supset.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\subseteq", "subset of or equals", imgPath+"subseteq.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\supseteq", "superset of or equals", imgPath+"supseteq.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\sqsubset", "squared subset of", imgPath+"sqsubset.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\sqsupset", "squared superset of", imgPath+"sqsupset.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\sqsubseteq", "squared subset of or equal", imgPath+"sqsubseteq.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\sqsupseteq", "squared superset of or equal", imgPath+"sqsupseteq.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\in", "element of", imgPath+"in.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\ni", "contains as a member", imgPath+"ni.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\vdash", "short vertical bar touching a single short horizontal bar", imgPath+"vdash.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\dashv", "single short horizontal bar touching a short vertical bar", imgPath+"dashv.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\equiv", "equivalent", imgPath+"equiv.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\sim", "similar", imgPath+"sim.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\simeq", "asymptotically equal", imgPath+"simeq.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\asymp", "asymptotic", imgPath+"asymp.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\approx", "approximately equal", imgPath+"approx.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\cong", "congruent", imgPath+"cong.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\neq", "not equal", imgPath+"neq.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\doteq", "equal with a dot above it", imgPath+"doteq.png"));
        
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\models", "models", imgPath+"models.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\perp", "perpendicular", imgPath+"perp.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\mid", "middle", imgPath+"mid.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\parallel", "parallel", imgPath+"parallel.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\bowtie", "normal subgroup of", imgPath+"bowtie.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\Join", "normal subgroup of", imgPath+"Join.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\smile", "smile", imgPath+"smile.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\frown", "frown", imgPath+"frown.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\propto", "proportional to", imgPath+"propto.png"));
        for (int i=0; i < 5; ++i)
            topPanel.add(createButtonPlaceHolder());
        
        //	Configure the spring layout for the topPanel.
        SpringUtilities.makeCompactGrid(topPanel, 3, 14, 0, 0, 4, 4);
        
        panel.add(Box.createGlue());
        
        Box output = Box.createHorizontalBox();
        output.setOpaque(true);
        output.add(panel);
        output.add(Box.createGlue());
        
        return output;
    }
    
    /**
     * Method that creates a palette of options for relation equation elements.
     */
    private Container createArrowsPalette() {
        ResourceBundle resBundle = this.getResourceBundle();
        String imgPath = resBundle.getString("btnImagePath");
        
        Box panel = Box.createVerticalBox();
        JPanel topPanel = new JPanel(new SpringLayout());
        panel.add(topPanel);
        
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\leftarrow", "left arrow", imgPath+"leftarrow.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\rightarrow", "right arrow", imgPath+"rightarrow.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\leftrightarrow", "double left-right arrow", imgPath+"leftrightarrow.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\mapsto", "maps to", imgPath+"mapsto.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\longleftarrow", "long left arrow", imgPath+"longleftarrow.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\longrightarrow", "long right arrow", imgPath+"longrightarrow.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\longleftrightarrow", "long double left-right arrow", imgPath+"longleftrightarrow.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\longmapsto", "long maps to", imgPath+"longmapsto.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\uparrow", "up arrow", imgPath+"uparrow.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\downarrow", "down arrow", imgPath+"downarrow.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\updownarrow", "double up-down arrow", imgPath+"updownarrow.png"));
        
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\Leftarrow", "left double arrow", imgPath+"leftdarrow.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\Rightarrow", "right double arrow", imgPath+"rightdarrow.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\Leftrightarrow", "double left-right double arrow", imgPath+"leftrightdarrow.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\leadsto", "leads to", imgPath+"leadsto.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\Longleftarrow", "long left double arrow", imgPath+"longleftdarrow.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\Longrightarrow", "long right double arrow", imgPath+"longrightdarrow.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\Longleftrightarrow", "long right double arrow", imgPath+"longleftrightdarrow.png"));
        topPanel.add(createButtonPlaceHolder());
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\Uparrow", "up double arrow", imgPath+"updarrow.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\Downarrow", "down double arrow", imgPath+"downdarrow.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\Updownarrow", "up-down double arrow", imgPath+"updowndarrow.png"));

        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\hookleftarrow", "left arrow with a hook", imgPath+"hookleftarrow.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\hookrightarrow", "right arrow with a hook", imgPath+"hookrightarrow.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\leftharpoonup", "left harpoon with barb up", imgPath+"leftharpoonup.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\leftharpoondown", "left harpoon with barb down", imgPath+"leftharpoondown.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\rightleftharpoons", "left-right harpoons", imgPath+"rightleftharpoons.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\rightharpoonup", "right harpoon with barb up", imgPath+"rightharpoonup.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\rightharpoondown", "right harpoon with barb down", imgPath+"rightharpoondown.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\nearrow", "northeast arrow", imgPath+"nearrow.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\searrow", "southeast arrow", imgPath+"searrow.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\swarrow", "southwest arrow", imgPath+"swarrow.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\nwarrow", "northwest arrow", imgPath+"nwarrow.png"));
        
        //	Configure the spring layout for the topPanel.
        SpringUtilities.makeCompactGrid(topPanel, 3, 11, 0, 0, 4, 4);
        
        panel.add(Box.createGlue());
        
        Box output = Box.createHorizontalBox();
        output.setOpaque(true);
        output.add(panel);
        output.add(Box.createGlue());
        
        return output;
    }
    
    /**
     * Method that creates a palette of options for misc. equation elements.
     */
    private Container createMiscPalette() {
        ResourceBundle resBundle = this.getResourceBundle();
        String imgPath = resBundle.getString("btnImagePath");
        
        Box panel = Box.createVerticalBox();
        JPanel topPanel = new JPanel(new SpringLayout());
        panel.add(topPanel);
        
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\aleph", "Hebrew letter aleph", imgPath+"aleph.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\beth", "Hebrew letter beth", imgPath+"beth.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\daleth", "Hebrew letter daleth", imgPath+"daleth.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\gimel", "Hebrew letter gimel", imgPath+"gimel.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\hbar", "h-bar, Planck's constant over 2 pi", imgPath+"hbar.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\imath", "lower case \"i\", imaginary number", imgPath+"imath.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\ell", "script small \"l\"", imgPath+"ell.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\wp", "script capital \"P\"", imgPath+"wp.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\Re", "real number indicator", imgPath+"Re.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\Im", "imaginary number indicator", imgPath+"Im.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\prime", "prime", imgPath+"prime.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\emptyset", "empty set", imgPath+"emptyset.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\nabla", "del, differential operator", imgPath+"nabla.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\surd", "square root symbol", imgPath+"surd.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\top", "top", imgPath+"top.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\bot", "bottom", imgPath+"bot.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\|", "vertical bars", imgPath+"verticalbars.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\angle", "angle", imgPath+"angle.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\measuredangle", "measured angle", imgPath+"measuredangle.png"));
        
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\forall", "for all", imgPath+"forall.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\exists", "there exists", imgPath+"exists.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\nexists", "there does not exist", imgPath+"nexists.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\neg", "negate", imgPath+"neg.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\flat", "music flat", imgPath+"flat.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\natural", "music natural", imgPath+"natural.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\sharp", "music sharp", imgPath+"sharp.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\backslash", "backslash", imgPath+"backslash.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\infty", "infinity", imgPath+"infty.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\Box", "box", imgPath+"Box.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\Diamond", "box", imgPath+"bigDiamond.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\triangle", "triangle", imgPath+"triangle.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\clubsuit", "club card suit", imgPath+"clubsuit.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\diamondsuit", "diamond card suit", imgPath+"diamondsuit.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\heartsuit", "heart card suit", imgPath+"heartsuit.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\spadesuit", "spade card suit", imgPath+"spadesuit.png"));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\therefore", "therefore", imgPath+"therefore.png"));
        
        //	Configure the spring layout for the topPanel.
        SpringUtilities.makeCompactGrid(topPanel, 3, 12, 0, 0, 4, 4);
        
        panel.add(Box.createGlue());
        
        Box output = Box.createHorizontalBox();
        output.setOpaque(true);
        output.add(panel);
        output.add(Box.createGlue());
        
        return output;
    }
    
    /**
     * Method that creates a palette of options for misc. equation elements.
     */
    private Container createFunctionsPalette() {
        Box panel = Box.createVerticalBox();
        JPanel topPanel = new JPanel(new GridLayout(4,8));
        panel.add(topPanel);
        
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\arccos", "arccos", "arc cosine", null));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\cos", "cos", "cosine", null));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\cosh", "cosh", "hyperbolic cosine", null));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\csc", "csc", "cosecant", null));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\exp", "exp", "exponential", null));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\ker", "ker", "the kernal of the linear operator", null));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\min", "min", "the minimum of", null));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\max", "max", "the maximum of", null));
    
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\arcsin", "arcsin", "arc sine", null));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\sin", "sin", "sine", null));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\sinh", "sinh", "hyperbolic sine", null));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\deg", "deg", "deg", null));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\gcd", "gcd", "the greatest common divisor", null));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\log", "log", "base 10 logarithm", null));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\ln", "ln", "natural logarithm", null));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\Pr", "Pr", "probability of an event", null));
        
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\arctan", "arctan", "arc tangent", null));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\tan", "tan", "tangent", null));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\tanh", "tanh", "hyperbolic tangent", null));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\det", "det", "determinant", null));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\hom", "hom", "the set of homomorphisms", null));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\lim_{\\to }", "lim", "the set of homomorphisms", null, 6));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\sup", "sup", "the least upper bound of", null));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\sec", "sec", "secant", null));
        
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\arg", "arg", "the angle in the polar form of a complex number", null));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\cot", "cot", "cotangent", null));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\coth", "coth", "hyperbolic cotangent", null));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\dim", "dim", "dimension of", null));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\inf", "inf", "the greatest lower bound of", null));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\liminf", "liminf", "limit of the sequence of infima of", null));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\bmod", "mod", "modulo", null));
        topPanel.add(new MathSymbolButton(_latexEditorPane, "\\pmod{}", "mod{}", "modulo", null, 6));
        
        //  Adjust the border of all the buttons.
        int num = topPanel.getComponentCount();
        for (int i=0; i < num; ++i) {
            JButton btn = (JButton)topPanel.getComponent(i);
            btn.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(2,2,2,2),
                BorderFactory.createRaisedBevelBorder()));
        }
        
        panel.add(Box.createGlue());
        
        Box output = Box.createHorizontalBox();
        output.setOpaque(true);
        output.add(panel);
        output.add(Box.createGlue());
        
        return output;
    }
    
    /**
     * Method that creates a palette of options for misc. equation elements.
     */
    private Container createAccentsPalette() {
        ResourceBundle resBundle = this.getResourceBundle();
        String imgPath = resBundle.getString("btnImagePath");
        
        Box panel1 = Box.createVerticalBox();
        Box panel = Box.createHorizontalBox();
        panel1.add(panel);
        panel1.add(Box.createGlue());
        JPanel leftPanel = new JPanel(new SpringLayout());
        panel.add(leftPanel);
        
        leftPanel.add(new MathSymbolButton(_latexEditorPane, "\\oe", "combined oe", imgPath+"oe.png"));
        leftPanel.add(new MathSymbolButton(_latexEditorPane, "\\OE", "combined OE", imgPath+"capOE.png"));
        leftPanel.add(new MathSymbolButton(_latexEditorPane, "\\mbox{\\aa}", "lower case \"a\" with a circle over it", imgPath+"aa.png"));
        leftPanel.add(new MathSymbolButton(_latexEditorPane, "\\mbox{\\AA}", "Angestrom unit", imgPath+"capAA.png"));

        leftPanel.add(new MathSymbolButton(_latexEditorPane, "\\ae", "combined ae", imgPath+"ae.png"));
        leftPanel.add(new MathSymbolButton(_latexEditorPane, "\\AE", "combined AE", imgPath+"capAE.png"));
        leftPanel.add(new MathSymbolButton(_latexEditorPane, "\\mbox{\\l}", "lower case \"l\" with a line through it", imgPath+"slashl.png"));
        leftPanel.add(new MathSymbolButton(_latexEditorPane, "\\L", "capital \"L\" with a line through it", imgPath+"capslashL.png"));
        
        leftPanel.add(new MathSymbolButton(_latexEditorPane, "\\O", "slash O", imgPath+"capslashO.png"));
        leftPanel.add(new MathSymbolButton(_latexEditorPane, "\\o", "slash o", imgPath+"slasho.png"));
        leftPanel.add(new MathSymbolButton(_latexEditorPane, "\\ss", "sharp \"S\"", imgPath+"ss.png"));
        leftPanel.add(new MathSymbolButton(_latexEditorPane, "\\Bbbk", "an outlined \"k\"", imgPath+"Bbbk.png"));
        
        //	Configure the spring layout for the topPanel.
        SpringUtilities.makeCompactGrid(leftPanel, 3, 4, 0, 0, 5, 5);
        
        panel.add(Box.createHorizontalStrut(20));
        
        JPanel rightPanel = new JPanel(new SpringLayout());
        panel.add(rightPanel);
        
        rightPanel.add(new MathSymbolButton(_latexEditorPane, "\\hat{}", "circumflex accent (hat)", imgPath+"hat.png", 5));
        rightPanel.add(new MathSymbolButton(_latexEditorPane, "\\check{}", "caron (inverted circumflex)", imgPath+"check.png", 7));
        rightPanel.add(new MathSymbolButton(_latexEditorPane, "\\breve{}", "breve accent", imgPath+"breve.png", 7));
        rightPanel.add(new MathSymbolButton(_latexEditorPane, "\\acute{}", "acute accent", imgPath+"acute.png", 7));
        rightPanel.add(new MathSymbolButton(_latexEditorPane, "\\grave{}", "grave accent", imgPath+"grave.png", 7));
        rightPanel.add(new MathSymbolButton(_latexEditorPane, "\\tilde{}", "tilde", imgPath+"tilde.png", 7));
        rightPanel.add(new MathSymbolButton(_latexEditorPane, "\\bar{}", "macron (horizontal bar)", imgPath+"bar.png", 5));
        rightPanel.add(new MathSymbolButton(_latexEditorPane, "\\vec{}", "vector indicator (right arrow)", imgPath+"vec.png", 5));
        rightPanel.add(new MathSymbolButton(_latexEditorPane, "\\dot{}", "a single dot above", imgPath+"dot.png", 5));
        rightPanel.add(new MathSymbolButton(_latexEditorPane, "\\ddot{}", "double dots above", imgPath+"ddot.png", 6));
        
        //	Configure the spring layout for the topPanel.
        SpringUtilities.makeCompactGrid(rightPanel, 2, 5, 0, 0, 5, 5);
        
        Box output = Box.createHorizontalBox();
        output.setOpaque(true);
        output.add(panel1);
        output.add(Box.createGlue());
        
        return output;
    }
    
    /**
     * Method that returns an empty component that can be a place holder for a
     * missing button in a button palette panel.
     */
    private static Component createButtonPlaceHolder() {
        return Box.createRigidArea(new Dimension(20, 23));
    }

    /**
     * Initializes the menus associated with this window.
     */
    private JMenuBar createMenuBar() throws NoSuchMethodException {
        ResourceBundle resBundle = this.getResourceBundle();

        JMenuBar menuBar = new JMenuBar();

        // Set up the file menu.
        List<String[]> menuStrings = new ArrayList<String[]>();
        String[] item = new String[3];
        item[0] = resBundle.getString("newItemText");
        item[1] = resBundle.getString("newItemKey");
        item[2] = "handleNew";
        menuStrings.add(item);

        item = new String[3];
        item[0] = resBundle.getString("openItemText");
        item[1] = resBundle.getString("openItemKey");
        item[2] = "handleOpen";
        menuStrings.add(item);

        menuStrings.add(new String[3]);	//	Blank line

        item = new String[3];
        item[0] = resBundle.getString("closeItemText");
        item[1] = resBundle.getString("closeItemKey");
        item[2] = "handleClose";
        menuStrings.add(item);

        menuStrings.add(new String[3]);	//	Blank line

        item = new String[3];
        item[0] = resBundle.getString("saveItemText");
        item[1] = null;
        item[2] = null;
        menuStrings.add(item);

        item = new String[3];
        item[0] = resBundle.getString("saveAsItemText");
        item[1] = resBundle.getString("saveItemKey");
        item[2] = "handleSaveAs";
        menuStrings.add(item);

        menuStrings.add(new String[3]);	//	Blank line

        item = new String[3];
        item[0] = resBundle.getString("pageSetupItemText");
        item[1] = null;
        item[2] = null;
        menuStrings.add(item);

        item = new String[3];
        item[0] = resBundle.getString("printItemText");
        item[1] = resBundle.getString("printItemKey");
        item[2] = null;
        menuStrings.add(item);

        /*JMenu menu = AppUtilities.buildMenu(this, resBundle.getString("fileMenuText"), menuStrings);
        menuBar.add(menu);

        //  Add a Quit menu item.
        MDIApplication guiApp = app.getGUIApplication();
        QuitJMenuItem quit = guiApp.getQuitJMenuItem();
        quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                app.getGUIApplication().handleQuit(e);
            }
        });
        if (!QuitJMenuItem.isAutomaticallyPresent()) {
            menu.addSeparator();
            menu.add(quit);
        }*/

        // Set up the edit menu.
        menuStrings = new ArrayList<String[]>();
        item = new String[3];
        item[0] = resBundle.getString("undoItemText");
        item[1] = resBundle.getString("undoItemKey");
        item[2] = null;
        menuStrings.add(item);

        item = new String[3];
        item[0] = resBundle.getString("redoItemText");
        item[1] = null;
        item[2] = null;
        menuStrings.add(item);

        menuStrings.add(new String[3]);	//	Blank line.

        item = new String[3];
        item[0] = resBundle.getString("cutItemText");
        item[1] = resBundle.getString("cutItemKey");
        item[2] = null;
        menuStrings.add(item);

        item = new String[3];
        item[0] = resBundle.getString("copyItemText");
        item[1] = resBundle.getString("copyItemKey");
        item[2] = null;
        menuStrings.add(item);

        item = new String[3];
        item[0] = resBundle.getString("pasteItemText");
        item[1] = resBundle.getString("pasteItemKey");
        item[2] = null;
        menuStrings.add(item);

        _editMenu = AppUtilities.buildMenu(this, resBundle.getString("editMenuText"), menuStrings);
        menuBar.add(_editMenu);
        _undoMenuItem = _editMenu.getItem(0);
        _redoMenuItem = _editMenu.getItem(1);
        _cutMenuItem = _editMenu.getItem(3);
        _copyMenuItem = _editMenu.getItem(4);
        _pasteMenuItem = _editMenu.getItem(5);

        //  Add a Preferences menu item.
        PreferencesJMenuItem preferences = this.getGUIApplication().getPreferencesJMenuItem();
        preferences.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prefs.showPreferenceDialog();
            }
        });
        if (!PreferencesJMenuItem.isAutomaticallyPresent()) {
            _editMenu.addSeparator();
            _editMenu.add(preferences);
        }

        // Set up the Run menu.
        /*menuStrings = new ArrayList<String[]>();
        item = new String[3];
        item[0] = resBundle.getString("runItemText");
        item[1] = null;
        item[2] = "handleTypeset";
        menuStrings.add(item);

        menu = AppUtilities.buildMenu(this, resBundle.getString("runMenuText"), menuStrings);
        menuBar.add(menu);

        //  Add an accelerator to the Run LaTeX command.
        JMenuItem menuItem = menu.getItem(0);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));

        //  Create a Window's menu.
        menu = guiApp.newWindowsMenu(resBundle.getString("windowsMenuText"));
        menuBar.add(menu);

        //	Create an about menu item.
        AboutJMenuItem about = guiApp.createAboutMenuItem();

        //	Create a "Help" menu for non-MacOS platforms.
        if (!AboutJMenuItem.isAutomaticallyPresent()) {
            //	Create Help menu.
            menu = new JMenu(resBundle.getString("helpMenuText"));
            menuBar.add(menu);

            //	Add the "About" item to the Help menu.
            menu.add(about);
        }*/

        return menuBar;
    }

    /**
     * Handle the user choosing the "New..." from the File menu. Creates a new,
     * blank, document window.
     *
     * @param event The event that caused this method to be called.
     * @return A new MainWindow instance.
     */
    public MainWindow handleNew(ActionEvent event) {
        return (MainWindow) (this.getGUIApplication().handleNew(event));
    }

    /**
     * Handle the user choosing "Close" from the File menu. This implementation
     * dispatches a "Window Closing" event to this window.
     *
     * @param event The event that caused this method to be called.
     */
    public void handleClose(ActionEvent event) {
        this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }

    /**
     * Handle the user clicking on the "Quit" button.
     *
     * @param event The action event that caused this method to be called.
     */
    public void handleQuit(ActionEvent event) {
        this.getGUIApplication().handleQuit(event);
    }

    /**
     * Method called by the quit listener when the application has been asked to
     * quit. Returns true if the quit should be canceled, or false if continuing
     * with quitting the application is fine.
     */
    private boolean handleQuit() {
        return false;
    }
	
	public void handleDone(ActionEvent event) {

        String input = "$"+_latexEditorPane.getText()+"$";
        //mcqseter.Question.insert(input);
        //Question.insert(input);
        if(this.textField != null){

			String val = textField.getText();
			String fir = val.substring(0, curPos);
			String sec = val.substring(curPos);
			
			String newS = fir + input +sec;
			textField.setText(newS);
			
			//System.out.println("text field");
			
        }else if(this.textArea != null){
        	String val = textArea.getText();
			String fir = val.substring(0, curPos);
			String sec = val.substring(curPos);
			
			String newS = fir + input +sec;
			textArea.setText(newS);
			
			//System.out.println("text area");
        }
        else if(this.textPaneEssay != null){

			String val = textPaneEssay.getText();
			String fir = val.substring(0, curPos);
			String sec = val.substring(curPos);
			
			String newS = fir + input +sec;
			textPaneEssay.setText(newS);
			
			//System.out.println("text Pane Essay");
        }
        else if(this.textAreaStruct != null){

			String val = textAreaStruct.getText();
			String fir = val.substring(0, curPos);
			String sec = val.substring(curPos);
			
			String newS = fir + input +sec;
			textAreaStruct.setText(newS);
			
        }
			
			//FileWriter fw= new FileWriter("math.txt");
			//fw.write(input);
			//fw.close();
		
		_latexEditorPane.setText("");
		this.setVisible(false);
		
		
			
	}

    /**
     * Handle the user clicking on the "Typeset" button.
     *
     * @param event The action event that caused this method to be called.
     */
    public void handleTypeset(ActionEvent event) {

        final String input = _latexEditorPane.getText();

        if (input == null || "".equals(input.trim())) {
            return;
        }

        // Use a SwingWorker to run the native latex and dvipng processes.
        SwingWorker worker = new SwingWorker<PNGImage, Object>() {
            PNGImage pngImage;
            Exception exception;

            @Override
            public PNGImage doInBackground() {
                try {
                    pngImage = _nativeRuntime.exec(input);
                } catch (IOException ex) {
                    // defer errorDialog popup to the event dispatching thread
                    exception = ex;
                } catch (InterruptedException ex) {
                    // defer errorDialog popup to the event dispatching thread
                    exception = ex;
                }
                return pngImage;
            }

            @Override
            protected void done() {
                ResourceBundle resBundle = RESBUNDLE;
                if (exception != null) {
                    AppUtilities.showException(null, resBundle.getString("latexRunErrorTitle"), "", exception);

                } else if (pngImage != null) {
                    String currentEquation = _latexEditorPane.getText();
                    try {
                        pngImage.setLatexPreamble(prefs.getLatexPreamble());
                        pngImage.setLatexPostamble(prefs.getLatexPostamble());
                        pngImage.setDvipngOptions(prefs.getDviPngOptions());
                        pngImage.setLatexEquation(currentEquation);
                        pngImage.setPointSize(prefs.getPointSize());

                        _pngPanel.setPNGImage(pngImage);

                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(MainWindow.this, ex.getMessage(),
                                resBundle.getString("setPNGFailedErrorTitle"), JOptionPane.ERROR_MESSAGE);
                    }
                }

            }
        };
        worker.execute();
	}
	
	

    /**
     * Handles the user choosing "Save As..." from the File menu.
     *
     * @param event The event that caused this method to be called.
     */
    public void handleSaveAs(ActionEvent event) {
        PNGImage pngImage = _pngPanel.getPNGImage();
        if (pngImage == null) {
            return;
        }

        ResourceBundle resBundle = this.getResourceBundle();

        //  Get the file name filter to use.
        FilenameFilter filter = this.getGUIApplication().getFilenameFilter();

        //	Build the directory and filename to prompt the user with.
        String dir = this.getPreferences().getLastPath();
        String fileName = resBundle.getString("defaultFileName");

        //	Ask the user to select a file for saving.
        String msg = MessageFormat.format(resBundle.getString("fileSaveDialogMsg"), "PNG");
        File chosenFile = AppUtilities.selectFile4Save(this, msg,
                dir, fileName, filter, ".png",
                resBundle.getString("fileExistsMsgFmt"), resBundle.getString("warningTitle"));
        if (chosenFile == null) {
            return;
        }

        if (chosenFile.exists() && !chosenFile.canWrite()) {
            JOptionPane.showMessageDialog(this,
                    resBundle.getString("canNotWrite2File"),
                    resBundle.getString("ioErrorTitle"), JOptionPane.ERROR_MESSAGE);
            return;
        }

        //  Write out the PNG image.
        try {
            pngImage.writeTo(chosenFile);

        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, e.toString(), resBundle.getString("ioErrorTitle"),
                    JOptionPane.ERROR_MESSAGE);
        }

    }

    /**
     * Handles the user choosing "Open..." from the File menu.
     *
     * @param event The event that caused this method to be called.
     */
    public void handleOpen(ActionEvent event) {
        ResourceBundle resBundle = this.getResourceBundle();

        //  Get the file name filter to use.
        FilenameFilter filter = this.getGUIApplication().getFilenameFilter();

        //	Build the directory and filename to prompt the user with.
        String dir = this.getPreferences().getLastPath();
        File file = AppUtilities.selectFile(this, FileDialog.LOAD, resBundle.getString("fileDialogLoad"),
                dir, null, filter);
        if (file == null) {
            return;
        }

        //  Read in the PNG file's meta-data.
        boolean res = _pngMetaDataHandler.updateMetaData(file);

    }

	@Override
	public ResourceBundle getResourceBundle() {
		return RESBUNDLE;
	}

	@Override
	public MDIApplication getGUIApplication() {
		return guiApp;
	}

	@Override
	public Preferences getPreferences() {
		return  prefs;
	}
    
    
    

}
