package com.spring.myapp;

public class Box {
	private String aaa;
	private String bbb;
	public String getAaa() {
		return aaa;
	}
	public void setAaa(String aaa) {
		this.aaa = aaa;
	}
	public String getBbb() {
		return bbb;
	}
	public void setBbb(String bbb) {
		this.bbb = bbb;
	}
	@Override
	public String toString() {
		return "Box [aaa=" + aaa + ", bbb=" + bbb + "]";
	}
	
	
	
}
