package com.aezen.www.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.aezen.www.vo.FollowVO;
import com.aezen.www.vo.UserVO; // UserVO import �߰�

/*
 * �������̽� ���: �ȷο� ���� �����ͺ��̽� ���� �� ������ ����ϴ� Repository �������̽�
 * (������ DAO ����)
 */
@Repository // Spring Bean���� ��ϵǵ��� ����
public interface FollowRepository {
    
    // MyBatis Mapper XML�� ���εǴ� �޼������ �״�� �����˴ϴ�.

    /**
     * �ȷο� ���踦 DB�� �����մϴ�. (�ȷο� ��û)
     */
    int insertFollow(FollowVO followVO);
    
    /**
     * �ȷο� ���踦 DB���� �����մϴ�. (���ȷο� ��û)
     */
    int deleteFollow(FollowVO followVO);

    /**
     * Ư�� �ȷο� ������ ���¸� Ȯ���մϴ�. (�ȷο� ������ ����)
     */
    int countFollowStatus(FollowVO followVO);

    /**
     * Ư�� ������� �ȷο� ���(���� �ȷο� �ϴ� ���)�� ��ȸ�մϴ�.
     * UserVO ������ �����Ͽ� ��ȯ�ϵ��� ����˴ϴ�.
     */
    List<UserVO> getFollowers(String followedId);

    /**
     * Ư�� ������� �ȷ��� ���(���� �ȷο� �ϴ� ���)�� ��ȸ�մϴ�.
     * UserVO ������ �����Ͽ� ��ȯ�ϵ��� ����˴ϴ�.
     */
    List<UserVO> getFollowings(String followingId);
}
