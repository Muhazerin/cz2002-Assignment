package hrps;

import java.io.Serializable;

public class Room implements Serializable{
	
	public static enum RoomType{
		SINGLE,
		STANDARD,
		VIP_SUITE,
		DELUXE
	}

	public static enum AvailabilityStatus{
		VACANT,
		OCCUPIED,
		RESERVED,
		MAINTENANCE
	}
	
	public static enum BedType{
		SINGLE,
		DOUBLE,
		MASTER
	}
	
	private static final long serialVersionUID = 1234L;
	private int id, roomLevel, roomNumber;
	private RoomType roomType;
	private float rate;
	private BedType bedType;
	private AvailabilityStatus availabilityStatus;
	private boolean wifiEnabled, smokingAllowed;
	private String facing;
	
	/*
	 * Constructor for Room class
	 */
	public Room(int id, RoomType roomType,float rate, BedType bedType, AvailabilityStatus availabilityStatus, boolean wifiEnabled, String facing, boolean smokingAllowed, int roomLevel, int roomNumber) {
		this.id = id;
		this.roomType = roomType;
		this.rate = rate;
		this.bedType = bedType;
		this.availabilityStatus = availabilityStatus;
		this.wifiEnabled = wifiEnabled;
		this.facing = facing;
		this.smokingAllowed = smokingAllowed;
		this.roomLevel = roomLevel;
		this.roomNumber = roomNumber;
	}
	
	/*
	 * This method returns the room id
	 */
	public int getId() {
		return id;
	}
	
	/*
	 * This method sets the room id
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/*
	 * This method returns the room type
	 */
	public RoomType getRoomType() {
		return roomType;
	}
	
	/*
	 * This method sets the room type
	 */
	public void setRoomType(RoomType roomType) {
		this.roomType = roomType;
	}
	
	/*
	 * This method returns the rate of the room
	 */
	public float getRate() {
		return rate;
	}
	
	/*
	 * This method sets the rate of the room
	 */
	public void setRate(float rate) {
		this.rate = rate;
	}
	
	/*
	 * This method returns the bed type
	 */
	public BedType getBedType() {
		return bedType;
	}
	
	/*
	 * This method sets the bed type
	 */
	public void setBedType(BedType bedType) {
		this.bedType = bedType;
	}
	
	/*
	 * This method returns the availability status
	 */
	public AvailabilityStatus getAvailabilityStatus() {
		return availabilityStatus;
	}
	
	/*
	 * This method sets the availability status
	 */
	public void setAvailabilityStatus(AvailabilityStatus availabilityStatus) {
		this.availabilityStatus = availabilityStatus;
	}
	
	/*
	 * This method returns a boolean answering whether wifi is enabled
	 * Returns true if enabled
	 * Returns false if not enabled
	 */
	public boolean isWifiEnabled() {
		return wifiEnabled;
	}
	
	/*
	 * This method sets whether the room has wifi enabled
	 */
	public void setWifiEnabled(boolean wifiEnabled) {
		this.wifiEnabled = wifiEnabled;
	}
	
	/*
	 * This method returns the view of the room
	 */
	public String getFacing() {
		return facing;
	}
	
	/*
	 * This method sets the view of the room
	 */
	public void setFacing(String facing) {
		this.facing = facing;
	}
	
	
	
	/* 
	 * This method returns a boolean whether smoking is allowed in the room
	 */
	public boolean getSmokingAllowed() {
		return smokingAllowed;
	}
	
	
	/*
	 * This method sets whether smoking is allowed in the room
	 */
	public void setSmokingAllowed(boolean smokingAllowed) {
		this.smokingAllowed = smokingAllowed;
	}

	/*
	 * This method returns the room level
	 */
	public int getRoomLevel() {
		return roomLevel;
	}

	/*
	 * This method sets the room level
	 */
	public void setRoomLevel(int roomLevel) {
		this.roomLevel = roomLevel;
	}

	/*
	 * This method returns the room number
	 */
	public int getRoomNumber() {
		return roomNumber;
	}

	/*
	 * This method sets the room number
	 */
	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}
	
	/*
	public void addRoomService(RoomService rs) {
		this.roomService.add(rs);
	}
	
	public ArrayList<RoomService> getRoomService() {
		return this.roomService;
	}
	
	public float getRoomServicePrice() {
		float total = 0;
		for(int i = 0; i < this.roomService.size(); i++ ) {
			//add up the costs of each room service
			total += this.roomService.get(i).getMenuItemsTotalCost();
		}
		return total;
	}
	
	public void clearRoomService() {
		this.roomService.clear();
	}
	*/
}
