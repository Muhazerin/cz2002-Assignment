package hrps;

import java.util.ArrayList;
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
	
	private float determineRate(Room.RoomType rType, Room.BedType bType) {
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
	
	private void bedTypeMenu() {
		System.out.println("\n+------------------+");
		System.out.println("| Select bed type: |");
		System.out.println("| 1. Single        |");
		System.out.println("| 2. Double        |");
		System.out.println("| 3. Master        |");
		System.out.println("+------------------+");
		System.out.print("Enter choice: ");
	}
	
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
	
	private void wifiMenu() {
		System.out.println("\n+----------------+");
		System.out.println("| Is there wifi: |");
		System.out.println("| 1. Yes         |");
		System.out.println("| 2. No          |");
		System.out.println("+----------------+");
		System.out.print("Enter choice: ");
	}
	
	private void smokingMenu() {
		System.out.println("\n+---------------------+");
		System.out.println("| Is smoking allowed: |");
		System.out.println("| 1. Yes              |");
		System.out.println("| 2. No               |");
		System.out.println("+---------------------+");
		System.out.print("Enter choice: ");
	}
	
	private boolean validateRoomNumber(int rL, int rN) {
		boolean valid = true;
		for (Room r : roomList) {
			if (r.getRoomLevel() ==  rL && r.getRoomNumber() == rN) {
				valid = false;
				break;
			}
		}
		return valid;
	}
	
	private void listRoomMenu() {
		System.out.println("\n+--------------------------------+");
		System.out.println("| Select display type:           |");
		System.out.println("| 1. List all rooms              |");
		System.out.println("| 2. List Room by occupancy rate |");
		System.out.println("| 3. List Room by room status    |");
		System.out.println("+--------------------------------+");
		System.out.print("Enter choice: ");
	}
	
	public void addRoom() {
		Scanner sc = new Scanner(System.in);
		int rtChoice = -1, btChoice = -1, asChoice = -1, wChoice = -1, sChoice = -1;
		Room.RoomType rt = null;
		Room.BedType bt = null;
		Room.AvailabilityStatus as = null;
		boolean wifiEnabled = false, smokingAllowed = false, valid = false;
		int rLevel = -1, rNumber = -1;
		
		do {
			roomTypeMenu();
			rtChoice = sc.nextInt();
			sc.nextLine();	// clear the "\n" in the buffer
			switch(rtChoice) {
				case 1:
					rt = Room.RoomType.SINGLE;
					break;
				case 2:
					rt = Room.RoomType.STANDARD;
					break;
				case 3:
					rt = Room.RoomType.VIP_SUITE;
					break;
				case 4:
					rt = Room.RoomType.DELUXE;
					break;
				default:
					System.out.println("Invalid Choice");
					break;
			}
			
		} while (rtChoice != 1 && rtChoice != 2 && rtChoice != 3 && rtChoice != 4);
	
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
	
		float rate = determineRate(rt, bt);
		
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
				valid = validateRoomNumber(rLevel, rNumber);
				if (!valid) {
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
	
	public void updateRoom() {
		
	}
	
	public void listRoom() {
		int lChoice = -1;
		Scanner sc = new Scanner(System.in);
		do {
			listRoomMenu();
			lChoice = sc.nextInt();
			sc.nextLine();	// clear the "\n" in the buffer
			switch (lChoice) {
				case 1:
					listAllRooms();
					break;
				case 2:
					listRoomsByOccupancyRate();
					break;
				case 3:
					listRoomsByRoomStatus();
					break;
				default:
					System.out.println("Invalid Choice");
					break;
			}
		} while (lChoice != 1 && lChoice != 2 && lChoice != 3);
	}
	
	private void listAllRooms() {
		System.out.println("\nRoom Details:");
		for (Room r : roomList) {
			System.out.println("Room id: " + r.getId());
			System.out.printf("Room: %s-%s\n", String.format("%02d", r.getRoomLevel()), String.format("%02d", r.getRoomNumber()));
			System.out.println("Room Type: " + r.getRoomType().toString());
			System.out.println("Bed Type: " + r.getBedType().toString());
			System.out.println("Room Rate: " + r.getRate());
			System.out.println("Availability Status: " + r.getAvailabilityStatus().toString());
			System.out.println("Wifi Enabled: " + r.isWifiEnabled());
			System.out.println("Smoking Allowed: " + r.getSmokingAllowed());
			System.out.println("Facing Description: " + r.getFacing());
			System.out.println("");
		}
	}
	
	private void listRoomsByOccupancyRate() {
		System.out.println("Something");
	}
	
	private void listRoomsByRoomStatus() {
		System.out.println("Something");
	}
	
}
