<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="d-flex">
	<div class="mytrend-nav">
		<nav class="nav flex-column">
		  <a class="nav-link" href="/user/profile_view">회원정보</a>
		  <a class="nav-link" href="#">주문/배송</a>
		  <a class="nav-link" href="#">문의내역</a>
		  <a class="nav-link" href="#">배송지 관리</a>
		  <a class="nav-link" href="#">예치금</a>
		</nav>
	</div>
	
	<!-- content -->
	<div class="content">
		<div class="mytrned-logo">회원 정보</div>
		<div class="mt-5">
			<div>
				<div class="mytrend-font">아이디</div>
				<a href="/user/update_loginid_view">
					${userLoginId}
					<img alt="update login id" src="/static/img/right.png" width="10px" class="ml-1">
				</a>
			</div>
			<div class="mt-4">
				<div class="mytrend-font">이름</div>
				<a href="/user/update_name_view">
					${userName}
					<img alt="update login id" src="/static/img/right.png" width="10px" class="ml-1">
				</a>
			</div>
			<div class="mt-4">
				<div class="mytrend-font">이메일</div>
				<a href="/user/update_email_view">
					${userEmail}
					<img alt="update login id" src="/static/img/right.png" width="10px" class="ml-1">
				</a>
			</div>
			<div class="mt-4">
				<div class="mytrend-font">휴대폰 번호</div>
				<a href="/user/update_phonenumber_view">
					${userPhoneNumber}
					<img alt="update login id" src="/static/img/right.png" width="10px" class="ml-1">
				</a>
			</div>
			<div class="mt-4">
				<div class="mytrend-font">비밀번호</div>
				<a href="/user/update_password_view">
					password
					<img alt="update login id" src="/static/img/right.png" width="10px" class="ml-1">
				</a>
			</div>
		</div>
	</div>
</div>