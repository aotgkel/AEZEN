<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/WEB-INF/views/include/header.jsp"%>

<!-- ✅ 마이페이지 전용 스타일 -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/mypage.css">

<!-- ✅ 전역 변수 설정: contextPath / 로그인 사용자 -->
<script>
  // JSP에서 전역 변수만 한 번만 선언 — 중복 방지
  window.CONTEXT_PATH = '${pageContext.request.contextPath}';
  window.LOGGED_IN_USER_ID = '${sessionScope.login.id}'; // 세션 로그인 정보
  
</script>

<!-- ✅ JS 파일 로드 -->
<script src="${pageContext.request.contextPath}/resources/js/mypage.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/message.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/point-history.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/alert.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/edit.js"></script>

  <!---------------- 메인영역 ---------------------->
  <body>
  <main data-page-owner-id="${pageOwnerId}" <c:if test="${not empty loginUser}">data-login-user-id="${loginUser.id}"</c:if>>
    <!-- 컨테이너 (게시글 래퍼 + 사이드바) -->
    <!-- 수정 시, 게시글 래퍼 전체수정 or 게시글 수정 -->
    <div class="container">

      <!-- 게시글 래퍼( 공지/정렬버튼 + 게시글 ) -->
      <div class="posts-wrapper">

        <!-- 공지/정렬버튼 -->
        <!-- <div class="notice-bar">
        <div class="notice-row">

          <div class="notice-text">
          <strong>📢 공지사항&nbsp;&nbsp;: </strong>&nbsp;&nbsp;
          <a href="info.html" class="notice-note" style="color: rgb(219, 219, 93);">이용규칙 안내 .......</a>
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
 -->
        <!-- 게시글 -->
        <div id="postContainer">
			<%@ include file="/WEB-INF/views/include/posts.jsp" %>
		</div>
        <!-- 글쓰기 버튼 -->
        <button id="writeBtn" class="write-btn">✏</button>

      <!-----------------정보수정 패널--------------------->
<div id="edit-panel" class="content-panel hidden">
  <div class="info-edit-wrapper">
    <h2>회원 정보 수정</h2>
    <form class="edit-form">
      <label for="userid">아이디</label>
      <input type="text" id="userid" name="userid" value="${loginUserId}" readonly>

      <label for="password">비밀번호</label>
      <input type="password" id="password" name="password" placeholder="새 비밀번호 입력">

      <label for="confirm-password">비밀번호 확인</label>
      <input type="password" id="confirm-password" name="confirm-password" placeholder="비밀번호 확인">

      <label for="nickname">닉네임</label>
      <div class="input-group">
        <input type="text" id="nickname" name="nickname" placeholder="닉네임 입력">
        <button type="button">중복확인</button>
      </div>

      <label for="email">이메일</label>
      <div class="input-group">
        <input type="email" id="email" name="email" placeholder="이메일 주소 입력">
        <button type="button" id="emailVerifyBtn">이메일 인증</button>
      </div>

      <!-- ✅ 인증번호 입력창: 이메일 아래 -->
      <div id="verify-section" class="input-group" style="display:none; margin-top:8px;">
        <input type="text" id="verify-code" placeholder="인증번호 입력">
        <button type="button" id="verifyBtn">인증 확인</button>
      </div>

      <!-- ✅ 인증 완료 표시 -->
      <p id="verify-status" style="color:green; font-weight:bold; display:none; margin-top:8px;">
        이메일 인증 완료 ✅
      </p>

      <div class="form-actions">
        <button type="submit">수정하기</button>
        <button type="button">취소</button>
      </div>
    </form>
  </div>
