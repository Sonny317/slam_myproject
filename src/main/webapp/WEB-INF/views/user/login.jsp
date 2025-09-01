<%@ include file="../common/header.jsp" %>

<div class="container">
    <div class="form-container">
        <h2 class="text-center mb-4">로그인</h2>
        
        <c:if test="${not empty error}">
            <div class="alert alert-danger" role="alert">
                ${error}
            </div>
        </c:if>
        
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
        
        <div class="text-center mt-3">
            <p>계정이 없으신가요? <a href="${pageContext.request.contextPath}/user/register">회원가입</a></p>
        </div>
    </div>
</div>

<%@ include file="../common/footer.jsp" %>