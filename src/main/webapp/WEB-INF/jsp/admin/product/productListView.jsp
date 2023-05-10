<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="m-5">
	<h2>제품 관리</h2>
	<div>
		<a type="button" href="/admin/product/product_create_view" class="btn btn-warning">제품 등록</a>
	</div>
	<div class="mt-5 d-flex">
		<c:forEach items="${productList}" var="product">
		<div class="ml-5">
			<img alt="제품 이미지" src="${product.mainImagePath}" width="250px">
			<div>제품명 : ${product.name}</div>
			<div>가격 : ${product.price}</div>
			<form action="/product_option/create_view" method="post">
				<input type="hidden" name="productId" value="${product.id}">
				<button type="submit" class="btn btn-info">옵션 추가</button>
			</form>
			<form action="/product_admin/product_update_view" method="post">
				<input type="hidden" name="productId" value="${product.id}">
				<button type="submit" class="btn btn-info">상품 수정</button>
			</form>
			<a href="#" class="btn btn-info">상품 삭제</a>
		</div>
		</c:forEach>
	</div>
</div>