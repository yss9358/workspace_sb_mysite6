package com.javaex.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.UserDao;
import com.javaex.vo.UserVo;

@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	// 로그인
	public UserVo exeLogin(UserVo userVo) {
		return userDao.userSelectByIdPw(userVo);
	}
	
	// 회원가입
	public int exeJoin(UserVo userVo) {
		int count = userDao.insertPerson(userVo);
		return count;
	}
	
	// 회원정보수정폼 - 회원정보가져오기
	public UserVo exeSelectOne(int no) {
		return userDao.selectByNo(no);
	}
	
	// 회원정보수정
	public int exeModify(UserVo userVo) {
		int count = userDao.update(userVo);
		System.out.println(count);
		return count;
	}
}
