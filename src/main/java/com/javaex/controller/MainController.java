package com.javaex.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

	/*
	@RequestMapping(value="/test", method= {RequestMethod.GET, RequestMethod.POST})
	public String test() {
		System.out.println("test page");
		return "/WEB-INF/test.jsp";
	}
	*/
	
	
	@RequestMapping(value="/main", method= {RequestMethod.GET, RequestMethod.POST})
	public String main() {
		System.out.println("main page print");
		/*return "/WEB-INF/views/main/index.jsp";*/
		/* spring-servlet.xml 의 소스 줄이기 */
		return "/main/index";
	}
	
	
}
