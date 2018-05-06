package edu.iot.butter.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Talk {
	private String	command;	// BROADCAST, TALK
	
	private	int		talkId;		// talk ID
	private String	userId;		// 사용자 ID
	private String	withTalk;	// 대상 사용자 ID
	private int		received;	// 수신 여부 0: 발신, 1: 수신
	private int		checked;	// 확인 여부
	private String	message;	// 메시지
	private Date	regDate;	// 등록일


}
