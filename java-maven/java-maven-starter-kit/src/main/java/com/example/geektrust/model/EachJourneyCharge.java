package com.example.geektrust.model;
/**
 * @author Simran Hotchandani
 * @date 29/10/22
 */

public class EachJourneyCharge {

	private Integer discount;
	private Integer costOfJourney;
	
	public Integer getDiscount() {
		return discount;
	}

	public void setDiscount(Integer discount) {
		this.discount = discount;
	}

	public Integer getCostOfJourney() {
		return costOfJourney;
	}

	public void setCostOfJourney(Integer costOfJourney) {
		this.costOfJourney = costOfJourney;
	}

	public EachJourneyCharge(Integer discount, Integer costOfJourney) {
		super();
		this.discount = discount;
		this.costOfJourney = costOfJourney;
	}
	
}
