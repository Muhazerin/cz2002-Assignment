package hrps;

//a) Hotel staff can order room service meals on guest’s behalf upon his/her request.
//b) List of menu items selection will be displayed for selection.
//c) Each menu item will have a name, a description of how it is prepared and price. (Under MenuItem class)
//d) When ordered, there will be a room service order created with a date/time, remarks (eg, less oil, less
//salt) and the status (confirmed, preparing, delivered).
//e) The order status will be updated accordingly. ???

import java.util.Date;
import java.util.ArrayList;

public class RoomService {
	
	private MenuItem[] menuItem;
	public enum orderStatus {CONFIRMED, PREPARING, DELIVERED; }
	private Date dateTime =  null;
	private String remarks;
	private int i;
	//private String roomId;
	
	public RoomService() {
		this.menuItem = new MenuItem[99];
		this.remarks = "";
		this.i = 0;
	}
	
	public void placeOrder(int id, String menuName, String menuDescription, float menuPrice, String rmks){
		// what is Id?
		this.dateTime = new Date();
		this.remarks = rmks;
		menuItem[i].setId(id);
		menuItem[i].setName(menuName);
		menuItem[i].setDescription(menuDescription);
		menuItem[i].setPrice(menuPrice);
		menuItem[i].setDate(dateTime);
		menuItem[i].setRemarks(rmks);
		this.i = i++;
	}
	
	public void printOrder() {
		System.out.println("Summary of all orders:");
		for(int j=0; j<i; j++) {
			System.out.println(menuItem[j].getName() + " , " + menuItem[j].getPrice() + " , " + menuItem[j].getDate());
		}
	}
	
	public float getMenuItemTotalCost() {
		float total = 0;
		for(int j=0; j<i; j++) {
			total += menuItem[j].getPrice();
		}
		return total;
	}
	
}
