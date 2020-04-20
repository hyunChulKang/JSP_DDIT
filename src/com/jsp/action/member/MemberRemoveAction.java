package com.jsp.action.member;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.action.Action;
import com.jsp.service.MemberServiceImpl;

public class MemberRemoveAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id= request.getParameter("id");
		String url="member/remove_success";
		try {
			MemberServiceImpl.getInstance().remove(id);
		} catch (SQLException e) {
			e.printStackTrace();
			url="error/500_error";
			request.setAttribute("exception", e);
		}
		return url;
	}

}
