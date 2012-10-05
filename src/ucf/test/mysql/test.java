package ucf.test.mysql;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.MySQLConnection;
import com.mysql.jdbc.PreparedStatement;

public class test {
	private static Connection con = null;
	private final static String URL = "jdbc:mysql://localhost:3306/project3";
	private final static String USER = "root";
	private final static String PASS = "";
	public static void main(String[] args){
		try {
			con = (MySQLConnection) DriverManager.getConnection(URL, USER, PASS);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM bikes");
			while(rs.next())
				System.out.println(String.valueOf(rs.getInt("cost"))+rs.getString("bikename")+rs.getString("country_of_origin") );
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
