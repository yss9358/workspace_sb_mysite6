package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GalleryVo;

@Repository
public class GalleryDao {

	@Autowired
	private SqlSession sqlSession;
	
	// 전체리스트 가져오기
	public List<GalleryVo> listAll() {
		return sqlSession.selectList("gallery.listAll");
	}
	
	// 등록
	public int insert(GalleryVo galleryVo) {
		return sqlSession.insert("gallery.insert", galleryVo);
	}
	
}
