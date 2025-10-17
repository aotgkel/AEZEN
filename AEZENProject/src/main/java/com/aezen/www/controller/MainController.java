package com.aezen.www.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aezen.www.repository.*;
import com.aezen.www.vo.*;

@Controller
public class MainController {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private BoardRepository boardRepository;

	// 공지사항 제목, 게시글 상세정보 불러오기
	@RequestMapping("/home")
	public String home(Model model) {
		String latestNoticeTitle = boardRepository.selectLatestNoticeTitle();
		List<BoardVO> boardList = boardRepository.selectBoardList();
		
		model.addAttribute("latestNoticeTitle", latestNoticeTitle);
		model.addAttribute("boardList", boardList);
		return "main/home";
	}

	// 실시간 공지 API
	@GetMapping(value = "/latestNotice", produces = "text/plain; charset=UTF-8")
	@ResponseBody
	public String latestNotice() {
	    return boardRepository.selectLatestNoticeTitle();
	}

}
