package hrps;

import java.util.ArrayList;
import java.util.Scanner;

public class MenuItemMgr {
	private ArrayList<MenuItem> menuItemList = new ArrayList<MenuItem>();
	private static int counter = 1;
	
	public void addMenuItem() {
		String name, desc;
		float price;
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter name: ");
		name = sc.nextLine();
		System.out.print("Enter description: ");
		desc = sc.nextLine();
		System.out.print("Enter price: $");
		price = sc.nextFloat();
		sc.nextLine();	// clear the "\n" in the buffer
		menuItemList.add(new MenuItem(counter, name, desc, price));
		counter++;
		System.out.println("Menu Item added");
	}
	
	private MenuItem searchMenuItems(String name) {
		if (menuItemList.size() == 0) {
			return null;
		}
		else {
			for (MenuItem mItem : menuItemList) {
				if (name.equalsIgnoreCase(mItem.getName())) {
					return mItem;
				}
			}
			return null;
		}
	}
	
	private void showOptions() {
		System.out.println("+---------------------------------+");
		System.out.println("| What would you like to update ? |");
		System.out.println("| 1. Name                         |");
		System.out.println("| 2. Description                  |");
		System.out.println("| 3. Price                        |");
		System.out.println("| 4. All                          |");
		System.out.println("+---------------------------------+");
		System.out.print("Enter choice: ");
	}
	
	public void listMenuItems() {
		if (menuItemList.size() == 0) {
			System.out.println("Menu is empty");
		}
		else {
			for (MenuItem mItem : menuItemList) {
				System.out.println("Name: " + mItem.getName());
				System.out.println("Description: " + mItem.getDescription());
				System.out.println("Price: " + mItem.getPrice() + "\n");
			}
		}
	}
	
	public void removeMenuItem(String name) {
		MenuItem mItem = searchMenuItems(name);
		if (mItem == null) {
			System.out.println("Menu item does not exist");
		}
		else {
			menuItemList.remove(mItem);
			System.out.println("Menu item removed");
		}
	}

	public void updateMenuItem(String name) {
		MenuItem mItem = searchMenuItems(name);
		int option = 1;
		Scanner sc = new Scanner(System.in);
		if (mItem == null) {
			System.out.println("Menu item does not exist");
		}
		else {
			do {
				showOptions();
				option = sc.nextInt();
				sc.nextLine();	// clear "\n" in the buffer
				switch (option) {
					case 1:
						System.out.print("Enter new name: ");
						mItem.setName(sc.nextLine());
						System.out.println("Menu Item updated");
						break;
					case 2:
						System.out.print("Enter new description: ");
						mItem.setDescription(sc.nextLine());
						System.out.println("Menu Item updated");
						break;
					case 3:
						System.out.print("Enter new price: $");
						mItem.setPrice(sc.nextFloat());
						sc.nextLine();	// clear the "\n" in the buffer
						System.out.println("Menu Item updated");
						break;
					case 4:
						System.out.print("Enter new name: ");
						mItem.setName(sc.nextLine());
						System.out.print("Enter new description: ");
						mItem.setDescription(sc.nextLine());
						System.out.print("Enter new price: $");
						mItem.setPrice(sc.nextFloat());
						sc.nextLine();	// clear the "\n" in the buffer
						System.out.println("Menu Item updated");
					default: 
						System.out.println("Invalid choice");
						break;
				}
			} while (option < 1 || option > 4);
		}
	}
}
