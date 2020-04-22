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
import com.jsp.request.MemberRegistRequest;
import com.jsp.service.MemberServiceImpl;

//@WebServlet("/member/modify")
public class ModifyServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url="member/modify";
		String id =request.getParameter("id");
//		try {
//			MemberVO member= MemberServiceImpl.getInstance().getMember(id);
//			
//			request.setAttribute("member", member);
//		} catch (SQLException e) {
//			e.printStackTrace();
//			url="error/500_error";
//			request.setAttribute("exception", e);
//		}
		ViewResolver.view(request, response, url);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url="member/modify_success";
		
		String id =request.getParameter("id");
		String pwd=request.getParameter("pwd");
		String email=request.getParameter("email");
		String picture=request.getParameter("picture");
		String[] phones=request.getParameterValues("phone");
		String[] phone=phones[0].split("-");
		String authority = request.getParameter("authority");
		String name=request.getParameter("name");
		MemberRegistRequest memberReq = new MemberRegistRequest(id,pwd,authority,email,picture,phone,name);
		MemberVO member = memberReq.toMemberVO();
		
//		try {
//			MemberServiceImpl.getInstance().modify(member);
//		} catch (SQLException e) {
//			e.printStackTrace();
//			url="member/modify_fail";
//		}
		
		ViewResolver.view(request, response, url);
		
	}

}
