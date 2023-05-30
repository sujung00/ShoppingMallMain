<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="d-flex">
	<div class="mytrend-nav">
		<nav class="nav flex-column">
		  <a class="nav-link" href="/user/profile_view">회원정보</a>
		  <a class="nav-link" href="/order/order_deliver_view">주문/배송</a>
		  <a class="nav-link" href="/review/review_view">리뷰</a>
		  <a class="nav-link" href="/inquiry/inquiry_view">문의내역</a>
		  <a class="nav-link" href="/address/address_view">배송지 관리</a>
		  <a class="nav-link" href="/point/point_view">포인트</a>
		</nav>
	</div>
	
	<!-- content -->
	<div class="content col-8">
		<div class="mytrned-logo">주문/배송</div>
		<!-- order -->
		<c:forEach items="${orderDetailViewList}" var="orderDetail">
		<div class="order-detail-div mt-5">
			<hr>
			<div class="d-flex justify-content-between">
				<div class="font7"><fmt:formatDate value="${orderDetail.order.createdAt}" pattern="yy.MM.dd"/></div>
				<form action="/order/order_detail_view" method="post">
				<input type="hidden" name="orderId" value="${orderDetail.order.id}">
				<button type="submit" class="font1 order-detail-btn">주문 상세 ></button>
				</form>
			</div>
			<hr>
			<!-- orderProduct -->
			<c:forEach items="${orderDetail.orderAdminViewList}" var="orderAdmin">
			<c:choose>
			<c:when test="${orderAdmin.orderProduct.state == '주문취소'}">
				<div class="mt-2 font13">${orderAdmin.orderProduct.state}</div>
			</c:when>
			<c:otherwise>
				<div class="mt-2 font8">${orderAdmin.orderProduct.state}</div>
			</c:otherwise>
			</c:choose>
			<div class="d-flex align-items-center mt-2">
				<form action="/product/product_detail_view" method="post">
				<input type="hidden" name="productId" value="${orderAdmin.product.id}">
				<button type="submit" class="order-detail-btn">
					<img alt="상품 대표 이미지" src="${orderAdmin.product.mainImagePath}" width="100px" height="100px">
				</button>
				</form>
				<div class="ml-3">
					<div class="font1">${orderAdmin.product.name}</div>
					<div class="d-flex mt-2">
					<div class="mytrend-font3">${orderAdmin.product.price}원</div>
					<div class="ml-2 font9">${orderAdmin.productOption.color}(${orderAdmin.productOption.size})</div>
					<div class="ml-2 font10">${orderAdmin.orderProduct.count}개</div>
					</div>
				</div>
			</div>
			</c:forEach>
		</div>
		</c:forEach>
	</div>
</div>