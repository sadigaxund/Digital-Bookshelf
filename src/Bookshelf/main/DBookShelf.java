package Bookshelf.main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

import javax.swing.JLabel;
import javax.swing.JList;
import java.awt.Color;
import java.awt.Component;

import java.awt.Font;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import java.awt.Cursor;
import javax.swing.UIManager;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JSeparator;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.JScrollPane;

import java.awt.ComponentOrientation;
import javax.swing.DropMode;
import java.awt.SystemColor;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.jcl.files.JImages;
import org.jcl.frameworktools.SwingAssistant;

import Utils.JFiles;

import javax.swing.ListSelectionModel;
import javax.swing.JRadioButton;

public class DBookShelf implements Listeners, Values {

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
	
	EventQueue.invokeLater(new Runnable() {

	    public void run() {
		try {
		    DBookShelf window = new DBookShelf();
		    window.mainframe.setVisible(true);
		} catch (Exception e) {
		    e.printStackTrace();
		}
	    }
	});
    }

    /**
     * Create the application.
     */
    public DBookShelf() {
	try {
	    initialize();
	} catch (IOException e) {

	}
    }

    /**
     * Initialize the contents of the frame.
     * 
     * @throws IOException
     */
    private void initialize() throws IOException {
	/* Changing the default look and feel of the application to Nimbus */
	SwingAssistant.changeLookAndFeel("Nimbus", getClass());

	/* Creating the main frame */
	mainframe = new JFrame();
	mainframe.setTitle("Digital Library");
	mainframe.getContentPane().setBackground(Color.LIGHT_GRAY);
	mainframe.setBounds(100, 100, 1150, 600);
	mainframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	/* Disabling resizibility */
	mainframe.setResizable(false);
	/* Changing the icon */
	mainframe.setIconImage(JFiles.getFileIcon(folder));
	/* Setting layout to absolute */
	mainframe.getContentPane().setLayout(null);

	JPanel mainPanel = new JPanel();
	mainPanel.setBounds(0, 22, 994 + 150, 549);
	mainPanel.setBackground(Color.WHITE);
	mainPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
	mainframe.getContentPane().add(mainPanel);
	mainPanel.setLayout(null);

	JPanel middleTopPanel = new JPanel();
	middleTopPanel.setBorder(UIManager.getBorder("Table.focusCellHighlightBorder"));
	middleTopPanel.setBackground(SystemColor.menu);
	middleTopPanel.setBounds(255, 0, 737 + 150, 38);
	mainPanel.add(middleTopPanel);
	middleTopPanel.setLayout(null);

	JPanel panelOfList = new JPanel();
	panelOfList.setBackground(SystemColor.inactiveCaptionBorder);
	panelOfList.setBorder(UIManager.getBorder("Table.focusCellHighlightBorder"));
	panelOfList.setBounds(0, 0, 256, 549);
	mainPanel.add(panelOfList);
	panelOfList.setLayout(null);

	JScrollPane scrollPaneOfList = new JScrollPane();
	scrollPaneOfList.setBounds(1, 38, 254, 491);
	panelOfList.add(scrollPaneOfList);
	scrollPaneOfList.setBorder(null);
	/* Adding list of shelves into scroll pane */
	scrollPaneOfList.setViewportView(listOfShelves);

	/* Initializing list */
	listOfShelves.setBorder(null);
	listOfShelves.setDropMode(DropMode.ON);
	listOfShelves.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
	listOfShelves.setToolTipText("");
	listOfShelves.setVisibleRowCount(22);
	listOfShelves.setFont(new Font("Arial", Font.BOLD, 16));
	listOfShelves.setBackground(SystemColor.inactiveCaptionBorder);

	JLabel lblBookshelves = new JLabel("Bookshelves");
	lblBookshelves.setBounds(10, 0, 109, 35);
	panelOfList.add(lblBookshelves);
	lblBookshelves.setFont(new Font("Arial", Font.BOLD, 16));

	JButton addNewShelf = new JButton("");
	addNewShelf.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	addNewShelf.setBounds(215, 7, 28, 25);
	panelOfList.add(addNewShelf);
	addNewShelf.setToolTipText("<html><body style = \"font-family:Prototype\">New Bookshelf\r\n</body></html>");

	JButton settings = new JButton("");
	settings.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	settings.setBounds(180, 7, 28, 25);
	panelOfList.add(settings);
	settings.setToolTipText("<html><body style = \"font-family:Prototype\">Settings\r\n</body></html>");

	JMenuBar menuBar = new JMenuBar();
	menuBar.setBounds(0, 0, 994 + 150, 25);
	mainframe.getContentPane().add(menuBar);

	JMenu mnNewMenu = new JMenu("   File   ");
	mnNewMenu.setMultiClickThreshhold(1L);
	mnNewMenu.setFont(new Font("Prototype", Font.PLAIN, 14));
	menuBar.add(mnNewMenu);

	JMenuItem mntmAddToLibrary = new JMenuItem("Add Book");
	mntmAddToLibrary.setFont(new Font("Prototype", Font.PLAIN, 13));
	mnNewMenu.add(mntmAddToLibrary);

	JMenuItem mntmCopyToBookshelf = new JMenuItem("Copy to Bookshelf");
	mntmCopyToBookshelf.setFont(new Font("Prototype", Font.PLAIN, 13));
	mnNewMenu.add(mntmCopyToBookshelf);

	JMenuItem mntmFindPdfs = new JMenuItem("Find PDFs");
	mntmFindPdfs.setFont(new Font("Prototype", Font.PLAIN, 13));
	mnNewMenu.add(mntmFindPdfs);

	JSeparator separator = new JSeparator();
	mnNewMenu.add(separator);

	JMenuItem mntmRead = new JMenuItem("Read");
	mntmRead.setFont(new Font("Prototype", Font.PLAIN, 13));
	mnNewMenu.add(mntmRead);

	JMenuItem mntmReadRecent = new JMenuItem("Read Recent");
	mntmReadRecent.setFont(new Font("Prototype", Font.PLAIN, 13));
	mnNewMenu.add(mntmReadRecent);

	JSeparator separator_1 = new JSeparator();
	mnNewMenu.add(separator_1);

	JMenuItem mntmOpenInBrowser = new JMenuItem("Open in Browser");
	mntmOpenInBrowser.setFont(new Font("Prototype", Font.PLAIN, 13));
	mnNewMenu.add(mntmOpenInBrowser);

	JMenuItem mntmClose = new JMenuItem("Close");
	mntmClose.setFont(new Font("Prototype", Font.PLAIN, 13));
	mnNewMenu.add(mntmClose);

	JMenuItem mntmRemoveFromBookshelf = new JMenuItem("Remove from Bookshelf");
	mntmRemoveFromBookshelf.setFont(new Font("Prototype", Font.PLAIN, 13));
	mnNewMenu.add(mntmRemoveFromBookshelf);

	JSeparator separator_2 = new JSeparator();
	mnNewMenu.add(separator_2);

	JMenuItem mntmPrint = new JMenuItem("Print");
	mntmPrint.setFont(new Font("Prototype", Font.PLAIN, 13));
	mnNewMenu.add(mntmPrint);

	JMenuItem mntmExit = new JMenuItem("Exit");
	mntmExit.setFont(new Font("Prototype", Font.PLAIN, 13));

	mnNewMenu.add(mntmExit);

	JMenu mnLibrary = new JMenu("   Library   ");
	mnLibrary.setFont(new Font("Prototype", Font.PLAIN, 14));
	menuBar.add(mnLibrary);

	JMenuItem mntmViewAsThumbnail = new JMenuItem("View as Thumbnail");
	mntmViewAsThumbnail.setFont(new Font("Prototype", Font.PLAIN, 14));
	mnLibrary.add(mntmViewAsThumbnail);

	JMenuItem mntmViewAsList = new JMenuItem("View as List");
	mntmViewAsList.setFont(new Font("Prototype", Font.PLAIN, 14));
	mnLibrary.add(mntmViewAsList);

	/* Further Modifications */
	/* Adding Action Listeners */

	addNewShelf.addActionListener(ADD_NEW_LIB_LISTENER);

	mntmExit.addActionListener(EXIT_MENU_ITEM_LISTENER);

	listOfShelves.addMouseListener(LIST_MOUSE_LISTENER);

	/* Changing the icon of button which adds new bookshelf */
	addNewShelf.setIcon(new ImageIcon(
		JImages.scaleImage(ImageIO.read(new File(ADD_BUTTON_ICON)), ADD_BUTTON_WIDTH, ADD_BUTTON_HEIGHT)));
	/* Changing the icon of setting button */
	settings.setIcon(new ImageIcon(JImages.scaleImage(ImageIO.read(new File(SETTINGS_BUTTON_ICON)),
		SETTINGS_BUTTON_WIDTH, SETTINGS_BUTTON_HEIGHT)));

	JPanel panelOfTable = new JPanel();
	panelOfTable.setBackground(Color.WHITE);
	panelOfTable.setBounds(255, 37, 739 + 150, 512);
	mainPanel.add(panelOfTable);
	panelOfTable.setLayout(null);

	JScrollPane scrollPaneOfTable = new JScrollPane();
	scrollPaneOfTable.setBounds(0, 0, 739 + 150, 512);
	panelOfTable.add(scrollPaneOfTable);
	scrollPaneOfTable.getViewport().setBackground(Color.WHITE);

	/* Initializing the table */
	tableOfBooks = new JTable();
	tableOfBooks.setFillsViewportHeight(true);

	tableOfBooks.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	tableOfBooks.setFont(new Font("Arial", Font.BOLD, 12));
	tableOfBooks.setShowGrid(false);
	tableOfBooks.setRowHeight(40);
	tableOfBooks.setDefaultRenderer(Object.class, new MyTableCellRenderer());
	/* Initializing the elements of the table */
	tableOfBooks.setModel(tableModel);
	tableOfBooks.getColumnModel().getColumn(0).setPreferredWidth(15);
	tableOfBooks.getColumnModel().getColumn(1).setPreferredWidth(150);
	tableOfBooks.getColumnModel().getColumn(2).setPreferredWidth(100);
	tableOfBooks.getColumnModel().getColumn(3).setPreferredWidth(100);
	tableOfBooks.getColumnModel().getColumn(4).setPreferredWidth(50);
	tableOfBooks.getColumnModel().getColumn(5).setPreferredWidth(50);
	scrollPaneOfTable.setViewportView(tableOfBooks);

    }

    static DefaultTableModel tableModel = new DefaultTableModel(new Object[][] {

    }, new String[] { "", "Title", "Author", "Publisher", "Last Read", "Date Added" });
    /* Variable Declaration */
    /**
     * <strong>- The list of the libraries<br>
     * - Located on the left handside of the frame </strong>
     */
    protected static JList<?> listOfShelves = null;

    /**
     * <strong>- The main frame of the application </strong>
     */
    protected static JFrame mainframe = null;

    /**
     * <strong>- The main folder of the application<br>
     * - All the Bookshelves are located here </strong>
     */
    protected static File folder = new File("D:\\Digital Bookshelf");

    /**
     * <strong>- The list of all bookshelves located in main folder</strong>
     */
    protected static File[] libs = null;

    /**
     * <strong>- The list model defining the elements in list of
     * bookshelves</strong>
     */
    protected static DefaultListModel<String> listModel = new DefaultListModel<>();

    /**
     * <strong>- The table where all the books that are contained in specified shelf
     * will be displayed</strong>
     */
    protected static JTable tableOfBooks;

    /* End of the variable declaration */

    public static void addElement(String obj) {
	listModel.addElement(obj);
    }

    /**
     * @return the list
     */
    public static JList<?> getList() {
	return listOfShelves;
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
	return tableOfBooks;
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
	return mainframe;
    }

    /**
     * @param frame
     *            the frame to set
     */
    public static void setFrame(JFrame frame) {
	DBookShelf.mainframe = frame;
    }

    static {

	/* If the application was run very first time */

	/* Creating main folder */
	if (!folder.exists()) {
	    folder.mkdir();
	}

	/* Creating default shelves */
	if (!new File("D:\\Digital Bookshelf\\Shelves").exists()) {
	    new File("D:\\Digital Bookshelf\\Shelves\\All Items").mkdirs();
	    new File("D:\\Digital Bookshelf\\Shelves\\Not Read").mkdirs();
	    new File("D:\\Digital Bookshelf\\Shelves\\Recently Added").mkdirs();
	    new File("D:\\Digital Bookshelf\\Shelves\\Recently Read").mkdirs();
	}

	/* Taking the list of all shelves */
	libs = new File("D:\\Digital Bookshelf\\Shelves").listFiles();

	/* Initializing the list */
	listOfShelves = new JList(listModel);
	listOfShelves.setCellRenderer(new MyListCellRenderer());

	for (File node : libs) {
	    String books[] = node.list();
	    File contain[] = node.listFiles();
	    listModel.addElement(node.getName());

	    int i = 0;
	    for (String str : books) {
		JLabel lbl = new JLabel("");

		String content[] = JFiles.readFileArraywise(contain[i++].getAbsolutePath());
		System.out.println();

		// TODO

//		tableOfBooks.setValueAt(new ImageIcon(imgFolder + "\\b3.png"), 0, 0);

		tableModel.addRow(new Object[] { lbl });
	    }
	    // TODO Learn how to get an author, title(not the name of the file), and content
	    // hierarchy

	}

    }
}
