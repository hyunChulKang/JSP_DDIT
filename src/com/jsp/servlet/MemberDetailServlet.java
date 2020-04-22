package com.jsp.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.dispatcher.ViewResolver;
import com.jsp.dto.MemberVO;
import com.jsp.service.MemberServiceImpl;

//@WebServlet("/member/detail")
public class MemberDetailServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url="member/detail";
		String id=request.getParameter("id");
		MemberVO member=null;
//		try {
//			member=MemberServiceImpl.getInstance().getMember(id);
//			request.setAttribute("member", member);
//		} catch (SQLException e) {
//			e.printStackTrace();
//			url="error/500_error";
//			request.setAttribute("exception", e);
//		}
		ViewResolver.view(request, response, url);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
