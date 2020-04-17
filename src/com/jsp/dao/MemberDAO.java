package com.jsp.dao;

import java.sql.SQLException;
import java.util.List;

import com.jsp.dto.MemberVO;
import com.jsp.request.SearchCriteria;

public interface MemberDAO {

	//회원 리스트
	List<MemberVO> selectMemberList() throws SQLException;
	
	List<MemberVO> selectMemberList(SearchCriteria cri) throws SQLException;
	//전체 회원리스트 개수
	int selectMemberListCount() throws SQLException;
	
	int selectMemberListCount(SearchCriteria cri) throws SQLException;
	
	//회원정보조회
	MemberVO selectMemberById(String id) throws SQLException;
	
	//회원 추가
	void insertMember(MemberVO member) throws SQLException;
	
	//회원정보 수정
	void updateMember(MemberVO member) throws SQLException;
	
	//회원정보 삭제
	void deleteMember(String id) throws SQLException;
	
	//회원정지
	void enabledMember(String id) throws SQLException;
	
	//회원정지
	void disabledMember(String id) throws SQLException;
	
	
}
