<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="d-flex">
	<div class="mytrend-nav">
		<nav class="nav flex-column">
		  <a class="nav-link" href="/user/profile_view">회원정보</a>
		  <a class="nav-link" href="#">주문/배송</a>
		  <a class="nav-link" href="#">문의내역</a>
		  <a class="nav-link" href="/address/address_view">배송지 관리</a>
		  <a class="nav-link" href="#">예치금</a>
		</nav>
	</div>
	
	<!-- content -->
	<div class="content">
		<div class="mytrned-logo">포인트</div>
		<c:forEach items="${pointList}" var="point" begin="0" end="0">
		<div class="mytrend-font2 mt-4">사용 가능한 포인트 : ${point.totalPoint}원</div>
		</c:forEach>
		<div class="mytrend-font2 mt-5">포인트 내역</div>
		<div class="mt-3">
		<c:forEach items="${pointList}" var="point">
			<div class="point-div mt-3">
				<div><b>
					<fmt:formatDate value="${point.createdAt}" pattern="yyyy.MM.dd"/> 
				</b></div>
				<div class="mt-2">${point.changePoint}원</div>
				<div class="mt-1">${point.changeDetail}</div>
			</div>
		</c:forEach>
		</div>
	</div>
</div>