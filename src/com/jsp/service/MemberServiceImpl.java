package com.jsp.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jsp.dao.MemberDAO;
import com.jsp.dao.MemberDAOImpl;
import com.jsp.dto.MemberVO;
import com.jsp.exception.InvalidPasswordException;
import com.jsp.exception.NotFoundIDExcepiton;
import com.jsp.request.PageMaker;
import com.jsp.request.SearchCriteria;

public class MemberServiceImpl implements MemberService{
	
	
	private MemberDAO memberDAO;/*=MemberDAOImpl.getMemberDaoImpl();*/
	public void setMemberDAO(MemberDAO memberDAO) {
		this.memberDAO=memberDAO;
	}
	@Override
	public void login(String id, String pwd) throws SQLException, NotFoundIDExcepiton, InvalidPasswordException {
	if(memberDAO == null) {
		System.out.println("memberDAO가 null이래~~~~~~~" );
	}
	MemberVO member = memberDAO.selectMemberById(id);
	
	if(member ==null) throw new NotFoundIDExcepiton();
	if(!pwd.equals(member.getPwd())) throw new InvalidPasswordException();
	}

	@Override
	public List<MemberVO> getMemberList() throws SQLException {
		return memberDAO.selectMemberList();
	}

	@Override
	public MemberVO getMember(String id) throws SQLException {
		return memberDAO.selectMemberById(id);
	}

	@Override
	public void regist(MemberVO member) throws SQLException {
		
		memberDAO.insertMember(member);
	}

	@Override
	public void modify(MemberVO member) throws SQLException {
		memberDAO.updateMember(member);
	}

	@Override
	public void remove(String id) throws SQLException {
		memberDAO.deleteMember(id);
	}
	@Override
	public void enabled(String id) throws SQLException {
		memberDAO.enabledMember(id);
	}
	@Override
	public void disabled(String id) throws SQLException {
		memberDAO.disabledMember(id);
		
	}
	@Override
	public Map<String, Object> getMemberList(SearchCriteria cri) throws SQLException {
		//기존에 전달하는 리스트 
		List<MemberVO> memberList = memberDAO.selectMemberList(cri);
		
		//Totalcount를 얻기 
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		System.out.println(cri.toString());
		pageMaker.setTotalCount(memberDAO.selectMemberListCount(cri));
		System.out.println( memberDAO.selectMemberListCount(cri)+"<-----selectMemberListCount 갯수");
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("memberList", memberList);
		dataMap.put("pageMaker", pageMaker);
		
		return dataMap;
	}

}
