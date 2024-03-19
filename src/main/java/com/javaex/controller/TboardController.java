package com.javaex.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.TboardService;
import com.javaex.vo.TboardVo;

@Controller
public class TboardController {

	@Autowired
	private TboardService tboardService;
	
	//리스트(검색X,페이징 X)
	@RequestMapping(value="/tboard/list", method= {RequestMethod.GET, RequestMethod.POST})
	public String list(Model model) {
		System.out.println("TboardController.list()");
		
		//boardService를 통해서 리스트를 가져온다
		List<TboardVo> boardList = tboardService.exeList();
		//모델에 리스트를 담는다(포워드)
		model.addAttribute("boardList", boardList);
		
		return "tboard/list";
	}

	//리스트2(검색X,페이징 O)
	@RequestMapping(value="/tboard/list2", method= {RequestMethod.GET, RequestMethod.POST})
	public String list2(@RequestParam(value="crtPage",required=false,defaultValue="1") int crtPage,
						Model model) {
//		파라미터 crtPage가 없으면 에러가 남 -> 파라미터가 없을경우 기본값을 1로 처리 
//		없으면 값을 1로 만들어줌 , 음수입력한것과 다름
		
		// 리스트만 가져오는게 아니라 다른 값들도 받아와야해서 Map으로 묶은 값을 받아온다
		// Map 에서 필요한 값을 꺼내서 사용한다
		Map<String, Object> boardMap = tboardService.exeList2(crtPage);
//		System.out.println(boardMap);
		model.addAttribute("boardMap", boardMap);
	
		return "tboard/list2";
	}
	
	//리스트3(검색O,페이징 O)
	@RequestMapping(value="/tboard/list3", method= {RequestMethod.GET, RequestMethod.POST})
	public String list3(@RequestParam(value="crtPage",required = false,defaultValue="1") int crtPage,
						@RequestParam(value="keyword",required = false,defaultValue="") String keyword,
						Model model) {
		Map<String, Object> boardMap = tboardService.exeList3(crtPage, keyword);
		model.addAttribute("boardMap", boardMap);
	
		return "tboard/list3";
	}
	

	
}
