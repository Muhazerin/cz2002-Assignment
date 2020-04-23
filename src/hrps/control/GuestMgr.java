package control;

import java.util.ArrayList;
import java.util.Scanner;

import entity.CreditCard;
import entity.Guest;

public class GuestMgr {
	private ArrayList<Guest> guestList;
	private FileIO fileIO;
	private int counter = 1;
	private Scanner sc;
	
	/*
	 * Constructor for GuestMgr class
	 * Initialize some variables
	 * Retrieve guests from file
	 */
	public GuestMgr(Scanner sc) {
		//System.out.println("GuestMgr");
		
		this.sc = sc;
		guestList = new ArrayList<Guest>();
		fileIO = new FileIO();
		
		Object[] objArray = fileIO.readObject(Guest.class);
		for (Object o : objArray) {
			Guest g = (Guest) o;
			guestList.add(g);
		}
		counter = guestList.size() + 1;
	}
		
	/*
	 * This method adds a guest to guestList and file
	 */
	public Guest addGuest() {
		Guest g = null;
		String id, name, address, country, gender, nationality, exp = "";
		int contact = 0, cvv = 0;
		long cardNo = 0;
		CreditCard.CardType cType = null;
		System.out.print("Enter name: ");
		name = sc.nextLine();
		System.out.print("Enter ID: ");
		id = sc.nextLine();
		System.out.print("Enter gender(M/F): ");
		gender = sc.nextLine();
		System.out.print("Enter address: ");
		address = sc.nextLine();
		System.out.print("Enter country: ");
		country = sc.nextLine();
		System.out.print("Enter nationality: ");
		nationality = sc.nextLine();
		System.out.print("Enter contact number: ");
		contact = validateChoice(contact, "Enter contact number: ");
		
		cType = getCardType();
		System.out.print("Enter card no:");
		cardNo = validateCardNo(cardNo, "Enter card no: ");
		System.out.print("Enter cvv: ");
		cvv = validateChoice(cvv, "Enter cvv: ");
		System.out.print("Enter expiry date (mm/yy): ");
		exp = validateExp(exp, "Enter expiry date (mm/yy): ");
		
		g = new Guest(counter, id, name, address, country, gender, nationality, contact, cType, cardNo, cvv, exp);
		
		counter++;
		guestList.add(g);
		System.out.println("Guest Added");
		
		fileIO.writeObject(guestList.toArray(), g.getClass());
		
		return g;
	}
	
	/* 
	 * This method searches and returns a guest in the guestList
	 * Returns a guest object if found
	 * Returns null if not found
	 */
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
	
	/*
	 * This method searches and lists the guest's details
	 */
	public void listGuestDetails(String name) {
		Guest g = searchGuest(name);
		if (g != null) {
			printGuestDetails(g);
		}
		else {
			System.out.println("Guest does not exist");
		}
	}
	
