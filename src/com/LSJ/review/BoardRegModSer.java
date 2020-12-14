package com.LSJ.review;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.LSJ.review.common.Utils;
import com.LSJ.review.model.BoardVO;

@WebServlet("/regMod")
public class BoardRegModSer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int typ = Utils.getIntParam(request, "typ");
		int i_board = Utils.getIntParam(request, "i_board");
		if(typ == 0) {
			Utils.forwardErr(request, response);
			return;
		}
		
		String title = "글쓰기";
		if(i_board > 0) {
			title = "글수정";
			BoardVO param = new BoardVO();
			param.setTyp(typ);
			param.setI_board(i_board);                 // 글읽기
			request.setAttribute("data", BoardService.readCtnt(param));
		}
		request.setAttribute("typ", typ);
		Utils.forward(title, "regMod", request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int typ = Utils.getIntParam(request, "typ");
		if(typ == 0) {
			request.setAttribute("err", "에러가 발생하였습니다!");
			doGet(request, response);
			return;
		}
		
		int i_board = Utils.getIntParam(request, "i_board");
		String title = request.getParameter("title");
		String ctnt = request.getParameter("ctnt");
		
		BoardVO param = new BoardVO();
		param.setTyp(typ);
		param.setI_board(i_board);
		param.setTitle(title);
		param.setCtnt(ctnt);
		
		int result = BoardService.regMod(param);
		
		if(result == 0) {
			request.setAttribute("err", "에러가 발생하였습니다.");
			doGet(request, response);
			return;
		}
		
		response.sendRedirect("/Read?typ=" + typ + "&i_board=" + param.getI_board());
	}

}
