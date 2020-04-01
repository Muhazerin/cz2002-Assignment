package hrps;

import java.time.DayOfWeek;
import java.time.temporal.ChronoField;
import java.time.LocalDate;
import java.time.Period;
import java.util.Scanner;

public class Payment{
	
	private double taxRate;
	private double discountRate;
	private double weekendRate;
	private Reservation rs;
	
	public Payment(Reservation r) {
		this.taxRate = 0.07;
		this.weekendRate = 1.15;
		this.discountRate = 0.25;
		this.rs = r;
	}
	
	public void billReport() {
		System.out.println("\nHotel Checkout Bill Report");
		if(rs.getRoom().getRoomServicePrice() != 0)
			System.out.println("Room Service Charge: + $SGD" + rs.getRoom().getRoomServicePrice());
		System.out.println("Room Charge(Weekdays: "+ calculateWeekdays() +"Weekends: "+ calculateWeekends() +"): " + "$SGD" + calculateRoomCharge());
		if(calculateDiscount() != 0)
			System.out.println("Discount: - $SGD" + calculateDiscount());
		System.out.println("Tax Charge: + $SGD" + calculateTaxCharge());
		System.out.println("Total bill is: $SGD" + calculateTotalBill());
	}
	
	private static void payMenu() {
		System.out.println("\n+--------------------------------+");
		System.out.println("| How would you like to pay:     |");
		System.out.println("| 1. Cash                        |");
		System.out.println("| 2. Credit Card                 |");
		System.out.println("| 3. I do not want to pay yet    |");
		System.out.println("+--------------------------------+");
		System.out.print("Enter choice: ");
	}
	
	public boolean payment() {
		int uChoice = -1;
		Scanner sc = new Scanner(System.in);
		while(true) {
			payMenu();
			uChoice = sc.nextInt();
			sc.nextLine();	// clear the "\n" in the buffer
			switch(uChoice) {
				case 1:
					int amt = 0;
					System.out.println("Enter amount paid:");
					amt = sc.nextInt();
					if(amt >= calculateTotalBill()) {
						System.out.println("You have paid: SGD$" + amt);
						if(amt > calculateTotalBill())
							System.out.println("Your change is: SGD$" + (amt - calculateTotalBill()));
						System.out.println("Payment completed");
						return true;
					}
					else if(amt < calculateTotalBill()) {
						System.out.println("Insufficient amount given");
					}
					break;
				case 2:
					if(rs.getGuest().getCreditCard() == null)
						System.out.print("You do not have a credit card");
					else {
						int csv = 0;
						System.out.print("Enter your credit card CSV pin: ");
						csv = sc.nextInt();
						if(csv == rs.getGuest().getCreditCart().getCsv()) {
							System.out.println("Payment completed");
							return true;
						}else
							System.out.println("You have entered the wrong CSV pin");
					}
					break;
				case 3:
					System.out.println("Payment Cancelled");
					return false;
					break;
				default:
					System.out.println("Invalid choice");
					break;
			}
		}
	}
	
	private int calculateWeekends() {
		int weekends = 0;
		Period stay = Period.between(rs.getCheckOutDate(), rs.getCheckInDate()); //Get the period between check in date and check out date.
	  	int totalDays = stay.getDays(); //Calculate the total days between check in date and check out date.
		for(int i=0; i<totalDays; i++){
			DayOfWeek day = DayOfWeek.of((rs.getCheckInDate().plusDays(i).get(ChronoField.DAY_OF_WEEK))); //Get the name of each day.
			if(day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY)
				weekends++;
		}
	}
	
	private int calculateWeekdays() {
		int weekdays = 0;
		Period stay = Period.between(rs.getCheckOutDate(), rs.getCheckInDate()); //Get the period between check in date and check out date.
	  	int totalDays = stay.getDays(); //Calculate the total days between check in date and check out date.
		for(int i=0; i<totalDays; i++){
			DayOfWeek day = DayOfWeek.of((rs.getCheckInDate().plusDays(i).get(ChronoField.DAY_OF_WEEK))); //Get the name of each day.
			if(day != DayOfWeek.SATURDAY && day != DayOfWeek.SUNDAY)
				weekdays++;
		}
	}
	
	private float calculateRoomCharge() {
		float totalPrice = 0;
		Period stay = Period.between(rs.getCheckOutDate(), rs.getCheckInDate()); //Get the period between check in date and check out date.
	  	int totalDays = stay.getDays(); //Calculate the total days between check in date and check out date.
		for(int i=0; i<totalDays; i++){
			DayOfWeek day = DayOfWeek.of((rs.getCheckInDate().plusDays(i).get(ChronoField.DAY_OF_WEEK))); //Get the name of each day.
			if(day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY){
				totalPrice += rs.getRoom().getRate() * totalDays * weekendRate;
				
			}else{
				
				totalPrice += rs.getRoom().getRate() * totalDays;
			}
		}
		return totalPrice;
	}
	
	private float calculateDiscount(){
		return (rs.getRoom().getRoomServicePrice() + calculateRoomCharge()) * discountRate;
	}
	
	private float calculateTaxCharge() {
		return (rs.getRoom().getRoomServicePrice() + calculateRoomCharge() - calculateDiscount()) * taxRate;
	}
	
	private float calculateTotalBill() {
		return (rs.getRoom().getRoomServicePrice() + calculateRoomCharge() - calculateDiscount()) + calculateTaxCharge();
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
}
