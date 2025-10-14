<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/WEB-INF/views/include/header.jsp"%>

<script src="resources/js/pointstore.js"></script>

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
          <strong>📢 공지사항&nbsp;&nbsp;: </strong>&nbsp;&nbsp;
          <a href="info.html" class="notice-note" style="color: rgb(219, 219, 93);">이용규칙 안내 .......</a>
          </div>

          <div class="notice-buttons">
            <div class="top-buttons">
              <button>최신순</button>
              <button>조회순</button>
              <button>검색순</button>
            </div>
            <div class="feed-buttons">
              <button>전체</button>
              <button>상품응모</button>
              <button>당첨자발표</button>
            </div>
          </div>
        </div>
        </div>

    <!-- 게시글 리스트 -->
    <div class="posts">

      <!-- 단일 게시글 -->
      <div class="post">

          <div class="post-header">
            <span class="kind">[상품응모]</span>
            <span class="title"> 🎉 에듀윌과 함께하는 교재 응모이벤트 ....</span>
            <span class="nick dropdown">작성자: 관리자</span>
            <span class="hit">조회수: 10</span>
            <span class="wdate">작성일: 2025.09.25 09:23</span>
          </div>

          <div class="post-body">
            <a href="resources/img/ezen_logo.jpg" class="upload">첨부파일 없음</a>
            <div class="bnote">
              25년 9월 에듀윌의 인기교재 응모하고 상품 받아가세요.<br>
              응모항목<br>
              1. 공인중개사 3명 ( -50P ) &nbsp;&nbsp;&nbsp;
              2. 주택관리사 3명 ( -30P ) &nbsp;&nbsp;&nbsp;
              3. 정보처리기사 2명 ( -10P )
            </div>           
            <!-- 이미지 미리보기 -->
            <div class="post-images" style="display: none;">
              <img src="resources/img/img1.jpg" alt="첨부 이미지">
              <img src="resources/img/img2.jpg" alt="첨부 이미지">
            </div>
            <br>
            <div class="tag">
              <a href="/tags/공지">#공지</a>
              <a href="/tags/이벤트">#이벤트</a>
              <a href="/tags/업데이트">#업데이트</a>
            </div>
          </div>

          <div class="post-footer">
            <span class="comment">댓글 24개</span>
          <div class="post-actions">
            <a href="write.html" class="edit-btn">수정</a>
            <span class="delete-btn" data-type="post">삭제</span>
          </div>
          <a href="#" class="more">더보기</a>
        </div>

        <!-- 댓글 영역 -->
        <br>
        <div class="comments">
          <div class="comment-item" data-id="1">
            <span class="c-text">첫 번째 댓글 내용입니다.</span>
            <div class="comment-actions">
              <span class="edit-btn">수정</span>
              <span class="delete-btn" data-type="comment">삭제</span>
            </div>
          </div>
          <div class="comment-item" data-id="2">
            <span class="c-text">두 번째 댓글 내용입니다.</span>
            <div class="comment-actions">
              <span class="edit-btn">수정</span>
              <span class="delete-btn" data-type="comment">삭제</span>
            </div>
          </div>
          <div class="comment-item" data-id="3">
            <span class="c-text">세 번째 댓글 내용입니다.</span>
            <div class="comment-actions">
              <span class="edit-btn">수정</span>
              <span class="delete-btn" data-type="comment">삭제</span>
            </div>
          </div>
        </div>

        <!-- 댓글 입력창 -->
        <div class="comment-form" style="display: none;">
          <textarea placeholder="댓글을 입력하세요..."></textarea>
          <button type="button" class="submit-comment">작성</button>
        </div>

    </div> <!-- post 끝 -->

      <!-- 단일 게시글 -->
      <div class="post">

          <div class="post-header">
            <span class="kind">[당첨자발표]</span>
            <span class="title"> 🔔 에듀윌과 함께하는 교재 응모이벤트 당첨자 발표 ....</span>
            <span class="nick dropdown">작성자: 관리자</span>
            <span class="hit">조회수: 10</span>
            <span class="wdate">작성일: 2025.09.25 09:23</span>
          </div>

          <div class="post-body">
            <a href="resources/img/ezen_logo.jpg" class="upload">첨부파일 없음</a>
            <div class="bnote">
              25년 9월 에듀윌의 인기교재 응모 당첨자 입니다.<br>
              당첨자<br>
              1. 공인중개사 : 홍길동  /  전우치  /  관리자 &nbsp;&nbsp;&nbsp;
              2. 주택관리사 : 개발의신   /   아무개     / &nbsp;&nbsp;&nbsp;
              3. 정보처리기사 : 고길동
            </div>           
            <!-- 이미지 미리보기 -->
            <div class="post-images" style="display: none;">
              <img src="img1.jpg" alt="첨부 이미지">
              <img src="img2.jpg" alt="첨부 이미지">
            </div>
            <br>
            <div class="tag">
              <a href="/tags/공지">#공지</a>
              <a href="/tags/이벤트">#이벤트</a>
              <a href="/tags/업데이트">#업데이트</a>
            </div>
          </div>

          <div class="post-footer">
            <span class="comment">댓글 24개</span>
          <div class="post-actions">
            <a href="write.html" class="edit-btn">수정</a>
            <span class="delete-btn" data-type="post">삭제</span>
          </div>
          <a href="#" class="more">더보기</a>
        </div>

        <!-- 댓글 영역 -->
        <br>
        <div class="comments">
          <div class="comment-item" data-id="1">
            <span class="c-text">첫 번째 댓글 내용입니다.</span>
            <div class="comment-actions">
              <span class="edit-btn">수정</span>
              <span class="delete-btn" data-type="comment">삭제</span>
            </div>
          </div>
          <div class="comment-item" data-id="2">
            <span class="c-text">두 번째 댓글 내용입니다.</span>
            <div class="comment-actions">
              <span class="edit-btn">수정</span>
              <span class="delete-btn" data-type="comment">삭제</span>
            </div>
          </div>
          <div class="comment-item" data-id="3">
            <span class="c-text">세 번째 댓글 내용입니다.</span>
            <div class="comment-actions">
              <span class="edit-btn">수정</span>
              <span class="delete-btn" data-type="comment">삭제</span>
            </div>
          </div>
        </div>

        <!-- 댓글 입력창 -->
        <div class="comment-form" style="display: none;">
          <textarea placeholder="댓글을 입력하세요..."></textarea>
          <button type="button" class="submit-comment">작성</button>
        </div>
        
    </div> <!-- post 끝 -->

      <!-- 단일 게시글 -->
      <div class="post">

          <div class="post-header">
            <span class="kind">[상품응모]</span>
            <span class="title"> 🎉 에듀윌과 함께하는 교재 응모이벤트 ....</span>
            <span class="nick dropdown">작성자: 관리자</span>
            <span class="hit">조회수: 10</span>
            <span class="wdate">작성일: 2025.09.25 09:23</span>
          </div>

          <div class="post-body">
            <a href="ezen_logo.jpg" class="upload">첨부파일 없음</a>
            <div class="bnote">
              25년 9월 에듀윌의 인기교재 응모하고 상품 받아가세요.<br>
              응모항목<br>
              1. 공인중개사 3명 ( -50P ) &nbsp;&nbsp;&nbsp;
              2. 주택관리사 3명 ( -30P ) &nbsp;&nbsp;&nbsp;
              3. 정보처리기사 2명 ( -10P )
            </div>           
            <!-- 이미지 미리보기 -->
            <div class="post-images" style="display: none;">
              <img src="resources/img/img1.jpg" alt="첨부 이미지">
              <img src="resources/img/img2.jpg" alt="첨부 이미지">
            </div>
            <br>
            <div class="tag">
              <a href="/tags/공지">#공지</a>
              <a href="/tags/이벤트">#이벤트</a>
              <a href="/tags/업데이트">#업데이트</a>
            </div>
          </div>

          <div class="post-footer">
            <span class="comment">댓글 24개</span>
          <div class="post-actions">
            <a href="write.html" class="edit-btn">수정</a>
            <span class="delete-btn" data-type="post">삭제</span>
          </div>
          <a href="#" class="more">더보기</a>
        </div>

        <!-- 댓글 영역 -->
        <br>
        <div class="comments">
          <div class="comment-item" data-id="1">
            <span class="c-text">첫 번째 댓글 내용입니다.</span>
            <div class="comment-actions">
              <span class="edit-btn">수정</span>
              <span class="delete-btn" data-type="comment">삭제</span>
            </div>
          </div>
          <div class="comment-item" data-id="2">
            <span class="c-text">두 번째 댓글 내용입니다.</span>
            <div class="comment-actions">
              <span class="edit-btn">수정</span>
              <span class="delete-btn" data-type="comment">삭제</span>
            </div>
          </div>
          <div class="comment-item" data-id="3">
            <span class="c-text">세 번째 댓글 내용입니다.</span>
            <div class="comment-actions">
              <span class="edit-btn">수정</span>
              <span class="delete-btn" data-type="comment">삭제</span>
            </div>
          </div>
        </div>

        <!-- 댓글 입력창 -->
        <div class="comment-form" style="display: none;">
          <textarea placeholder="댓글을 입력하세요..."></textarea>
          <button type="button" class="submit-comment">작성</button>
        </div>
        
    </div> <!-- post 끝 -->

    </div> <!-- .posts 끝 -->

        <!-- 글쓰기 버튼 -->
        <button id="writeBtn" class="write-btn">✏</button>

      </div> <!-- 게시글 래퍼 끝 -->

      <!-- 사이드바 -->
      <div class="sidebar">
        <div class="ad-top">
          <a href="https://www.eduwill.net/sites/home" target="_blank">
            <img src="resources/img/adbaner.jpg" alt="광고 이미지">
          </a>
        </div>

        <!-- 탭 버튼 (실시간채팅 / 포인트상점) -->
        <div class="sidebar-tabs" role="tablist">
          <button class="tab-button" data-target="panel-chat" role="tab" aria-selected="false">
            실시간채팅
          </button>
          <button class="tab-button active" data-target="panel-pointstore" role="tab" aria-selected="true">
            포인트 상점<br>
            ( 보유 포인트 54,000 P )
          </button>
        </div>

        <!-- 탭 패널: 채팅 -->
        <div class="tab-panel hidden" id="panel-chat" role="tabpanel" aria-hidden="true">
          <div style="text-align: center;">현재 접속자 수</div>
          <div style="text-align: center;">100,543명</div>

          <!-- 기존 chat-messages 형태 -->
          <div class="messages">
            안녕하세요... (닉네임) 🔔
            <div style="margin-top:6px;color:#666;font-size:12px;">(예시 채팅이 표시됩니다.)</div>
          </div>

          <!-- 입력창 (하단 고정) -->
          <div class="chat-input">
            <input type="text" placeholder="내용을 입력하세요">
            <button>전송</button>
          </div>
        </div>

        <!-- 탭 패널: 포인트상점 -->
        <div class="tab-panel" id="panel-pointstore" role="tabpanel">
          <ul class="pointstore-list" style="flex:1; overflow:auto;">
            <li class="selected"><a href="pointstore.html">상품응모 / 당첨자발표</a></li>
            <li><a href="pointshop.html">아이템 구매</a></li>
            <li><a href="minigame.html">미니게임</a></li>
          </ul>
        </div>
      </div>
    </div>
  </main>
<%@ include file="/WEB-INF/views/include/tail.jsp"%>