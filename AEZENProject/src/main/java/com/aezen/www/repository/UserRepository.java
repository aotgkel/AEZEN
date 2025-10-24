package com.aezen.www.repository;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import com.aezen.www.vo.*;

@Repository
public class UserRepository {
	@Autowired
	private SqlSession session;

	private static final String namespace = "com.aezen.www.header";

	// 로그인
	public UserVO login(String id, String pw)
	{
		UserVO param = new UserVO();
		param.setId(id.trim());
		param.setPassword(pw.trim()); // 공백 제거

		UserVO vo = session.selectOne(namespace + ".login", param);
		return vo;
	}
	
	// 로그인 시간 업데이트
    public void updateLastLoginAt(String id) {
        session.update(namespace + ".updateLastLoginAt", id);
    }
    // 회원가입 메서드 추가 구현
    public int insertUser(UserVO userVO) {
        // XML의 id가 signup라고 가정
        return session.insert(namespace + ".signup", userVO); 
    }
    
    // 아이디 중복 체크 메서드 추가 구현
    public int countByUserid(String userid) {
        // XML의 id가 countByUserid라고 가정
        return session.selectOne(namespace + ".countByUserid", userid); 
    }
    
    // 닉네임 중복 체크 메서드 추가 구현
    public int countByNickname(String nickname) {
        // XML의 id가 countByNickname라고 가정
        return session.selectOne(namespace + ".countByNickname", nickname); 
    }
    
    public int countByEmail(String email) {
        return session.selectOne(namespace + ".countByEmail", email); 
    }
    
    public int updatePasswordByEmail(String email, String newPassword) {
        // XML 쿼리가 Map을 인자로 받으므로, Map을 생성하여 전달합니다.
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("email", email);
        paramMap.put("newPassword", newPassword);
        
        return session.update(namespace + ".updatePasswordByEmail", paramMap);
    }
}
