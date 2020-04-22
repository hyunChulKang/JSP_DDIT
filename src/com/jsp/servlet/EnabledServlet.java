package com.jsp.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jsp.dispatcher.ViewResolver;
import com.jsp.dto.MemberVO;
import com.jsp.service.MemberServiceImpl;

//@WebServlet("/member/enabled")
public class EnabledServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url="member/enabled_success";
		String id = request.getParameter("id");
		HttpSession session = request.getSession();
		MemberVO member= (MemberVO) session.getAttribute("loginUser");
		if(member.getId().equals(id)) {
			url="member/disabled_denied";
		}else {
//			try {
//				MemberServiceImpl.getInstance().enabled(id);
//			} catch (SQLException e) {
//				url="error/500_error";
//				request.setAttribute("exception", e);
//			}
		}
		ViewResolver.view(request, response, url);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
