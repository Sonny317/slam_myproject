<%@ include file="common/header.jsp" %>

<div class="container mt-5">
    <div class="row">
        <div class="col-md-12 text-center">
            <h1 class="display-4 mb-4">SLAM에 오신 것을 환영합니다!</h1>
            <p class="lead mb-5">Student Language Association Meeting</p>
            
            <c:if test="${empty sessionScope.user}">
                <div class="row justify-content-center">
                    <div class="col-md-4">
                        <div class="card">
                            <div class="card-body text-center">
                                <h5 class="card-title">로그인</h5>
                                <p class="card-text">기존 회원이신가요?</p>
                                <a href="${pageContext.request.contextPath}/user/login" class="btn slam-btn">로그인</a>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="card">
                            <div class="card-body text-center">
                                <h5 class="card-title">회원가입</h5>
                                <p class="card-text">새로운 회원이신가요?</p>
                                <a href="${pageContext.request.contextPath}/user/register" class="btn slam-btn">회원가입</a>
                            </div>
                        </div>
                    </div>
                </div>
            </c:if>
            
            <c:if test="${not empty sessionScope.user}">
                <div class="alert alert-success" role="alert">
                    <h4>환영합니다, ${sessionScope.user.nickname}님!</h4>
                    <p>SLAM 커뮤니티에 참여해주셔서 감사합니다.</p>
                </div>
            </c:if>
        </div>
    </div>
</div>

<%@ include file="common/footer.jsp" %>