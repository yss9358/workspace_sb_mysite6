package com.javaex.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.GuestbookService;
import com.javaex.vo.GuestbookVo;

@Controller
public class ApiGuestbookController {

	@Autowired
	private GuestbookService guestbookService;
	
	@ResponseBody // return 의 데이터를 json으로 변경해서 응답문서의 body에 붙여서 보낸다
	@RequestMapping(value="/api/guestbooks", method = RequestMethod.GET) // Handler Mapping
	public List<GuestbookVo> list() {
//		System.out.println("ApiGuestbookController>list");
//		guestbookService.exeList();
//		System.out.println(guestbookService.exeList());
		// 글자로 표현할때는 
		return guestbookService.exeList();
	}
	
}
