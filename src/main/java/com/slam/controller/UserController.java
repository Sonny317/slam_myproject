package com.slam.controller;

import java.util.Collections;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
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
	
	// Google OAuth2 설정 (properties에서 주입)
	@Value("${google.oauth.client.id}")
	private String googleClientId;
	
	@Value("${google.oauth.client.secret}")
	private String googleClientSecret;
	
	@Value("${google.oauth.redirect.uri}")
	private String googleRedirectUri;

	// 로그인 페이지를 보여주는 GET 요청 처리
	@GetMapping("/login")
	public String loginPage(Model model) {
		// Google OAuth2 설정을 JSP에 전달 (하드코딩 제거)
		model.addAttribute("googleClientId", googleClientId);
		model.addAttribute("googleRedirectUri", googleRedirectUri);
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

	@GetMapping("/google-login")
	public String googleLogin(@RequestParam("code") String authorizationCode, HttpSession session, Model model) {
		try {
			// 1. authorization code를 access token으로 교환
			String accessToken = exchangeCodeForAccessToken(authorizationCode);
			
			if (accessToken == null) {
				model.addAttribute("error", "구글 액세스 토큰 요청 실패");
				return "user/login";
			}

			// 2. access token으로 사용자 정보 가져오기
			JsonObject userInfo = getUserInfo(accessToken);
			
			if (userInfo == null) {
				model.addAttribute("error", "구글 사용자 정보 요청 실패");
				return "user/login";
			}

			// 3. 사용자 정보 추출
			String email = userInfo.get("email").getAsString();
			String name = userInfo.get("name").getAsString();
			String googleId = userInfo.get("id").getAsString();

			// 4. DB에서 해당 사용자 존재 여부 확인 (없으면 회원가입)
			User user = userService.getUserByEmail(email);
			if (user == null) {
				user = new User();
				user.setEmail(email);
				user.setNickname(name);
				user.setGoogleId(googleId);
				user.setRole("Member");
				userService.registerUser(user);
			}

			// 5. 세션에 저장 (로그인 상태 유지)
			session.setAttribute("user", user);

			// 로그인 성공 → 메인으로
			return "redirect:/main";

		} catch (Exception e) {
			model.addAttribute("error", "구글 로그인 처리 중 오류 발생: " + e.getMessage());
			return "user/login";
		}
	}
	
	// authorization code를 access token으로 교환
	private String exchangeCodeForAccessToken(String authorizationCode) throws Exception {
		// properties에서 주입받은 값들 사용 (하드코딩 제거)
		
		String tokenUrl = "https://oauth2.googleapis.com/token";
		
		String postData = "code=" + URLEncoder.encode(authorizationCode, "UTF-8") +
				"&client_id=" + URLEncoder.encode(googleClientId, "UTF-8") +
				"&client_secret=" + URLEncoder.encode(googleClientSecret, "UTF-8") +
				"&redirect_uri=" + URLEncoder.encode(googleRedirectUri, "UTF-8") +
				"&grant_type=authorization_code";
		
		URL url = new URL(tokenUrl);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		conn.setDoOutput(true);
		
		try (OutputStream os = conn.getOutputStream()) {
			os.write(postData.getBytes("UTF-8"));
		}
		
		int responseCode = conn.getResponseCode();
		if (responseCode != 200) {
			return null;
		}
		
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
			StringBuilder response = new StringBuilder();
			String line;
			while ((line = reader.readLine()) != null) {
				response.append(line);
			}
			
			JsonObject jsonResponse = JsonParser.parseString(response.toString()).getAsJsonObject();
			return jsonResponse.get("access_token").getAsString();
		}
	}
	
	// access token으로 사용자 정보 가져오기
	private JsonObject getUserInfo(String accessToken) throws Exception {
		String userInfoUrl = "https://www.googleapis.com/oauth2/v2/userinfo?access_token=" + accessToken;
		
		URL url = new URL(userInfoUrl);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		
		int responseCode = conn.getResponseCode();
		if (responseCode != 200) {
			return null;
		}
		
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
			StringBuilder response = new StringBuilder();
			String line;
			while ((line = reader.readLine()) != null) {
				response.append(line);
			}
			
			return JsonParser.parseString(response.toString()).getAsJsonObject();
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
			// userId는 DB가 자동 생성하므로 중복 검사 불필요
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