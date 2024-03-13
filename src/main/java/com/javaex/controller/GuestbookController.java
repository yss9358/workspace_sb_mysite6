package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.GuestbookService;
import com.javaex.vo.GuestbookVo;

@Controller
public class GuestbookController {

	@Autowired
	private GuestbookService guestbookService;
	
	// 등록폼 + 리스트
	@RequestMapping(value="/guest/list", method = {RequestMethod.GET, RequestMethod.POST})
	public String list(Model model) {
		model.addAttribute("guestbookList",guestbookService.exeList());
		return "guestbook/addlist";
	}
	
	// 등록
	@RequestMapping(value="/guest/add", method = {RequestMethod.GET, RequestMethod.POST})
	public String add(@ModelAttribute GuestbookVo guestbookVo) {
		guestbookService.exeAdd(guestbookVo);
		return "redirect:/guest/list";
	}
	
	// 삭제폼
	@RequestMapping(value="/guest/deleteform", method = {RequestMethod.GET, RequestMethod.POST})
	public String deleteForm(@RequestParam(value="no") int no) {
		return "guestbook/deleteForm";
	}
	
	// 삭제
	@RequestMapping(value="/guest/delete", method = {RequestMethod.GET, RequestMethod.POST})
	public String delete(@ModelAttribute GuestbookVo guestbookVo) {
		guestbookService.exeDelete(guestbookVo);
		return "redirect:/guest/list";
	}
	
	// ajax 방명록 메인
	@RequestMapping(value="/guest/ajaxindex", method = {RequestMethod.GET, RequestMethod.POST})
	public String ajaxIndex() {
		System.out.println("controller>ajaxIndex");
		return "guestbook/ajaxIndex";
	}
	
	
	
}
