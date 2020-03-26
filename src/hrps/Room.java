package hrps;

public class Room {
	
	enum RoomType{
		SINGLE,
		STANDARD,
		VIP_SUITE,
		DELUXE
	}

	enum AvailabilityStatus{
		VACANT,
		OCCUPIED,
		RESERVED,
		MAINTENANCE
	}
	
	enum BedType{
		SINGLE,
		DOUBLE,
		MASTER
	}
	
	
	private String id;
	private RoomType roomType;
	private float rate;
	private String bedType;
	private AvailabilityStatus availabilityStatus;
	private boolean wifiEnabled;
	private String facing;
	private boolean smokingAllowed;
	private RoomService[] roomService;
	
	public Room(String id, RoomType roomType,float rate, String bedType, AvailabilityStatus availabilityStatus, boolean wifiEnabled, String facing, boolean smokingAllowed, RoomService[] roomService) {
		this.id = id;
		this.roomType = roomType;
		this.rate = rate;
		this.bedType = bedType;
		this.availabilityStatus = availabilityStatus;
		this.wifiEnabled = wifiEnabled;
		this.facing = facing;
		this.smokingAllowed = smokingAllowed;
		this.roomService = roomService;
		
	}
	
	public AvailabilityStatus checkRoomAvailability() {
		return this.getAvailabilityStatus();
	}
	
	public void updateRoomAvailability(AvailabilityStatus availStatus) {
		this.setAvailabilityStatus(availStatus);
	}
	
	public float getRoomServicePrice() {
		float total = 0;
		for(int i = 0; i < this.roomService.length; i++ ) {
			//add up the costs of each room service
			total += this.roomService[i].getMenuItemsTotalCost();
		}
		
		return total;
	}
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
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
	public String getBedType() {
		return bedType;
	}
	public void setBedType(String bedType) {
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
	public void setSmokingAllowd(boolean smokingAllowed) {
		this.smokingAllowed = smokingAllowed;
	}
	public RoomService[] getRoomService() {
		return roomService;
	}
	public void setRoomService(RoomService[] roomService) {
		this.roomService = roomService;
	}
	
}
