package com.example.geektrust.service;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import com.example.geektrust.exception.ProcessingException;
import com.example.geektrust.model.EachJourneyCharge;
import com.example.geektrust.model.InputCommands;
import com.example.geektrust.model.Passenger;
import com.example.geektrust.model.PassengerCheckIn;
import com.example.geektrust.model.PassengerSummary;
import com.example.geektrust.model.StationStatistics;
import com.example.geektrust.utility.InputChecks;
import com.example.geektrust.utility.InputChecksImpl;
/**
 * @author Simran Hotchandani
 * @date 29/10/22
 */

public class CardExecutionServiceImpl implements CardExecutionService {

	//metrocard data
	private PassengerSummary summary=new PassengerSummary();

	//executes given set of commands from input file
	@Override
	public String executeCommands(List<InputCommands> arguments) {

		String output="";
		for (InputCommands input : arguments) {
			
			//validate incoming input 
			InputChecksImpl inputCheck=new InputChecksImpl();
			inputCheck.commandChecks(input);
			
			if (input.getCommand().equals("BALANCE")) {
				initializeBalance(input.getToken());
			} else if (input.getCommand().equals("CHECK_IN")) {
				processCheckIn(input.getToken());
			} else if (input.getCommand().equals("PRINT_SUMMARY")) {
				output= printSummary();
				System.out.print(output);
			} else {
				throw new ProcessingException("Invalid Input Commands, please check the input command.");
			}
		}
		return output;
	}

	//Called when invoked BALANCE command
	//Initializes user, user's balance
	//adds user to metrocard
	@Override
	public Passenger initializeBalance(List<String> tokens) {
		String id = tokens.get(0);
		Integer balance = Integer.parseInt(tokens.get(1));
		Passenger passenger = new Passenger(id, balance);
		summary.getPassengerMap().put(id, passenger);
		return passenger;
	}

	//Called when invoked CHECK_IN command
	//adds checkIn journey to journey List
	@Override
	public void processCheckIn(List<String> tokens) {
		PassengerCheckIn checkedIn = intializeCheckList(tokens);
		EachJourneyCharge journeyCharge = updateBalance(checkedIn.getMetroCardNumber(), checkedIn.getCharge());
		checkedIn.setJourneyCharge(journeyCharge);
		if(checkedIn.getFromStation().equals("AIRPORT") && !summary.getOrderByTypeAirport().contains(checkedIn.getPassengerType())) {
			summary.getOrderByTypeAirport().add(checkedIn.getPassengerType());
		}
		if(checkedIn.getFromStation().equals("CENTRAL") && !summary.getOrderByTypeCentral().contains(checkedIn.getPassengerType())) {
			summary.getOrderByTypeCentral().add(checkedIn.getPassengerType());
		}
		summary.getCheckInList().add(checkedIn);
	}

	//Called when invoked PRINT_SUMMARY Command
	@Override
	public String printSummary() {
		String output= calculateStationStatistics(summary.getCheckInList());
		return output;
	}
	
	//Calculates Station Statistics and sets output in expected format
	public String calculateStationStatistics(List<PassengerCheckIn> checkedIn) {
		
		//filter journey from station "AIRPORT" group by Passenger Type and calculate statistics for "AIRPORT" station
		Map<String,List<PassengerCheckIn>> passengerAtAirport=checkedIn.stream().filter(current->current.getFromStation().equals("AIRPORT")).collect(Collectors.groupingBy(current->current.getPassengerType()));
		List<StationStatistics> airportStats=calcEachStationStatistics("AIRPORT",passengerAtAirport);
		String airportSummary=createSummary("AIRPORT", airportStats);
		
		//filter journey from station "CENTRAL" group by Passenger Type and calculate statistics for "CENTRAL" station
		Map<String,List<PassengerCheckIn>> passengerAtCentral=checkedIn.stream().filter(current->current.getFromStation().equals("CENTRAL")).collect(Collectors.groupingBy(current->current.getPassengerType()));
		List<StationStatistics> centralStats=calcEachStationStatistics("CENTRAL",passengerAtCentral);
		String centralSummary=createSummary("CENTRAL", centralStats);
		
		//Output Construction
		String output="";
		output+="TOTAL_COLLECTION CENTRAL "+summary.getTotalAmountCentral()+" "+summary.getTotalDiscountCentral()+"\n";
		output+="PASSENGER_TYPE_SUMMARY\n";
		output+=centralSummary;
		output+="TOTAL_COLLECTION AIRPORT "+summary.getTotalAmountAirport()+" "+summary.getTotalDiscountAirport()+"\n";
		output+="PASSENGER_TYPE_SUMMARY\n";
		output+=airportSummary;
		
		return output;	
	}
	
