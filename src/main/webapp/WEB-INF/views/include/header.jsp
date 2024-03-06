<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

		<div id="header" class="clearfix">
			<h1>
				<a href="${pageContext.request.contextPath}/main">MySite</a>
			</h1>
			<c:choose>
				<c:when test="${authUser != null}">
					<ul>
						<li>${authUser.name}님 안녕하세요^^</li>
						<li><a href="${pageContext.request.contextPath}/user/logout" class="btn_s">로그아웃</a></li>
						<li><a href="${pageContext.request.contextPath}/user/modifyform?no=${authUser.no}" class="btn_s">회원정보수정</a></li>
					</ul>
				</c:when>
				<c:otherwise>
					<ul>
						<li><a href="${pageContext.request.contextPath}/user/loginform" class="btn_s">로그인</a></li>
						<li><a href="${pageContext.request.contextPath}/user/joinform" class="btn_s">회원가입</a></li>
					</ul>
				</c:otherwise>
			</c:choose>
		</div>
		<!-- //header -->

		<div id="nav">
			<ul class="clearfix">
				<li><a href="">입사지원서</a></li>
				<li><a href="${pageContext.request.contextPath}/board/list">게시판</a></li>
				<li><a href="">갤러리</a></li>
				<li><a href="${pageContext.request.contextPath}/guest/list">방명록</a></li>
			</ul>
		</div>
		<!-- //nav -->