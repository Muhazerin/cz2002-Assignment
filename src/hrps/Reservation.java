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
		System.out.println("\nThis is your reservation details.");
		System.out.println("Reservation Code: " + resCode);
		System.out.println("Reservation Status: " + this.getStatus().toString());
		System.out.println("Check In Date: " + checkInDate.toString());
		System.out.println("No of adults: " + this.getNoOfAdults());
		System.out.println("No of children: " + this.getNoOfChildren());
		System.out.println("\nThis is the guest details.");
		System.out.println("Name: " + guest.getName());
		System.out.println("ID: " + guest.getId());
		System.out.println("Gender: " + guest.getGender());
		System.out.println("Country: " + guest.getCountry());
		System.out.println("Nationality: " + guest.getNationality());
		System.out.println("Contact Number: " + guest.getContact());
		System.out.println("\nThis is the room details.");
		System.out.println("Room: " + String.format("%02d-%02d", room.getRoomLevel(), room.getRoomNumber()));
		System.out.println("Room Type: " + room.getRoomType().toString());
		System.out.println("Bed Type: " + room.getBedType().toString());
		System.out.println("Wifi Enabled: " + boolToString(room.isWifiEnabled()));
		System.out.println("Smoking Allowed: " + boolToString(room.getSmokingAllowed()));
		System.out.println("Room rate: $" + room.getRate() + " per day");
		System.out.println("\nYour reservation will expire if you fail to arrive after 1 hour of the scheduled check-in date.");
	}
	
	public void checkOut() {
		System.out.println("Something");
	}
	
	private static String boolToString(boolean bool) {
		if (bool) {
			return "Yes";
		}
		else {
			return "No";
		}
	}
	
	public void removeRoom() {
		this.room.setAvailabilityStatus(Room.AvailabilityStatus.VACANT);
	}
}
