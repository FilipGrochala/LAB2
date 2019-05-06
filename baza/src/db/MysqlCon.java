package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.scene.control.Alert;

public class MysqlCon {

	private Connection baseCon() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "");
			return con;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	public void insert(String cmd) {
		Connection con = baseCon();
		Statement stmt;
		try {
			stmt = con.createStatement();
			stmt.executeUpdate(cmd);
			con.close();
			
		} catch (SQLException e) {
		
		
			e.printStackTrace();
		}

	}
}
