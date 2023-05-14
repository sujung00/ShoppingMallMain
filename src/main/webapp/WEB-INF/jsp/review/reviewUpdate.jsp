<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="container pt-5">
	<div class="d-flex justify-content-around">
		<!-- 제품 이미지 -->
		<div>
			<img alt="상품 대표 이미지" src="${product.mainImagePath}" width="300px">
		</div>
		<!-- 리뷰 쓸 제품 정보 -->
		<div class="product-detail mt-4">
			<div class="font2">${product.name}</div>
			<div class="mytrend-font3 my-4">${product.price}원</div>
			<div class="my-4">
				<div class="font1">COLOR</div>
				<div class="mytrend-font3">black</div>
			</div>
			<div class="my-4">
				<div class="font1">SIZE</div>
				<div class="mytrend-font3">M</div>
			</div>
		</div>
		<!-- 리뷰 작성 -->
		<div class="product-detail">
			<div>
				<input type="text" name="subject" placeholder="제목" class="form-control">
			</div>
			<div class="mt-3">
				<textarea rows="10" placeholder="리뷰 내용을 작성해주세요." class="form-control"></textarea>
			</div>
			<div class="d-flex justify-content-end mt-2">
				<button type="button" class="btn sign-up-btn col-3">이미지</button>
			</div>
			<div class="mt-4">
				<button type="button" class="btn review-btn">수정 완료</button>
			</div>
		</div>
	</div>
</div>