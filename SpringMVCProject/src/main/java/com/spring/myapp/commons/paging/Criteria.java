package com.spring.myapp.commons.paging;

public class Criteria {
	
	//사용자가 요청한 페이지 번호
	private int page;
	//한 페이지당 들어갈 게시물 수
	private int countPerPage;
	
	
	public Criteria() {
		this.page = 1;			//사용자가 별다른 요청을 안하면 처음페이지는 1페이지이다.
		this.countPerPage = 10; //사용자가 별다른 요청을 안하면 페이지별로 게시물은 10개이다.
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		if(page <= 0) {
			this.page = 1;
			return;
		}
		this.page = page;
	}

	
	public int getCountPerPage() {
		return countPerPage;
	}

	public void setCountPerPage(int countPerPage) {
		if(countPerPage <= 0 || countPerPage > 100) {
			this.countPerPage = 10;
			return;
		}
		this.countPerPage =  countPerPage;
	}
	
	//SQL LIMIT절의 조회 시작 인덱스를 계산하는 메서드
	public int getPageStart() {
		return (this.page - 1) * countPerPage;
	}

	@Override
	public String toString() {
		return "Criteria [page=" + page + ", countPerPage=" + countPerPage + "]";
	}
	
	
}
