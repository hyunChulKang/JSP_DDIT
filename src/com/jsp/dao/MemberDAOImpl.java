package com.jsp.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.jsp.dto.MemberVO;
import com.jsp.request.SearchCriteria;

public class MemberDAOImpl implements MemberDAO{
	/*SqlSessionFactory sqlSessionFactory;
	private static MemberDAOImpl memberDao;
	private MemberDAOImpl() {
		sqlSessionFactory= OracleMyBatisSqlSessionFactoryBuilder.getSqlSessionFactory();
	}
	public static MemberDAOImpl getMemberDaoImpl() {
		if(memberDao ==null) {
			memberDao= new MemberDAOImpl();
			return memberDao;
		}
		return memberDao;
	}*/

	private SqlSessionFactory sessionFactory;
	public void setSqlSessionFactory(SqlSessionFactory sessionFactory) {
		this.sessionFactory =sessionFactory;
	}
//	=OracleMyBatisSqlSessionFactoryBuilder.getSqlSessionFactory(); 

	@Override
	public List<MemberVO> selectMemberList() throws SQLException {
		SqlSession session = sessionFactory.openSession();
		List<MemberVO> memberList=null;
		try {
			 memberList = session.selectList("Member-Mapper.selectMemberList",null);
		}finally {		//에러 발생시에도 무조건 세션닫히도록 한다.
			session.close();
		}
		System.out.println(memberList.toString()+"리스트!");
		return memberList;
	}

	@Override
	public int selectMemberListCount() throws SQLException {
		int count=0;
		SqlSession session=sessionFactory.openSession();
		count=session.selectOne("Member-Mapper.selectMemberListCont",null);
		session.close();
		return count;
	}

	@Override
	public MemberVO selectMemberById(String id) throws SQLException {
		SqlSession session=sessionFactory.openSession();
		MemberVO member=session.selectOne("Member-Mapper.selectMemberById",id);
		session.close();
		return member;
	}
	/* DML이기때문에 commit이 필요!
	 * session안에 있는 오토 컴잇이 false로 고정되어있어서 true로 변경하여 commit한다   .*/
	@Override
	public void insertMember(MemberVO member) throws SQLException {
		SqlSession session =sessionFactory.openSession(true);
		session.update("Member-Mapper.insertMember",member);
		session.close();
	}

	@Override
	public void updateMember(MemberVO member) throws SQLException {
		SqlSession session= sessionFactory.openSession(true);
		System.out.println(member.toString());
		session.update("Member-Mapper.updateMember",member);
		session.close();
	}

	@Override
	public void deleteMember(String id) throws SQLException {
		SqlSession session= sessionFactory.openSession(true);
		session.update("Member-Mapper.deletMember",id);
		session.close();
	}
	@Override
	public void enabledMember(String id) throws SQLException {
		SqlSession session =sessionFactory.openSession(true);
		session.update("Member-Mapper.enabledMember",id);
		session.close();
	}
	@Override
	public void disabledMember(String id) throws SQLException {
		SqlSession session =sessionFactory.openSession(true);
		session.update("Member-Mapper.disabledMember",id);
		session.close();

		
	}

	@Override
	public List<MemberVO> selectMemberList(SearchCriteria cri) throws SQLException {
		SqlSession session = sessionFactory.openSession(true);
		
		int offset = cri.getPageStartRowNum();
		int limit = cri.getPerPageNum();
		RowBounds rowBounds = new RowBounds(offset, limit);
		List<MemberVO> memberList = null;
		
		memberList=session.selectList("Member-Mapper.selectSearchMemberList",cri,rowBounds);
		
		session.close();
		return memberList;
	}

	@Override
	public int selectMemberListCount(SearchCriteria cri) throws SQLException {
		int count=0;
		SqlSession session = sessionFactory.openSession(true);
		count=session.selectOne("Member-Mapper.selectSearchMemberListCount",cri);
		
		session.close();
		return count;
	}

}
