package com.javaex.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.TboardVo;

@Repository
public class TboardDao {

	@Autowired
	private SqlSession sqlSession;

	// 리스트(검색X,페이징X)
	public List<TboardVo> boardSelectList() {
		System.out.println("TboardDao.boardSelectList()");

		List<TboardVo> boardList = sqlSession.selectList("tboard.selectList");

		return boardList;

	}


	// 글 전체 갯수 //리스트(검색X,페이징X)
	public int selectTotalCount() {
//		System.out.println("TboardDao.selectTotalCount()");

		int totalCount = sqlSession.selectOne("tboard.selectTotalCount");

		return totalCount;
	}
	
	
	// 리스트2(검색X, 페이징 O)
	public List<TboardVo> boardSelectList2(Map<String, Integer> limitMap){
//		System.out.println("TboardDao>boardSelectList2()");
//		System.out.println(limitMap);
		
		List<TboardVo> boardList= sqlSession.selectList("tboard.selectList2",limitMap);
//		System.out.println(boardList);
		return boardList;
	}
	
	
	// 리스트3(검색O, 페이징 O)
	public List<TboardVo> boardSelectList3(Map<String, Object> limitMap){
		List<TboardVo> boardList= sqlSession.selectList("tboard.selectList3",limitMap);
		return boardList;
	}
	
	// 글 전체 갯수3 //리스트(검색O,페이징O)
	public int selectTotalCount3(String keyword) {
		int totalCount = sqlSession.selectOne("tboard.selectTotalCount3", keyword);
		return totalCount;
	}

}
