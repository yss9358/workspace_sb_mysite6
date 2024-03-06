package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BoardVo;

@Repository
public class BoardDao {

	@Autowired
	private SqlSession sqlSession;
	
	// 리스트 가져오기
	public List<BoardVo> list() {
		return sqlSession.selectList("board.list");
	}

	// 글쓰기
	public int insert(BoardVo boardVo) {
		return sqlSession.insert("board.insert",boardVo);
	}
	
	// 삭제
	public int delete(int no) {
		return sqlSession.delete("board.delete", no);
	}
	
	// 읽기 - 한명데이터 가져오기
	public BoardVo selectOne(int no) {
		return sqlSession.selectOne("board.selectOne", no);
	}
	
	// 수정
	public int update(BoardVo boardVo) {
		return sqlSession.update("board.update", boardVo);
	}
}

