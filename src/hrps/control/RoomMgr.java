package control;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

import entity.Room;

public class RoomMgr {
	private ArrayList<Room> roomList;
	private int counter = 1;
	private FileIO fileIO = new FileIO();
	private Scanner sc;
	
	/*
	 * Constructor for RoomMgr class
	 * Contains some initialization
	 * Can re-create the 48 rooms
	 * Can retrieve the rooms from the file
	 */
	public RoomMgr(Scanner sc) {
		//System.out.println("RoomMgr");
		
		roomList = new ArrayList<Room>();
		this.sc = sc;
		
		//re-creates the rooms and writes it back to the file
		Object[] objArray = fileIO.readObject(Room.class);
		
		if(objArray.length == 0) {
			int roomNumber = 1;
			 for (int i = 0; i < 48; i++) {
				if (i == 6 || i == 12 || i == 18 || i == 24 || i == 36) {
					roomNumber = 1;
				}
				if (i < 12) {
					if (i < 6) {
						roomList.add(new Room(counter, Room.RoomType.SINGLE, (float)50.00, Room.BedType.SINGLE, Room.AvailabilityStatus.VACANT, true, "nice view", false, 2, roomNumber));
						roomNumber++;
					}
					else {
						roomList.add(new Room(counter, Room.RoomType.SINGLE, (float)75.00, Room.BedType.DOUBLE, Room.AvailabilityStatus.VACANT, true, "nice view", false, 3, roomNumber));
						roomNumber++;
					}
				}
				else if (i >= 12 && i < 24){
					if (i < 18) {
						roomList.add(new Room(counter, Room.RoomType.STANDARD, (float)100.00, Room.BedType.SINGLE, Room.AvailabilityStatus.VACANT, true, "nice view", false, 4, roomNumber));
						roomNumber++;
					}
					else {
						roomList.add(new Room(counter, Room.RoomType.STANDARD, (float)125.00, Room.BedType.DOUBLE, Room.AvailabilityStatus.VACANT, true, "nice view", false, 5, roomNumber));
						roomNumber++;
					}
				}
				else if (i >= 24 && i < 36) {
					roomList.add(new Room(counter, Room.RoomType.VIP_SUITE, (float)150.00, Room.BedType.MASTER, Room.AvailabilityStatus.VACANT, true, "nice view", false, 6, roomNumber));
					roomNumber++;
				}
				else {
					roomList.add(new Room(counter, Room.RoomType.DELUXE, (float)175.00, Room.BedType.MASTER, Room.AvailabilityStatus.VACANT, true, "nice view", false, 7, roomNumber));
					roomNumber++;
				}
				counter++;
			}
			fileIO.writeObject(roomList.toArray(), Room.class); 
		}else {
			for (Object o : objArray) {
				Room r = (Room) o;
				roomList.add(r);
			}
			counter = roomList.size() + 1;
		}

	}
	
	/*
	 * This method contains basic logic to auto set rate based on room type and bed type
	 */
	private float determineRate(Room.RoomType rType, Room.BedType bType) {
		if (rType.equals(Room.RoomType.SINGLE)) {
			if (bType.equals(Room.BedType.SINGLE)) {
				return 50;
			}
			else {
				return 75;
			}
		}
		else if (rType.equals(Room.RoomType.STANDARD)) {
			if (bType.equals(Room.BedType.SINGLE)) {
				return 100;
			}
			else {
				return 125;
			}
		}
		else if (rType.equals(Room.RoomType.VIP_SUITE)) {
			return 150;
		}
		else {
			return 175;
		}
	}
	
	/*
	 * This method contains the menu for updating room type
	 */
	private void roomTypeMenu() {
		System.out.println("\n+-------------------+");
		System.out.println("| Select room type: |");
		System.out.println("| 1. Single         |");
		System.out.println("| 2. Standard       |");
		System.out.println("| 3. VIP Suite      |");
		System.out.println("| 4. Deluxe         |");
		System.out.println("+-------------------+");
		System.out.print("Enter choice: ");
	}
	
