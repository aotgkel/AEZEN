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
            session.close(); // ���� ����
            return;
        }
    	
    	// �α��� �� ��츸 sessions�� �߰�
        sessions.add(session);
        broadcastUserCount();             // ���⼭ ������ �� ��ε�ĳ��Ʈ
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        ChatVO chat = mapper.readValue(message.getPayload(), ChatVO.class);

        // ��¥ ��ȯ: ISO 8601 �� LocalDateTime
        String isoDate = chat.getChatCreatedAt(); // "2025-10-22T09:04:56.820Z"
        LocalDateTime kstDate = OffsetDateTime.parse(isoDate)
                .atZoneSameInstant(java.time.ZoneOffset.UTC)
                .withZoneSameInstant(java.time.ZoneId.of("Asia/Seoul"))
                .toLocalDateTime();
        chat.setChatCreatedAt(kstDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        HttpSession httpSession = (HttpSession) session.getAttributes().get("HTTP_SESSION");

        if (httpSession != null) {
            UserVO loginUser = (UserVO) httpSession.getAttribute("login"); // ���ǿ� ����� UserVO

            if (loginUser != null) {
                chat.setId(loginUser.getId()); // UserVO���� id ��������
            } else {
                return; // DB ���� ����
            }
        } else {
            return;
        }

        // DB ����
        chatRepository.save(chat);

        // ��ε�ĳ��Ʈ
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
        int count = sessions.size(); // ���� ������ ��
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
        broadcastUserCount();             // ������ �� ���� ��ε�ĳ��Ʈ
    }
}
