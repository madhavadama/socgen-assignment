package com.socgen.discounts.apparel.model;

public class Category {
	
	private String name;
	private String parent;
	private long discount;
	
	public Category(String name, String parent, long discount) {
		this.name = name;
		this.parent = parent;
		this.discount = discount;
	}
	public long getDiscount() {
		return discount;
	}
	public void setDiscount(long discount) {
		this.discount = discount;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	public String getParent() {
		return parent;
	}
	public void setParent(String parent) {
		this.parent = parent;
	}
	
	

}
