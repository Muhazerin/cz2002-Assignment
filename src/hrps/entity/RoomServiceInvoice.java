package entity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.time.LocalDate;
import java.util.ArrayList;

import entity.RoomService.OrderType;

public class RoomServiceInvoice extends Invoice{

	private int rsId;
	private LocalDate dateTime;
	private OrderType orderStatus;
	
	public RoomServiceInvoice(ArrayList<MenuItem> list, RoomService rs) {
		super(list,rs.getMenuItemTotalCost());
		rsId = rs.getId();
		dateTime = rs.getDateTime();
		orderStatus = rs.getOrderStatus();
	}
	
	public void printInvoice() {
		System.out.println("---------------Summary of order------------------");
		System.out.println("Room Service Id: " + this.rsId);
		System.out.println("Ordered on: " + this.dateTime.toString());
		System.out.println("Order status: " + this.orderStatus.toString());
	
		
		for (Object mi : this.itemsList) {
			try {
				MenuItem menuItem = (MenuItem) mi;
				System.out.println("Menu Item Ordered: " + menuItem.getName());
				System.out.println("Price: $" + menuItem.getPrice());
				System.out.println("------------------------------------------");
			} catch (Exception e) {
				if(e.getMessage() != null) {
					System.out.println("Error: " + e.getMessage());
				}
			}
	
		}
		System.out.printf("Grand Total: $%.2f\n", this.totalCost);
		System.out.println("------------------End of Order--------------------\n");
	}
	
	
}
