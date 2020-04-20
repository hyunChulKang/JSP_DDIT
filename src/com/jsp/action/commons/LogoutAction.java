package com.jsp.action.commons;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jsp.action.Action;

public class LogoutAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
	String url ="redirect:/commons/loginForm.do";
	HttpSession session = request.getSession();
	session.invalidate();
		return url;
	}

}
