package com.slam.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage() {
		return "user/login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@RequestParam String email, @RequestParam String password, HttpSession session, Model model) {
		try {
			User user = userService.loginUser(email, password);
			if (user != null) {
				session.setAttribute("user", user);
				return "redirect:/main";
			} else {
				model.addAttribute("error", "로그인에 실패했습니다.");
				return "user/login";
			}

		} catch (Exception e) {
			model.addAttribute("error", "로그인 중 오류가 발생했습니다");
			return "user/login";
		}
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
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
				return "redirect:/user/login";
			} else {
				model.addAttribute("error", "회원가입 실패했습니다");
				return "user/register";
			}
		} catch (Exception e) {
			model.addAttribute("error", "회원 가입 중 오류 발생");
			return "user/register";
		}
	}
}
