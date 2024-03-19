<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath}/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/assets/css/board.css" rel="stylesheet" type="text/css">
</head>

<body>
	<div id="wrap">

		<c:import url="/WEB-INF/views/include/header.jsp"></c:import>

		<div id="container" class="clearfix">
			<div id="aside">
				<h2>게시판</h2>
				<ul>
					<li><a href="">일반게시판</a></li>
					<li><a href="">댓글게시판</a></li>
				</ul>
			</div>
			<!-- //aside -->

			<div id="content">

				<div id="content-head">
					<h3>게시판</h3>
					<div id="location">
						<ul>
							<li>홈</li>
							<li>게시판</li>
							<li class="last">일반게시판</li>
						</ul>
					</div>
					<div class="clear"></div>
				</div>
				<!-- //content-head -->
	
				<div id="board">
					<div id="list">
						<form action="${pageContext.request.contextPath}/tboard/list3" method="get">
							<div class="form-group text-right">
								<input type="text" name="keyword">
								<button type="submit" id=btn_search>검색</button>
							</div>
						</form>
						<table >
							<thead>
								<tr>
									<th>번호</th>
									<th>제목</th>
									<th>글쓴이</th>
									<th>조회수</th>
									<th>작성일</th>
									<th>관리</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${requestScope.boardMap.boardList}" var="boardVo" >
									<tr>
										<td>${boardVo.no}</td>
										<td class="text-left">
											<a href="${pageContext.request.contextPath}/board/read?no=${boardVo.no}">${boardVo.title}</a>
										</td>
										<td>${boardVo.name}</td>
										<td>${boardVo.hit}</td>
										<td>${boardVo.regDate}</td>
										<td>
											<c:if test="${boardVo.userNo == authUser.no}">
												<a href="${pageContext.request.contextPath}/board/delete?no=${boardVo.no}">[삭제]</a>
											</c:if>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
			
						<div id="paging">
							<ul>
								<c:if test="${boardMap.prev}">
									<li><a href="${pageContext.request.contextPath}/tboard/list3?crtPage=${boardMap.startPageBtnNo-1}&keyword=${param.keyword}">◀</a></li>
								</c:if>
								
								<c:forEach begin="${boardMap.startPageBtnNo}"
										   end="${boardMap.endPageBtnNo}"
										   step="1"
										   var="page">
									<c:choose>
										<c:when test="${page == param.crtPage}">
											<li class="active"><a href="${pageContext.request.contextPath}/tboard/list3?crtPage=${page}&keyword=${param.keyword}">${page}</a></li>
										</c:when>
										<c:otherwise>
											<li><a href="${pageContext.request.contextPath}/tboard/list3?crtPage=${page}&keyword=${param.keyword}">${page}</a></li>
										</c:otherwise>
									</c:choose>
								</c:forEach>
								
								<c:if test="${boardMap.next}">
									<li><a href="${pageContext.request.contextPath}/tboard/list3?crtPage=${boardMap.endPageBtnNo+1}&keyword=${param.keyword}">▶</a></li>
								</c:if>
							</ul>
							
							
							<div class="clear"></div>
						</div>
						<c:if test="${!empty authUser}">
							<a id="btn_write" href="${pageContext.request.contextPath}/board/writeForm">글쓰기</a>
						</c:if>
					
					</div>
					<!-- //list -->
				</div>
				<!-- //board -->
			</div>
			<!-- //content  -->

		</div>
		<!-- //container  -->
		

		<!-- footer -->
		<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>
	</div>
	<!-- //wrap -->

</body>

</html>
