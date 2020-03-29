package hrps;

import java.time.LocalDate;

public class Reservation {
	public static enum ResStatus {
		CONFIRMED, IN_WAITLIST, CHECKED_IN, EXPIRED, CHECKED_OUT;
	}
	
	private static int counter = 1;
	private int resCode;
	private Guest guest = null;
	private Room room = null;
	private LocalDate checkInDate = null;
	private LocalDate checkOutDate = null;
	private int noOfAdults;
	private int noOfChildren;
	private ResStatus status;
	
	public Reservation() {
		this.resCode = counter;
		counter++;
	}
	
	public int getResCode() {
		return resCode;
	}
	public Guest getGuest() {
		return guest;
	}
	public void setGuest(Guest g) {
		this.guest = g;
	}
	public Room getRoom() {
		return room;
	}
	public void setRoom(Room r) {
		this.room = r;
	}
	public LocalDate getCheckInDate() {
		return checkInDate;
	}
	public void setCheckInDate(LocalDate checkInDate) {
		this.checkInDate = checkInDate;
	}
	public LocalDate getCheckOutDate() {
		return checkOutDate;
	}
	public void setCheckOutDate(LocalDate checkOutDate) {
		this.checkOutDate = checkOutDate;
	}
	public int getNoOfAdults() {
		return noOfAdults;
	}
	public void setNoOfAdults(int noOfAdults) {
		this.noOfAdults = noOfAdults;
	}
	public int getNoOfChildren() {
		return noOfChildren;
	}
	public void setNoOfChildren(int noOfChildren) {
		this.noOfChildren = noOfChildren;
	}
	public ResStatus getStatus() {
		return status;
	}
	public void setStatus(ResStatus status) {
		this.status = status;
	}
	
	public void printReceipt() {
		System.out.println("This is your reservation details.");
		System.out.println("Reservation Code: " + resCode);
		System.out.println("Check In Date: " + checkInDate.toString());
		System.out.println("Your reservation will expire if you fail to arrive after 1 hour of the scheduled check-in date.");
	}
	
	public void checkOut() {
		System.out.println("Something");
	}
}
