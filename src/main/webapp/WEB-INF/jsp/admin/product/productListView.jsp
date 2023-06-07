<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="m-5">
	<h2>제품 관리</h2>
	<div>
		<a type="button" href="/product_admin/product_create_view" class="btn btn-warning">제품 등록</a>
	</div>
	<div class="mt-5 flex-wrap d-flex justify-content-between">
		<c:forEach items="${productList}" var="product">
		<div class="ml-5 my-3">
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
			<button type="submit" class="deleteBtn btn btn-danger" data-product-id="${product.id}">상품 삭제</button>
		</div>
		</c:forEach>
	</div>
</div>

<script>
$(document).ready(function(){
	$(".deleteBtn").on("click", function(){
		let productId = $(this).data("product-id");
		
		$.ajax({
			type:"POST"
			, url:"/product_admin/product_delete"
			, data:{"productId":productId}
			
			, success:function(data){
				if(data.code == 1){
					alert(data.result);
					location.reload();
				} else {
					alert(data.errorMessage);
					return;
				}
			}
		})
	});
});
</script>