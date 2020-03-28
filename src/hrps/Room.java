package hrps;

import java.util.ArrayList;

public class Room {
	
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
	
	private int id;
	private RoomType roomType;
	private float rate;
	private BedType bedType;
	private AvailabilityStatus availabilityStatus;
	private boolean wifiEnabled;
	private String facing;
	private boolean smokingAllowed;
	private ArrayList<RoomService> roomService;
	private int roomLevel, roomNumber;
	
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
		this.roomService = new ArrayList<RoomService>();
	}
	
	public AvailabilityStatus checkRoomAvailability() {
		return this.getAvailabilityStatus();
	}
	
	public void updateRoomAvailability(AvailabilityStatus availStatus) {
		this.setAvailabilityStatus(availStatus);
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public RoomType getRoomType() {
		return roomType;
	}
	public void setRoomType(RoomType roomType) {
		this.roomType = roomType;
	}
	public float getRate() {
		return rate;
	}
	public void setRate(float rate) {
		this.rate = rate;
	}
	public BedType getBedType() {
		return bedType;
	}
	public void setBedType(BedType bedType) {
		this.bedType = bedType;
	}
	public AvailabilityStatus getAvailabilityStatus() {
		return availabilityStatus;
	}
	public void setAvailabilityStatus(AvailabilityStatus availabilityStatus) {
		this.availabilityStatus = availabilityStatus;
	}
	public boolean isWifiEnabled() {
		return wifiEnabled;
	}
	public void setWifiEnabled(boolean wifiEnabled) {
		this.wifiEnabled = wifiEnabled;
	}
	public String getFacing() {
		return facing;
	}
	public void setFacing(String facing) {
		this.facing = facing;
	}
	public boolean getSmokingAllowed() {
		return smokingAllowed;
	}
	public void setSmokingAllowed(boolean smokingAllowed) {
		this.smokingAllowed = smokingAllowed;
	}

	public int getRoomLevel() {
		return roomLevel;
	}

	public void setRoomLevel(int roomLevel) {
		this.roomLevel = roomLevel;
	}

	public int getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}
	
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
}
