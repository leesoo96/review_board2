package com.LSJ.review.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.LSJ.review.model.BoardVO;

public class BoardDAO {

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
	
}
