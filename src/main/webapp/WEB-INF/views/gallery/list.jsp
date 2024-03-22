<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath }/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath }/assets/css/gallery.css" rel="stylesheet" type="text/css">
<style>
	.modal {
		display : none;
		width : 100%;
		height : 100%;
		position : fixed;
		left : 0;
		top : 0;
		z-index : 999;
		overflow : auto;
		background-color : rgba(0,0,0,0.4); 
	};
	
	.modal 
	
	.modal .modal-content{
		width : 400px;
		margin : 100px auto 100px auto; 
		padding : 0px 20px 20px 20px;
		background-color : #ffffff; 
		border : 1px solid #888888;
	}
	
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
            <h2>갤러리</h2>
            <ul>
               <li><a href="${pageContext.request.contextPath}/gallery/list">일반갤러리</a></li>
               <li><a href="${pageContext.request.contextPath}/attach/uploadform">파일첨부연습</a></li>
            </ul>
         </div>
         <!-- //aside -->
         <div id="content">

            <div id="content-head">
               <h3>갤러리</h3>
               <div id="location">
                  <ul>
                     <li>홈</li>
                     <li>갤러리</li>
                     <li class="last">갤러리</li>
                  </ul>
               </div>
               <div class="clear"></div>
            </div>
            <!-- //content-head -->

            <div id="gallery">
               <div id="list">
				  <c:choose>
				  	<c:when test="${authUser != null}">
				  		<button id="btnImgUpload" type="button">이미지올리기</button>
				  	</c:when>
				  	<c:otherwise></c:otherwise>
				  </c:choose>				
                  <div class="clear"></div>

                  <ul id="viewArea">
					<c:forEach items="${galleryList}" var="list">
                     <!-- 이미지반복영역 -->
                     <li>
                        <div class="view">
                           <img class="imgItem" src="${pageContext.request.contextPath}/upload/${list.saveName}" data-no="${list.no}" data-content="${list.content}">
                           <div class="imgWriter">
                              작성자: <strong>${list.name}</strong>
                           </div>
                        </div>
                     </li>
                     <!-- //이미지반복영역 -->
					</c:forEach>
                  </ul>
               </div>
               <!-- //list -->
            </div>
            <!-- //board -->
         </div>
         <!-- //content  -->
      </div>
      <!-- //container  -->
      <c:import url="/WEB-INF/views/include/footer.jsp"></c:import>
      <!-- //footer -->
   </div>
   <!-- //wrap -->

   <!-- 이미지등록 팝업(모달)창 -->
   <div id="addModal" class="modal">
      <div class="modal-content">
         <form action="${pageContext.request.contextPath}/gallery/add" method="post" enctype="multipart/form-data">
            <div class="closeBtn">×</div>
            <div class="m-header">이미지등록</div>
            <div class="m-body">
               <div>
                  <label class="form-text">글작성</label> <input id="addModalContent" type="text" name="content" value="">
               </div>
               <div class="form-group">
                  <label class="form-text">이미지선택</label> <input id="file" type="file" name="file" value="">
               </div>
               <input type="hidden" id="addFile-no" name="userNo" value="${authUser.no}">
            </div>
            <div class="m-footer">
               <button type="submit">저장</button>
            </div>
         </form>
      </div>
   </div>


   <!-- 이미지보기 팝업(모달)창 -->
   <div id="viewModal" class="modal">
      <div class="modal-content">
         <div class="closeBtn">×</div>
         <div class="m-header">이미지 보기</div>
         <div class="m-body">
            <div>
               <img id="viewModelImg" src="">
               <!-- ajax로 처리 : 이미지출력 위치-->
            </div>
            <div>
               <p id="viewModelContent">asd</p>
            </div>
         </div>
         <div class="m-footer">
         	<c:choose>
         		<c:when test="${authUser.no == list.userNo}">
            		<button type="button" data-no="${list.no}">삭제</button>
            	</c:when>
            	<c:otherwise> </c:otherwise>
            </c:choose>
         </div>
      </div>
   </div>
</body>

<script type="text/javascript">
  
// DOM tree 완료 시점
document.addEventListener("DOMContentLoaded",function(event){
	//console.log("DOM tree 생성");
	event.preventDefault();
	let view = document.querySelector("#viewArea");
	
	// 사진클릭했을때
	view.addEventListener("click",function(event){
		//console.log("click");
		//console.log(event.target);
		if(event.target.tagName == "IMG"){
			//console.log(event.target.dataset.no);
			//console.log(document.querySelector("img").dataset.no)
			let viewModal = document.querySelector("#viewModal");
			viewModal.style.display = "block";
			let v = document.querySelector("img").dataset.content;
			console.log(v);
			let content = document.querySelector("#viewModal p");
			
		//	content.textContent = "";
		//	document.querySelector("img").dataset.no;
			

		}

	});
	
	
	
	// 등록폼 보이기
	document.querySelector("#btnImgUpload").addEventListener("click", function(event){
		event.preventDefault();
		let addModal = document.querySelector("#addModal");
		addModal.style.display = "block";
	});
	
	
	   
});
   
   
//////////////////////////////////// 함수 //////////////////////////////////////////////////////

///////////////////////////////////////////////////////////////////////////////////////////////
   
</script>

</html>

