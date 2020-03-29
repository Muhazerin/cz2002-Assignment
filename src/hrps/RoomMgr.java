package hrps;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class RoomMgr {
	private ArrayList<Room> roomList = new ArrayList<Room>();
	private int counter = 1;
	
	// Initialize 48 rooms in the constructor
	public RoomMgr() {
		int roomNumber = 1;
		for (int i = 0; i < 48; i++) {
			if (i == 6 || i == 12 || i == 18 || i == 24 || i == 36) {
				roomNumber = 1;
			}
			if (i < 12) {
				if (i < 6) {
					roomList.add(new Room(counter, Room.RoomType.SINGLE, (float)1.00, Room.BedType.SINGLE, Room.AvailabilityStatus.VACANT, true, "nice view", false, 2, roomNumber));
					roomNumber++;
				}
				else {
					roomList.add(new Room(counter, Room.RoomType.SINGLE, (float)2.00, Room.BedType.DOUBLE, Room.AvailabilityStatus.VACANT, true, "nice view", false, 3, roomNumber));
					roomNumber++;
				}
			}
			else if (i >= 12 && i < 24){
				if (i < 18) {
					roomList.add(new Room(counter, Room.RoomType.STANDARD, (float)3.00, Room.BedType.SINGLE, Room.AvailabilityStatus.VACANT, true, "nice view", false, 4, roomNumber));
					roomNumber++;
				}
				else {
					roomList.add(new Room(counter, Room.RoomType.STANDARD, (float)4.00, Room.BedType.DOUBLE, Room.AvailabilityStatus.VACANT, true, "nice view", false, 5, roomNumber));
					roomNumber++;
				}
			}
			else if (i >= 24 && i < 36) {
				roomList.add(new Room(counter, Room.RoomType.VIP_SUITE, (float)5.00, Room.BedType.MASTER, Room.AvailabilityStatus.VACANT, true, "nice view", false, 6, roomNumber));
				roomNumber++;
			}
			else {
				roomList.add(new Room(counter, Room.RoomType.DELUXE, (float)6.00, Room.BedType.MASTER, Room.AvailabilityStatus.VACANT, true, "nice view", false, 7, roomNumber));
				roomNumber++;
			}
			counter++;
		}
	}
	
	private static float determineRate(Room.RoomType rType, Room.BedType bType) {
		if (rType.equals(Room.RoomType.SINGLE)) {
			if (bType.equals(Room.BedType.SINGLE)) {
				return 1;
			}
			else {
				return 2;
			}
		}
		else if (rType.equals(Room.RoomType.STANDARD)) {
			if (bType.equals(Room.BedType.SINGLE)) {
				return 3;
			}
			else {
				return 4;
			}
		}
		else if (rType.equals(Room.RoomType.VIP_SUITE)) {
			return 5;
		}
		else {
			return 6;
		}
	}
	
	private static void roomTypeMenu() {
		System.out.println("\n+-------------------+");
		System.out.println("| Select room type: |");
		System.out.println("| 1. Single         |");
		System.out.println("| 2. Standard       |");
		System.out.println("| 3. VIP Suite      |");
		System.out.println("| 4. Deluxe         |");
		System.out.println("+-------------------+");
		System.out.print("Enter choice: ");
	}
	
	private static Room.RoomType selectRoomType() {
		int rtChoice = -1;
		Scanner sc = new Scanner(System.in);
		Room.RoomType rt = null;
		do {
			roomTypeMenu();
			rtChoice = sc.nextInt();
			sc.nextLine();	// clear the "\n" in the buffer
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
	
	private static void bedTypeMenu() {
		System.out.println("\n+------------------+");
		System.out.println("| Select bed type: |");
		System.out.println("| 1. Single        |");
		System.out.println("| 2. Double        |");
		System.out.println("| 3. Master        |");
		System.out.println("+------------------+");
		System.out.print("Enter choice: ");
	}

	private static Room.BedType selectBedType() {
		int btChoice = -1;
		Scanner sc = new Scanner(System.in);
		Room.BedType bt = null;
		do {
			bedTypeMenu();
			btChoice = sc.nextInt();
			sc.nextLine();	// clear the "\n" in the buffer
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
	
	private static void availStatusMenu() {
		System.out.println("\n+-----------------------------+");
		System.out.println("| Select availability status: |");
		System.out.println("| 1. Vacant                   |");
		System.out.println("| 2. Occupied                 |");
		System.out.println("| 3. Reserved                 |");
		System.out.println("| 4. Maintenance              |");
		System.out.println("+-----------------------------+");
		System.out.print("Enter choice: ");
	}
	
	private static Room.AvailabilityStatus selectAvailStatus() {
		int asChoice = -1;
		Scanner sc = new Scanner(System.in);
		Room.AvailabilityStatus as = null;
		
		do {
			availStatusMenu();
			asChoice = sc.nextInt();
			sc.nextLine();	// clear the "\n" in the buffer
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
	
	private static void wifiMenu() {
		System.out.println("\n+----------------+");
		System.out.println("| Is there wifi: |");
		System.out.println("| 1. Yes         |");
		System.out.println("| 2. No          |");
		System.out.println("+----------------+");
		System.out.print("Enter choice: ");
	}
	
	private static boolean selectWifiOption() {
		int wChoice = -1;
		Scanner sc = new Scanner(System.in);
		boolean wifiEnabled = false;
		
		do {
			wifiMenu();
			wChoice = sc.nextInt();
			sc.nextLine();	// clear the "\n" in the buffer
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
	
	private static void smokingMenu() {
		System.out.println("\n+---------------------+");
		System.out.println("| Is smoking allowed: |");
		System.out.println("| 1. Yes              |");
		System.out.println("| 2. No               |");
		System.out.println("+---------------------+");
		System.out.print("Enter choice: ");
	}
	
	private static boolean selectSmokingOption() {
		int sChoice = -1;
		Scanner sc = new Scanner(System.in);
		boolean smokingAllowed = false;
		
		do {
			smokingMenu();
			sChoice = sc.nextInt();
			sc.nextLine();	// clear the "\n" in the buffer
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
	
	private static void updateRoomMenu() {
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

	private static void printRoomDetails(Room r) {
		System.out.println("Room id: " + r.getId());
		System.out.println("Room: " + String.format("%02d-%02d", r.getRoomLevel(), r.getRoomNumber()));
		System.out.println("Room type: " + r.getRoomType().toString());
		System.out.println("Bed Type: " + r.getBedType().toString());
		System.out.println("Room rate: " + r.getRate());
		System.out.println("Availability Status: " + r.getAvailabilityStatus().toString());
		System.out.println("Wifi Enabled: " + boolToString(r.isWifiEnabled()));
		System.out.println("Smoking Allowed: " + boolToString(r.getSmokingAllowed()));
		System.out.println("Facing: " + r.getFacing());
	}
	
	private static String boolToString(boolean bool) {
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
		Scanner sc = new Scanner(System.in);
		int wChoice = -1, sChoice = -1;
		Room.RoomType rt = null;
		Room.BedType bt = null;
		Room.AvailabilityStatus as = null;
		boolean wifiEnabled = false, smokingAllowed = false, valid = false;
		int rLevel = -1, rNumber = -1;
		Room r = null;
		
		rt = selectRoomType();
		bt = selectBedType();
		float rate = determineRate(rt, bt);
		as = selectAvailStatus();
		wifiEnabled = selectWifiOption();
		smokingAllowed = selectSmokingOption();
		System.out.print("Facing: ");
		String facing = sc.nextLine();
		
		while(!valid) {
			System.out.print("Enter room level: ");
			rLevel = sc.nextInt();
			sc.nextLine();	// clear the "\n" in the buffer
			if (rLevel > 1 && rLevel < 8) {
				System.out.print("Enter room number: ");
				rNumber = sc.nextInt();
				sc.nextLine();	// clear the "\n" in the buffer
				r = validateRoomNumber(rLevel, rNumber);
				if (r.equals(null)) {
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
		
		roomList.add(new Room(counter, rt, rate, bt, as, wifiEnabled, facing, smokingAllowed, rLevel, rNumber));
		counter++;
	}
	
	public void updateRoom(String room) {
		int uChoice = -1;
		float rate = 0;
		Scanner sc = new Scanner(System.in);
		String[] parts = room.split("-");
		Room r = validateRoomNumber(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
		if (Objects.equals(r, null)) {
			System.out.println("Invalid room");
		}
		else {
			do {
				updateRoomMenu();
				uChoice = sc.nextInt();
				sc.nextLine();	// clear the "\n" in the buffer
				switch (uChoice) {
					case 0:
						System.out.println("\nThe new room details are:");
						printRoomDetails(r);
						break;
					case 1:
						r.setRoomType(selectRoomType());
						System.out.println("New room type set");
						break;
					case 2:
						r.setBedType(selectBedType());
						System.out.println("New bed type set");
						break;
					case 3:
						System.out.print("Enter rate: ");
						rate = sc.nextFloat();
						sc.nextLine();	// clear the "\n" in the buffer
						r.setRate(rate);
						System.out.println("New room rate set");
						break;
					case 4:
						r.setAvailabilityStatus(selectAvailStatus());
						System.out.println("New availability status set");
						break;
					case 5:
						System.out.print("Enter facing: ");
						r.setFacing(sc.nextLine());
						System.out.println("New facing set");
						break;
					case 6:
						r.setWifiEnabled(selectWifiOption());
						System.out.println("New wifi option set");
						break;
					case 7:
						r.setSmokingAllowed(selectSmokingOption());
						System.out.println("New smoking option set");
						break;
					case 8:
						r.setRoomType(selectRoomType());
						r.setBedType(selectBedType());
						System.out.print("Enter rate: ");
						rate = sc.nextFloat();
						sc.nextLine();	// clear the "\n" in the buffer
						r.setRate(rate);
						r.setAvailabilityStatus(selectAvailStatus());
						System.out.print("Enter facing: ");
						r.setFacing(sc.nextLine());
						System.out.println("New facing set");
						r.setWifiEnabled(selectWifiOption());
						r.setSmokingAllowed(selectSmokingOption());
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
	public Room isVacant(String room, boolean walkIn) {
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
			else {
				if (walkIn) {
					r.setAvailabilityStatus(Room.AvailabilityStatus.OCCUPIED);
				}
				else {
					r.setAvailabilityStatus(Room.AvailabilityStatus.RESERVED);
				}
			}
		}
		return r;
	}
}
