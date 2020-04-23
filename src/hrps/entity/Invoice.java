package entity;

import java.util.ArrayList;

public class Invoice {
	
	protected double totalCost;
	protected ArrayList<?> itemsList;

	
	public Invoice(ArrayList<?> items, double total) {
		totalCost = total;
		itemsList = items;
		
	}
	
	
	public void printInvoice() {
		
		System.out.printf("Total Cost = %.2f", totalCost);
		
	}
	
	public double getTotalCost() {
		return totalCost;
	}
	
	public ArrayList<?> getItemsList(){
		return itemsList;
	}
}
