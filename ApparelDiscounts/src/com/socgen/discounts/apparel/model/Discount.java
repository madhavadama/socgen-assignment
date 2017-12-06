package com.socgen.discounts.apparel.model;

public class Discount {

	private String name;
	private double discount;
	
	public Discount(String name, double discount) {
		this.name = name;
		this.discount = discount;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(long discount) {
		this.discount = discount;
	}
}
