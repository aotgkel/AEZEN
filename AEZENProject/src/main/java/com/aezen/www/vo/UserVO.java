package com.aezen.www.vo;

import java.time.LocalDateTime;

/*
 * Ŭ���� ��� : ����� ���� (user ���̺�)
 */
public class UserVO {

    private String id; 		 // ���̵� (Primary Key)
    private String name; 	 // �̸�
    private String nick; 	 // �г��� (Unique)
    private String email; 	 // �̸��� (Unique)
    private String password; // ��й�ȣ

    private LocalDateTime createdAt;   // �����Ͻ�
    private LocalDateTime updatedAt;   // �����Ͻ�
    private LocalDateTime lastLoginAt; // ������ �α���

    private int userType; 		   // ����� Ÿ�� (0: �Ϲ�, 1: ������ ��)
    private String icon; 		   // ���� ������ ���
    private String backgroundImg;  // ��� �̹��� ���
    private String insignia;       // ����/Īȣ

    private LocalDateTime withdrawAt; // Ż���Ͻ�
    private boolean withdraw;         // Ż�𿩺� (boolean Ÿ��, true: Ż�� ����)
    private int userCurrentPoint;     // ���� �ܿ� ����Ʈ

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
