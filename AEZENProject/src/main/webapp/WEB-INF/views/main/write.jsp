<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/WEB-INF/views/include/header.jsp"%>

<link rel="stylesheet" href="resources/css/write.css">
<script src="resources/js/write.js"></script>
<body class="write-page">
  <!---------------- 메인영역 ---------------------->
  <main>
    <!-- 컨테이너 (게시글 래퍼 + 사이드바) -->
    <!-- 수정 시, 게시글 래퍼 전체수정 or 게시글 수정 -->
    <div class="container">
      <!-- 게시글 래퍼( 공지/정렬버튼 + 게시글 ) -->
      <div class="posts-wrapper">

      <!------------------------------ 글작성 폼 영역 ------------------------------------->
      <h2 class="form-title">글 작성</h2>
      <form class="write-form">
        <!-- 1. 카테고리 -->
        <div class="category-row">
          <label for="category">카테고리</label>
          <select id="category" name="category">
            <option value="free">자유</option>
            <option value="coding">코딩테스트</option>
            <option value="qna">Q&A</option>
          </select>

          <div id="extraCategory" class="extra-category"></div>
        </div>

        <!-- 2. 제목 -->
        <div>
          <label for="title">제목</label>
          <input type="text" id="title" name="title" placeholder="제목을 입력하세요">
        </div>

        <!-- 3. 내용 -->
        <div>
          <label for="content">내용</label>
          <textarea id="content" name="content" placeholder="내용을 입력하세요"></textarea>
        </div>

        <!-- 4. 해시태그 -->
        <div>
          <label for="hashtags">해시태그</label>
          <input type="text" id="hashtags" name="hashtags" placeholder="#태그를 입력하세요 (쉼표로 구분)">
        </div>

        <!-- 5. 파일 업로드 + 버튼 한 줄 -->
        <div class="form-bottom">
          <div class="file-upload-wrapper">
            <label for="fileUpload" class="file-upload-label">파일 선택</label>
            <input type="file" id="fileUpload" name="fileUpload">
            <span class="file-name">선택된 파일 없음</span>
          </div>

          <div class="form-actions">
            <button type="submit" class="btn-submit">등록</button>
            <button type="button" class="btn-cancel" onclick="window.location.href='home.html'">취소</button>
          </div>
        </div>
      </form>
    </div>
        
      <!---- 사이드바 ---->
        <div class="sidebar">
        <div class="ad-top">
          <a href="https://www.eduwill.net/sites/home" target="_blank">
            <img src="resources/img/adbaner.jpg" alt="광고 이미지">
          </a>
        </div>

        <!-- 탭 버튼 (실시간채팅 / 마이페이지) -->
        <div class="sidebar-tabs" role="tablist">
            <button class="tab-button active" data-target="panel-chat" role="tab" aria-selected="true">실시간채팅</button>
            <button class="tab-button" data-target="panel-mypage" role="tab" aria-selected="false">마이페이지</button>
        </div>

        <!-- 탭 패널: 실시간채팅 -->
        <div class="tab-panel" id="panel-chat" role="tabpanel">
            <div style="text-align: center;">현재 접속자 수</div>
            <div style="text-align: center;">100,543명</div>
            <!-- 채팅로그 -->
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

        <!-- 탭 패널: 마이페이지 -->
        <div class="tab-panel hidden" id="panel-mypage" role="tabpanel" aria-hidden="true">
          <!-- 목록은 스크롤 가능하도록 flex:1 -->
          <ul class="mypage-list" style="flex:1; overflow:auto;">
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