package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BoardDao;
import com.javaex.vo.BoardVo;

@Service
public class BoardService {

	@Autowired
	private BoardDao boardDao;
	
	// 리스트폼 - 리스트 가져오기
	public List<BoardVo> exeList() {
		return boardDao.list();
	}
	
	// 글쓰기
	public int exeWrite(BoardVo boardVo) {
		int count = boardDao.insert(boardVo);
		return count;
	}
	
	// 삭제
	public int exeDelete(int no) {
		int count =boardDao.delete(no);
		return count;
	}
	
	// 한명데이터 가져오기
	public BoardVo exeRead(int no) {
		boardDao.updateHit(no); // 조회수 업데이트
		return boardDao.selectOne(no);
	}
	
	// 수정
	public int exeModify(BoardVo boardVo) {
		int count = boardDao.update(boardVo);
		return count;
	}
	
}
