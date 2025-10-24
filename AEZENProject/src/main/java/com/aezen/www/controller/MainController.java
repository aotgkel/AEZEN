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

	// 怨듭��ы�� ��紐�, 寃���湲� ���몄��蹂� 遺��ъ�ㅺ린
	@RequestMapping("/home")
	public String home(Model model) {
		String latestNoticeTitle = boardRepository.selectLatestNoticeTitle();
		List<BoardVO> boardList = boardRepository.selectBoardList();

		model.addAttribute("latestNoticeTitle", latestNoticeTitle);
		model.addAttribute("boardList", boardList);
		return "main/home";
	}

	// �ㅼ��媛� 怨듭� API
	@GetMapping(value = "/latestNotice", produces = "text/plain; charset=UTF-8")
	@ResponseBody
	public String latestNotice() {
		return boardRepository.selectLatestNoticeTitle();
	}

	// 湲��곌린 ���댁�(GET) �� 濡�洹몄�� 泥댄��
	@RequestMapping(value = "/write", method = RequestMethod.GET)
	public String writeForm(HttpServletRequest request,
			@RequestParam(value = "board_no", required = false) Integer boardNo, Model model) {

		UserVO login = (UserVO) request.getSession().getAttribute("login");
		if (login == null) {
			return "redirect:/home"; // 鍮�濡�洹몄�� �� ���쇰�
		}

		if (boardNo != null) {
			// ���� 紐⑤��: 寃���湲� 遺��ъ�ㅺ린
			BoardVO post = boardRepository.selectBoardByNo(boardNo);

			// ���깆��媛� 濡�洹몄�명�� �ъ�⑹���� 媛���吏� 泥댄��
			if (!login.getId().equals(post.getId())) {
				return "redirect:/home"; // 沅��� ���쇰㈃ ���쇰�
			}

			model.addAttribute("post", post); // JSP���� 媛� 梨����� ����
			
			if (post.getTags() != null && !post.getTags().isEmpty()) {
	            StringBuilder sb = new StringBuilder();
	            for (TagVO tag : post.getTags()) {
	                sb.append(tag.getTagName()).append(",");
	            }
	            // 留�吏�留� �쇳�� ��嫄�
	            String tagString = sb.substring(0, sb.length() - 1);
	            model.addAttribute("tagString", tagString);
	        }	
		}
		return "main/write";
	}

	// 寃���湲� �깅� + ���쇱��濡���
	@RequestMapping(value = "/write", method = RequestMethod.POST)
	public String Write(BoardVO vo, @RequestParam("attach") MultipartFile file, HttpServletRequest request)
			throws IllegalStateException, IOException {
		// 濡�洹몄�� ��蹂대�� 議고������.
		UserVO login = (UserVO) request.getSession().getAttribute("login");
		if (login == null) {
			return "redirect:/home";
		}
		
		System.out.println(vo);

		// 寃���湲� ���깆�� ���대��瑜� �ㅼ������.
		vo.setId(login.getId());

		if (vo.getBoardNo() != 0) {
			// ���� 紐⑤��
			BoardVO existing = boardRepository.selectBoardByNo(vo.getBoardNo());
			if (!login.getId().equals(existing.getId())) {
				return "redirect:/home"; // ���깆��媛� ����硫� ��洹� 嫄곕�
			}
			boardRepository.updateBoard(vo);
		} else {
			// �� 湲� ����
			boardRepository.insertBoard(vo);
		}

		if (file != null && !file.isEmpty()) {
			File uploadDir = new File(uploadPath); // �대���� ���� �ъ��
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

		// 4. ��洹� 泥�由�
		String tagInput = request.getParameter("tagName"); // �쇳��濡� 援щ��� ��洹�
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
				        // �ㅻⅨ �몄������ �대�� insert���� 寃쎌�� 泥�由�
				        tagVO = boardRepository.selectTagByName(t);
				    }
				}

				// 寃���湲�-��洹� �곌껐 ����
				// 湲곗〈�� �곌껐�� ��洹몄�몄� ���� �� �곌껐
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

	// 泥⑤����� �ㅼ�대���
	@RequestMapping(value = "/download", method = RequestMethod.GET)
	public void downloadFile(@RequestParam("no") int fileNo, HttpServletResponse response) throws IOException {
		//  File ��蹂� 議고��
		FileVO fileVO = boardRepository.selectFileByNo(fileNo); // Repository���� ���� 議고��
		if (fileVO == null) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}

		//  �ㅼ�� ���� 寃쎈�
		String fullFileName = fileVO.getPhysicalFileName();

		File file = new File(uploadPath, fullFileName); // �щ�瑜� 蹂���(fullFileName) �ъ��
		if (!file.exists()) {
			// �댁�� �� 404媛� 諛�����硫� 臾쇰━�� ���쇱�� ��留� ���� 寃�������.
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}

		// Controller.java (downloadFile 硫����� ��)
		// ������ 遺�遺�:
		String originalFileName = fileVO.getLogicalFileName();
		String encodedFileName = java.net.URLEncoder.encode(originalFileName, "UTF-8").replaceAll("\\+", "%20");

		response.setContentType("application/octet-stream");
		response.setContentLength((int) file.length());
		// Content-Disposition �ㅼ�� (�곗�댄�� ���� 源�����寃� 泥�由�)
		response.setHeader("Content-Disposition", "attachment; filename=" + encodedFileName);
		response.setHeader("Pragma", "no-cache"); // 罹��� 諛⑹� 異�媛� (沅���)
		response.setHeader("Expires", "0");

		// �ㅽ�몃┝�쇰� ���� ����
		try (FileInputStream fis = new FileInputStream(file); OutputStream os = response.getOutputStream()) {
			byte[] buffer = new byte[1024];
			int length;
			while ((length = fis.read(buffer)) > 0) {
				os.write(buffer, 0, length);
			}
			os.flush();
		}
	}
	
	//寃���湲� ����
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
	
	// AJAX濡� fragment留� 諛��� (移댄��怨�由� ���곗��)
	@GetMapping("/home/posts")
	public String getPostsByCategoryAndSort(
	        @RequestParam(required = false, defaultValue = "0") int category,
	        @RequestParam(required = false, defaultValue = "latest") String sort,
	        Model model,
	        HttpServletRequest request,
	        HttpSession session) {
		
		//1. ���ы���댁�
		//2. ��泥� 寃���湲� 媛���
		
		String referer = request.getHeader("referer");
		String userId = session.getAttribute("login") == null || referer.contains("home") ? null : ((UserVO)session.getAttribute("login")).getId();

		
	    List<BoardVO> boardList = boardRepository.selectBoardByCategory(category, sort, userId);
	    model.addAttribute("boardList", boardList);
	    return "include/posts";
	}
}
