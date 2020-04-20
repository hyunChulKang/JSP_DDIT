package com.jsp.request;

public class PageMaker {

	private int totalCount;		//전체 행의 개수
	private int startPage=1;	//시작 페이지 번호
	private int endPage=1;		//마지막 페이지 번호
	private int realEndPage;	//끝 페이지 번호
	private boolean prev;		//이전페이지 버튼 유무
	private boolean	next;		//다음 페이지 버튼 유무
	
	private int displayPageNum=10;		//한 페이지에 보여줄 페이지번호 개수
	private SearchCriteria cri;				//현재  페이지 정보
	
	//starPage, endPage, prev , next 설정. by totalCount
	
	private void calcData() {
		//(페이지 번호/한페이지에 보여줄 페이번호 개수 (1/10) ) *한페이지에 보여줄 페이지번호 개수 (10))= 마지막 페이지 번호(10)
		endPage = (int)(Math.ceil(cri.getPage()/(double)displayPageNum) *displayPageNum);
		//마지막 페이지 번호 - 한페이지보여줄 페이지번호 갯수 (10-10)+1  = 1
		startPage=(endPage - displayPageNum) + 1;	
		//총리스트 개수/보여줄 행의 갯수 (13/10) 올림 -> 2 2가 realEndPage수
		realEndPage = (int)(Math.ceil(totalCount/(double)cri.getPerPageNum()));
		//startPage가 1일때 
		if(startPage<0) {
			startPage=1;
		}
		if(endPage>realEndPage) {
			endPage=realEndPage;
		}
		
		prev = startPage == 1 ? false : true;
		next = endPage * cri.getPerPageNum() >= totalCount ? false : true;
	}
	
	public String makeQuery(int page) {
		String query="?page="+page+"?perPageNum="+cri.getPerPageNum()
					+"?sertchType="+cri.getSearchType()
					+"keyword="+cri.getKeyword();
		return query;
	}
	

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		calcData();
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public int getRealEndPage() {
		return realEndPage;
	}

	public void setRealEndPage(int realEndPage) {
		this.realEndPage = realEndPage;
	}

	public boolean isPrev() {
		return prev;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public int getDisplayPageNum() {
		return displayPageNum;
	}

	public void setDisplayPageNum(int displayPageNum) {
		this.displayPageNum = displayPageNum;
	}

	public SearchCriteria getCri() {
		return cri;
	}

	public void setCri(SearchCriteria cri) {
		this.cri = cri;
	}
	
	
}
