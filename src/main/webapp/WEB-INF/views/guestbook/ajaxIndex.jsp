<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link href="${pageContext.request.contextPath}/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/guestbook.css" rel="stylesheet" type="text/css">

<!-- Axios 라이브러리 포함 -->
<script src="https://cdn.jsdelivr.net/npm/axios/dist/axios.min.js"></script>

<style>
	/* 모달창 배경 회색부분*/
	.modal {
		width : 100%; /*가로 전체*/
		height : 100%;  /*세로 전체*/
		display : none; /* 시장할때 숨김처리*/
		position : fixed; /* 화면 고정*/
		left : 0; /*왼쪽에서 0에서 시작*/
		top : 0 ; /* 위쪽에서 0 에서 시작*/
		z-index : 999; /*제일위에*/
		overflow : auto; /*내용이 많으면 스크롤 생김*/
		background-color : rgba(0,0,0,0.4); /*배경이 검정색에 반투명*/
 	}
 	/* 모달창 내용 흰색부분 */
	.modal .modal-content{
		width : 400px;
		margin : 100px auto 100px auto; /*위에서 100xp 상함 100xp  - 좌우에서 가운데*/
		padding : 0px 20px 20px 20px; /*안쪽 여백*/
		background-color : #ffffff; /*배경색 흰색*/
		border : 1px solid #888888; /*테두리 모양 색*/
	}
	/* 닫기 버튼 */
	.modal .modal-content .closeBtn{
		text-align : right;
		color : #aaaaaa;
		font-size : 28px;
		font-weight : bold;
		cursor : pointer;
	}
</style>
</head>

<body>
	<div id="wrap">

		<c:import url="/WEB-INF/views/include/header.jsp"></c:import>
	
		<div id="container" class="clearfix">
			<div id="aside">
				<h2>방명록</h2>
				<ul>
					<li>일반방명록</li>
					<li><a href="${pageContext.request.contextPath}/guest/ajaxindex">ajax방명록</a></li>
				</ul>
			</div>
			<!-- //aside -->

			<div id="content">
				
				<div id="content-head" class="clearfix">
					<h3>일반방명록</h3>
					<div id="location">
						<ul>
							<li>홈</li>
							<li>방명록</li>
							<li class="last">일반방명록</li>
						</ul>
					</div>
				</div>
				<!-- //content-head -->

				<div id="guestbook">
					<form id="guestForm" action="" method="">
						<table id="guestAdd">
							<colgroup>
								<col style="width: 70px;">
								<col>
								<col style="width: 70px;">
								<col>
							</colgroup>
							<tbody>
								<tr>
									<th><label class="form-text" for="input-uname">이름</label></th>
									<td><input id="input-uname" type="text" name="name"></td>
									<th><label class="form-text" for="input-pass">패스워드</label></th>
									<td><input id="input-pass"type="password" name="password"></td>
								</tr>
								<tr>
									<td colspan="4"><textarea name="content" cols="72" rows="5"></textarea></td>
								</tr>
								<tr class="button-area">
									<td colspan="4" class="text-center"><button id="btnAdd" type="submit">등록</button></td>
								</tr>
							</tbody>
							
						</table>
						<!-- //guestWrite -->
						
					</form>	
					
					<!-- 모달 창 컨텐츠 -->
	               <div id="myModal" class="modal">
	                  <div id="guestbook" class="modal-content">
	                     <div class="closeBtn">×</div>
	                     <div class="m-header">패스워드를 입력하세요</div>
	                     <div class="m-body">
	                        <input type="password" name="password" value=""><br>
	                        <input type="hidden" name="no" value="">
	                     </div>
	                     <div class="m-footer">
	                        <button class="btnDelete" type="submit">삭제</button>
	                     </div>
	                  </div>
	               </div>
	               
					<div id="guestbookListArea">
						<!-- 방명록 글 올 자리 -->
					</div>
					
				</div>
				<!-- //guestbook -->
			
			</div>
			<!-- //content  -->
		</div>
		<!-- //container  -->

		<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>
	</div>
	<!-- //wrap -->

</body>

<script>

// DOM tree가 생성되었을때
document.addEventListener("DOMContentLoaded",function(){
		
	// 리스트 가져오기 + 그리기 메소드
	getListAndRender();

	// 저장하기
	document.querySelector("#guestForm").addEventListener("submit", addAndRender);
	
	// 모달창 호출 버튼 클릭했을 때  
	document.querySelector("#guestbookListArea").addEventListener("click", callModal); 
	
	// 모달창 닫기 버튼 클릭했을 때 
	document.querySelector(".closeBtn").addEventListener("click", closeModal); 
	
	// 삭제버튼 클릭 - 삭제기능
	document.querySelector("#myModal button").addEventListener("click", deleteAndRemove); 
	
}); // document.addEventListener 끝

