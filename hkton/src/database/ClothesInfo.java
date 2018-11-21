package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.Clothes;

public class ClothesInfo {
	private String sql;
	
	public ArrayList<Clothes> getClothes(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<Clothes> clothesList = new ArrayList<Clothes>();
		
		try {
			JDBC_Connection jdbc = new JDBC_Connection();
			conn = jdbc.getConnection();
			
			sql = "SELECT * FROM CLOTHES WHERE ID = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Clothes clothes = new Clothes();
				clothes.setId(rs.getString(1));
				clothes.setType(rs.getString(2));
				clothes.setRed(rs.getInt(3));
				clothes.setGreen(rs.getInt(4));
				clothes.setBlue(rs.getInt(5));
				clothes.setAlpha(rs.getInt(6));
				clothesList.add(clothes);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null)
					pstmt.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return clothesList;
	}
	
	public Clothes addClothes(Clothes clothes) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			sql = "INSERT INTO CLOTHES VALUES(?,?,?,?,?,?)";
			
			JDBC_Connection jdbc = new JDBC_Connection();
			conn = jdbc.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, clothes.getId());
			pstmt.setString(2, clothes.getType());
			pstmt.setInt(3, clothes.getRed());
			pstmt.setInt(4, clothes.getGreen());
			pstmt.setInt(5, clothes.getBlue());
			pstmt.setInt(6, clothes.getAlpha());
			pstmt.executeQuery();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null) {pstmt.close();}
			}catch(Exception e) {e.printStackTrace();}
		}
		return clothes;
	}
	
	// ø  ªË¡¶
	public void removeClothes(Clothes clothes) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			sql = "DELETE FROM CLOTHES WHERE ID=? AND TYPE=? AND RED=? AND GREEN=? AND BLUE=? AND ALPHA=?";
			
			JDBC_Connection jdbc = new JDBC_Connection();
			conn = jdbc.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, clothes.getId());
			pstmt.setString(2, clothes.getType());
			pstmt.setInt(3, clothes.getRed());
			pstmt.setInt(4, clothes.getGreen());
			pstmt.setInt(5, clothes.getBlue());
			pstmt.setInt(6, clothes.getAlpha());
			
			pstmt.executeQuery();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null) {pstmt.close();}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}
