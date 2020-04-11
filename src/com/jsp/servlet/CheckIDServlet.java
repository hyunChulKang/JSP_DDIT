package com.jsp.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.dto.MemberVO;
import com.jsp.service.MemberServiceImpl;

/**
 * Servlet implementation class CheckIDServlet
 */
@WebServlet("/member/idCheck")
public class CheckIDServlet extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	String id = request.getParameter("id");
	String userID= "";
		try {
			MemberVO member=null;
			member=MemberServiceImpl.getInstance().getMember(id);
			if(member!=null) userID=member.getId();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		response.getWriter().print(userID);

	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
