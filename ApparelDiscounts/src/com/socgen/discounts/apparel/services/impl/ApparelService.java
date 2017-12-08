package com.socgen.discounts.apparel.services.impl;



import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.socgen.discounts.apparel.model.Apparel;
import com.socgen.discounts.apparel.model.Bill;
import com.socgen.discounts.apparel.model.Category;
import com.socgen.discounts.apparel.model.Discount;
import com.socgen.discounts.apparel.services.ApparelBillService;

public class ApparelService {

	private Map<Integer, Apparel> apparelMap = new HashMap<Integer, Apparel>();

	private Map<String,Discount> brandsDicountsMap = new HashMap<String,Discount>();
	
	private Map<String,Discount> parentDicountsMap = new HashMap<String,Discount>();
	private Map<String, Category> categoryDicountsMap = new HashMap<String,Category>();
	//private List<Integer> billItems = new ArrayList<Integer>();
	
	private Map< Integer, Bill> storedBills = new HashMap<Integer, Bill>();
	



	private static ApparelService apparelService = new ApparelService();
	
	
	
	public static ApparelService getApparelService() {
		
		return apparelService;
	}
	public void init() {
		
		uploadApparelBrandDiscounts();
		uploadApparelCategoryDiscounts();
		uploadApparelParentGroupsDiscounts();
		uploadApparels();

		
		
	}
	
	public void readShopInventory()
	{
		int number =0;
		

		java.util.Scanner sc = new Scanner(System.in);
		
		number = Integer.parseInt(sc.nextLine().trim());
		
		while(number >= 0) {
		
		Scanner lineScanner = new Scanner(sc.nextLine());  // it will not leave untill user enter data. 
		lineScanner = lineScanner.useDelimiter(",");
		
		while (lineScanner.hasNext()) {
			
			
				int id = lineScanner.nextInt(); 
				String name = lineScanner.next();
				String catergory = lineScanner.next();
				double price = lineScanner.nextDouble();
			
				Apparel apparel = new Apparel(id, name, catergory, price);
				if(apparelMap.containsKey(id))
					apparelMap.remove(id);
				
				apparelMap.put(id, apparel);
				
			}
		number--;
		lineScanner.close();
		}
		
		
		
		sc.close();
	}
	
	public List< List<Integer>> scanUserBillItems()
	{
		List< List<Integer>> bills = new ArrayList<List<Integer>>();
		
		int number =0;
		java.util.Scanner sc = new Scanner(System.in);
		
		number = Integer.parseInt(sc.nextLine().trim());
		
		while(number > 0) {
		Scanner lineScanner = new Scanner(sc.nextLine());  // it will not leave until user enter data. 
		lineScanner = lineScanner.useDelimiter(",");
		
		List<Integer> billItems = new ArrayList<Integer>();
		while (lineScanner.hasNext()) {
			
				int id = lineScanner.nextInt(); 
				billItems.add(id);
				
			}
		bills.add(billItems);
		number--;
		lineScanner.close();
		}
				
		sc.close();
		return bills;
	}
	
	public void generateBills(List< List<Integer>> bills) {
		
		int billNo = storedBills.size();
		for(List<Integer> bill : bills)
		{
			ApparelBillService apparelBillService = new ApparelBillServiceImpl();
			
			Bill bill1 = apparelBillService.generateBill(bill);
			apparelService.setStoredBill(billNo++, bill1);
		}
	}
	
	
	public void printBills(){
		
		for(Bill bill : storedBills.values())
		{
			
			ApparelBillService apparelBillService = new ApparelBillServiceImpl();
			
			apparelBillService.printBill(bill);
		}
		
	}

	
	public void uploadApparelBrandDiscounts() {
		try{

	    	 String fileName = "brands.data";
	    	 Stream<String> discountsRows = Files.lines(Paths.get(fileName));
	    	
	    	 brandsDicountsMap = 
	        		 discountsRows.skip(1).map(line -> line.split(","))
	                      .collect(Collectors.toMap(line -> line[0], 
	                    		  					line -> new Discount(line[0].trim(), Long.parseLong(line[1].trim()))));
	         
	         discountsRows.close();


	    } catch (IOException e) {


	    }
	}
	
	public void uploadApparelCategoryDiscounts() {
		try{

	    	 String fileName = "category.data";
	    	 Stream<String> discountsRows = Files.lines(Paths.get(fileName));
	    	
	    	 categoryDicountsMap = 
	        		 discountsRows.skip(1).map(line -> line.split(","))
	                      .collect(Collectors.toMap(line -> line[0], 
	                    		  					line -> new Category(line[0].trim(), 
	                    		  							            line[1].trim(), 
	                    		  							            Long.parseLong(line[2].trim()))));
	         
	         discountsRows.close();


	    } catch (IOException e) {


	    }
	}
	
