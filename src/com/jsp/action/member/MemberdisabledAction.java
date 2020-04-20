package com.jsp.action.member;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jsp.action.Action;
import com.jsp.dto.MemberVO;
import com.jsp.service.MemberServiceImpl;

public class MemberdisabledAction implements Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url="member/disabled";
		String id= request.getParameter("id");
		HttpSession session = request.getSession();
		MemberVO member= (MemberVO) session.getAttribute("loginUser");
		if(member.getId().equals(id)) {
			url="member/disabled_denied";
		}else {
			try {
				MemberServiceImpl.getInstance().disabled(id);
			} catch (SQLException e) {
				url="member/disabled_fail";
				e.printStackTrace();
			}
		}
		return url;
	}

}
