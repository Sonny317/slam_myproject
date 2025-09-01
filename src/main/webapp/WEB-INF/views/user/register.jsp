<%@ include file="../common/header.jsp" %>

<div class="container">
    <div class="form-container">
        <h2 class="text-center mb-4">
            sign up
        </h2>

        <c:if test="${not empty error}">
            <div class="alert alert-danger" role="alert">
                ${error}
            </div>
        </c:if>

        <form action="${pageContext.request.contextPath}/user/register" method="post"  id="registerForm">
            <div class="mb-3">
                <label for="userId" class="form-label">
                    user ID *
                </label>
                <input type="texT" class="form-control" id="userId" name="userId" required>
                <div id="userIdCheck" class="form-text">
                
            </div>

            <div class="mb-3">
                <label for="email" class="form-label">
                    Email *
                </label>
                <input type="email" class="form-control" id="email" name="email" required>
                <div id="emailCheck" class="form-text"></div>
            </div>
            <div class="mb-3">
                <label for="password" class="form-label">
                    password *
                </label>
                <input type="password" class="form-control" id="password" name="password" required>
            </div>

            <div class="mb-3">
                <label for="nickname" class="form-label">
                    Nickname *
                </label>
                <input type="text" class="form-control" id="nickname" name="nickname" required>
            </div>

            <div class="d-grid gap-2">
                <button type="submit" class="btn slam-btn">회원가입</button>
            </div>

        </form>
                      
        <div class="text-center mt-3">
            <p>Already has E-mail? <a href="${pageContext.request.contextPath}/user/login">Log in</a></p>
        </div>


    </div>
</div>



<%@ include file="../common/footer.jsp" %>