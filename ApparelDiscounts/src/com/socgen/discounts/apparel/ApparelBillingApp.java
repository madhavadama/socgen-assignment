package com.socgen.discounts.apparel;

import static org.junit.jupiter.api.DynamicTest.stream;

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
import com.socgen.discounts.apparel.model.Category;
import com.socgen.discounts.apparel.model.Discount;

public class ApparelBillingApp {

	private Map<Integer, Apparel> apparelMap = new HashMap<Integer, Apparel>();

	private Map<String,Discount> brandsDicountsMap = new HashMap<String,Discount>();
	
	private Map<String,Discount> parentDicountsMap = new HashMap<String,Discount>();
	private Map<String, Category> categoryDicountsMap = new HashMap<String,Category>();
	private List<Integer> billItems = new ArrayList<Integer>();
	
	private List< List<Integer>> bills = new ArrayList<List<Integer>>();
	

	private static ApparelBillingApp apparelBillingApp = new ApparelBillingApp();
	
	
	
	public static ApparelBillingApp getApparelBillingApp() {
		
		return apparelBillingApp;
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
	
	public void scanUserBillItems()
	{
		int number =0;
		
		
		java.util.Scanner sc = new Scanner(System.in);
		
		number = Integer.parseInt(sc.nextLine().trim());
		
		
		while(number > 0) {
		Scanner lineScanner = new Scanner(sc.nextLine());  // it will not leave untill user enter data. 
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
	}
	
	public void generateBill() {
		
		for(List<Integer> bill : bills)
		{
			double originalPrice = 0;
			double discountedPrice = 0;
			
			
			for(int itemId:bill){
				Apparel apparel = apparelMap.get(itemId);
				
				double price= apparel.getPrice();
				//System.out.println("Price = "+price);
				double brandDiscount = brandsDicountsMap.get(apparel.getBrand()).getDiscount();
				
				double categoryDiscount =categoryDicountsMap.get(apparel.getCategory()).getDiscount();
				
				Category category = categoryDicountsMap.get(apparel.getCategory());
				
				double parentDiscount = parentDicountsMap.get(category.getParent()).getDiscount();
				
			    double applicableDiscount = Math.max(brandDiscount, Math.max(categoryDiscount, parentDiscount));
			    //System.out.println("applicableDiscount = "+applicableDiscount);
			    
			    //System.out.println("Price = "+price+" "+(price*applicableDiscount/100));
			    originalPrice+=price;
			    discountedPrice += price*(100-applicableDiscount)/100;
			    
			}
			
			System.out.println("Items "+bill.toString()+"Price ="+originalPrice+"Discounted price="+discountedPrice);
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
	public List<List<Integer>> getBills() {
		return bills;
	}
	public void setBills(List<List<Integer>> bills) {
		this.bills = bills;
	}
	
	public void resetBills()
	{
		bills.clear();
	}
	
	public static void main(String args[]) {
		
		ApparelBillingApp apparelBillingApp = getApparelBillingApp();
		
		apparelBillingApp.init();
		System.out.println("Do you want to enter Shop Inventory in Console");
		java.util.Scanner sc = new Scanner(System.in);
		String response = sc.next();
		
		if(response.equals("Y") || response.equals("y") || 
				response.equals("YES") || response.equals("yes"))
			apparelBillingApp.readShopInventory();
		
		boolean done = false;
		while(!done) {
			try {
				apparelBillingApp.resetBills();
				apparelBillingApp.scanUserBillItems();
				done=true;
			}
			catch(NumberFormatException | InputMismatchException e) {
				System.out.println("Wrong Input, Re enter inputs");
			}
		}
		apparelBillingApp.generateBill();
		
	}
}
