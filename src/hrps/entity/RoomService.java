package entity;

//a) Hotel staff can order room service meals on guest's behalf upon his/her request.
//b) List of menu items selection will be displayed for selection.
//c) Each menu item will have a name, a description of how it is prepared and price. (Under MenuItem class)
//d) When ordered, there will be a room service order created with a date/time, remarks (eg, less oil, less
//salt) and the status (confirmed, preparing, delivered).
//e) The order status will be updated accordingly. ???

import java.time.LocalDate;
import java.util.ArrayList;

import entity.RoomService.OrderType;

import java.io.Serializable;

public class RoomService implements Serializable{
	
	private static final long serialVersionUID = 1234L;
	private ArrayList<MenuItem> menuItemList;
	public enum OrderType {CONFIRMED, PREPARING, DELIVERED; }
	private LocalDate dateTime =  null;
	private String remarks;
	private int id;
	private OrderType orderStatus;
	//private String roomId;
	
	public RoomService() {
		this.menuItemList = new ArrayList<MenuItem>();
		this.orderStatus = OrderType.PREPARING;
	}
	
	public void placeOrder(int id, String remarks, ArrayList<MenuItem> miList){
		// what is Id?
		this.dateTime = LocalDate.now();
		this.id = id;
		this.remarks = remarks;
		menuItemList = miList;
	}
	
	public void printOrder() {
<<<<<<< HEAD:src/hrps/RoomService.java
		System.out.println("---------------Summary of order------------------");
		System.out.println("Room Service Id: " + this.id);
		System.out.println("Ordered on: " + this.dateTime.toString());
		System.out.println("Order status: " + this.getOrderStatus().toString());
		for (MenuItem mi : menuItemList) {
			System.out.println("Menu Item Ordered: " + mi.getName());
			System.out.println("Price: $" + mi.getPrice());
		}
		System.out.println("---------------------End------------------------");
=======
//		System.out.println("---------------Summary of order------------------");
//		System.out.println("Room Service Id: " + this.id);
//		System.out.println("Ordered on: " + this.dateTime.toString());
//		System.out.println("Order status: " + this.getOrderStatus().toString());
//		for (MenuItem mi : menuItemList) {
//			System.out.println("Menu Item Ordered: " + mi.getName());
//			System.out.println("Price: $" + mi.getPrice());
//		}
//		System.out.println("---------------------End------------------------");
		
		Invoice invoice = new RoomServiceInvoice(menuItemList, this); 
		invoice.printInvoice();
				
				
	}
	
	public ArrayList<MenuItem> getMenuItemsList(){
		return this.menuItemList;
	}
	
	public LocalDate getDateTime() {
		return this.dateTime;
>>>>>>> b3eb5b1210ece8de6499b59808c00fa2f142987d:src/hrps/entity/RoomService.java
	}
	
	public float getMenuItemTotalCost() {
		float total = 0;
		for (MenuItem mi : menuItemList) {
			total += mi.getPrice();
		}
		return total;
	}
	
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	public String getRemarks() {
		return this.remarks;
	}

	public int getId() {
		return this.id;
	}

	public void setOrderStatus(OrderType orderStatus) {
		this.orderStatus = orderStatus;
	}
	
	public OrderType getOrderStatus() {
		return this.orderStatus;
	}
}
