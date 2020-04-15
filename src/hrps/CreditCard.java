package hrps;

import java.io.Serializable;

public class CreditCard implements Serializable{
	public static enum CardType {
		MASTER, VISA;
	}
	
	private static final long serialVersionUID = 1234L;
	long cardNo; 
	int cvv;
	CardType cType;
	String name, address, country, exp;
		
	/*
	 * Constructor for CreditCard class
	 * Initialize some variables
	 */
	public CreditCard(String name, String address, String country, CreditCard.CardType cType, long cardNo, int cvv, String exp) {
		this.name = name;
		this.address = address;
		this.country = country;
		this.cType = cType;
		this.cardNo = cardNo;
		this.cvv = cvv;
		this.exp = exp;
	}

	/*
	 * This method returns the cardNo
	 */
	public long getCardNo() {
		return cardNo;
	}

	/*
	 * This method sets the cardNo
	 */
	public void setCardNo(long cardNo) {
		this.cardNo = cardNo;
	}

	/*
	 * This method returns the cvv
	 */
	public int getCvv() {
		return cvv;
	}

	/*
	 * This method sets the cvv
	 */
	public void setCvv(int cvv) {
		this.cvv = cvv;
	}

	/*
	 * This method returns the card type
	 */
	public CardType getcType() {
		return cType;
	}

	/*
	 * This method sets the card type
	 */
	public void setcType(CardType cType) {
		this.cType = cType;
	}

	/*
	 * This method returns the name of the credit card
	 */
	public String getName() {
		return name;
	}

	/*
	 * This method sets the name of the credit card
	 */
	public void setName(String name) {
		this.name = name;
	}

	/*
	 * This method returns the address of the credit card
	 */
	public String getAddress() {
		return address;
	}

	/*
	 * This method sets the address of the credit card
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/*
	 * This method returns the country
	 */
	public String getCountry() {
		return country;
	}

	/*
	 * This method sets the country
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/* 
	 * This method returns the credit card's expiry date
	 */
	public String getExp() {
		return exp;
	}

	/*
	 * This method sets the credit card's expiry date
	 */
	public void setExp(String exp) {
		this.exp = exp;
	}
	
}