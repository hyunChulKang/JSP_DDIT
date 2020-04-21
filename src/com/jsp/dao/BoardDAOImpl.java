package com.jsp.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.jsp.dto.BoardVO;
import com.jsp.dto.MemberVO;
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
		
		int offset = cri.getPageStartRowNum();
		int limit = cri.getPerPageNum();
		RowBounds rowBounds = new RowBounds(offset, limit);
		
		try {
		boardList = session.selectList("Board-Mapper.selectBoardCriteria",cri,rowBounds);
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
		BoardVO vo = new BoardVO();
			SqlSession session = sessionFactory.openSession();
			vo=session.selectOne("Board-Mapper.selectBoardByBno", bno);
			session.close();
		return vo;
	}

	@Override
	public void insertBoard(BoardVO board) throws SQLException {
		SqlSession session = sessionFactory.openSession(true);
		System.out.println(board.toString()+"13242wrghbdfgutjdy");
		int count=0;
		count=session.insert("Board-Mapper.insertBoard",board);
		System.out.println("로그인 확인!!!!! "+count);
		session.close();
	}

	@Override
	public void updateBoard(BoardVO board) throws SQLException {
		SqlSession session = sessionFactory.openSession(true);
		session.update("Board-Mapper.updateBoard",board);
		session.close();
	}

	@Override
	public void deleteBoard(int bno) throws SQLException {
		SqlSession session =sessionFactory.openSession(true);
		session.delete("Board-Mapper.deleteBoard",bno);
		session.close();

	}

	@Override
	public void increaseViewCnt(int bno) throws SQLException {
		SqlSession session = sessionFactory.openSession(true);
		session.update("Board-Mapper.increaseViewCnt",bno);
		session.close();
	}

	@Override
	public int selectBoardSeqNext() throws SQLException {
		int cnt=0;
		SqlSession session = sessionFactory.openSession();
		cnt=session.selectOne("Board-Mapper.selectBoardSeqNext");
		return cnt;
	}


}