</div>

      	<div id="follow-panel" class="content-panel hidden">
    <div class="follow-wrapper">
        <div class="follow-tabs">
            <!-- data-list 속성 추가: JS에서 목록 로딩 타입을 구분하기 위해 사용 -->
            <button class="tab-button active" data-list="following" data-target="following-list">
                팔로잉(<span id="following-count">0</span>)
            </button>
            <button class="tab-button" data-list="follower" data-target="follower-list">
                팔로워(<span id="follower-count">0</span>)
            </button>
        </div>
        
        <!-- 팔로잉 목록 (내가 팔로우 하는 사람) - UL 태그로 변경 -->
        <ul id="following-list" class="follow-list active">
            <li class="empty-message">데이터를 불러오는 중입니다...</li>
        </ul>
        
        <!-- 팔로워 목록 (나를 팔로우 하는 사람) - 초기 숨김, UL 태그로 변경 -->
        <ul id="follower-list" class="follow-list hidden">
            <li class="empty-message">데이터를 불러오는 중입니다...</li>
        </ul>
    </div>
</div>
      <!-----------------메세지 패널------------------------>
      <div id="messages-panel" class="content-panel hidden">
        <div class="panel-wrapper">
        <div class="message-header-bar">
          <div class="message-title">
            <h2>
              <span class="title-text">📨 메세지함</span>
              <!-- <span class="unread-badge" id="message-unread-badge">🔴 90</span> -->
            </h2>
          </div>
        <!-- 선택 기능 영역 -->
        <div class="message-controls">
          <div class="left-controls">
            <label><input type="checkbox" id="select-all"> 전체 선택</label>
          </div>
          <div class="right-controls">
            <button class="btn-delete-selected-message" style="padding: 6px 12px;
  font-size: 13px;
  background: #ff4d4d;
  color: #fff;
  border: none;
  border-radius: 6px;
  cursor: pointer;
  transition: background 0.2s ease;">선택 삭제</button>
          </div>
        </div>
        <!--메시지 리스트 영역-->
        <div class="message-list">
          <div class="message-item unread">
            <input type="checkbox" class="message-check">
            <div class="message-left">
              <div class="unread-mark"></div>
              <div class="nickname-group">
                <span class="nickname">개발의 보냄</span>
                <span class="direction">[보냄]</span>
              </div>
              <span class="content">개발의 소신이시오...</span>
            </div>
            <div class="message-actions">
              <button class="btn-more"onclick="viewMessage(123)">더보기</button>
              <button class="btn-delete" onclick="event.stopPropagation(); deleteMessage(456, this.closest('.message-item'))">삭제</button>
            </div>
          </div>
          <div class="message-item">
            <input type="checkbox" class="message-check">
            <div class="message-left">
              <div class="nickname-group">
                <span class="nickname">개발의 초보</span>
                <span class="direction">[받음]</span>
              </div>
              <span class="content">안녕하세요</span>
            </div>
            <div class="message-actions">
              <button class="btn-more"onclick="viewMessage(123)">더보기</button>
              <button class="btn-delete" onclick="event.stopPropagation(); deleteMessage(456, this.closest('.message-item'))">삭제</button>
            </div>
          </div>
        </div>
       </div>
       </div>
      </div>
      <!-- 메시지 -> 채팅패널 -->
      <div id="chat-panel" class="content-panel hidden">
        <div class="panel-wrapper">
        <h2>💬 대화창</h2>
        <div class="chat-box" id="chat-box"></div>
        <div class="chat-input">
          <textarea id="reply-input" placeholder="메세지를 입력하세요..."></textarea>
          <button onclick="sendReply()">전송</button>
          <button onclick="goBackToList()">뒤로가기</button>
        </div>
        </div>
      </div>
		 <!-- 메시지 삭제 확인 모달 -->
		<div id="delete-confirm-modal" class="modal hidden">
		  <div class="modal-content">
		    <p>이 대화방을 삭제하시겠습니까?</p>
		    <div class="modal-actions">
		      <button id="modal-confirm-btn">확인</button>
		      <button id="modal-cancel-btn">취소</button>
		    </div>
		  </div>
		</div>

      <!--------------------포인트 패널-------------------->
      <div id="points-panel" class="content-panel hidden">
        <div class="panel-wrapper">
        <div class="point-top-bar">
          <div class="point-tabs">
          <button id="btn-point-history" class="tab-btn active"
                      onclick="showPointHistory()">포인트 내역</button>
          <button id="btn-point-store" class="tab-btn"
                      onclick="goToPointStore()">포인트 상점</button>
          </div>
          <div class="point-filters">
            <button class="filter-btn">최신순</button>
            <button class="filter-btn">포인트순</button>
            <button class="filter-btn">적립사유</button>
            <button class="filter-btn">적립상태</button>
          </div>
        </div>
        <!-- 포인트 내역 테이블 -->
        <div id="point-history-section">
          <div class="point-controls">
            <div class="left-controls">
              
            </div>
            <div class="right-controls">
              
            </div>
          </div>
          <table class="point-table">
            <thead>
              <tr>
                <th><input type="checkbox" disabled></th>
                <th>일자</th>
                <th>상세내역</th>
                <th>포인트 수량</th>
                <th>누적 포인트</th>
                <th>활동 링크</th>
                <th>상태</th>
                
              </tr>
            </thead>
            <tbody>
              <tr>
                <td><input type="checkbox" class="point-check"></td>
                <td>2025-09-26</td>
                <td>출석 체크</td>
                <td>+10</td>
                <td>120</td>
                <td><a href="#">바로가기</a></td>
                <td>적립완료</td>
                
              </tr>
              <!-- 추가 행들 -->
            </tbody>
          </table>
        </div>
        </div>
      </div>

 <!--------------------알림 패널-------------------->
