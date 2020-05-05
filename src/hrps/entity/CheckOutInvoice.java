package entity;

import java.util.ArrayList;

public class CheckOutInvoice extends Invoice{
	
	private int numWeekdays, numWeekends;
	private double roomCharge, discount, taxCharge;
	
	
	public CheckOutInvoice(ArrayList<RoomService> roomServiceList, Payment p) {
		super(roomServiceList, p.calculateTotalBill());
		this.discount = p.getDiscountRate();
		this.numWeekdays = p.calculateWeekdays();
		this.numWeekends = p.calculateWeekends();
		this.roomCharge = p.calculateRoomCharge();
		this.taxCharge = p.getTaxRate();
		this.discount = p.getDiscountRate();
	}
	
	
	public void printInvoice() {
		System.out.println("\nHotel Checkout Bill Report");
		System.out.println("Total Room Charge(Weekdays: "+ numWeekdays +", Weekends: "+ numWeekends +"): " + "$SGD" + roomCharge);
		
		
		if (this.itemsList.size() != 0) {
			System.out.println("Room Service Charges:");
			
			float total = 0;
			for(Object rs: this.itemsList) {
				if(rs instanceof RoomService) {
					RoomService roomService = (RoomService) rs;
					RoomServiceInvoice rsInvoice = new RoomServiceInvoice(roomService.getMenuItemsList(), (RoomService)rs);
					total += roomService.getMenuItemTotalCost();
					rsInvoice.printInvoice();		
				}
			}
			System.out.printf("Total Room Service Charge: + $SGD%.2f\n",total);
		}

		if(discount != 0)
			System.out.printf("Discount: - $SGD%.2f\n",discount);
		System.out.printf("Tax Charge: + $SGD%.2f\n",taxCharge);
		System.out.printf("Total bill is: $SGD%.2f",totalCost);
		
	}
	
	
	

}
