package com.jsp.action.member;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.action.Action;
import com.jsp.dto.MemberVO;
import com.jsp.service.MemberService;
import com.jsp.service.MemberServiceImpl;

public class MemberDetailFormAction implements Action {

	private MemberService memberService= MemberServiceImpl.getInstance();
	public void setMemberService(MemberService memberService) {
		this.memberService=memberService;
	}
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url="member/detail";
		String id=request.getParameter("id");
		MemberVO member=null;
		try {
			member=memberService.getMember(id);
			request.setAttribute("member", member);
		} catch (SQLException e) {
			e.printStackTrace();
			url="error/500_error";
			request.setAttribute("exception", e);
		}
		return url;
	}

}
