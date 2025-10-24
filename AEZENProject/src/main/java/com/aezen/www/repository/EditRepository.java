package com.aezen.www.repository;

import org.springframework.stereotype.Repository;
import com.aezen.www.vo.UserVO;

@Repository
public interface EditRepository {
    int updateUserInfo(UserVO user);
    UserVO getUserInfo(String id);
 
    int checkNickExists(String nick);

}
