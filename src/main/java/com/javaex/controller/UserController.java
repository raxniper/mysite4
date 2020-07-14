package com.javaex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.javaex.service.UserService;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;

	// 회원가입 폼
	@RequestMapping("/joinForm")
	public String joinForm() {
		System.out.println("/user/joinForm()");
		return "user/joinForm";
	}
	
	// 회원가입
	@RequestMapping("/join")
	public String join(@ModelAttribute UserVo userVo) {
		System.out.println("/user/join()");
		System.out.println(userVo.toString());
		/* UserService userService = new UserService(); */
		int count = userService.join(userVo);
		/*return "redirect:/main";*/
		return "user/joinOk";
	}
	
	// 로그인 폼
	@RequestMapping("/loginForm")
	public String loginForm() {
		System.out.println("/user/loginForm()");
		return "user/loginForm";
	}
	
	// 로그인
	@RequestMapping("/login")
	public String login(@ModelAttribute UserVo userVo, HttpSession session) {
		
		//System.out.println("/user/login");
		//System.out.println(userVo.toString());
		
		UserVo authUser = userService.login(userVo);
		//System.out.println(authUser.toString());
		
		if(authUser != null) {	// 로그인 성공일 때
			System.out.println("/user/login-seccess");
			session.setAttribute("authUser", authUser);
			return "redirect:/main";		// 메인페이지로 리다이렉트
		}else{  // 로그인 실패일 때
			System.out.println("/user/login-fail");
			return "redirect:/user/loginForm?result=fail";	// 다시 로그인폼을 요청하라
		}
	}
	
	// 로그아웃
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		System.out.println("/user/logout");
		session.removeAttribute("authUser");// 세션 "authUser"값 지우기
		session.invalidate();// 세션값지우기2 세트로사용
		return "redirect:/main";
	}
	
	// 회원정보 수정폼
	@RequestMapping("/modifyForm")
	public String modifyForm(Model model, HttpSession session) {
		System.out.println("/user/modifyForm");
		int no = ((UserVo)session.getAttribute("authUser")).getNo();
		UserVo vo = userService.modifyForm(no);
		model.addAttribute("userVo", vo);
		return "user/modifyForm";
	}
	
	// 회원정보 수정
	@RequestMapping("/modify")
	public String modify(@ModelAttribute UserVo userVo, HttpSession session) {
		System.out.println("/user/modify");
		UserVo vo = (UserVo)session.getAttribute("authUser");
		userVo.setNo(vo.getNo());
		userService.modify(userVo);
		vo.setName(userVo.getName());
		return "redirect:/main";
	}
	
}
