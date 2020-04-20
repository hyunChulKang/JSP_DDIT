package com.jsp.action.commons;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.action.Action;

public class LoginFormAction implements Action {
	//화면만 주면 된다...... 기능하지말고 화면만..
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String url="commons/loginForm";
		
		return url;
	}

}
