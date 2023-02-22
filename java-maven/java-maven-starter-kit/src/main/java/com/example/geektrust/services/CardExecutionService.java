package com.example.geektrust.services;

import java.util.List;

import com.example.geektrust.model.InputCommands;
import com.example.geektrust.model.Passenger;


public interface CardExecutionService {

	  //executes given set of commands from input file
	  public String executeCommands(List<InputCommands> arguments);
	  
	  //BALANCE command execution
	  public Passenger initializeBalance(List<String> tokens);
	  
	  //CHECK_IN command execution
	  public void processCheckIn(List<String> tokens);
	  
	  //PRINT_SUMMARY command execution
	  public String printSummary();
}
