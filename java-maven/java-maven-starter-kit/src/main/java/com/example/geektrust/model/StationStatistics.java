package com.example.geektrust.model;
/**
 * @author Simran Hotchandani
 * @date 29/10/22
 */

public class StationStatistics {

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if(this==obj) {
			return true;
		}
		if(obj==null || this.getClass()!=obj.getClass()) {
			return false;
		}
		StationStatistics stats=(StationStatistics)obj;;
		return this.count.equals(stats.count)
				&& this.passengerType.equals(stats.passengerType)
				&& this.totalCharge.equals(stats.totalCharge)
				&& this.totalDiscount.equals(stats.totalDiscount);
	}
	private Integer count;
	private String passengerType;
	private Integer totalCharge;
	private Integer totalDiscount;
	private Integer orderByType;
	public Integer getOrderByType() {
		return orderByType;
	}
	public void setOrderByType(Integer orderByType) {
		this.orderByType = orderByType;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public String getPassengerType() {
		return passengerType;
	}
	public void setPassengerType(String passengerType) {
		this.passengerType = passengerType;
	}
	
	public Integer getTotalCharge() {
		return totalCharge;
	}
	public void setTotalCharge(Integer totalCharge) {
		this.totalCharge = totalCharge;
	}
	public Integer getTotalDiscount() {
		return totalDiscount;
	}
	public void setTotalDiscount(Integer totalDiscount) {
		this.totalDiscount = totalDiscount;
	}
	public StationStatistics(String passengerType) {
		super();
		this.passengerType=passengerType;
		this.totalCharge=0;
		this.totalDiscount=0;
		this.count=0;
	}
}
