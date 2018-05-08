/*package edu.iot.butter.controller;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import edu.iot.butter.model.Image;
import edu.iot.butter.model.Pagination;
import edu.iot.butter.service.ImageService;

@RestController
@RequestMapping("/api/gallery")
public class GalleryApiController {

	@Autowired
	ImageService service;
	
	@RequestMapping(method=RequestMethod.GET)
	public List<Image> list(
			@RequestParam(value="page", defaultValue="1") int page) throws Exception {
		Pagination pagination = service.getPagination(page);
		return service.getList(pagination);
	}
	
	@RequestMapping(value="/{imageId}", method=RequestMethod.GET)
	public Image get(@PathVariable int imageId) throws Exception {
		return service.getImage(imageId);
	}	
	
	
	@RequestMapping(method=RequestMethod.POST)	
	public boolean uploadSubmit(
					@Valid Image image, 
					BindingResult result,
					@RequestParam("file") MultipartFile file) throws Exception {

		if(result.hasErrors()) return false;
	
		service.saveImage(image, file);
		return true;

	}
	
}*/
