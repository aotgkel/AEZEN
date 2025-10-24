package com.aezen.www.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.aezen.www.vo.FollowVO;
import com.aezen.www.vo.UserVO; // UserVO import 추가

/*
 * 인터페이스 기능: 팔로우 관련 데이터베이스 접근 및 로직을 담당하는 Repository 인터페이스
 * (이전의 DAO 역할)
 */
@Repository // Spring Bean으로 등록되도록 지정
public interface FollowRepository {
    
    // MyBatis Mapper XML에 매핑되는 메서드들은 그대로 유지됩니다.

    /**
     * 팔로우 관계를 DB에 삽입합니다. (팔로우 요청)
     */
    int insertFollow(FollowVO followVO);
    
    /**
     * 팔로우 관계를 DB에서 삭제합니다. (언팔로우 요청)
     */
    int deleteFollow(FollowVO followVO);

    /**
     * 특정 팔로우 관계의 상태를 확인합니다. (팔로우 중인지 여부)
     */
    int countFollowStatus(FollowVO followVO);

    /**
     * 특정 사용자의 팔로워 목록(나를 팔로우 하는 사람)을 조회합니다.
     * UserVO 정보를 포함하여 반환하도록 변경됩니다.
     */
    List<UserVO> getFollowers(String followedId);

    /**
     * 특정 사용자의 팔로잉 목록(내가 팔로우 하는 사람)을 조회합니다.
     * UserVO 정보를 포함하여 반환하도록 변경됩니다.
     */
    List<UserVO> getFollowings(String followingId);
}
