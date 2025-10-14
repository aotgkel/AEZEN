package com.aezen.www.vo;

import java.time.LocalDateTime;

/*
 * 클래스 기능 : 사용자 정보 (user 테이블)
 */
public class UserVO {

    private String id; 		 // 아이디 (Primary Key)
    private String name; 	 // 이름
    private String nick; 	 // 닉네임 (Unique)
    private String email; 	 // 이메일 (Unique)
    private String password; // 비밀번호

    private LocalDateTime createdAt;   // 가입일시
    private LocalDateTime updatedAt;   // 수정일시
    private LocalDateTime lastLoginAt; // 마지막 로그인

    private int userType; 		   // 사용자 타입 (0: 일반, 1: 관리자 등)
    private String icon; 		   // 유저 아이콘 경로
    private String backgroundImg;  // 배경 이미지 경로
    private String insignia;       // 휘장/칭호

    private LocalDateTime withdrawAt; // 탈퇴일시
    private boolean withdraw;         // 탈퇴여부 (boolean 타입, true: 탈퇴 상태)
    private int userCurrentPoint;     // 현재 잔여 포인트

    // =============================
    // Getters
    // =============================

    public String getId()                 {return id;}
    public String getName()               {return name;}
    public String getNick()               {return nick;}
    public String getEmail()              {return email;}
    public String getPassword()           {return password;}
    public LocalDateTime getCreatedAt()   {return createdAt;}
    public LocalDateTime getUpdatedAt()   {return updatedAt;}
    public LocalDateTime getLastLoginAt() {return lastLoginAt;}
    public int getUserType()              {return userType;}
    public String getIcon()               {return icon;}
    public String getBackgroundImg()      {return backgroundImg;}
    public String getInsignia()           {return insignia;}
    public LocalDateTime getWithdrawAt()  {return withdrawAt;}
    public boolean isWithdraw()           {return withdraw;}
    public int getUserCurrentPoint()      {return userCurrentPoint;}
    // =============================
    // Setters
    // =============================

    public void setId(String id)                           {this.id = id;}
    public void setName(String name)                       {this.name = name;}
    public void setNick(String nick)                       {this.nick = nick;}
    public void setEmail(String email)                     {this.email = email;}
    public void setPassword(String password)               {this.password = password;}
    public void setCreatedAt(LocalDateTime createdAt)      {this.createdAt = createdAt;}
    public void setUpdatedAt(LocalDateTime updatedAt)      {this.updatedAt = updatedAt;}
    public void setLastLoginAt(LocalDateTime lastLoginAt)  {this.lastLoginAt = lastLoginAt;}
    public void setUserType(int userType)                  {this.userType = userType;}
    public void setIcon(String icon)                       {this.icon = icon;}
    public void setBackgroundImg(String backgroundImg)     {this.backgroundImg = backgroundImg;}
    public void setInsignia(String insignia)               {this.insignia = insignia;}
    public void setWithdrawAt(LocalDateTime withdrawAt)    {this.withdrawAt = withdrawAt;}
    public void setWithdraw(boolean withdraw)              {this.withdraw = withdraw;}
    public void setUserCurrentPoint(int userCurrentPoint)  {this.userCurrentPoint = userCurrentPoint;}
}
