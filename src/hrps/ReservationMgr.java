package hrps;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import java.time.LocalDate;

public class ReservationMgr {
	private ArrayList<Reservation> reservationList = new ArrayList<Reservation>();
	private GuestMgr gMgr = null;
	private RoomMgr rMgr = null;
	private Scanner sc = new Scanner(System.in);
	
	public ReservationMgr(GuestMgr gMgr, RoomMgr rMgr) {
		this.gMgr = gMgr;
		this.rMgr = rMgr;
	}
	
	/*
	 * return true if walk in
	 * return false if reservation
	 */
	private static boolean selectCheckInType(Scanner sc) {
		boolean b = false;
		int bChoice = -1;
		
		do {
			System.out.println("\n+-----------------------+");
			System.out.println("| Select check-in type: |");
			System.out.println("| 1. Walk-in            |");
			System.out.println("| 2. Reservation        |");
			System.out.println("+-----------------------+");
			System.out.print("Enter choice: ");
			bChoice = sc.nextInt();
			sc.nextLine();	// clear the "\n" in the buffer
			switch(bChoice) {
				case 1:
					b = true;
					break;
				case 2:
					b = false;
					break;
				default:
					System.out.println("Invalid choice");
					break;
			}
		} while (bChoice != 1 && bChoice != 2);
		
		return b;
	}
	
	private Reservation searchReservation(String name) {
		Reservation r = null;
		
		for (Reservation res : reservationList) {
			if (res.getGuest().getName().equals(name)) {
				r = res;
				break;
			}
		}
		
		return r;
	}
	
	private Reservation searchReservation(int rLevel, int rNumber) {
		Reservation r = null;
		
		for (Reservation res : reservationList) {
			if (res.getRoom().getRoomLevel() == rLevel && res.getRoom().getRoomNumber() == rNumber) {
				r = res;
				break;
			}
		}
		
		return r;
	}
	
	public void newReservation() {
		Reservation r = new Reservation();
		boolean walkIn = selectCheckInType(sc);
		String room;
		int noOfAdults, noOfChildren;
		
		// set the check in date and reservation status
		if (walkIn) {
			r.setCheckInDate(LocalDate.now());
			r.setStatus(Reservation.ResStatus.CHECKED_IN);
		}
		else {
			System.out.print("Enter date (yyyy-mm-dd): ");
			r.setCheckInDate(LocalDate.parse(sc.nextLine()));
			r.setStatus(Reservation.ResStatus.CONFIRMED);
		}
		
		// set the reservation guest
		r.setGuest(this.gMgr.addGuest());
		
		// set room
		while (Objects.equals(r.getRoom(), null)) {
			rMgr.listRoomsByOccupancyRate();
			System.out.print("Enter room (level-number): ");
			room = sc.nextLine();
			r.setRoom(rMgr.isVacant(room, walkIn));
		}
		
		// set the no of adults and children
		System.out.print("Enter no of adults: ");
		noOfAdults = sc.nextInt();
		sc.nextLine();	// clear the "\n" in the buffer
		r.setNoOfAdults(noOfAdults);
		System.out.print("Enter no of children: ");
		noOfChildren = sc.nextInt();
		sc.nextLine();	// clear the "\n" in the buffer
		r.setNoOfChildren(noOfChildren);
		reservationList.add(r);
		r.printReceipt();
	}

	public void updateReservation() {
		String s;
		Reservation r = null;
		
		System.out.print("Enter guest's name or room: ");
		s = sc.nextLine();
		if (s.contains("-")) {
			String[] parts = s.split("-");
			r = searchReservation(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
		}
		else {
			r = searchReservation(s);
		}
		if(Objects.equals(r, null)) {
			System.out.println("Reservation does not exist");
			return;
		}
		else {
			updateOption(r);
		}
	}

	private void updateOption(Reservation r) {
		int uChoice = -1;
		
		do {
			updateMenu();
			uChoice = sc.nextInt();
			sc.nextLine();	// clear the "\n" in the buffer
			switch(uChoice) {
				case 0:
					System.out.println("Going back...");
					break;
				case 1:
					gMgr.updateGuestDetails(r.getGuest());
					break;
				case 2:
					changeRoomMenu(r);
					break;
				default:
					System.out.println("Invalid Choice");
					break;
			}
		} while (uChoice != 0 && uChoice != 1 && uChoice != 2);
	}
	
	private static void updateMenu() {
		System.out.println("\n+--------------------------------+");
		System.out.println("| What would you like to update? |");
		System.out.println("| 0. Nothing                     |");
		System.out.println("| 1. Update Guest Details        |");
		System.out.println("| 2. Change Room                 |");
		System.out.println("+--------------------------------+");
		System.out.print("Enter choice: ");
	}

	private void changeRoomMenu(Reservation r) {
		Room room = null;
		int uChoice = -1;
		
		rMgr.listRoomsByOccupancyRate();
		
		do {
			System.out.println("\nPress 0 to quit");
			System.out.print("Enter new room number: ");
			if (sc.hasNextInt()) {
				uChoice = sc.nextInt();
				sc.nextLine();	// clear the "\n" in the buffer
			}
			else {
				room = rMgr.isVacant(sc.next());
				sc.nextLine();	// clear the "\n" in the buffer
				if (!Objects.equals(room, null)) {
					rMgr.changeRoom(r.getRoom(), room);
					r.setRoom(room);
					System.out.println("Room changed");
				}
			}
		} while (Objects.equals(room, null) && uChoice != 0);
		
	}
}
