package com.example.geektrust.model;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
/**
 * @author Simran Hotchandani
 * @date 29/10/22
 */

public class PassengerSummary {

	private HashMap<String,Passenger> passengerMap;
	private List<PassengerCheckIn> checkInList;
	private Integer totalAmountAirport;
	private Integer totalAmountCentral;
	private Integer totalDiscountAirport;
	private Integer totalDiscountCentral;
	private List<String> orderByTypeAirport;
	private List<String> orderByTypeCentral;
	
	public List<String> getOrderByTypeAirport() {
		return orderByTypeAirport;
	}

	public void setOrderByTypeAirport(List<String> orderByTypeAirport) {
		this.orderByTypeAirport = orderByTypeAirport;
	}

	public List<String> getOrderByTypeCentral() {
		return orderByTypeCentral;
	}

	public void setOrderByTypeCentral(List<String> orderByTypeCentral) {
		this.orderByTypeCentral = orderByTypeCentral;
	}

	public PassengerSummary() {
		this.passengerMap=new HashMap<>();
		this.checkInList=new ArrayList<>();
		this.totalAmountAirport=0;
		this.totalAmountCentral=0;
		this.totalDiscountAirport=0;
		this.totalDiscountCentral=0;
		this.orderByTypeAirport=new ArrayList<>();
		this.orderByTypeCentral=new ArrayList<>();
	}
	
	public Integer getTotalAmountAirport() {
		return totalAmountAirport;
	}

	public void setTotalAmountAirport(Integer totalAmountAirport) {
		this.totalAmountAirport = totalAmountAirport;
	}

	public Integer getTotalAmountCentral() {
		return totalAmountCentral;
	}

	public void setTotalAmountCentral(Integer totalAmountCentral) {
		this.totalAmountCentral = totalAmountCentral;
	}

	public Integer getTotalDiscountAirport() {
		return totalDiscountAirport;
	}

	public void setTotalDiscountAirport(Integer totalDiscountAirport) {
		this.totalDiscountAirport = totalDiscountAirport;
	}

	public Integer getTotalDiscountCentral() {
		return totalDiscountCentral;
	}

	public void setTotalDiscountCentral(Integer totalDiscountCentral) {
		this.totalDiscountCentral = totalDiscountCentral;
	}

	public HashMap<String, Passenger> getPassengerMap() {
		return passengerMap;
	}
	public void setPassengerMap(HashMap<String, Passenger> passengerMap) {
		this.passengerMap = passengerMap;
	}
	public List<PassengerCheckIn> getCheckInList() {
		return checkInList;
	}
	public void setCheckInList(List<PassengerCheckIn> checkInList) {
		this.checkInList = checkInList;
	}
	
	
}
