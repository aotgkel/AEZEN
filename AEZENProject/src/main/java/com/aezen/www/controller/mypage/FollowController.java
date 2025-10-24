package com.aezen.www.controller.mypage;

import com.aezen.www.repository.FollowRepository;
import com.aezen.www.vo.FollowVO;
import com.aezen.www.vo.UserVO; // UserVO import �߰�
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional; // ��� �� Ʈ����� ó���� ���� �߰�
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession; // ���ǿ��� �α��� ����� ID�� �������� ���� �߰�
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * �ȷο� ����� ���� REST API Controller
 * URL: /api/follow
 */
@RestController
@RequestMapping("/api/follow")
public class FollowController {

    private final FollowRepository followRepository;

    @Autowired
    public FollowController(FollowRepository followRepository) {
        this.followRepository = followRepository;
    }

    /**
     * Ư�� ������� �ȷ��� ����� ��ȸ�մϴ�. (���� �ȷο��ϴ� �����)
     * GET /api/follow/followings/{userId}
     * ��ȯ Ÿ��: List<UserVO> (������ ������ �ٷ� ǥ���ϱ� ����)
     */
    @GetMapping("/followings")
    public ResponseEntity<List<UserVO>> getFollowings(HttpSession session) {
    	if(session.getAttribute("user") == null) {
    		
    	}
    	
    	UserVO user = (UserVO)session.getAttribute("login");
    	List<UserVO> followings = null;
        try {
            // FollowRepository�� �̹� UserVO�� ��ȯ�ϵ��� �����Ǿ���
            followings = followRepository.getFollowings(user.getId()); 
            return new ResponseEntity<>(followings, HttpStatus.OK);
        } catch (Exception e) {
            System.err.println("�ȷ��� ��� ��ȸ ����: " + e.getMessage());
            // ��ȯ Ÿ���� ���߱� ���� UserVO ����Ʈ�� ��ȯ
            return new ResponseEntity<>(followings, HttpStatus.INTERNAL_SERVER_ERROR); 
        }
    }

    /**
     * Ư�� ������� �ȷο� ����� ��ȸ�մϴ�. (���� �ȷο��ϴ� �����)
     * GET /api/follow/followers/{userId}
     * ��ȯ Ÿ��: List<UserVO> (������ ������ �ٷ� ǥ���ϱ� ����)
     */
    @GetMapping("/followers")
    public ResponseEntity<List<UserVO>> getFollowers(HttpSession session) {
    	if(session.getAttribute("user") == null) {
    		
    	}
    	
    	UserVO user = (UserVO)session.getAttribute("login");
    	List<UserVO> followers = null;
        try {
            // FollowRepository�� �̹� UserVO�� ��ȯ�ϵ��� �����Ǿ���
            followers = followRepository.getFollowers(user.getId());
            return new ResponseEntity<>(followers, HttpStatus.OK);
        } catch (Exception e) {
            System.err.println("�ȷο� ��� ��ȸ ����: " + e.getMessage());
            // ��ȯ Ÿ���� ���߱� ���� UserVO ����Ʈ�� ��ȯ
            return new ResponseEntity<>(followers, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * �ȷο� ���¸� ���(Follow/Unfollow) ó���մϴ�.
     * POST /api/follow/toggle
     * @param requestBody { targetId: String (���� ID) }
     * @param session �α��� ����� ���� ����
     */
    @Transactional
    @PostMapping("/toggle")
    public ResponseEntity<Map<String, Object>> toggleFollow(@RequestBody Map<String, String> requestBody, HttpSession session) {
        Map<String, Object> response = new HashMap<>();
        UserVO loginUser = (UserVO) session.getAttribute("login");
        
        // 1. �α��� Ȯ��
        if (loginUser == null) {
            response.put("error", "�α����� �ʿ��մϴ�.");
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }

        String initiatorId = loginUser.getId(); // �� (�ȷο츦 �Ŵ� ���: followingId)
        String targetId = requestBody.get("targetId"); // ���� (�ȷο� ���ϴ� ���: followedId)

        // 2. ��ȿ�� �� �ڱ� �ڽ� �ȷο� ����
        if (targetId == null || targetId.isEmpty() || initiatorId.equals(targetId)) {
            response.put("error", "��ȿ���� ���� ��û�Դϴ�.");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        
        FollowVO vo = new FollowVO();
        vo.setFollowingId(initiatorId); 
        vo.setFollowedId(targetId);    

        try {
            // 3. ���� Ȯ�� �� ��� ó��
            int status = followRepository.countFollowStatus(vo);
            String actionStatus;

            if (status > 0) {
                // ���ȷο�
                followRepository.deleteFollow(vo);
                actionStatus = "unfollowed";
            } else {
                // �ȷο�
                followRepository.insertFollow(vo);
                actionStatus = "followed";
            }
            
            response.put("success", true);
            response.put("status", actionStatus);
            response.put("targetId", targetId);
            response.put("message", actionStatus.equals("followed") ? "�ȷο� ó�� �Ϸ�." : "���ȷο� ó�� �Ϸ�.");

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            System.err.println("�ȷο� ��� ����: " + e.getMessage());
            response.put("success", false);
            response.put("message", "���� ������ ó���� �����߽��ϴ�.");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
