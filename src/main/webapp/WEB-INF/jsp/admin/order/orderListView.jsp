<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="container">
	<h2>주문 관리</h2>
	<c:if test="${not empty orderAdminViewList}">
	<c:forEach items="${orderAdminViewList}" var="order">
	<div class="mt-2 d-flex border">
		<div>
			<div>주문번호(orderProductId) - ${order.orderProduct.id}</div>
			<div>주문 일시 - 
			<fmt:formatDate value="${order.orderProduct.createdAt}" pattern="yyyy년 MM월 dd일 HH:mm:ss"/>
			</div>
			<div>
			주문 상태(입금대기, 결제 완료, 상품준비중, 배송준비중, 배송완료) : 
			<div>${order.orderProduct.state}
			<select id="state" class="ml-4">
				<option value="0" selected>선택...</option>
				<option>입금대기</option>
				<option>결제완료</option>
				<option>상품준비중</option>
				<option>배송준비중</option>
				<option>배송완료</option>
			</select>
			<button class="state-update-btn btn btn-dark ml-3" data-order-product-id="${order.orderProduct.id}">변경</button>
			</div>
			</div>
			<div>상품 정보 :</div>
			<div class="ml-1">상품명 - ${order.product.name}</div>
			<div class="ml-1">옵션 - 컬러 : ${order.productOption.color}</div>
			<div class="ml-1">옵션 - 사이즈 : ${order.productOption.size}</div>
			<div class="ml-1">가격 - ${order.product.price}</div>
			<div class="ml-1">수량 - ${order.orderProduct.count}</div>
			<div>결제 정보 :</div>
			<div class="ml-1">결제 금액 : ${order.order.totalPay}</div>
			<div class="ml-1">결제 수단 : ${order.order.payType}</div>
			<div class="ml-1">결제 일시 : 
			<fmt:formatDate value="${order.order.createdAt}" pattern="yyyy년 MM월 dd일 HH:mm:ss"/>
			</div>
		</div>
		<div class="ml-5">
			<div>배송 정보 :</div>
			<div class="ml-1">수령인 정보 : ${order.address.name}</div>
			<div class="ml-1">주소 : ${order.address.address}${order.address.detailedAddress}</div>
			<div>구매자 정보 :</div>
			<div class="ml-1">이름 : ${order.user.name}</div>
			<div class="ml-1">연락처 : ${order.user.phoneNumber}</div>
			<div class="ml-1">이메일 : ${order.user.email}</div>
			<div>취소/환불 정보 :</div>
			<c:if test="${empty order.cancelRefund}">
			<input type="text" id="reason" placeholder="취소/환불 이유">
			<select id="cancelRefundState">
				<option value="0" selected>선택...</option>
				<option>취소</option>
				<option>환불</option>
			</select>
			<button id="cancelRefundBtn" class="btn btn-dark ml-3" data-order-product-id="${order.orderProduct.id}">취소/환불</button>
			</c:if>
			<c:if test="${not empty order.cancelRefund}">
			<div>취소/환불 : ${order.cancelRefund.state}</div>
			<div class="ml-1">
			사유 : ${order.cancelRefund.reason}
			<input type="text" id="cancelRefundReason" value="${order.cancelRefund.reason}">
			</div>
			<div class="ml-1">
			취소/환불 일자 : 
			<fmt:formatDate value="${order.cancelRefund.updatedAt}" pattern="yyyy년 MM월 dd일 HH:mm:ss"/>
			</div>
			<button id="cancelRefundDeleteBtn" class="btn btn-danger ml-3" data-cancel-refund-id="${order.cancelRefund.id}">삭제</button>
			<button id="cancelRefundUpdateBtn" class="btn btn-dark ml-3" data-cancel-refund-id="${order.cancelRefund.id}">변경</button>
			</c:if>
		</div>
	</div>
	</c:forEach>
	</c:if>
</div>

<script>
$(document).ready(function(){
	$(".state-update-btn").on("click", function(){
		let state = $("#state option:selected").val();
		let orderProductId = $(this).data("order-product-id");
		
		if(state == 0){
			alert("state를 선택해주세요");
			return;
		}
		
		$.ajax({
			type:"POST"
			, url:"/order_admin/state_update"
			, data:{"state":state, "orderProductId":orderProductId}
		
			, success:function(data){
				if(data.code == 1){
					alert(data.result);
					location.reload();
				} else {
					alert(data.errorMessage);
				}
			}
			, error : function(request, status, error) {
				alert("배송 상태 변경 실패")
				return;
			}
		})
	});
	
	$("#cancelRefundBtn").on("click", function(){
		let orderProductId = $(this).data("order-product-id");
		let reason = $("#reason").val().trim();
		let state = $("#cancelRefundState option:selected").val();
		
		if(!reason){
			alert("취소/환불 이유를 작성해주세요");
			return;
		}
		if(state == 0){
			alert("취소/환불 state를 선택해주세요.");
			return;
		}
		
		$.ajax({
			type:"POST"
			, url:"/order_admin/cancel_refund_create"
			, data:{"orderProductId":orderProductId, "reason":reason, "state":state}
		
			, success:function(data){
				if(data.code == 1){
					alert(data.result);
					location.reload();
				} else {
					alert(data.errorMessage);
					return;
				}
			}
			, error : function(request, status, error) {
				alert("취소/환불 생성 실패");
				return;
			}
		});
	});
	
	$("#cancelRefundDeleteBtn").on("click", function(){
		let cancelRefundId = $(this).data("cancel-refund-id");
		
		$.ajax({
			type:"POST"
			, url:"/order_admin/cancel_refund_delete"
			, data:{"cancelRefundId":cancelRefundId}
		
			, success:function(data){
				if(data.code == 1){
					alert(data.result);
					location.reload();
				} else {
					alert(data.errorMessage);
					return;
				}
			}
			, error : function(request, status, error) {
				alert("취소/환불 삭제 실패");
				return;
			}
		})
	});
	
	$("#cancelRefundUpdateBtn").on("click", function(){
		let cancelRefundId = $(this).data("cancel-refund-id");
		let reason = $("#cancelRefundReason").val().trim();
		
		if(!reason){
			alert("취소/환불 사유를 작성해주세요.");
			return;
		}
		
		$.ajax({
			type:"POST"
			, url:"/order_admin/cancel_refund_update"
			, data:{"cancelRefundId":cancelRefundId, "reason":reason}
		
			, success:function(data){
				if(data.code == 1){
					alert(data.result);
					location.reload();
				} else {
					alert(data.errorMessage);
					return;
				}
			}
			, error : function(request, status, error) {
				alert("취소/환불 수정 실패");
				return;
			}
		})
	});
});
</script>