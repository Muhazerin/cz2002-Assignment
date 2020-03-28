package hrps;

import java.util.ArrayList;
import java.util.Scanner;

public class GuestMgr {
	private ArrayList<Guest> guestList = new ArrayList<Guest>();
	
	public void addGuest() {
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Enter name: ");
		
		sc.close();
	}
	
	public void listGuestDetails(String name) {
		if (searchGuest(name)) {
			System.out.println("Something");
		}
		else {
			System.out.println("Guest does not exist");
		}
	}
	
	private boolean searchGuest(String name) {
		if(name == "Muhazerin")
			return true;
		else
			return false;
	}
	
	public boolean updateGuestDetails() {
		return true;
	}
}
