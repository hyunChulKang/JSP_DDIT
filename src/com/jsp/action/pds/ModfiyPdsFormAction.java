package com.jsp.action.pds;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.action.Action;
import com.jsp.dto.PdsVO;
import com.jsp.service.PdsService;

public class ModfiyPdsFormAction implements Action {
	private PdsService pdsService;
	public void setPdsService(PdsService pdsService) {
		this.pdsService = pdsService;
	}
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url="pds/modify";
		int pno = Integer.parseInt(request.getParameter("pno"));
		PdsVO pds = new PdsVO();
				
		try {
			pds=pdsService.getPds(pno);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.setAttribute("pds", pds);
		
		return url;
	}

}