package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.GalleryService;
import com.javaex.vo.GalleryVo;


@Controller
public class GalleryController {

	@Autowired
	private GalleryService galleryService;
	
	// 전체 리스트 가져오기 
	@RequestMapping(value="/gallery/list", method = RequestMethod.GET)
	public String list(Model model) {
		model.addAttribute("galleryList",galleryService.exeList());
		return "gallery/list";
	}
	
	@RequestMapping(value="/gallery/upload", method = RequestMethod.POST)
	public void upload() {
		
	}

	@RequestMapping(value="/gallery/add", method = RequestMethod.POST)
	public String add(@ModelAttribute GalleryVo galleryVo, @RequestParam(value="file") MultipartFile file) {
		int count = galleryService.exeAdd(galleryVo,file);
		if(count == 1) {
			return "redirect:/gallery/list";
		} else {
			return "redirect:/main";
		}
	}
	
}
