package com.aezen.www.controller.mypage;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.aezen.www.repository.EditRepository;
import com.aezen.www.vo.UserVO;

@RestController
@RequestMapping("/edit")
public class EditController {

    @Autowired
    private EditRepository editRepository;

    @PostMapping("/update")
    public String updateUserInfo(@RequestBody UserVO user) {
        int result = editRepository.updateUserInfo(user);
        return result > 0 ? "success" : "fail";
    }
    @GetMapping("")
    public String showEditPage(HttpSession session, Model model) {
        UserVO loginUser = (UserVO) session.getAttribute("login");
        if (loginUser == null) return "redirect:/login";
        model.addAttribute("loginUserId", loginUser.getId());
        return "mypage/edit"; // JSP 경로
    }
    @GetMapping("/info/{id}")
    public UserVO getUserInfo(@PathVariable String id) {
        return editRepository.getUserInfo(id);
    }
    @GetMapping("/check-nick")
    public boolean checkNick(@RequestParam String nick) {
        return editRepository.checkNickExists(nick) > 0;
    }

    @Autowired
    private JavaMailSender mailSender;

    private final Map<String, String> emailCodes = new HashMap<>();

    @PostMapping("/sendEmail")
    public String sendEmail(@RequestParam String email) {
        String code = String.valueOf((int)(Math.random() * 900000) + 100000);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("이젠 커뮤니티 이메일 인증 코드");
        message.setText("인증 코드: " + code);
        message.setFrom("jjibask@naver.com");
        mailSender.send(message);
        
        emailCodes.put(email, code);
        return "sent";
    }

    @PostMapping("/verifyCode")
    public String verifyCode(@RequestParam String email, @RequestParam String code) {
        String storedCode = emailCodes.get(email);
        return storedCode != null && storedCode.equals(code) ? "verified" : "invalid";
    }

}

