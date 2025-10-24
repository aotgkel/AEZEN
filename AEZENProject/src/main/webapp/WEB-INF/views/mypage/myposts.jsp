<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!-- ✅ 내가 쓴 게시글 목록 -->
<div class="posts">

    <!-- ✅ 게시글이 없을 때 -->
    <c:if test="${empty boardList}">
        <div class="no-posts">
            <p>작성한 게시글이 없습니다.</p>
        </div>
    </c:if>

    <!-- ✅ 내 글만 출력 -->
    <c:forEach items="${boardList}" var="post">
        <!-- 혹시 백엔드에서 전체 글이 내려올 가능성 대비 -->
        <c:if test="${post.id eq loginUser.id}">
            <div class="post" data-id="${post.boardNo}">

                <div class="post-header">
                    <span class="kind">[
                        <c:choose>
                            <c:when test="${post.boardCategory == 1}">자유게시판</c:when>
                            <c:when test="${post.boardCategory == 2}">Q&A</c:when>
                            <c:when test="${post.boardCategory == 3}">코딩테스트</c:when>
                            <c:when test="${post.boardCategory == 4}">공지사항</c:when>
                            <c:when test="${post.boardCategory == 5}">이용약관</c:when>
                            <c:when test="${post.boardCategory == 6}">개인정보처리방침</c:when>
                            <c:otherwise>미분류</c:otherwise>
                        </c:choose>
                    ]</span>
                    <span class="title"><c:out value="${post.boardTitle}" /></span>
                    <span class="nick">작성자: <c:out value="${post.nick}" /></span>
                    <span class="hit">조회수: <c:out value="${post.hit}" /></span>
                    <span class="wdate">작성일: ${post.boardCreatedAt}</span>
                    <span class="report" data-post-id="${post.boardNo}">🚨신고하기</span>
                </div>

                <div class="post-body">
                    <c:choose>
                        <c:when test="${not empty post.files}">
                            <c:forEach var="file" items="${post.files}">
                                <a href="${pageContext.request.contextPath}/download?no=${file.fileNo}" class="upload">
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
                    <c:if test="${not empty post.files}">
                        <div class="post-images" style="display: none;">
                            <c:forEach var="file" items="${post.files}">
                                <c:if test="${fn:toLowerCase(file.fileExt) == 'jpg' or fn:toLowerCase(file.fileExt) == 'png' or fn:toLowerCase(file.fileExt) == 'gif'}">
                                    <img src="${pageContext.request.contextPath}/download?no=${file.fileNo}"
                                         alt="${file.logicalFileName}">
                                </c:if>
                            </c:forEach>
                        </div>
                    </c:if>

                    <div class="tag">
                        <c:set var="tagNames" value="" />
                        <c:forEach var="tag" items="${post.tags}">
                            <c:if test="${!fn:contains(tagNames, tag.tagName)}">
                                <a href="/tags/${tag}">#<c:out value="${tag.tagName}" /></a>
                                <c:set var="tagNames" value="${tagNames},${tag.tagName}" />
                            </c:if>
                        </c:forEach>
                    </div>
                </div>

                <div class="post-footer">
                    <span class="comment">
                        댓글 
                        <c:set var="commentCount" value="0" />
                        <c:forEach var="c" items="${post.comments}">
                            <c:if test="${c.commentAnswerType == 1}">
                                <c:set var="commentCount" value="${commentCount + 1}" />
                            </c:if>
                        </c:forEach>
                        ${commentCount}개
                    </span>

                    <div class="votes">
                        <span class="post-up" data-id="${post.boardNo}">추천 <c:out value="${post.boardLike}" /> 👍</span>
                        <span class="post-down" data-id="${post.boardNo}">비추천 <c:out value="${post.boardDislike}" /> 👎</span>
                    </div>

                    <div class="post-actions">
        <a href="${pageContext.request.contextPath}/board/modify?boardNo=${post.boardNo}" class="edit-btn">수정</a>
        <a href="${pageContext.request.contextPath}/board/delete?boardNo=${post.boardNo}" 
           class="delete-btn" 
           onclick="return confirm('정말 삭제하시겠습니까?');">삭제</a>
    </div>

    <a href="${pageContext.request.contextPath}/board/detail?boardNo=${post.boardNo}" class="more">더보기</a>
                </div>
            </div>
        </c:if>
    </c:forEach>
</div>
