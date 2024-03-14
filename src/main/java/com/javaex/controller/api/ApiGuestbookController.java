package com.javaex.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.GuestbookService;
import com.javaex.vo.GuestbookVo;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class ApiGuestbookController {

	@Autowired
	private GuestbookService guestbookService;
	
	// 리스트 
	@ResponseBody // return 의 데이터를 json으로 변경해서 응답문서의 body에 붙여서 보낸다
	@RequestMapping(value="/api/guestbooks", method = RequestMethod.GET) // Handler Mapping
	public List<GuestbookVo> list() {
		// 보내줄 데이터에 따라 메소드 리턴값이 달라진다. 
		return guestbookService.exeList();
	}
	
	// 글 등록
	@ResponseBody
	@RequestMapping(value="/api/guestbooks", method = RequestMethod.POST)
	public GuestbookVo add(@ModelAttribute GuestbookVo guestbookVo) {
		return guestbookService.exeAddAndGuest(guestbookVo);
	}
	
	// 글 삭제
	@ResponseBody
	@RequestMapping(value="/api/guestbooks/delete", method = RequestMethod.POST)
	public int delete(@ModelAttribute GuestbookVo guestbookVo) {
		int count = guestbookService.exeDelete(guestbookVo);
		return count;
	}
	
}
