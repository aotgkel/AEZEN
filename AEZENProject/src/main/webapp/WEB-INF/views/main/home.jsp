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
								<button>최신순</button>
								<button>조회순</button>
								<button>추천순</button>
								<button>팔로우순</button>
								<button>검색순</button>
							</div>
							<div class="feed-buttons">
								<button>전체</button>
								<button>자유</button>
								<button>코딩테스트</button>
								<button>Q&A</button>
							</div>
						</div>
					</div>
				</div>

				<!-- 게시글 -->
				<div class="posts">

					<!-- 게시글 예시 2 -->
					<c:forEach items="${boardList}" var="post">
					<div class="post">

						<div class="post-header">
							<span class="kind">[ <c:choose>
									<c:when test="${post.boardCategory == 1}">자유게시판</c:when>
									<c:when test="${post.boardCategory == 2}">Q&A</c:when>
									<c:when test="${post.boardCategory == 3}">코딩테스트</c:when>
									<c:when test="${post.boardCategory == 4}">공지사항</c:when>
									<c:when test="${post.boardCategory == 5}">이용약관</c:when>
									<c:when test="${post.boardCategory == 6}">개인정보처리방침</c:when>
									<c:otherwise>미분류</c:otherwise>
								</c:choose> ]
							</span> <span class="title"><c:out value="${post.boardTitle}" /></span>
							<span class="nick dropdown">작성자: <c:out
									value="${post.id}" />
								<ul class="dropdown-menu">
									<li><a href="#">프로필 보기</a></li>
									<li><a href="#">팔로우 하기</a></li>
									<li><a href="#">메세지 보내기</a></li>
									<li><a href="#">차단하기</a></li>
								</ul>
							</span> <span class="hit">조회수: <c:out value="${post.hit}" /></span>
							<span class="wdate">작성일: ${post.boardCreatedAt}</span>
							<span class="report" data-post-id="${post.boardNo}">🚨신고하기</span>
						</div>

						<div class="post-body">
							<c:choose>
							    <c:when test="${not empty post.files}">
							        <c:forEach var="file" items="${post.files}">
							            <a href="/upload/${file.physicalFileName}" class="upload" download>
							                <c:out value="${file.logicalFileName}" />
							            </a>
							        </c:forEach>
							    </c:when>
							    <c:otherwise>
							        첨부파일 없음
							    </c:otherwise>
							</c:choose>
							<div class="bnote">
								<c:out value="${post.boardContent}" />
							</div>
							<!-- 이미지 미리보기 -->
							<c:choose>
							    <c:when test="${empty post.files}">
							        <div class="post-images" style="display:none;"></div>
							    </c:when>
							    <c:otherwise>
							        <div class="post-images" style="display:none;">
							            <c:forEach var="file" items="${post.files}">
							                <c:if test="${fn:toLowerCase(file.fileExt) == 'jpg' 
									                     or fn:toLowerCase(file.fileExt) == 'png' 
									                     or fn:toLowerCase(file.fileExt) == 'gif'}">
							                    <img src="/AEZENProject/resources/upload/${file.physicalFileName}" alt="${file.logicalFileName}">
							                </c:if>
							            </c:forEach>
							        </div>
							    </c:otherwise>
							</c:choose>
							<br>
							<div class="tag">
								<c:forEach var="tag" items="${post.tags}">
									<a href="/tags/${tag}">#<c:out value="${tag.tagName}" /></a>
								</c:forEach>
							</div>
						</div>

						<!-- 답안 토글 -->
						<c:if test="${post.boardCategory == 2 or post.boardCategory == 3}">
						<div class="answer-toggle">
							<button class="answer-btn">답안 작성 / 모아보기</button>
						</div>
						<!-- 답안 영역 -->
						<div class="answers">
							<div class="answer-list">
								<c:forEach var="answer" items="${post.comments}">
								<c:if test="${answer.commentAnswerType == 2}">
									<div class="answer-item" data-id="${answer.commentAnswerNo}">
										<div class="a-text">
											<c:out value="${answer.commentAnswerContent}" />
										</div>
										<div class="a-footer">
											<span class="author">by <c:out value="${answer.id}" /></span>
											<div class="answer-actions">
												<span class="edit-btn">수정</span> <span class="delete-btn"
													data-type="answer">삭제</span>
											</div>
											<button class="a-choice">채택</button>
										</div>
									</div>
									</c:if>
								</c:forEach>
							</div>
							<!-- 답안 입력 창-->
							<form class="answer-form">
								<textarea placeholder="답안을 작성해보세요..." required></textarea>
								<button type="submit">등록</button>
							</form>
						</div>
						</c:if>

						<div class="post-footer">
							<span class="comment">댓글 
							<c:set var="commentCount" value="0" />
							  <c:forEach var="c" items="${post.comments}">
							    <c:if test="${c.commentAnswerType == 1}">
							      <c:set var="commentCount" value="${commentCount + 1}" />
							    </c:if>
							  </c:forEach>
							  ${commentCount}개
							</span>
							<div class="votes">
								<span class="post-up">추천 <c:out
										value="${post.boardLike}" /> 👍
								</span> <span class="post-down">비추천 <c:out
										value="${post.boardDislike}" /> 👎
								</span>
							</div>
							<div class="post-actions">
								<a href="write?board_no=${post.boardNo}" class="edit-btn">수정</a>
								<span class="delete-btn" data-type="post">삭제</span>
							</div>
							<a href="#" class="more">더보기</a>
						</div>

						<!-- 댓글 영역 -->
						<br>
						<div class="comments">
							<c:forEach var="comment" items="${post.comments}">
								<c:if test="${comment.commentAnswerType == 1}">
									<div class="comment-item" data-id="${comment.commentAnswerNo}">
										<span class="c-text"><c:out
												value="${comment.commentAnswerContent}" /></span>
										<div class="c-votes">
											<span class="comment-up">👍</span> <span class="comment-down">👎</span>
										</div>
										<div class="comment-actions">
											<span class="edit-btn">수정</span>
											<span class="delete-btn" data-type="comment">삭제</span>
										</div>
									</div>
								</c:if>
							</c:forEach>
						</div>
						<!-- 댓글 입력창 -->
						<div class="comment-form" style="display: none;">
							<textarea placeholder="댓글을 입력하세요..."></textarea>
							<button type="button" class="submit-comment">작성</button>
						</div>
					</div>
					
					</c:forEach>

				</div>

				<!-- 글쓰기 버튼 -->
				<button id="writeBtn" class="write-btn">✏</button>

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
					<div style="text-align: center;">현재 접속자 수</div>
					<div style="text-align: center;">100,543명</div>
					<!-- 채팅로그 -->
					<div class="messages">
						안녕하세요... (닉네임) 🔔
						<div style="margin-top: 6px; color: #666; font-size: 12px;">(예시
							채팅이 표시됩니다.)</div>
					</div>
					<!-- 입력창 (하단 고정) -->
					<div class="chat-input">
						<input type="text" placeholder="내용을 입력하세요">
						<button>전송</button>
					</div>
				</div>

				<!-- 탭 패널: 마이페이지 -->
				<div class="tab-panel hidden" id="panel-mypage" role="tabpanel"
					aria-hidden="true">
					<!-- 목록은 스크롤 가능하도록 flex:1 -->
					<ul class="mypage-list" style="flex: 1; overflow: auto;">
						<li><a href="mypage.html?panel=posts">내 글 관리</a></li>
						<li><a href="mypage.html?panel=edit">정보수정</a></li>
						<li><a href="mypage.html?panel=follow">팔로우 / 팔로워 관리</a></li>
						<li><a href="mypage.html?panel=messages">메세지</a></li>
						<li><a href="mypage.html?panel=points">포인트</a></li>
						<li><a href="mypage.html?panel=alerts">알림</a></li>
						<li><a href="mypage.html?panel=withdraw">회원탈퇴</a></li>
					</ul>
				</div>
			</div>
		</div>
	</main>
	<%@ include file="/WEB-INF/views/include/tail.jsp"%>