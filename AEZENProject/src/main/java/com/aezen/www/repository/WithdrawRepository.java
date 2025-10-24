package com.aezen.www.repository;

import com.aezen.www.vo.UserVO;

public interface WithdrawRepository {
    UserVO findUserById(String id);
    int updateWithdrawStatus(String id);
    int hideUserBoards(String id);
}
