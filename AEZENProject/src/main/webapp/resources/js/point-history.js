

document.addEventListener("DOMContentLoaded", async () => {
  const userId = window.LOGGED_IN_USER_ID;

  if (!userId) {
    console.error("로그인된 사용자 정보를 찾을 수 없습니다.");
    return;
  }

  const POINT_API_URL = `${location.origin}${CONTEXT_PATH}/point/history/${userId}`;

  try {
    const res = await fetch(POINT_API_URL);
    if (!res.ok) throw new Error("포인트 내역을 불러오는 중 오류 발생");

    const pointList = await res.json();
    console.log("✅ 포인트 내역:", pointList);

    renderPointHistory(pointList);
  } catch (err) {
    console.error("❌ 서버 통신 오류:", err);
  }
});


// ============================
// 💡 포인트 내역 테이블 렌더링
// ============================
function renderPointHistory(pointList) {
  const tbody = document.querySelector("#point-history-section tbody");

  if (!tbody) {
    console.warn("⚠️ 포인트 내역 테이블 tbody 요소를 찾을 수 없습니다.");
    return;
  }

  if (pointList.length === 0) {
    tbody.innerHTML = `
      <tr>
        <td colspan="8" style="text-align:center; color:gray;">포인트 내역이 없습니다.</td>
      </tr>
    `;
    return;
  }

  let totalPoints = 0;
  const rows = pointList
    .map((p) => {
      totalPoints += p.pointAmount;

      return `
        <tr>
          <td><input type="checkbox" class="point-check" data-point-no="${p.pointNo}"></td>
          <td>${p.pointCreatedAt ? p.pointCreatedAt.split('T')[0] : '-'}</td>
          <td>${p.pointReason || '내용 없음'}</td>
          <td style="color:${p.pointAmount > 0 ? 'green' : 'red'};">
            ${p.pointAmount > 0 ? '+' : ''}${p.pointAmount}
          </td>
          <td>${totalPoints}</td>
          <td>${p.boardNo ? `<a href="/board/detail/${p.boardNo}">바로가기</a>` : '-'}</td>
          <td>${getPointTypeName(p.pointType)}</td>
          
        </tr>
      `;
    })
    .join("");

  tbody.innerHTML = rows;
}


// ============================
// 🧩 포인트 타입 한글 변환
// ============================
function getPointTypeName(type) {
  switch (type) {
    case 1:
      return "사용완료";
    case 2:
      return "적립완료";
    case 3:
      return "보너스";
    default:
      return "기타";
  }
}


// ============================
// 🗑️ 포인트 삭제 기능
// ============================
async function deletePoint(pointNo) {
  if (!confirm("이 포인트 내역을 삭제하시겠습니까?")) return;

  try {
    const res = await fetch(`${location.origin}${CONTEXT_PATH}/point/${pointNo}`, {
      method: "DELETE",
    });

    const data = await res.json();

    if (data.success) {
      alert("삭제 완료되었습니다.");
      document.querySelector(`.point-check[data-point-no="${pointNo}"]`)?.closest("tr")?.remove();
    } else {
      alert("삭제 실패: 서버 오류");
    }
  } catch (err) {
    console.error("삭제 요청 중 오류 발생:", err);
  }
}
