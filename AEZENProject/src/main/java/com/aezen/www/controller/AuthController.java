package com.aezen.www.controller;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aezen.www.vo.UserVO;

import java.util.HashMap;
import java.util.Map;

@RestController
public class AuthController {

    @GetMapping("/auth/me")
    public Map<String, Object> getCurrentUser(HttpSession session) {
        Map<String, Object> res = new HashMap<>();

        UserVO loginUser = (UserVO) session.getAttribute("login");

        if (loginUser == null) {
            res.put("success", false);
            res.put("userId", null);
        } else {
            res.put("success", true);
            res.put("userId", loginUser.getId());
            res.put("nickname", loginUser.getNick());
        }

        return res;
    }
}
