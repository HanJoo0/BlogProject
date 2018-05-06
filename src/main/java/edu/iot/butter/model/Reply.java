package edu.iot.butter.model;

import java.util.Date;

import lombok.Data;

@Data
public class Reply {
	private int 	replyId;	// 글 번호
	private int 	boardId;	// 글 그룹
	private int		replyLevel;	// 댓글 수준
	private	int		parent;		// 상위 글 번호 
	private String 	writer;		// 작성자 ID
	private String	content;	// 내용
	private int		deleted;
	private Date	regDate;
	private Date	updateDate;
}
