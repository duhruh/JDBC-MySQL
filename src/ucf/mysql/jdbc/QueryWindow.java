package ucf.mysql.jdbc;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;


public class QueryWindow {
	private static JFrame mJFrame;
	
	private static JPanel mMainPanel;
	
	private static JTextArea mSQLCommandArea;
	private static JTable mSQLResultsArea;
	
	private static JTextField mUsernameTF;
	private static JTextField mPasswordTF;
	
	private static JLabel mUsernameL;
	private static JLabel mPasswordL;
	private static JLabel mConnectionL;
	private static JLabel mDBInformationL;
	private static JLabel mSQLCommandL;
	private static JLabel mSQLExecutionL;
	
	private static JButton mExecuteButton;
	private static JButton mClearCommandButton;
	private static JButton mConnectButton;
	private static JButton mClearResultsButton;
	
	private static JSeparator js = new JSeparator();
	
	private static SpringLayout mSpringLayout;
	
	private static DBEngine mDBEngine;
	private static DAO mDAO;
	
	public static void main(String[] args){
		InitWindow();
	}
	private static void InitWindow(){
		mJFrame = new JFrame("Query Window");
		mMainPanel = new JPanel();
		mJFrame.setSize(750,350);
		
		InitLabels();
		InitTextFields();
		InitButtons();
		InitPlacement();
		
		mJFrame.add(mMainPanel);
		
		
		mJFrame.setVisible(true);
	}
	private static void InitLabels(){
		mUsernameL = new JLabel("Username");
		mPasswordL = new JLabel("Password");
		mConnectionL = new JLabel("No Connection Now");
		mDBInformationL = new JLabel("Enter Database Information");
		mSQLCommandL = new JLabel("Enter SQL Command");
		mSQLExecutionL = new JLabel("SQL Execution Results");
		
		mMainPanel.add(mUsernameL);
		mMainPanel.add(mPasswordL);
		mMainPanel.add(mConnectionL);
		mMainPanel.add(mDBInformationL);
		mMainPanel.add(mSQLCommandL);
		mMainPanel.add(mSQLExecutionL);
		
		mMainPanel.add(js);
	}
	private static void InitTextFields(){
		mUsernameTF = new JTextField(20);
		mPasswordTF = new JTextField(20);
		mSQLCommandArea = new JTextArea(5,32);
		mSQLResultsArea = new JTable();
		
		mSQLResultsArea.setPreferredSize(new Dimension(700, 100));
		
		mMainPanel.add(mUsernameTF);
		mMainPanel.add(mPasswordTF);
		mMainPanel.add(mSQLCommandArea);
		mMainPanel.add(mSQLResultsArea);
	}
	private static void InitButtons(){
		mConnectButton = new JButton("Connect");
		mExecuteButton = new JButton("Execute SQL Command");
		mExecuteButton.setEnabled(false);
		mClearCommandButton = new JButton("Clear Command");
		mClearResultsButton = new JButton("Clear Results");
		
		mConnectButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(!mUsernameTF.getText().isEmpty() ){
					mDBEngine = new DBEngine("jdbc:mysql://localhost:3306/project3",mUsernameTF.getText(),mPasswordTF.getText());
				
					try {
						mDBEngine.EstablishConnection();
					} catch (SQLException e) {
						//e.printStackTrace();
						JOptionPane.showMessageDialog(mMainPanel, "Could not connect!\nCheck username and/or password and try again!");
					}
					if(mDBEngine.getConnection() != null){
						mConnectionL.setText("jdbc:mysql://localhost:3306/project3");
						mExecuteButton.setEnabled(true);
					}
				}
			}
		});
		mExecuteButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String mQuery = mSQLCommandArea.getText();
				mDAO = new DAO(mDBEngine.getConnection());
				
					
				if(mQuery.toLowerCase().startsWith("select")){
					String[] mBrokenString = mQuery.split(" ");
					if(mBrokenString[1] == "*");
						
						
				}
				else if(mQuery.toLowerCase().startsWith("delete")){
					
				}
				else if(mQuery.toLowerCase().startsWith("instert")){
					
				}
				
			}
			
		});
		
		mMainPanel.add(mConnectButton);
		mMainPanel.add(mExecuteButton);
		mMainPanel.add(mClearCommandButton);
		mMainPanel.add(mClearResultsButton);
	}
	private static void InitPlacement(){
		mSpringLayout = new SpringLayout();
		
		mSpringLayout.putConstraint(SpringLayout.NORTH, mDBInformationL, 10, SpringLayout.NORTH, mMainPanel);
		mSpringLayout.putConstraint(SpringLayout.WEST, mDBInformationL, 5, SpringLayout.WEST, mMainPanel);
		
		mSpringLayout.putConstraint(SpringLayout.NORTH, mUsernameL, 15, SpringLayout.SOUTH, mDBInformationL);
		mSpringLayout.putConstraint(SpringLayout.WEST, mUsernameL, 5, SpringLayout.WEST, mMainPanel);
		
		mSpringLayout.putConstraint(SpringLayout.NORTH, mUsernameTF, 15, SpringLayout.SOUTH, mDBInformationL);
		mSpringLayout.putConstraint(SpringLayout.WEST, mUsernameTF, 20, SpringLayout.EAST, mUsernameL);
		
		mSpringLayout.putConstraint(SpringLayout.NORTH, mPasswordL, 15, SpringLayout.SOUTH, mUsernameL);
		mSpringLayout.putConstraint(SpringLayout.WEST, mPasswordL, 5, SpringLayout.WEST, mMainPanel);
		
		mSpringLayout.putConstraint(SpringLayout.WEST, mPasswordTF, 22, SpringLayout.EAST, mPasswordL);
		mSpringLayout.putConstraint(SpringLayout.NORTH, mPasswordTF, 10, SpringLayout.SOUTH, mUsernameTF);
		
		mSpringLayout.putConstraint(SpringLayout.NORTH, mConnectionL, 20, SpringLayout.SOUTH, mPasswordL);
		mSpringLayout.putConstraint(SpringLayout.WEST, mConnectionL, 10, SpringLayout.WEST, mMainPanel);
		
		mSpringLayout.putConstraint(SpringLayout.NORTH, mConnectButton, 10, SpringLayout.SOUTH, mPasswordTF);
		mSpringLayout.putConstraint(SpringLayout.WEST, mConnectButton, 80, SpringLayout.EAST, mConnectionL);
		
		mSpringLayout.putConstraint(SpringLayout.WEST, mSQLCommandL, 160, SpringLayout.EAST, mDBInformationL);
		mSpringLayout.putConstraint(SpringLayout.NORTH, mSQLCommandL, 10, SpringLayout.NORTH, mMainPanel);
		
		mSpringLayout.putConstraint(SpringLayout.NORTH, mSQLCommandArea, 10, SpringLayout.SOUTH, mSQLCommandL);
		mSpringLayout.putConstraint(SpringLayout.WEST, mSQLCommandArea, 40, SpringLayout.EAST, mUsernameTF);
		
		mSpringLayout.putConstraint(SpringLayout.EAST, mClearCommandButton, 0, SpringLayout.EAST, mSQLCommandArea);
		mSpringLayout.putConstraint(SpringLayout.NORTH, mClearCommandButton, 10, SpringLayout.SOUTH, mSQLCommandArea);
		
		mSpringLayout.putConstraint(SpringLayout.WEST, mExecuteButton, 0, SpringLayout.WEST, mSQLCommandArea);
		mSpringLayout.putConstraint(SpringLayout.NORTH, mExecuteButton, 10, SpringLayout.SOUTH, mSQLCommandArea);
		
		mSpringLayout.putConstraint(SpringLayout.SOUTH, mSQLResultsArea, -10, SpringLayout.SOUTH, mMainPanel);
		mSpringLayout.putConstraint(SpringLayout.WEST, mSQLResultsArea, 10, SpringLayout.WEST, mMainPanel);
		
		mSpringLayout.putConstraint(SpringLayout.EAST, mClearResultsButton, 0, SpringLayout.EAST, mSQLResultsArea);
		mSpringLayout.putConstraint(SpringLayout.SOUTH, mClearResultsButton, -10, SpringLayout.NORTH, mSQLResultsArea);
		
		mSpringLayout.putConstraint(SpringLayout.SOUTH, mSQLExecutionL, -10, SpringLayout.NORTH, mSQLResultsArea);
		mSpringLayout.putConstraint(SpringLayout.WEST, mSQLExecutionL, 5, SpringLayout.WEST, mMainPanel);
		
		mMainPanel.setLayout(mSpringLayout);
	}
}
