package com.javaex.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.TboardDao;
import com.javaex.vo.TboardVo;

@Service
public class TboardService {

	@Autowired
	private TboardDao tboardDao;
	
	//리스트(검색X,페이징 X)
	public List<TboardVo> exeList(){
		System.out.println("TboardService.exeList()");
		
		List<TboardVo> boardList = tboardDao.boardSelectList();
		
		return boardList;
	}
	
	// 리스트2(검색X, 페이징 O)
	public Map<String, Object> exeList2(int crtPage){
//		System.out.println("exeList2()");
//		System.out.println(crtPage);
		
		//////////////////////////////////////////
		// 리스트가져오기
		//////////////////////////////////////////
		
		
//		crtPage
//		아래 if문과 같음. 
//		(조건) ? 조건이 참일때 값 : 조건이 거짓일때 값
		crtPage = (crtPage > 0) ? crtPage : (crtPage = 1);
		
//		if(crtPage > 0) {
//			crtPage = crtPage
//		} else {
//			crtPage = 1;
//		}
//		

//		한페이지당 출력 글 갯수
		int listCount = 10;
		
//		시작번호 row번호
//		1페이지 -> 1~10 , 2페이지 -> 11~20, 3페이지 -> 21~30
//		    limit 0,10 ,     limit 10,10,     limit 20,10  1번부터 가져오려면 가져오려는 번호-1 부터 시작해야함
//		    (현재페이지-1) * 글갯수 -> 시작번호
		int startRowNo = (crtPage-1)*listCount;
		
//		startRowNo , listCount 를 Map 으로 묶어서 넘긴다.
//		넘겨줄 값을 Map에 넣고 별명을 붙여준다. - 나중에 꺼낼때 필요
		Map<String, Integer> limitMap = new HashMap<String, Integer>();
		limitMap.put("startRowNo", startRowNo);
		limitMap.put("listCount", listCount);
		
		List<TboardVo> boardList = tboardDao.boardSelectList2(limitMap);
//		System.out.println(boardList);
		
		
		
		
		///////////////////////////////////////////////////////
		// 페이징계산
		//////////////////////////////////////////////////////
		
		// 페이지당 버튼 갯수
		int pageBtnCount = 5;
		
		// 전체 글 갯수
		int totalCount = tboardDao.selectTotalCount();
		
		// 마지막 버튼 번호
		// 1페이지 -> (1,2,3,4,5)
		// 2페이지 -> (1,2,3,4,5)
		// 3페이지 -> (1,2,3,4,5)
		// 4페이지 -> (1,2,3,4,5)
		// 5페이지 -> (1,2,3,4,5)
		// 6페이지 -> (6,7,8,9,10)
		// 현재 페이지 1
		// 1 2 3 4 5  -> 올림(현재페이지/5) -> 0.2(1)*5 -> 5
		// 5개씩 같은 식이 나온다
		
		// 마지막 버튼 번호
		int endPageBtnNo = (int)Math.ceil(crtPage/(double)pageBtnCount)*pageBtnCount;
		// 시작 버튼 번호
		// 마지막 버튼 번호에서 페이지당버튼 갯수를 빼고 +1해준다
		int startPageBtnNo = endPageBtnNo - pageBtnCount + 1;
		
		// 다음 화살표 유무
		boolean next = false;
		
		// 다음 화살표는 기본적으로 안보임. 전체 글 갯수가 가져온 리스트보다 많으면 -> 더 보여줄 내용이 있으면 다음화살표가 활성화
		// 한페이지당 글의 갯수(10개씩) * 마지막버튼 번호(5) < 전체 글 갯수(187) -> 다음화살표가 있어야함
		if(listCount*endPageBtnNo < totalCount) { // 전체 글 갯수가 더 남았을때
			next = true;
		} else { // 다음 화살표가 없을때 - false일때
			// 
			endPageBtnNo = (int)Math.ceil((totalCount/(double)listCount));
		}
		
		
		// 이전 화살표 유무
		boolean prev = false;
		if(startPageBtnNo != 1) {
			prev = true;
		}
		
		// 보내야할 값 
		// boardList , startPageBtnNo, endPageBtnNo, prev, next -> Map 으로 묶어서 보낸다
		Map<String, Object> boardMap = new HashMap<String, Object>();
		boardMap.put("boardList", boardList);
		boardMap.put("startPageBtnNo", startPageBtnNo);
		boardMap.put("endPageBtnNo", endPageBtnNo);
		boardMap.put("prev", prev);
		boardMap.put("next", next);
		
//		System.out.println(boardMap);
		
		return boardMap;
	}

	
	
	
	
	
	// 리스트3(검색O, 페이징 O)
		public Map<String, Object> exeList3(int crtPage, String keyword){
			crtPage = (crtPage > 0) ? crtPage : (crtPage = 1);
//			한페이지당 출력 글 갯수
			int listCount = 10;
			
//			시작번호 row번호
			int startRowNo = (crtPage-1)*listCount;
			
//			startRowNo , listCount 를 Map 으로 묶어서 넘긴다.
			Map<String, Object> limitMap = new HashMap<String, Object>();
			limitMap.put("startRowNo", startRowNo);
			limitMap.put("listCount", listCount);
			limitMap.put("keyword", keyword);
			
			List<TboardVo> boardList = tboardDao.boardSelectList3(limitMap);
			
			// 페이지당 버튼 갯수
			int pageBtnCount = 5;
			
			// 전체 글 갯수
			int totalCount = tboardDao.selectTotalCount3(keyword);
			
			// 마지막 버튼 번호
			int endPageBtnNo = (int)Math.ceil(crtPage/(double)pageBtnCount)*pageBtnCount;

			// 시작 버튼 번호
			int startPageBtnNo = endPageBtnNo - pageBtnCount + 1;
			
			// 다음 화살표 유무
			boolean next = false;
			
			// 다음 화살표는 기본적으로 안보임. 전체 글 갯수가 가져온 리스트보다 많으면 -> 더 보여줄 내용이 있으면 다음화살표가 활성화
			if(listCount*endPageBtnNo < totalCount) { // 전체 글 갯수가 더 남았을때
				next = true;
			} else { // 다음 화살표가 없을때 - false일때
				// 
				endPageBtnNo = (int)Math.ceil((totalCount/(double)listCount));
			}
			
			// 이전 화살표 유무
			boolean prev = false;
			if(startPageBtnNo != 1) {
				prev = true;
			}
			
			// 보내야할 값 
			Map<String, Object> boardMap = new HashMap<String, Object>();
			boardMap.put("boardList", boardList);
			boardMap.put("startPageBtnNo", startPageBtnNo);
			boardMap.put("endPageBtnNo", endPageBtnNo);
			boardMap.put("prev", prev);
			boardMap.put("next", next);
			
			return boardMap;
		}
	
	
}
