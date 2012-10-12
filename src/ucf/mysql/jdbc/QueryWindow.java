/**
 * Author: David Rivera
 * Course: CNT 4714 – Fall 2012
 * Assignment title: Program 3 – Java JDBC
 * Date: October 21, 2012
 */
package ucf.mysql.jdbc;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.table.DefaultTableModel;

/**
 * Initializes and displays the GUI query window
 * @author david
 *
 */
public class QueryWindow {
	private static JFrame mJFrame;
	
	private static JSeparator mJSeparator;
	
	private static JPanel mMainPanel;
	
	private static JTextArea mSQLCommandArea;
	private static JTable mSQLResultsArea;
	
	private static JScrollPane mResultsScrollPane;
	private static JScrollPane mCommandScrollPane;
	
	private static JTextField mUsernameTF;
	private static JTextField mPasswordTF;
	
	private static JLabel mUsernameL;
	private static JLabel mPasswordL;
	private static JLabel mConnectionL;
	private static JLabel mDBInformationL;
	private static JLabel mSQLCommandL;
	private static JLabel mSQLExecutionL;
	private static JLabel mDatabaseL;
	private static JLabel mDriverL;
	
	private static JComboBox mDatabaseCB;
	private static JComboBox mDriverCB;
	
	private static JButton mExecuteButton;
	private static JButton mClearCommandButton;
	private static JButton mConnectButton;
	private static JButton mClearResultsButton;
	
	private static SpringLayout mSpringLayout;
	
	private static DBEngine mDBEngine = null;
	private static DAO mDAO;
	
