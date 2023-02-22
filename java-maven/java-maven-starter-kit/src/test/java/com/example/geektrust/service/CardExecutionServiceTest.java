package com.example.geektrust.service;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.example.geektrust.model.EachJourneyCharge;
import com.example.geektrust.model.PassengerCheckIn;
import com.example.geektrust.model.StationStatistics;
/**
 * @author Simran Hotchandani
 * @date 29/10/22
 */

public class CardExecutionServiceTest {

	CardExecutionServiceImpl service;
	
	@BeforeEach
	void setUp() {
		service=new CardExecutionServiceImpl(); 
	}
	
	@Test
	public void passengerTypeChargeIntializedTest() {
		List<String> tokens=new ArrayList<>();
		tokens.add("MC1");
		tokens.add("SENIOR_CITIZEN");
		tokens.add("AIRPORT");
		assertEquals(100, service.intializeCheckList(tokens).getCharge());
	}
	
	@Test
	public void calculateEachStationStatisticsCheck() {
		Map<String,List<PassengerCheckIn>> passengerAtStation=new HashMap<>();
		//Input Initialization
		List<PassengerCheckIn> seniorCitizenList=new ArrayList<>();
		PassengerCheckIn seniorCitizen1=new PassengerCheckIn("MC1", "SENIOR_CITIZEN", "CENTRAL");
		seniorCitizen1.setJourneyCharge(new EachJourneyCharge(0, 100));
		seniorCitizenList.add(seniorCitizen1);
		passengerAtStation.put("SENIOR_CITIZEN", seniorCitizenList);
		
		List<PassengerCheckIn> adultList=new ArrayList<>();
		PassengerCheckIn adultCitizen1=new PassengerCheckIn("MC3", "ADULT", "CENTRAL");
		adultCitizen1.setJourneyCharge(new EachJourneyCharge(0, 200));
		adultList.add(adultCitizen1);
		PassengerCheckIn adultCitizen2=new PassengerCheckIn("MC3", "ADULT", "CENTRAL");
		adultCitizen2.setJourneyCharge(new EachJourneyCharge(100, 100));
		adultList.add(adultCitizen2);
		passengerAtStation.put("ADULT", adultList);
		
		//Expected Output Construction
		List<StationStatistics> stationStatsList=new ArrayList<>();
		
		StationStatistics stationStatsAdult=new StationStatistics("ADULT");
		stationStatsAdult.setCount(2);
		stationStatsAdult.setTotalCharge(300);
		stationStatsAdult.setTotalDiscount(100);
		stationStatsList.add(stationStatsAdult);
		
		StationStatistics stationStatsSeniorCitizen=new StationStatistics("SENIOR_CITIZEN");
		stationStatsSeniorCitizen.setCount(1);
		stationStatsSeniorCitizen.setTotalCharge(100);
		stationStatsSeniorCitizen.setTotalDiscount(0);
		stationStatsList.add(stationStatsSeniorCitizen);
		
		//Calling method to test
		List<StationStatistics> output=service.calcEachStationStatistics("AIRPORT",passengerAtStation);

		//Testing
		assertEquals(output, stationStatsList);
	}
	
	@Test
	public void createSummaryTest() {
		String station="AIRPORT";
		
		List<StationStatistics> stationStatsList=new ArrayList<>();
		StationStatistics stationStatsAdult=new StationStatistics("ADULT");
		stationStatsAdult.setCount(2);
		stationStatsAdult.setTotalCharge(300);
		stationStatsAdult.setTotalDiscount(100);
		stationStatsList.add(stationStatsAdult);
		
		StationStatistics stationStatsSeniorCitizen=new StationStatistics("SENIOR_CITIZEN");
		stationStatsSeniorCitizen.setCount(1);
		stationStatsSeniorCitizen.setTotalCharge(100);
		stationStatsSeniorCitizen.setTotalDiscount(0);
		stationStatsList.add(stationStatsSeniorCitizen);
				
		String expectedOutput="ADULT 2\nSENIOR_CITIZEN 1\n";
		
		String actualOutput=service.createSummary(station, stationStatsList);
		assertEquals(expectedOutput, actualOutput);
	}
	
	@Test
	public void createPassengerCheckInTest() {
		List<String> tokens=new ArrayList<>();
		tokens.add("MC1");
		tokens.add("SENIOR_CITIZEN");
		tokens.add("AIRPORT");
		
		PassengerCheckIn checkedInExpected=new PassengerCheckIn("MC1", "SENIOR_CITIZEN", "AIRPORT");
		checkedInExpected.setCharge("SENIOR_CITIZEN");
		assertEquals(checkedInExpected, service.intializeCheckList(tokens));
	}
	

}
