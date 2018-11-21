package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserInfo {
	private String sql;
	
	public int signUpUser(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int check = 0;
		
		try {
			sql = "INSERT INTO CLOSET_USER VALUES(?)";
			
			JDBC_Connection jdbc = new JDBC_Connection();
			conn = jdbc.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			check = pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return check;
	} 

	public boolean searchUser(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean confirm = false;
		
		try {
			JDBC_Connection jdbc = new JDBC_Connection();
			conn = jdbc.getConnection();
			
			sql = "SELECT * FROM CLOSET_USER WHERE ID = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				confirm = true;
				return confirm;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null) 
					pstmt.close();
				if(conn != null) 
					conn.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return confirm;
	}
}
