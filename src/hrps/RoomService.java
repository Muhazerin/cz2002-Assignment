package hrps;

//a) Hotel staff can order room service meals on guests behalf upon his/her request.
//b) List of menu items selection will be displayed for selection.
//c) Each menu item will have a name, a description of how it is prepared and price. (Under MenuItem class)
//d) When ordered, there will be a room service order created with a date/time, remarks (eg, less oil, less
//salt) and the status (confirmed, preparing, delivered).
//e) The order status will be updated accordingly. ???

import java.time.LocalDate;
import java.util.ArrayList;

public class RoomService {
	
	private ArrayList<MenuItem> menuItem;
	
	private enum orderStatus {
		CONFIRMED, PREPARING, DELIVERED; 
	}
	
	private LocalDate dateTime =  null;
	
	private String remarks;
//	private String roomId;
	
	public RoomService() {
		this.menuItem = new ArrayList<MenuItem>();
		this.remarks = "";
	}
	
	public void placeOrder(String roomId, int menuId, String rmks) {
		//get date and time
		this.dateTime = LocalDate.now();
		
		System.out.println("Room: " + roomId);
		System.out.println("Ordered: " + menuItem[menuId-1].getName());
		System.out.println("Date/Time: " + dateTime.toString());
		System.out.println("Remarks: " + rmks);
		
		this.remarks = rmks; 
		//adds the cost of the menu item and date time to a list
	}
	
	public void printOrder() {
		//prints individual order and the time ordered
	}
	
	public float getMenuItemsTotalCost() {
		return 0;
	}
	
}
