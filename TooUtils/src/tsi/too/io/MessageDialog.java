package tsi.too.io;

import static javax.swing.JOptionPane.INFORMATION_MESSAGE;
import static javax.swing.JOptionPane.PLAIN_MESSAGE;
import static javax.swing.JOptionPane.WARNING_MESSAGE;
import static javax.swing.JOptionPane.YES_NO_OPTION;
import static javax.swing.JOptionPane.YES_OPTION;
import static javax.swing.JOptionPane.showConfirmDialog;
import static javax.swing.JOptionPane.showMessageDialog;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Frame;

import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

/**
 * Simple class to pop up a standard dialog box that informs user of something.
 * 
 * @author Lucas Cristovam
 * 
 * @version 0.1
 */
public abstract class MessageDialog {
    
	/**
     * Brings up an information-message dialog.
     *
     * @param parentComponent the {@link Frame} to which this dialog is associated.
     * @param title   the <code>Object</code> to display in the dialog title bar.
     * @param message the <code>Object</code> to display.
     * 
     * @since 0.1
     */
    public static void showInformationDialog(Component parentComponent, String title, Object message) {
        showMessageDialog(parentComponent, message, title, INFORMATION_MESSAGE);
    }
    
    /**
     * Brings up an information-message dialog.
     *
     * @param title   the <code>Object</code> to display in the dialog title bar.
     * @param message the <code>Object</code> to display.
     * 
     * @since 0.1
     */
    public static void showInformationDialog( String title, Object message) {
    	showInformationDialog(null, title, message);
    }

    /**
     * Brings up a plain-message dialog.
     *
     * @param parentComponent the {@link Frame} to which this dialog is associated.
     * @param title   the <code>Object</code> to display in the dialog title bar.
     * @param message the <code>Object</code> to display.
     * 
     * @since 0.1
     */
    public static void showPlainMessageDialog(Component parentComponent, String title, Object message) {
        showMessageDialog(parentComponent, message, title, PLAIN_MESSAGE);
    }

    /**
     * Brings up a plain-message dialog.
     *
     * @param title   the <code>Object</code> to display in the dialog title bar.
     * @param message the <code>Object</code> to display.
     * 
     * @since 0.1
     */
    public static void showPlainMessageDialog(String title, Object message) {
    	showPlainMessageDialog(null, title, message);
    }
    
    /**
     * Brings up an alert-message dialog.
     *
     * @param parentComponent the {@link Frame} to which this dialog is associated.
     * @param title   the <code>Object</code> to display in the dialog title bar.
     * @param message the <code>Object</code> to display.
     * 
     * @since 0.1
     */
    public static void showAlertDialog(Component parentComponent, String title, Object message) {
        showMessageDialog(parentComponent, message, title, WARNING_MESSAGE);
    }
    
    /**
     * Brings up an alert-message dialog.
     *
     * @param title   the <code>Object</code> to display in the dialog title bar.
     * @param message the <code>Object</code> to display.
     * 
     * @since 0.1
     */
    public static void showAlertDialog(String title, Object message) {
    	showAlertDialog(null, title, message);
    }

    /**
     * Brings up a confirmation dialog with <code>YES_NO_OPTION</code> options.
     *
     * @param parentComponent the {@link Frame} to which this dialog is associated.
     * @param title   the <code>Object</code> to display in the dialog title bar.
     * @param message the <code>Object</code> to display.
     * @return true if user clicked on <code>YES_OPTION</code>
     * 
     * @since 0.1
     */
    public static boolean showConfirmationDialog(Component parentComponent, String title, String message) {
        return showConfirmDialog(
                parentComponent,
                message,
                title,
                YES_NO_OPTION,
                PLAIN_MESSAGE
        ) == YES_OPTION;
    }
    
    /**
     * Brings up a confirmation dialog with <code>YES_NO_OPTION</code> options.
     *
     * @param title   the <code>Object</code> to display in the dialog title bar.
     * @param message the <code>Object</code> to display.
     * @return true if user clicked on <code>YES_OPTION</code>
     * 
     * @since 0.1
     */
    public static boolean showConfirmationDialog(String title, String message) {
        return showConfirmationDialog(null, title, message);
    }

    /**
     * Brings up an information-message dialog written in a {@link JTextArea} inside a
     * {@link JScrollPane} showing scrollBars when is needed.
     *
     * @param parentComponent the {@link Frame} to which this dialog is associated.
     * @param title   the <code>Object</code> to display in the dialog title bar.
     * @param message the <code>Object</code> to display.
     * 
     * @since 0.1
     */
    public static void showTextMessage(Component parentComponent, String title, String message) {
        var textArea = new JTextArea(10, 50);
        int margin = 5;

        textArea.setEditable(false);
        textArea.setText(message);
        textArea.setBorder(BorderFactory.createCompoundBorder(
                textArea.getBorder(),
                BorderFactory.createEmptyBorder(margin, margin, margin, margin)));

        showPlainMessageDialog(parentComponent, title, new JScrollPane(textArea));
    }
    
    /**
     * Brings up an information-message dialog written in a {@link JTextArea} inside a
     * {@link JScrollPane} showing scrollBars when is needed.
     *
     * @param title   the <code>Object</code> to display in the dialog title bar.
     * @param message the <code>Object</code> to display.
     * 
     * @since 0.1
     */
    public static void showTextMessage(String title, String message) {
        showTextMessage(null, title, message);
    }
    
    /**
     * Displays a table in a message box based on {@link TableModel}
     * 
     * @param parentComponent the {@link Frame} to which this dialog is associated.
     * @param title the dialog title
     * @param tableModel the {@link TableModel} for the displaying table.
     * @param colunsWidth the columns width
     * @param tableDimension the preferred table {@link Dimension}
     * 
     * @since 0.1
     */
    public static void showDataTable(
    		Component parentComponent, 
    		String title, 
    		TableModel tableModel,
    		int[] colunsWidth, 
    		Dimension tableDimension
    ) {
		JTable table = new JTable(tableModel);
		
		TableColumnModel taColumnModel = table.getColumnModel();
		
		if(colunsWidth != null) {
			for(int i = 0; i < colunsWidth.length; i++)
				taColumnModel.getColumn(i).setPreferredWidth(colunsWidth[i]);
		}
		
		table.setPreferredScrollableViewportSize(tableDimension);
		showPlainMessageDialog(parentComponent, title, new JScrollPane(table));
	}
    
    /**
     * Displays a table in a message box based on {@link TableModel}
     * 
     * @param title the dialog title
     * @param tableModel the {@link TableModel} for the displaying table.
     * @param colunsWidth the columns width
     * @param tableDimension the preferred table {@link Dimension}
     * 
     * @since 0.1
     */
    public static void showDataTable(
    		String title, 
    		TableModel tableModel,
    		int[] colunsWidth, 
    		Dimension tableDimension
    ) {
    	showDataTable( title, tableModel, colunsWidth, tableDimension);
	}
}