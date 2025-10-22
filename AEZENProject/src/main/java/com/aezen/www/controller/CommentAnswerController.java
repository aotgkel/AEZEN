package com.aezen.www.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.aezen.www.repository.*;
import com.aezen.www.vo.*;

@Controller
//@RestController - @ResponseBody 안써도 됨.
public class CommentAnswerController {

	@Autowired
    private BoardRepository boardRepository;
	@Autowired
    private CommentAnswerRepository repository;

    // 댓글 작성
    @PostMapping("/addComment")
    //@DeleteMapping
    //@PatchMapping
    @ResponseBody
    public Map<String, Object> addComment(@RequestParam int boardNo,
                                          @RequestParam String content,
                                          HttpSession session) {
        Map<String, Object> result = new HashMap<>();
        UserVO login = (UserVO) session.getAttribute("login");
        if (login == null) {
            result.put("success", false);
            result.put("message", "로그인이 필요합니다.");
            return result;
        }

        CommentAnswerVO comment = new CommentAnswerVO();
        comment.setBoardNo(boardNo);
        comment.setId(login.getId());
        comment.setCommentAnswerContent(content);
        comment.setCommentAnswerType(1);

        repository.insertComment(comment);
        
        result.put("success", true);
        result.put("id", comment.getCommentAnswerNo());
        result.put("author", login.getNick());
        return result;
    }

    // 답변 작성
    @PostMapping("/addAnswer")
    @ResponseBody
    public Map<String, Object> addAnswer(@RequestParam int boardNo,
                                         @RequestParam String content,
                                         HttpSession session) {
        Map<String, Object> result = new HashMap<>();
        UserVO login = (UserVO) session.getAttribute("login");
        if (login == null) {
            result.put("success", false);
            result.put("message", "로그인이 필요합니다.");
            return result;
        }

        CommentAnswerVO answer = new CommentAnswerVO();
        answer.setBoardNo(boardNo);
        answer.setId(login.getId());
        answer.setCommentAnswerContent(content);
        answer.setCommentAnswerType(2);

        repository.insertAnswer(answer);

        result.put("success", true);
        result.put("id", answer.getCommentAnswerNo());
        result.put("author", login.getNick());
        return result;
    }
    
    // 수정
    @PostMapping("/update")
    @ResponseBody
    public Map<String, Object> update(@RequestParam int id,
                                      @RequestParam String content,
                                      HttpSession session) {
        Map<String, Object> result = new HashMap<>();
        UserVO login = (UserVO) session.getAttribute("login");
        if (login == null) {
            result.put("success", false);
            result.put("message", "로그인이 필요합니다.");
            return result;
        }

        
        CommentAnswerVO item = repository.selectById(id);
        if (item == null || !login.getId().equals(item.getId())) {
            result.put("success", false);
            result.put("message", "수정 권한이 없습니다.");
            return result;
        }

        item.setCommentAnswerContent(content);
        
        System.out.println("DEBUG update: id=" + item.getCommentAnswerNo() + ", content=" + content);

        
        repository.update(item);
        result.put("success", true);
        return result;
    }

    // 삭제
    @PostMapping("/delete")
    @ResponseBody
    public Map<String, Object> delete(@RequestParam int id, HttpSession session) {
        Map<String, Object> result = new HashMap<>();
        UserVO login = (UserVO) session.getAttribute("login");
        if (login == null) {
            result.put("success", false);
            result.put("message", "로그인이 필요합니다.");
            return result;
        }

        CommentAnswerVO item = repository.selectById(id);
        if (item == null || !login.getId().equals(item.getId())) {
            result.put("success", false);
            result.put("message", "삭제 권한이 없습니다.");
            return result;
        }

        repository.delete(id);
        result.put("success", true);
        return result;
    }
    
    //게시글, 댓글 추천/비추천 기능
    @PostMapping("/vote")
    @ResponseBody
    public Map<String, Object> vote(
            @RequestParam String type,      // post / comment
            @RequestParam String action,    // like / dislike
            @RequestParam int id,           // boardNo 또는 commentNo
            HttpSession session) {
    	
    	Map<String,Object> result = new HashMap<>();

    	// 세션에서 UserVO 가져오기
        UserVO user = (UserVO) session.getAttribute("login");
        if(user == null) {
            result.put("success", false);
            result.put("message", "로그인이 필요합니다.");
            return result;
        }

        String userId = user.getId();
        int count = 0;

        try {
            if("post".equals(type)) {
                boardRepository.updateBoardVote(id, action);              // 추천/비추천 DB 반영
                count = boardRepository.selectBoardVoteCount(id, action); // 최신 count 조회
            } else if("comment".equals(type)) {
            	repository.updateCommentVote(id, action);
                count = repository.selectCommentVoteCount(id, action);
            }

            result.put("success", true);
            result.put("count", count);

        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "서버 요청 중 오류가 발생했습니다.");
            e.printStackTrace();
        }

        return result;
    }

}
