package com.aezen.www.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.aezen.www.vo.MessageRoomVO;
import com.aezen.www.vo.MessageVO;

@Repository
public interface MessageRepository {

    // 📦 MessageRoom 관련
    MessageRoomVO findRoomByUsers(Map<String, Object> params);
    int insertMessageRoom(MessageRoomVO room);
    List<MessageRoomVO> getRoomsByUser(String userId);
    int updateRoomLastMessageAt(int roomNo);

    // 💌 Message 관련
    int insertMessage(MessageVO message);
    List<MessageVO> getMessagesByRoom(int roomNo);
    int deleteMessage(int messageNo);
    List<MessageVO> getReceivedMessages(@Param("receiverId") String receiverId);
    List<MessageVO> getSentMessages(@Param("senderId") String senderId);
}
