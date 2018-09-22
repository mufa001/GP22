package java.ui.jahuwaldt.jatex;


import java.ui.jahuwaldt.io.ExtFilenameFilter;
import java.ui.jahuwaldt.io.StreamGobbler;
import java.ui.jahuwaldt.jatex.latexeditor.LatexEditorPane;
import java.ui.jahuwaldt.swing.AppUtilities;
import java.ui.jahuwaldt.swing.EscapeJDialog;
import java.ui.jahuwaldt.swing.MainApp;
import java.ui.jahuwaldt.swing.SpringUtilities;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.FileDialog;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;




/**
 * This class serves as a collection of preferences for this program.
 * 
 * <p> Modified by: Joseph A. Huwaldt </p>
 * 
 * @author Joseph A. Huwaldt Date: May 10, 2013
 * @version May 15, 2013
 */
public class AppPreferences implements java.ui.jahuwaldt.swing.Preferences {

	//  The preferences for this application are stored as a properties list.
	private final Preferences prefs = Preferences.userNodeForPackage(this.getClass());
	
	//  Use a change-listener system to keep the preferences dialog updated
    private final PropertyChangeSupport propertyChange = new PropertyChangeSupport(this);
	
	//  A reference to the window containing the application preferences.
	private PrefsDialog prefsDialog = null;
	
	//	Reference to the main application object for this program.
	private final MainApp app;
	
    //  Preference keys
    /**
     * The preference key for the latex application options.
     */
    public static final String LATEX_APP = "latexApp";
    /**
     * The preference key for the dvipng application options.
     */
    public static final String DVIPNG_APP = "dvipngApp";
    
    private static final String LATEX_PREAMBLE = "latexPreamble";
    private static final String LATEX_POSTAMBLE = "latexPostamble";
    private static final String DVIPNG_OPTIONS = "dvipngOptions";
    private static final String POINTSIZE = "pointSize";
    private static final String LASTPATH = "lastPath";
    
    private final static String newline = System.getProperty("line.separator");
    private static final String LATEX_APP_DEFAULT = "latex";
    private static final String DVIPNG_APP_DEFAULT = "dvipng";
    private static final String LATEX_PREAMBLE_DEFAULT =
            "\\documentclass{article}" + newline +
            "\\usepackage{amssymb,amsmath}" + newline +
            "\\usepackage{mathptmx}% Times Roman font" + newline +
            "\\pagestyle{empty}" + newline +
            "\\begin{document}" + newline +
            "\\begin{displaymath}";
    private static final String LATEX_POSTAMBLE_DEFAULT =
            "\\end{displaymath}" + newline +
            "\\newpage" + newline +
            "\\end{document}" + newline;
    private static final String DVIPNG_OPTIONS_DEFAULT = "-T tight -z 9 -bg Transparent";
    private static final int POINTSIZE_DEFAULT = 10;
    
    //  The best guess at the path to the required applications.
    private static String latexApp_default = LATEX_APP_DEFAULT;
    private static String dvipngApp_default = DVIPNG_APP_DEFAULT;
    static {
        if (!AppUtilities.isWindows()) {
            //  Assume we have a UNIX-like system.
            latexApp_default = findExecutablePath("latex", LATEX_APP_DEFAULT);
            dvipngApp_default = findExecutablePath("dvipng", DVIPNG_APP_DEFAULT);
        }
    }
    
	//	Text fields used to input paths to required external programs.
	private JTextField _latexApp;
	private JTextField _dviPngApp;
    private JTextField _dviOptions;
	private JTextField _latexPreamble;
	private JTextField _latexPostamble;
	
    //  The LaTeX pre and postamble text fields.
    private LatexEditorPane _inputLatexPreamble;
    private LatexEditorPane _inputLatexPostamble;
    
    //  The dvipng options.
    private JTextField _inputDviPngOptions;
    
	
	/**
	*  Construct the preferences object for this application.  This constructor will
	*  locate the preference file and load in any available preferences for the application.
	*
	*  @param app  Reference to the main application.
	**/
	public AppPreferences(MainApp app) {
		this.app = app;
	}
	
	
	/**
	*  Returns the file options to the parent of the last referenced file.
	*  Returns null if no last options could be found.
	**/
    @Override
	public String getLastPath() {
		return prefs.get(LASTPATH, null);
	}
	
