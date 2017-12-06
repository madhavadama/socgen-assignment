package com.socgen.discounts.apparel.services;

import com.socgen.discounts.apparel.model.Apparel;
import com.socgen.discounts.services.exception.ApparelNotFoundException;
import com.socgen.discounts.services.exception.DiscountNameNotFoundException;

public interface ApparelService {

	public long getPrice(int id) throws ApparelNotFoundException;
	public long getDiscountedPrice(int id) throws ApparelNotFoundException;
	public void create(Apparel record) throws ApparelNotFoundException;
	public void update(Apparel record) throws ApparelNotFoundException;
	public void delete(int id) throws ApparelNotFoundException;
}
