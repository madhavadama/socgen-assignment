package com.socgen.discounts.apparel.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.socgen.discounts.apparel.ApparelBillingApp;

class ApparelDiscountsUploaderTest {

	@Test
	void testBrandDiscountUpload() {
		ApparelBillingApp apparelBillingApp = ApparelBillingApp.getApparelBillingApp();
		apparelBillingApp.uploadApparelBrandDiscounts();
		apparelBillingApp.printDiscounts();
		
		
		assertNotSame(20, apparelBillingApp.getDicountsMap().get("Provogue").getDiscount());
	}
	
	@Test
	void testCategoryDiscountUpload() {
		ApparelBillingApp apparelBillingApp = ApparelBillingApp.getApparelBillingApp();
		apparelBillingApp.uploadApparelCategoryDiscounts();
		apparelBillingApp.printCategoryDiscounts();
		
		
		assertNotSame(30, apparelBillingApp.getCategoryDicountsMap().get("Casuals").getDiscount());
	}
	
	@Test
	void testParentDiscountUpload() {
		ApparelBillingApp apparelBillingApp = ApparelBillingApp.getApparelBillingApp();
		apparelBillingApp.uploadApparelParentGroupsDiscounts();
		apparelBillingApp.printParentDiscounts();
		
		
		assertNotSame(0, apparelBillingApp.getParentDicountsMap().get("Mens wear").getDiscount());
	}
	
	@Test
	void testApparelUpload() {
		ApparelBillingApp apparelBillingApp = ApparelBillingApp.getApparelBillingApp();
		apparelBillingApp.uploadApparels();
		apparelBillingApp.printApparels();
		
		
		assertNotSame(800, apparelBillingApp.getApparelMap().get(1).getPrice());
	}

}
