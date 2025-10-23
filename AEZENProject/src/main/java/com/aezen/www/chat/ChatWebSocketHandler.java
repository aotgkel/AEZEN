package com.aezen.www.chat;

import com.aezen.www.vo.ChatVO;
import com.aezen.www.vo.UserVO;
import com.aezen.www.repository.ChatRepositroy;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.springframework.web.socket.CloseStatus;

import javax.servlet.http.HttpSession;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Component
public class ChatWebSocketHandler extends TextWebSocketHandler {

    private static final Set<WebSocketSession> sessions = Collections.synchronizedSet(new HashSet<>());

    @Autowired
    private ChatRepositroy chatRepository;

    private ObjectMapper mapper = new ObjectMapper();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
    	HttpSession httpSession = (HttpSession) session.getAttributes().get("HTTP_SESSION");
    	
    	if (httpSession == null || httpSession.getAttribute("login") == null) {
            session.close(); // 연결 종료
            return;
        }
    	
    	// 로그인 된 경우만 sessions에 추가
        sessions.add(session);
        broadcastUserCount();             // 여기서 접속자 수 브로드캐스트
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        ChatVO chat = mapper.readValue(message.getPayload(), ChatVO.class);

        // 날짜 변환: ISO 8601 → LocalDateTime
        String isoDate = chat.getChatCreatedAt(); // "2025-10-22T09:04:56.820Z"
        LocalDateTime kstDate = OffsetDateTime.parse(isoDate)
                .atZoneSameInstant(java.time.ZoneOffset.UTC)
                .withZoneSameInstant(java.time.ZoneId.of("Asia/Seoul"))
                .toLocalDateTime();
        chat.setChatCreatedAt(kstDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        HttpSession httpSession = (HttpSession) session.getAttributes().get("HTTP_SESSION");

        if (httpSession != null) {
            UserVO loginUser = (UserVO) httpSession.getAttribute("login"); // 세션에 저장된 UserVO

            if (loginUser != null) {
                chat.setId(loginUser.getId()); // UserVO에서 id 가져오기
            } else {
                return; // DB 저장 방지
            }
        } else {
            return;
        }

        // DB 저장
        chatRepository.save(chat);

        // 브로드캐스트
        String json = mapper.writeValueAsString(chat);
        synchronized (sessions) {
            for (WebSocketSession s : sessions) {
                if (s.isOpen()) {
                    s.sendMessage(new TextMessage(json));
                }
            }
        }
    }
    
    private void broadcastUserCount() {
        int count = sessions.size(); // 현재 접속자 수
        String json = "{\"userCount\": " + count + "}";
        synchronized (sessions) {
            for (WebSocketSession s : sessions) {
                if (s.isOpen()) {
                    try {
                        s.sendMessage(new TextMessage(json));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessions.remove(session);
        broadcastUserCount();             // 접속자 수 갱신 브로드캐스트
    }
}
