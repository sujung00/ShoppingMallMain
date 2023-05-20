<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="d-flex">
	<div class="mytrend-nav">
		<nav class="nav flex-column">
			<a class="nav-link" href="/user/profile_view">회원정보</a> <a
				class="nav-link" href="#">주문/배송</a> <a class="nav-link"
				href="/review/review_view">리뷰</a> <a class="nav-link" href="#">문의내역</a>
			<a class="nav-link" href="/address/address_view">배송지 관리</a> <a
				class="nav-link" href="/point/point_view">포인트</a>
		</nav>
	</div>
	<div class="p-5">
		<div class="d-flex align-items-center review-write-div">
			<input type="button" id="writeBtn" value="작성 가능한 리뷰" class="review-btn">
			<input type="button" id="writtenBtn" value="작성한 리뷰" class="review-btn ml-5">
		</div>
		<hr>

		<div id="writeReview">
		<!-- 작성 가능한 리뷰 -->
		<c:forEach items="${reviewViewList}" var="reviewView">
		<div class="review-div d-flex align-items-center justify-content-between m-3">
			<div class="d-flex align-items-center">
				<div id="imageBtn" class="more-btn m-2">
					<img alt="제품 대표 이미지" src="${reviewView.product.mainImagePath}" width="100px"
						height="100px">
				</div>
				<div class="ml-4">
					<div class="mytrend-font3">${reviewView.product.name}</div>
					<div class="mytrend-font3">${reviewView.product.price}원</div>
					<div class="mytrend-font3">색상 : ${reviewView.productOption.color} / 
					사이즈 : ${reviewView.productOption.size}</div>
					<div class="mytrend-font3">수량 : ${reviewView.orderProduct.count}</div>
				</div>
			</div>
			<form action="/review/review_create" method="post">
			<input type="hidden" name="orderProductId" value="${reviewView.orderProduct.id}">
			<button type="submit" class="review-write-btn font1 mr-2">리뷰 작성 ></button>
			</form>
		</div>
		</c:forEach>
		</div>
		<div id="writtenReview"  class="d-none">
		<c:forEach items="${writtenReviewList}" var="writtenReview">
			<!-- 작성한 리뷰 -->
			<div class="review-div d-flex justify-content-between align-items-center my-3">
				<div class="d-flex align-items-center">
				<!-- 리뷰 이미지가 있다면 -->
				<c:if test="${not empty writtenReview.review.reviewImagePath}">
				<div>
					<img alt="리뷰 이미지" src="${writtenReview.review.reviewImagePath}" width="100px" height="100px">
				</div>
				</c:if>
				<c:if test="${empty writtenReview.review.reviewImagePath}">
				<div class="non-image font6 d-flex justify-content-center">(이미지 없음)</div>
				</c:if>
				<div class="ml-3 pr-3 review-product">
					<div class="font1">${writtenReview.product.name}</div>
					<div class="font5">색상 : ${writtenReview.productOption.color}</div>
					<div class="font5">사이즈 : ${writtenReview.productOption.size}</div>
					<div class="font5">수량 : ${writtenReview.orderProduct.count}</div>
				</div>
				<div class="ml-5">
					<div class="font1">${writtenReview.review.subject}</div>
					<div class="font5">${writtenReview.review.content}</div>
				</div>
				</div>
				<button type="button" class="more-btn mr-2" data-toggle="modal" data-target="#reviewModal" data-review-id="${writtenReview.review.id}">
					<img alt="더보기 메뉴" src="/static/img/more-icon.png" width="20px" height="20px">
				</button>
			</div>
		</c:forEach>
		</div>
	</div>
</div>

<div class="modal" tabindex="-1" id="reviewModal">
  <div class="modal-dialog modal-dialog-centered modal-sm">
  	<div class="modal-content">
      <div class="modal-body d-flex justify-content-around">
      	<form action="/review/review_update" method="post">
      	<input type="hidden" name="reviewId">
      	<input type="submit" id="reviewUpdateBtn" class="btn font1" value="수정 하기">
      	</form>
      	<input type="button" id="reviewDeleteBtn" class="btn font1" value="삭제 하기">
      </div>
    </div>
   </div>
</div>

<div class="modal" tabindex="-1" id="writtenReviewModal">
	<div class="modal-dialog modal-dialog-centered modal-sm">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title">Notice</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body" id="modalBody"></div>
		</div>
	</div>
</div>

<script>
$(document).ready(function(){
	$("#writeBtn").on("click", function(){
		$("#writeReview").removeClass("d-none");
		$("#writtenReview").addClass("d-none");
	});
	$("#writtenBtn").on("click", function(){
		$("#writtenReview").removeClass("d-none");
		$("#writeReview").addClass("d-none");
	});
	
	$(".more-btn").on("click", function(e) {
		e.preventDefault(); // 위로 올라가는 현상 방지

		let reviewId = $(this).data("review-id"); // getting

		$("#reviewModal").data("review-id", reviewId);
		$("input[name=reviewId]").val(reviewId);
	});
	
	$("#reviewModal #reviewDeleteBtn").on("click", function(e){
		e.preventDefault(); // 위로 올라가는 현상 방지
		
		let reviewId = $("#reviewModal").data("review-id");
		
		$.ajax({
			type:"POST"
			, url:"/review/delete"
			, data:{"reviewId":reviewId}
		
			, success:function(data){
				if(data.code == 1){
					$("#writtenReviewModal").modal();
					$("#modalBody").text(data.result);
					
					$('#writtenReviewModal').on('hidden.bs.modal', function(e) {
						location.reload();
					})
				}
			}
			, error : function(request, status, error) {
				$("#writtenReviewModal").modal();
				$("#modalBody").text("리뷰를 삭제하는데 실패했습니다.");
				return;
			}
		})
	})
});
</script>