package hrps;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class GuestMgr {
	private ArrayList<Guest> guestList = new ArrayList<Guest>();
	
	public void addGuest(Guest g) {
		guestList.add(g);
		System.out.println("Guest Added");
	}
	
	public Guest addGuest() {
		Guest g = null;
		String id, name, address, country, gender, nationality;
		int contact;
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter name: ");
		name = sc.nextLine();
		System.out.print("Enter ID: ");
		id = sc.nextLine();
		System.out.print("Enter gender: ");
		gender = sc.nextLine();
		System.out.print("Enter address: ");
		address = sc.nextLine();
		System.out.print("Enter country: ");
		country = sc.nextLine();
		System.out.print("Enter nationality: ");
		nationality = sc.nextLine();
		System.out.print("Enter contact number: ");
		contact = sc.nextInt();
		sc.nextLine(); // clear the "\n" in the buffer
		g = new Guest(id, name, address, country, gender, nationality, contact);
		guestList.add(g);
		System.out.println("Guest Added");
		return g;
	}
	
	private Guest searchGuest(String name) {
		if (guestList.size() == 0) {
			return null;
		}
		for (Guest g: guestList) {
			if (name.equalsIgnoreCase(g.getName())) {
				return g;
			}
		}
		return null;
	}
	
	public void listGuestDetails(String name) {
		Guest g = searchGuest(name);
		if (g != null) {
			printGuestDetails(g);
		}
		else {
			System.out.println("Guest does not exist");
		}
	}
	
	private static void printGuestDetails(Guest g) {
		System.out.println("\nGuest Details: ");
		System.out.println("\tName: " + g.getName());
		System.out.println("\tID: " + g.getId());
		System.out.println("\tGender: " + g.getGender());
		System.out.println("\tAddress: " + g.getAddress());
		System.out.println("\tCountry: " + g.getCountry());
		System.out.println("\tNationality: " + g.getNationality());
		System.out.println("\tContact No: " + g.getContact());
		System.out.println("Credit Card Details:");
		System.out.println("\tCard No: " + g.getCreditCard().getCardNo());
		System.out.println("\tCard Type: " + g.getCreditCard().getcType().toString());
		System.out.println("\tCVV: " + g.getCreditCard().getCvv());
		System.out.println("\tExpiry: " + g.getCreditCard().getExp());
		System.out.println("\tName: " + g.getCreditCard().getName());
		System.out.println("\tAddress: " + g.getCreditCard().getAddress());
		System.out.println("\tCountry: " + g.getCreditCard().getCountry());
	}
	
	public void updateGuestDetails(Guest g) {
		int uChoice = -1;
		Scanner sc = new Scanner(System.in);
		if (g == null) {
			System.out.println("Guest does not exist");
		}
		else {
			do {
				int number = 0;
				updateGuestMenu();
				uChoice = sc.nextInt();
				sc.nextLine();	// clear the "\n" in the buffer
				switch(uChoice) {
					case 0:
						System.out.println("\nThe new guest details are:");
						printGuestDetails(g);
						break;
					case 1:
						System.out.print("Enter new name: ");
						g.setName(sc.nextLine());
						break;
					case 2:
						System.out.print("Enter new ID: ");
						g.setId(sc.nextLine());
						break;
					case 3:
						System.out.print("Enter new address: ");
						g.setAddress(sc.nextLine());
						break;
					case 4:
						System.out.print("Enter new country: ");
						g.setCountry(sc.nextLine());
						break;
					case 5:
						System.out.print("Enter new nationality: ");
						g.setNationality(sc.nextLine());
						break;
					case 6:
						System.out.print("Enter new gender: ");
						g.setGender(sc.nextLine());
						break;
					case 7:
						System.out.print("Enter new contact number: ");
						number = sc.nextInt();
						sc.nextLine();	// clear the "\n" in the buffer
						g.setContact(number);
						break;
					case 8:
						updateCreditCard(g);
						break;
					case 9:
						System.out.print("Enter new name: ");
						g.setName(sc.nextLine());
						System.out.print("Enter new address: ");
						g.setAddress(sc.nextLine());
						System.out.print("Enter new country: ");
						g.setCountry(sc.nextLine());
						System.out.print("Enter new nationality: ");
						g.setNationality(sc.nextLine());
						System.out.print("Enter new gender: ");
						g.setGender(sc.nextLine());
						System.out.println("Enter new contact number: ");
						number = sc.nextInt();
						sc.nextLine();	// clear the "\n" in the buffer
						g.setContact(number);
						updateCreditCard(g);
						printGuestDetails(g);
						break;
					default:
						System.out.println("Invalid choice");
						break;
				}
			} while (uChoice != 0 && uChoice != 9);
		}
	}
	
	public void updateGuestDetails(String name) {
		Guest g = searchGuest(name);
		updateGuestDetails(g);
	}

	private static void updateGuestMenu() {
		System.out.println("\n+--------------------------------+");
		System.out.println("| What would you like to update: |");
		System.out.println("| 0. Nothing                     |");
		System.out.println("| 1. Name                        |");
		System.out.println("| 2. ID                          |");
		System.out.println("| 3. Address                     |");
		System.out.println("| 4. Country                     |");
		System.out.println("| 5. Nationality                 |");
		System.out.println("| 6. Gender                      |");
		System.out.println("| 7. Contact Number              |");
		System.out.println("| 8. Credit Card                 |");
		System.out.println("| 9. All                         |");
		System.out.println("+--------------------------------+");
		System.out.print("Enter choice: ");
	}

	private static void updateCreditCardMenu() {
		System.out.println("\n+--------------------------------+");
		System.out.println("| What would you like to update: |");
		System.out.println("| 0. Nothing                     |");
		System.out.println("| 1. Card no                     |");
		System.out.println("| 2. Card type                   |");
		System.out.println("| 3. CVV                         |");
		System.out.println("| 4. Expiry                      |");
		System.out.println("| 5. Name                        |");
		System.out.println("| 6. Address                     |");
		System.out.println("| 7. Country                     |");
		System.out.println("| 8. All                         |");
		System.out.println("+--------------------------------+");
		System.out.print("Enter choice: ");
	}

	private void updateCreditCard(Guest g) {
		Scanner sc = new Scanner(System.in);
		int uChoice = -1, cvv = -1;
		long cardNo = 0;
		do {
			updateCreditCardMenu();
			uChoice = sc.nextInt();
			sc.nextLine();	// clear the "\n" in the buffer
			switch (uChoice) {
				case 0:
					System.out.println("Going back...");
					break;
				case 1:
					System.out.print("Enter new card no: ");
					cardNo = sc.nextLong();
					sc.nextLine();	// clear the "\n" in the buffer
					g.getCreditCard().setCardNo(cardNo);
					break;
				case 2:
					g.getCreditCard().setcType(Guest.selectCardType());
					break;
				case 3:
					System.out.print("Enter new cvv: ");
					cvv = sc.nextInt();
					sc.nextLine();	// clear the "\n" in the buffer
					g.getCreditCard().setCvv(cvv);
					break;
				case 4:
					System.out.print("Enter new expiry: ");
					g.getCreditCard().setExp(sc.nextLine());
					break;
				case 5:
					System.out.print("Enter new name: ");
					g.getCreditCard().setName(sc.nextLine());
					break;
				case 6:
					System.out.print("Enter new address: ");
					g.getCreditCard().setAddress(sc.nextLine());
					break;
				case 7:
					System.out.print("Enter new country: ");
					g.getCreditCard().setCountry(sc.nextLine());
					break;
				case 8:
					System.out.print("Enter new card no: ");
					cardNo = sc.nextLong();
					sc.nextLine();	// clear the "\n" in the buffer
					g.getCreditCard().setCardNo(cardNo);
					g.getCreditCard().setcType(Guest.selectCardType());
					System.out.print("Enter new cvv: ");
					cvv = sc.nextInt();
					sc.nextLine();	// clear the "\n" in the buffer
					g.getCreditCard().setCvv(cvv);
					System.out.print("Enter new expiry: ");
					g.getCreditCard().setExp(sc.nextLine());
					System.out.print("Enter new name: ");
					g.getCreditCard().setName(sc.nextLine());
					System.out.print("Enter new address: ");
					g.getCreditCard().setAddress(sc.nextLine());
					System.out.print("Enter new country: ");
					g.getCreditCard().setCountry(sc.nextLine());
					System.out.println("Going back...");
					break;
				default:
					System.out.println("Invalid choice");
					break;
			}
		} while(uChoice != 0 && uChoice != 8);
	}

}