	/*
	 * This method returns the room type based on user's input
	 */
	private Room.RoomType selectRoomType(Scanner sc) {
		int rtChoice = -1;
		Room.RoomType rt = null;
		do {
			roomTypeMenu();
			rtChoice = validateChoice(rtChoice, "Enter choice: ");
			switch(rtChoice) {
				case 1:
					rt =  Room.RoomType.SINGLE;
					break;
				case 2:
					rt =  Room.RoomType.STANDARD;
					break;
				case 3:
					rt =  Room.RoomType.VIP_SUITE;
					break;
				case 4:
					rt =  Room.RoomType.DELUXE;
					break;
				default:
					System.out.println("Invalid Choice");
					break;
			}
			
		} while (rtChoice != 1 && rtChoice != 2 && rtChoice != 3 && rtChoice != 4);
		return rt;
	}
	
	/*
	 * This method contains the menu for updating bed type
	 */
	private void bedTypeMenu() {
		System.out.println("\n+------------------+");
		System.out.println("| Select bed type: |");
		System.out.println("| 1. Single        |");
		System.out.println("| 2. Double        |");
		System.out.println("| 3. Master        |");
		System.out.println("+------------------+");
		System.out.print("Enter choice: ");
	}

	/*
	 * This method returns the bed type based on user's input
	 */
	private Room.BedType selectBedType(Scanner sc) {
		int btChoice = -1;
		Room.BedType bt = null;
		do {
			bedTypeMenu();
			btChoice = validateChoice(btChoice, "Enter choice: ");
			switch(btChoice) {
				case 1:
					bt = Room.BedType.SINGLE;
					break;
				case 2:
					bt = Room.BedType.DOUBLE;
					break;
				case 3:
					bt = Room.BedType.MASTER;
					break;
				default:
					System.out.println("Invalid Choice");
					break;
			}
		} while (btChoice != 1 && btChoice != 2 && btChoice != 3);
		return bt;
	}
	
	/*
	 * This method contains the menu for updating the availability status
	 */
	private void availStatusMenu() {
		System.out.println("\n+-----------------------------+");
		System.out.println("| Select availability status: |");
		System.out.println("| 1. Vacant                   |");
		System.out.println("| 2. Occupied                 |");
		System.out.println("| 3. Reserved                 |");
		System.out.println("| 4. Maintenance              |");
		System.out.println("+-----------------------------+");
		System.out.print("Enter choice: ");
	}
	
	/*
	 * This method returns the availability status based on user's input
	 */
	private Room.AvailabilityStatus selectAvailStatus(Scanner sc) {
		int asChoice = -1;
		Room.AvailabilityStatus as = null;
		
		do {
			availStatusMenu();
			asChoice = validateChoice(asChoice, "Enter choice: ");
			switch(asChoice) {
				case 1:
					as = Room.AvailabilityStatus.VACANT;
					break;
				case 2:
					as = Room.AvailabilityStatus.OCCUPIED;
					break;
				case 3:
					as = Room.AvailabilityStatus.RESERVED;
					break;
				case 4:
					as = Room.AvailabilityStatus.MAINTENANCE;
					break;
				default:
					System.out.println("Invalid Choice");
					break;
			}
			
		} while (asChoice != 1 && asChoice != 2 && asChoice != 3 && asChoice != 4);
		return as;
	}
	
	/*
	 * This method contains the menu for updating whether wifi is enabled
	 */
	private void wifiMenu() {
		System.out.println("\n+----------------+");
		System.out.println("| Is there wifi: |");
		System.out.println("| 1. Yes         |");
		System.out.println("| 2. No          |");
		System.out.println("+----------------+");
		System.out.print("Enter choice: ");
	}
	
