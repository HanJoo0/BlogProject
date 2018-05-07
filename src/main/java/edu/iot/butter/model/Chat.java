package edu.iot.butter.model;

import java.util.List;

import lombok.Data;

public class Chat {
	@Data
	public static class Room {
		private String name;
		private List<String> members;
	}
	
	@Data
	public static class Message {
		private ChatCommand	command;
		private String userId; 
		private List<String> args;
		private String message;
	}
}
