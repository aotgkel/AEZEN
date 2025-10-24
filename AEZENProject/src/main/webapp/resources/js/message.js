// 📄 message.js (수정완성본)

// ✅ 중복 선언 방지 — JSP에서 이미 선언된 경우 그대로 사용
var CONTEXT_PATH = window.CONTEXT_PATH || '';
const API_BASE = `${location.origin}${CONTEXT_PATH}/message`;


let roomNo;

document.addEventListener('DOMContentLoaded', async function () {
  
  const currentUserId = window.LOGGED_IN_USER_ID; // 나중에 동적으로 바꿀 예정

  async function fetchJSON(url, options = {}) {
    const res = await fetch(url, options);
    if (!res.ok) throw new Error(`HTTP ${res.status}`);
    return res.json();
  }

  // ✅ 메시지 목록 불러오기
  async function loadMessages() {
    try {
      const list = await fetchJSON(`${API_BASE}/received`);
      const container = document.querySelector('.message-list');
      container.innerHTML = '';

      if (!list || list.length === 0) {
        container.innerHTML = `<div class="empty">📭 받은 메시지가 없습니다.</div>`;
        return;
      }
      
      //document.getElementById("message-unread-badge").innerText = "🔴 "+list.length;

      list.forEach(msg => {
        const div = document.createElement('div');
        div.className = `message-item`;
        div.dataset.messageNo = msg.messageNo;
        div.dataset.roomNo = msg.roomNo;
        roomNo = msg.roomNo;
        div.innerHTML = `
          <input type="checkbox" class="message-check">
          <div class="message-left">
            <div class="nickname-group">
              <span class="nickname">${msg.senderId}</span>
              <span class="direction">[받음]</span>
            </div>
            <span class="content">${msg.messageContent}</span>
          </div>
          <div class="message-actions">
            <button class="btn-more" onclick="viewMessage(${msg.roomNo})">더보기</button>
            <button class="btn-delete">삭제</button>
          </div>
        `;
        container.appendChild(div);
      });
    } catch (err) {
      console.error('❌ 메시지 목록 불러오기 실패:', err);
      alert('메시지를 불러오는데 실패했습니다.');
    }
  }

  // ✅ 메시지 삭제
  async function deleteMessage(messageNo, element) {
    if (!confirm('이 메시지를 삭제하시겠습니까?')){
    	return;
    };

    try {
      const res = await fetch(`${API_BASE}/${messageNo}`, { method: 'DELETE' });
      if (res.ok) {
        element.remove();
        const messageItem = document.getElementsByClassName("message-item");
        if(messageItem.length < 1){
        	const container = document.querySelector('.message-list');
        	container.innerHTML = `<div class="empty">📭 받은 메시지가 없습니다.</div>`;
        }
        alert('삭제되었습니다.');
      } else {
        alert('삭제 실패');
      }
    } catch (err) {
      console.error('❌ 삭제 오류:', err);
      alert('삭제 중 오류가 발생했습니다.');
    }
  }

  // ✅ 선택 삭제
  async function deleteSelectedMessages() {
    const selected = Array.from(document.querySelectorAll('.message-check:checked'));
    if (selected.length === 0) return alert('삭제할 메시지를 선택하세요.');

    if (!confirm(`${selected.length}개의 메시지를 삭제하시겠습니까?`)) return;

    for (const cb of selected) {
      const item = cb.closest('.message-item');
      const messageNo = item.dataset.messageNo;
      await fetch(`${API_BASE}/${messageNo}`, { method: 'DELETE' });
      item.remove();
    }

    alert('선택한 메시지가 삭제되었습니다.');
  }

  // ✅ 이벤트 위임 (삭제버튼)
  document.querySelector('.message-list').addEventListener('click', function (e) {
    const item = e.target.closest('.message-item');
    if (!item) return;

    const messageNo = item.dataset.messageNo;

    if (e.target.classList.contains('btn-delete')) {
      e.stopPropagation();
      deleteMessage(messageNo, item);
    }
  });

  // ✅ 전체 선택
  document.getElementById('select-all').addEventListener('change', function () {
    const checkboxes = document.querySelectorAll('.message-check');
    checkboxes.forEach(cb => (cb.checked = this.checked));
  });

  // ✅ 선택 삭제 버튼
  document.querySelector('.btn-delete-selected-message').addEventListener('click', deleteSelectedMessages);

  // ✅ 메시지 전송
 window.sendReply = async function () {
  const input = document.getElementById('reply-input');
  const text = input.value.trim();
  if (!text) return alert('메시지를 입력하세요.');

  if (!targetUserId) {
    alert("대화 상대 정보가 없습니다. 메시지를 보낼 수 없습니다.");
    return;
  }

  const payload = {
    senderId: currentUserId,
    messageContent: text,
    roomNo: roomNo,
    receiverId: targetUserId,
  };

  try {
    const res = await fetchJSON(`${API_BASE}/send`, {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(payload),
    });

    if (res.success) {
      // ✅ 입력창 비우기
      input.value = '';

      // ✅ 새 메시지를 바로 채팅창에 추가
      const chatBox = document.getElementById('chat-box');
      const bubble = document.createElement('div');
      bubble.className = 'chat-bubble sent';
      bubble.textContent = text;

      chatBox.appendChild(bubble);

      // ✅ 최신 메시지가 보이도록 스크롤 맨 아래로
      chatBox.scrollTop = chatBox.scrollHeight;
    } else {
      alert('메시지 전송 실패');
    }
  } catch (err) {
    console.error('❌ 메시지 전송 오류:', err);
    alert('메시지 전송 중 오류가 발생했습니다.');
  }
};

  // ✅ 뒤로가기
  window.goBackToList = function () {
    document.getElementById('chat-panel').classList.add('hidden');
    document.getElementById('messages-panel').classList.remove('hidden');
  };

  // ✅ viewMessage — 전역 등록 (HTML onclick에서 호출 가능)
  window.viewMessage = async function (roomNo) {
  try {
    const messages = await fetchJSON(`${API_BASE}/room/${roomNo}`);
    const chatBox = document.getElementById('chat-box');
    chatBox.innerHTML = '';

    // ✅ 대화상대(targetUserId) 자동 세팅
    if (messages.length > 0) {
      const firstMsg = messages[0];
      const currentUserId = window.LOGGED_IN_USER_ID;

      if (firstMsg.senderId === currentUserId) {
        targetUserId = firstMsg.receiverId;
      } else {
        targetUserId = firstMsg.senderId;
      }

      console.log('🎯 대화상대 ID 세팅 완료:', targetUserId);
    }

    messages.forEach(msg => {
      const bubble = document.createElement('div');
      bubble.className = `chat-bubble ${msg.senderId === currentUserId ? 'sent' : 'received'}`;
      bubble.textContent = msg.messageContent;
      chatBox.appendChild(bubble);
    });

    document.getElementById('messages-panel').classList.add('hidden');
    document.getElementById('chat-panel').classList.remove('hidden');
  } catch (err) {
    console.error('❌ 대화 불러오기 실패:', err);
    alert('대화 내용을 불러올 수 없습니다.');
  }
};
  // ✅ 최초 실행
  loadMessages();
});
