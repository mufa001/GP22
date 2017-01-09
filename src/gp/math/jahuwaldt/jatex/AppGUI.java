package gp.math.jahuwaldt.jatex;

//import com.centerkey.utils.BareBonesBrowserLaunch;
import gp.math.jahuwaldt.io.ExtFilenameFilter;
import gp.math.jahuwaldt.jatex.MainWindow;
import gp.math.jahuwaldt.swing.AppUtilities;
import gp.math.jahuwaldt.swing.MDIApplication;
import gp.math.jahuwaldt.swing.MainApp;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.UIManager;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

import net.roydesign.app.AboutJMenuItem;
import net.roydesign.app.PreferencesJMenuItem;
import net.roydesign.app.QuitJMenuItem;
import net.roydesign.ui.StandardMacAboutFrame;

/**
 * This class represents a multi-document interface GUI application.
 * 
 * <p> Modified by: Joseph A. Huwaldt   </p>
 * 
 * @author Joseph A. Huwaldt Date: May 10, 2013
 * @version January 9, 2015
 */
public class AppGUI extends MDIApplication {

    //	Reference to the main application object for this program.
    private final MainApp app;

    /**
     * Constructor for our application that displays the GUI.
     * 
     * @param resBundle The resource bundle for this application.
     * @param application A reference to this applications MainApp instance.
     * @throws java.lang.Exception if anything can not be set up correctly.
     */
    public AppGUI(ResourceBundle resBundle, MainApp application) throws Exception {

        this.app = application;

        // Set the system look and feel to hide that hideous Java LAF.
        AppUtilities.setSystemLAF();

	    // MacOS X users like the menu bar to be at the top of the screen.
        // This does nothing on non-MacOS platforms.
        System.setProperty("apple.laf.useScreenMenuBar", "true");
        
        // Store the resource bundle.
        setResourceBundle(resBundle);

        // Set the application title.
        setName(resBundle.getString("appName"));

        // Set up the file name filter.
        ExtFilenameFilter fnFilter = new ExtFilenameFilter();

        //  Add any other extensions that might be encountered that we might be able to read.
        String fileExtensions = resBundle.getString("fileExtensions");
        String[] tokens = fileExtensions.split(",");
        for (String token : tokens) {
            fnFilter.addExtension(token.trim());
        }
        this.setFilenameFilter(fnFilter);

        //  Create the menu bar.
        JMenuBar menuBar = createMenuBar();

	// Add the menu bar to this application.
        //  Cause the application to stay open when the last window is closed under MacOS.
        //  When the last window is closed, a menu bar will remain for the application with no
        //  window open.  This is standard MacOS behavior and will do nothing on other platforms.
        setFramelessJMenuBar(menuBar);
    }

    //-----------------------------------------------------------------------------------
    /**
     * Handle the user choosing the "New..." from the File menu. Creates and
     * returns a reference to a new document window.
     * 
     * @return A new instance of this application's main window.
     */
    @Override
    public Frame handleNew(ActionEvent event) {
        Frame window = null;

        try {
            // Create an instance of an application window to get the program rolling.
            //window = new MainWindow(null,null,0);
            //window.setVisible(true);
            //this.addWindow(window);

        } catch (Exception e) {
            AppUtilities.showException(null, getResourceBundle().getString("unexpectedTitle"),
                    getResourceBundle().getString("unexpectedMsg"), e);
            e.printStackTrace();
        }

        return window;
    }

