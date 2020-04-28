package com.jsp.action.pds;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.action.Action;
import com.jsp.dto.AttachVO;
import com.jsp.dto.PdsVO;
import com.jsp.request.PageMaker;
import com.jsp.service.PdsService;
import com.jsp.utils.CreatePageMaker;
import com.jsp.utils.MakeFileName;

public class DetailPdsAction implements Action {

	private PdsService pdsService;
	public void setPdsService(PdsService pdsService) {
		this.pdsService = pdsService;
	}

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String url="pds/detail";
		String flag= request.getParameter("flag");
		System.out.println(flag);
		int pno =Integer.parseInt(request.getParameter("pno"));
		int page =Integer.parseInt(request.getParameter("page"));
		int perPageNum =Integer.parseInt(request.getParameter("perPageNum"));
		String searchType= request.getParameter("searchType");
		String keyword = request.getParameter("keyword");
				System.out.println(pno);
		PdsVO pds = null;
		try {
			if(flag!=null && flag.equals("inTheModify")) {
				pds=pdsService.getPds(pno);
			}else {
				pds=pdsService.read(pno);
				//OS형식을 ---> url형식으로 변경해서 클라이언트에 전달 하기 위한 작업
				List<AttachVO> renamedAttachList = MakeFileName.parseFileFromAttaches(pds.getAttachList(), "\\$\\$");
				pds.setAttachList(renamedAttachList);
			}
			System.out.println("pds :" + pds.toString());
			PageMaker pageMaker =CreatePageMaker.make(request);
			request.setAttribute("pageMaker", pageMaker);
			request.setAttribute("pds", pds);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return url;
	}

}
