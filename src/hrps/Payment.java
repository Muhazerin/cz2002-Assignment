package hrps;

import java.time.DayOfWeek;
import java.time.temporal.ChronoField;
import java.time.LocalDate;
import java.time.Period;

public class Payment{
	public enum PaymentType{
		CREDITCARD, 
		CASH;
	}
	
	private double taxPrice;
	private double discountPrice;
	private double weekendPrice;
	private PaymentType paymentType;
	private Reservation rs;
	
	
	
	
	public Payment(double dp, PaymentType pt, Reservation r) {
		this.taxPrice = 0.07;
		this.weekendPrice = 1.15;
		this.discountPrice = dp;
		this.paymentType = pt;
		this.rs = r;
	}
	
	public float calculateBill() {
		float totalPrice = 0;
		Period stay = Period.between(rs.getCheckOutDate(), rs.getCheckInDate()); //Get the period between check in date and check out date.
	  	int totalDays = stay.getDays(); //Calculate the total days between check in date and check out date.
	  	DayOfWeek day;
		for(int i=0; i<days; i++){
			day = DayOfWeek.of((rs.getCheckInDate().plusDays(i).get(ChronoField.DAY_OF_WEEK))); //Get the name of each day.
			if(day == day.SATURDAY || day == day.SUNDAY){
				totalPrice += (((rs.getRoom().getRoomServicePrice() + (rs.getRoom().getRate()*(totalDays))) * (1 - discountPrice)* 1.15) * (1 - taxPrice));
			}else{
				totalPrice += (((rs.getRoom().getRoomServicePrice() + (rs.getRoom().getRate()*(totalDays))) * (1 - discountPrice)) * (1 - taxPrice));
			}
		}
		return totalPrice;
	}
	
	public
	
	public double getTaxPrice() {
		return taxPrice;
	}
	public void setTaxPrice(double taxPrice) {
		this.taxPrice = taxPrice;
	}
	public double getDiscountPrice() {
		return discountPrice;
	}
	public void setDiscountPrice(double discountPrice) {
		this.discountPrice = discountPrice;
	}
}
