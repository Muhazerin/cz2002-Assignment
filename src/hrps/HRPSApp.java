package hrps;

import java.util.Scanner;

public class HRPSApp {

public static void main(String[] args) {
		
		GuestMgr guestMgr = new GuestMgr();
		Scanner sc = new Scanner(System.in);
		int choice, gChoice;
		choice = gChoice = -1;
		
		do {
			menu();
			choice = sc.nextInt();
			switch (choice) {
				case 0:
					System.out.println("Bye Bye!!");
					break;
				case 1:
					do {
						guestMenu();
						gChoice = sc.nextInt();
						switch (gChoice) {
							case 0:
								System.out.println("Going back...");
								break;
							default:
								System.out.println("Invalid choice");
								break;
						}
					}while (gChoice != 0 && gChoice != 1 && gChoice != 2 && gChoice != 3);
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
	
	public static void menu() {
		System.out.println("\n+-----------------------------------------------------+");
		System.out.println("| Welcome to the Hotel Reservation and Payment System |");
		System.out.println("| 0. Exit the program                                 |");
		System.out.println("| 1. Create/Update/Search guest details               |");
		System.out.println("+-----------------------------------------------------+");
		System.out.print("Enter choice: ");
	}

	public static void guestMenu() {
		System.out.println("\n+-----------------------------+");
		System.out.println("| What would you like to do ? |");
		System.out.println("| 0. Go back                  |");
		System.out.println("| 1. Create new guest         |");
		System.out.println("| 2. Update guest details     |");
		System.out.println("| 3. Search guest details     |");
		System.out.println("+-----------------------------+");
		System.out.print("Enter choice: ");
	}
	
}
