package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.GuestbookDao;
import com.javaex.vo.GuestbookVo;

@Service
public class GuestbookService {

	@Autowired
	private GuestbookDao guestbookDao;
	
	// 리스트가져오기
	public List<GuestbookVo> exeList() {
		return guestbookDao.list();
	}
	
	// 등록
	public int exeAdd(GuestbookVo guestbookVo) {
		int count = guestbookDao.insert(guestbookVo);
		return count;
	}
	
	// 삭제
	public int exeDelete(GuestbookVo guestbookVo) {
		int count = guestbookDao.delete(guestbookVo);
		return count;
	}
	
	// ajax 등록
	public GuestbookVo exeAddAndGuest(GuestbookVo guestbookVo) {
		guestbookDao.insertSelectKey(guestbookVo);
		return guestbookDao.selectOneByNo(guestbookVo.getNo());
	}
	
	// ajax 삭제
	public void exeDeleteAndGuest(GuestbookVo guestbookVo) {
		
	}
	
	
}
