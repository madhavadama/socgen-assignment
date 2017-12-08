package com.socgen.discounts.apparel.model;

import java.util.List;

public class Bill {
	private List< Integer> billItems;
	private double price;
	private double discountedPrice;
	
	
	

	public List<Integer> getBillItems() {
		return billItems;
	}

	public void setBillItems(List<Integer> billItems) {
		this.billItems = billItems;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getDiscountedPrice() {
		return discountedPrice;
	}

	public void setDiscountedPrice(double discountedPrice) {
		this.discountedPrice = discountedPrice;
	}
	
}
