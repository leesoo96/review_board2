package com.LSJ.review;

import java.util.List;

import com.LSJ.review.db.BoardDAO;
import com.LSJ.review.model.BoardVO;

public class BoardService {

//	전체 글 목록 확인
	public static List<BoardVO> readList(BoardVO param, int page) {
		int s_idx = (page - 1) * param.getRowCntPerPage();
		param.setS_idx(s_idx);
		
		return BoardDAO.showListAll(param);
	}
}
