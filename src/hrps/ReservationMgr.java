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
	public static boolean selectCheckInType(Scanner sc) {
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
			System.out.println("Enter date (dd-mm-yyyy): ");
			r.setCheckInDate(LocalDate.parse(sc.nextLine()));
			r.setStatus(Reservation.ResStatus.CONFIRMED);
		}
		
		// set the reservation guest
		r.setGuest(this.gMgr.addGuest());
		
		// set room
		while (Objects.equals(r.getRoom(), null)) {
			rMgr.listRoomsByOccupancyRate();
			System.out.println("Enter room (level-number): ");
			room = sc.nextLine();
			r.setRoom(rMgr.isVacant(room));
		}
		
		// set the no of adults and children
		System.out.println("Enter no of adults: ");
		noOfAdults = sc.nextInt();
		sc.nextLine();	// clear the "\n" in the buffer
		r.setNoOfAdults(noOfAdults);
		System.out.println("Enter no of children: ");
		noOfChildren = sc.nextInt();
		sc.nextLine();	// clear the "\n" in the buffer
		r.setNoOfChildren(noOfChildren);
		reservationList.add(r);
	}
}
