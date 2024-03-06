package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.UserVo;

@Repository
public class UserDao {

	@Autowired
	private SqlSession sqlSession;
	
	// 로그인 - 한명데이터 가져오기
	public UserVo userSelectByIdPw(UserVo userVo) {
		return sqlSession.selectOne("user.selectByIdPw", userVo);
	}
	
	// 회원가입
	public int insertPerson(UserVo userVo) {
		return sqlSession.insert("user.insertPerson",userVo);
	}
	
	// 회원정보수정폼 - 회원정보가져오기
	public UserVo selectByNo(int no) {
		return sqlSession.selectOne("user.selectByNo", no);
	}
	
	// 회원정보수정
	public int update(UserVo userVo) {
		return sqlSession.update("user.update",userVo);
	}
}
