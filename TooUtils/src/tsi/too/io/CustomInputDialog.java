package tsi.too.io;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.text.JTextComponent;

/**
 * 
 * @author Lucas Cristovam
 * 
 * @version 0.1
 */

@SuppressWarnings("serial")
public class CustomInputDialog extends JDialog {
	private JButton buttonOK;
	private JButton buttonCancel;
	private JLabel messageLabel;

	private final boolean singleLine;
	
	private GroupLayout groupLayout;
	private JTextComponent textComponent;
	
	private String userInput = null;
		
	public static final Dimension SINGLE_LINE_DIALOG_DIMENSION = new Dimension(300, 128);
	public static final Dimension SMALL_MULTILINE_DIALOG = new Dimension(480, 354);
	public static final Dimension MEDIUM_MULTILINE_DIALOG = new Dimension(640, 466);
	public static final Dimension LARGE_MULTILINE_DIALOG = new Dimension(800, 596);
	
	/**
	 * Creates a custom inputDialog.
	 * 
	 * @param title the input dialog title.
	 * @param message the message to be shown.
	 * @param textComponent the target input component.
	 * @param dialogDimension the preferred dialog dimension.
	 * @param singleLine 
	 */
	private CustomInputDialog(
		String title, 
		String message,
		JTextComponent textComponent,
		Dimension dialogDimension,
		boolean singleLine
	) {
		setTitle(title);		
		setModal(true);
		this.singleLine = singleLine;
		
		if(textComponent == null)
			throw new IllegalArgumentException("textComponent cannot be null");
		
		this.textComponent = textComponent;
		setResizable(false);
		setupDialog(message);
		pack();
		setSize(dialogDimension);
		setMinimumSize(dialogDimension);	
		setLocationRelativeTo(null);
	}
	
	private void setupDialog(String message) {
		createMessageLabel(message);
		createOkButton();
		createCancelButton();
		
		createLayout();
		setupTextComponent();
		setupWindowClosing();
	}
	
	private void createLayout() {
		Component inputComponent = textComponent;
		
		if(textComponent instanceof JTextArea) {
			inputComponent = new JScrollPane(textComponent);
		}
		
		groupLayout = new GroupLayout(getContentPane());
		
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(inputComponent, GroupLayout.DEFAULT_SIZE, 266, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(buttonOK)
							.addGap(6)
							.addComponent(buttonCancel)
							.addContainerGap())
						.addComponent(messageLabel, GroupLayout.DEFAULT_SIZE, 276, Short.MAX_VALUE)))
		);
		
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(messageLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(inputComponent, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(buttonCancel)
						.addComponent(buttonOK, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
					)
		);
		
		getContentPane().setLayout(groupLayout);
	}

	protected void createMessageLabel(String message) {		
		messageLabel = new JLabel(message);
		messageLabel.setFont(new Font("Dialog", Font.BOLD, 12));
	}
	
	private void createOkButton() {
		buttonOK = new JButton("Ok");
		buttonOK.addActionListener(e -> onOk());
	}

	private void createCancelButton() {
		buttonCancel = new JButton("Cancel");
		buttonCancel.addActionListener(e -> onCancel());
	}
	
	private void onOk() {
		userInput = textComponent.getText();
		dispose();
	}

	private void onCancel() {
		userInput = null;
		dispose();
	}
	
	private void setupTextComponent() {
		if(singleLine)
			textComponent.registerKeyboardAction(
				e -> onOk(),
				KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0),
				JComponent.WHEN_FOCUSED
				);
	}
	
	private void setupWindowClosing() {
		// call onCancel() when cross is clicked
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		addWindowListener((WindowListener) new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				onCancel();
			}
		});

		// call onCancel() on ESCAPE
		((JComponent) getContentPane()).registerKeyboardAction(
				e -> onCancel(),
				KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
				JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT
		);
	}
		
	public String getUserInput() {
		return userInput;
	}
	
	protected static String showInputDialog(
			String title, 
			String message,
			JTextComponent textComponent, 
			Dimension dialogDimension,
			boolean singleLine
	){
		var dialog = new CustomInputDialog(title, message, textComponent, dialogDimension, singleLine);
		dialog.setVisible(true);
		
		return dialog.getUserInput();
	}		
	
	/**
	 * Displays a single line custom input dialog
	 * @param title
	 * @param message
	 * @param textComponent
	 * @return
	 * 
	 * @since 0.1
	 */
	protected static String showSingleLineInputDialog(
			String title, 
			String message,
			JTextComponent textComponent 
	){
		return showInputDialog(title, message, textComponent, SINGLE_LINE_DIALOG_DIMENSION, true);
	}	
	
	/**
	 * Displays a multiLine custom input dialog
	 * @param title
	 * @param message
	 * @param textComponent
	 * @param dialogDimension
	 * @return 
	 * 
	 * @since 0.1
	 */
	protected static String showMultiLineInputDialog(
			String title, 
			String message,
			JTextComponent textComponent, 
			Dimension dialogDimension
	){
		return showInputDialog(title, message, textComponent, dialogDimension, false);
	}	
}
