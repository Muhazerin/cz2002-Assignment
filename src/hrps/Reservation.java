package hrps;

import java.util.Date;

public class Reservation {
	enum ResStatus {
		CONFIRMED, IN_WAITLIST, CHECKED_IN, EXPIRED, CHECKED_OUT;
	}
	
	private int resCode;
	private Guest guest = null;
	private Room room = null;
	private Payment payment = null;
	private Date checkInDate = null;
	private Date checkOutDate = null;
	private int noOfAdults;
	private int noOfChildren;
	private ResStatus status;
	
	public Reservation() {
		
	}
	
	public int getResCode() {
		return resCode;
	}
	public void setResCode(int resCode) {
		this.resCode = resCode;
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
	public Payment getPayment() {
		return payment;
	}
	public void setPayment(Payment p) {
		this.payment = p;
	}
	public Date getCheckInDate() {
		return checkInDate;
	}
	public void setCheckInDate(Date checkInDate) {
		this.checkInDate = checkInDate;
	}
	public Date getCheckOutDate() {
		return checkOutDate;
	}
	public void setCheckOutDate(Date checkOutDate) {
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
