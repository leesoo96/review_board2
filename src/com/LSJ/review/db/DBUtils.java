package com.LSJ.review.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUtils {

	public static Connection getConn() throws ClassNotFoundException, SQLException {
		final String URL = "jdbc:mysql://localhost:3306/review_board?serverTimezone=UTC";
		final String USER = "root";
		final String PW = "wls120239";
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = DriverManager.getConnection(URL, USER, PW);
		
		System.out.println("DATABASE CONNECT");
		
		return conn;
	}
	
//	public static void main(String[] args) throws ClassNotFoundException, SQLException {
//		getConn();
//	}
	
	public static void close(Connection conn, PreparedStatement pstmt) {
		if(pstmt != null) {
			try { pstmt.close(); } catch (SQLException e) { e.printStackTrace(); }
		}
		
		if(conn != null) {
			try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }
		}
		
	}
	public static void close(Connection conn, PreparedStatement pstmt, ResultSet rs) {
		close(conn, pstmt);
		
		if(rs != null) {
			try { rs.close(); } catch (SQLException e) { e.printStackTrace(); }
		}		
		
	}
}
