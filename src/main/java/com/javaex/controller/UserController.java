package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.UserService;
import com.javaex.vo.UserVo;

import jakarta.servlet.http.HttpSession;


@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	// 회원가입폼
	@RequestMapping(value="/user/joinform", method = {RequestMethod.GET, RequestMethod.POST})
	public String joinForm() {
		System.out.println("joinform");
		return "user/joinForm";
	}
	
	// 회원가입
	@RequestMapping(value="/user/join", method = {RequestMethod.GET, RequestMethod.POST})
	public String join(@ModelAttribute UserVo userVo) {
		userService.exeJoin(userVo);
		return "user/joinOk";
	}
	
	// 로그인폼
	@RequestMapping(value="/user/loginform", method= {RequestMethod.GET, RequestMethod.POST})
	public String loginForm() {
		return "user/loginForm";
	}
	
	// 로그인
	@RequestMapping(value="/user/login", method = {RequestMethod.GET, RequestMethod.POST})
	public String login(@ModelAttribute UserVo userVo, HttpSession session) {
		UserVo authUser = userService.exeLogin(userVo);
		session.setAttribute("authUser", authUser);
		if(authUser != null) {
			return "redirect:/main";
		} else {
			return "redirect:/user/loginform";
		}
	}
	
	// 로그아웃
	@RequestMapping(value="/user/logout", method = {RequestMethod.GET, RequestMethod.POST})
	public String logOut(HttpSession session) {
		session.invalidate();
		return "redirect:/main";
	}
	
	// 수정폼
	@RequestMapping(value="/user/modifyform", method = {RequestMethod.GET, RequestMethod.POST})
	public String modifyForm(@RequestParam(value="no") int no, Model model) {
		model.addAttribute("selectVo",userService.exeSelectOne(no));
		return "user/modifyForm";
	}
	
	// 수정
	@RequestMapping(value="/user/modify", method = {RequestMethod.GET, RequestMethod.POST})
	public String modify(@ModelAttribute UserVo userVo,HttpSession session) {
		userService.exeModify(userVo);
		session.removeAttribute("authUser");
		session.setAttribute("authUser", userService.exeSelectOne(userVo.getNo()));
		return "redirect:/main";
	}

}
