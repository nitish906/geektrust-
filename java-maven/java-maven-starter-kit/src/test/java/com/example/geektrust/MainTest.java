package com.example.geektrust;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MainTest {
	
	private Main metroCardService;
	
	@BeforeEach
	void setUp() {
		metroCardService=new Main(); 
	}
	
	@Test
	public void input1() {
		String filePath="src/test/java/resources/MetroCard_Input1";
		String actualOutput=metroCardService.processMetroCard(filePath);
		
		String expectedOutput="TOTAL_COLLECTION CENTRAL 300 0\n"
				+ "PASSENGER_TYPE_SUMMARY\n"
				+ "ADULT 1\n"
				+ "SENIOR_CITIZEN 1\n"
				+ "TOTAL_COLLECTION AIRPORT 403 100\n"
				+ "PASSENGER_TYPE_SUMMARY\n"
				+ "ADULT 2\n"
				+ "KID 2";
		
		assertEquals(actualOutput.trim(), expectedOutput);
	}
	
	@Test
	public void input2() {
		String filePath="src/test/java/resources/MetroCard_Input2";
		String actualOutput=metroCardService.processMetroCard(filePath);
		
		String expectedOutput="TOTAL_COLLECTION CENTRAL 504 50\n"
				+ "PASSENGER_TYPE_SUMMARY\n"
				+ "ADULT 2\n"
				+ "KID 1\n"
				+ "SENIOR_CITIZEN 1\n"
				+ "TOTAL_COLLECTION AIRPORT 151 100\n"
				+ "PASSENGER_TYPE_SUMMARY\n"
				+ "ADULT 1\n"
				+ "KID 1";
		
		assertEquals(actualOutput.trim(), expectedOutput);
	}
	
	@Test
	public void input3() {
		String filePath="src/test/java/resources/MetroCard_Input3";
		String actualOutput=metroCardService.processMetroCard(filePath);
		
		String expectedOutput="TOTAL_COLLECTION CENTRAL 300 50\n"
				+ "PASSENGER_TYPE_SUMMARY\n"
				+ "ADULT 1\n"
				+ "KID 1\n"
				+ "SENIOR_CITIZEN 1\n"
				+ "TOTAL_COLLECTION AIRPORT 225 125\n"
				+ "PASSENGER_TYPE_SUMMARY\n"
				+ "SENIOR_CITIZEN 1\n"
				+ "ADULT 1\n"
				+ "KID 1";
		
		assertEquals(actualOutput.trim(), expectedOutput);
	}
	
	@Test
	public void input4() {
		String filePath="src/test/java/resources/MetroCard_Input4";
		String actualOutput=metroCardService.processMetroCard(filePath);
		
		String expectedOutput="TOTAL_COLLECTION CENTRAL 457 50\n"
				+ "PASSENGER_TYPE_SUMMARY\n"
				+ "ADULT 2\n"
				+ "SENIOR_CITIZEN 1\n"
				+ "TOTAL_COLLECTION AIRPORT 252 100\n"
				+ "PASSENGER_TYPE_SUMMARY\n"
				+ "ADULT 1\n"
				+ "SENIOR_CITIZEN 1\n"
				+ "KID 1";
		
		assertEquals(actualOutput.trim(), expectedOutput);
	}
}