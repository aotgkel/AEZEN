package com.aezen.www.repository;

import com.aezen.www.vo.ChatVO;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;

@Repository
public class ChatRepositroy {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void save(ChatVO chat) {
        String sql = "INSERT INTO CHAT (id, chat_content, chat_created_at) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, chat.getId(), chat.getChatContent(), chat.getChatCreatedAt());
    }
}