	/**
	*  Set the last file options referenced by the user.  This is the options
	*  to the last parent of the last referenced file.
	**/
    @Override
	public void setLastPath(String path) {
		String oldPath = prefs.get(LASTPATH, null);
		if ( (path != null && !path.equals(oldPath)) || (path == null && oldPath != null) ) {
			prefs.put(LASTPATH, path);
		}
	}
    
    /**
     * Return the options to the latex application.
     */
    public String getLatexApplication() {
        return prefs.get(LATEX_APP,latexApp_default);
    }
    
     /**
     * Set the options to the latex application.
     */
    public synchronized void setLatexApplication(String path) {
		String oldPath = prefs.get(LATEX_APP, null);
		if ( (path != null && !path.equals(oldPath)) || (path == null && oldPath != null) ) {
			prefs.put(LATEX_APP, path);
		}
    }
    
    /**
     * Return the dvipng application options.
     */
    public synchronized String getDviPngApplication() {
        return prefs.get(DVIPNG_APP, dvipngApp_default);
    }
    
     /**
     * Set the options to the dvipng application.
     */
    public synchronized void setDviPngApplication(String path) {
		String oldPath = prefs.get(DVIPNG_APP, null);
		if ( (path != null && !path.equals(oldPath)) || (path == null && oldPath != null) ) {
			prefs.put(DVIPNG_APP, path);
		}
    }
    
    
    /**
     * Return the latex preamble.
     */
    public synchronized String getLatexPreamble() {
        return prefs.get(LATEX_PREAMBLE, LATEX_PREAMBLE_DEFAULT);
    }
    
    /**
     * Set the latex preamble.
     */
    public synchronized void setLatexPreamble(String latexPreamble) {
        String oldPreamble = prefs.get(LATEX_PREAMBLE, null);
        if ( (latexPreamble != null && !latexPreamble.equals(oldPreamble)) || (latexPreamble == null && oldPreamble != null) ) {
			prefs.put(LATEX_PREAMBLE, latexPreamble);
            propertyChange.firePropertyChange("LatexPreamble", oldPreamble, latexPreamble);
        }
    }
    
    /**
     * Return the latex postamble.
     */
    public synchronized String getLatexPostamble() {
        return prefs.get(LATEX_POSTAMBLE, LATEX_POSTAMBLE_DEFAULT);
    }
    
    /**
     * Set the latex preamble.
     */
    public synchronized void setLatexPostamble(String latexPostamble) {
        String oldPostamble = prefs.get(LATEX_POSTAMBLE, null);
        if ( (latexPostamble != null && !latexPostamble.equals(oldPostamble)) || (latexPostamble == null && oldPostamble != null) ) {
			prefs.put(LATEX_POSTAMBLE, latexPostamble);
            propertyChange.firePropertyChange("LatexPostamble", oldPostamble, latexPostamble);
        }
    }
    
    /**
     * Return the dvipng options.
     */
    public synchronized String getDviPngOptions() {
        return prefs.get(DVIPNG_OPTIONS, DVIPNG_OPTIONS_DEFAULT);
    }
    
    /**
     * Return the dvipng options as a list with a single option in each element.
     */
    public synchronized List<String> getDviPngOptionsList() {
        String[] opts = getDviPngOptions().split("\\s+");
        return Arrays.asList(opts);
    }
    
    /**
     * Set the dvipng dvipngOptions.
     */
    public synchronized void setDviPngOptions(String dvipngOptions) {
        String oldOptions = prefs.get(DVIPNG_OPTIONS, null);
        if ( (dvipngOptions != null && !dvipngOptions.equals(oldOptions)) || (dvipngOptions == null && oldOptions != null) ) {
			prefs.put(DVIPNG_OPTIONS, dvipngOptions);
            propertyChange.firePropertyChange("DvipngOptions", oldOptions, dvipngOptions);
        }
    }
    
