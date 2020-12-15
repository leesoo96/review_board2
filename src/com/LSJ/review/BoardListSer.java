package com.LSJ.review;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.LSJ.review.common.Utils;
import com.LSJ.review.model.BoardVO;

@WebServlet("/List")
public class BoardListSer extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int typ = Utils.getIntParam(request, "typ", 1);
		int page = Utils.getIntParam(request, "page", 1);
		System.out.println("typ = " + typ);
		
		BoardVO param = new BoardVO();
		param.setTyp(typ);
		param.setRowCntPerPage(5); // 목록 당 10개씩 보이도록 설정
		
		request.setAttribute("pageCnt", BoardService.pageCount(param));
		
		request.setAttribute("typ", typ);
		request.setAttribute("lists", BoardService.readList(param, page));
	
		Utils.forward("전체목록확인", "List", request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
