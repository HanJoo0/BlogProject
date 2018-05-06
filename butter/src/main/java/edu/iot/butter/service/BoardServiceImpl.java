package edu.iot.butter.service;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import edu.iot.butter.dao.AttachmentDao;
import edu.iot.butter.dao.BoardDao;
import edu.iot.butter.model.Attachment;
import edu.iot.butter.model.Board;
import edu.iot.butter.model.Pagination;

@Service
public class BoardServiceImpl implements BoardService {
	@Autowired
	BoardDao dao;
	
	@Autowired
	AttachmentDao attachmentDao;

	
	@Override
	public Pagination getPagination(int page) throws Exception {
		int total = dao.getCount();
		Pagination pagination = new Pagination(page, total);
		return pagination;
	}

	@Override
	public List<Board> getList(Pagination pagination) throws Exception {
		return dao.selectList(pagination);
	}

	@Override
	public Board getBoard(int boardId) throws Exception {
		dao.increaseReadCnt(boardId); 		// 조회수 증대 
		Board board = dao.selectOne(boardId);
		board.setAttachments(attachmentDao.selectList(boardId));		
		
		return board;
	}

	@Transactional
	@Override
	public boolean create(Board board, List<MultipartFile> fileList) throws Exception {
		int result = dao.insert(board);
		upload(board.getBoardId(), fileList);
		return result == 1;
	}

	@Transactional
	@Override
	public boolean update(Board board, List<MultipartFile> fileList) throws Exception {
		int result = dao.update(board);
		upload(board.getBoardId(), fileList);
		
		return result==1;
	}
/*
	@Transactional
	@Override
	public boolean delete(Board board) throws Exception {
		// 첨부파일 먼저 삭제
		attachmentDao.deleteByBoardId(board.getBoardId());
		dao.delete(board);
		return true;
	}

	@Transactional
	@Override
	// 관리자에 의한 삭제
	public boolean delete(int boardId) throws Exception {
		// 첨부파일 먼저 삭제
		attachmentDao.deleteByBoardId(boardId);
		dao.delete(boardId);
		
		return true;
	}

*/

	public boolean upload(int boardId, List<MultipartFile> fileList) throws Exception {
		for(MultipartFile file : fileList) {
			if(!file.isEmpty()) {
				Attachment attachment = save(boardId, file);	
				// DB 저장 
				attachmentDao.insert(attachment);
			}	
		}
		return true;
	}
	
	public Attachment save(int boardId, MultipartFile file) throws Exception {
		long fileNo = System.currentTimeMillis();
		String fname = file.getOriginalFilename();
		String newName = fileNo + "_" + fname;
		String path = "c:/temp/upload/" + newName;
		
		file.transferTo(new File(path));
		
		return new Attachment(boardId, fname, newName);
	}

	@Override
	public Attachment getAttachment(int attachmentId) throws Exception  {
		return 	attachmentDao.selectOne(attachmentId);
	}

	@Transactional
	@Override
	public boolean deleteAttachment(int attachmentId) throws Exception {
		return attachmentDao.delete(attachmentId) ==1;
	}
	
	
	
}



