/**
 * 
 */
package Bookshelf.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

/**
 * @author Sadig Akhund
 *
 */
interface Listeners extends Values {

    public ActionListener ADD_NEW_LIB_LISTENER = new ActionListener() {
	@SuppressWarnings("unchecked")
	public void actionPerformed(ActionEvent arg0) {
	    DBookShelf.getList().clearSelection();
	    @SuppressWarnings("rawtypes")
	    DefaultListModel model = (DefaultListModel) DBookShelf.getList().getModel();

	    String newName = JOptionPane.showInputDialog(DBookShelf.getFrame(),
		    "<html><body style = \"font-family: Prototype\">Enter name for Shelf</body></html");

	    if (newName == null || newName.trim().equals("")) {
		return;
	    }
	    if (new File(DBookShelf.getFolder().getAbsolutePath() + "\\Bookcase\\" + newName).exists()) {
		int i = 2;
		do {
		    String temp = newName + ((i == 0) ? "" : "(" + i + ")");

		    File file = new File(DBookShelf.getFolder().getAbsolutePath() + "\\Bookcase\\" + temp);

		    if (!file.exists()) {
			newName = temp;
			break;
		    } else {
			i++;
		    }

		} while (true);
	    }

	    new File(DBookShelf.getFolder().getAbsolutePath() + "\\Bookcase\\" + newName).mkdirs();

	    model.addElement(newName);
	}
    };

    public ActionListener EXIT_MENU_ITEM_LISTENER = new ActionListener() {
	public void actionPerformed(ActionEvent arg0) {
	    System.exit(0);
	}
    };

    public MouseListener LIST_MOUSE_LISTENER = new MouseAdapter() {

	// TODO ADD LIBRARY DRAG
	@Override
	public void mouseClicked(MouseEvent e) {
	    DBookShelf.getTable().clearSelection();

	    @SuppressWarnings("unused")
	    double elemX = DBookShelf.getList().getX()
		    + DBookShelf.getList()
			    .getCellBounds(DBookShelf.getList().getSelectedIndex(),
				    DBookShelf.getList().getSelectedIndex() + 1)
			    .getX(),
		    elemY = DBookShelf.getList().getY()
			    + DBookShelf.getList().getCellBounds(DBookShelf.getList().getSelectedIndex(),
				    DBookShelf.getList().getSelectedIndex() + 1).getY();

	    int row = (elemY + LIST_FIXED_CELL_SIZE < e.getY()) ? -1 : 1;

	    if (row == -1) {// When user clicks on the "empty surface"
		DBookShelf.getList().clearSelection();
		return;
	    }
	}
    };
}
