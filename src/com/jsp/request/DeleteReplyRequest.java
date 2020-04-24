package com.jsp.request;

import java.util.Date;

import com.jsp.dto.ReplyVO;

public class DeleteReplyRequest {
	//화면에서 넘어오는 데이터를 변수로 선언한다.
	private int bno;
	private int rno;
	private int Page;
	
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	public int getRno() {
		return rno;
	}
	public void setRno(int rno) {
		this.rno = rno;
	}
	public int getPage() {
		return Page;
	}
	public void setPage(int page) {
		Page = page;
	}
	
	
}
