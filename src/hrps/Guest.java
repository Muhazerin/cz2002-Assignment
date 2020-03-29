package hrps;

import java.util.Scanner;

public class Guest {
	private String id;
	private String name;
	private CreditCard creditCard;
	private String address;
	private String country;
	private String gender;
	private String nationality;
	private int contact;
	
	public Guest(String id, String name, String address, String country, String gender, String nationality, int contact) {
		Scanner sc = new Scanner(System.in);
		long cardNo = 0;
		int cvv = 0;
		String exp;
		CreditCard.CardType cType = selectCardType();
		
		System.out.print("Enter card number: ");
		cardNo = sc.nextLong();
		sc.nextLine();	// clear the "\n" in the buffer
		System.out.print("Enter cvv: ");
		cvv = sc.nextInt();
		sc.nextLine();	// clear the "\n" in the buffer
		System.out.print("Enter expiry date (mm/yy): ");
		exp = sc.nextLine();
		
		creditCard = new CreditCard(name, address, country, cType, cardNo, cvv, exp);
		
		this.id = id;
		this.name = name;
		this.address = address;
		this.country = country;
		this.gender = gender;
		this.nationality = nationality;
		this.contact = contact;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public int getContact() {
		return contact;
	}
	public void setContact(int contact) {
		this.contact = contact;
	}

	public CreditCard getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(CreditCard creditCard) {
		this.creditCard = creditCard;
	}
	
	public static CreditCard.CardType selectCardType() {
		int cChoice = -1;
		CreditCard.CardType cType = null;
		Scanner sc = new Scanner(System.in);
		
		do {
			System.out.println("\n+-------------------+");
			System.out.println("| Select card type: |");
			System.out.println("| 1. Mastercard     |");
			System.out.println("| 2. Visa           |");
			System.out.println("+-------------------+");
			System.out.print("Enter choice: ");
			cChoice = sc.nextInt();
			sc.nextLine();	// clear the "\n" in the buffer
			switch (cChoice) {
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
		} while (cChoice != 1 && cChoice != 2);
		return cType;
	}
}
