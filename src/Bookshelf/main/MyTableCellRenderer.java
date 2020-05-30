package Bookshelf.main;

import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import org.jcl.files.JImages;

public class MyTableCellRenderer extends DefaultTableCellRenderer {
    /**
     * 
     */
    private static final long serialVersionUID = 9113339387738548532L;
    protected DefaultTableCellRenderer defaultRenderer = new DefaultTableCellRenderer();


    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
	    int row, int column) {
	super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
	setBorder(noFocusBorder);

	JLabel renderer = (JLabel) defaultRenderer.getTableCellRendererComponent(table, value, isSelected, hasFocus,
		row, column);
	renderer.setIcon(new ImageIcon(JImages.scaleImage(new ImageIcon(Values.OPEN_BOOK_ICON).getImage(),
		Values.LIST_ITEM_ICON_SIZE, Values.LIST_ITEM_ICON_SIZE)));

	return this;
    }
}
