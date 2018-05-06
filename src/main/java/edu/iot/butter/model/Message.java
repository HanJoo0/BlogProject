package edu.iot.butter.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message {
	private String title;
	private String message;
	private String linkTitle;
	private String link;
}
