package com.jsp.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSessionFactory;

import com.jsp.dao.AttachDAO;
import com.jsp.dao.PdsDAO;
import com.jsp.dto.AttachVO;
import com.jsp.dto.PdsVO;
import com.jsp.request.PageMaker;
import com.jsp.request.SearchCriteria;

public class PdsServiceImpl implements PdsService {

	private PdsDAO pdsDAO;
	public void setPdsDAO(PdsDAO pdsDAO) {
		this.pdsDAO = pdsDAO;
	}
	
	private AttachDAO attachDAO;
	public void setAttachDAO(AttachDAO attachDAO) {
		this.attachDAO=attachDAO;
	}
	
	@Override
	public Map<String, Object> getList(SearchCriteria cri) throws SQLException {
		
		List<PdsVO> pdsList = pdsDAO.selectPdsCriteria(cri);
		System.out.println("pdsList getAttachList : " + pdsList.get(0).getAttachList());
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(pdsDAO.selectPdsCriteriaTotalCount(cri));
		
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("pdsList", pdsList);
		dataMap.put("pageMaker", pageMaker);
		
		return dataMap;
	}
	@Override
	public PdsVO getPds(int pno) throws SQLException {
		PdsVO pds = new PdsVO();
		pds= pdsDAO.selectPdsByPno(pno);
		List<AttachVO> attachList=attachDAO.selectAttachesByPno(pno);
		System.out.println("attachList :"+attachList.toString());
		pds.setAttachList(attachList);
		return pds;
	}
	@Override
	public void regist(PdsVO pds) throws SQLException {
		int pno = pdsDAO.getSeqNextValue();
		pds.setPno(pno);
		pdsDAO.insertPds(pds);
		System.out.println("pds.toString() :" + pds.toString());
		for(AttachVO attach:pds.getAttachList()) {
			attach.setPno(pno);
			attach.setAttacher(pds.getWriter());
			attachDAO.insertAttach(attach);
		}
	}
	@Override
	public void modify(PdsVO pds) throws SQLException {
		pdsDAO.updatePds(pds);		
		 //attachDAO.deleteAllAttach(pds.getPno());
		for(AttachVO attach:pds.getAttachList()) {
			attach.setPno(pds.getPno());
			attach.setAttacher(pds.getWriter());
			attachDAO.insertAttach(attach);
		}
	}
	@Override
	public void remove(int pno) throws SQLException {
		pdsDAO.deletePds(pno);		
	}
	@Override
	public PdsVO read(int pno) throws SQLException {
		PdsVO pds = pdsDAO.selectPdsByPno(pno);
		List<AttachVO> attachList=attachDAO.selectAttachesByPno(pno);
		pds.setAttachList(attachList);
		pdsDAO.increaseViewCnt(pno);
		
		
		return pds;
	}
	
	
}
