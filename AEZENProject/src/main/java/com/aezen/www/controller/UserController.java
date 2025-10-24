package com.aezen.www.controller;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
//💡 [추가] 해싱 및 랜덤 문자열 생성을 위한 Import
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64; // 바이트 배열을 문자열로 변환
import java.util.Random;


import com.aezen.www.repository.UserRepository;
import com.aezen.www.vo.UserVO;

@Controller
public class UserController {
	
	@Autowired
	UserRepository userRepository;
	
	@RequestMapping(value="/account/signup", method = RequestMethod.GET)
	public String signup() {
		return "account/join_info";
	}
	
	@RequestMapping(value="/account/signupok", method = RequestMethod.POST)
	public String signupOk(UserVO user) {
		//@ReuqestParam("id") String id
		//insert into user(id, name, nick, mail, password)
		//values('hong', '홍길동', hong, ~~, 1234)
		userRepository.insertUser(user);
		return "redirect:/home";
	}
	// 아이디 중복 확인 엔드포인트 추가
   
	@GetMapping("/check-userid")
    @ResponseBody // JSON 형태로 응답하기 위함
    public Map<String, Boolean> checkUserid(@RequestParam("userid") String userid) {
        // UserRepository의 countByUserid를 호출하여 중복 여부를 확인합니다.
        // 0이면 사용 가능 (중복 아님), 1 이상이면 중복
        int count = userRepository.countByUserid(userid);
        boolean exists = count > 0;
        
        // JSON 응답을 위한 Map 생성
        Map<String, Boolean> response = new HashMap<>();
        response.put("exists", exists);
        
        return response; // {"exists": true} 또는 {"exists": false} 반환
    }
	
	@GetMapping("/check-nickname")
    @ResponseBody 
    public Map<String, Boolean> checkUsernickname(@RequestParam("nickname") String nickname) {
		int count = userRepository.countByNickname(nickname);
		boolean exists = count > 0;          
		Map<String, Boolean> response = new HashMap<>();
		response.put("exists", exists);        
		return response;
    }
	
	@GetMapping("/check-email")
    @ResponseBody 
    public Map<String, Boolean> checkEmail(@RequestParam("email") String email) {
        int count = userRepository.countByEmail(email);
        boolean exists = count > 0;       
        Map<String, Boolean> response = new HashMap<>();
        response.put("exists", exists);        
        return response;
    }
	
	

	
	
    
  

	
}