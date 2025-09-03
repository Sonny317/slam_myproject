<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>SLAM - Student Language Association Meeting</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .slam-header {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            color: white;
            padding: 1rem 0;
        }
        .slam-btn {
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            border: none;
            color: white;
            padding: 10px 20px;
            border-radius: 5px;
            transition: all 0.3s ease;
        }
        .slam-btn:hover {
            transform: translateY(-2px);
            box-shadow: 0 5px 15px rgba(0,0,0,0.2);
            color: white;
        }
        .form-container {
            max-width: 400px;
            margin: 50px auto;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 10px 30px rgba(0,0,0,0.1);
        }
    </style>
</head>
<body>
    <nav class="navbar navbar-expand-lg slam-header">
        <div class="container">
            <a class="navbar-brand text-white" href="${pageContext.request.contextPath}/main">
                <strong>SLAM</strong> - Student Language Association Meeting
            </a>
            <div class="navbar-nav ms-auto">
                <c:if test="${empty sessionScope.user}">
                    <a class="nav-link text-white" href="${pageContext.request.contextPath}/user/login">Sign In</a>
                    <a class="nav-link text-white" href="${pageContext.request.contextPath}/user/register">Sign Up</a>
                </c:if>
                <c:if test="${not empty sessionScope.user}">
                    <span class="nav-link text-white">안녕하세요, ${sessionScope.user.nickname}님!</span>
                    <a class="nav-link text-white" href="${pageContext.request.contextPath}/user/logout">Log Out</a>
                    <a class="nav-link text-white" href="${pageContext.request.contextPath}/user/mypage">My Page</a>
                </c:if>
            </div>
        </div>
    </nav>