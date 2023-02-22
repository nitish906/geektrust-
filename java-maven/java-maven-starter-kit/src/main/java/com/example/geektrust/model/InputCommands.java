package com.example.geektrust.model;

import java.util.List;
/**
 * @author Simran Hotchandani
 * @date 29/10/22
 */

public class InputCommands {

	private String command;
	private List<String> token;
	
	public String getCommand() {
		return command;
	}
	public void setCommand(String command) {
		this.command = command;
	}
	public List<String> getToken() {
		return token;
	}
	public void setToken(List<String> token) {
		this.token = token;
	}
	
	public InputCommands(String command, List<String> token) {
		super();
		this.command = command;
		this.token = token;
	}
	@Override
	public boolean equals(Object obj) {
		if(this==obj) {
			return true;
		}
		if(obj==null || this.getClass()!=obj.getClass()) {
			return false;
		}
		InputCommands inputCommand=(InputCommands)obj;;
		return this.command.equals(inputCommand.command)
				&& this.token.equals(inputCommand.token);
	}
}
