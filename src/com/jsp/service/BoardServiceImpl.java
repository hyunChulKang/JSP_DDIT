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
		List<BoardVO> boardList = boardDAO.selectBoardCriteria(cri);
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		System.out.println(cri.toString());
		pageMaker.setTotalCount(boardDAO.selectBoardCriteriaTotalCount(cri));
		System.out.println( boardDAO.selectBoardCriteriaTotalCount(cri)+"<-----selectMemberListCount 갯수");
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("boardList", boardList);
		dataMap.put("pageMaker", pageMaker);
		return dataMap;
	}

	@Override
	public BoardVO getBoard(int bno) throws SQLException {
		BoardVO vo = new BoardVO();
		vo=boardDAO.selectBoardByBno(bno);
			boardDAO.increaseViewCnt(bno);
		
		return vo;
	}

	@Override
	public BoardVO getBoardForModify(int bno) throws SQLException {
		BoardVO vo = new BoardVO();
		vo=boardDAO.selectBoardByBno(bno);
		
		return vo;
	}

	@Override
	public void write(BoardVO board) throws SQLException {
		int cnt =0;
		cnt=boardDAO.selectBoardSeqNext();
		System.out.println("cnt( 보드번호)" + cnt);
		board.setBno(cnt);
		boardDAO.insertBoard(board);
	}

	@Override
	public void modify(BoardVO board) throws SQLException {
		boardDAO.updateBoard(board);
	}

	@Override
	public void remove(int bno) throws SQLException {
		boardDAO.deleteBoard(bno);
	}
	

}
