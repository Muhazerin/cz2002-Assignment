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
	
	private enum orderStatus {
		Confirmed, Preparing, Delivered; 
	}
	
	private Date dateTime =  null;
	
	private ArrayList<String> listOfOrderTimeDate;
	
	private ArrayList<Float> listOfMenuItemCost;
	
	private String remarks;
//	private String roomId;
	
	public RoomService() {
		this.menuItem = new MenuItem[99];
		this.listOfOrderTimeDate = new ArrayList<String>();
		this.listOfMenuItemCost = new ArrayList<Float>();
		this.remarks = "";
	}
	
	public String listMenuItems() {
		for(int i = 0; i<menuItem.length; i++) {
			menuItem[i].printOrder;
		}
	}
	
	public void placeOrder(String roomId, int menuId, String rmks) {
		//get date and time
		this.dateTime = new Date();
		
		System.out.println("Room: " + roomId);
		System.out.println("Ordered: " + menuItem[menuId-1].name );
		System.out.println("Date/Time: " + dateTime.toString());
		System.out.println("Remarks: " + rmks);
		
		this.remarks = rmks; 
		//adds the cost of the menu item and date time to a list
		this.listOfMenuItemCost.add(menuItem[menuId-1].cost);
		this.listOfOrderTimeDate.add(dateTime.toString());
	}
	
	public void printOrder() {
		//prints individual order and the time ordered
	}
	
	public float getMenuItemsTotalCost() {
		
		float sum = 0;
		for(float i: listOfMenuItemCost) {
			sum += i;
		}
		return sum;
	}
	
}
