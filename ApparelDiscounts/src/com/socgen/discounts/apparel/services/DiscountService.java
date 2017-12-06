package com.socgen.discounts.apparel.services;

import com.socgen.discounts.apparel.model.Discount;
import com.socgen.discounts.services.exception.DiscountNameNotFoundException;

public interface DiscountService {

	public long getDiscount(String name) throws DiscountNameNotFoundException;
	public void create(Discount record) throws DiscountNameNotFoundException;
	public void update(Discount record) throws DiscountNameNotFoundException;
	public void delete(String name) throws DiscountNameNotFoundException;
}
