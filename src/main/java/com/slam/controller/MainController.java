package com.slam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
    
    // 메인 페이지
    @RequestMapping("/main")
    public String main() {
        return "main";
    }
    
    // 홈 페이지 (루트 경로)
    @RequestMapping("/")
    public String home() {
        return "redirect:/main";
    }
    
    // 소개 페이지
    @RequestMapping("/about")
    public String about() {
        return "about";
    }
    
    // 연락처 페이지
    @RequestMapping("/contact")
    public String contact() {
        return "contact";
    }
}

