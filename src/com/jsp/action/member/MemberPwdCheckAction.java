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

public class MemberPwdCheckAction implements Action {
	private MemberService memberService;
	public void setMemberService(MemberService memberService) {
		this.memberService=memberService;
	}
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		String mempwd ="";
		
		try {
			MemberVO mem = null;
			mem=memberService.getMember(id);
			if(mem!=null) {
				mempwd=mem.getPwd();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		response.getWriter().write(mempwd);
		
		return null;
	}

}
