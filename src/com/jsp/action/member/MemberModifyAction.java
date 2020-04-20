package com.jsp.action.member;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.action.Action;
import com.jsp.dto.MemberVO;
import com.jsp.request.MemberRegistRequest;
import com.jsp.service.MemberServiceImpl;

public class MemberModifyAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
		
		try {
			MemberServiceImpl.getInstance().modify(member);
		} catch (SQLException e) {
			e.printStackTrace();
			url="member/modify_fail";
		}
		return url;
	}

}
