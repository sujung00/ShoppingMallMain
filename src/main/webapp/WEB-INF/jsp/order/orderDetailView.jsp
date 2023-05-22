<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="d-flex">
	<div class="mytrend-nav">
		<nav class="nav flex-column">
			<a class="nav-link" href="/user/profile_view">회원정보</a> <a
				class="nav-link" href="#">주문/배송</a> <a class="nav-link" href="#">문의내역</a>
			<a class="nav-link" href="/address/address_view">배송지 관리</a> <a
				class="nav-link" href="#">예치금</a>
		</nav>
	</div>

	<!-- content -->
	<div class="content col-8">
		<div class="mytrned-logo">주문 상세</div>
		<div class="mt-5 col-10">
			<div class="font7"><fmt:formatDate value="${orderDetail.order.createdAt}" pattern="yy.MM.dd"/></div>
			<c:forEach items="${orderDetail.orderAdminViewList}" var="orderAdmin">
			<div class="mt-2 font8">${orderAdmin.orderProduct.state}</div>
			<div class="order-product-detail d-flex justify-content-between align-items-center">
				<div class="d-flex align-items-center mt-2">
					<form action="/product/product_detail_view" method="post">
					<input type="hidden" name="productId" value="${orderAdmin.product.id}">
					<button type="submit" class="order-detail-btn">
						<img alt="상품 대표 이미지" src="${orderAdmin.product.mainImagePath}" width="80px" height="80px">
					</button>
					</form>
					<div class="ml-3">
						<div class="font1">${orderAdmin.product.name}</div>
						<div class="d-flex mt-2">
							<div class="d-flex">
								<div class="product-price mytrend-font3">${orderAdmin.product.price}</div><div class="mytrend-font3">원</div>
							</div>
							<div class="ml-2 font9">${orderAdmin.productOption.color}(${orderAdmin.productOption.size})</div>
							<div class="product-count ml-2 font10">${orderAdmin.orderProduct.count}개</div>
						</div>
					</div>
				</div>
				<button type="button" class="order-detail-btn font1">옵션 변경 ></button>
			</div>
			</c:forEach>
			<div>
				<button class="btn review-btn2 mt-3 p-2">문의하기</button>
			</div>
			<hr>
			<div class="d-flex justify-content-between">
				<div class="font7">배송지 정보</div>
				<button class="btn review-btn2 col-3">배송지 정보 변경</button>
			</div>
			<div class="d-flex justify-content-between mt-3">
				<div class="col-4">
					<div class="font1 mt-1">수령인</div>
					<div class="font1 mt-1">휴대폰</div>
					<div class="font1 mt-1">주소</div>
					<div class="font1 mt-1">배송시 요청사항</div>
				</div>
				<div class="col-8">
					<div class="font11 mt-1">${orderDetail.address.name}</div>
					<div class="font11 mt-1">${orderDetail.address.phoneNumber}</div>
					<div class="font11 mt-1">${orderDetail.address.address} ${orderDetail.address.detailedAddress}</div>
					<c:if test="${not empty orderDetail.order.orderRequest}">
					<div class="font11 mt-1">${orderDetail.order.orderRequest}</div>
					</c:if>
				</div>
			</div>
			<hr>
			<div class="font7">결제 내역</div>
			<div class="mt-3 p-3">
				<div class="d-flex justify-content-between">
					<div class="font1 mt-1">상품금액</div>
					<div id="totalProductPrice" class="font11 mt-1"></div>
				</div>
				<div class="d-flex justify-content-between">
					<div class="font1 mt-1">배송비</div>
					<div id="deliveryPay" class="font11 mt-1">배송비</div>
				</div>
				<div class="d-flex justify-content-between">
					<div class="font1 mt-1">총 할인 금액</div>
					<div class="font11 mt-1">총 할인 금액</div>
				</div>
				<div class="d-flex justify-content-between mt-4">
					<div class="font7">총 결제 금액</div>
					<div class="font7">${orderDetail.order.totalPay}</div>
				</div>
			</div>
			<button class="btn sign-up-btn mt-5 p-2">구매 취소</button>
		</div>
	</div>
</div>

<script>
var totalProductPrice = 0;
$(document).ready(function(){
	
});
</script>