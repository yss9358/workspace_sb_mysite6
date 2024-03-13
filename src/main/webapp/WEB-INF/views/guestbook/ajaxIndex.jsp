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

</head>

<body>
	<div id="wrap">

		<c:import url="/WEB-INF/views/include/header.jsp"></c:import>
	
		<div id="container" class="clearfix">
			<div id="aside">
				<h2>방명록</h2>
				<ul>
					<li>일반방명록</li>
					<li>ajax방명록</li>
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
					<form action="${pageContext.request.contextPath}/guest/add" method="get">
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
									<td colspan="4" class="text-center"><button type="submit">등록</button></td>
								</tr>
							</tbody>
							
						</table>
						<!-- //guestWrite -->
						
					</form>	
					<%-- <c:forEach items="${guestbookList}" var="gbList">
						<table class="guestRead">
							<colgroup>
								<col style="width: 10%;">
								<col style="width: 40%;">
								<col style="width: 40%;">
								<col style="width: 10%;">
							</colgroup>
							<tr>
								<td>${gbList.no}</td>
								<td>${gbList.name}</td>
								<td>${gbList.regDate}</td>
								<td><a href="${pageContext.request.contextPath}/guest/deleteform?no=${gbList.no}">[삭제]</a></td>
							</tr>
							<tr>
								<td colspan=4 class="text-left">${gbList.content}</td>
							</tr>
						</table>
						<!-- //guestRead -->
					</c:forEach>  --%>
					
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
		
	// 리스트요청 데이터 받기
	axios({
		method: 'get', // put, post, delete 
		url: '/mysite6/api/guestbooks',
		headers: {"Content-Type" : "application/json; charset=utf-8"}, //전송타입 
		//params: guestbookVo, //get방식 파라미터로 값이 전달 
		//data: guestbookVo, //put, post, delete 방식 자동으로 JSON으로 변환 전달 
	
		responseType: 'json' //수신타입
	}).then(function (response) {
		console.log(response); //수신데이타
		console.log("데이터전송완료");
		
		// 리스트 자리에 글 추가 ..
		for(let i=0; i<response.data.length; i++){
			let guestbookVo = response.data[i]
			render(guestbookVo);
		}
	 	render(); // render() 함수를 만들어서 실행시킨다 
	
		
	}).catch(function (error) {
		console.log(error);
	}); 

	
}); // document.addEventListener 끝

//////////////////////////// 함수 정리 ///////////////////////////////////////////////
function render(guestbookVo){
	console.log(guestbookVo);
};
//////////////////////////// 함수 정리 끝 ////////////////////////////////////////////
</script>
</html>