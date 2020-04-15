package hrps;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class HRPSApp {
	
	/*
	 * This is the main method
	 */
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		RoomMgr rMgr = new RoomMgr(sc);
		GuestMgr guestMgr = new GuestMgr(sc);
		MenuItemMgr miMgr = new MenuItemMgr(sc);
		RoomServiceMgr rsMgr = new RoomServiceMgr(sc);
		ReservationMgr resMgr = new ReservationMgr(guestMgr, rMgr, rsMgr, sc);
		
		int choice = -1;
		
		do {
			menu();
			choice = validateChoice(choice, sc);
			switch (choice) {
				case 0:
					System.out.println("Bye Bye!!");
					sc.close();
					break;
				case 1:
					guestOption(guestMgr, sc);
					break;
				case 2:
					roomOption(rMgr, sc);
					break;
				case 3:
					reservationOption(guestMgr, rMgr, resMgr, rsMgr, miMgr, sc);
					break;
				case 4:
					menuOption(miMgr, sc);
					break;
				case 5:
					roomServiceOption(resMgr, rsMgr, miMgr, sc);
					break;
				case 6:
					checkInCheckOutExpireOption(resMgr, sc);
					break;
				default:
					System.out.println("Invalid choice");
					break;
			}
			
		}while (choice != 0);
		
		// TODO Auto-generated method stub

		
		//functions:
//		a. Create/Update/Search guests detail (Search by name using keyword/s)
//		b. Create/Update/Remove/Print reservation
//		c. Create/Update rooms details (include setting status "like Under Maintenance",
//		d. Entering room service orders - list menu items for selection
//		e. Create/Update/Remove room service menu items.
//		f. Check room availability
//		g. Room Check-in (for walk-in or reservation)
//		h. Room Check-out and print bill invoice (with breakdowns on days of stay, room service order items
//		and its total, tax and total amount)
		
		
			
	}
	
	/*
	 * This method contains the menu used in main method
	 */
	private static void menu() {
		System.out.println("\n+-----------------------------------------------------+");
		System.out.println("| Welcome to the Hotel Reservation and Payment System |");
		System.out.println("| What would you like to do?                          |");
		System.out.println("| 0. Exit the program                                 |");
		System.out.println("| 1. Update/Search guest details                      |");
		System.out.println("| 2. Create/Update/List room                          |");
		System.out.println("| 3. Create/Update/Remove/Print reservation           |");
		System.out.println("| 4. Create/Update/Remove/List menu items             |");
		System.out.println("| 5. Create/Update/Remove/Print room service          |");
		System.out.println("| 6. Guest Check In/Check Out/Expire Reservation      |");
		System.out.println("+-----------------------------------------------------+");
		System.out.print("Enter choice: ");
	}

	/*
	 * This method contains the menu for guest options
	 */
	private static void guestMenu() {
		System.out.println("\n+------------------------------------------+");
		System.out.println("| What would you like to do ?              |");
		System.out.println("| 0. Go back                               |");
		System.out.println("| 1. Update guest details                  |");
		System.out.println("| 2. Search for guest and list its details |");
		System.out.println("+------------------------------------------+");
		System.out.print("Enter choice: ");
	}
	
	/*
	 * This method allows the user to deal with guest object
	 */
	private static void guestOption(GuestMgr guestMgr, Scanner sc) {
		int choice = -1;
		String name;
		do {
			guestMenu();
			choice = validateChoice(choice, sc);
			switch (choice) {
				case 0:
					System.out.println("Going back...");
					break;
				case 1:
					System.out.print("Search guest's name: ");
					name = sc.nextLine();
					guestMgr.updateGuestDetails(name);
					break;
				case 2:
					System.out.print("Search guest's name: ");
					name = sc.nextLine();
					guestMgr.listGuestDetails(name);
					break;
				default:
					System.out.println("Invalid choice");
					break;
			}
		}while (choice != 0 && choice != 1 && choice != 2);
	}
	
	/*
	 * This method contains the menu for menuOption
	 */
	private static void menuItemMenu() {
		System.out.println("\n+------------------------------------------+");
		System.out.println("| What would you like to do ?              |");
		System.out.println("| 0. Go back                               |");
		System.out.println("| 1. Create new menu item                  |");
		System.out.println("| 2. Update menu item                      |");
		System.out.println("| 3. Remove menu item                      |");
		System.out.println("| 4. List all menu items                   |");
		System.out.println("+------------------------------------------+");
		System.out.print("Enter choice: ");
	}

	/*
	 * This method allows the user to deal with menu item objects
	 */
	private static void menuOption(MenuItemMgr miMgr, Scanner sc) {
		int choice = -1;
		
		do {
			menuItemMenu();
			choice = validateChoice(choice, sc);
			switch (choice) {
				case 0:
					System.out.println("Going back...");
					break;
				case 1:
					miMgr.addMenuItem();
					break;
				case 2:
					System.out.print("Enter menu item name: ");
					miMgr.updateMenuItem(sc.nextLine());;
					break;
				case 3:
					System.out.print("Enter menu item name: ");
					miMgr.removeMenuItem(sc.nextLine());
					break;
				case 4:
					miMgr.listMenuItems();
					break;
				default:
					System.out.println("Invalid choice");
					break;
			}
		} while (choice != 0 && choice != 1 && choice != 2 && choice != 3 && choice != 4);
	}
	
	/*
	 * This method contains the menu for roomOption
	 */
	private static void roomMenu() {
		System.out.println("\n+------------------------------------------+");
		System.out.println("| What would you like to do ?              |");
		System.out.println("| 0. Go back                               |");
		System.out.println("| 1. Create new room                       |");
		System.out.println("| 2. Update room details                   |");
		System.out.println("| 3. List Room                             |");
		System.out.println("+------------------------------------------+");
		System.out.print("Enter choice: ");
	}

	/*
	 * This method allows the user to deal with room objects
	 */
	private static void roomOption(RoomMgr rMgr, Scanner sc) {
		int choice = -1;
		
		do {
			roomMenu();
			choice = validateChoice(choice, sc);
			switch (choice) {
				case 0:
					System.out.println("Going back...");
					break;
				case 1:
					rMgr.addRoom();
					break;
				case 2:
					System.out.print("Enter room (level-number): ");
					rMgr.updateRoom(sc.nextLine());
					break;
				case 3:
					listRoom(rMgr, sc);
					break;
				default:
					System.out.println("Invalid choice");
					break;
			}
		} while (choice != 0 && choice != 1 && choice != 2 && choice != 3);
	}
	
	/*
	 * This method contains the different menu for listing rooms
	 */
	private static void listRoomMenu() {
		System.out.println("\n+--------------------------------+");
		System.out.println("| Select display type:           |");
		System.out.println("| 1. List Room by occupancy rate |");
		System.out.println("| 2. List Room by room status    |");
		System.out.println("+--------------------------------+");
		System.out.print("Enter choice: ");
	}
	
	/*
	 * This method lists all the rooms based on user's input
	 */
	private static void listRoom(RoomMgr rMgr, Scanner sc) {
		int choice = -1;
		do {
			listRoomMenu();
			choice = validateChoice(choice, sc);
			switch (choice) {
				case 1:
					rMgr.listRoomsByOccupancyRate();
					break;
				case 2:
					rMgr.listRoomsByRoomStatus();
					break;
				default:
					System.out.println("Invalid Choice");
					break;
			}
		} while (choice != 1 && choice != 2);
	}

	/* 
	 * This method contains the menu for reservationOption
	 */
	private static void reservationMenu() {
		System.out.println("\n+-------------------------------+");
		System.out.println("| What would you like to do ?   |");
		System.out.println("| 0. Go back                    |");
		System.out.println("| 1. Create reservation         |");
		System.out.println("| 2. Update reservation detail  |");
		System.out.println("| 3. Remove reservation         |");
		System.out.println("| 4. Print a reservation detail |");
		System.out.println("| 5. Print all reservation      |");
		System.out.println("+-------------------------------+");
		System.out.print("Enter choice: ");
	}
	
	/*
	 * This method allows the user to deal with reservation objects
	 */
	private static void reservationOption(GuestMgr guestMgr, RoomMgr rMgr, ReservationMgr resMgr, RoomServiceMgr rsMgr, MenuItemMgr miMgr,Scanner sc) {
		int choice = -1;
		
		do {
			reservationMenu();
			choice = validateChoice(choice, sc);
			switch(choice) {
				case 0:
					System.out.println("Going back...");
					break;
				case 1:

					resMgr.newReservation();
					break;
				case 2:
					resMgr.updateReservation();
					break;
				case 3:
					resMgr.removeReservation();
					break;
				case 4:
					resMgr.printReservation();
					break;
				case 5:
					resMgr.printAllReservation();
					break;
				default:
					System.out.println("Invalid Choice");
					break;
			}
		} while (choice != 0 && choice != 1 && choice != 2 && choice != 3 && choice != 4);
	}

	/*
	 * This method contains the menu for roomServiceOption()
	 */
	private static void roomServiceMenu() {
		System.out.println("\n+-----------------------------+");
		System.out.println("| What would you like to do ? |");
		System.out.println("| 0. Go back                  |");
		System.out.println("| 1. Add room service         |");
		System.out.println("| 2. Update room service      |");
		System.out.println("| 3. Remove room service      |");
		System.out.println("| 4. Print all room service   |");
		System.out.println("+-----------------------------+");
		System.out.print("Enter choice: ");
	}
	
	/*
	 * This method allows the user to create/update/remove/print room services
	 */
	private static void roomServiceOption(ReservationMgr resMgr, RoomServiceMgr rsMgr, MenuItemMgr miMgr, Scanner sc) {
		int choice = -1;
		
		do {
			roomServiceMenu();
			choice = validateChoice(choice, sc);
			switch (choice) {
				case 0:
					System.out.println("Going back...");
					break;
				case 1:
					addRoomService(resMgr, rsMgr, miMgr);
					break;
				case 2:
					resMgr.updateRoomService();
					break;
				case 3:
					resMgr.removeRoomService();
					break;
				case 4:
					resMgr.printRoomService();
					break;
				default:
					System.out.println("Invalid choice");
					break;
			}
		} while (choice != 0 && choice != 1 && choice != 2);
	}
	
	private static void addRoomService(ReservationMgr resMgr, RoomServiceMgr rsMgr, MenuItemMgr miMgr) {
		Reservation res = resMgr.searchReservation();
		if (Objects.equals(res, null)) {
			System.out.println("Reservation does not exist");
			return;
		}
					
		ArrayList<MenuItem> miList = new ArrayList<MenuItem>();
		System.out.println("");
		miList = miMgr.selectMenuItem();
		if (miList.size() == 0) {
			System.out.println("Menu Item List is empty. Exiting...");
			return;
		}
		
		RoomService rs = rsMgr.orderRoomService(miList);
		resMgr.addRoomService(res, rs);
		
		System.out.println("Room Service Added");
	}
	
	/*
	 * This method contains the menu for checkInCheckOutExpireOption()
	 */
	private static void checkInCheckOutExpireMenu() {
		System.out.println("\n+---------------------------------+");
		System.out.println("| What would you like to do ?     |");
		System.out.println("| 0. Go back                      |");
		System.out.println("| 1. Guest Check In (Walk In)     |");
		System.out.println("| 2. Guest Check In (Reservation) |");
		System.out.println("| 3. Guest Check Out              |");
		System.out.println("| 4. Expire a reservation         |");
		System.out.println("+---------------------------------+");
		System.out.print("Enter choice: ");
	}
	
	/*
	 * This method allows the guest to check in/check out, and the hotel staff to expire a reservation
	 */
	private static void checkInCheckOutExpireOption(ReservationMgr resMgr, Scanner sc) {
		int choice = 0;
		
		do {
			checkInCheckOutExpireMenu();
			choice = validateChoice(choice, sc);
			switch (choice) {
				case 0:
					System.out.println("Going back...");
					break;
				case 1:
					resMgr.checkIn();
					break;
				case 2:
					resMgr.checkIn(resMgr.searchReservation());
					break;
				case 3:
					checkOut(resMgr, sc);
					break;
				case 4:
					resMgr.expireAReservation();
					break;
				default:
					System.out.println("Invalid choice");
					break;
			}
		} while (choice != 0 && choice != 1 && choice != 2 && choice != 3);
	}

	/*
	 * This method allows the guest to check out of the hotel and makes payment
	 */
	private static void checkOut(ReservationMgr resMgr, Scanner sc) {
		Reservation res = resMgr.checkOut();
		
		if (!Objects.equals(res, null)) {
			Payment p = new Payment(res, sc);
			p.billReport();
			p.payment();
		}
	}
	
	/*
	 * This method is used to check whether the user input inside sc's buffer has an integer. 
	 * It will loop until the user inputs an integer.
	 */
	private static int validateChoice(int choice, Scanner sc) {
		boolean valid = false;
		
		while (!valid) {
			if (!sc.hasNextInt()) {
				System.out.println("Invalid Input. Please enter an integer");
				sc.nextLine();	// clear the input in the buffer
				System.out.print("Enter choice: ");
			}
			else {
				valid = true;
				choice = sc.nextInt();
				sc.nextLine();	// clear the "\n" in the buffer
			}
		}
		
		return choice;
	}

}