	/*
	 * This method lists guest's details
	 */
	private void printGuestDetails(Guest g) {
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
	
	/*
	 * This method updates the guest and updates the file
	 */
	public void updateGuestDetails(Guest g) {
		int uChoice = -1;
		if (g == null) {
			System.out.println("Guest does not exist");
		}
		else {
			do {
				int number = 0;
				updateGuestMenu();
				uChoice = validateChoice(uChoice, "Enter choice: ");
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
						number = validateChoice(number, "Enter new contact number: ");
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
						number = validateChoice(number, "Enter new contact number: ");
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
		fileIO.writeObject(guestList.toArray(), Guest.class);
	}
	
	/*
	 * Overloaded function for updateGuestDetails
	 * searchs for guest and go to updateGuestDetails(Guest g)
	 */
	public void updateGuestDetails(String name) {
		Guest g = searchGuest(name);
		updateGuestDetails(g);
	}

	/*
	 * This method contains the menu for updating guest
	 */
	private void updateGuestMenu() {
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

	/*
	 * This method contains the menu for updating credit card
	 */
	private void updateCreditCardMenu() {
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

	/*
	 * This method updates the credit card. No need fileIO as this is method is only accessible on updateGuestDetails()
	 */
	private void updateCreditCard(Guest g) {
		int uChoice = -1, cvv = -1;
		long cardNo = 0;
		String exp = null;
		do {
			updateCreditCardMenu();
			uChoice = validateChoice(uChoice, "Enter choice: ");
			switch (uChoice) {
				case 0:
					System.out.println("Going back...");
					break;
				case 1:
					System.out.print("Enter new card no: ");
					cardNo = validateCardNo(cardNo, "Enter new card no: ");
					g.getCreditCard().setCardNo(cardNo);
					break;
				case 2:
					g.getCreditCard().setcType(getCardType());
					break;
				case 3:
					System.out.print("Enter new cvv: ");
					cvv = validateChoice(cvv, "Enter new cvv: ");
					g.getCreditCard().setCvv(cvv);
					break;
				case 4:
					System.out.print("Enter new expiry: ");
					exp = validateExp(exp, "Enter new expiry: ");
					g.getCreditCard().setExp(exp);
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
					cardNo = validateCardNo(cardNo, "Enter new card no: ");
					g.getCreditCard().setCardNo(cardNo);
					g.getCreditCard().setcType(getCardType());
					System.out.print("Enter new cvv: ");
					cvv = validateChoice(cvv, "Enter new cvv: ");
					g.getCreditCard().setCvv(cvv);
					System.out.print("Enter new expiry: ");
					exp = validateExp(exp, "Enter new expiry: ");
					g.getCreditCard().setExp(exp);
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

	private CreditCard.CardType getCardType() {
		int choice = -1;
		CreditCard.CardType cType = null;
		
		do {
			System.out.println("\n+-------------------+");
			System.out.println("| Select card type: |");
			System.out.println("| 1. Mastercard     |");
			System.out.println("| 2. Visa           |");
			System.out.println("+-------------------+");
			System.out.print("Enter choice: ");
			choice = validateChoice(choice, "Enter choice: ");
			switch (choice) {
				case 1:
					cType = CreditCard.CardType.MASTER;
					break;
				case 2:
					cType = CreditCard.CardType.VISA;
					break;
				default:
					System.out.println("Invalid Choice");
					break;
			}
		} while (choice != 1 && choice != 2);
		return cType;
	}
	
	/*
	 * This method returns the guest based on the gID
	 * Used in ReservationMgr to set guests for reservation
	 */
	public Guest getGuestByGId(int gId) {
		Guest g = null;
		
		for (Guest temp : guestList) {
			if (temp.getGId() == gId) {
				g = temp;
				break;
			}
		}
		return g;
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
	 * This method is used to ensure that user enters a long integer
	 */
	private long validateCardNo(long cardNo, String inputText) {
		boolean valid = false;
		
		while (!valid) {
			if (!sc.hasNextLong()) {
				System.out.println("Invalid Input. Please enter an integer");
				sc.nextLine();	// clear the input in the buffer
				System.out.print(inputText);
			}
			else {
				valid = true;
				cardNo = sc.nextLong();
				sc.nextLine();	// clear the "\n" in the buffer
			}
		}
		
		return cardNo;
	}

	/*
	 * This method is used to ensure that user enters a proper expiry date
	 */
	private String validateExp(String exp, String inputText) {
		boolean valid = false, isNum1 = false, isNum2 = false;
		
		while (!valid) {
			exp = sc.nextLine();
			if (!exp.contains("/")) {
				System.out.println("Invalid expiry date. Please enter an expiry date");
				System.out.print(inputText);
			}
			else {
				String[] parts = exp.split("/");
				if (parts.length == 2) {
					isNum1 = isInteger(parts[0]);
					isNum2 = isInteger(parts[1]);
				}
				if (!isNum1 || !isNum2) {
					System.out.println("Invalid expiry date. Please enter an expiry date");
					System.out.print(inputText);
				}
				else {
					valid = true;
				}
			}
		}
		
		return exp;
	}

	/*
	 * This method checks whether a string is an integer
	 */
	private boolean isInteger(String input) {
		try {
			Integer.parseInt(input);
			return true;
		}
		catch (Exception e) {
			return false;
		}
	}
}
