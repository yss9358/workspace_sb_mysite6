package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.AttachVo;

@Repository
public class AttachDao {

	@Autowired
	private SqlSession sqlSession;
	
	// 파일 업로드
	public int insertFile(AttachVo attachVo) {
		return sqlSession.insert("attach.insertFile", attachVo);
	}
	
}
