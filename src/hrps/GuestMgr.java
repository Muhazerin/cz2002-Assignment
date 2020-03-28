package hrps;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class GuestMgr {
	private ArrayList<Guest> guestList = new ArrayList<Guest>();
	
	public void addGuest() {
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
		guestList.add(new Guest(id, name, address, country, gender, nationality, contact));
		System.out.println("Guest Added");
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
			System.out.println("\nGuest Details: ");
			System.out.println("\tName: " + g.getName());
			System.out.println("\tID: " + g.getId());
			System.out.println("\tGender: " + g.getGender());
			System.out.println("\tAddress: " + g.getAddress());
			System.out.println("\tCountry: " + g.getCountry());
			System.out.println("\tNationality: " + g.getNationality());
			System.out.println("\tContact No: " + g.getContact());
		}
		else {
			System.out.println("Guest does not exist");
		}
	}
	
	public void updateGuestDetails(String name) {
		Guest g = searchGuest(name);
		if (g == null) {
			System.out.println("Guest does not exist");
		}
		else {
			Scanner sc = new Scanner(System.in);
			System.out.print("Enter new name: ");
			name = sc.nextLine();
			g.setName(name);
			System.out.println("Guest Details updated");
		}
	}
}
