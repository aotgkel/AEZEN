document.addEventListener("DOMContentLoaded", async () => {
  const userId = window.LOGGED_IN_USER_ID;

  if (!userId) {
    console.error("로그인된 사용자 ID가 없습니다.");
    return;
  }

  // ✅ 로그인한 유저 정보 불러오기
  try {
    const res = await fetch(`${CONTEXT_PATH}/edit/info/${userId}`);
    if (!res.ok) throw new Error("회원 정보를 불러오지 못했습니다.");
    const user = await res.json();

    console.log("✅ 불러온 유저 정보:", user);

    // 폼 초기화
    document.querySelector("#userid").value = user.id || "";
    document.querySelector("#nickname").value = user.nick || "";
    document.querySelector("#email").value = user.email || "";
  } catch (err) {
    console.error("❌ 유저 정보 불러오기 오류:", err);
  }

  // ✅ 수정 폼 제출 이벤트
  const form = document.querySelector(".edit-form");
  form.addEventListener("submit", async (e) => {
    e.preventDefault();

    const id = document.querySelector("#userid").value;
    const password = document.querySelector("#password").value;
    const confirm = document.querySelector("#confirm-password").value;
    const nickname = document.querySelector("#nickname").value;
    const email = document.querySelector("#email").value;

    if (password && password !== confirm) {
      alert("비밀번호가 일치하지 않습니다.");
      return;
    }

    const payload = { id, password, nick: nickname, email };

    try {
      const res = await fetch(`${CONTEXT_PATH}/edit/update`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(payload),
      });

      const result = await res.text();
      if (result === "success") {
        alert("회원정보가 수정되었습니다.");
        location.reload();
      } else {
        alert("수정 실패! 잠시 후 다시 시도해주세요.");
      }
    } catch (err) {
      console.error("❌ 서버 통신 오류:", err);
      alert("서버 오류가 발생했습니다.");
    }
  });

  // ✅ 비밀번호 확인 실시간 체크
  const passwordInput = document.querySelector("#password");
  const confirmInput = document.querySelector("#confirm-password");

  confirmInput.addEventListener("input", () => {
    let message = document.querySelector("#pw-check-msg");
    if (!message) {
      message = document.createElement("span");
      message.id = "pw-check-msg";
      confirmInput.insertAdjacentElement("afterend", message);
    }

    if (!passwordInput.value) {
      message.textContent = "";
      return;
    }

    if (passwordInput.value === confirmInput.value) {
      message.textContent = "비밀번호가 일치합니다 ✅";
      message.style.color = "green";
    } else {
      message.textContent = "비밀번호가 일치하지 않습니다 ❌";
      message.style.color = "red";
    }
  });

  // ✅ 닉네임 중복 확인
  const nickCheckBtn = document.querySelector("#nickname + button");
  if (nickCheckBtn) {
    nickCheckBtn.addEventListener("click", async () => {
      const nick = document.querySelector("#nickname").value.trim();
      if (!nick) {
        alert("닉네임을 입력하세요.");
        return;
      }

      try {
        const res = await fetch(`${CONTEXT_PATH}/edit/check-nick?nick=${encodeURIComponent(nick)}`);
        const exists = await res.json();
        if (exists) {
          alert("이미 사용 중인 닉네임입니다 ❌");
        } else {
          alert("사용 가능한 닉네임입니다 ✅");
        }
      } catch (err) {
        console.error("닉네임 중복 확인 오류:", err);
      }
    });
  }

// ✅ 이메일 인증
let verifiedEmail = false;

const emailVerifyBtn = document.querySelector("#emailVerifyBtn");
const verifySection = document.querySelector("#verify-section");
const verifyBtn = document.querySelector("#verifyBtn");
const verifyCodeInput = document.querySelector("#verify-code");
const verifyStatus = document.querySelector("#verify-status");

if (emailVerifyBtn) {
  emailVerifyBtn.addEventListener("click", async () => {
    const email = document.querySelector("#email").value.trim();
    if (!email) {
      verifyStatus.style.display = "block";
      verifyStatus.style.color = "red";
      verifyStatus.textContent = "이메일을 입력하세요 ❌";
      return;
    }

    const res = await fetch(`${CONTEXT_PATH}/edit/sendEmail`, {
      method: "POST",
      headers: { "Content-Type": "application/x-www-form-urlencoded" },
      body: `email=${encodeURIComponent(email)}`
    });

    const result = await res.text();

    if (result === "sent") {
      verifySection.style.display = "flex"; // 인증 입력창 표시
      verifyStatus.style.display = "block";
      verifyStatus.style.color = "blue";
      verifyStatus.textContent = "인증 메일이 발송되었습니다. 메일을 확인하세요 ✉️";
    } else {
      verifyStatus.style.display = "block";
      verifyStatus.style.color = "red";
      verifyStatus.textContent = "이메일 전송 실패 ❌";
    }
  });
}

if (verifyBtn) {
  verifyBtn.addEventListener("click", async () => {
    const email = document.querySelector("#email").value.trim();
    const code = verifyCodeInput.value.trim();

    if (!code) {
      verifyStatus.style.display = "block";
      verifyStatus.style.color = "red";
      verifyStatus.textContent = "인증 코드를 입력하세요 ❌";
      return;
    }

    const verifyRes = await fetch(`${CONTEXT_PATH}/edit/verifyCode`, {
      method: "POST",
      headers: { "Content-Type": "application/x-www-form-urlencoded" },
      body: `email=${encodeURIComponent(email)}&code=${encodeURIComponent(code)}`
    });

    const verifyResult = await verifyRes.text();

    if (verifyResult === "verified") {
      verifiedEmail = true;
      verifySection.style.display = "none";
      verifyStatus.style.display = "block";
      verifyStatus.style.color = "green";
      verifyStatus.textContent = "이메일 인증 완료 ✅";
    } else {
      verifyStatus.style.display = "block";
      verifyStatus.style.color = "red";
      verifyStatus.textContent = "인증 코드가 일치하지 않습니다 ❌";
    }
  });
  }
});
