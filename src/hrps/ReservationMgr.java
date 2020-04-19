package hrps;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

import hrps.Reservation.ResStatus;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class ReservationMgr {
	
	private ArrayList<Reservation> reservationList;
	private FileIO fileIO;
	private GuestMgr guestMgr;
	private RoomMgr roomMgr;
	private RoomServiceMgr rsMgr;
	private Scanner sc;
	private int counter = 1;
	
	/*
	 * Constructor method of this ReservationMgr class
	 * Do some variable initialization and retrieve reservation from file
	 */
	public ReservationMgr(GuestMgr guestMgr, RoomMgr roomMgr, RoomServiceMgr rsMgr, Scanner sc) {	

		
		reservationList = new ArrayList<Reservation>();
		fileIO = new FileIO();
		this.guestMgr = guestMgr;
		this.roomMgr = roomMgr;
		this.rsMgr = rsMgr;
		this.sc = sc;
		
		Object[] objArray = fileIO.readObject(Reservation.class);
		for (Object o : objArray) {
			Reservation res = (Reservation) o;
			Guest g = guestMgr.getGuestByGId(res.getGuest().getGId());
			Room room = roomMgr.getRoomById(res.getRoom().getId());
			
			// set the proper room service object
			ArrayList<RoomService> rsList = res.getRoomServiceList();
			if (rsList.size() > 0) {
				res.clearRoomServiceList();
				for (RoomService rs1 : rsList) {
					RoomService rs = rsMgr.getRoomServiceById(rs1.getId());
					res.addRoomServiceNoPrintOrder(rs);
				}
			}
			counter = reservationList.size() + 1;
			
			res.setRoom(room);
			res.setGuest(g);
			reservationList.add(res);
		}
	}

	/*
	 * This method adds a new reservation to the reservation list
	 */
	public void newReservation() {
		// add guest into guest manager list and return that guest
		Guest g = guestMgr.addGuest();
		
		// checking room
		Room room = null;
		String s;
		boolean isValidRoom = false;
		while (Objects.equals(room, null)) {
			roomMgr.listRoomsByOccupancyRate();
			
			do {
				System.out.print("Enter room number (level-number): ");
				s = sc.nextLine();
				isValidRoom = validateRoom(s);
			} while (!isValidRoom);
			
			room = roomMgr.isVacant(s);
		}
		
		Reservation r = new Reservation(counter);
		counter++;
		int noOfAdults = 0, noOfChildren = 0;
		
		// set the check in date and reservation status
		System.out.print("Enter check in date (yyyymmdd): ");
		DateTimeFormatter dateFormatter = DateTimeFormatter.BASIC_ISO_DATE;
		String time = "";
		time  = validateTime(time, dateFormatter, "Enter date (yyyymmdd): ");
		r.setCheckInDate(LocalDate.parse(time, dateFormatter));
		r.setStatus(Reservation.ResStatus.CONFIRMED);
		room.setAvailabilityStatus(Room.AvailabilityStatus.RESERVED);
		
		roomMgr.writeToFile();
		r.setGuest(g);
		r.setRoom(room);
		
		// set the no of adults and children
		System.out.print("Enter no of adults: ");
		noOfAdults = validateChoice(noOfAdults, "Enter no of adults: ");
		r.setNoOfAdults(noOfAdults);
		System.out.print("Enter no of children: ");
		noOfChildren = validateChoice(noOfChildren, "Enter no of children: ");
		r.setNoOfChildren(noOfChildren);
				
		reservationList.add(r);
		
		fileIO.writeObject(reservationList.toArray(), Reservation.class);
		
		r.printReceipt();
	}
		
	/*
	 * This method searches for reservation
	 * Returns reservation if found
	 * Returns null if not found
	 */
	public Reservation searchReservation() {
		String name;
		Reservation r = null;
		
		System.out.print("Enter guest's name: ");
		name = sc.nextLine();
		
		for (Reservation res : reservationList) {
			if (res.getGuest().getName().equals(name)) {
				r = res;
				break;
			}
		}
		
		return r;
	}
	
	/*
	 * This method updates the guest details or room number based on the reservation
	 */
	public void updateReservation() {
		int choice = -1;
		Reservation res = searchReservation();
		
		if (Objects.equals(res, null)) {
			System.out.println("Reservation does not exist");
			return;
		}
		do {
			updateReservationMenu();
			choice = validateChoice(choice, "Enter choice: ");
			switch(choice) {
				case 0:
					System.out.println("Going back...");
					break;
				case 1:
					guestMgr.updateGuestDetails(res.getGuest());
					fileIO.writeObject(reservationList.toArray(), Reservation.class);
					break;
				case 2:
					int noOfAdults = 0;
					System.out.print("Enter no of adults: ");
					noOfAdults = validateChoice(noOfAdults, "Enter no of adults: ");
					res.setNoOfAdults(noOfAdults);
					fileIO.writeObject(reservationList.toArray(), Reservation.class);
					break;
				case 3:
					int noOfChildren = 0;
					System.out.print("Enter no of children: ");
					noOfAdults = validateChoice(noOfChildren, "Enter no of children: ");
					res.setNoOfChildren(noOfChildren);
					fileIO.writeObject(reservationList.toArray(), Reservation.class);
					break;
				default:
					System.out.println("Invalid Choice");
					break;
			}
		} while (choice != 0 && choice != 1 && choice != 2);
	}
	
	/*
	 * This method contains the menu for update reservation
	 */
	private void updateReservationMenu() {
		System.out.println("\n+--------------------------------+");
		System.out.println("| What would you like to update? |");
		System.out.println("| 0. Nothing                     |");
		System.out.println("| 1. Update Guest Details        |");
		System.out.println("| 2. No of adults                |");
		System.out.println("| 3. No of children              |");
		System.out.println("+--------------------------------+");
		System.out.print("Enter choice: ");
	}
	
	/*
	 * This method removes a reservation from the reservation list if found
	 * Else, it prints out error message
	 */
	public void removeReservation() {
		Reservation r = searchReservation();
		
		if(Objects.equals(r, null)) {
			System.out.println("Reservation does not exist");
		}
		else {
			r.removeRoom();
			reservationList.remove(r);
			fileIO.writeObject(reservationList.toArray(), Reservation.class);
			roomMgr.writeToFile();
			System.out.println("Reservation removed");
		}
	}
		
	/*
	 * This method prints the reservation details if found
	 * Else, it prints error message
	 */
	public void printReservation() {
		Reservation r = searchReservation();
		
		if(Objects.equals(r, null)) {
			System.out.println("Reservation does not exist");
		}
		else {
			r.printReceipt();
		}
	}	

	/*
	 * This method prints all non-expired reservation
	 */
	public void printAllReservation() {
		for (Reservation res : reservationList) {
			//if the date of the reservation check in has passed
			if(res.getCheckInDate().isBefore(LocalDate.now())) {
				//set reservation status to expired.
				res.setStatus(ResStatus.EXPIRED);
			}
			if (res.getStatus() != Reservation.ResStatus.EXPIRED) {
				res.printReceipt();
			}
		}
	}
	
	/*
	 * Add room service to reservation and write to file
	 */
	public void addRoomService(Reservation res, RoomService rs) {
		res.addRoomService(rs);
		fileIO.writeObject(reservationList.toArray(), Reservation.class);
	}
	
	/*
	 * This method checks whether the user input inside sc's buffer has an integer. 
	 * It will loop until the user inputs an integer.
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
	 * This method checks whether the user input valid room number
	 */
	private boolean validateRoom(String room) {
		boolean isNum1 = false, isNum2 = false;
		if (!room.contains("-")) {
			System.out.println("Invalid room");
			return false;
		}
		else {
			String[] parts = room.split("-");
			if (parts.length == 2) {
				isNum1 = isInteger(parts[0]);
				isNum2 = isInteger(parts[1]);
			}
			if (!isNum1 || !isNum2) {
				System.out.println("Invalid room");
				return false;
			}
		}
		return true;
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
	
	/*
	 * This method removes a valid room service from reservation and room service mgr
	 */
	public void removeRoomService() {
		Reservation res = searchReservation();
		if (Objects.equals(res, null)) {
			System.out.println("Reservation does not exist");
			return;
		}
		else {
			res.printRoomServices();
			if (res.getRoomServiceList().size() == 0) {
				return;
			}
			else {
				int id = -1;
				System.out.print("Enter room service id: ");
				id = validateChoice(id, "Enter room service id: ");
				RoomService rs = res.getRoomServiceById(id);
				if (Objects.equals(rs, null)) {
					System.out.println("Room Service does not exist");
				}
				else {
					rsMgr.removeRoomService(rs);
					res.removeRoomService(rs);
					fileIO.writeObject(reservationList.toArray(), Reservation.class);
				}
			}
		}
	}
	
	/*
	 * This method allows the user to update the status of the room service
	 */
	public void updateRoomService() {
		Reservation res = searchReservation();
		if (Objects.equals(res, null)) {
			System.out.println("Reservation does not exist");
			return;
		}
		else {
			res.printRoomServices();
			if (res.getRoomServiceList().size() == 0) {
				return;
			}
			else {
				int id = -1;
				System.out.print("Enter room service id: ");
				id = validateChoice(id, "Enter room service id: ");
				RoomService rs = res.getRoomServiceById(id);
				if (Objects.equals(rs, null)) {
					System.out.println("Room Service does not exist");
				}
				else {
					rsMgr.updateRoomService(rs);
				}
			}
		}
	}
	
	/*
	 * This method searches a reservation and print all the room services
	 * 
	 */
	public void printRoomService() {
		Reservation res = searchReservation();
		if (Objects.equals(res, null)) {
			System.out.println("Reservation does not exist");
		}
		else {
			res.printRoomServices();
		}
	}

	/*
	 * This method checks whether the user input a valid time and according to the format specified
	 */
	private String validateTime(String time, DateTimeFormatter dateFormatter, String inputText) {
		boolean valid = false;
		time = sc.nextLine();
		
		while (!valid) {
			try {
				LocalDate.parse(time, dateFormatter);
			}
			catch (DateTimeParseException e) {
				valid = false;
				System.out.println("Invalid date. Please enter a valid date (yyyymmdd).");
				System.out.print(inputText);
				time = sc.nextLine();
				continue;
			}
			valid = true;
		}
		return time;
	}
	
	/*
	 * This method allows the guest to check in without a reservation
	 */
	public void checkIn() {
		// add guest into guest manager list and return that guest
		Guest g = guestMgr.addGuest();
		
		// checking room
		Room room = null;
		String s;
		boolean isValidRoom = false;
		while (Objects.equals(room, null)) {
			roomMgr.listRoomsByOccupancyRate();
			
			do {
				System.out.print("Enter room number (level-number): ");
				s = sc.nextLine();
				isValidRoom = validateRoom(s);
			} while (!isValidRoom);
			
			room = roomMgr.isVacant(s);
		}
		
		Reservation r = new Reservation(counter);
		int noOfAdults = 0, noOfChildren = 0;
		
		r.setCheckInDate(LocalDate.now());
		r.setStatus(Reservation.ResStatus.CHECKED_IN);
		room.setAvailabilityStatus(Room.AvailabilityStatus.OCCUPIED);
		
		roomMgr.writeToFile();
		r.setGuest(g);
		r.setRoom(room);
		
		// set the no of adults and children
		System.out.print("Enter no of adults: ");
		noOfAdults = validateChoice(noOfAdults, "Enter no of adults: ");
		r.setNoOfAdults(noOfAdults);
		System.out.print("Enter no of children: ");
		noOfChildren = validateChoice(noOfChildren, "Enter no of children: ");
		r.setNoOfChildren(noOfChildren);
				
		reservationList.add(r);
		
		fileIO.writeObject(reservationList.toArray(), Reservation.class);
		
		r.printReceipt();
	}

	/*
	 * This method allows the guest to check in with a valid reservation
	 */
	public void checkIn(Reservation res) {
		if (Objects.equals(res, null)) {
			System.out.println("Reservation does not exist");
			return;
		}
		else {
			if (res.getStatus() == Reservation.ResStatus.CONFIRMED) {
				res.setStatus(Reservation.ResStatus.CHECKED_IN);
				res.getRoom().setAvailabilityStatus(Room.AvailabilityStatus.OCCUPIED);
				fileIO.writeObject(reservationList.toArray(), Reservation.class);
				roomMgr.writeToFile();
				System.out.println("Guest checked in");
			}
			else {
				System.out.println("Reservation does not exist");
			}
		}
	}
	
	/*
	 * This method changes a reservation status to expired
	 */
	public void expireAReservation() {
		Reservation res = searchReservation();
		if (Objects.equals(res, null)) {
			System.out.println("Reservation does not exist");
			return;
		}
		else {
			res.setStatus(Reservation.ResStatus.EXPIRED);
		}
	}

	/*
	 * This method checks out the guest from the hotel
	 */
	public Reservation checkOut() {
		Reservation res = null;
		res = searchReservation();
		if (Objects.equals(res, null)) {
			System.out.println("Reservation does not exist");
			return res;
		}
		else {
			res.getRoom().setAvailabilityStatus(Room.AvailabilityStatus.VACANT);
			res.setCheckOutDate(LocalDate.now());
			res.setStatus(Reservation.ResStatus.CHECKED_OUT);
			
			fileIO.writeObject(reservationList.toArray(), Reservation.class);
			roomMgr.writeToFile();
			
			return res;
		}
	}
}
