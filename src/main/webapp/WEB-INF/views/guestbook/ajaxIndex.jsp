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
	.modal {
		display : block;
	}
	.modal .modal-content{
		width : 818px;
		border : 1px solid #000000;
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
	                     <div class="m-header">
	                        패스워드를 입력하세요
	                     </div>
	                     <div class="m-body">
	                        <input type="password" name="password" value=""><br>
	                        <input type="text" name="no" value="">
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
// document.addEventListener("DOMContentLoaded",function(){});
document.addEventListener("DOMContentLoaded",function(){
		
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

	
	
	// 등록버튼을 눌렀을 때
	//let guestForm = document.querySelector("#guestForm"); // 버튼잡기
	document.querySelector("#guestForm").addEventListener("submit",function(event){
		//console.log("submit");
		event.preventDefault();
		
		//let name = document.querySelector('#guestAdd>tbody input[name="name"]').value.trim;
		//let password = document.querySelector('#guestAdd>tbody input[name="password"]').value.trim;
		//let content = document.querySelector('#guestAdd>tbody textarea[name="content"]').value.trim;
		//console.log(name);
		//console.log(password);
		//console.log(content);
		let guestVo = {
			name : document.querySelector('#guestAdd>tbody input[name="name"]').value,
			password : document.querySelector('#guestAdd>tbody input[name="password"]').value,
			content : document.querySelector('#guestAdd>tbody textarea[name="content"]').value,
		};
		//guestVo.name = name;
		//guestVo.password = password;
		//guestVo.content = content;
		//console.log(guestVo);

		axios({
			method: 'post', // get(select), put(update), post(insert), delete(delete)
			url: '${pageContext.request.contextPath}/api/guestbooks',
			headers: {"Content-Type" : "application/json; charset=utf-8"}, //전송타입
			params: guestVo, //get방식 파라미터로 값이 전달
			//data: guestbookVo, //put, post, delete 방식 자동으로 JSON으로 변환 전달
			
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
		
	});	 // 등록 끝

	
	
	// 모달창 호출 버튼 클릭했을 때  
	document.querySelector("#guestbookListArea").addEventListener("click",function(event){
		//console.log(event.target.tagName);
		//console.log(this);
		
		// tagName 이 button 이면 모달창을 보이게 만들기
		if(event.target.tagName == "BUTTON"){
			//console.log("모달창");
			document.querySelector(".modal").style.display = "block";
			
			// hidden 에 no값 넣기
			//console.log(event.target.dataset.no);
			document.querySelector('#myModal input[name="no"]').value = event.target.dataset.no;
		}
	}); // 모달창 호출 끝
	
	// 삭제버튼 클릭 - 삭제기능
	document.querySelector("#myModal button").addEventListener("click",function(event){
		//console.log("삭제");
		
		// 데이터 모으기
		
		let guestVo = {};
		guestVo.no = document.querySelector('#myModal input[name="no"]').value;
		guestVo.password = document.querySelector('#myModal input[name="password"]').value;
		//console.log(guestVo);
		
		// 이벤트 잡기 
		// 데이터수집
		// 요청 - 서버쪽 요청처리
		// 응답 - 데이터 수신
		// 응답 화면에 반영 -- 수작업
		axios({
			method: 'post', // put, post, delete
			url: '${pageContext.request.contextPath}/api/guestbooks/delete',
			headers: {"Content-Type" : "application/json; charset=utf-8"}, //전송타입
			params: guestVo, //get방식 파라미터로 값이 전달
			data: guestVo, //put, post, delete 방식 자동으로 JSON으로 변환 전달
			responseType: 'json' //수신타입
		}) .then(function (response) {
			console.log(response); //수신데이타
			if(response.data==1){
				let ko = document.querySelectorAll(".guestRead");
				let noList = [];
				for(let i=0; i<ko.length; i++){
					let no = ko[i].children[1].firstElementChild.firstElementChild.textContent
					if(no==guestVo.no){
						ko[i].remove();
						document.querySelector('#myModal input[name="no"]').value = "";
						document.querySelector('#myModal input[name="password"]').value = "";
						// 3개만 가져올때는 어케함?
					}
				}
			}
			
		}) .catch(function (error) {
			console.log(error);
		}); 
	
	
	}); // 삭제기능 끝
	
}); // document.addEventListener 끝

//////////////////////////// 함수 정리 ///////////////////////////////////////////////
	function render(guestbookVo, dir){
		//console.log(guestbookVo); 
		
		let guestbookListArea = document.querySelector("#guestbookListArea");
		//console.log(guestbookListArea);
		//  ${pageContext.request.contextPath} 는 자바문법. 자바스크립트에서 사용할 수 있다.
		let str = '';
		str += ' <table class="guestRead"> ';
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
		
	};// render 함수 끝
	
	
//////////////////////////// 함수 정리 끝 ////////////////////////////////////////////
</script>
</html>