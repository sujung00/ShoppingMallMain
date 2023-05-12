<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container">
	<div class="text-center product-logo mt-3">신상품</div>
	<div class="mt-5 d-flex flex-wrap justify-content-between">
		<c:forEach items="${newProductList}" var="product">
		<form action="/product/product_detail_view" class="m-2">
		<input type="hidden" name="productId" value="${product.id}">
			<button type="submit" class="product-btn">
			<div class="mb-2">
				<img alt="제품 이미지" src="${product.mainImagePath}" width="200px">
			</div>
			<div class="mytrend-font3">${product.name}</div>
			<div class="mytrend-font3">${product.price}원</div>
			</button>
		</form>
		</c:forEach>
	</div>
</div>
