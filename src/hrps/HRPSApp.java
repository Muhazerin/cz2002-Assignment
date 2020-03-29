package hrps;

import java.util.Scanner;

public class HRPSApp {

	public static void main(String[] args) {
		
		GuestMgr guestMgr = new GuestMgr();
		MenuItemMgr miMgr = new MenuItemMgr();
		RoomMgr rMgr = new RoomMgr();
		ReservationMgr resMgr = new ReservationMgr(guestMgr, rMgr);
		
		Scanner sc = new Scanner(System.in);
		int choice, gChoice, mChoice, rChoice;
		choice = gChoice = mChoice = rChoice = -1;
		String name;
		
		do {
			menu();
			choice = sc.nextInt();
			sc.nextLine(); // clear the "\n" in the buffer
			switch (choice) {
				case 0:
					System.out.println("Bye Bye!!");
					break;
				case 1:
					do {
						guestMenu();
						gChoice = sc.nextInt();
						sc.nextLine(); // clear the "\n" in the buffer
						switch (gChoice) {
							case 0:
								System.out.println("Going back...");
								break;
							case 1:
								System.out.print("Enter guest's name: ");
								name = sc.nextLine();
								guestMgr.updateGuestDetails(name);
								break;
							case 2:
								System.out.print("Enter guest's name: ");
								name = sc.nextLine();
								guestMgr.listGuestDetails(name);
								break;
							default:
								System.out.println("Invalid choice");
								break;
						}
					}while (gChoice != 0 && gChoice != 1 && gChoice != 2 && gChoice != 3);
					break;
				case 2:
					do {
						roomMenu();
						rChoice = sc.nextInt();
						sc.nextLine();	// clear the "\n" in the buffer
						switch (rChoice) {
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
								listRoom(rMgr);
								break;
							default:
								System.out.println("Invalid choice");
								break;
						}
					} while (rChoice != 0 && rChoice != 1 && rChoice != 2 && rChoice != 3);
					break;
				case 4:
					do {
						menuItemMenu();
						mChoice = sc.nextInt();
						sc.nextLine();	// clear the "\n" in the buffer
						switch (mChoice) {
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
					} while (mChoice != 0 && mChoice != 1 && mChoice != 2 && mChoice != 3 && mChoice != 4);
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
	
	private static void menu() {
		System.out.println("\n+-----------------------------------------------------+");
		System.out.println("| Welcome to the Hotel Reservation and Payment System |");
		System.out.println("| What would you like to do?                          |");
		System.out.println("| 0. Exit the program                                 |");
		System.out.println("| 1. Update/Search guest details                      |");
		System.out.println("| 2. Create/Update/List room                          |");
		System.out.println("| 3. Create/Update/Remove/Print reservation           |");
		System.out.println("| 4. Create/Update/Remove/List menu items             |");
		System.out.println("+-----------------------------------------------------+");
		System.out.print("Enter choice: ");
	}

	private static void guestMenu() {
		System.out.println("\n+------------------------------------------+");
		System.out.println("| What would you like to do ?              |");
		System.out.println("| 0. Go back                               |");
		System.out.println("| 1. Update guest details                  |");
		System.out.println("| 2. Search for guest and list its details |");
		System.out.println("+------------------------------------------+");
		System.out.print("Enter choice: ");
	}
	
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

	private static void listRoomMenu() {
		System.out.println("\n+--------------------------------+");
		System.out.println("| Select display type:           |");
		System.out.println("| 1. List Room by occupancy rate |");
		System.out.println("| 2. List Room by room status    |");
		System.out.println("+--------------------------------+");
		System.out.print("Enter choice: ");
	}
	
	private static void listRoom(RoomMgr rMgr) {
		int lChoice = -1;
		Scanner sc = new Scanner(System.in);
		do {
			listRoomMenu();
			lChoice = sc.nextInt();
			sc.nextLine();	// clear the "\n" in the buffer
			switch (lChoice) {
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
		} while (lChoice != 1 && lChoice != 2);
	}
}
