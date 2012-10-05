package ucf.mysql.jdbc;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;


public class QueryWindow {
	private static JFrame mJFrame;
	
	private static JPanel mCommandPanel;
	private static JPanel mAdminPanel;
	private static JPanel mSQLPanel;
	
	private static JTextArea mSQLCommandArea;
	private static JTextArea mSQLResultsArea;
	
	private static JTextField mUsernameTF;
	private static JTextField mPasswordTF;
	
	private static JLabel mUsernameL;
	private static JLabel mPasswordL;
	private static JLabel mConnectionL;
	
	private static JButton mExecuteButton;
	private static JButton mClearCommandButton;
	private static JButton mConnectButton;
	private static JButton mClearResultsButton;
	
	private static SpringLayout mSpringLayout;
	
	public static void main(String[] args){
		InitWindow();
	}
	private static void InitWindow(){
		mJFrame = new JFrame("Query Window");
		mSpringLayout = new SpringLayout();
		mJFrame.setSize(1000,1000);
		mJFrame.setLayout(mSpringLayout);
		
		InitAdminPanel();
		InitCommandPanel();
		
		
		
		mJFrame.add(mCommandPanel);
		mJFrame.add(mAdminPanel);

		//mSpringLayout.putConstraint(SpringLayout.WEST, mAdminPanel, 5, SpringLayout.WEST, mJFrame);
		//mSpringLayout.putConstraint(SpringLayout.NORTH, mAdminPanel, 5, SpringLayout.NORTH, mJFrame);
		
		//mSpringLayout.putConstraint(SpringLayout.NORTH, mCommandPanel, 5, SpringLayout.NORTH, mJFrame);
		//mSpringLayout.putConstraint(SpringLayout.WEST, mCommandPanel, 5, SpringLayout.EAST, mAdminPanel);
		
		
		mJFrame.setVisible(true);
	}
	private static void InitAdminPanel(){
		mSpringLayout = new SpringLayout();
		mAdminPanel = new JPanel(mSpringLayout);
		mAdminPanel.setSize(100, 50);
		
		mUsernameL = new JLabel("Username");
		mPasswordL = new JLabel("Password");
		mConnectionL = new JLabel("No Connection Now");
		
		mConnectButton = new JButton("Connect");
		
		mUsernameTF = new JTextField(10);
		mPasswordTF = new JTextField(10);
		
		mAdminPanel.add(mUsernameL);
		mAdminPanel.add(mPasswordL);
		mAdminPanel.add(mConnectionL);
		mAdminPanel.add(mConnectButton);
		mAdminPanel.add(mUsernameTF);
		mAdminPanel.add(mPasswordTF);
		
		mSpringLayout.putConstraint(SpringLayout.NORTH, mUsernameL, 10, SpringLayout.NORTH, mAdminPanel);
		mSpringLayout.putConstraint(SpringLayout.WEST, mUsernameL, 10, SpringLayout.WEST, mAdminPanel);
		
		mSpringLayout.putConstraint(SpringLayout.NORTH, mUsernameTF, 10, SpringLayout.NORTH, mAdminPanel);
		mSpringLayout.putConstraint(SpringLayout.WEST, mUsernameTF, 10, SpringLayout.EAST, mUsernameL);
		
		mSpringLayout.putConstraint(SpringLayout.NORTH, mPasswordL, 10, SpringLayout.SOUTH, mUsernameL);
		mSpringLayout.putConstraint(SpringLayout.WEST, mPasswordL, 10, SpringLayout.WEST, mAdminPanel);
		
		mSpringLayout.putConstraint(SpringLayout.WEST, mPasswordTF, 10, SpringLayout.EAST, mPasswordL);
		mSpringLayout.putConstraint(SpringLayout.NORTH, mPasswordTF, 10, SpringLayout.SOUTH, mUsernameTF);
		
		mSpringLayout.putConstraint(SpringLayout.NORTH, mConnectionL, 10, SpringLayout.SOUTH, mPasswordL);
		mSpringLayout.putConstraint(SpringLayout.WEST, mConnectionL, 10, SpringLayout.WEST, mAdminPanel);
		
		mSpringLayout.putConstraint(SpringLayout.NORTH, mConnectButton, 10, SpringLayout.SOUTH, mPasswordTF);
		mSpringLayout.putConstraint(SpringLayout.WEST, mConnectButton, 10, SpringLayout.EAST, mConnectionL);
		
		mAdminPanel.setVisible(true);
		//mAdminPanel.setLayout(mSpringLayout);
	}
	private static void InitCommandPanel(){
		mSpringLayout = new SpringLayout();
		mCommandPanel = new JPanel(mSpringLayout);
		mCommandPanel.setSize(400,200);
		
		mSQLCommandArea = new JTextArea(5,32);
		mExecuteButton = new JButton("Execute SQL Command");
		mClearCommandButton = new JButton("Clear Command");
		
		mCommandPanel.add(mSQLCommandArea);
		mCommandPanel.add(mExecuteButton);
		mCommandPanel.add(mClearCommandButton);
		
		mSpringLayout.putConstraint(SpringLayout.WEST, mSQLCommandArea, 5, SpringLayout.WEST, mCommandPanel);
		mSpringLayout.putConstraint(SpringLayout.NORTH, mSQLCommandArea, 5, SpringLayout.NORTH, mCommandPanel);
		mSpringLayout.putConstraint(SpringLayout.NORTH, mExecuteButton, 10, SpringLayout.SOUTH, mSQLCommandArea);
		mSpringLayout.putConstraint(SpringLayout.WEST, mExecuteButton, 5, SpringLayout.WEST, mCommandPanel);
		mSpringLayout.putConstraint(SpringLayout.NORTH, mClearCommandButton, 10, SpringLayout.SOUTH, mSQLCommandArea);
		mSpringLayout.putConstraint(SpringLayout.WEST, mClearCommandButton, 15, SpringLayout.EAST, mExecuteButton);
		
		mCommandPanel.setVisible(true);
		//mCommandPanel.setLayout(mSpringLayout);
		
	}
	
}
