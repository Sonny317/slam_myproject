<%@ include file="../common/header.jsp" %>

<!-- Google Identity Services -->
<script src="https://accounts.google.com/gsi/client" async defer></script>

<div class="container">
    <div class="form-container">
        <h2 class="text-center mb-4">로그인</h2>
        
        <c:if test="${not empty error}">
            <div class="alert alert-danger" role="alert">
                ${error}
            </div>
        </c:if>
        
        <!-- 일반 로그인 폼 -->
        <form action="${pageContext.request.contextPath}/user/login" method="post">
            <div class="mb-3">
                <label for="email" class="form-label">이메일</label>
                <input type="email" class="form-control" id="email" name="email" required>
            </div>
            
            <div class="mb-3">
                <label for="password" class="form-label">비밀번호</label>
                <input type="password" class="form-control" id="password" name="password" required>
            </div>
            
            <div class="d-grid gap-2">
                <button type="submit" class="btn slam-btn">로그인</button>
            </div>
        </form>
        
        <!-- 구분선 -->
        <div class="text-center my-3">
            <hr style="width: 100%; margin: 20px 0;">
            <span style="background: white; padding: 0 15px; color: #6c757d;">또는</span>
        </div>
        
        <!-- ✅ 구글 로그인 버튼 (직접 OAuth2 URL 방식) -->
        <div class="text-center mb-3">
            <a href="https://accounts.google.com/o/oauth2/v2/auth?client_id=${googleClientId}&redirect_uri=${googleRedirectUri}&response_type=code&scope=openid%20email%20profile" 
               class="btn btn-outline-primary btn-lg d-flex align-items-center justify-content-center" 
               style="max-width: 300px; margin: 0 auto;">
                <svg width="18" height="18" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 48 48" style="margin-right: 8px;">
                    <path fill="#FFC107" d="M43.611,20.083H42V20H24v8h11.303c-1.649,4.657-6.08,8-11.303,8c-6.627,0-12-5.373-12-12c0-6.627,5.373-12,12-12c3.059,0,5.842,1.154,7.961,3.039l5.657-5.657C34.046,6.053,29.268,4,24,4C12.955,4,4,12.955,4,24c0,11.045,8.955,20,20,20c11.045,0,20-8.955,20-20C44,22.659,43.862,21.35,43.611,20.083z"></path>
                    <path fill="#FF3D00" d="M6.306,14.691l6.571,4.819C14.655,15.108,18.961,12,24,12c3.059,0,5.842,1.154,7.961,3.039l5.657-5.657C34.046,6.053,29.268,4,24,4C16.318,4,9.656,8.337,6.306,14.691z"></path>
                    <path fill="#4CAF50" d="M24,44c5.166,0,9.86-1.977,13.409-5.192l-6.19-5.238C29.211,35.091,26.715,36,24,36c-5.202,0-9.619-3.317-11.283-7.946l-6.522,5.025C9.505,39.556,16.227,44,24,44z"></path>
                    <path fill="#1976D2" d="M43.611,20.083H42V20H24v8h11.303c-0.792,2.237-2.231,4.166-4.087,5.571c0.001-0.001,0.002-0.001,0.003-0.002l6.19,5.238C36.971,39.205,44,34,44,24C44,22.659,43.862,21.35,43.611,20.083z"></path>
                </svg>
                Google 계정으로 로그인
            </a>
        </div>
        
        <div class="text-center mt-3">
            <p>계정이 없으신가요? <a href="${pageContext.request.contextPath}/user/register">회원가입</a></p>
        </div>
    </div>
</div>

<%@ include file="../common/footer.jsp" %>