<!--------------------- 알림 패널 --------------------->
<div id="alerts-panel" class="content-panel hidden">
  <div class="panel-wrapper">

    <!-- 상단 바 -->
    <div class="point-top-bar">
      <div class="point-tabs">
        <button id="btn-alert-history" class="tab-btn active">알림 내역</button>
      </div>
      <div class="point-filters">
        <button class="btn-delete-selected" style="background-color: #ff6b6b; color: white;">선택 삭제</button>
      </div>
    </div>

    <!-- 알림 테이블 -->
    <div id="alert-history-section">
      <table class="point-table">
        <thead>
          <tr>
            <th><input type="checkbox" id="select-all-notifications"></th>
            <th>알림일시</th>
            <th>내용</th>
            <th>타입</th>
            <th>관련 게시글</th>
            <th>삭제</th>
          </tr>
        </thead>
        <tbody id="alert-tbody">
          <tr>
            <td colspan="6" style="text-align:center; color:gray;">알림 내역을 불러오는 중...</td>
          </tr>
        </tbody>
      </table>
    </div>

  </div>
</div>

      <!-------------------회원탈퇴 패널---------------------->
      <div id="withdraw-panel" class="content-panel hidden">
        <div class="withdraw-card">
          <h2>회원탈퇴</h2>
          <div class="withdraw-warning">
            <p>
              회원탈퇴 시 <strong>모든 활동 정보가 삭제</strong>되며, 복구가 불가능합니다.<br>
              탈퇴 후에는 동일한 아이디로 <strong>재가입할 수 없습니다.</strong><br>
              포인트, 게시글, 댓글 등 모든 데이터가 삭제됩니다.
            </p>
          </div>
          <label class="withdraw-agree">
            <input type="checkbox" id="agree-check">
            <span>위 내용을 확인하고, 회원탈퇴 약관에 동의합니다.</span>
          </label>
          <button id="btn-withdraw" class="btn-danger">회원탈퇴</button>
        </div>
      </div>

      <!-- 탈퇴 확인 모달 -->
      <div id="withdrawal-popup" class="modal hidden">
        <div class="modal-content">
          <h3>회원탈퇴 확인</h3>
          <p>회원탈퇴를 진행하려면 아이디와 비밀번호를 입력해주세요.</p>
          <input type="text" id="withdraw-id" placeholder="아이디 입력">
          <input type="password" id="withdraw-pw" placeholder="비밀번호 입력">
          <div class="modal-buttons">
            <button class="confirm" >확인</button>
            <button class="cancel" onclick="closeWithdrawalPopup()">취소</button>
          </div>
        </div>
      </div>

      <!-- 탈퇴 완료 모달 -->
      <div id="withdrawal-complete" class="modal hidden">
        <div class="modal-content">
          <h3>회원탈퇴가 완료되었습니다.</h3>
          <p>그동안 이용해주셔서 감사합니다.</p>
          <button class="confirm" onclick="goToHome()">홈으로 이동</button>
        </div>
      </div>

      </div> <!-- 게시글 래퍼 끝 -->

      <!-- 신고모달 -->
        <div id="reportModal" class="modal">
          <div class="modal-content">
            <span class="close">&times;</span>
            <h2>신고하기</h2>
            <p>신고 사유를 선택해주세요:</p>
            <form id="reportForm">
              <label><input type="radio" name="reason" value="욕설/비방"> 욕설/비방</label><br>
              <label><input type="radio" name="reason" value="스팸/광고"> 스팸/광고</label><br>
              <label><input type="radio" name="reason" value="음란물"> 음란물</label><br>
              <label><input type="radio" name="reason" value="도배"> 도배</label><br><br>
              <button type="submit">신고하기</button>
              <button type="button" id="cancelBtn">취소</button>
            </form>
          </div>
        </div>
        
      <!---- 사이드바 ---->
        <div class="sidebar">
        <div class="ad-top">
          <a href="https://www.eduwill.net/sites/home" target="_self">
            <img src="resources/img/adbaner.jpg" alt="광고 이미지">
          </a>
        </div>

        <!-- 탭 버튼 (실시간채팅 / 마이페이지) -->
        <div class="sidebar-tabs" role="tablist">
          <button class="tab-button" data-target="panel-chat" role="tab" aria-selected="false">실시간채팅</button>
          
		<button class="tab-button active" data-target="panel-mypage" role="tab" aria-selected="true">마이페이지</button>
        </div>

        <!-- 탭 패널: 실시간채팅 -->
        <div class="tab-panel hidden" id="panel-chat" role="tabpanel" aria-hidden="true">
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
        
        <div class="tab-panel" id="panel-mypage" role="tabpanel">
          <!-- 목록은 스크롤 가능하도록 flex:1 -->
          <ul class="mypage-list" style="flex:1; overflow:auto;">
            <li data-target="posts-panel" class="selected"><a href="#">내 글 관리</a></li>
            <li data-target="edit-panel"><a href="#">정보수정</a></li>
            
           	<li data-target="follow-panel"><a href="#">팔로우 / 팔로워 관리</a></li>
           	
            <li data-target="messages-panel"><a href="#">메세지</a></li>
            <li data-target="points-panel"><a href="#">포인트</a></li>
            <li data-target="alerts-panel"><a href="#">알림</a></li>
            <li data-target="withdraw-panel"><a href="#">회원탈퇴</a></li>
          </ul>
        </div>
        </div>
    </div>
    
  </main>
    <script>
  async function invitation(senderId, recieverId){
	const MESSAGE_API_BASE = location.origin + CONTEXT_PATH + '/message';
	console.log(MESSAGE_API_BASE)
	console.log(senderId, recieverId);
	
	const payload = {
      senderId: senderId,
      receiverId: recieverId // TODO: 실제 대화 상대 ID로 변경
    };
	
	const res = await fetch(MESSAGE_API_BASE + "/invitation", {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(payload)
      });
     console.log(res);
      
}
</script>
<script src="${pageContext.request.contextPath}/resources/js/withdraw.js"></script>
<%@ include file="/WEB-INF/views/include/tail.jsp"%>