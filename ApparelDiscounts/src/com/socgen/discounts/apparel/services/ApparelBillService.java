package com.socgen.discounts.apparel.services;

import java.util.List;

import com.socgen.discounts.apparel.model.Bill;

public interface ApparelBillService {
	
	public Bill generateBill(List< Integer> billItems);
	public void printBill(Bill bill);
	
}