//////////////////////////// 함수 정리 ///////////////////////////////////////////////


	// getListAndRender 함수 - 리스트 가져오기 + 그리기
	function getListAndRender(){
		// 리스트 요청 데이터 받기
		axios({
			method: 'get', // put, post, delete 
			url: '${pageContext.request.contextPath}/api/guestbooks',
			headers: {"Content-Type" : "application/json; charset=utf-8"}, //전송타입 
			//params: guestbookVo, //get방식 파라미터로 값이 전달 
			//data: guestbookVo, //put, post, delete 방식 자동으로 JSON으로 변환 전달 
		
			responseType: 'json' //수신타입
		}).then(function (response) {
			//console.log(response); //수신데이타
			// 리스트 자리에 글 추가 ..
			for(let i=0; i<response.data.length; i++){
				let guestbookVo = response.data[i]
				render(guestbookVo,"down"); // render() 함수를 만들어서 실행시킨다
			}
		}).catch(function (error) {
			console.log(error);
		});  // 리스트 요청 데이터 받기
	};
	
	
	
	// 글 저장하고 그리기 - 등록버튼을 눌렀을 때
	function addAndRender(event){
		event.preventDefault();
		
		let guestVo = {
			name : document.querySelector('#guestAdd>tbody input[name="name"]').value,
			password : document.querySelector('#guestAdd>tbody input[name="password"]').value,
			content : document.querySelector('#guestAdd>tbody textarea[name="content"]').value,
		};

		axios({
			method: 'post', // get(select), put(update), post(insert), delete(delete)
			url: '${pageContext.request.contextPath}/api/guestbooks',
			headers: {"Content-Type" : "application/json; charset=utf-8"}, //전송타입
			//params: guestVo, //get방식 파라미터로 값이 전달
			data: guestVo, //put, post, delete 방식 자동으로 JSON으로 변환 전달
			
			responseType: 'json' //수신타입
		}) .then(function (response) {
				//console.log(response); //수신데이타
				//console.log(response.data);
				render(response.data,"up"); // 새로 그리기
				document.querySelector('#guestAdd>tbody input[name="name"]').value = "";
				document.querySelector('#guestAdd>tbody input[name="password"]').value = "";
				document.querySelector('#guestAdd>tbody textarea[name="content"]').value = "";
		}) .catch(function (error) {
				console.log(error);
		});
		
	} // 등록 끝

	
	
	// render 함수 - guestbookListArea영역에 리스트 그리기
	function render(guestbookVo, dir){
		
		let guestbookListArea = document.querySelector("#guestbookListArea");
		//  ${pageContext.request.contextPath} 는 자바문법. 자바스크립트에서 사용할 수 있다.
		let str = '';
		str += ' <table id="gr-' + guestbookVo.no + '" class="guestRead"> ';
		str += ' 	<colgroup> ';
		str += ' 		<col style="width: 10%;"> ';
		str += ' 		<col style="width: 40%;"> ';
		str += ' 		<col style="width: 40%;"> ';
		str += ' 		<col style="width: 10%;"> ';
		str += '	 </colgroup> ';
		str += ' 	 <tr>';
		str += ' 		<td>' + guestbookVo.no + '</td> ';
		str += ' 		<td>' + guestbookVo.name + '</td> ';
		str += ' 		<td>' + guestbookVo.regDate + '</td> ';
		str += ' 		<td><button class="btnModal" type="button" data-no="' + guestbookVo.no + '">삭제</button></td> ';
		str += ' 	 </tr> ';
		str += ' 	 <tr> ';
		str += ' 		<td colspan=4 class="text-left">' + guestbookVo.content +'</td> ';
		str += ' 	 </tr> ';
		str += ' </table> ';
		
		if(dir=="up"){
			guestbookListArea.insertAdjacentHTML("afterbegin",str);
		} else if(dir == "down"){
			guestbookListArea.insertAdjacentHTML("beforeend",str);
		}
		
	} // render 함수 끝
	
	
	
	// 모달창 보이기 - 버튼을 클릭했을 때 
	function callModal(event){
		// tagName 이 button 이면 모달창을 보이게 만들기
		if(event.target.tagName == "BUTTON"){
			//console.log("모달창");
			document.querySelector(".modal").style.display = "block";
			
			// hidden 에 no값 넣기 - console.log(event.target.dataset.no);
			document.querySelector('#myModal input[name="no"]').value = event.target.dataset.no;
			// password 비우기
			document.querySelector('#myModal input[name="password"]').value = "";
		}
	} // 모달창 호출 끝
	
	
	
	// 모달창 숨기기 - 닫기 버튼 클릭했을 때 
	function closeModal(event){
		// modal창을 안보이게 none으로 바꿔준다
		document.querySelector('#myModal').style.display = "none";
	} //모달창 닫기버튼 끝
	
	
	
	
	// 데이터 삭제 + 화면 새로 그리기
	function deleteAndRemove(event){
		// 데이터 모으기 - url에 값이 들어가면 만들어둔 vo에 같이 값이 넘어간다
		let guestVo = {};
		//guestVo.no = document.querySelector('#myModal input[name="no"]').value;
		guestVo.password = document.querySelector('#myModal input[name="password"]').value;
		//console.log(guestVo);
		axios({
			method: 'delete', // put, post, delete
			url: '${pageContext.request.contextPath}/api/guestbooks/' + document.querySelector('#myModal input[name="no"]').value,
			headers: {"Content-Type" : "application/json; charset=utf-8"}, //전송타입
			params: guestVo, //get방식 파라미터로 값이 전달
			//data: guestVo, //put, post, delete 방식 자동으로 JSON으로 변환 전달
			responseType: 'json' //수신타입
			
		}) .then(function (response) {
			//console.log(response); //수신데이타
			if(response.data==1){ // 삭제가 됐을때 
				// 테이블에 아이디를 추가해서 찾아낸다
				//let removeTable = document.querySelector("#gr-" + document.querySelector('#myModal input[name="no"]').value);
				document.querySelector("#gr-" + document.querySelector('#myModal input[name="no"]').value).remove();
				document.querySelector('#myModal input[name="no"]').value = "";
				document.querySelector('#myModal input[name="password"]').value = "";
			}
			document.querySelector(".modal").style.display = "none";
		}) .catch(function (error) {
			console.log(error);
		}); 
	}// 삭제기능 끝
	
	
//////////////////////////// 함수 정리 끝 ////////////////////////////////////////////
</script>
</html>