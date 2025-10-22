package com.aezen.www.vo;

public class SearchVO {
	
	public SearchVO(String type, String order) {
		this.type = type;
		this.order = order;
	}
	
	public SearchVO() {
	}


	private String type;
	private String order;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
}
