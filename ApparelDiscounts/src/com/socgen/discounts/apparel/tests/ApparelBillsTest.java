package com.socgen.discounts.apparel.tests;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import com.socgen.discounts.apparel.services.impl.ApparelService;

public class ApparelBillsTest {

	ApparelService apparelService;
	@Before
	public void testGenerateBill() {
		
		apparelService = ApparelService.getApparelService();
		
		apparelService.init();
	}
	
	@Test
	void testApparelBill()
	{
		apparelService = ApparelService.getApparelService();
		
		apparelService.generateBills(apparelService.scanUserBillItems());
	}
}
