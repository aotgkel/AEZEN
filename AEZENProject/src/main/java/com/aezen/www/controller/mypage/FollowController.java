package com.aezen.www.controller.mypage;

import com.aezen.www.repository.FollowRepository;
import com.aezen.www.vo.FollowVO;
import com.aezen.www.vo.UserVO; // UserVO import 추가
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional; // 토글 시 트랜잭션 처리를 위해 추가
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession; // 세션에서 로그인 사용자 ID를 가져오기 위해 추가
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 팔로우 기능을 위한 REST API Controller
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
     * 특정 사용자의 팔로잉 목록을 조회합니다. (내가 팔로우하는 사람들)
     * GET /api/follow/followings/{userId}
     * 반환 타입: List<UserVO> (프로필 정보를 바로 표시하기 위해)
     */
    @GetMapping("/followings")
    public ResponseEntity<List<UserVO>> getFollowings(HttpSession session) {
    	if(session.getAttribute("user") == null) {
    		
    	}
    	
    	UserVO user = (UserVO)session.getAttribute("login");
    	List<UserVO> followings = null;
        try {
            // FollowRepository는 이미 UserVO를 반환하도록 수정되었음
            followings = followRepository.getFollowings(user.getId()); 
            return new ResponseEntity<>(followings, HttpStatus.OK);
        } catch (Exception e) {
            System.err.println("팔로잉 목록 조회 오류: " + e.getMessage());
            // 반환 타입을 맞추기 위해 UserVO 리스트를 반환
            return new ResponseEntity<>(followings, HttpStatus.INTERNAL_SERVER_ERROR); 
        }
    }

    /**
     * 특정 사용자의 팔로워 목록을 조회합니다. (나를 팔로우하는 사람들)
     * GET /api/follow/followers/{userId}
     * 반환 타입: List<UserVO> (프로필 정보를 바로 표시하기 위해)
     */
    @GetMapping("/followers")
    public ResponseEntity<List<UserVO>> getFollowers(HttpSession session) {
    	if(session.getAttribute("user") == null) {
    		
    	}
    	
    	UserVO user = (UserVO)session.getAttribute("login");
    	List<UserVO> followers = null;
        try {
            // FollowRepository는 이미 UserVO를 반환하도록 수정되었음
            followers = followRepository.getFollowers(user.getId());
            return new ResponseEntity<>(followers, HttpStatus.OK);
        } catch (Exception e) {
            System.err.println("팔로워 목록 조회 오류: " + e.getMessage());
            // 반환 타입을 맞추기 위해 UserVO 리스트를 반환
            return new ResponseEntity<>(followers, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 팔로우 상태를 토글(Follow/Unfollow) 처리합니다.
     * POST /api/follow/toggle
     * @param requestBody { targetId: String (상대방 ID) }
     * @param session 로그인 사용자 세션 정보
     */
    @Transactional
    @PostMapping("/toggle")
    public ResponseEntity<Map<String, Object>> toggleFollow(@RequestBody Map<String, String> requestBody, HttpSession session) {
        Map<String, Object> response = new HashMap<>();
        UserVO loginUser = (UserVO) session.getAttribute("login");
        
        // 1. 로그인 확인
        if (loginUser == null) {
            response.put("error", "로그인이 필요합니다.");
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }

        String initiatorId = loginUser.getId(); // 나 (팔로우를 거는 사람: followingId)
        String targetId = requestBody.get("targetId"); // 상대방 (팔로우 당하는 사람: followedId)

        // 2. 유효성 및 자기 자신 팔로우 방지
        if (targetId == null || targetId.isEmpty() || initiatorId.equals(targetId)) {
            response.put("error", "유효하지 않은 요청입니다.");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        
        FollowVO vo = new FollowVO();
        vo.setFollowingId(initiatorId); 
        vo.setFollowedId(targetId);    

        try {
            // 3. 상태 확인 및 토글 처리
            int status = followRepository.countFollowStatus(vo);
            String actionStatus;

            if (status > 0) {
                // 언팔로우
                followRepository.deleteFollow(vo);
                actionStatus = "unfollowed";
            } else {
                // 팔로우
                followRepository.insertFollow(vo);
                actionStatus = "followed";
            }
            
            response.put("success", true);
            response.put("status", actionStatus);
            response.put("targetId", targetId);
            response.put("message", actionStatus.equals("followed") ? "팔로우 처리 완료." : "언팔로우 처리 완료.");

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            System.err.println("팔로우 토글 오류: " + e.getMessage());
            response.put("success", false);
            response.put("message", "서버 오류로 처리에 실패했습니다.");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
