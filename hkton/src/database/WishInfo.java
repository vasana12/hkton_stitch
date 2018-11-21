package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Clothes;

public class WishInfo {
	private String sql;
	
	// 추천된 색과 일정 범위 이상으로 차이가 생길 경우, 그 색을 저장해둔다.
	public void saveRecommendedColor(Clothes clothes) {
		Connection conn = null;
		PreparedStatement pstmt1 = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;

		JDBC_Connection jdbc = new JDBC_Connection();
		conn = jdbc.getConnection();

		try {
			sql = "SELECT * FROM WISH WHERE ID=? AND TYPE=? AND RED=? AND GREEN=? AND BLUE=? AND ALPHA=?";
				
			pstmt1 = conn.prepareStatement(sql);
			pstmt1.setString(1, clothes.getId());
			pstmt1.setString(2, clothes.getType());
			pstmt1.setInt(3, clothes.getRed());
			pstmt1.setInt(4, clothes.getGreen());
			pstmt1.setInt(5, clothes.getBlue());
			pstmt1.setInt(6, clothes.getAlpha());
				
			rs = pstmt1.executeQuery();
				
			if (rs.next()) {
				sql = "UPDATE WISH SET COUNT=? WHERE ID=? AND TYPE=? AND RED=? AND GREEN=? AND BLUE=? AND ALPHA=?";
					
				pstmt2 = conn.prepareStatement(sql);
				pstmt2.setInt(1, rs.getInt("count") + 1);
				pstmt2.setString(2, clothes.getId());
				pstmt2.setString(3, clothes.getType());
				pstmt2.setInt(4, clothes.getRed());
				pstmt2.setInt(5, clothes.getGreen());
				pstmt2.setInt(6, clothes.getBlue());
				pstmt2.setInt(7, clothes.getAlpha());
				
			} else {
				sql = "INSERT INTO WISH VALUES(?,?,?,?,?,?,?)";
						
				pstmt2 = conn.prepareStatement(sql);
				pstmt2.setString(1, clothes.getId());
				pstmt2.setString(2, clothes.getType());
				pstmt2.setInt(3, clothes.getRed());
				pstmt2.setInt(4, clothes.getGreen());
				pstmt2.setInt(5, clothes.getBlue());
				pstmt2.setInt(6, clothes.getAlpha());
				pstmt2.setInt(7, 1);
			}
				
			pstmt2.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt1 != null) {pstmt1.close();}
				if(pstmt2 != null) {pstmt2.close();}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	// 부족했던 경우가 가장 많았던 색.
	public Clothes mostRecommendedColor(String id) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Clothes clothes = null;
			
		try {
			sql = "SELECT * FROM WISH WHERE ID = ? AND COUNT = (SELECT MAX(COUNT) FROM WISH WHERE ID = ?)";
			JDBC_Connection jdbc = new JDBC_Connection();
			conn = jdbc.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				clothes = new Clothes();
				clothes.setId(rs.getString("ID"));
				clothes.setType(rs.getString("TYPE"));
				clothes.setRed(rs.getInt("RED"));
				clothes.setGreen(rs.getInt("GREEN"));
				clothes.setBlue(rs.getInt("BLUE"));
				clothes.setAlpha(rs.getInt("ALPHA"));
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt != null) {pstmt.close();}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return clothes;
	}
}