	/*
	 * This method returns a boolean whether wifi is enabled based on user's input
	 * Returns true if wifi is enabled
	 * Returns false if wifi is not enabled
	 */
	private boolean selectWifiOption(Scanner sc) {
		int wChoice = -1;
		boolean wifiEnabled = false;
		
		do {
			wifiMenu();
			wChoice = validateChoice(wChoice, "Enter choice: ");
			switch(wChoice) {
				case 1:
					wifiEnabled = true;
					break;
				case 2:
					wifiEnabled = false;
					break;
				default:
					System.out.println("Invalid Choice");
					break;
			}
		} while (wChoice != 1 && wChoice != 2);
		return wifiEnabled;
	}
	
	/*
	 * This method contains the menu for updating whether smoking is allowed
	 */
	private void smokingMenu() {
		System.out.println("\n+---------------------+");
		System.out.println("| Is smoking allowed: |");
		System.out.println("| 1. Yes              |");
		System.out.println("| 2. No               |");
		System.out.println("+---------------------+");
		System.out.print("Enter choice: ");
	}
	
	/*
	 * This method returns a boolean whether smoking is allowed based on user's input
	 * Returns true if smoking is allowed
	 * Returns false if smoking is not allowed
	 */
	private boolean selectSmokingOption(Scanner sc) {
		int sChoice = -1;
		boolean smokingAllowed = false;
		
		do {
			smokingMenu();
			sChoice = validateChoice(sChoice, "Enter choice: ");
			switch(sChoice) {
				case 1:
					smokingAllowed = true;
					break;
				case 2:
					smokingAllowed = false;
					break;
				default:
					System.out.println("Invalid Choice");
					break;
			}
		} while (sChoice != 1 && sChoice != 2);
		return smokingAllowed;
	}
	
	/*
	 * This method contains the menu for updating the room
	 */
	private void updateRoomMenu() {
		System.out.println("\n+--------------------------------+");
		System.out.println("| What would you like to update: |");
		System.out.println("| 0. Nothing                     |");
		System.out.println("| 1. Room type                   |");
		System.out.println("| 2. Bed type                    |");
		System.out.println("| 3. Room rate                   |");
		System.out.println("| 4. Availability status         |");
		System.out.println("| 5. Facing                      |");
		System.out.println("| 6. Is wifi enabled?            |");
		System.out.println("| 7. Is smoking allowed?         |");
		System.out.println("| 8. All                         |");
		System.out.println("+--------------------------------+");
		System.out.print("Enter choice: ");
	}

	/*
	 * This method prints the room details
	 */
	public void printRoomDetails(Room r) {
		System.out.println("\n+----------Room Details------------+");
		System.out.println("Room ID: " + r.getId());
		System.out.println("Room number: " + String.format("%02d-%02d", r.getRoomLevel(), r.getRoomNumber()));
		System.out.println("Room type: " + r.getRoomType().toString());
		System.out.println("Bed Type: " + r.getBedType().toString());
		System.out.println("Room rate: $" + r.getRate());
		System.out.println("Availability Status: " + r.getAvailabilityStatus().toString());
		System.out.println("Wifi Enabled: " + boolToString(r.isWifiEnabled()));
		System.out.println("Smoking Allowed: " + boolToString(r.getSmokingAllowed()));
		System.out.println("Facing: " + r.getFacing());
	}
	
	/*
	 * This method returns a String based on the boolean
	 * Returns Yes if true
	 * Returns No if false
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
	 * return the room if room is found
	 * return null if room is not found
	 */
	private Room validateRoomNumber(int rL, int rN) {
		Room room = null;
		for (Room r : roomList) {
			if (r.getRoomLevel() ==  rL && r.getRoomNumber() == rN) {
				room = r;
				break;
			}
		}
		return room;
	}
	
