
document.addEventListener('DOMContentLoaded', function () {

// ✅ [추가] 마이페이지 전용 로그인 박스 → 아이콘 표시
  const loginBox = document.querySelector('.login-box');
  const icons = document.querySelector('.icons');
  if (loginBox && icons) {
    loginBox.style.display = 'none';
    icons.style.display = 'block';
  }
  
  // ----------------------------------------------------
  // ✅ 0. 커스텀 메시지 팝업 함수 (alert 대체)
  // ----------------------------------------------------
 // ----------------------------------------------------
// ✅ 0. 커스텀 메시지 팝업 함수 (alert 대체) - 전역으로 등록 (window.showMessage)
// ----------------------------------------------------
window.showMessage = function (message) {
    // NOTE: JSP 파일에 <div id="custom-message-popup" class="hidden">...</div> 와 
    // 메시지를 표시할 <div id="custom-message-text"></div> 엘리먼트가 필요합니다.
    const popup = document.getElementById("custom-message-popup");
    const text = document.getElementById("custom-message-text");
    
    if (text) text.innerText = message;
    
    // 팝업 엘리먼트가 없는 경우 (UI 부재 시) 콘솔 로그로 대체
    if (!popup) {
        console.log("Message: " + message);
        return;
    }
    
    popup.classList.remove("hidden");
    // 3초 후 자동 닫기
    setTimeout(() => {
        popup.classList.add("hidden");
    }, 3000); 
}


  // ⚠️ 중요: 현재 로그인 사용자의 ID를 JSP의 <body> 태그에서 가져옵니다.
  // 이 값이 'testuser'라면 JSP에서 ID를 제대로 못 읽어왔다는 뜻입니다.
  const currentUserId = document.body.dataset.loginUserId || '1'; 
  console.log("✅ Current Login User ID (from JSP data-attribute):", currentUserId); 
  
  // ✅ 1. 사이드바 탭 전환 (실시간채팅 / 마이페이지)
  const sidebarTabButtons = document.querySelectorAll('.sidebar-tabs .tab-button');
  const sidebarTabPanels  = document.querySelectorAll('.tab-panel');
  sidebarTabButtons.forEach(btn => {
    btn.addEventListener('click', () => {
      sidebarTabButtons.forEach(b => b.classList.remove('active'));
      btn.classList.add('active');
      sidebarTabPanels.forEach(p => p.classList.add('hidden'));
      const target = document.getElementById(btn.dataset.target);
      if (target) target.classList.remove('hidden');
    });
  });

  // ✅ 2. 마이페이지 콘텐츠 전환 + 정렬 버튼 / 글쓰기 버튼 표시
  const menuItems     = document.querySelectorAll('.mypage-list li[data-target]');
  const contentPanels = document.querySelectorAll('.content-panel');
  const sortButtons   = document.querySelector('.sort-buttons');
  const writeBtn      = document.getElementById('writeBtn');
  const noticeButtons = document.querySelector('.notice-buttons');
  
  menuItems.forEach(item => {
    item.addEventListener('click', () => {
      // 탭 활성화
      menuItems.forEach(i => i.classList.remove('selected'));
      item.classList.add('selected');

      // 콘텐츠 패널 전환
      contentPanels.forEach(p => p.classList.add('hidden'));
      const targetId = item.dataset.target;
      const targetPanel = document.getElementById(targetId);
      if (targetPanel) targetPanel.classList.remove('hidden');

      // 글쓰기/정렬 버튼 표시 제어
      const showButtons = (targetId === 'posts-panel' || targetId === 'write-panel');
      if (sortButtons) sortButtons.style.display = showButtons ? 'flex' : 'none';
      if (writeBtn) writeBtn.style.display = showButtons ? 'block' : 'none';
      if (noticeButtons) noticeButtons.style.display = 'flex';
      
      // ✅ 팔로우 패널 선택 시 데이터 로드 및 탭 활성화
      if (targetId === 'follow-panel') {
          // 탭을 열 때만 최초 로드
          loadFollowData(currentUserId); 
          
          // 초기 탭 상태 설정
          const activeTab = document.querySelector('.follow-tabs .tab-button.active');
          const targetListId = activeTab ? activeTab.dataset.target : 'following-list';
          document.querySelectorAll('.follow-list').forEach(list => list.classList.add('hidden'));
          document.getElementById(targetListId)?.classList.remove('hidden');
      } 
    });
  });

  // 초기 마이페이지 설정
  // 마이페이지 탭 클릭 시 '내 글 관리' 패널로 시작
  const initialPanel = document.querySelector('.mypage-list li[data-target="posts-panel"]');
  if (initialPanel) {
      initialPanel.click(); 
  }


  

  // ✅ 4. 포인트 탭 전환
  window.showPointHistory = function () {
    document.getElementById("point-history-section").style.display = "block";
    document.getElementById("btn-point-history").classList.add("active");
    document.getElementById("btn-point-store").classList.remove("active");
  };

  window.goToPointStore = function () {
    window.location.href = "pointstore.html";
  };

  // ✅ 5. 회원탈퇴 팝업 기능
  const withdrawBtn = document.getElementById("btn-withdraw");
  if (withdrawBtn) {
    withdrawBtn.addEventListener("click", function () {
      const agreed = document.getElementById("agree-check").checked;
      if (!agreed) {
        showMessage("회원탈퇴 약관에 동의해주세요."); 
        return;
      }
      document.getElementById("withdrawal-popup").classList.remove("hidden");
    });
  }

  window.closeWithdrawalPopup = function () {
    document.getElementById("withdrawal-popup").classList.add("hidden");
  };

  window.confirmWithdrawal = function () {
    const id = document.getElementById("withdraw-id").value.trim();
    const pw = document.getElementById("withdraw-pw").value.trim();
    if (!id || !pw) {
      showMessage("아이디와 비밀번호를 입력해주세요."); 
      return;
    }
    // TODO: 서버에 탈퇴 요청 로직 추가
    console.log("탈퇴 시도:", id);
    // document.getElementById("withdrawal-form").submit();
  };

  // ----------------------------------------------------
  // ✅ 6. 팔로우/팔로워 관리 기능 (데이터 로딩 및 처리)
  // ----------------------------------------------------
  const followingListEl = document.getElementById('following-list');
  const followerListEl = document.getElementById('follower-list');
  const followingCountEl = document.getElementById('following-count');
  const followerCountEl = document.getElementById('follower-count');
  const followWrapper = document.querySelector('.follow-wrapper');
  
  // **목록 항목 생성 함수**
  function createFollowListItem(user, listType, currentUserId) {
      const li = document.createElement('li');
      li.classList.add('follow-item');
      li.dataset.id = user.id;

      const isFollowing = listType === 'following';
      
      let actionButtonHtml = '';
      
      // 자기 자신이 아닌 경우에만 버튼 생성
      if (user.id !== currentUserId) {
        const buttonText = isFollowing ? '언팔로잉' : '팔로잉';
        const buttonClass = isFollowing ? 'following' : ''; 
        
        actionButtonHtml = `
            <button class="follow-toggle ${buttonClass}" data-id="${user.id}">
                ${buttonText}
            </button>
        `;
      }


      const profileHtml = `
          <div class="user-info">
              <!-- user.icon이 null일 경우 기본 이미지 사용 -->
              
              <div>
                  <span class="user-nick">${user.nick}</span>
                  <!-- user.introduction이 null일 경우 공백 처리 -->
                  <span class="user-intro">${user.introduction || ''}</span>
              </div>
          </div>
          ${actionButtonHtml}
      `;
      li.innerHTML = profileHtml;
      return li;
  }

  // **목록 로드 함수 (API 오류 디버깅 강화)**
  window.loadFollowData = function (userId) {
      if (!userId || userId === 'testuser') {
          // JSP에서 ID를 가져오지 못했거나 로그인하지 않은 경우
          followingListEl.innerHTML = '<li class="empty-message error">❌ 로그인 ID를 찾을 수 없습니다. (JSP data-login-user-id 확인 필요)</li>';
          followerListEl.innerHTML = '<li class="empty-message error">❌ 로그인 ID를 찾을 수 없습니다. (JSP data-login-user-id 확인 필요)</li>';
          followingCountEl.textContent = '0';
          followerCountEl.textContent = '0';
          return;
      }
      
      // 로딩 메시지 표시
      const loadingMessage = '<li class="empty-message">데이터를 불러오는 중입니다...</li>';
      if (!followingListEl.innerHTML || followingListEl.innerHTML.includes('오류')) followingListEl.innerHTML = loadingMessage;
      if (!followerListEl.innerHTML || followerListEl.innerHTML.includes('오류')) followerListEl.innerHTML = loadingMessage;

      // 1. 팔로잉 목록 로드
      fetch(`${location.origin}${CONTEXT_PATH}/api/follow/followings`)
          .then(res => {
          		console.log(res)
              // ⚠️ 응답 상태 확인 (디버깅 핵심)
              if (!res.ok) {
                  console.error(`[API ERROR] 팔로잉: URL/경로 오류 또는 서버 오류 발생. 상태 코드: ${res.status}`);
                  return res.text().then(text => { throw new Error(`HTTP Error ${res.status}: ${res.statusText || '서버 응답 없음'}`) });
              }
              return res.json();
          })
          .then(data => {
          	  
              followingListEl.innerHTML = ''; 
              if (!Array.isArray(data)) {
                  console.error("Error: Followings data is not an array.", data);
                  followingListEl.innerHTML = '<li class="empty-message error">서버 응답 데이터 형식이 잘못되었습니다. (콘솔 확인)</li>';
                  return;
              }

              followingCountEl.textContent = data.length;

              if (data.length === 0) {
                  followingListEl.innerHTML = '<li class="empty-message">현재 팔로우하는 사람이 없습니다.</li>';
              } else {
                  data.forEach(user => followingListEl.appendChild(createFollowListItem(user, 'following', userId)));
              }
          })
          .catch(error => {
              console.error('Error loading followings:', error);
              const statusCodeMatch = error.message.match(/HTTP Error (\d+)/);
              const statusCode = statusCodeMatch ? statusCodeMatch[1] : '확인 불가';
              followingListEl.innerHTML = `<li class="empty-message error">❌ 팔로잉 목록 로드 중 오류 발생. (코드: ${statusCode}). 서버 로그를 확인하세요.</li>`;
          });

      // 2. 팔로워 목록 로드
      fetch(`${location.origin}${CONTEXT_PATH}/api/follow/followers`)
          .then(res => {
              // ⚠️ 응답 상태 확인 (디버깅 핵심)
              if (!res.ok) {
                  console.error(`[API ERROR] 팔로워: URL/경로 오류 또는 서버 오류 발생. 상태 코드: ${res.status}`);
                  return res.text().then(text => { throw new Error(`HTTP Error ${res.status}: ${res.statusText || '서버 응답 없음'}`) });
              }
              return res.json();
          })
          .then(data => {
              console.log(data);
              followerListEl.innerHTML = ''; 
              if (!Array.isArray(data)) {
                  console.error("Error: Followers data is not an array.", data);
                  followerListEl.innerHTML = '<li class="empty-message error">서버 응답 데이터 형식이 잘못되었습니다. (콘솔 확인)</li>';
                  return;
              }

              followerCountEl.textContent = data.length;

              if (data.length === 0) {
                  followerListEl.innerHTML = '<li class="empty-message">나를 팔로우하는 사람이 없습니다.</li>';
              } else {
                  data.forEach(user => followerListEl.appendChild(createFollowListItem(user, 'follower', userId)));
              }
          })
          .catch(error => {
              console.error('Error loading followers:', error);
              const statusCodeMatch = error.message.match(/HTTP Error (\d+)/);
              const statusCode = statusCodeMatch ? statusCodeMatch[1] : '확인 불가';
              followerListEl.innerHTML = `<li class="empty-message error">❌ 팔로워 목록 로드 중 오류 발생. (코드: ${statusCode}). 서버 로그를 확인하세요.</li>`;
          });
  };

window.handleFollowAction = function (targetId, button) {
    const originalText = button.textContent.trim(); 
    
    // JSP에서 설정된 전역 변수 사용
    const currentUserId = window.LOGGED_IN_USER_ID;
    const contextPath = window.CONTEXT_PATH || '';

    // 1. 로그인 유효성 검사 
    if (!currentUserId || String(currentUserId).trim() === '' || String(currentUserId).toLowerCase() === 'null') {
        window.showMessage("⚠️ 로그인이 필요합니다.");
        return;
    }
    
    // 2. 자기 자신을 팔로우/언팔로우 시도 방지
    if (currentUserId === targetId) {
        window.showMessage("스스로를 팔로우/언팔로우 할 수 없습니다.");
        return;
    }

    // 3. UI 업데이트 및 요청 준비
    button.disabled = true;
    button.style.opacity = '0.5';
    button.textContent = '처리 중...';

    const isFollowing = button.classList.contains('following');
    // 서버의 toggle 엔드포인트에 전달할 action 타입
    const action = isFollowing ? 'unfollow' : 'follow'; 
    
    // 🚩 URL을 /api/follow/toggle로 고정
    const apiUrl = `${location.origin}${contextPath}/api/follow/toggle`;
    
    // AJAX 요청
    fetch(apiUrl, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        // 타겟 ID와 함께 수행할 'action' (follow 또는 unfollow)을 서버로 전송
        body: JSON.stringify({ targetId: targetId, action: action }) 
    })
    .then(res => {
        if (!res.ok) {
            // 서버 응답이 JSON이 아닐 경우 대비
            return res.json().catch(() => { 
              throw new Error(`HTTP Error ${res.status}: ${res.statusText || '서버 응답 오류'}`) 
            });
        }
        return res.json();
    })
    .then(response => {
        if (response.success) {
            // response.status는 서버에서 받은 최종 상태 (followed 또는 unfollowed)여야 합니다.
            if (response.status === 'unfollowed') {
                button.textContent = '팔로우';
                button.classList.remove('following');
                
            } else if (response.status === 'followed') {
                button.textContent = '팔로잉';
                button.classList.add('following');
            } else {
                // 서버가 예상치 못한 성공 상태를 반환했을 경우
                window.showMessage(response.message || '팔로우 상태 변경 성공 (새로고침 필요)');
            }
            
            // 2. 카운트 및 목록 재로드 (실시간 반영)
            window.loadFollowData(currentUserId);

        } else {
            // 서버가 success: false를 반환한 경우
            window.showMessage(response.message || response.error || '처리 중 오류 발생');
        }
    })
    .catch(error => {
        console.error('AJAX Error:', error);
        window.showMessage('네트워크 또는 서버 오류: 팔로우/언팔로우 실패');
    })
    .finally(() => {
        // UI 복구
        button.disabled = false;
        button.style.opacity = '1';
        
        // 오류로 인해 텍스트가 '처리 중...'인 상태로 멈춰 있을 경우 원래 텍스트로 복구
        if (button.textContent === '처리 중...') {
           button.textContent = originalText; 
        }
    });
};
if (followWrapper) {
  followWrapper.addEventListener('click', function(e) {
    const button = e.target.closest('.follow-toggle');
    if (!button) return;

    const targetId = button.dataset.id;
    const currentUserId = window.LOGGED_IN_USER_ID;
    const contextPath = window.CONTEXT_PATH || '';

    if (!currentUserId) {
      window.showMessage('⚠️ 로그인 후 이용해주세요.');
      return;
    }
    if (currentUserId === targetId) {
      window.showMessage('자기 자신은 팔로우할 수 없습니다.');
      return;
    }

    // 로컬 상태 저장 (원상복구용)
    const originalText = button.textContent;
    const wasFollowing = button.classList.contains('following');

    // UI 즉시 변경 (낙관적 업데이트)
    button.disabled = true;
    button.style.opacity = '0.6';
    if (wasFollowing) {
      // 언팔로우 진행 UI
      button.classList.remove('following');
      button.textContent = '팔로잉';
      // 팔로잉 카운트 하나 감소 (즉시 반영)
      followingCountEl.textContent = Math.max(0, parseInt(followingCountEl.textContent||'0', 10) - 1);
      // 팔로잉 리스트에서 항목 제거 (있다면)
      const liToRemove = followingListEl.querySelector(`li.follow-item[data-id="${targetId}"]`);
      if (liToRemove) liToRemove.remove();
    } else {
      // 팔로우 진행 UI
      button.classList.add('following');
      button.textContent = '언팔로잉';
      // 팔로잉 카운트 하나 증가 (즉시 반영)
      followingCountEl.textContent = (parseInt(followingCountEl.textContent||'0', 10) + 1).toString();

      // 팔로잉 리스트에 즉시 추가 (간단한 항목 생성, 서버 응답과 다를 수 있으므로 재로딩으로 보완)
      // user 데이터가 팔로워 목록에 있으면 그 항목을 이동. 없으면 임시 항목 생성.
      const existingFollowerLi = followerListEl.querySelector(`li.follow-item[data-id="${targetId}"]`);
      if (existingFollowerLi) {
        // follower-list에 있으면 clone하여 following-list 앞으로 추가
        const clone = existingFollowerLi.cloneNode(true);
        // 버튼 텍스트/클래스 정리 (clone 내부 버튼)
        const clonedBtn = clone.querySelector('.follow-toggle');
        if (clonedBtn) {
          clonedBtn.classList.add('following');
          clonedBtn.textContent = '언팔로잉';
        }
        // 데이터-id 유지되어 있으므로 바로 append
        followingListEl.prepend(clone);
      } else {
        // follower-list에 없으면 임시 빈 사용자 객체로 빠르게 생성 (닉네임/소개가 서버와 다를 수 있음)
        const tempUser = { id: targetId, nick: targetId, introduction: '' };
        const newLi = createFollowListItem(tempUser, 'following', currentUserId);
        // ensure button in newLi shows '언팔로잉'
        const newBtn = newLi.querySelector('.follow-toggle');
        if (newBtn) { newBtn.classList.add('following'); newBtn.textContent = '언팔로잉'; }
        followingListEl.prepend(newLi);
      }
    }

    // 실제 서버 요청 (토글)
    fetch(`${contextPath}/api/follow/toggle`, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ targetId })
    })
    .then(res => res.json())
    .then(result => {
      console.log('[toggle result]', result);
      if (!result || !result.success) {
        // 실패 시 롤백: 원래 상태로 복구하고 목록 재로드
        window.showMessage(result && result.message ? result.message : '처리에 실패했습니다. 변경을 되돌립니다.');
        // 강제 재로딩으로 동기화
        if (typeof window.loadFollowData === 'function') {
          window.loadFollowData(currentUserId);
        }
        return;
      }

      // 성공: 서버 상태와 UI 동기화 (안전차원으로 전체 재로딩 권장)
      if (typeof window.loadFollowData === 'function') {
        window.loadFollowData(currentUserId);
      }
    })
    .catch(err => {
      console.error('팔로우 토글 서버 오류:', err);
      window.showMessage('네트워크 오류가 발생했습니다. 변경을 되돌립니다.');
      // 롤백: 전체 재로딩
      if (typeof window.loadFollowData === 'function') {
        window.loadFollowData(currentUserId);
      }
    })
    .finally(() => {
      button.disabled = false;
      button.style.opacity = '1';
    });
  });
}



  // **탭 버튼 이벤트 리스너**
  const followTabButtons = document.querySelectorAll('.follow-tabs .tab-button');
  followTabButtons.forEach(btn => {
      btn.addEventListener('click', function() {
          followTabButtons.forEach(b => b.classList.remove('active'));
          btn.classList.add('active');

          // 목록 전환 (data-target 사용)
          const targetListId = btn.dataset.target;
          document.querySelectorAll('.follow-list').forEach(list => list.classList.add('hidden'));
          document.getElementById(targetListId).classList.remove('hidden');
      });
  });

});
