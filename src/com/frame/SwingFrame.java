/**
 * 
 */
package com.frame;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.function.BaseFunction;
import com.function.ViewDatabaseEntry;

/**
 * @author - rahul.rathore
 * @date - 10-Dec-2014
 * @project - Swingapplication
 * @package - com.frame
 * @file name - SwingFrame.java
 */
public class SwingFrame extends JFrame implements ActionListener,WindowListener {

	private static final long serialVersionUID = 1L;
	
	private JLabel nameLable = new JLabel("User Name : ");
	private JLabel countryLable = new JLabel("Country : ");
	private JLabel addressLable = new JLabel("Address : ");
	private JLabel idLable = new JLabel("Id : ");
	
	private JTextField nameField = new JTextField();
	private JTextField countryField = new JTextField();
	private JTextArea addressField = new JTextArea(15,15);
	private JScrollPane addressScroll = new JScrollPane(addressField);
	private JTextField idField = new JTextField();
	
	private JButton idButton = new JButton("Generate Id");
	private JButton clearButton = new JButton("Clear");
	private JButton submitButton = new JButton("Submit");
	private JButton closeButton = new JButton("Close");
	private JButton viewButton = new JButton("View");
	
	private int id = 0;
	private String name = null;
	private String country = null;
	private String address = null;
	
	private final int range = 999999;
	
	
	private static BaseFunction baseFun = new BaseFunction();
	
	public SwingFrame() {
		super("Data Base Application");
		this.addWindowListener(this);
		this.addComponent();
		this.setBound();
		this.addListener();
		
	}
	
	private Dimension getScreenDimension(){
		int width = Toolkit.getDefaultToolkit().getScreenSize().width;
		int height = Toolkit.getDefaultToolkit().getScreenSize().height;
		return new Dimension(width/3 ,height/3 );
		
	}
	
	private void addListener() {
		idButton.addActionListener(this);
		clearButton.addActionListener(this);
		submitButton.addActionListener(this);
		closeButton.addActionListener(this);
		viewButton.addActionListener(this);
	}

	private void setBound(){
		
		idLable.setBounds(30, 30, 50, 20);
		idField.setBounds(130, 30, 100, 20);
		idButton.setBounds(250, 30, 100, 20);
		
		nameLable.setBounds(30, 60, 150, 20);
		nameField.setBounds(130, 60, 250, 20);
		
		countryLable.setBounds(30, 90, 150, 20);
		countryField.setBounds(130, 90, 250, 20);
		
		addressLable.setBounds(30, 120, 150, 20);
		addressScroll.setBounds(130, 120, 250, 150);
		
		submitButton.setBounds(130, 290, 100, 20);
		clearButton.setBounds(250, 290, 100, 20);	
		closeButton.setBounds(370, 290, 100, 20);
		viewButton.setBounds(450, 60, 100, 100);
	}
	
	private void addComponent() {
		
		add(idLable);
		idField.setEditable(false);
		add(idField);
		add(nameLable);
		add(nameField);
		add(countryLable);
		add(countryField);
		add(addressLable);
		addressField.setLineWrap(true);
		add(addressScroll);
		add(idButton);
		add(submitButton);
		add(clearButton);
		add(closeButton);
		add(viewButton);
		
	}
	
	
	@Override
	protected void frameInit() {
		super.frameInit();
		setVisible(true);
		setLocation(100, 100);
		setSize(getScreenDimension());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setDefaultLookAndFeelDecorated(true);
		setResizable(true);
		setLocale(null);
		
	}
	
	public static void main(String[] args) {
		new SwingFrame();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == idButton){
			this.setId();
		}else if(e.getSource() == clearButton){
			this.clearField();
		}else if(e.getSource() == submitButton){
			this.getInsertData();
			this.submit();
			submitButton.setEnabled(false);
		}else if(e.getSource() == closeButton){
			this.close();
		}else if(e.getSource() == viewButton){
			ViewDatabaseEntry view = new ViewDatabaseEntry();
			view.closeConnection();
		}
		
	}
	
	private void setId() {
		idField.setText("" + baseFun.generateID(range));
		idButton.setEnabled(false);
	}
	
	private void clearField(){
		idField.setText("");
		nameField.setText("");
		countryField.setText("");
		addressField.setText("");
		idButton.setEnabled(true);
		submitButton.setEnabled(true);
	}
	
	private void getInsertData(){
		
		id = Integer.parseInt(idField.getText());
		name = nameField.getText();
		country = countryField.getText();
		address = addressField.getText();
	}
	
	private void submit(){
		try {
			baseFun.createConnection("sa", "admin1234#");
			if(!baseFun.isTablePresent())
				baseFun.createTable();
			baseFun.insertData(id,name,country,address);
			baseFun.closeConnection();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	private void close() {
		baseFun.closeConnection();
		this.dispose();
		System.exit(NORMAL);
	}

	@Override
	public void windowOpened(WindowEvent e) {
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		baseFun.closeConnection();
		
	}

	@Override
	public void windowClosed(WindowEvent e) {	
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
}
