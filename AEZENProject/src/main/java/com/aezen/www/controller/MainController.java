package com.aezen.www.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.aezen.www.repository.*;
import com.aezen.www.vo.*;

@Controller
public class MainController {

	private static final String uploadPath = "D:\\jwh\\aezen\\AEZEN\\AEZEN\\AEZENProject\\src\\main\\webapp\\resources\\upload";

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

	// 글쓰기 페이지(GET) – 로그인 체크
	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public String writeForm(HttpServletRequest request,
			@RequestParam(value = "board_no", required = false) Integer boardNo, Model model) {

		UserVO login = (UserVO) request.getSession().getAttribute("login");
		if (login == null) {
			return "redirect:/home"; // 비로그인 시 홈으로
		}

		if (boardNo != null) {
			// 수정 모드: 게시글 불러오기
			BoardVO post = boardRepository.selectBoardByNo(boardNo);

			// 작성자가 로그인한 사용자와 같은지 체크
			if (!login.getId().equals(post.getId())) {
				return "redirect:/home"; // 권한 없으면 홈으로
			}

			model.addAttribute("post", post); // JSP에서 값 채워서 표시
			
			if (post.getTags() != null && !post.getTags().isEmpty()) {
	            StringBuilder sb = new StringBuilder();
	            for (TagVO tag : post.getTags()) {
	                sb.append(tag.getTagName()).append(",");
	            }
	            // 마지막 쉼표 제거
	            String tagString = sb.substring(0, sb.length() - 1);
	            model.addAttribute("tagString", tagString);
	        }	
		}
		return "main/write";
	}

	// 게시글 등록 + 파일업로드
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String Write(BoardVO vo, @RequestParam("attach") MultipartFile file, HttpServletRequest request)
			throws IllegalStateException, IOException {
		// 로그인 정보를 조회한다.
		UserVO login = (UserVO) request.getSession().getAttribute("login");
		if (login == null) {
			return "redirect:/home";
		}
		
		System.out.println(vo);

		// 게시글 작성자 아이디를 설정한다.
		vo.setId(login.getId());

		if (vo.getBoardNo() != 0) {
			// 수정 모드
			BoardVO existing = boardRepository.selectBoardByNo(vo.getBoardNo());
			if (!login.getId().equals(existing.getId())) {
				return "redirect:/home"; // 작성자가 아니면 접근 거부
			}
			boardRepository.updateBoard(vo);
		} else {
			// 새 글 작성
			boardRepository.insertBoard(vo);
		}

		if (file != null && !file.isEmpty()) {
			File uploadDir = new File(uploadPath); // 클래스 상수 사용
			if (!uploadDir.exists())
				uploadDir.mkdirs();

			String originalFileName = file.getOriginalFilename();
			String ext = "";
			int dotIndex = originalFileName.lastIndexOf(".");
			if (dotIndex > 0)
				ext = originalFileName.substring(dotIndex + 1);

			UUID uuid = UUID.randomUUID();
			String savedFileName = uuid.toString() + "." + ext;

			File newFile = new File(uploadDir, savedFileName);
			file.transferTo(newFile);

			FileVO fvo = new FileVO();
			fvo.setLogicalFileName(originalFileName);
			fvo.setPhysicalFileName(savedFileName);
			fvo.setFileExt(ext);
			fvo.setFileSize(file.getSize());
			fvo.setBoardNo(vo.getBoardNo());

			if (vo.getFiles() == null)
				vo.setFiles(new ArrayList<>());
			vo.getFiles().add(fvo);

			boardRepository.insertFile(fvo);
		}

		// 4. 태그 처리
		String tagInput = request.getParameter("tagName"); // 쉼표로 구분된 태그
		if (tagInput != null && !tagInput.isEmpty()) {
			String[] tags = tagInput.split(",");
			for (String t : tags) {
				t = t.trim();
				if (t.isEmpty())
					continue;

				TagVO tagVO = boardRepository.selectTagByName(t);
				if (tagVO == null) {
				    try {
				        tagVO = new TagVO();
				        tagVO.setTagName(t);
				        boardRepository.insertTag(tagVO);
				    } catch (DuplicateKeyException e) {
				        // 다른 세션에서 이미 insert됐을 경우 처리
				        tagVO = boardRepository.selectTagByName(t);
				    }
				}

				// 게시글-태그 연결 시도
				// 기존에 연결된 태그인지 확인 후 연결
				if (tagVO != null && tagVO.getTagNo() != null) {
				    if (!boardRepository.isTagBoardExists(vo.getBoardNo(), tagVO.getTagNo())) {
				        TagBoardVO tb = new TagBoardVO();
				        tb.setBoardNo(vo.getBoardNo());
				        tb.setTagNo(tagVO.getTagNo());
				        boardRepository.insertTagBoard(tb);
				    }
				}
			}
		}
		return "redirect:/home";
	}

	// 첨부파일 다운로드
	@RequestMapping(value = "/download", method = RequestMethod.GET)
	public void downloadFile(@RequestParam("no") int fileNo, HttpServletResponse response) throws IOException {
		//  File 정보 조회
		FileVO fileVO = boardRepository.selectFileByNo(fileNo); // Repository에서 파일 조회
		if (fileVO == null) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}

		//  실제 파일 경로
		String fullFileName = fileVO.getPhysicalFileName();

		File file = new File(uploadPath, fullFileName); // 올바른 변수(fullFileName) 사용
		if (!file.exists()) {
			// 이제 이 404가 발생하면 물리적 파일이 정말 없는 것입니다.
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}

		// Controller.java (downloadFile 메서드 내)
		// 수정된 부분:
		String originalFileName = fileVO.getLogicalFileName();
		String encodedFileName = java.net.URLEncoder.encode(originalFileName, "UTF-8").replaceAll("\\+", "%20");

		response.setContentType("application/octet-stream");
		response.setContentLength((int) file.length());
		// Content-Disposition 설정 (따옴표 없이 깔끔하게 처리)
		response.setHeader("Content-Disposition", "attachment; filename=" + encodedFileName);
		response.setHeader("Pragma", "no-cache"); // 캐싱 방지 추가 (권장)
		response.setHeader("Expires", "0");

		// 스트림으로 파일 전송
		try (FileInputStream fis = new FileInputStream(file); OutputStream os = response.getOutputStream()) {
			byte[] buffer = new byte[1024];
			int length;
			while ((length = fis.read(buffer)) > 0) {
				os.write(buffer, 0, length);
			}
			os.flush();
		}
	}
	
	//게시글 삭제
	@PostMapping("/deleteBoard")
	@ResponseBody
	public String deleteBoard(@RequestParam("boardNo") int boardNo, HttpServletRequest request) {
	    UserVO login = (UserVO) request.getSession().getAttribute("login");
	    if (login == null) {
	        return "NOT_LOGIN";
	    }

	    BoardVO post = boardRepository.selectBoardByNo(boardNo);
	    if (post == null || !login.getId().equals(post.getId())) {
	        return "NO_PERMISSION";
	    }

	    boardRepository.deleteBoard(boardNo);
	    return "SUCCESS";
	}
	
	// AJAX로 fragment만 반환 (카테고리 필터용)
	@GetMapping("/home/posts")
	public String getPostsByCategoryAndSort(
	        @RequestParam(required = false, defaultValue = "0") int category,
	        @RequestParam(required = false, defaultValue = "latest") String sort,
	        Model model) {
		
		//1. 현재페이지
		//2. 전체 게시글 개수

		
	    List<BoardVO> boardList = boardRepository.selectBoardByCategory(category, sort);
	    model.addAttribute("boardList", boardList);
	    return "include/posts";
	}
}
