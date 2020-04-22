package com.jsp.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jsp.dto.MemberVO;
import com.jsp.service.MemberServiceImpl;

//@WebServlet("/member/pwdCheck")
public class CheckPaswordServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String mempwd ="";
		
//		try {
//			MemberVO mem = null;
//			mem=MemberServiceImpl.getInstance().getMember(id);
//			if(mem!=null) {
//				mempwd=mem.getPwd();
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
		response.getWriter().write(mempwd);
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
