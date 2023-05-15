<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container p-4">
	<div class="d-flex justify-content-around">
		<!-- 장바구니에 담은 제품 -->
		<div>
			<!-- 제품 정보 -->
			<c:forEach>
			<div class="basket-div d-flex align-items-center">
				<div>
					<img alt="제품 대표 이미지" src="/static/img/dressMain.jpg" width="100px"
						height="100px">
				</div>
				<div class="ml-4">
					<div class="mytrend-font3">제품명</div>
					<div class="mytrend-font3 my-1">가격</div>
					<div class="mytrend-font3">색상 사이즈 개수</div>
				</div>
			</div>
			</c:forEach>
		</div>
		<!-- 결제 정보 -->
		<div class="order-div">
			<div class="pb-5">
				<div class="d-flex justify-content-between">
					<div class="font2">총 상품 금액</div>
					<div class="font1">225000원</div>
				</div>
				<div class="d-flex justify-content-between">
					<div class="font2">배송비</div>
					<div class="font1">0원</div>
				</div>
			</div>
			<hr class="mt-5">
			<div class="d-flex justify-content-between">
				<div class="font2">총 결제 예정 금액</div>
				<div class="font1">225000원</div>
			</div>
			<button type="button" class="btn sign-up-btn w-100 mt-4">주문 하기</button>
		</div>
	</div>
</div>