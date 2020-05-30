package Bookshelf.main;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import org.jcl.files.JImages;

@SuppressWarnings("rawtypes")
public class MyListCellRenderer implements ListCellRenderer, Values {
    protected DefaultListCellRenderer defaultRenderer = new DefaultListCellRenderer();

    Icon closedIcon = new ImageIcon(
	    JImages.scaleImage(new ImageIcon(CLOSED_BOOK_ICON).getImage(), LIST_ITEM_ICON_SIZE, LIST_ITEM_ICON_SIZE));
    Icon openIcon = new ImageIcon(
	    JImages.scaleImage(new ImageIcon(OPEN_BOOK_ICON).getImage(), LIST_ITEM_ICON_SIZE, LIST_ITEM_ICON_SIZE));

    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
	    boolean cellHasFocus) {

	JLabel renderer = (JLabel) defaultRenderer.getListCellRendererComponent(list, value, index, isSelected,
		cellHasFocus);

	renderer.setIcon((isSelected && cellHasFocus) ? openIcon : closedIcon);

	return renderer;
    }
}
