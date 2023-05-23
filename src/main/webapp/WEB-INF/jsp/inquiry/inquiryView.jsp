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
		<div class="mytrned-logo pb-2">문의 내역</div>
		<!-- 문의 내역 -->
		<c:forEach items="${inquiryViewList}" var="inquiryView">
		<div class="mt-4 d-flex justify-content-between inquiry-header">
			<div class="font1">
			<fmt:formatDate value="${inquiryView.inquiry.createdAt}" pattern="yy.MM.dd"/>
			</div>
			<c:if test="${empty inquiryView.answer}">
			<div class="font1">답변 대기중</div>
			</c:if>
			<c:if test="${not empty inquiryView.answer}">
			<button class="font1 answer-btn order-detail-btn" data-answer-content="${inquiryView.answer.content}">답변 완료 ></button>
			</c:if>
		</div>
		<div class="mt-2 inquiry-div p-3">
			<div class="font12">${inquiryView.inquiry.subject}</div>
			<div class="mt-1 font5">${inquiryView.inquiry.content}</div>
		</div>
		</c:forEach>
	</div>
</div>

<div class="modal" tabindex="-1" id="inquiryModal">
	<div class="modal-dialog modal-dialog-centered modal-sm">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title mytrned-logo">문의 답변</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body mytrend-font2" id="modalBody"></div>
		</div>
	</div>
</div>

<script>
$(document).ready(function(){
	$(".answer-btn").on("click", function(){
		let content = $(this).data("answer-content");
		
		$("#inquiryModal").modal();
		$("#modalBody").text(content);
		return;
	})
})
</script>