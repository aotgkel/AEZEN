package com.aezen.www.controller.mypage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.aezen.www.repository.MessageRepository;
import com.aezen.www.vo.InvitationVO;
import com.aezen.www.vo.MessageRoomVO;
import com.aezen.www.vo.MessageVO;
import com.aezen.www.vo.UserVO;

@RestController
@RequestMapping("/message")
public class MessageController {

    @Autowired
    private MessageRepository messageRepository;

    // 📬 받은 메시지 목록
    @GetMapping("/received")
    public List<MessageVO> getReceivedMessages(HttpSession session) {
    	
    	Object loginObject = session.getAttribute("login");

        // 2. 로그인 정보가 null인 경우 (비로그인 상태)
        if (loginObject == null || !(loginObject instanceof UserVO)) {
        	return List.of();
        }
        UserVO loginUser = (UserVO) loginObject;
        return messageRepository.getReceivedMessages(loginUser.getId());
    }

    // 💬 특정 대화방 메시지 조회
    @GetMapping("/room/{roomNo}")
    public List<MessageVO> getMessagesByRoom(@PathVariable int roomNo) {
        return messageRepository.getMessagesByRoom(roomNo);
    }
    
    @PostMapping("/invitation")
    public Map<String, Object> invitation(@RequestBody InvitationVO vo) {
    	
    	Map<String, Object> params = new HashMap<>();
        params.put("userIdA", vo.getSenderId());
        params.put("userIdB", vo.getReceiverId());
    	MessageRoomVO room = messageRepository.findRoomByUsers(params);
    	
    	if (room == null) {
            room = new MessageRoomVO();
            room.setUserIdA(vo.getSenderId());
            room.setUserIdB(vo.getReceiverId());
            messageRepository.insertMessageRoom(room);
        }
    	
    	return Map.of("result","success");
    }

    // ✉️ 메시지 전송 (방 자동 생성)
    @PostMapping("/send")
    @ResponseBody
    public Map<String, Object> sendMessage(@RequestBody MessageVO vo) {
        System.out.println("📩 받은 데이터: " + vo); // 디버깅용

        int result = messageRepository.insertMessage(vo);
        messageRepository.updateRoomLastMessageAt(vo.getRoomNo());

        Map<String, Object> res = new HashMap<>();
        res.put("success", result > 0);
        return res;
    }
    
    @DeleteMapping("/{messageNo}")
    public Map<String, Object> deleteMessage(@PathVariable int messageNo) {
        Map<String, Object> res = new HashMap<>();
        int result = messageRepository.deleteMessage(messageNo);
        res.put("success", result > 0);
        return res;
    }
    
}
