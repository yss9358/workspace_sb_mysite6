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
	public BoardVo selectOneByNo(int no) {
		return sqlSession.selectOne("board.selectOneByNo", no);
	}
	
	// 수정
	public int update(BoardVo boardVo) {
		return sqlSession.update("board.update", boardVo);
	}
	
	// 조회수 업데이트
	public int updateHit(int no) {
		return sqlSession.update("board.updateHit", no);
	}
	
	// 이름으로 검색
	public List<BoardVo> selectByName(String name) {
		return sqlSession.selectList("selectByName", name);
	}
}

