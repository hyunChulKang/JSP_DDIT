package com.jsp.action.member;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jsp.action.Action;
import com.jsp.dto.MemberVO;
import com.jsp.service.MemberService;
import com.jsp.service.MemberServiceImpl;

public class MemberRemoveAction implements Action{
	private MemberService memberService;
	public void setMemberService(MemberService memberService) {
		this.memberService=memberService;
	}
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id= request.getParameter("id");
		String url="member/remove_success";
		HttpSession session = request.getSession();
		
		MemberVO member = (MemberVO) session.getAttribute("loginUser");
		if(member.getId().equals(id)) {
			url="member/remove_denied";
		}else {
			try {
				memberService.remove(id);
			} catch (SQLException e) {
				e.printStackTrace();
				url="error/500_error";
				request.setAttribute("exception", e);
			}
			
		}
		return url;
	}

}
