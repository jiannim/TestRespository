package com.spring.myapp.commons.paging;

public class SearchCriteria extends Criteria{
	
	private String condition; //검색조검
	private String keyword; //검색어
	
	public SearchCriteria() {
		super();			//이건 안보여도 extends하면 자동으로 super가된다.
		this.keyword = "";
		this.condition = "";
	}
	
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	
}
