package hrps;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

import java.io.Serializable;

public class Reservation implements Serializable{
	public static enum ResStatus {
		CONFIRMED, IN_WAITLIST, CHECKED_IN, EXPIRED, CHECKED_OUT;
	}
	
	private static final long serialVersionUID = 1234L;
	private int resCode;
	private Guest guest;
	private Room room;
	private LocalDate checkInDate;
	private LocalDate checkOutDate;
	private int noOfAdults;
	private int noOfChildren;
	private ResStatus status;
	private ArrayList<RoomService> roomServiceList;
	
	/*
	 * Default constructor for reservation
	 */
	public Reservation(int counter) {
		this.resCode = counter;
		this.guest = null;
		this.room = null;
		this.checkInDate = null;
		this.checkOutDate = null;
		this.noOfAdults = 0;
		this.noOfChildren = 0;
		this.status = ResStatus.CONFIRMED;
		this.roomServiceList = new ArrayList<RoomService>();
	}
	
	/*
	 * Returns the reservation code
	 */
	public int getResCode() {
		return resCode;
	}
	
	/*
	 * Return Guest g
	 */
	public Guest getGuest() {
		return guest;
	}
	
	/*
	 * Sets Guest g for a reservation
	 */
	public void setGuest(Guest g) {
		this.guest = g;
	}
	
	/*
	 * Returns Room r
	 */
	public Room getRoom() {
		return room;
	}
	
	/*
	 * Sets Room r for a reservation
	 */
	public void setRoom(Room r) {
		this.room = r;
	}
	
	/* 
	 * Returns LocalDate checkInDate
	 */
	public LocalDate getCheckInDate() {
		return checkInDate;
	}
	
	/*
	 * Sets checkInDate for a reservation
	 */
	public void setCheckInDate(LocalDate checkInDate) {
		this.checkInDate = checkInDate;
	}
	
	/*
	 * Returns LocalDate checkOutDate
	 */
	public LocalDate getCheckOutDate() {
		return checkOutDate;
	}
	
	/*
	 * Sets checkOutDate for a reservation
	 */
	public void setCheckOutDate(LocalDate checkOutDate) {
		this.checkOutDate = checkOutDate;
	}
	
	/*
	 * Returns no of adults
	 */
	public int getNoOfAdults() {
		return noOfAdults;
	}
	
	/*
	 * Sets no of adults for a reservation
	 */
	public void setNoOfAdults(int noOfAdults) {
		this.noOfAdults = noOfAdults;
	}
	
	/*
	 * Returns no of children
	 */
	public int getNoOfChildren() {
		return noOfChildren;
	}
	
	/*
	 * Sets no of children for a reservation
	 */
	public void setNoOfChildren(int noOfChildren) {
		this.noOfChildren = noOfChildren;
	}
	
	/*
	 * Returns the reservation status
	 */
	public ResStatus getStatus() {
		return status;
	}
	
	/*
	 * Sets the reservation status for a reservation
	 */
	public void setStatus(ResStatus status) {
		this.status = status;
	}
	
	/*
	 * This method prints the reservation details
	 */
	public void printReceipt() {
		System.out.println("-------------------------------------Reservation Details-----------------------------------------");
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
		System.out.println("\nYour reservation will expire if you fail to arrive after 1 day of the scheduled check-in date.");
	}
	
	/*
	 * This method checkouts a reservation
	 */
	public void checkOut() {
		System.out.println("Something");
	}
	
	/*
	 * This method return a string depending on the boolean
	 * Returns "Yes" if true
	 * Returns "No" if false
	 */
	private String boolToString(boolean bool) {
		if (bool) {
			return "Yes";
		}
		else {
			return "No";
		}
	}
	
	/*
	 * This method removes a room from reservation and set the room status to vacant
	 */
	public void removeRoom() {
		this.room.setAvailabilityStatus(Room.AvailabilityStatus.VACANT);
		this.room = null;
	}
	
	/*
	 * Print all of the ordered room services
	 */
	public void printRoomServices() {
		if (roomServiceList.size() == 0) {
			System.out.println("No room services");
			return;
		}
		System.out.println("Lists of room services: ");
		for (RoomService rs : roomServiceList) {
			rs.printOrder();
		}
	}
	
	/*
	 * Add room service to roomServiceList
	 */
	public void addRoomService(RoomService rs) {
		System.out.println("");
		rs.printOrder();
		this.roomServiceList.add(rs);
	}
	
	public void addRoomServiceNoPrintOrder(RoomService rs ) {
		this.roomServiceList.add(rs);
	}
	
	public ArrayList<RoomService> getRoomServiceList() {
		return this.roomServiceList;
	}
	
	public void clearRoomServiceList() {
		this.roomServiceList = new ArrayList<RoomService>();
	}

	public double getRoomServicePrice() {
		double total = 0;
		for (RoomService rs : roomServiceList) {
			total += rs.getMenuItemTotalCost();
		}
		return total;
	}

	public RoomService getRoomServiceById(int id) {
		RoomService rs = null;
		
		for (RoomService temp : roomServiceList) {
			if (temp.getId() == id) {
				rs = temp;
				break;
			}
		}
		
		return rs;
	}

	public void removeRoomService(RoomService rs) {
		if (Objects.equals(rs, null)) {
			System.out.println("Room Service does not exist");
		}
		else {
			roomServiceList.remove(rs);
		}
	}
}
