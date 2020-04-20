package com.jsp.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.jsp.dto.BoardVO;
import com.jsp.request.SearchCriteria;

public class BoardDAOImpl implements BoardDAO {
	
	private SqlSessionFactory sessionFactory;
	public void setSqlSessionFactory(SqlSessionFactory sessionFactory) {
		this.sessionFactory=sessionFactory;
	}

	@Override
	public List<BoardVO> selectBoardCriteria(SearchCriteria cri) throws SQLException {
		SqlSession session =sessionFactory.openSession();
		List<BoardVO> boardList = null;
		try {
		boardList = session.selectList("Boad-Mapper.selectBoardCriteria",cri);
		}finally {
			session.close();
		}
		return boardList;
	}

	@Override
	public int selectBoardCriteriaTotalCount(SearchCriteria cri) throws SQLException {
		int count=0;
		SqlSession session = sessionFactory.openSession();
		count=session.selectOne("Board-Mapper.selectBoardCriteriaTotalCount",cri);
		session.close();
		return count;
	}

	@Override
	public BoardVO selectBoardByBno(int bno) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertBoard(BoardVO board) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateBoard(BoardVO board) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteBoard(int bno) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void increaseViewCnt(int bno) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public int selectBoardSeqNext() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

}
