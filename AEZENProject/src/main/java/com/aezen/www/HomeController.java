package com.aezen.www;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value = "/home.do", method = RequestMethod.GET)
	public String home() {

		

		return "main/home";
	}
	
	@RequestMapping(value = "/write.do", method = RequestMethod.GET)
	public String write(Locale locale, Model model) {

		
		return "main/write";
	}
	
	@RequestMapping(value = "/info.do", method = RequestMethod.GET)
	public String info(Locale locale, Model model) {
		
		
		return "main/info";
	}
	
	@RequestMapping(value = "/pointstore.do", method = RequestMethod.GET)
	public String pointstore(Locale locale, Model model) {
		
		
		return "main/pointstore"; 
	}
	
	@RequestMapping(value = "/minigame.do", method = RequestMethod.GET)
	public String minigame(Locale locale, Model model) {
		
		
		return "main/minigame";
	}
	
	@RequestMapping(value = "/mypage.do", method = RequestMethod.GET)
	public String mypage(Locale locale, Model model) {
		
		
		return "main/mypage";
	}
	
	@RequestMapping(value = "/join_agree.do", method = RequestMethod.GET)
	public String join_agree(Locale locale, Model model) {
		
		
		return "account/join_agree";
	}
	
	@RequestMapping(value = "/join_find.do", method = RequestMethod.GET)
	public String join_find(Locale locale, Model model) {
		
		
		return "account/join_find";
	}
	
	@RequestMapping(value = "/join_info.do", method = RequestMethod.GET)
	public String join_info(Locale locale, Model model) {
		
		
		return "account/join_info";
	}
}
