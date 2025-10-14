<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/WEB-INF/views/include/header.jsp"%>

<link rel="stylesheet" href="resources/css/join_agree.css">
<script src="resources/js/join_agree.js"></script>


  <!---------------- 메인영역 ---------------------->
  <body class="join-agree-page">
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

        </div>
        </div>

        <!-- 게시글 -->
        <div class="posts">

          <!-- ✅ 약관동의 폼 게시글 -->
          <div class="post">
            <h2>약관동의</h2>
            <form action="/step2" method="POST">
              <div class="select-all">
                <input type="checkbox" id="select-all">
                <label for="select-all">전체 약관에 동의합니다.</label>
              </div>

              <div class="terms-section">
                <label>이용약관 동의 <span style="color:red;">(필수)</span></label>
                <div class="terms-box">여기에 이용약관 내용을 입력하세요.</div>
                <div class="checkbox-group">
                  <input type="checkbox" class="terms-checkbox" id="agree-terms" name="agree-terms" required>
                  <label for="agree-terms">위 약관에 동의합니다.</label>
                </div>
              </div>

              <div class="terms-section">
                <label>개인정보 수집 및 이용 동의 <span style="color:red;">(필수)</span></label>
                <div class="terms-box">여기에 개인정보 수집 및 이용 내용을 입력하세요.</div>
                <div class="checkbox-group">
                  <input type="checkbox" class="terms-checkbox" id="agree-privacy" name="agree-privacy" required>
                  <label for="agree-privacy">개인정보 수집 및 이용에 동의합니다.</label>
                </div>
              </div>

              <div class="terms-section">
                <label>마케팅 정보 수신 동의 <span style="color:gray;">(선택)</span></label>
                <div class="terms-box">여기에 마케팅 정보 수신 관련 내용을 입력하세요.</div>
                <div class="checkbox-group">
                  <input type="checkbox" class="terms-checkbox" id="agree-marketing" name="agree-marketing">
                  <label for="agree-marketing">마케팅 정보 수신에 동의합니다.</label>
                </div>
              </div>

              <div class="button-group">
                <a href="home.html" class="cancel-btn">취소</a>
                <a href="join_info.html" class="next-btn" id="nextBtn" style="pointer-events:none; opacity:0.5;">다음 단계</a>
              </div>
            </form>
          </div>

        </div> 

      </div> <!-- 게시글 래퍼 끝 -->
        
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