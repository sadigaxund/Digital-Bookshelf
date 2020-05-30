package org.jcl.frameworktools;

import java.awt.Component;
import java.io.File;
import java.lang.invoke.MethodHandles;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFileChooser;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class SwingAssistant {

    public static void main(String[] args) {

    }

    /**
     * Firstly, this method checks whether a given style is in the installed "Look
     * and Feel"s list, if yes then it changes the standard "look and feel"
     * 
     * @param lookAndFeel
     *            - a given style
     * @param LoggerClass
     *            - A name for the logger. This should be a dot-separated name and
     *            should normally be based on the package name or class name of the
     *            subsystem, such as java.net or javax.swing
     */
    public static void changeLookAndFeel(String lookAndFeel, final Class<?> LoggerClass) {
	System.out.println(MethodHandles.lookup().lookupClass());
	try {
	    for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
//		System.out.println(info.getName());
		if (lookAndFeel.equals(info.getName())) {
		    UIManager.setLookAndFeel(info.getClassName());
		    break;
		}
	    }
	} catch (ClassNotFoundException ex) {
	    Logger.getLogger(LoggerClass.getName()).log(Level.SEVERE, null, ex);
	} catch (InstantiationException ex) {
	    Logger.getLogger(LoggerClass.getName()).log(Level.SEVERE, null, ex);
	} catch (IllegalAccessException ex) {
	    Logger.getLogger(LoggerClass.getName()).log(Level.SEVERE, null, ex);
	} catch (UnsupportedLookAndFeelException ex) {
	    Logger.getLogger(LoggerClass.getName()).log(Level.SEVERE, null, ex);
	}
    }

    /**
     * Opens default directory browser
     * 
     * @return selected directory, if no file has been selected then returns null
     */
    public static File openDirectoryBrowser() {
	return openDirectoryBrowser(null, "Browse");
    }

    /**
     * Opens default directory browser
     * 
     * @param Title
     *            - a Title of the Browser's frame
     * @return selected directory, if no file has been selected then returns null
     */
    public static File openDirectoryBrowser(String Title) {
	return openDirectoryBrowser(null, Title);
    }

    /**
     * Opens default directory browser
     * 
     * @param Title
     *            - a Title of the Browser's frame
     * @param parent
     *            - a Parent Component
     * @return selected directory, if no file has been selected then returns null
     */
    public static File openDirectoryBrowser(Component parent, String Title) {
	return openDirectoryBrowser(parent, Title, new File("."));

    }

    /**
     * Opens default directory browser
     * 
     * @param Title
     *            - a Title of the Browser's frame
     * @param parent
     *            - a Parent Component
     * @param CurrentDirectory
     *            - a directory that will be first displayed when browser is opened
     * @return selected directory, if no file has been selected then returns null
     */
    public static File openDirectoryBrowser(Component parent, String Title, File CurrentDirectory) {
	JFileChooser chooser = new JFileChooser();
	chooser.setCurrentDirectory(CurrentDirectory);
	chooser.setDialogTitle(Title);
	chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	//
	// disable the "All files" option.
	//
	chooser.setAcceptAllFileFilterUsed(false);
	//
	if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
	    return chooser.getSelectedFile();
	} else {
	    return null;
	}
    }

    /**
     * Opens default file browser
     * 
     * @param dialogTitle
     *            - a Title of the Browser's frame
     * @param parent
     *            - a Parent Component
     * @param CurrentDirectory
     *            - a directory that will be first displayed when browser is opened
     * @return selected file, if no file has been selected then returns null
     */
    public static File openFileBrowser(Component parent, File Directory, String dialogTitle) {
	JFileChooser fileChooser = new JFileChooser();
	fileChooser.setCurrentDirectory(Directory);
	int result = fileChooser.showOpenDialog(parent);
	fileChooser.setDialogTitle(dialogTitle);
	if (result == JFileChooser.APPROVE_OPTION) {

	    return fileChooser.getSelectedFile();
	}
	return null;
    }

    /**
     * Opens default file browser, with the current directory set to user.home
     * 
     * @param dialogTitle
     *            - a Title of the Browser's frame
     * @param parent
     *            - a Parent Component
     * @return selected file, if no file has been selected then returns null
     */
    public static File openFileBrowser(Component parent, String dialogTitle) {
	return openFileBrowser(parent, new File(System.getProperty("user.home")), dialogTitle);
    }

    /**
     * Opens default file browser, with the current directory set to user.home and
     * no parent component
     * 
     * @param dialogTitle
     *            - a Title of the Browser's frame
     * @return selected file, if no file has been selected then returns null
     */
    public static File openFileBrowser(String dialogTitle) {
	return openFileBrowser(null, new File(System.getProperty("user.home")), dialogTitle);
    }

    /**
     * Opens default file browser, with the current directory set to user.home and
     * with default name
     * 
     * @param dialogTitle
     *            - a Title of the Browser's frame
     * @param parent
     *            - a Parent Component
     * @return selected file, if no file has been selected then returns null
     */
    public static File openFileBrowser(Component parent) {
	return openFileBrowser(parent, new File(System.getProperty("user.home")), "Browse");
    }

    /**
     * Opens default file browser, with the current directory set to user.home, with
     * default name and no parent component
     * 
     * @param dialogTitle
     *            - a Title of the Browser's frame
     * @param parent
     *            - a Parent Component
     * @return selected file, if no file has been selected then returns null
     */
    public static File openFileBrowser() {
	return openFileBrowser(null, new File(System.getProperty("user.home")), "Browse");
    }
}
