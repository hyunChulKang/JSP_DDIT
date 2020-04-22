package com.jsp.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jsp.dao.BoardDAO;
import com.jsp.dao.BoardDAOImpl;
import com.jsp.dao.ReplyDAO;
import com.jsp.dto.BoardVO;
import com.jsp.dto.MemberVO;
import com.jsp.request.PageMaker;
import com.jsp.request.SearchCriteria;

public class BoardServiceImpl implements BoardService {
	
	
	private BoardDAO boardDAO;
	public void setBoardDAO(BoardDAO boardDAO) {
		this.boardDAO=boardDAO;
	}
	
	private ReplyDAO replyDAO;
	public void setReplyDAO(ReplyDAO replyDAO) {
		this.replyDAO = replyDAO;
	}
	
	@Override
	public Map<String, Object> getBoardList(SearchCriteria cri) throws SQLException {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		List<BoardVO> boardList = boardDAO.selectBoardCriteria(cri);
		int totalCount =boardDAO.selectBoardCriteriaTotalCount(cri);
		for(BoardVO board : boardList) {
			int replycnt=replyDAO.countReply(board.getBno());
			board.setReplycnt(replycnt);
		}
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(boardDAO.selectBoardCriteriaTotalCount(cri));
		pageMaker.setTotalCount(totalCount);
		
		dataMap.put("boardList", boardList);
		dataMap.put("pageMaker", pageMaker);
		return dataMap;
	}

	@Override
	public BoardVO getBoard(int bno) throws SQLException {
		BoardVO vo = new BoardVO();
			boardDAO.increaseViewCnt(bno);
			vo=boardDAO.selectBoardByBno(bno);
		
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
