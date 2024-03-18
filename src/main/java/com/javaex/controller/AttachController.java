package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.service.AttachService;

@Controller
public class AttachController {

	@Autowired
	private AttachService attachService;
	
	// 업로드폼 불러오기
	@RequestMapping(value="/attach/uploadform", method = {RequestMethod.GET,RequestMethod.POST})
	public String uploadForm() {
		return "attach/form";
	}
	
	// 업로드
	@RequestMapping(value="/attach/upload", method = RequestMethod.POST)
	public String upload(@RequestParam(value="file") MultipartFile file, Model model) {
		model.addAttribute("saveName", attachService.exeUpload(file));
		return "attach/result";
	}
	
}
