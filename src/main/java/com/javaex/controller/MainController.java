package com.javaex.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

	// 메인
	@RequestMapping(value="/main", method = {RequestMethod.GET,RequestMethod.POST})
	public String index() {
		return "main/index";
	}
}
