package com.aezen.www.vo;

import java.time.LocalDateTime;

/*
 * 클래스 기능 : 차단 정보 (block 테이블)
 */
public class BlockVO {
    private String blockerId; // 차단을 요청한 사용자 ID (Primary Key 일부)
    private String blockedId; // 차단을 당한 사용자 ID (Primary Key 일부)
    private LocalDateTime blockedAt; // 차단 시작 일시

    // =============================
    // Getters
    // =============================

    public String getBlockerId() {
        return blockerId;
    }

    public String getBlockedId() {
        return blockedId;
    }

    public LocalDateTime getBlockedAt() {
        return blockedAt;
    }

    // =============================
    // Setters
    // =============================

    public void setBlockerId(String blockerId) {
        this.blockerId = blockerId;
    }

    public void setBlockedId(String blockedId) {
        this.blockedId = blockedId;
    }

    public void setBlockedAt(LocalDateTime blockedAt) {
        this.blockedAt = blockedAt;
    }
}