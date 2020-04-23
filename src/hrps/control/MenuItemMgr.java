package control;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

import entity.MenuItem;

public class MenuItemMgr {
	private ArrayList<MenuItem> menuItemList;
	private int counter = 1;
	private Scanner sc;
	private FileIO fileIO;
	
	/*
	 * Constructor method for MenuItemMgr
	 * Initialize some variables and retrieve menu items from file
	 */
	public MenuItemMgr(Scanner sc) {
		//System.out.println("MenuItemMgr");
		
		menuItemList = new ArrayList<MenuItem>();
		this.sc = sc;
		fileIO = new FileIO();
		
		Object[] objArray = fileIO.readObject(MenuItem.class);
		for (Object o : objArray) {
			MenuItem mi = (MenuItem) o;
			menuItemList.add(mi);
		}
		counter = menuItemList.size() + 1;
	}
	
	/*
	 * This method adds menu item into menuItemList and update the file
	 */
	public void addMenuItem() {
		String name, desc;
		float price = 0;
		System.out.print("Enter name: ");
		name = sc.nextLine();
		
		//checks if menu item of same name already exists.
		if(searchMenuItems(name) != null) {
			//if it does print out error message and exit.
			System.out.println("Menu Item already exists.");
			return;
		}
		
		
		System.out.print("Enter description: ");
		desc = sc.nextLine();
		System.out.print("Enter price: $");
		price = validatePrice(price, "Enter price: $");
		menuItemList.add(new MenuItem(counter, name, desc, price));
		counter++;
		
		fileIO.writeObject(menuItemList.toArray(), MenuItem.class);
		
		System.out.println("New Menu Item added");
	}
	
	/*
	 * This method searches for the menu item in the menuItemList
	 * Returns the menu item if found
	 * Returns null if not found
	 */
	public MenuItem searchMenuItems(String name) {
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
	
	/*
	 * This method contains the update menu for updating menu items
	 */
	private void updateMenu() {
		System.out.println("\n+---------------------------------+");
		System.out.println("| What would you like to update ? |");
		System.out.println("| 0. Nothing                      |");
		System.out.println("| 1. Name                         |");
		System.out.println("| 2. Description                  |");
		System.out.println("| 3. Price                        |");
		System.out.println("| 4. All                          |");
		System.out.println("+---------------------------------+");
		System.out.print("Enter choice: ");
	}
	
	/*
	 * This method lists the menu item in the menuItemList
	 */
	public void listMenuItems() {
		if (menuItemList.size() == 0) {
			System.out.println("Menu is empty");
		}
		else {
			for (MenuItem mItem : menuItemList) {
				System.out.println("+---------------------------------+");
				System.out.println("ID: " + mItem.getID());
				System.out.println("Name: " + mItem.getName());
				System.out.println("Description: " + mItem.getDescription());
				System.out.println("Price: $" + mItem.getPrice() + "\n");
			}
		}
	}
	
	/*
	 * This method removes menu item from menuItemList and updates the file
	 */
	public void removeMenuItem(String name) {
		MenuItem mItem = searchMenuItems(name);
		if (mItem == null) {
			System.out.println("Menu item does not exist");
		}
		else {
			menuItemList.remove(mItem);
			
			fileIO.writeObject(menuItemList.toArray(), MenuItem.class);
			
			System.out.println("Menu item removed");
		}
	}

	/*
	 * This method updates a menu item in the menuItemList and updates the file
	 */
	public void updateMenuItem(String name) {
		MenuItem mItem = searchMenuItems(name);
		int option = 1;
		float price = 0;
		if (mItem == null) {
			System.out.println("Menu item does not exist");
		}
		else {
			do {
				updateMenu();
				option = sc.nextInt();
				sc.nextLine();	// clear "\n" in the buffer
				switch (option) {
					case 0:
						System.out.println("Going back...");
						break;
					case 1:
						System.out.print("Enter new name: ");
						mItem.setName(sc.nextLine());
						System.out.println("Menu Item updated");
						fileIO.writeObject(menuItemList.toArray(), MenuItem.class);
						break;
					case 2:
						System.out.print("Enter new description: ");
						mItem.setDescription(sc.nextLine());
						System.out.println("Menu Item updated");
						fileIO.writeObject(menuItemList.toArray(), MenuItem.class);
						break;
					case 3:
						System.out.print("Enter new price: $");
						price = validatePrice(price, "Enter new price: $");
						mItem.setPrice(price);
						sc.nextLine();	// clear the "\n" in the buffer
						System.out.println("Menu Item updated");
						fileIO.writeObject(menuItemList.toArray(), MenuItem.class);
						break;
					case 4:
						System.out.print("Enter new name: ");
						mItem.setName(sc.nextLine());
						System.out.print("Enter new description: ");
						mItem.setDescription(sc.nextLine());
						System.out.print("Enter new price: $");
						price = validatePrice(price, "Enter new price: $");
						mItem.setPrice(price);
						sc.nextLine();	// clear the "\n" in the buffer
						System.out.println("Menu Item updated");
						fileIO.writeObject(menuItemList.toArray(), MenuItem.class);
					default: 
						System.out.println("Invalid choice");
						break;
				}
			} while (option < 0 || option > 4);
		}
	}

	public ArrayList<MenuItem> selectMenuItem() {
		ArrayList<MenuItem> miList = new ArrayList<MenuItem>();
		MenuItem mi = null;
		String name = "";
		int choice = 1, temp = 1;
		
		this.listMenuItems();
		
		while (choice != 0) {
			System.out.println("Enter 0 to quit");
			System.out.print("Enter menu item name: ");
			if (sc.hasNextInt()) {
				temp = sc.nextInt();
				sc.nextLine();	// clear the "\n" in the buffer
				if (temp == 0) {
					choice = temp;
				}
				else {
					System.out.println("Invalid integer");
				}
			}
			else {
				name = sc.nextLine();
				mi = searchMenuItems(name);
				if (Objects.equals(mi, null)) {
					System.out.println("Menu Item doesn't exist");
				}
				else {
					miList.add(mi);
					System.out.println("Menu Item added");
					mi = null;
				}
			}
		}
		
		return miList;
	}
		
	/*
	 *  This method is used to check whether the user input inside sc's buffer is a price. 
	 * It will loop until the user inputs a price.
	 */
	private float validatePrice(float price, String input) {
		boolean valid = false;
		
		while (!valid) {
			if (!sc.hasNextFloat()) {
				System.out.println("Invalid Price. Please enter a price");
				sc.nextLine();	// clear the input in the buffer
				System.out.print(input);
			}
			else {
				valid = true;
				price = sc.nextFloat();
				sc.nextLine();	// clear the "\n" in the buffer
			}
		}
		
		return price;
	}
}
