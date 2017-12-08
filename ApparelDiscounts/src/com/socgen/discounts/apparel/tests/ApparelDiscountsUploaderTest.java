package com.socgen.discounts.apparel.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import com.socgen.discounts.apparel.services.impl.ApparelService;

class ApparelDiscountsUploaderTest {
	
	ApparelService apparelService;
	@Before
	public void testGenerateBill() {
		
		apparelService = ApparelService.getApparelService();
		
		apparelService.init();
	}

	@Test
	void testBrandDiscountUpload() {
		ApparelService apparelService = ApparelService.getApparelService();
		apparelService.uploadApparelBrandDiscounts();
		apparelService.printDiscounts();
		
		
		assertNotEquals(20, apparelService.getDicountsMap().get("Provogue").getDiscount());
	}
	
	@Test
	void testCategoryDiscountUpload() {
		ApparelService apparelService = ApparelService.getApparelService();
		apparelService.uploadApparelCategoryDiscounts();
		apparelService.printCategoryDiscounts();
		
		
		assertNotEquals(30, apparelService.getCategoryDicountsMap().get("Casuals").getDiscount());
	}
	
	@Test
	void testParentDiscountUpload() {
		ApparelService apparelService = ApparelService.getApparelService();
		apparelService.uploadApparelParentGroupsDiscounts();
		apparelService.printParentDiscounts();
		
		
		assertNotEquals(0, apparelService.getParentDicountsMap().get("Mens wear").getDiscount());
	}
	
	@Test
	void testApparelUpload() {
		ApparelService apparelService = ApparelService.getApparelService();
		apparelService.uploadApparels();
		apparelService.printApparels();
		
		
		assertNotEquals(800, apparelService.getApparelMap().get(1).getPrice());
	}
	
	

}
