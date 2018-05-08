package edu.iot.butter.service;

import java.io.File;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import edu.iot.butter.dao.ImageDao;
import edu.iot.butter.model.Image;
import edu.iot.butter.model.Member;
import edu.iot.butter.model.Pagination;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;


@Service
public class ImageServiceImpl implements ImageService {
	
	@Autowired 
	ImageDao dao;

	@Override
	public Pagination getPagination(int page) throws Exception {
		int total = dao.getCount();
		Pagination pagination = new Pagination(page, total, 12);
		return pagination;
	}

	@Override
	public List<Image> getList(Pagination pagination) throws Exception {
		return dao.selectList(pagination);
	}

	@Override
	public Image getImage(int id)  throws Exception {
		return dao.selectOne(id);
	}

	@Override
	public boolean upload(Image image, List<MultipartFile> fileList ) throws Exception {
		
		for(MultipartFile file : fileList) {
			if(!file.isEmpty()) {
				saveImage(image, file);
				dao.insert(image);				
			}	
		}
		return true;
	}
	
	@Autowired
	ServletContext context;
	
	@Override
	public void saveImage(Image image, MultipartFile file) throws Exception {
		String fileName = file.getOriginalFilename();
		String newName = saveImage(fileName, file);
		String thumbName = saveThumb(newName);
		

        String mimeType = context.getMimeType(fileName);
        
		image.setFileName(fileName);
		image.setMimeType(mimeType);
		image.setFileSize(file.getSize());
		image.setNewName(newName);
		image.setThumbName(thumbName);
	}
	
	
	
	public String saveImage(String fname, MultipartFile file) throws Exception{
		long fileNo = System.currentTimeMillis();
		String newName = fileNo + "_" + fname;
		String path = IMAGE_DIR + "/" + newName;
		
		file.transferTo(new File(path));			
		return newName;
	}
	
	public String saveThumb(String fname) throws Exception{
		String thumbName = "thumbnail-" + fname;

		Thumbnails
			.of(new File(IMAGE_DIR + "/" + fname))
			.crop(Positions.CENTER)
			.size(200, 200)
			.toFile(new File(THUMB_DIR + "/" + thumbName));
					
		return thumbName;
	}
		
}
