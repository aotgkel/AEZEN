package com.aezen.www.controller.mypage;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import com.aezen.www.repository.WithdrawRepository;
import com.aezen.www.vo.UserVO;

@RestController
@RequestMapping("/withdraw")
public class WithdrawController {

    @Autowired
    private WithdrawRepository withdrawRepository;

    @PostMapping("/confirm")
    @Transactional // ✅ user + board 둘 다 처리되도록 트랜잭션 적용
    public String withdraw(@RequestParam String id,
                           @RequestParam String password,
                           HttpSession session) {

        // 로그인 유저 확인
        UserVO loginUser = (UserVO) session.getAttribute("login");
        if (loginUser == null) {
            return "not_logged_in";
        }

        // 입력한 ID가 세션과 일치하는지 확인
        if (!loginUser.getId().equals(id)) {
            return "not_match_id";
        }

        // DB에서 유저 정보 조회
        UserVO user = withdrawRepository.findUserById(id);
        if (user == null) {
            return "not_found";
        }

        // 비밀번호 일치 확인
        if (!user.getPassword().equals(password)) {
            return "wrong_pw";
        }

        // 이미 탈퇴한 회원인지 확인
        if (user.isWithdraw()) {
            return "already_withdrawn";
        }

        // ✅ 1. 회원 탈퇴 처리
        int userUpdate = withdrawRepository.updateWithdrawStatus(id);

        // ✅ 2. 회원이 작성한 게시글 숨김 처리
        int boardUpdate = withdrawRepository.hideUserBoards(id);

        if (userUpdate > 0) {
            session.invalidate(); // 세션 종료
            return "deleted";
        } else {
            return "fail";
        }
    }
}
