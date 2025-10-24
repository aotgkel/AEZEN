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

	// �α���
	public UserVO login(String id, String pw)
	{
		UserVO param = new UserVO();
		param.setId(id.trim());
		param.setPassword(pw.trim()); // ���� ����

		UserVO vo = session.selectOne(namespace + ".login", param);
		return vo;
	}
	
	// �α��� �ð� ������Ʈ
    public void updateLastLoginAt(String id) {
        session.update(namespace + ".updateLastLoginAt", id);
    }
    // ȸ������ �޼��� �߰� ����
    public int insertUser(UserVO userVO) {
        // XML�� id�� signup��� ����
        return session.insert(namespace + ".signup", userVO); 
    }
    
    // ���̵� �ߺ� üũ �޼��� �߰� ����
    public int countByUserid(String userid) {
        // XML�� id�� countByUserid��� ����
        return session.selectOne(namespace + ".countByUserid", userid); 
    }
    
    // �г��� �ߺ� üũ �޼��� �߰� ����
    public int countByNickname(String nickname) {
        // XML�� id�� countByNickname��� ����
        return session.selectOne(namespace + ".countByNickname", nickname); 
    }
    
    public int countByEmail(String email) {
        return session.selectOne(namespace + ".countByEmail", email); 
    }
    
    public int updatePasswordByEmail(String email, String newPassword) {
        // XML ������ Map�� ���ڷ� �����Ƿ�, Map�� �����Ͽ� �����մϴ�.
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("email", email);
        paramMap.put("newPassword", newPassword);
        
        return session.update(namespace + ".updatePasswordByEmail", paramMap);
    }
}
