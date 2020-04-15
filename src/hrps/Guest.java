package hrps;

import java.io.Serializable;

public class Guest implements Serializable{
	
	private static final long serialVersionUID = 1234L;
	private int gId;
	private String id;
	private String name;
	private CreditCard creditCard;
	private String address;
	private String country;
	private String gender;
	private String nationality;
	private int contact;

	/*
	 * Constructor for Guest class
	 * Initialize some variables
	 * Creates a credit card class too
	 */
	public Guest(int gId, String id, String name, String address, String country, String gender, String nationality, int contact, CreditCard.CardType cType, long cardNo, int cvv, String exp) {
		creditCard = new CreditCard(name, address, country, cType, cardNo, cvv, exp);
		
		this.gId = gId;
		this.id = id;
		this.name = name;
		this.address = address;
		this.country = country;
		this.gender = gender;
		this.nationality = nationality;
		this.contact = contact;
	}
	
	/* 
	 * This method returns the id of guest object
	 * Used in ReservationMgr
	 */
	public int getGId() {
		return gId;
	}
	
	/*
	 * This method sets the id of guest object
	 */
	public void setGId(int gId) {
		this.gId = gId;
	}
	
	/*
	 * This method returns the guest's id
	 */
	public String getId() {
		return id;
	}
	
	/* 
	 * This method sets the guest's id
	 */
	public void setId(String id) {
		this.id = id;
	}
	
	/*
	 * This method returns the guest's name
	 */
	public String getName() {
		return name;
	}
	
	/*
	 * This method sets the guest's name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/*
	 * This method returns the guest's address
	 */
	public String getAddress() {
		return address;
	}
	
	/*
	 * This method sets the guest's address
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	
	/*
	 * This method returns the guest's country
	 */
	public String getCountry() {
		return country;
	}
	
	/*
	 * This method sets the guest's country
	 */
	public void setCountry(String country) {
		this.country = country;
	}
	
	/*
	 * This method returns the guest's gender
	 */
	public String getGender() {
		return gender;
	}
	
	/*
	 * This method sets the guest's gender
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	/*
	 * This method returns the guest's nationality
	 */
	public String getNationality() {
		return nationality;
	}
	
	/*
	 * This method sets the guest's nationality
	 */
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	
	/*
	 * This method returns the guest's contact
	 */
	public int getContact() {
		return contact;
	}
	
	/*
	 * This method sets the guest's contact
	 */
	public void setContact(int contact) {
		this.contact = contact;
	}

	/*
	 * This method returns guest's credit card object
	 */
	public CreditCard getCreditCard() {
		return creditCard;
	}

	/*
	 * This method sets the credit card object
	 */
	public void setCreditCard(CreditCard creditCard) {
		this.creditCard = creditCard;
	}
}
