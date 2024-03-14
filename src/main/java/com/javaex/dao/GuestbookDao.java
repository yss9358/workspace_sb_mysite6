package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GuestbookVo;

@Repository
public class GuestbookDao {
	
	@Autowired
	private SqlSession sqlSession;

	// 리스트 가져오기
	public List<GuestbookVo> list() {
		return sqlSession.selectList("guestbook.list");
	}
	
	// 등록
	public int insert(GuestbookVo guestbookVo) {
		return sqlSession.insert("guestbook.insert", guestbookVo);
	}
	
	// 삭제
	public int delete(GuestbookVo guestbookVo) {
		return sqlSession.delete("guestbook.delete",guestbookVo);
	}
	
	// ajax 등록
	public int insertSelectKey(GuestbookVo guestbookVo) {
		// 값을 등록시키고 no값을 넣은 값이 나옴
		return sqlSession.insert("guestbook.insertSelectKey",guestbookVo);
	}
	
	// no값으로 한명데이터 가져오기
	public GuestbookVo selectOneByNo(int no) {
		return sqlSession.selectOne("guestbook.selectOneByNo",no);
	}
}
