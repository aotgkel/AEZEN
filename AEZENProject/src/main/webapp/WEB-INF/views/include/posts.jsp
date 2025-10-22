<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!-- 게시글 -->
<div class="posts">

	<!-- 게시글 예시 2 -->
	<c:forEach items="${boardList}" var="post">
		<div class="post" data-id="${post.boardNo}">

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
				</span> <span class="title"><c:out value="${post.boardTitle}" /></span> <span
					class="nick dropdown">작성자: <c:out value="${post.nick}" />
					<ul class="dropdown-menu">
						<li><a href="#">프로필 보기</a></li>
						<li><a href="#">팔로우 하기</a></li>
						<li><a href="#">메세지 보내기</a></li>
						<li><a href="#">차단하기</a></li>
					</ul>
				</span> <span class="hit">조회수: <c:out value="${post.hit}" /></span> <span
					class="wdate">작성일: ${post.boardCreatedAt}</span> <span
					class="report" data-post-id="${post.boardNo}">🚨신고하기</span>
			</div>

			<div class="post-body">
				<c:choose>
					<c:when test="${not empty post.files}">
						<c:forEach var="file" items="${post.files}">
							<a
								href="${pageContext.request.contextPath}/download?no=${file.fileNo}"
								class="upload"> <c:out value="${file.logicalFileName}" />
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
						<div class="post-images" style="display: none;"></div>
					</c:when>
					<c:otherwise>
						<div class="post-images" style="display: none;">
							<c:forEach var="file" items="${post.files}">
								<c:if
									test="${fn:toLowerCase(file.fileExt) == 'jpg' 
									                     or fn:toLowerCase(file.fileExt) == 'png' 
									                     or fn:toLowerCase(file.fileExt) == 'gif'}">
									<img
										src="${pageContext.request.contextPath}/download?no=${file.fileNo}"
										alt="${file.logicalFileName}">
								</c:if>
							</c:forEach>
						</div>
					</c:otherwise>
				</c:choose>
				<br>
				<c:set var="tagNames" value="" />
				<!-- 중복 체크용 문자열 -->
				<div class="tag">
					<c:forEach var="tag" items="${post.tags}">
						<c:if test="${!fn:contains(tagNames, tag.tagName)}">
							<a href="/tags/${tag}">#<c:out value="${tag.tagName}" /></a>
							<c:set var="tagNames" value="${tagNames},${tag.tagName}" />
						</c:if>
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
										<c:if test="${not empty login and login.nick eq answer.id}">
											<div class="answer-actions">
												<span class="edit-btn">수정</span> <span class="delete-btn"
													data-type="answer">삭제</span>
											</div>
										</c:if>
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
				<span class="comment">댓글 <c:set var="commentCount" value="0" />
					<c:forEach var="c" items="${post.comments}">
						<c:if test="${c.commentAnswerType == 1}">
							<c:set var="commentCount" value="${commentCount + 1}" />
						</c:if>
					</c:forEach> ${commentCount}개
				</span>
				<div class="votes">
					<span class="post-up" data-id="${post.boardNo}">추천 <c:out
							value="${post.boardLike}" /> 👍
					</span> <span class="post-down" data-id="${post.boardNo}">비추천 <c:out
							value="${post.boardDislike}" /> 👎
					</span>
				</div>
				<div class="post-actions">
					<c:if test="${not empty login and login.id eq post.id}">
						<a href="write?board_no=${post.boardNo}" class="edit-btn">수정</a>
						<span class="delete-btn" data-type="post"
							data-board-no="${post.boardNo}">삭제</span>
					</c:if>
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
								<span class="comment-up" data-id="${comment.commentAnswerNo}">
									<c:out value="${comment.commentLikeCount}" /> 👍
								</span> <span class="comment-down" data-id="${comment.commentAnswerNo}"><c:out
										value="${comment.commentDislikeCount}" /> 👎</span>
							</div>
							<c:if test="${not empty login and login.nick eq comment.id}">
								<div class="comment-actions">
									<span class="edit-btn">수정</span> <span class="delete-btn"
										data-type="comment">삭제</span>
								</div>
							</c:if>
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