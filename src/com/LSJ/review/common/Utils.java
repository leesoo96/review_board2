package com.LSJ.review.common;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Utils {

	public static void forward(String title, String target, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		메인페이지 경로설정
		String jspPath = "/WEB-INF/jsp/temp/template.jsp";
		
//		서브페이지 경로설정
		request.setAttribute("page", String.format("/WEB-INF/jsp/%s.jsp", target));
		
		request.setAttribute("title", title);
		request.getRequestDispatcher(jspPath).forward(request, response);
	}
	
	public static void forwardErr(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		forward("에러발생", "err", request, response);
	}
	
	public static int getIntParam(HttpServletRequest request, String key) {
		return getIntParam(request, key, 0);
	}
	public static int getIntParam(HttpServletRequest request, String key, int defVal) {
		String param = request.getParameter(key);
		return parseStrToInt(param, defVal);
	}
	public static int parseStrToInt(String val) {
		return parseStrToInt(val, 0);
	}
	
	public static int parseStrToInt(String val, int defVal) {
		try {
			return Integer.parseInt(val);
		} catch(Exception e) {}
		return defVal;
	}
}
