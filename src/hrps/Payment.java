package hrps;

import java.util.ArrayList;
import java.time.DayOfWeek;
import java.time.temporal.ChronoField;
import java.time.Period;
import java.util.Scanner;

import hrps.RoomService.OrderType;

public class Payment{
	
	private double taxRate;
	private double discountRate;
	private double weekendRate;
	private Reservation rs;
	private Scanner sc;
	
	/*
	 * Constructor for Payment class
	 * Initialize the taxRate, weekendRate, discountRate, Reservation, and Scanner class
	 */
	public Payment(Reservation r, Scanner sc) {
		this.taxRate = 0.07;
		this.weekendRate = 1.15;
		this.discountRate = 0.25;
		this.rs = r;
		this.sc = sc;
	}
	
	/*
	 * This method prints the bill
	 */
	public void billReport() {
		System.out.println("\nHotel Checkout Bill Report");
		System.out.println("Total Room Charge(Weekdays: "+ calculateWeekdays() +", Weekends: "+ calculateWeekends() +"): " + "$SGD" + calculateRoomCharge());
		if (rs.getRoomServiceList().size() != 0) {
			System.out.println("Room Service Charges:");
			getRoomServicePriceList();
			System.out.printf("Total Room Service Charge: + $SGD%.2f\n",rs.getRoomServicePrice());
		}

		if(calculateDiscount() != 0)
			System.out.printf("Discount: - $SGD%.2f\n",calculateDiscount());
		System.out.printf("Tax Charge: + $SGD%.2f\n",calculateTaxCharge());
		System.out.printf("Total bill is: $SGD%.2f",calculateTotalBill());
	}
	
	/*
	 * This method prints all of the room service's order
	 */
	private void getRoomServicePriceList() {
		ArrayList<RoomService> rsL = rs.getRoomServiceList();
		for (RoomService roomService : rsL) {
			System.out.println("Room Service <"+ rsL.indexOf(roomService)+1 +">");
			//during payment change all room service status to delivered.
			roomService.setOrderStatus(OrderType.DELIVERED);
			roomService.printOrder();
		}
	}
	
	/*
	 * This method contains the menu for payment method
	 */
	private void payMenu() {
		System.out.println("\n+--------------------------------+");
		System.out.println("| How would you like to pay:     |");
		System.out.println("| 1. Cash                        |");
		System.out.println("| 2. Credit Card                 |");
		System.out.println("+--------------------------------+");
		System.out.print("Enter choice: ");
	}
	
	/*
	 * This method lets the user pay for the reservation by cash or credit card
	 */
	public void payment() {
		int uChoice = -1;
		do {
			payMenu();
			uChoice = validateChoice(uChoice, "Enter choice: ");
			
			switch (uChoice) {
			case 1:
				System.out.println("Payment Details:");
				System.out.println("Paid by: Cash");
				System.out.printf("Total Cost: SGD$%.2f\n",calculateTotalBill());
				System.out.printf("Amount Paid: SGD$%.2f\n",calculateTotalBill());
				System.out.println("Payment completed");
				break;
			case 2:
				System.out.println("Payment Details:");
				System.out.println("Paid by: Credit Card");
				System.out.println("Type: " + rs.getGuest().getCreditCard().getcType());
				System.out.println("Name: " + rs.getGuest().getCreditCard().getName());
				System.out.println("Address: " + rs.getGuest().getCreditCard().getAddress());
				System.out.printf("Amount Billed: $%.2f\n",calculateTotalBill());
				System.out.println("Payment completed");
				break;
			default:
				System.out.println("Invalid choice");
				break;
			}
			
		} while (uChoice != 1 && uChoice != 2);
		
	}
	
	/*
	 * This method calculates the number of weekends the user spent in the hotel 
	 */
	private int calculateWeekends() {
		int weekends = 0;
		Period stay = Period.between(rs.getCheckInDate(), rs.getCheckOutDate()); //Get the period between check in date and check out date.
	  	int totalDays = stay.getDays(); //Calculate the total days between check in date and check out date.
		for(int i=0; i<totalDays; i++){
			DayOfWeek day = DayOfWeek.of((rs.getCheckInDate().plusDays(i).get(ChronoField.DAY_OF_WEEK))); //Get the name of each day.
			if(day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY)
				weekends++;
		}
		return weekends;
	}
	
	/*
	 * This method calculates the number of weekdays the user spent in the hotel
	 */
	private int calculateWeekdays() {
		int weekdays = 0;
		Period stay = Period.between(rs.getCheckInDate(), rs.getCheckOutDate()); //Get the period between check in date and check out date.
	  	int totalDays = stay.getDays(); //Calculate the total days between check in date and check out date.
		for(int i=0; i<totalDays; i++){
			DayOfWeek day = DayOfWeek.of((rs.getCheckInDate().plusDays(i).get(ChronoField.DAY_OF_WEEK))); //Get the name of each day.
			if(day != DayOfWeek.SATURDAY && day != DayOfWeek.SUNDAY)
				weekdays++;
		}
		return weekdays;
	}
	
	/*
	 * This method calculate the total room charge
	 * Return totalDay*roomRate
	 */
	private float calculateRoomCharge() {
		float totalPrice = 0;
		Period stay = Period.between(rs.getCheckInDate(), rs.getCheckOutDate()); //Get the period between check in date and check out date.
	  	int totalDays = stay.getDays(); //Calculate the total days between check in date and check out date.
	  	for(int i=0; i<totalDays; i++){
			DayOfWeek day = DayOfWeek.of((rs.getCheckInDate().plusDays(i).get(ChronoField.DAY_OF_WEEK))); //Get the name of each day.
			if(day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY){
				totalPrice += rs.getRoom().getRate() * weekendRate;
				
			}else{
				
				totalPrice += rs.getRoom().getRate();
			}
		}
		return totalPrice;
	}
	
	/*
	 * This method calculate the total discount 
	 */
	private double calculateDiscount(){
		return (rs.getRoomServicePrice() + calculateRoomCharge()) * discountRate;
	}
	
	/*
	 * This method calculate the total tax payable
	 */
	private double calculateTaxCharge() {
		return (rs.getRoomServicePrice() + calculateRoomCharge() - calculateDiscount()) * taxRate;
	}
	
	/*
	 * This method calculate the total bill based on the discount and tax
	 */
	private double calculateTotalBill() {
		return (rs.getRoomServicePrice() + calculateRoomCharge() - calculateDiscount()) + calculateTaxCharge();
	}

	public double getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(double taxRate) {
		this.taxRate = taxRate;
	}

	public double getDiscountRate() {
		return discountRate;
	}

	public void setDiscountRate(double discountRate) {
		this.discountRate = discountRate;
	}

	public double getWeekendRate() {
		return weekendRate;
	}

	public void setWeekendRate(double weekendRate) {
		this.weekendRate = weekendRate;
	}
	
	private int validateChoice(int choice, String inputText) {
		boolean valid = false;
		
		while (!valid) {
			if (!sc.hasNextInt()) {
				System.out.println("Invalid Input. Please enter an integer");
				sc.nextLine();	// clear the input in the buffer
				System.out.print(inputText);
			}
			else {
				valid = true;
				choice = sc.nextInt();
				sc.nextLine();	// clear the "\n" in the buffer
			}
		}
		
		return choice;
	}
}
