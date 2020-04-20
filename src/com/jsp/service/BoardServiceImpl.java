package com.jsp.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jsp.dao.BoardDAO;
import com.jsp.dao.BoardDAOImpl;
import com.jsp.dto.BoardVO;
import com.jsp.dto.MemberVO;
import com.jsp.request.PageMaker;
import com.jsp.request.SearchCriteria;

public class BoardServiceImpl implements BoardService {
	
	private static BoardServiceImpl instance = new BoardServiceImpl();
	private BoardServiceImpl() {}
	public static BoardServiceImpl getInstance() {
		return instance;
	}
	
	private BoardDAO boardDAO;
	public void setBoardDAO(BoardDAO boardDAO) {
		this.boardDAO=boardDAO;
	}
	
	@Override
	public Map<String, Object> getBoardList(SearchCriteria cri) throws SQLException {
		List<BoardVO> memberList = boardDAO.selectBoardCriteria(cri);
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		System.out.println(cri.toString());
		pageMaker.setTotalCount(boardDAO.selectBoardCriteriaTotalCount(cri));
		System.out.println( boardDAO.selectBoardCriteriaTotalCount(cri)+"<-----selectMemberListCount 갯수");
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("memberList", memberList);
		dataMap.put("pageMaker", pageMaker);
		return dataMap;
	}

	@Override
	public BoardVO getBoard(int bno) throws SQLException {
		return null;
	}

	@Override
	public BoardVO getBoardForModify(int bno) throws SQLException {
		
		return null;
	}

	@Override
	public void write(BoardVO board) throws SQLException {
		
	}

	@Override
	public void modify(BoardVO board) throws SQLException {
		
	}

	@Override
	public void remove(int bno) throws SQLException {
		
	}
	

}
