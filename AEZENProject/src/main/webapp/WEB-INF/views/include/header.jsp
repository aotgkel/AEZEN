<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="ko">
<head>
  <meta charset="utf-8">
  <title>AEZEN 일반인 EZEN 개발자</title>
  <link rel="stylesheet" href="resources/css/home.css">
  <script src="resources/js/home.js"></script>
</head>
  <!---------------- 헤더영역 ---------------------->
  <!-- 로고영역 + 로그인박스 + 아이콘 -->
  <header>
    <!-- 로고영역 -->
    <div class="logo-area">
      <div class="logo">
        <a href="home">
        <img src="resources/img/ezen_logo.jpg" style="width: 80px; height: 80px;">
        </a>
      </div>
      <div class="site-title">
        <a href="home" class="site-link">
          <span class="part1">AEZEN&nbsp;일반인</span> &nbsp;&nbsp;
          <span class="part2">EZEN&nbsp;개발자</span>
        </a>
      </div>
    </div>
    <!-- 로그인박스 (초기 보임) -->
    <div class="login-box">
      <input type="text" id="loginId" placeholder="ID 입력">
      <input type="password" id="loginPw" placeholder="PW 입력">
      <div class="login-options">
        <label><input type="checkbox" id="rememberMe"> ID 기억하기</label>
        <a href="join_find">ID/PW 찾기</a>
        <a href="join_agree">회원가입</a>
      </div>
      <button id="loginBtn">로그인</button>
    </div>
    <!-- 아이콘 (초기 숨김) -->
    <div class="icons" style="display:none;">
      <button id="btnMyPage" data-tooltip="마이페이지">👨</button>
      <button id="btnNotify" data-tooltip="알림">🔔</button>
      <button id="btnMsg" data-tooltip="메세지">📬</button>
      <button id="btnTrophy" data-tooltip="포인트">🏆</button>
      <button id="btnLogout" data-tooltip="로그아웃">🔑</button>
    </div>
  </header>