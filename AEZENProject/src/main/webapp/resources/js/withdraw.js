document.addEventListener("DOMContentLoaded", () => {
  const confirmBtn = document.querySelector("#withdrawal-popup .confirm"); // ✅ 특정 모달 안의 버튼만 선택

  if (confirmBtn) {
    console.log("✅ 탈퇴 확인 버튼 이벤트 등록 완료");

    confirmBtn.addEventListener("click", async function () {
      const id = document.getElementById("withdraw-id").value.trim();
      const pw = document.getElementById("withdraw-pw").value.trim();

      console.log("탈퇴 시도:", id);

      if (!id || !pw) {
        alert("아이디와 비밀번호를 입력해주세요.");
        return;
      }

      if (!confirm("정말로 회원탈퇴를 진행하시겠습니까?")) return;

      try {
        const res = await fetch(`${CONTEXT_PATH}/withdraw/confirm`, {
          method: "POST",
          headers: { "Content-Type": "application/x-www-form-urlencoded" },
          body: `id=${encodeURIComponent(id)}&password=${encodeURIComponent(pw)}`
        });

        const result = await res.text();
        console.log("서버 응답:", result);

        switch (result) {
          case "deleted":
            document.getElementById("withdrawal-popup").classList.add("hidden");
            document.getElementById("withdrawal-complete").classList.remove("hidden");
            break;
          case "wrong_pw":
            alert("비밀번호가 일치하지 않습니다 ❌");
            break;
          case "not_match_id":
            alert("현재 로그인된 계정과 입력한 아이디가 다릅니다 ⚠️");
            break;
          case "already_withdrawn":
            alert("이미 탈퇴한 계정입니다 ❗");
            break;
          default:
            alert("회원탈퇴 처리 중 오류가 발생했습니다 ❌");
            break;
        }
      } catch (err) {
        console.error("❌ 탈퇴 중 오류:", err);
        alert("서버 오류가 발생했습니다 ❌");
      }
    });
  } else {
    console.error("❌ 탈퇴 확인 버튼(.confirm)을 찾을 수 없습니다.");
  }
});
// ✅ 홈으로 이동 버튼 기능
window.goToHome = function() {
  // 홈 URL은 프로젝트 구조에 맞게 수정하세요.
  // 예: CONTEXT_PATH가 "/AEZENProject"라면
  window.location.href = `${CONTEXT_PATH}/home`;
};