	//Returns Output of Statistics as a list
	public String createSummary(String station,List<StationStatistics> stationStats) {
		String output="";
		int totalCharge=0,totalDiscount=0;
		for(StationStatistics stats:stationStats) {
			totalCharge+=stats.getTotalCharge();
			totalDiscount+=stats.getTotalDiscount();
			output+=stats.getPassengerType()+" "+stats.getCount()+"\n";
		}
		if(station.equals("AIRPORT")) {
			summary.setTotalAmountAirport(totalCharge);
			summary.setTotalDiscountAirport(totalDiscount);
		}
		else if(station.equals("CENTRAL")) {
			summary.setTotalAmountCentral(totalCharge);
			summary.setTotalDiscountCentral(totalDiscount);
		}
		return output;
	}
	
	//Input - Map Key(PassengerType) Value(Every Journey detail for that passenger type)
	//Output- Returns Station Statistics List - Contains For particular passenger type, total passengers, total charge, total discount
	public List<StationStatistics> calcEachStationStatistics(String fromStation,Map<String,List<PassengerCheckIn>> passengerAtStation) {
		List<StationStatistics> stationStatsList=new ArrayList<>();
		
		//traversing passenger Type
		for(Map.Entry<String, List<PassengerCheckIn>> current: passengerAtStation.entrySet()) {
			StationStatistics statistics=new StationStatistics(current.getKey());
			if(fromStation=="AIRPORT") {
				statistics.setOrderByType(summary.getOrderByTypeAirport().indexOf(current.getKey()));
			}
			if(fromStation=="CENTRAL") {
				statistics.setOrderByType(summary.getOrderByTypeCentral().indexOf(current.getKey()));
			}
			//traversing through every journey
			for(PassengerCheckIn passenger: current.getValue()) {
				statistics.setCount(statistics.getCount()+1);
				statistics.setTotalCharge(statistics.getTotalCharge()+passenger.getJourneyCharge().getCostOfJourney());
				statistics.setTotalDiscount(statistics.getTotalDiscount()+passenger.getJourneyCharge().getDiscount());
			}
			stationStatsList.add(statistics);
		}
		stationStatsList=sortBasedOnHighestAmount(stationStatsList);
		return stationStatsList;
	}
	
	//sort given PassengerType List according to value field(Total Amount) descending
	public List<StationStatistics> sortBasedOnHighestAmount(List<StationStatistics> stationStatsList) {
		String output="";
		Comparator<StationStatistics> compareByAmount=(StationStatistics s1,StationStatistics s2) -> s2.getTotalCharge().compareTo(s1.getTotalCharge());
		Comparator<StationStatistics> compareByType=(StationStatistics s1,StationStatistics s2) -> s1.getOrderByType().compareTo(s2.getOrderByType());
		Collections.sort(stationStatsList,compareByAmount.thenComparing(compareByType));
		return stationStatsList;
	}

	//Checks In user with given details
	public PassengerCheckIn intializeCheckList(List<String> tokens) {
		String id = tokens.get(0);
		String type = tokens.get(1);
		String station = tokens.get(2);
		PassengerCheckIn checkedIn = new PassengerCheckIn(id, type, station);
		return checkedIn;
	}

	//calculates total amount to be paid, discount
	//updates user balance accordingly
	public EachJourneyCharge updateBalance(String id, int charge) {
		int totalAmountCollected = 0,discount=0;
		Passenger currentPassenger = summary.getPassengerMap().get(id);
		if(currentPassenger==null) {
			throw new ProcessingException("Metrocard User Not Registered");
		}
		currentPassenger.setJourneyCount(currentPassenger.getJourneyCount() + 1);
		
		//Return journey, 50% discount calculated
		if (currentPassenger.getJourneyCount() % 2 == 0) {
			charge = charge / 2;
			discount=charge;
		}
		
		//Insufficient Balance, Calculate service fee and balance required
		//SufficientBalance, Deduct Money from metrocard
		if (currentPassenger.getBalanceInTheMetrocard() < charge) {
			int balanceRequired = charge - currentPassenger.getBalanceInTheMetrocard();
			totalAmountCollected += 0.02 * balanceRequired;
			currentPassenger.setBalanceInTheMetrocard(0);
		} else {
			currentPassenger.setBalanceInTheMetrocard(currentPassenger.getBalanceInTheMetrocard() - charge);
		}
		totalAmountCollected += charge;
		
		//stores discount, amount paid during this journey
		EachJourneyCharge journeyCharge=new EachJourneyCharge(discount,totalAmountCollected);
		return journeyCharge;
	}
}
