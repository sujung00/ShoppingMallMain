<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:choose>
	<c:when test="${userLoginId eq 'admin'}">
		<div class="header d-flex justify-content-between px-3">
			<div class="d-flex align-items-center h-100">
				<div class="main-logo mr-3">
					<a href="/product_admin/product_list_view">TREND ADMIN</a>
				</div>
				<nav class="main-nav d-flex align-items-center">
					<ul class="nav">
						<li class="nav-item"><a href="/product_admin/product_list_view" class="nav-link">상품관리</a></li>
						<li class="nav-item"><a href="/order_admin/order_list_view" class="nav-link">주문관리</a></li>
						<li class="nav-item"><a href="/inquiry_admin/inquiry_view" class="nav-link">문의사항</a></li>
					</ul>
				</nav>
			</div>


			<c:choose>
				<c:when test="${empty userId}">
					<div class="d-flex align-items-center">
						<div class="log-in mr-3">
							<a href="/user/sign_in_view">Login</a>
						</div>
					</div>
				</c:when>
				<c:otherwise>
					<div class="d-flex align-items-center">
						<div class="log-in mr-3">
							<a href="/user/sign_out">Logout</a>
						</div>
					</div>
				</c:otherwise>
			</c:choose>
		</div>
	</c:when>
	
	<c:otherwise>
		<div class="header d-flex justify-content-between px-3">
			<div class="d-flex align-items-center h-100">
				<div class="main-logo mr-3">
					<a href="/main/main_view">TREND</a>
				</div>
				<nav class="main-nav d-flex align-items-center">
					<ul class="nav">
						<li class="nav-item"><a href="/product/new_product_view" class="nav-link">신제품</a></li>
						<li class="nav-item"><a href="/product/woman_product_view" class="nav-link">여성</a></li>
						<li class="nav-item"><a href="/product/man_product_view" class="nav-link">남성</a></li>
					</ul>
				</nav>
			</div>


			<c:choose>
				<c:when test="${empty userId}">
					<div class="d-flex align-items-center">
						<div class="log-in mr-3">
							<a href="/user/sign_in_view">Login</a>
						</div>
						<a class="btn sign-up-btn" href="/user/sign_up_view">Sign Up</a>
					</div>
				</c:when>
				<c:otherwise>
					<div class="d-flex align-items-center">
						<div class="log-in mr-3">
							<a href="/user/profile_view">MY TREND</a>
						</div>
						<div class="log-in mr-3">
							<a href="/user/sign_out">LogOut</a>
						</div>
						<a href="/basket/basket_view" class="d-flex align-items-center mr-3"> 
							<img alt="장바구니" src="/static/img/basket.png" width="20px">
						</a>
					</div>
				</c:otherwise>
			</c:choose>
		</div>
	</c:otherwise>
</c:choose>