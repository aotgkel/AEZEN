<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="/WEB-INF/views/include/header.jsp"%>


<!---------------- 메인영역 ---------------------->
<body>
	<main>
		<!-- 컨테이너 (게시글 래퍼 + 사이드바) -->
		<!-- 수정 시, 게시글 래퍼 전체수정 or 게시글 수정 -->
		<div class="container">

			<!-- 게시글 래퍼( 공지/정렬버튼 + 게시글 ) -->
			<div class="posts-wrapper">

				<!-- 공지/정렬버튼 -->
				<div class="notice-bar">
					<div class="notice-row">

						<div class="notice-text">
							<strong>📢 공지사항&nbsp;&nbsp;: </strong>&nbsp;&nbsp; <a href="info"
								class="notice-note" style="color: rgb(219, 219, 93);"> <c:out
									value="${latestNoticeTitle}" /></a>
						</div>

						<div class="notice-buttons">
							<div class="top-buttons">
								<button data-sort="latest">최신순</button>
							    <button data-sort="hit">조회순</button>
							    <button data-sort="like">추천순</button>
							    <button data-sort="follow">팔로우순</button>
							    <button data-sort="search">검색순</button>
							</div>
							<div class="feed-buttons">
								<button data-category="all">전체</button>
							    <button data-category="free">자유</button>
							    <button data-category="coding">코딩테스트</button>
							    <button data-category="qna">Q&A</button>
							</div>
						</div>
					</div>
				</div>

				<div id="postContainer">
					<%@ include file="/WEB-INF/views/include/posts.jsp" %>
				</div>

				<!-- 글쓰기 버튼 -->
				<c:if test="${not empty login}">
					<button id="writeBtn" class="write-btn">✏</button>
				</c:if>
			</div>
			<!-- 게시글 래퍼 끝 -->

			<!-- 신고모달 -->
			<div id="reportModal" class="modal">
				<div class="modal-content">
					<span class="close">&times;</span>
					<h2>신고하기</h2>
					<p>신고 사유를 선택해주세요:</p>
					<form id="reportForm">
						<label><input type="radio" name="reason" value="욕설/비방">
							욕설/비방</label><br> <label><input type="radio" name="reason"
							value="스팸/광고"> 스팸/광고</label><br> <label><input
							type="radio" name="reason" value="음란물"> 음란물</label><br> <label><input
							type="radio" name="reason" value="도배"> 도배</label><br> <br>
						<button type="submit">신고하기</button>
						<button type="button" id="cancelBtn">취소</button>
					</form>
				</div>
			</div>

			<!---- 사이드바 ---->
			<div class="sidebar">
				<div class="ad-top">
					<a href="https://www.eduwill.net/sites/home" target="_blank"> <img
						src="resources/img/adbaner.jpg" alt="광고 이미지">
					</a>
				</div>

				<!-- 탭 버튼 (실시간채팅 / 마이페이지) -->
				<div class="sidebar-tabs" role="tablist">
					<button class="tab-button active" data-target="panel-chat"
						role="tab" aria-selected="true">실시간채팅</button>
					<button class="tab-button" data-target="panel-mypage" role="tab"
						aria-selected="false">마이페이지</button>
				</div>

				<!-- 탭 패널: 실시간채팅 -->
				<div class="tab-panel" id="panel-chat" role="tabpanel">
					<div style="text-align: center;">현재 접속자 수: <span id="user-count">0</span>명</div>
					
					<!-- 채팅로그 --> 
					<div class="messages" id="chatMessages" style="height:300px; overflow-y:auto; border:1px solid #ccc; padding:5px;">
				        <!-- 메시지가 JS로 추가됩니다 -->
				    </div>
				    
					<!-- 입력창 (하단 고정) -->
					<div class="chat-input">
				        <input type="text" id="chatInput" placeholder="내용을 입력하세요">
				        <button id="sendBtn">전송</button>
				    </div>
				</div>

				<!-- 탭 패널: 마이페이지 -->
				<div class="tab-panel hidden" id="panel-mypage" role="tabpanel"
					aria-hidden="true">
					<!-- 목록은 스크롤 가능하도록 flex:1 -->
					<ul class="mypage-list" style="flex: 1; overflow: auto;">
						<li><a href="mypage?panel=posts">내 글 관리</a></li>
						<li><a href="mypage?panel=edit">정보수정</a></li>
						<li><a href="mypage?panel=follow">팔로우 / 팔로워 관리</a></li>
						<li><a href="mypage?panel=messages">메세지</a></li>
						<li><a href="mypage?panel=points">포인트</a></li>
						<li><a href="mypage?panel=alerts">알림</a></li>
						<li><a href="mypage?panel=withdraw">회원탈퇴</a></li>
					</ul>
				</div>
			</div>
		</div>
	</main>
	<%@ include file="/WEB-INF/views/include/tail.jsp"%>