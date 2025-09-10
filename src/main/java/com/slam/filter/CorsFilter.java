package com.slam.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

public class CorsFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// 필터 초기화
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("[CorsFilter] 실행됨"); // 로그 확인용
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		// Google OAuth를 위해 COOP 헤더를 아예 설정하지 않음 (브라우저 기본값 사용)
		// httpResponse.setHeader("Cross-Origin-Opener-Policy", "unsafe-none");
		// httpResponse.setHeader("Cross-Origin-Opener-Policy", "");
		
		// 추가적인 CORS 헤더들만 설정
		httpResponse.setHeader("Cross-Origin-Embedder-Policy", "unsafe-none");
		
		System.out.println("[CorsFilter] COOP 헤더 설정하지 않음 (브라우저 기본값 사용)");

		// 다음 필터로 요청 전달
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		// 필터 정리
	}
}
