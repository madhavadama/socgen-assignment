package com.socgen.discounts.apparel.model;

public class Apparel {

	
	private int apparelId;
	private String brand;
	private String category;
	
	private double price;
	
	
	private String name;
	
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Apparel()
	{
		
	}
	public Apparel(int id, String brand, String category, double price) {
		this.setBrand(brand);
		this.setCategory(category);
		this.price = price;
		
	}
	public int getApparelId() {
		return apparelId;
	}
	public void setApparelId(int apparelId) {
		this.apparelId = apparelId;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(long price) {
		this.price = price;
	}
	
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	
}