	public void addRoom() {
		Room.RoomType rt = null;
		Room.BedType bt = null;
		Room.AvailabilityStatus as = null;
		boolean wifiEnabled = false, smokingAllowed = false, valid = false;
		int rLevel = -1, rNumber = -1;
		Room r = null;
		
		rt = selectRoomType(sc);
		bt = selectBedType(sc);
		float rate = determineRate(rt, bt);
		as = selectAvailStatus(sc);
		wifiEnabled = selectWifiOption(sc);
		smokingAllowed = selectSmokingOption(sc);
		System.out.print("Facing: ");
		String facing = sc.nextLine();
		
		while(!valid) {
			System.out.print("Enter room level: ");
			rLevel = validateChoice(rLevel, "Enter room level: ");
			if (rLevel > 1 && rLevel < 8) {
				System.out.print("Enter room number: ");
				rNumber = validateChoice(rNumber, "Enter room number: ");
				r = validateRoomNumber(rLevel, rNumber);
				if (r == null) {
					valid = true;
				}
				else {
					System.out.println("Invalid room number");
				}
			}
			else {
				System.out.println("Invalid room level");
			}
		}

		Room newRoom = new Room(counter, rt, rate, bt, as, wifiEnabled, facing, smokingAllowed, rLevel, rNumber);
		roomList.add(newRoom);
		System.out.println("New room "+ newRoom.getRoomLevel()+"-"+newRoom.getRoomNumber()+" has been created.");
		fileIO.writeObject(roomList.toArray(), Room.class);
		counter++;
	}
	
	private boolean isStringInt(String s)
	{
	    try
	    {
	        Integer.parseInt(s);
	        return true;
	    } catch (NumberFormatException ex)
	    {
	        return false;
	    }
	}
	
