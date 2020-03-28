package hrps;

public class CreditCard {
	enum CardType {
		MASTER, VISA;
	}
	
	int cardNo, cvv;
	CardType cType;
	String name, address, country, exp;
	
	public CreditCard(String name, String address, String country) {
		this.name = name;
		this.address = address;
		this.country = country;
	}

	public int getCardNo() {
		return cardNo;
	}

	public void setCardNo(int cardNo) {
		this.cardNo = cardNo;
	}

	public int getCvv() {
		return cvv;
	}

	public void setCvv(int cvv) {
		this.cvv = cvv;
	}

	public CardType getcType() {
		return cType;
	}

	public void setcType(CardType cType) {
		this.cType = cType;
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

	public String getExp() {
		return exp;
	}

	public void setExp(String exp) {
		this.exp = exp;
	}
	
}
