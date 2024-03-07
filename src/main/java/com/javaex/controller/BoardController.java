package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.BoardService;
import com.javaex.vo.BoardVo;

@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	// 리스트폼 - 리스트 가져오기 
	@RequestMapping(value="/board/list", method = {RequestMethod.GET, RequestMethod.POST})
	public String list(Model model) {
		List<BoardVo> boardList =boardService.exeList();
		model.addAttribute("boardList",boardList);
		return "board/list";
	}
	
	// 글쓰기폼
	@RequestMapping(value="/board/writeform", method = {RequestMethod.GET, RequestMethod.POST})
	public String writeForm() {
		return "board/writeForm";
	}
	
	// 글쓰기
	@RequestMapping(value="/board/write", method = {RequestMethod.GET, RequestMethod.POST})
	public String write(@ModelAttribute BoardVo boardVo) {
		boardService.exeWrite(boardVo);
		return "redirect:/board/list";
	}
	
	// 삭제
	@RequestMapping(value="/board/delete", method = {RequestMethod.GET, RequestMethod.POST})
	public String delete(@RequestParam(value="no") int no) {
		boardService.exeDelete(no);
		return "redirect:/board/list";
	}
	
	// 읽기폼 - 한명데이터 가져오기
	@RequestMapping(value="/board/read", method = {RequestMethod.GET, RequestMethod.POST})
	public String read(@RequestParam(value="no") int no,Model model) {
		model.addAttribute("selectOneVo",boardService.exeRead(no));
		return "board/read";
	}
	
	// 수정폼
	@RequestMapping(value="/board/modifyform", method = {RequestMethod.GET, RequestMethod.POST})
	public String modifyForm(@RequestParam(value="no") int no, Model model) {
		model.addAttribute("selectOneVo",boardService.exeRead(no));
		return "board/modifyForm";
	}
	
	// 수정
	@RequestMapping(value="/board/modify", method = {RequestMethod.GET, RequestMethod.POST})
	public String modify(@ModelAttribute BoardVo boardVo) {
		boardService.exeModify(boardVo);
		return "redirect:/board/list";
	}
	
	
}
