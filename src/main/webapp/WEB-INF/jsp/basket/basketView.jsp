<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container p-4">
	<div class="d-flex justify-content-around">
		<!-- 장바구니에 담은 제품 -->
		<div>
			<!-- 제품 정보 -->
			<c:forEach items="${basketViewList}" var="basketView">
				<div class="basket-div d-flex align-items-center justify-content-between m-3">
					<div class="d-flex align-items-center">
						<form action="/product/product_detail_view" method="post">
							<input type="hidden" name="productId"
								value="${basketView.product.id}">
							<button type="submit" id="imageBtn" class="more-btn">
								<img alt="제품 대표 이미지" src="${basketView.product.mainImagePath}"
									width="100px" height="100px">
							</button>
						</form>
						<div class="ml-4">
							<div class="mytrend-font3">${basketView.product.name}</div>
							<div class="mytrend-font3">${basketView.product.price}원</div>
							<div class="mytrend-font3">색상 :
								${basketView.productOption.color} / 사이즈 :
								${basketView.productOption.size}</div>
							<div class="mytrend-font3">수량 :
								${basketView.basketProduct.count}</div>
						</div>
					</div>
					<button type="button" class="deleteBtn m-3 more-btn" data-basket-id="${basket.id}" data-basket-product-id="${basketView.basketProduct.id}">
						<img alt="삭제" src="/static/img/delete.png" width="10px" height="10px">
					</button>
				</div>
			</c:forEach>
		</div>
		<!-- 결제 정보 -->
		<div class="order-div">
			<div class="pb-5">
				<div class="d-flex justify-content-between">
					<div class="font2">총 상품 금액</div>
					<div class="font1">${basket.totalPrice}원</div>
				</div>
				<c:if test="${basket.totalPrice > 300000}">
				<div class="d-flex justify-content-between">
					<div class="font2">배송비</div>
					<div class="font1">0원</div>
				</div>
				</c:if>
				<c:if test="${basket.totalPrice <= 300000}">
				<div class="d-flex justify-content-between">
					<div class="font2">배송비</div>
					<div class="font1">3000원</div>
				</div>
				</c:if>
			</div>
			<hr class="mt-5">
			<c:if test="${basket.totalPrice > 300000}">
			<div class="d-flex justify-content-between">
				<div class="font2">총 결제 예정 금액</div>
				<div class="font1">${basket.totalPrice}원</div>
			</div>
			</c:if>
			<c:if test="${basket.totalPrice <= 300000}">
			<div class="d-flex justify-content-between">
				<div class="font2">총 결제 예정 금액</div>
				<div class="font1">${basket.totalPrice + 3000}원</div>
			</div>
			</c:if>
			<form action="/order/order_view" method="post">
			<input type="hidden" name="basketId" value="${basket.id}">
			<button type="submit" class="btn sign-up-btn w-100 mt-4">주문 하기</button>
			</form>
		</div>
	</div>
</div>

<div class="modal" tabindex="-1" id="basketModal">
  <div class="modal-dialog modal-dialog-centered modal-sm">
  	<div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title">Notice</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body" id="modalBody">
      </div>
    </div>
   </div>
</div>

<script>
$(document).ready(function(){
	$(".deleteBtn").on("click", function(){
		let basketId = $(this).data("basket-id");
		let basketProductId = $(this).data("basket-product-id");
		
		$.ajax({
			type:"POST"
			, url:"/basket_product/delete"
			, data:{"basketId":basketId, "basketProductId":basketProductId}
		
			, success:function(data){
				if(data.code == 1){
					$("#basketModal").modal();
					$("#modalBody").text(data.result);

					$('#basketModal').on('hidden.bs.modal', function (e) {
						location.reload();
					})
				}
			}
			, error : function(request, status, error) {
				$("#basketModal").modal();
				$("#modalBody").text("장바구니 제품 삭제에 실패했습니다.");
				return;
			}
		})	
	});
});
</script>