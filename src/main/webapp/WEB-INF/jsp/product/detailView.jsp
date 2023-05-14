<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container pt-5">
	<div class="d-flex justify-content-between">
		<!-- 상품 대표 이미지 -->
		<div>
			<img alt="상품 대표 이미지" src="${product.mainImagePath}" width="300px">
		</div>
		<!-- 상품 설명 및 상세 정보 -->
		<div class="product-detail mt-3">
			<!-- 상품 설명 -->
			<div>
				<div class="font1">상품 설명</div>
				<div class="mytrend-font3 mt-1">${product.information}</div>
			</div>
			<!-- 상세 설명 -->
			<div class="mt-4">
				<div class="font1">상세 설명</div>
				<div class="mytrend-font3 mt-1">${product.detailedInfo}​​</div>
			</div>
		</div>
		<!-- 상품 옵션, 장바구니, 리뷰쓰기 -->
		<div class="product-detail mt-3">
			<div class="font2">${product.name}</div>
			<div class="mytrend-font3 my-3">${product.price}원</div>
			<div class="input-group my-4">
				<div class="input-group-prepend">
					<label class="input-group-text" for="inputGroupSelect01">COLOR</label>
				</div>
				<select class="custom-select" id="colorOption">
					<option selected class="mytrend-font3">Choose...</option>
					<c:forEach items="${colorList}" var="color">
						<option value="${color}" class="mytrend-font3">${color}</option>
					</c:forEach>
				</select>
			</div>

			<div class="input-group my-4">
				<div class="input-group-prepend">
					<label class="input-group-text" for="inputGroupSelect01">SIZE</label>
				</div>
				<select class="custom-select" id="sizeOption">
					<!-- color에 따른 size -->
					<option selected class="mytrend-font3">Choose...</option>
					<c:forEach items="${blackSizeList}" var="size">
						<option value="${size}" class="mytrend-font3">${size}</option>
					</c:forEach>
				</select>
			</div>
			<button type="button" id="basketBtn" class="btn w-100 sign-up-btn my-3">장바구니 담기</button>
			<form action="/review/create" method="post">
			<input type="hidden" name="productId" value="${product.id}">
				<button type="submit" id="reviewWriteBtn" class="btn w-100 review-btn">리뷰 쓰기</button>
			</form>
		</div>
	</div>

	<!-- 제품 상세 이미지 -->
	<div class="mt-5">
		<c:forEach items="${productImageList}" var="productImage">
			<div class="d-flex justify-content-center my-3">
				<img alt="상품 상세 이미지" src="${productImage.imagePath}" width="300px">
			</div>
		</c:forEach>
	</div>

	<!-- review -->
	<div class="mt-5">
		<div class="font4 m-4 ml-5">REVIEW</div>

		<div class="ml-5 review p-3 d-flex justify-content-between">
			<div class="d-flex">
				<div>
					<img alt="리뷰 이미지" src="/static/img/dressMain.jpg" width="70px" height="70px">
				</div>
				<div class="d-flex align-items-center ml-3">
					<div>
						<div class="font1 mb-1">리뷰 제목</div>
						<div class="mytrend-font3">리뷰 내용~~~~</div>
					</div>
				</div>
			</div>
			<div class="d-flex align-items-center">
				<img alt="리뷰 이미지" src="/static/img/more-icon.png" width="20px" height="20px">
			</div>
		</div>
	</div>
</div>