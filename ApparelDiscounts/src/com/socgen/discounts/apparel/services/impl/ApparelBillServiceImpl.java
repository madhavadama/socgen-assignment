package com.socgen.discounts.apparel.services.impl;

import java.util.List;

import com.socgen.discounts.apparel.model.Apparel;
import com.socgen.discounts.apparel.model.Bill;
import com.socgen.discounts.apparel.model.Category;
import com.socgen.discounts.apparel.services.ApparelBillService;

public class ApparelBillServiceImpl implements ApparelBillService{



	public Bill generateBill(List< Integer> billItems) {
		double originalPrice = 0;
		double discountedPrice = 0;
		Bill bill = new Bill();
		
		bill.setBillItems(billItems);
		ApparelService apparelService = ApparelService.getApparelService();
		
		//billItems.stream().
		for(int itemId:billItems){
			
			Apparel apparel = apparelService.getApparelMap().get(itemId);
			
			double price= apparel.getPrice();
			double brandDiscount = apparelService.getBrandsDicountsMap().get(apparel.getBrand()).getDiscount();
			
			double categoryDiscount =apparelService.getCategoryDicountsMap().get(apparel.getCategory()).getDiscount();
			
			Category category = apparelService.getCategoryDicountsMap().get(apparel.getCategory());
			
			double parentDiscount = apparelService.getParentDicountsMap().get(category.getParent()).getDiscount();
			
		    double applicableDiscount = Math.max(brandDiscount, Math.max(categoryDiscount, parentDiscount));
		    originalPrice+=price;
		    discountedPrice += price*(100-applicableDiscount)/100;
		    
		}
		
		bill.setPrice(originalPrice);
		bill.setDiscountedPrice(discountedPrice);
		
		return bill;
		
	}
	public void printBill(Bill bill) {
		
		System.out.println("Items "+bill.getBillItems().toString()+
				"Price = "+bill.getPrice()+"  Discounted price= "+bill.getDiscountedPrice());
	}
}
