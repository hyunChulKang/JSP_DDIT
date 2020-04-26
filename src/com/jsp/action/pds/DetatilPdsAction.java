package com.jsp.action.pds;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.action.Action;
import com.jsp.dto.PdsVO;
import com.jsp.service.PdsService;

public class DetatilPdsAction implements Action {

	private PdsService pdsService;
	public void setPdsService(PdsService pdsService) {
		this.pdsService = pdsService;
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url="pds/detail";
		String flag= request.getParameter("flag");
		int pno =Integer.parseInt(request.getParameter("pno"));
		PdsVO pds = new PdsVO();
		try {
			if(flag.equals("inTheModify")) {
				pds=pdsService.getPds(pno);
			}else {
				pds=pdsService.read(pno);
			}
			System.out.println("pds :" + pds.toString());
			request.setAttribute("pds", pds);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return url;
	}

}
