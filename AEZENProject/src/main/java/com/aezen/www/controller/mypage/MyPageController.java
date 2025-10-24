package com.aezen.www.controller.mypage;

import java.util.List;
import java.util.Locale;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.aezen.www.repository.UserRepository;
import com.aezen.www.repository.BoardRepository;
import com.aezen.www.repository.MyBoardRepository;
import com.aezen.www.vo.BoardVO;
import com.aezen.www.vo.UserVO;

@Controller
public class MyPageController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BoardRepository boardRepository; // 공지, 전체 리스트용

    @Autowired
    private MyBoardRepository myBoardRepository; // ✅ 내 글 전용

    /** ✅ 마이페이지 메인 */
    @RequestMapping(value = "/mypage", method = RequestMethod.GET)
    public String mypage(
            @RequestParam(value = "panel", required = false, defaultValue = "main") String panel,
            @RequestParam(required = false, defaultValue = "0") int category,
	        @RequestParam(required = false, defaultValue = "latest") String sort,
            Locale locale, Model model, HttpSession session) {

        Object loginObject = session.getAttribute("login");
        if (loginObject == null || !(loginObject instanceof UserVO)) {
            return "redirect:/home";
        }

        UserVO loginUser = (UserVO) loginObject;
        model.addAttribute("loginUser", loginUser);

        // 공통 데이터
        String latestNoticeTitle = boardRepository.selectLatestNoticeTitle();
        model.addAttribute("latestNoticeTitle", latestNoticeTitle);

        // ✅ 탭에 따라 데이터 분기
    	List<BoardVO> boardList = boardRepository.selectBoardByCategory(category, sort, loginUser.getId());
        model.addAttribute("boardList", boardList);
        model.addAttribute("activeTab", "myposts");
        return "main/mypage";
    }

    /** ✅ 내가 쓴 글 보기 */
    @RequestMapping(value = "/mypage/posts", method = RequestMethod.GET)
    public String myPosts(Model model, HttpSession session) {
        Object loginObject = session.getAttribute("login");
        if (loginObject == null || !(loginObject instanceof UserVO)) {
            return "redirect:/home";
        }

        UserVO loginUser = (UserVO) loginObject;

        // ✅ 내 글만 조회 (MyBoardRepository 사용)
        List<BoardVO> myPosts = myBoardRepository.selectMyBoardListByUserId(loginUser.getId());

        // ✅ 서버 콘솔 확인용
        System.out.println("✅ 로그인 유저 ID: " + loginUser.getId());
        System.out.println("✅ 내 글 수: " + myPosts.size());

        model.addAttribute("boardList", myPosts);
        model.addAttribute("loginUser", loginUser);
        model.addAttribute("activeTab", "myposts"); // ✅ JSP include 분기용

        System.out.println("✅ 로그인 유저 ID: " + loginUser.getId());
       
        System.out.println("✅ 내 글 수: " + (myPosts == null ? "NULL" : myPosts.size()));
        return "main/mypage"; // 동일 JSP에서 탭 구조 유지
        
    }
    
}
