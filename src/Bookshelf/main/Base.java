package Bookshelf.main;

import java.awt.Component;
import java.io.File;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public abstract class Base {
    /* Variable Declaration */
    /**
     * <strong>- The list of the libraries<br>
     * - Located on the left handside of the frame </strong>
     */
    protected static JList<?> list = null;

    /**
     * <strong>- The main frame of the application </strong>
     */
    protected static JFrame frame = null;

    /**
     * <strong>- The main folder of the application<br>
     * - All the Bookshelves are located here </strong>
     */
    protected static File folder = new File("D:\\Digital Bookshelf");

    /**
     * <strong>- The list of all bookshelves located in main folder</strong>
     */
    protected static String[] libs = null;

    /**
     * <strong>- The list model defining the elements in list of
     * bookshelves</strong>
     */
    protected static DefaultListModel<String> listModel = new DefaultListModel<>();

    /**
     * <strong>- The table where all the books that are contained in specified shelf
     * will be displayed</strong>
     */
    protected static JTable table;
    /* End of the variable declaration */

    @SuppressWarnings("unused")
    private static void addNewNode(JTree tree, String... nodehierarchy) {

	DefaultTreeModel model = (DefaultTreeModel) tree.getModel();

	DefaultMutableTreeNode root = (DefaultMutableTreeNode) model.getRoot();

	DefaultMutableTreeNode parent = root;

	for (int i = 0; i < nodehierarchy.length; i++) {
	    DefaultMutableTreeNode child = new DefaultMutableTreeNode(nodehierarchy[i]);
	    boolean Continue = false;

	    for (int j = 0; j < parent.getChildCount(); j++) {
		if (parent.getChildAt(j).toString().equals(nodehierarchy[i])) {
		    System.out.println("Found: " + parent.getChildAt(j));
		    child = (DefaultMutableTreeNode) parent.getChildAt(j);
		    Continue = true;
		    break;
		}
	    }

	    if (Continue) {
		parent = child;
		continue;
	    } else {
		model.insertNodeInto(child, parent, parent.getChildCount());
		System.out.println("Create: " + child + " in " + parent);

		parent = child;
		System.out.println("Changing parent: " + child);

	    }

	}
    }

    public static void addElement(String obj) {
	listModel.addElement(obj);
    }

    /**
     * @return the list
     */
    public static JList<?> getList() {
	return list;
    }

    /**
     * @param list
     *            the list to set
     */
    public static void setList(JList<?> list) {
	DBookShelf.listOfShelves = list;
    }

    /**
     * @return the table
     */
    public static JTable getTable() {
	return table;
    }

    /**
     * @param table
     *            the table to set
     */
    public static void setTable(JTable table) {
	DBookShelf.tableOfBooks = table;
    }

    /**
     * @return the folder
     */
    public static File getFolder() {
	return folder;
    }

    /**
     * @param folder
     *            the folder to set
     */
    public static void setFolder(File folder) {
	DBookShelf.folder = folder;
    }

    /**
     * @return the frame
     */
    public static Component getFrame() {
	return frame;
    }

    /**
     * @param frame
     *            the frame to set
     */
    public static void setFrame(JFrame frame) {
	DBookShelf.mainframe = frame;
    }

}