	public static void main(String[] args){
		InitWindow();
	}
	private static void InitWindow(){
		mJFrame = new JFrame("Query Window");
		mMainPanel = new JPanel();
		mJFrame.setSize(750,450);
		
		mJSeparator = new JSeparator();
        Dimension mDim = mJSeparator.getPreferredSize();  
        mDim.height = 1;
        mJSeparator.setPreferredSize(mDim);   
		
        mMainPanel.add(mJSeparator);
        
		InitLabels();
		InitTextFields();
		InitButtons();
		InitPlacement();
		
		mJFrame.add(mMainPanel);
		
		mJFrame.addWindowListener(new WindowListener(){

			@Override
			public void windowActivated(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosed(WindowEvent arg0) {

			}

			@Override
			public void windowClosing(WindowEvent arg0) {
				if(mDBEngine != null){
					try {
						mDBEngine.CloseConnection();
					} catch (SQLException e) {
						System.out.println("Could not close connection, it's okay though application closing...");
						mJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					}
				}else{
					mJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				}
			}

			@Override
			public void windowDeactivated(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeiconified(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowIconified(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowOpened(WindowEvent arg0) {
				// TODO Auto-generated method stub
				
			}});
		
		mJFrame.setVisible(true);
	}
	private static void InitLabels(){
		mUsernameL = new JLabel("Username");
		mPasswordL = new JLabel("Password");
		mConnectionL = new JLabel("No Connection Now");
		mDBInformationL = new JLabel("Enter Database Information");
		mSQLCommandL = new JLabel("Enter SQL Command");
		mSQLExecutionL = new JLabel("SQL Execution Results");
		mDriverL = new JLabel("JBDC Driver");
		mDatabaseL = new JLabel("Database URL");
		
		mMainPanel.add(mUsernameL);
		mMainPanel.add(mPasswordL);
		mMainPanel.add(mConnectionL);
		mMainPanel.add(mDBInformationL);
		mMainPanel.add(mSQLCommandL);
		mMainPanel.add(mSQLExecutionL);
		mMainPanel.add(mDriverL);
		mMainPanel.add(mDatabaseL);
		
	}
	private static void InitTextFields(){
		mUsernameTF = new JTextField(20);
		mPasswordTF = new JTextField(20);
		
		mSQLCommandArea = new JTextArea(5,30);
		
		mSQLResultsArea = new JTable();
		
		mDriverCB = new JComboBox(new String[]{"com.mysql.jbdc.Driver"});
		mDatabaseCB = new JComboBox(new String[]{"jdbc:mysql://localhost:3306/project3"});
		//mDriverCB.setEnabled(false);
		//mDatabaseCB.setEnabled(false);
		
		mResultsScrollPane = new JScrollPane(mSQLResultsArea);
		mCommandScrollPane = new JScrollPane(mSQLCommandArea);
		
		mMainPanel.add(mUsernameTF);
		mMainPanel.add(mPasswordTF);
		mMainPanel.add(mCommandScrollPane);
		mMainPanel.add(mResultsScrollPane);
		mMainPanel.add(mDriverCB);
		mMainPanel.add(mDatabaseCB);
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
				Vector<String> mColumns = new Vector<String>();
				Vector<Vector<String>> mResults = new Vector<Vector<String>>();

				if(mQuery.toLowerCase().startsWith("select")){
					try {
						mResults = mDAO.runQuery(mQuery);
						mColumns = mDAO.getColumns();
					} catch (SQLException e) {
						JOptionPane.showMessageDialog(mMainPanel, "Could not execute query!");
						//e.printStackTrace();
					}finally{
						mSQLResultsArea.setModel(new DefaultTableModel(mResults,mColumns));
					}
				}
				else{
					try {
						mDAO.runUpdate(mQuery);
						mSQLResultsArea.setModel(new DefaultTableModel(new String[][]{new String[]{"Row Updated!"}},new String[]{""}));
					} catch (SQLException e) {
						JOptionPane.showMessageDialog(mMainPanel, "Could not execute query!");
						e.printStackTrace();
					}finally{
						
					}
				}
				
			}
			
		});
		mClearCommandButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				mSQLCommandArea.setText("");
				
			}});
		mClearResultsButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				mSQLResultsArea.setModel(new DefaultTableModel(new String[][]{new String[]{""}},new String[]{""}));
				
			}});
		
		mMainPanel.add(mConnectButton);
		mMainPanel.add(mExecuteButton);
		mMainPanel.add(mClearCommandButton);
		mMainPanel.add(mClearResultsButton);
	}
	private static void InitPlacement(){
		mSpringLayout = new SpringLayout();
		
		mSpringLayout.putConstraint(SpringLayout.NORTH, mDBInformationL, 10, SpringLayout.NORTH, mMainPanel);
		mSpringLayout.putConstraint(SpringLayout.WEST, mDBInformationL, 5, SpringLayout.WEST, mMainPanel);
		
		mSpringLayout.putConstraint(SpringLayout.NORTH, mDriverL,20, SpringLayout.SOUTH,mDBInformationL);
		mSpringLayout.putConstraint(SpringLayout.WEST, mDriverL,5, SpringLayout.WEST,mMainPanel);
		
		mSpringLayout.putConstraint(SpringLayout.NORTH, mDriverCB, 15, SpringLayout.SOUTH, mDBInformationL);
		mSpringLayout.putConstraint(SpringLayout.WEST, mDriverCB, 0, SpringLayout.WEST, mUsernameTF);
		mSpringLayout.putConstraint(SpringLayout.EAST, mDriverCB, -10, SpringLayout.HORIZONTAL_CENTER, mMainPanel);
		
		mSpringLayout.putConstraint(SpringLayout.NORTH, mDatabaseL,18, SpringLayout.SOUTH,mDriverL);
		mSpringLayout.putConstraint(SpringLayout.WEST, mDatabaseL,5, SpringLayout.WEST,mMainPanel);
		
		mSpringLayout.putConstraint(SpringLayout.NORTH, mDatabaseCB, 10, SpringLayout.SOUTH, mDriverCB);
		mSpringLayout.putConstraint(SpringLayout.WEST, mDatabaseCB, 0, SpringLayout.WEST, mUsernameTF);
		mSpringLayout.putConstraint(SpringLayout.EAST, mDatabaseCB, -10, SpringLayout.HORIZONTAL_CENTER, mMainPanel);
		
		mSpringLayout.putConstraint(SpringLayout.NORTH, mUsernameL, 20, SpringLayout.SOUTH, mDatabaseL);
		mSpringLayout.putConstraint(SpringLayout.WEST, mUsernameL, 5, SpringLayout.WEST, mMainPanel);
		
		mSpringLayout.putConstraint(SpringLayout.NORTH, mUsernameTF, 15, SpringLayout.SOUTH, mDatabaseCB);
		mSpringLayout.putConstraint(SpringLayout.WEST, mUsernameTF, 30, SpringLayout.EAST, mUsernameL);
		mSpringLayout.putConstraint(SpringLayout.EAST, mUsernameTF, -10, SpringLayout.HORIZONTAL_CENTER, mMainPanel);
		
		mSpringLayout.putConstraint(SpringLayout.NORTH, mPasswordL, 15, SpringLayout.SOUTH, mUsernameL);
		mSpringLayout.putConstraint(SpringLayout.WEST, mPasswordL, 5, SpringLayout.WEST, mMainPanel);
		
		mSpringLayout.putConstraint(SpringLayout.WEST, mPasswordTF, 0, SpringLayout.WEST, mUsernameTF);
		mSpringLayout.putConstraint(SpringLayout.NORTH, mPasswordTF, 10, SpringLayout.SOUTH, mUsernameTF);
		mSpringLayout.putConstraint(SpringLayout.EAST, mPasswordTF, -10, SpringLayout.HORIZONTAL_CENTER, mMainPanel);
		
		mSpringLayout.putConstraint(SpringLayout.SOUTH, mConnectionL, -10, SpringLayout.VERTICAL_CENTER, mMainPanel);
		mSpringLayout.putConstraint(SpringLayout.WEST, mConnectionL, 10, SpringLayout.WEST, mMainPanel);
		
		mSpringLayout.putConstraint(SpringLayout.SOUTH, mConnectButton, -10, SpringLayout.VERTICAL_CENTER, mMainPanel);
		mSpringLayout.putConstraint(SpringLayout.EAST, mConnectButton, -10, SpringLayout.HORIZONTAL_CENTER, mMainPanel);
		
		mSpringLayout.putConstraint(SpringLayout.WEST, mSQLCommandL, 160, SpringLayout.EAST, mDBInformationL);
		mSpringLayout.putConstraint(SpringLayout.NORTH, mSQLCommandL, 10, SpringLayout.NORTH, mMainPanel);
		mSpringLayout.putConstraint(SpringLayout.WEST, mSQLCommandL, 10, SpringLayout.HORIZONTAL_CENTER, mMainPanel);
		
		mSpringLayout.putConstraint(SpringLayout.WEST, mCommandScrollPane, 10, SpringLayout.HORIZONTAL_CENTER, mMainPanel);
		mSpringLayout.putConstraint(SpringLayout.NORTH, mCommandScrollPane, 10, SpringLayout.SOUTH, mSQLCommandL);
		mSpringLayout.putConstraint(SpringLayout.EAST, mCommandScrollPane, -10, SpringLayout.EAST, mMainPanel);
		mSpringLayout.putConstraint(SpringLayout.SOUTH, mCommandScrollPane, -10, SpringLayout.NORTH, mClearCommandButton);
		
		mSpringLayout.putConstraint(SpringLayout.EAST, mClearCommandButton, -10, SpringLayout.EAST, mMainPanel);
		mSpringLayout.putConstraint(SpringLayout.SOUTH, mClearCommandButton, -10, SpringLayout.VERTICAL_CENTER, mMainPanel);
		
		mSpringLayout.putConstraint(SpringLayout.WEST, mExecuteButton, 0, SpringLayout.WEST, mCommandScrollPane);
		mSpringLayout.putConstraint(SpringLayout.SOUTH, mExecuteButton, -10, SpringLayout.VERTICAL_CENTER, mMainPanel);
		
		
		mSpringLayout.putConstraint(SpringLayout.SOUTH, mResultsScrollPane, -10, SpringLayout.SOUTH, mMainPanel);
		mSpringLayout.putConstraint(SpringLayout.WEST, mResultsScrollPane, 10, SpringLayout.WEST, mMainPanel);
		mSpringLayout.putConstraint(SpringLayout.NORTH, mResultsScrollPane, 10, SpringLayout.SOUTH, mClearResultsButton);
		mSpringLayout.putConstraint(SpringLayout.EAST, mResultsScrollPane, -10, SpringLayout.EAST, mMainPanel);

		mSpringLayout.putConstraint(SpringLayout.NORTH, mClearResultsButton, 10, SpringLayout.VERTICAL_CENTER, mMainPanel);
		mSpringLayout.putConstraint(SpringLayout.EAST, mClearResultsButton, 0, SpringLayout.EAST, mResultsScrollPane);
		
		mSpringLayout.putConstraint(SpringLayout.NORTH, mSQLExecutionL, 10, SpringLayout.VERTICAL_CENTER, mMainPanel);
		mSpringLayout.putConstraint(SpringLayout.WEST, mSQLExecutionL, 5, SpringLayout.WEST, mMainPanel);
		
		mSpringLayout.putConstraint(SpringLayout.HORIZONTAL_CENTER, mJSeparator, 0, SpringLayout.HORIZONTAL_CENTER, mMainPanel);
		mSpringLayout.putConstraint(SpringLayout.VERTICAL_CENTER, mJSeparator, 0, SpringLayout.VERTICAL_CENTER, mMainPanel);
		mSpringLayout.putConstraint(SpringLayout.EAST, mJSeparator, 0, SpringLayout.EAST, mMainPanel);
		mSpringLayout.putConstraint(SpringLayout.WEST, mJSeparator, 0, SpringLayout.WEST, mMainPanel);
		
		mMainPanel.setLayout(mSpringLayout);
	}
}
