package com.socgen.discounts.apparel.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import com.socgen.discounts.apparel.services.impl.ApparelService;

public class ApparelBillsTest {

	ApparelService apparelService;
	@Before
	public void testGenerateBill() {
		
		//apparelService = ApparelService.getApparelService();
		
		//apparelService.init();
	}
	
	@Test
	void testApparelBill()
	{
		apparelService = ApparelService.getApparelService();
		
		apparelService.init();
		
		List<List<Integer>> lists = new ArrayList<>(2);
		
		List<Integer> list1 = new ArrayList<>();
		list1.add(1);
		list1.add(2);
		list1.add(3);
		list1.add(4);
		
		lists.add( list1);
		
		List<Integer> list2 = new ArrayList<Integer>();
		list2.add(1);
		list2.add(5);
		lists.add( list2);
		//apparelService.readShopInventory();
		apparelService.generateBills(lists);
		apparelService.printBills();
		assertEquals(3860, (int)apparelService.getStoredBill(0).getDiscountedPrice());
		assertEquals(2140, (int)apparelService.getStoredBill(1).getDiscountedPrice());
		
	}
}
