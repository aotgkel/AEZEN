package com.aezen.www.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aezen.www.repository.*;
import com.aezen.www.vo.*;

@Controller
public class HeaderController
{
	@Autowired
	private UserRepository userRepository;
	
    // 로그인 처리
    @PostMapping("/login")
    @ResponseBody
    public String login(String id, String pw, HttpServletRequest request)
    {

        HttpSession session = request.getSession();
        UserVO vo = userRepository.login(id, pw);

        if (vo == null) {
            session.setAttribute("login", null);
            return "false";
        } else {
            session.setAttribute("login", vo);
            // 로그인 성공 시 마지막 로그인 시간 업데이트
            userRepository.updateLastLoginAt(vo.getId());
            return "true";
        }
    }

    // 로그아웃 처리
    @PostMapping("/logout")
    @ResponseBody
    public String logout(HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        session.invalidate();
        return "true";
    }
}
