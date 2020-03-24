package hrps;

import java.time.DayOfWeek;
import java.time.temporal.ChronoField;
import java.time.LocalDate;
import java.time.Period;

public class Payment extends Reservation{
	private enum PaymentType{
		CREDITCARD, 
		CASH;
	}
	
	private enum ResStatus {
		CHECKED_OUT;
	}
	private double taxPrice;
	private PaymentType paymentType;
	private double discountPrice;
	
	public Payment(float tp, PaymentType pt) {
		this.taxPrice = tp;
		this.paymentType = pt;
	}
	public float calculateBill() {
		float totalPrice = 0;
		Period stay = Period.between(super.getcheckOutDate(), super.getcheckInDate()); //Get the period between check in date and check out date.
	  	int totalDays = stay.getDays(); //Calculate the total days between check in date and check out date.
	  	DayOfWeek day;
		for(int i=0; i<days; i++){
			day = DayOfWeek.of((super.getcheckInDate().plusDays(i).get(ChronoField.DAY_OF_WEEK))); //Get the name of each day.
			if(day == day.SATURDAY || day == day.SUNDAY){
				totalPrice += (((super.getR().getRoomServicePrice() + (super.getR().getrate()*(totalDays))) * (1 - discountPrice)) * taxPrice);
			}else{
				totalPrice += (((super.getR().getRoomServicePrice() + (super.getR().getrate()*(totalDays))) * (1 - discountPrice) * 1.15) * taxPrice);
			}
		}
		return totalPrice;
	}
	public void checkOut() {
		ResStatus rs = ResStatus.CHECKED_OUT;
		super.setStatus(rs);
	}
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