	public void updateRoom(String room) {
		int uChoice = -1;
		float rate = 0;
		String[] parts = room.split("-");
		if(!isStringInt(parts[0]) || !isStringInt(parts[1])){
			System.out.println("Invalid format");
			return;
		}
		Room r = validateRoomNumber(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
		if (Objects.equals(r, null)) {
			System.out.println("Invalid room");
		}
		else {
			do {
				updateRoomMenu();
				uChoice = validateChoice(uChoice, "Enter choice: ");
				switch (uChoice) {
					case 0:
						System.out.println("The new room details are:");
						printRoomDetails(r);
						break;
					case 1:
						r.setRoomType(selectRoomType(sc));
						System.out.println("New room type set");
						fileIO.writeObject(roomList.toArray(), Room.class);
						break;
					case 2:
						r.setBedType(selectBedType(sc));
						System.out.println("New bed type set");
						fileIO.writeObject(roomList.toArray(), Room.class);
						break;
					case 3:
						System.out.print("Enter rate: ");
						rate = validateRate(rate, "Enter rate: ");
						r.setRate(rate);
						System.out.println("New room rate set");
						fileIO.writeObject(roomList.toArray(), Room.class);
						break;
					case 4:
						r.setAvailabilityStatus(selectAvailStatus(sc));
						System.out.println("New availability status set");
						fileIO.writeObject(roomList.toArray(), Room.class);
						break;
					case 5:
						System.out.print("Enter facing: ");
						r.setFacing(sc.nextLine());
						System.out.println("New facing set");
						fileIO.writeObject(roomList.toArray(), Room.class);
						break;
					case 6:
						r.setWifiEnabled(selectWifiOption(sc));
						System.out.println("New wifi option set");
						fileIO.writeObject(roomList.toArray(), Room.class);
						break;
					case 7:
						r.setSmokingAllowed(selectSmokingOption(sc));
						System.out.println("New smoking option set");
						fileIO.writeObject(roomList.toArray(), Room.class);
						break;
					case 8:
						r.setRoomType(selectRoomType(sc));
						r.setBedType(selectBedType(sc));
						System.out.print("Enter rate: ");
						rate = validateRate(rate, "Enter rate: ");
						r.setRate(rate);
						r.setAvailabilityStatus(selectAvailStatus(sc));
						System.out.print("Enter facing: ");
						r.setFacing(sc.nextLine());
						System.out.println("New facing set");
						r.setWifiEnabled(selectWifiOption(sc));
						r.setSmokingAllowed(selectSmokingOption(sc));
						fileIO.writeObject(roomList.toArray(), Room.class);
						System.out.println("Room updated\n\nThe new room details are:");
						printRoomDetails(r);
						break;
					default:
						System.out.println("Invalid choice");
						break;
				}
			} while (uChoice != 0 && uChoice != 8);
		}
	}
	
	public void listRoomsByOccupancyRate() {
		ArrayList<String> vacantRoom = new ArrayList<String>();
		int totalRooms = 0;
		for (Room.RoomType rt : Room.RoomType.values()) {
			totalRooms = 0;
			vacantRoom.clear();
			System.out.print(rt.toString() + ": ");
			for (Room r : roomList) {
				if (rt.equals(r.getRoomType())) {
					if (r.getAvailabilityStatus().equals(Room.AvailabilityStatus.VACANT)) {
						vacantRoom.add(String.format("%02d-%02d", r.getRoomLevel(), r.getRoomNumber()));
					}
					totalRooms++;
				}
			}
			System.out.printf("\tNumber: %d out of %d\n", vacantRoom.size(), totalRooms);
			System.out.print("\t\tRooms: ");
			for (String s : vacantRoom) {
				System.out.print(s + ", ");
			}
			System.out.println("");
		}
	}
	
	public void listRoomsByRoomStatus() {
		for (Room.AvailabilityStatus as : Room.AvailabilityStatus.values()) {
			System.out.println(as.toString() + " : ");
			System.out.print("\tRooms : ");
			for (Room r : roomList) {
				if (as.equals(r.getAvailabilityStatus())) {
					System.out.printf("%s, ", String.format("%02d-%02d", r.getRoomLevel(), r.getRoomNumber()));
				}
			}
			System.out.println("");
		}
	}
	
	/*
	 * return the room if vacant
	 * return false if room not found or not vacant
	 */
	public Room isVacant(String room) {
		String[] parts = room.split("-");
		Room r = validateRoomNumber(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
		if (Objects.equals(r, null)) {
			System.out.println("Invalid room");
		}
		else {
			if (!r.getAvailabilityStatus().equals(Room.AvailabilityStatus.VACANT) ) {
				r = null;
				System.out.println("Room is not vacant");
			}
		}
		return r;
	}	

	/*
	 * This method changes the availability status of 2 rooms
	 * newRoom gets oldRoom status
	 * oldRoom status = Vacant
	 * Used when guest changes room
	 */
	public void changeRoom(Room oldRoom, Room newRoom) {
		newRoom.setAvailabilityStatus(oldRoom.getAvailabilityStatus());
		oldRoom.setAvailabilityStatus(Room.AvailabilityStatus.VACANT);
	}

	/*
	 * This method returns the room based on the room id
	 * Used in ReservationMgr to set the room object
	 */
	public Room getRoomById(int id) {
		Room r = null;
		
		for (Room temp : roomList) {
			if (temp.getId() == id) {
				r = temp;
				break;
			}
		}
		return r;
	}
	
	public void getRoomDetailsByNumber(String roomNo) {
		String[] parts = roomNo.split("-");
		Room r = validateRoomNumber(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
		if(r == null) {
			System.out.println("Invalid Room Number.");
		}else {
			printRoomDetails(r);
		}
	}

	
	/*
	 * This method writes the roomList to file
	 * Mainly used when other class needs room to update the file
	 */
	public void writeToFile() {
		fileIO.writeObject(roomList.toArray(), Room.class);
	}

	/*
	 * This method is used to ensure that user enters an integer
	 */
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

	/*
	 * This method is used to ensure that user enters a proper rate
	 */
	private float validateRate(float rate, String inputText) {
		boolean valid = false;
		
		while (!valid) {
			if (!sc.hasNextFloat()) {
				System.out.println("Invalid rate. Please enter a rate");
				sc.nextLine();	// clear the input in the buffer
				System.out.print(inputText);
			}
			else {
				valid = true;
				rate = sc.nextFloat();
				sc.nextLine();	// clear the "\n" in the buffer
			}
		}
		
		return rate;
	}
}
