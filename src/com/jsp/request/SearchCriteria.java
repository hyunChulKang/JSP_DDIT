package com.jsp.request;

public class SearchCriteria {	

	private int page;			//페이지 번호
	private int perPageNum;		//한페이지당 리스트개수
	private String searchType; 	//검색구분
	private String keyword;		//검색어
	
	public SearchCriteria() {		//초기값 생성
		this.page= 1;
		this.perPageNum = 10;
		this.searchType ="";
		this.keyword ="";
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPerPageNum() {
		return perPageNum;
	}

	public void setPerPageNum(int perPageNum) {
		this.perPageNum = perPageNum;
	}

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	public int getPageStartRowNum() {
		return (this.page-1)*perPageNum;
	}

	@Override
	public String toString() {
		return "SearchCriteria [page=" + page + ", perPageNum=" + perPageNum + ", searchType=" + searchType
				+ ", keyword=" + keyword + "]";
	}
	
}
