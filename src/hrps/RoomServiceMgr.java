package hrps;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Objects;

public class RoomServiceMgr {
	
	private ArrayList<RoomService> roomServiceList;	// this list contains all the room service across different reservation
	private FileIO fileIO;
	private Scanner sc;
	private int counter = 1;
	
	public RoomServiceMgr(Scanner sc) {
		//System.out.println("Room Service Mgr");
		
		roomServiceList = new ArrayList<RoomService>();
		fileIO = new FileIO();
		this.sc = sc;
		
		Object[] objArray = fileIO.readObject(RoomService.class);
		for (Object o : objArray) {
			RoomService rs = (RoomService) o;
			roomServiceList.add(rs);
		}
		
		counter = roomServiceList.size() + 1;
	}
	
	public RoomService orderRoomService(ArrayList<MenuItem> miList) {
		RoomService rs = new RoomService();
		String remarks = "";
		
		System.out.print("Remarks: ");
		remarks = sc.nextLine();
		rs.placeOrder(counter, remarks, miList);
		roomServiceList.add(rs);
		fileIO.writeObject(roomServiceList.toArray(), RoomService.class);
		counter++;
		
		return rs;
	}

	public void removeRoomService(RoomService rs) {
		if (Objects.equals(rs, null)) {
			System.out.println("Room service does not exist");
			return;
		}
		else {
			roomServiceList.remove(rs);
			fileIO.writeObject(roomServiceList.toArray(), RoomService.class);
		}
	}
	
	public void updateRoomService(RoomService rs) {
		int choice = 0;
		
		if (Objects.equals(rs, null)) {
			System.out.println("Room service does not exist");
			return;
		}
		else {
			do {
				updateRoomServiceMenu();
				choice = validateChoice(choice, "Enter choice: ");
				switch (choice) {
					case 1:
						rs.setOrderStatus(RoomService.OrderType.CONFIRMED);
						break;
					case 2:
						rs.setOrderStatus(RoomService.OrderType.PREPARING);
						break;
					case 3:
						rs.setOrderStatus(RoomService.OrderType.DELIVERED);
						break;
					default:
						System.out.println("Invalid Choice");
						break;
				}
			} while (choice != 1 && choice != 2 && choice != 3);
		}
		fileIO.writeObject(roomServiceList.toArray(), RoomService.class);
	}
	
	private void updateRoomServiceMenu() {
		System.out.println("\n+----------------------+");
		System.out.println("| Select order status: |");
		System.out.println("| 1. Confirmed         |");
		System.out.println("| 2. Preparing         |");
		System.out.println("| 3. Delivered         |");
		System.out.println("+----------------------+");
		System.out.print("Enter choice: ");
	}
	
	private int validateChoice(int choice, String inputText) {
		boolean valid = false;
		
		while (!valid) {
			if (!sc.hasNextInt()) {
				System.out.println("Invalid input. Please enter an integer");
				sc.nextLine();
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

	public RoomService getRoomServiceById(int id) {
		RoomService rs = null;
		for (RoomService temp : roomServiceList) {
			if (temp.getId() == id) {
				rs = temp;
				break;
			}
		}
		return rs;
	}
}
