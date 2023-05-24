<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="m-5">
	<h2>제품 옵션 추가</h2>
	<div class="mt-4">제품명 : ${product.name}</div>
	<!-- 이미 존재하는 옵션 List -->
	<c:forEach items="${poList}" var="productOption">
	<div class="border mt-2 d-flex align-items-center">
		<div class="mr-5">
			<div>색상 : ${productOption.color}</div>
			<div>사이즈 : ${productOption.size}</div>
			<div>재고 : ${productOption.stock}</div>
		</div>
		<form action="/product_option/update_view" method="post">
			<input type="hidden" name="productoptionId" value="${productOption.id}">
			<input type="hidden" name="productId" value="${product.id}">
			<button type="submit" id="updateBtn" class="btn btn-info">재고 수정</button>
		</form>
		<button type="submit" class="delete-btn btn btn-danger" data-option-id="${productOption.id}">옵션 삭제</button>
	</div>
	</c:forEach>

	<div class="mt-4">
		<div>색상</div>
		<input type="text" id="color" class="form-control col-5">
	</div>
	<div class="mt-2">
		<div>사이즈</div>
		<input type="text" id="size" class="form-control col-5">
	</div>
	<div class="mt-2">
		<div>재고</div>
		<input type="number" id="stock" class="form-control col-5">
	</div>
	<button type="button" id="optionBtn" class="btn btn-warning mt-3" data-product-id="${product.id}">옵션 추가</button>
</div>

<script>
$(document).ready(function(){
	$("#optionBtn").on("click", function(){
		let productId = $(this).data("product-id");
		let color = $("#color").val().trim();
		let size = $("#size").val().trim();
		let stock = $("#stock").val().trim();
		
		if(!color){
			alert("색상을 입력해주세요.");
			return;
		}
		if(!size){
			alert("사이즈을 입력해주세요.");
			return;
		}
		if(!stock){
			alert("재고을 입력해주세요.");
			return;
		}
		
		$.ajax({
			type:"POST"
			, url:"/product_option/create"
			, data:{"productId":productId, "color":color, "size":size, "stock":stock}
		
			, success:function(data){
				if(data.code == 1){
					alert(data.result);
					location.reload();
				} else {
					alert(data.errorMessage);
					return;
				}
			}
			, error:function(request, status, error) {
				alert("옵션 생성 실패");
			}
		})
	});
	
	$(".delete-btn").on("click", function(){
		let productOptionId = $(this).data("option-id");
		
		$.ajax({
			type:"POST"
			, url:"/product_option/delete"
			, data:{"productOptionId":productOptionId}
		
			, success:function(data){
				if(data.code == 1){
					alert(data.result);
					location.reload();
				} else {
					alert(data.errorMessage);
					return;
				}
			}
			, error:function(request, status, error) {
				alert("옵션 삭제 실패");
			}
		})
	});
	
})
</script>