    /**
     * Return the base font point size.
     */
    public int getPointSize() {
        return prefs.getInt(POINTSIZE,POINTSIZE_DEFAULT);
    }
    
     /**
     * Set the base font point size.
     */
    public synchronized void setPointSize(int ptSize) {
		int oldSize = getPointSize();
		if ( ptSize != oldSize ) {
			prefs.putInt(POINTSIZE, ptSize);
            propertyChange.firePropertyChange("PointSize", oldSize, ptSize);
		}
    }
    


	/**
	* Return the preference with the specified key String.
	*
	*  @param key  The key String identifying the preference to be retrieved.
	**/
    @Override
	public String get(String key) {
		return prefs.get(key, null);
	}
	
	/**
	* Set the preference with the specified key String.
	*
	*  @param key  The key String identifying the preference to be set.
	*  @param value THe String value to store as the preference.
	**/
    @Override
	public void set(String key, String value) {
		prefs.put(key, value);
	}
	

    public synchronized void addPropertyChangeListener(PropertyChangeListener l) {
        propertyChange.addPropertyChangeListener(l);
    }

    public synchronized void addPropertyChangeListener(String propertyName, PropertyChangeListener l) {
        propertyChange.addPropertyChangeListener(propertyName, l);
    }

    public synchronized void removePropertyChangeListener(PropertyChangeListener l) {
        propertyChange.removePropertyChangeListener(l);
    }

    public synchronized void removePropertyChangeListener(String propertyName, PropertyChangeListener l) {
        propertyChange.removePropertyChangeListener(propertyName, l);
    }
    
	/**
	*  Method that displays a dialog that allows the user to change the application preferences.
	*  If the application is running in batch mode, this method does nothing.
	**/
    @Override
	public void showPreferenceDialog() {
		if (app.getGUIApplication() != null) {
			if (prefsDialog != null)
				prefsDialog.setVisible(true);
				
			else {
				try {
					
					prefsDialog = new PrefsDialog();
					prefsDialog.pack();
					AppUtilities.positionWindow( prefsDialog, prefsDialog.getWidth(), prefsDialog.getHeight() );
					prefsDialog.setVisible(true);
					
				} catch (NoSuchMethodException e) {
					e.printStackTrace();	//	Should only happen during development.
				}
			}
		}

	}


