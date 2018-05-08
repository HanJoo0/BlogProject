package edu.iot.butter.model;

import java.util.Date;
import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;

@Data
public class Board {
	private int		boardId;
	@NotEmpty(message="제목은 필수항목입니다.")
	private String 	title;
	@NotEmpty(message="작성자는 필수항목입니다.")
	private String 	writer;
	private String 	password;
	private int		readCnt;
	
	private List<Attachment> attachments;
	private String	content;
	private Date	regDate;
	private Date	updateDate;
}
