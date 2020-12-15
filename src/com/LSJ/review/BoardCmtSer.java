package com.LSJ.review;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.LSJ.review.common.Utils;
import com.LSJ.review.model.BoardCmtVO;

@WebServlet("/cmt")
public class BoardCmtSer extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		댓글 입력
		int typ = Utils.getIntParam(request, "typ");
		int i_board = Utils.getIntParam(request, "i_board");
		String ctnt = request.getParameter("cmt_ctnt");
		
		BoardCmtVO param = new BoardCmtVO();
		param.setTyp(typ);
		param.setI_board(i_board);
		param.setCtnt(ctnt);
		
		int result = BoardService.insertCmt(param);
		
		response.sendRedirect("/Read?typ=" + typ + "&i_board=" + i_board);
	}

}
