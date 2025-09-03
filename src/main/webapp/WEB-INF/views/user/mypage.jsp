마이페이지 생성 JSP

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>마이페이지 - SLAM</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .profile-section {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
            padding: 2rem 0;
            margin-bottom: 2rem;
        }
        .profile-avatar {
            width: 120px;
            height: 120px;
            border-radius: 50%;
            border: 4px solid white;
            object-fit: cover;
        }
        .info-card {
            border: none;
            box-shadow: 0 4px 6px rgba(0,0,0,0.1);
            border-radius: 10px;
        }
        .edit-btn {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            border: none;
            color: white;
            padding: 8px 20px;
            border-radius: 5px;
        }
    </style>
</head>
<body>
    <!-- 헤더 include -->
    <%@ include file="../common/header.jsp" %>

    <!-- 프로필 섹션 -->
    <div class="profile-section">
        <div class="container">
            <div class="row align-items-center">
                <div class="col-md-3 text-center">
                    <img src="${user.profileImageUrl != null ? user.profileImageUrl : '/static/images/default-avatar.jpg'}" 
                         alt="프로필 이미지" class="profile-avatar">
                </div>
                <div class="col-md-9">
                    <h2>${user.nickname}님의 마이페이지</h2>
                    <p class="mb-0">이메일: ${user.email}</p>
                    <p class="mb-0">가입일: ${user.createdAt}</p>
                </div>
            </div>
        </div>
    </div>

    <!-- 메인 콘텐츠 -->
    <div class="container">
        <div class="row">
            <!-- 개인정보 카드 -->
            <div class="col-md-6 mb-4">
                <div class="card info-card">
                    <div class="card-header d-flex justify-content-between align-items-center">
                        <h5 class="mb-0">개인정보</h5>
                        <button class="btn edit-btn" onclick="editProfile()">수정</button>
                    </div>
                    <div class="card-body">
                        <p><strong>사용자 ID:</strong> ${user.userId}</p>
                        <p><strong>닉네임:</strong> ${user.nickname}</p>
                        <p><strong>이메일:</strong> ${user.email}</p>
                        <p><strong>역할:</strong> ${user.role}</p>
                        <p><strong>상태:</strong> ${user.status}</p>
                    </div>
                </div>
            </div>

            <!-- 활동 내역 카드 -->
            <div class="col-md-6 mb-4">
                <div class="card info-card">
                    <div class="card-header">
                        <h5 class="mb-0">활동 내역</h5>
                    </div>
                    <div class="card-body">
                        <p><strong>가입일:</strong> ${user.createdAt}</p>
                       
                        <p><strong>게시글 수:</strong> ${postCount != null ? postCount : 0}개</p>
                        <p><strong>댓글 수:</strong> ${commentCount != null ? commentCount : 0}개</p>
                    </div>
                </div>
            </div>
        </div>

        <!-- 액션 버튼들 -->
        <div class="row">
            <div class="col-12 text-center">
                <button class="btn btn-warning me-2" onclick="changePassword()">비밀번호 변경</button>
                <button class="btn btn-danger" onclick="deleteAccount()">회원탈퇴</button>
            </div>
        </div>
    </div>

    <!-- 푸터 include -->
    <%@ include file="../common/footer.jsp" %>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
    <script>
        function editProfile() {
            window.location.href = '${pageContext.request.contextPath}/user/edit-profile';
        }
        
        function changePassword() {
            window.location.href = '${pageContext.request.contextPath}/user/change-password';
        }
        
        function deleteAccount() {
            if (confirm('정말로 회원탈퇴를 하시겠습니까? 이 작업은 되돌릴 수 없습니다.')) {
                window.location.href = '${pageContext.request.contextPath}/user/delete-account';
            }
        }
    </script>
</body>
</html>


