package com.slam.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.slam.service.ProfileService;
import com.slam.service.UserService;
import com.slam.vo.User;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private ProfileService profileService;

	// 로그인 페이지를 보여주는 GET 요청 처리
	@GetMapping("/login")
	public String loginPage() {
		return "user/login";
	}

	// 로그인 폼 제출(POST) 요청 처리
	@PostMapping("/login")
	public String login(@RequestParam String email, @RequestParam String password, HttpSession session, Model model) {
		try {
			User user = userService.loginUser(email, password);
			System.out.println("로그인 시 가져온 userId: " + user.getUserId());
			if (user != null) {
				session.setAttribute("user", user);
				return "redirect:/main"; // 로그인 성공 시 메인 페이지로 리다이렉트
			} else {
				model.addAttribute("error", "로그인에 실패했습니다.");
				return "user/login";
			}
		} catch (Exception e) {
			model.addAttribute("error", "로그인 중 오류가 발생했습니다");
			return "user/login";
		}
	}

	// 회원가입 페이지를 보여주는 GET 요청 처리
	@GetMapping("/register")
	public String registerPage() {
		return "user/register";
	}

	// 회원가입 폼 제출(POST) 요청 처리
	@PostMapping("/register")
	public String register(User user, Model model) {
		try {
			if (userService.isEmailDuplicate(user.getEmail())) {
				model.addAttribute("error", "이미 사용중인 이메일입니다.");
				return "user/register";
			}
			if (userService.isUserIdDuplicate(user.getUserId())) {
				model.addAttribute("error", "이미 사용 중인 사용자 ID입니다.");
				return "user/register";
			}
			boolean result = userService.registerUser(user);
			if (result) {
				return "redirect:/user/login"; // 회원가입 성공 시 로그인 페이지로 리다이렉트
			} else {
				model.addAttribute("error", "회원가입 실패했습니다");
				return "user/register";
			}
		} catch (Exception e) {
			model.addAttribute("error", "회원 가입 중 오류 발생");
			return "user/register";
		}
	}

	@GetMapping("/mypage")
	public String myPage(HttpSession session, Model model) {
		User loginUser = (User) session.getAttribute("user");
		model.addAttribute("user", loginUser);
		return "user/mypage";
	}

	@GetMapping("/delete-account")
	public String deleteAccount(HttpSession session, Model model) {
		User loginUser = (User) session.getAttribute("user");
		System.out.println("세션에서 가져온 User: " + loginUser);
		if (loginUser != null) {
			System.out.println("삭제 시점 userId: " + loginUser.getUserId());
			userService.deleteUser(loginUser.getUserId());
			session.invalidate();
			model.addAttribute("message", "회원 탈퇴가 완료되었습니다");
		} else {
			model.addAttribute("error", "로그인 상태가 아닙니다.");
		}
		return "redirect:/";
	}

	// 로그아웃 GET 요청 처리
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/"; // 로그아웃 후 메인 페이지로 리다이렉트
	}
}