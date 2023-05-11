<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="m-5">
	<h2>재고 수정</h2>
	<div class="mt-4">제품명 : ${product.name}</div>

	<div>색상 : ${productOption.color}</div>
	<div>사이즈 : ${productOption.size}</div>
	<div>재고 : ${productOption.stock}</div>

	<div class="mt-2">
		<div>재고</div>
		<input type="number" id="stock" class="form-control col-5">
	</div>
	<button type="button" id="updateBtn" class="btn btn-warning mt-3" data-product-option-id="${productOption.id}">재고 수정</button>
</div>

<script>
$(document).ready(function(){
	$("#updateBtn").on("click", function(){
		let productOptionId = $(this).data("product-option-id");
		let stock = $("#stock").val().trim();
		
		if(!stock){
			alert("재고을 입력해주세요.");
			return;
		}
		
		$.ajax({
			type:"POST"
			, url:"/product_option/update"
			, data:{"productOptionId":productOptionId, "stock":stock}
		
			, success:function(data){
				if(data.code == 1){
					alert(data.result);
					location.href="/product_admin/product_list_view";
				} else {
					alert(data.errorMessage);
					return;
				}
			}
			, error:function(request, status, error) {
				alert("옵션 수정 실패");
			}
		})
	});
});
</script>