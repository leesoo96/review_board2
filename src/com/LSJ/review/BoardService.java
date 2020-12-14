package com.LSJ.review;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import com.LSJ.review.db.BoardDAO;
import com.LSJ.review.db.SQLInterUpdate;
import com.LSJ.review.model.BoardVO;

public class BoardService {

//	전체 글 목록 확인
	public static List<BoardVO> readList(BoardVO param, int page) {
		int s_idx = (page - 1) * param.getRowCntPerPage();
		param.setS_idx(s_idx);
		
		return BoardDAO.showListAll(param);
	}
	
//	페이징
	public static int pageCount(BoardVO param) {
		return BoardDAO.pageCnt(param);
	}
	
//	글 읽기
	public static BoardVO readCtnt(BoardVO param) {
		return BoardDAO.readCtnt(param);
	}
	
//	글 읽기 + 조회수 
	public static BoardVO read(BoardVO param, HttpServletRequest request) {
		String ip = request.getRemoteAddr();
		System.out.println("접속한 아이피 = " + ip);
		
		String key = String.format("b_%d_%d", param.getTyp(), param.getI_board());
		
		ServletContext application = request.getServletContext();
		String savedIp = (String) application.getAttribute(key);
		
		if(!ip.equals(savedIp)) { // 조회수 증가 처리
			application.setAttribute(key, ip);
			
			String sql = " UPDATE r_board_? "
						 + " SET hits = hits + 1 "
						 + " WHERE i_board = ? ";
			
			BoardDAO.myExecuteUpdate(sql, new SQLInterUpdate() {
				
				@Override
				public void proc(PreparedStatement pstmt) throws SQLException {
					pstmt.setInt(1, param.getTyp());
					pstmt.setInt(2, param.getI_board());
				}
			});
		}
		
		return readCtnt(param); // 글읽기 기능 실행
	}
	
//	글쓰기 + 글수정
	public static int regMod(BoardVO param) {
		if(param.getI_board() > 0) {
			String sql = " UPDATE r_board_? "
						 + " SET title = ?, ctnt = ? "
						 + " WHERE i_board = ? ";
			
			return BoardDAO.myExecuteUpdate(sql, new SQLInterUpdate() {
				
				@Override
				public void proc(PreparedStatement pstmt) throws SQLException {
					pstmt.setInt(1, param.getTyp());
					pstmt.setString(2, param.getTitle());
					pstmt.setString(3, param.getCtnt());
					pstmt.setInt(4, param.getI_board());
				}
			});
		}
		return BoardDAO.insertCtnt(param);
	}
}