	public void uploadApparelParentGroupsDiscounts() {
		try{

	    	 String fileName = "parent.data";
	    	 Stream<String> discountsRows = Files.lines(Paths.get(fileName));
	    	
	    	 parentDicountsMap = 
	        		 discountsRows.skip(1).map(line -> line.split(","))
	                      .collect(Collectors.toMap(line -> line[0], 
	                    		  					line -> new Discount(line[0].trim(), 
	                    		  							            Long.parseLong(line[1].trim()))));
	         
	         discountsRows.close();


	    } catch (IOException e) {


	    }
	}
	
	public void uploadApparels() {
		try{

	    	 String fileName = "apparel.data";
	    	 Stream<String> apparelsRows = Files.lines(Paths.get(fileName));
	    	
	    	 apparelMap = 
	    			 apparelsRows.skip(1).map(line -> line.split(","))
	                      .collect(Collectors.toMap(line -> Integer.valueOf(line[0].trim()), 
	                    		  					line -> new Apparel(Integer.parseInt(line[0].trim()), line[1].trim(), line[2].trim(),
	                    		  							            Double.parseDouble(line[3].trim()))));
	         
	    	 apparelsRows.close();


	    } catch (IOException e) {


	    }
	}
	
	
	public void printDiscounts() {
		
		brandsDicountsMap.forEach((k,v)->System.out.println("name : " + k + " discount : " + v.getDiscount()));
	}
	public void printCategoryDiscounts() {
		categoryDicountsMap.forEach((k,v)->System.out.println("name : " + k + " parent : " + v.getParent() +" discount : " + v.getDiscount()));
		
	}
	public void printParentDiscounts() {
		
		brandsDicountsMap.forEach((k,v)->System.out.println("name : " + k + " discount : " + v.getDiscount()));
		
	}
	
	public void printApparels() {
		
		apparelMap.forEach((k,v)->System.out.println("name : " + k + " price : " + v.getPrice()));
		
	}
	
	public Map<Integer, Apparel> getApparelMap() {
		return apparelMap;
	}
	public void setApparelMap(Map<Integer, Apparel> apparelMap) {
		this.apparelMap = apparelMap;
	}
	
	public Map<String, Discount> getBrandsDicountsMap() {
		return brandsDicountsMap;
	}
	public void setBrandsDicountsMap(Map<String, Discount> brandsDicountsMap) {
		this.brandsDicountsMap = brandsDicountsMap;
	}

	
	public Map<String, Discount> getDicountsMap() {
		return brandsDicountsMap;
	}
	public void setDicountsMap(Map<String, Discount> dicountsMap) {
		this.brandsDicountsMap = dicountsMap;
	}
	public Map<String,Discount> getParentDicountsMap() {
		return parentDicountsMap;
	}
	public void setParentDicountsMap(Map<String,Discount> parentDicountsMap) {
		this.parentDicountsMap = parentDicountsMap;
	}
	public Map<String, Category> getCategoryDicountsMap() {
		return categoryDicountsMap;
	}
	public void setCategoryDicountsMap(Map<String, Category> categoryDicountsMap) {
		this.categoryDicountsMap = categoryDicountsMap;
	}
	public Map<Integer, Bill> getStoredBills() {
		return storedBills;
	}
	public void setStoredBills(Map<Integer, Bill> storedBills) {
		this.storedBills = storedBills;
	}
	public Bill getStoredBill(int billNo) {
		
		
		return storedBills.get(billNo);
		
	}
	public void setStoredBill(int billNo, Bill storedBill) {
		
		this.storedBills.put(billNo, storedBill);
		
	}
	
	public void resetStoredBills()
	{
		storedBills.clear();
	}
	
	public static void main(String args[]) {
		
		ApparelService apparelService = getApparelService();
		
		apparelService.init();
		System.out.println("Do you want to enter Shop Inventory in Console");
		java.util.Scanner sc = new Scanner(System.in);
		String response = sc.next();
		
		if(response.equals("Y") || response.equals("y") || 
				response.equals("YES") || response.equals("yes"))
			apparelService.readShopInventory();
		
		boolean done = false;
		while(!done) {
			try {
				apparelService.resetStoredBills();
				List<List<Integer>> listBills= apparelService.scanUserBillItems();
				
				apparelService.generateBills(listBills);
				apparelService.printBills();
				
				done=true;
			}
			catch(NumberFormatException | InputMismatchException e) {
				System.out.println("Wrong Input, Re enter inputs");
			}
		}
		//apparelService.generateBill();
		
	}
}
