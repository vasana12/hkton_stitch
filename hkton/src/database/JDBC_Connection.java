package database;

import java.sql.Connection;
import java.sql.DriverManager;

public class JDBC_Connection {
	private Connection conn;
	
	public Connection getConnection() {
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "hr", "123456");
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return conn;
	}	
}