    /**
     * Create an about menu item for use in this application.
     * 
     * @return The about menu item for this program.
     */
    @Override
    public AboutJMenuItem createAboutMenuItem() {

        AboutJMenuItem about = this.getAboutJMenuItem();
        about.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ResourceBundle resBundle = getResourceBundle();

                //  Load in the application's icon image.
                Icon appIcon = null;

                try {
                    URL imgURL = ClassLoader.getSystemResource(resBundle.getString("applicationIconURL"));
                    appIcon = new ImageIcon(imgURL);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                if (appIcon == null) //  Use a generic icon since this app can't read it's custom icon.
                {
                    appIcon = UIManager.getIcon("OptionPane.informationIcon");
                }

                //  Read in the about box text information.
                String credits = readAboutText(resBundle.getString("aboutTextURLStr"));

                String aName = resBundle.getString("appName");
                String aVersion = resBundle.getString("appVersion");
                String aModDate = resBundle.getString("appModDate");
                String copyright = resBundle.getString("copyright");
                StandardMacAboutFrame f = new StandardMacAboutFrame(aName, aVersion + " - " + aModDate);
                f.setApplicationIcon(appIcon);
                f.setCopyright(copyright);
                f.setCredits(credits, "text/html");
                f.setHyperlinkListener(new HyperlinkListener() {
                    @Override
                    public void hyperlinkUpdate(HyperlinkEvent e) {
                        if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
                            //  Display the selected URL in the default browser for this platform.
                            //BareBonesBrowserLaunch.openURL(e.getURL().toString());
                        }
                    }
                });
                f.setVisible(true);
            }
        });

        return about;
    }

    /**
     * Method that handles reading in the contents of the text region of the
     * about box from a text file.
     */
    private String readAboutText(String urlStr) {
        String text;
        try {

            URL url = ClassLoader.getSystemResource(urlStr);

            InputStreamReader reader = new InputStreamReader(url.openStream(), "UTF-8");
            StringWriter writer = new StringWriter();
            int character = reader.read();
            while (character != -1) {
                writer.write(character);
                character = reader.read();
            }
            text = writer.toString();

        } catch (IOException e) {
            e.printStackTrace();

            //  Create a standard set of credits if we didn't find the text file.
            text = "<html><body><b>Author:</b><br>Joseph A. Huwaldt<br/>";
            text += "<a href=\"mailto:jhuwaldt@mac.com\">jhuwaldt@mac.com</a><br/>";
            text += "<P ALIGN=CENTER><br/>" + getResourceBundle().getString("appName") + " comes with ABSOLUTELY NO WARRANTY;";
            text += "</body></html>";
        }

        return text;
    }

    /**
     * Initializes and displays the menus associated with this application.
     */
    private JMenuBar createMenuBar() throws NoSuchMethodException {
        ResourceBundle resBundle = getResourceBundle();

        //  Create a menu bar.
        JMenuBar menuBar = new JMenuBar();

        // Set up the file menu.
        int row = 0;
        String[][] menuStrings = new String[6][3];
        menuStrings[row][0] = resBundle.getString("newItemText");
        menuStrings[row][1] = resBundle.getString("newItemKey");
        menuStrings[row][2] = "handleNew";
        ++row;
        menuStrings[row][0] = resBundle.getString("openItemText");
        menuStrings[row][1] = resBundle.getString("openItemKey");
        menuStrings[row][2] = null;
        ++row;
        ++row;	//	Blank line
        menuStrings[row][0] = resBundle.getString("closeItemText");
        menuStrings[row][1] = resBundle.getString("closeItemKey");
        menuStrings[row][2] = "handleClose";
        ++row;
        menuStrings[row][0] = resBundle.getString("saveItemText");
        menuStrings[row][1] = resBundle.getString("saveItemKey");
        menuStrings[row][2] = null;
        ++row;
        menuStrings[row][0] = resBundle.getString("saveAsItemText");
        menuStrings[row][1] = null;
        menuStrings[row][2] = null;

        JMenu menu = AppUtilities.buildMenu(this, resBundle.getString("fileMenuText"), menuStrings);
        menuBar.add(menu);

        //  Add a Quit menu item.
        QuitJMenuItem quit = this.getQuitJMenuItem();
        quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleQuit(e);
            }
        });
        if (!QuitJMenuItem.isAutomaticallyPresent()) {
            menu.addSeparator();
            menu.add(quit);
        }

        // Set up the edit menu.
        row = 0;
        menuStrings = new String[6][3];
        menuStrings[row][0] = resBundle.getString("undoItemText");
        menuStrings[row][1] = resBundle.getString("undoItemKey");
        menuStrings[row][2] = null;
        ++row;
        menuStrings[row][0] = resBundle.getString("redoItemText");
        menuStrings[row][1] = null;
        menuStrings[row][2] = null;
        ++row;
        ++row;	//	Blank line.
        menuStrings[row][0] = resBundle.getString("cutItemText");
        menuStrings[row][1] = resBundle.getString("cutItemKey");
        menuStrings[row][2] = null;
        ++row;
        menuStrings[row][0] = resBundle.getString("copyItemText");
        menuStrings[row][1] = resBundle.getString("copyItemKey");
        menuStrings[row][2] = null;
        ++row;
        menuStrings[row][0] = resBundle.getString("pasteItemText");
        menuStrings[row][1] = resBundle.getString("pasteItemKey");
        menuStrings[row][2] = null;

        menu = AppUtilities.buildMenu(this, resBundle.getString("editMenuText"), menuStrings);
        menuBar.add(menu);

        //  Add a Preferences menu item.
        PreferencesJMenuItem preferences = this.getPreferencesJMenuItem();
        preferences.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                app.getPreferences().showPreferenceDialog();
            }
        });
        if (!PreferencesJMenuItem.isAutomaticallyPresent()) {
            menu.addSeparator();
            menu.add(preferences);
        }

        //	Create an about menu item.
        AboutJMenuItem about = createAboutMenuItem();

        //	Create a "Help" menu for non-MacOS platforms.
        if (!AboutJMenuItem.isAutomaticallyPresent()) {
            //	Create Help menu.
            menu = new JMenu(resBundle.getString("helpMenuText"));
            menuBar.add(menu);

            //	Add the "About" item to the Help menu.
            menu.add(about);
        }

        return menuBar;
    }

}
