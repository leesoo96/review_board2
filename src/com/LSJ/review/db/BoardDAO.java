package com.LSJ.review.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.LSJ.review.model.BoardCmtVO;
import com.LSJ.review.model.BoardVO;

public class BoardDAO {
//	AOP
	public static int myExecuteUpdate(String sql, SQLInterUpdate sqlInter) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = DBUtils.getConn();
			pstmt = conn.prepareStatement(sql);
			sqlInter.proc(pstmt);
			
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.getMessage();
		} finally {
			DBUtils.close(conn, pstmt);
		}
		
		return 0;
	}
	
//	페이징
	public static int pageCnt(final BoardVO param) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = " SELECT ceil(COUNT(i_board) / ?) "
					 + " as cnt FROM r_board_? ";
		
		try {
			conn = DBUtils.getConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, param.getRowCntPerPage());
			pstmt.setInt(2, param.getTyp());
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getInt("cnt"); 
//				sql문을 실행한 결과의 가장 첫번째 컬럼값을 가지고 온다
			}
		} catch (Exception e) {
			e.getMessage();
		} finally {
			DBUtils.close(conn, pstmt, rs);
		}
		
		return 0;
	}
	
//	글목록확인
	public static List<BoardVO> showListAll(final BoardVO param){
		List<BoardVO> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = " SELECT i_board, title, ctnt, r_dt, hits "
					 + " FROM r_board_? "
					 + " ORDER BY i_board DESC "
					 + " LIMIT ?, ? ";
		try {
			conn = DBUtils.getConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, param.getTyp());
			pstmt.setInt(2, param.getS_idx()); 
			pstmt.setInt(3, param.getRowCntPerPage()); 
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BoardVO vo = new BoardVO();
				
				vo.setI_board(rs.getInt("i_board"));
				vo.setTitle(rs.getString("title"));
				vo.setR_dt(rs.getString("r_dt"));
				vo.setHits(rs.getInt("hits"));

				list.add(vo);
			}
		} catch (Exception e) {
			e.getMessage();
		} finally {
			DBUtils.close(conn, pstmt, rs);
		}
		
		return list;
	}	
	
//	댓글 목록 확인
	public static List<BoardCmtVO> showCmtAll(final BoardVO param){
		List<BoardCmtVO> list = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = " SELECT i_cmt, ctnt FROM r_board_cmt_? "
					 + " WHERE i_board = ? ORDER BY i_cmt DESC ";
		
		try {
			conn = DBUtils.getConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, param.getTyp());
			pstmt.setInt(2, param.getI_board());
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				BoardCmtVO cv = new BoardCmtVO();
				cv.setI_cmt(rs.getInt("i_cmt"));
				cv.setCtnt(rs.getString("ctnt"));
				
				list.add(cv);
			}
		} catch (Exception e) {
			e.getMessage();
		} finally {
			DBUtils.close(conn, pstmt, rs);
		}
		
		return list;
	}
	
//	글 읽기
	public static BoardVO readCtnt(final BoardVO param) {
		BoardVO vo = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = " SELECT i_board, title, ctnt, r_dt, hits "
					 + " FROM r_board_? WHERE i_board = ? ";
		
		try {
			conn = DBUtils.getConn();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, param.getTyp());
			pstmt.setInt(2, param.getI_board());
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				vo = new BoardVO();
				vo.setTyp(param.getTyp());
				vo.setI_board(param.getI_board());
				
				vo.setTitle(rs.getString("title"));
				vo.setCtnt(rs.getString("ctnt"));
				vo.setR_dt(rs.getString("r_dt"));
				vo.setHits(rs.getInt("hits"));
			}
		} catch (Exception e) {
			e.getMessage();
		} finally {
			DBUtils.close(conn, pstmt, rs);
		}
		
		return vo;
	}
	
//	글쓰기
	public static int insertCtnt(final BoardVO param) {
		int result = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = " INSERT INTO r_board_? "
					 + " (title, ctnt) "
					 + " VALUES (?, ?) ";
		
		try {
			conn = DBUtils.getConn();
			pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1, param.getTyp());
			pstmt.setString(2, param.getTitle());
			pstmt.setString(3, param.getCtnt());
			
			result = pstmt.executeUpdate();
			
//			PK값을 가져와 rs에 담는다
			rs = pstmt.getGeneratedKeys();
			if(rs.next()) {
				int i_board = rs.getInt(1);
				System.out.println("바뀌기전 i_board = " + i_board);
				param.setI_board(i_board);
				System.out.println("바뀐 후 i_board = " + i_board);
			}
	
		} catch (Exception e) {
			e.getMessage();
		} finally {
			DBUtils.close(conn, pstmt, rs);
		}
		
		return result;
	}
}
