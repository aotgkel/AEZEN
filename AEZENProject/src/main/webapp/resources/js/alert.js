document.addEventListener("DOMContentLoaded", async () => {
  const userId = window.LOGGED_IN_USER_ID;
  if (!userId) {
    console.error("로그인된 사용자 정보를 찾을 수 없습니다.");
    return;
  }

  const ALERT_API_URL = `${location.origin}${CONTEXT_PATH}/alert/history/${userId}`;

  try {
    const res = await fetch(ALERT_API_URL);
    if (!res.ok) throw new Error("알림 내역을 불러오는 중 오류 발생");

    const alertList = await res.json();
    console.log("✅ 알림 내역:", alertList);

    renderAlertHistory(alertList);
  } catch (err) {
    console.error("❌ 서버 통신 오류:", err);
  }
});


// ============================
// 💡 알림 내역 테이블 렌더링
// ============================
function renderAlertHistory(alertList) {
  const tbody = document.querySelector("#alert-history-section tbody");

  if (!tbody) return;

  if (!alertList || alertList.length === 0) {
    tbody.innerHTML = `
      <tr>
        <td colspan="6" style="text-align:center; color:gray;">알림 내역이 없습니다.</td>
      </tr>
    `;
    return;
  }

  const rows = alertList.map(a => `
    <tr>
      <td><input type="checkbox" class="alert-check" data-alert-no="${a.alertNo}"></td>
      <td>${a.alertedAt || '-'}</td>
      <td>${a.alertContent || '-'}</td>
      <td>${getAlertTypeName(a.alertType)}</td>
      <td>${a.targetBoardNo ? `<a href="/board/detail/${a.targetBoardNo}">바로가기</a>` : '-'}</td>
      <td><button class="btn-delete" data-alert-no="${a.alertNo}">삭제</button></td>
    </tr>
  `).join("");

  tbody.innerHTML = rows;

  bindAlertActions();
}


// ============================
// 🧩 알림 타입 한글 변환
// ============================
function getAlertTypeName(type) {
  switch (type) {
    case 1: return "댓글 알림";
    case 2: return "답글 알림";
    case 3: return "공지사항";
    default: return "기타";
  }
}


// ============================
// 🗑️ 삭제 관련 동작 바인딩
// ============================
function bindAlertActions() {
  // ✅ 전체 선택
  const selectAll = document.getElementById('select-all-alerts');
  if (selectAll) {
    selectAll.addEventListener('change', function () {
      document.querySelectorAll('.alert-check').forEach(chk => chk.checked = selectAll.checked);
    });
  }

  // ✅ 개별 삭제
  document.querySelectorAll('.btn-delete').forEach(btn => {
    btn.addEventListener('click', async (e) => {
      const alertNo = e.target.dataset.alertNo;
      if (!confirm('해당 알림을 삭제하시겠습니까?')) return;

      const res = await fetch(`${CONTEXT_PATH}/alert/${alertNo}`, { method: 'DELETE' });
      const result = await res.json();

      if (result.success) e.target.closest('tr').remove();
      else alert('삭제 실패');
    });
  });

  // ✅ 선택 삭제
  const deleteSelectedBtn = document.querySelector('.btn-delete-selected');
  if (deleteSelectedBtn) {
    deleteSelectedBtn.addEventListener('click', async () => {
      const selected = Array.from(document.querySelectorAll('.alert-check:checked'))
        .map(cb => cb.dataset.alertNo);

      if (selected.length === 0) return alert("삭제할 알림을 선택해주세요.");

      if (!confirm("선택된 알림을 삭제하시겠습니까?")) return;

      const res = await fetch(`${CONTEXT_PATH}/alert/deleteSelected`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(selected),
      });

      const result = await res.json();
      if (result.success) selected.forEach(no => {
        document.querySelector(`.alert-check[data-alert-no="${no}"]`)?.closest("tr")?.remove();
      });
      else alert("삭제 실패");
    });
  }
}