	/**
	 * This dialog used to allow the user to set the preferences for this program.
	 */
	@SuppressWarnings("serial")
	private class PrefsDialog extends EscapeJDialog {
		PrefsDialog() throws NoSuchMethodException {
			super(null, app.getResourceBundle().getString("prefsPanelTitle"), false);
			ResourceBundle resBundle = app.getResourceBundle();
			
			//  Layout the dialog window.
			Container cp = this.getContentPane();
			cp.setLayout(new BorderLayout());
			
			//	Add a close button to the bottom.
			JButton close = new JButton(resBundle.getString("closeItemText"));
			close.addActionListener(new ActionListener() {
                @Override
				public void actionPerformed(ActionEvent e) {
					PrefsDialog.this.setVisible(false);
				}
			});
			Box box = Box.createHorizontalBox();
			box.setBorder(BorderFactory.createEmptyBorder(0,10,10,10));
			box.add(Box.createHorizontalGlue());
			box.add(close);
			cp.add(box, BorderLayout.SOUTH);
			this.getRootPane().setDefaultButton(close);	//	Make the default button.

			//	Add executable paths.
			JPanel panel = new JPanel(new SpringLayout());
			panel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(10,10,10,10),
					BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black),
					resBundle.getString("execPathsLabel"))));
			cp.add(panel, BorderLayout.NORTH);
			
			//	Add the latex executable options.
			_latexApp = createFileInput(LATEX_APP, latexApp_default, resBundle.getString("latexExecLabel"), 20,
                    resBundle.getString("latexExecToolTipText"), panel);

			//	Add the dvipng executable options.
			_dviPngApp = createFileInput(DVIPNG_APP, dvipngApp_default, resBundle.getString("dvipngExecLabel"), 20,
                    resBundle.getString("dvipngExecToolTipText"), panel);

			//	Configure the spring layout for the panel.
			SpringUtilities.makeCompactGrid(panel, 2, 3, 0, 0, 5, 5);
			
			
            //  Add the LaTeX preamble and postamble.
            JPanel centerPanel = new JPanel(new BorderLayout());
            cp.add(centerPanel, BorderLayout.CENTER);
            
            panel = new JPanel(new BorderLayout());
            centerPanel.add(panel, BorderLayout.NORTH);
			panel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(10,10,0,10),
					BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black),
					resBundle.getString("latexSettingsLabel"))));
            final JTabbedPane tpane = new JTabbedPane(JTabbedPane.TOP);
			panel.add(tpane, BorderLayout.CENTER);
            
            //  Set Defaults button for the LaTeX settings.
			JButton defBtn = new JButton(resBundle.getString("defaults"));
            defBtn.setToolTipText(resBundle.getString("defLatexSettignsTTText"));
			defBtn.addActionListener(new ActionListener() {
                @Override
				public void actionPerformed(ActionEvent e) {
                    int selected = tpane.getSelectedIndex();
                    if (selected == 0) {
                        setLatexPreamble(LATEX_PREAMBLE_DEFAULT);
                        _inputLatexPreamble.setText(LATEX_PREAMBLE_DEFAULT);
                    } else {
                        setLatexPostamble(LATEX_POSTAMBLE_DEFAULT);
                        _inputLatexPostamble.setText(LATEX_POSTAMBLE_DEFAULT);
                    }
				}
			});
			box = Box.createHorizontalBox();
			box.setBorder(BorderFactory.createEmptyBorder(0,0,0,10));
			box.add(Box.createHorizontalGlue());
			box.add(defBtn);
            panel.add(box, BorderLayout.EAST);
            
            //  The preamble.
            _inputLatexPreamble = new LatexEditorPane(app.getResourceBundle());
            _inputLatexPreamble.setFont(new java.awt.Font("Monospaced", 0, 12));
            _inputLatexPreamble.setText(getLatexPreamble());
            _inputLatexPreamble.addFocusListener(new FocusListener() {
                @Override
                public void focusLost(FocusEvent evt) {
                    LatexEditorPane tf = (LatexEditorPane)evt.getSource();
                    String pref = tf.getText();
					AppPreferences.this.set(LATEX_PREAMBLE, pref);
                }
                @Override
                public void focusGained(FocusEvent evt) { }
            });
            JScrollPane sp = new JScrollPane(_inputLatexPreamble);
            sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
            tpane.add(resBundle.getString("preambleLabel"), sp);
            
            //  The postamble.
            _inputLatexPostamble = new LatexEditorPane(app.getResourceBundle());
            _inputLatexPostamble.setFont(new java.awt.Font("Monospaced", 0, 12));
            _inputLatexPostamble.setText(getLatexPostamble());
            _inputLatexPostamble.addFocusListener(new FocusListener() {
                @Override
                public void focusLost(FocusEvent evt) {
                    LatexEditorPane tf = (LatexEditorPane)evt.getSource();
                    String pref = tf.getText();
					AppPreferences.this.set(LATEX_POSTAMBLE, pref);
                }
                @Override
                public void focusGained(FocusEvent evt) { }
            });
            sp = new JScrollPane(_inputLatexPostamble);
            sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
            tpane.add(resBundle.getString("postambleLabel"),sp);
            
            //  Add the dvipng options.
            panel = new JPanel(new SpringLayout());
			panel.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(0,10,10,10),
					BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black),
					resBundle.getString("dvipngSettingsLabel"))));
            centerPanel.add(panel, BorderLayout.SOUTH);
            
            _inputDviPngOptions = createLabeledInput(DVIPNG_OPTIONS, DVIPNG_OPTIONS_DEFAULT,
                    resBundle.getString("dvipngOptionsLabel"), 20, panel);
            
            //  Set Defaults button for the LaTeX settings.
			defBtn = new JButton(resBundle.getString("defaults"));
            defBtn.setToolTipText(resBundle.getString("defDVIPNGSettignsTTText"));
			defBtn.addActionListener(new ActionListener() {
                @Override
				public void actionPerformed(ActionEvent e) {
                    setDviPngOptions(DVIPNG_OPTIONS_DEFAULT);
                    _inputDviPngOptions.setText(DVIPNG_OPTIONS_DEFAULT);
				}
			});
            panel.add(new JLabel());
            panel.add(defBtn);

			//	Configure the spring layout for the panel.
			SpringUtilities.makeCompactGrid(panel, 1, 4, 0, 0, 5, 5);
            
            
			// Define a window listener that handles closing the window.
			this.addWindowListener( new WindowAdapter() {
                @Override
				public  void windowClosing( WindowEvent e ) {
					//  Hide the window.
					PrefsDialog.this.performEscapeAction(null);
				}

			});
            
            //  Have the text boxes listen for changes to their preferences.
            initPreferencesListener();
            
            //  Put the caret at the start of the pre and post ambles.
            _inputLatexPreamble.setCaretPosition(0);
            _inputLatexPostamble.setCaretPosition(0);
            
		}
		
        /**
         * Listen for changes to preferences and update components.
         */
        private void initPreferencesListener() {
            AppPreferences.this.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
                @Override
                public void propertyChange(java.beans.PropertyChangeEvent evt) {
                    if ("LatexPreamble".equals(evt.getPropertyName())) {
                        String text = (String)evt.getNewValue();
                        if (text == null) return;
                        _inputLatexPreamble.setText(text);
                        _inputLatexPreamble.setCaretPosition(0);
                    }
                    if ("LatexPostamble".equals(evt.getPropertyName())) {
                        String text = (String)evt.getNewValue();
                        if (text == null) return;
                        _inputLatexPostamble.setText(text);
                        _inputLatexPostamble.setCaretPosition(0);
                    }
                    if ("DvipngOptions".equals(evt.getPropertyName())) {
                        String text = (String)evt.getNewValue();
                        if (text == null) return;
                        _inputDviPngOptions.setText(text);
                    }
                }
            });
        }
        
		/**
		 * Construct a new file selection JTextField with an associated label and selection button
		 * and add these three items to the specified panel.
		 * 
		 * @param prefKey The preferences database key used by this file input field.
         * @param defPath The default path to the file referred to be prefKey.
		 * @param label   The label used for this input field.
		 * @param columns The number of columns for the text field.
		 * @param btnToolTip The tool-tip to show for the select button.
         * @param panel   The panel that the text field and related items are added to.
		 */
		private JTextField createFileInput(final String prefKey, String defPath, String label, int columns, final String btnToolTip,
								JPanel panel) {
			
			//	Add the editor executable options.
			panel.add(new JLabel(label, SwingConstants.RIGHT));
			final JTextField tf = new JTextField(columns);
			tf.setEditable(true);
			tf.setDragEnabled(true);
			tf.addActionListener(new ActionListener() {
                @Override
				public void actionPerformed(ActionEvent evt) {
					JTextField tf = (JTextField)evt.getSource();
					String path = tf.getText();
					File file = new File(path);
					if (file.exists())
						AppPreferences.this.set(prefKey, path);
                    else {
						ResourceBundle resBundle = app.getResourceBundle();
                        JOptionPane.showMessageDialog(PrefsDialog.this,
                                MessageFormat.format(resBundle.getString("pathDoesntExit"), prefKey), 
								resBundle.getString("ioErrorTitle"), JOptionPane.ERROR_MESSAGE);
                    }
				}
			});
            tf.addFocusListener(new FocusListener() {
                @Override
                public void focusLost(FocusEvent evt) {
                    JTextField tf = (JTextField)evt.getSource();
                    String path = tf.getText();
                    File file = new File(path);
					if (file.exists())
						AppPreferences.this.set(prefKey, path);
                    else {
                        ResourceBundle resBundle = app.getResourceBundle();
						JOptionPane.showMessageDialog(PrefsDialog.this,
                                MessageFormat.format(resBundle.getString("pathDoesntExit"), prefKey), 
								resBundle.getString("ioErrorTitle"), JOptionPane.ERROR_MESSAGE);
                    }
                }
                @Override
                public void focusGained(FocusEvent evt) { }
            });
            
			String path = AppPreferences.this.get(prefKey);
			if (path == null)
                path = defPath;
            tf.setText(path);
			tf.setCaretPosition(tf.getDocument().getLength());
			
			panel.add(tf);
			JButton btn = new JButton("...");
			btn.addActionListener(new ActionListener() {
                @Override
				public void actionPerformed(ActionEvent e) {
					doChooseFile(prefKey, tf, btnToolTip, null);
				}
			});
			btn.setCursor( Cursor.getPredefinedCursor( Cursor.HAND_CURSOR ) );
			btn.setToolTipText( btnToolTip );
			panel.add(btn);
			
			return tf;
		}
		
		/**
		 * Ask the user to select a file using a file chooser dialog.
		 * Store the results of the file selection in the specified preferences key
		 * and text field.
		 * 
		 * @param pefKey The preferences database key where the file options is to be stored.
		 * @param tf     The text field that is to be filled in with the selected options.
		 * @param msg    The message to display to the user in the file chooser dialog.
		 * @param filter The file name filter to use or <code>null</code> for none.
		 */
		public void doChooseFile( String prefKey, JTextField tf, String msg, ExtFilenameFilter filter ) {
			ResourceBundle resBundle = app.getResourceBundle();
			
			try {
				String path = AppPreferences.this.get(prefKey);
				String parent=null, name=null;
				if (path != null) {
					File theFile = new File(path);
					parent = theFile.getParent();
					name = theFile.getName();
				}
				File theFile = AppUtilities.selectFile(prefsDialog, FileDialog.LOAD, msg,
						parent, name, filter);
				if (theFile == null)	return;
				
				String newPath = theFile.getPath();
				if (!newPath.equals(path)) {
					//	Set the project options text field.
					tf.setText(newPath);
				
					//	Store off the options reference for later use.
					AppPreferences.this.set(prefKey, newPath);
				}
				
			} catch (Exception e) {
				AppUtilities.showException(prefsDialog, resBundle.getString("unexpectedTitle"),
									resBundle.getString("unexpectedMsg"), e);
				e.printStackTrace();
			}
		}
		
		/**
		 * Construct a new JTextField with an associated label
		 * and add these 2 items to the specified panel.
		 * 
		 * @param prefKey The preferences database key used by this file input field.
         * @param defInput The default input to use if prefKey is not found in the preferences.
		 * @param label   The label used for this input field.
		 * @param columns The number of columns for the text field.
         * @param panel   The panel that the text field and related items are added to.
		 */
		private JTextField createLabeledInput(final String prefKey, String defInput, String label, int columns, JPanel panel) {
			
			//	Add the editor executable options.
			panel.add(new JLabel(label, SwingConstants.RIGHT));
			JTextField tf = new JTextField(columns);
			tf.setEditable(true);
			tf.setDragEnabled(true);
			tf.addActionListener(new ActionListener() {
                @Override
				public void actionPerformed(ActionEvent evt) {
					JTextField tf = (JTextField)evt.getSource();
					String pref = tf.getText();
					AppPreferences.this.set(prefKey, pref);
				}
			});
            tf.addFocusListener(new FocusListener() {
                @Override
                public void focusLost(FocusEvent evt) {
                    JTextField tf = (JTextField)evt.getSource();
                    String pref = tf.getText();
					AppPreferences.this.set(prefKey, pref);
                }
                @Override
                public void focusGained(FocusEvent evt) { }
            });
			String pref = AppPreferences.this.get(prefKey);
			if (pref == null)
                pref = defInput;
			tf.setText(pref);
			tf.setCaretPosition(tf.getDocument().getLength());
			
			panel.add(tf);
			
			return tf;
		}
		
		//	Add support for Control-W to close the window.
        @Override
		public void keyPressed(KeyEvent e) {
			super.keyPressed(e);
			
			int accCharMask = Toolkit.getDefaultToolkit().getMenuShortcutKeyMask();
			KeyStroke ks1 = KeyStroke.getKeyStroke( 'W', accCharMask, false );
			
			int keyCode = e.getKeyCode();
			int modifiers = e.getModifiers();
			KeyStroke ks2 = KeyStroke.getKeyStroke(keyCode, modifiers);
			
			if (ks1.equals(ks2))
				setVisible(false);
			
		}
	}
	
	/**
	 * Method that executes the specified command as a separate process and returns a list
	 * containing the output to standard out and standard error for that process.
	 *
	 * @param cmds  The array of commands and arguments to run in the system's command shell (Unix "sh" or DOS cmd.exe shell).
	 * @param dir  The working directory for the process to run in.
	 * @param wait Wait for the command to complete before returning if true.  Return after a short pause if false.
	 * @return A list of strings that represent the combination of standard out and standard error
	 * 			from the process.
	 * @throws IOException if there is any I/O error.
	 * @throws InterruptedException if the current thread is interrupted by another thread while
	 *			it is waiting.
	 **/
	private static List<String> runCommand(List<String> cmds, File dir, boolean wait) throws IOException, InterruptedException {
        ProcessBuilder pb = new ProcessBuilder(cmds);
        pb = pb.redirectErrorStream(true);
        pb = pb.directory(dir);

        if (!AppUtilities.isWindows()) {
            //	Add more directories to the search path where commands might be found.
            //	Java apps on MacOS X have only the default system path and not the user path,
            //	so this is necessary on that platform and harmless on other Unix type systems.
            Map<String, String> env = pb.environment();
            String path = env.get("PATH");
            env.put("PATH", path + ":/usr/local/bin:/opt/local/bin:/sw/bin:~/bin:.");
        }

        Process proc = pb.start();			//	Start the process.
		
		//	Collect the output from the process.
		StreamGobbler stdout = new StreamGobbler(proc.getInputStream(), "OUTPUT");
		stdout.start();
		if (wait)
			proc.waitFor();
		else
			Thread.sleep(1000*2);	//	Sleep this thread for a couple of seconds.

		//  Collect the output as a list and return it.
        stdout.join(3000);
		stdout.close();
		List<String> messages = stdout.getLines();
		
		return messages;
	}
    
    /**
     * Method that, on UNIX platforms ONLY, uses the "which" command to locate 
     * the specified executable on the search path.
     * 
     * @param name  The name of the executable to search for.
     * @param defaultPath  The default path to return if the executable can't be found.
     * @return The path to the executable or the defaultPath if the executable can't be found.
     */
    private static String findExecutablePath(String name, String defaultPath) {
        //  Assumes we have a UNIX-like system.
        List<String> cmds = new ArrayList();
        cmds.add("which");
        cmds.add(name);
        File dir = new File(System.getProperty("user.dir"));
        try {
            List<String> output = runCommand(cmds, dir, true);
            if (output.size() > 0) {
                String line = output.get(0);
                if (!line.contains("No such file")) {
                    return line;
                }
            }
        } catch (Exception e) {
        }
        return defaultPath;
    }
    
    /**
	 *  Returns true if this system has the required programs on the search path.
	 **/
	public boolean hasLatex() {
        
        try {
            File dir = new File(System.getProperty("user.dir"));
            
            //  Look for latex
            List<String> cmds = new ArrayList();
            cmds.add(getLatexApplication());
            cmds.add("-v");
            List<String> output = runCommand(cmds, dir, true);
            if (output.size() < 1)
                return false;
            String line = output.get(0);
            if (!line.contains("TeX")) {
                return false;
            }
            
            //  Look for dvipng
            cmds.set(0,getDviPngApplication());
            output = runCommand(cmds, dir, true);
            if (output.size() < 1)
                return false;
            line = output.get(0);
            if (!line.contains("This is dvipng")) {
                return false;
            }
            
        } catch (Exception e) {
            return false;
        }
        
        return true;
    }
}
