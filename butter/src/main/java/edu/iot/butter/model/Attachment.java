package edu.iot.butter.model;

import java.util.Date;

import org.springframework.ui.Model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Attachment {
	private int 	attachmentId;
	private int		boardId;	// 게시글 ID
	private String	fileName;	// 원본 파일명
	private String 	location;	// 서버에서 파일명
	private Date 	regDate;	// 등록일
	
	public Attachment(int boardId, String fileName, String location) {
		super();
		this.boardId = boardId;
		this.fileName = fileName;
		this.location = location;
	}

	
	public void readyDownload(String dir, Model model) {
		String path = dir +  location;
		model.addAttribute("type", "application/octet-stream");
		model.addAttribute("path", path);
		model.addAttribute("fileName", fileName);
	}
	
}
