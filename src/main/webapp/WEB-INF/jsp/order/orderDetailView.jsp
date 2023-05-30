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
		<div class="mytrned-logo">주문 상세</div>
		<div class="mt-5 col-10">
			<div class="font7">
				<fmt:formatDate value="${orderDetail.order.createdAt}"
					pattern="yy.MM.dd" />
			</div>
			<c:forEach items="${orderDetail.orderAdminViewList}" var="orderAdmin">
				<c:choose>
				<c:when test="${orderAdmin.orderProduct.state eq '주문취소'}">
					<div class="mt-2 font13">${orderAdmin.orderProduct.state}</div>
				</c:when>
				<c:otherwise>
					<div class="mt-2 font8">${orderAdmin.orderProduct.state}</div>
				</c:otherwise>
				</c:choose>
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
									<div class="product-price mytrend-font3">${orderAdmin.product.price}</div>
									<div class="mytrend-font3">원</div>
								</div>
								<div class="ml-2 font9">${orderAdmin.productOption.color}(${orderAdmin.productOption.size})</div>
								<div class="d-flex">
									<div class="product-count ml-2 font10">${orderAdmin.orderProduct.count}</div>
									<div class="font10">개</div>
								</div>
							</div>
						</div>
					</div>
					<c:if test="${orderAdmin.orderProduct.state != '배송완료' && orderAdmin.orderProduct.state != '주문취소'}">
						<button type="button" class="option-update-btn order-detail-btn font1" data-toggle="modal"
						 data-target="#optionChangeModal" data-product-id="${orderAdmin.product.id}"
						 data-color-list="${orderAdmin.colorList}" data-orderproduct-id="${orderAdmin.orderProduct.id}"
						 data-origin-color="${orderAdmin.productOption.color}" data-origin-size="${orderAdmin.productOption.size}"
						 data-origin-count="${orderAdmin.orderProduct.count}">옵션 변경 ></button>
					</c:if>
				</div>
			</c:forEach>
			<div>
				<button class="btn review-btn2 mt-3 p-2" data-toggle="modal" data-target="#inquiryModal">문의하기</button>
			</div>
			<hr>
			<div class="d-flex justify-content-between">
				<div class="font7">배송지 정보</div>
				<c:if test="${orderDetail.deliveryCheck eq true}">
				<button class="btn review-btn2 col-3" data-toggle="modal" data-target="#addressModal">배송지 정보 변경</button>
				</c:if>
			</div>
			<div class="d-flex justify-content-between mt-3">
				<div class="col-3">
					<div class="font1 mt-1">수령인</div>
					<div class="font1 mt-1">휴대폰</div>
					<div class="font1 mt-1">주소</div>
					<c:if test="${not empty orderDetail.order.orderRequest}">
					<div class="font1 mt-1">배송시 요청사항</div>
					</c:if>
				</div>
				<div class="col-9">
					<div class="font11 mt-1">${orderDetail.address.name}</div>
					<div class="font11 mt-1">${orderDetail.address.phoneNumber}</div>
					<div class="font11 mt-1">${orderDetail.address.address}
						${orderDetail.address.detailedAddress}</div>
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
					<div class="mt-1 d-flex">
						<div id="totalProductPrice" class="font11"></div>
						<div class="font11">원</div>
					</div>
				</div>
				<div class="d-flex justify-content-between">
					<div class="font1 mt-1">배송비</div>
					<div class="d-flex mt-1">
						<div id="deliveryPay" class="font11"></div>
						<div class="font11">원</div>
					</div>
				</div>
				<div class="d-flex justify-content-between">
					<div class="font1 mt-1">총 할인 금액</div>
					<div class="d-flex mt-1">
						<div id="point" class="font11"></div>
						<div class="font11">원</div>
					</div>
				</div>
				<div class="d-flex justify-content-between mt-4">
					<div class="font7">총 결제 금액</div>
					<div class="d-flex">
						<div id="totalPrice" class="font7">${orderDetail.order.totalPay}</div>
						<div class="font7">원</div>
					</div>
				</div>
			</div>
			<c:if test="${orderDetail.deliveryCheck eq true}">
			<button id="orderCancelBtn" class="btn sign-up-btn mt-5 p-2" data-order-id="${orderDetail.order.id}">구매 취소</button>
			</c:if>
		</div>
	</div>
</div>

<div class="modal" tabindex="-1" id="optionChangeModal">
	<div class="modal-dialog modal-dialog-centered modal-sm">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title mytrned-logo">옵션 변경</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body" id="modalBody">
				<div class="mytrend-font">COLOR</div>
				<select name="colorOption" class="custom-select" id="colorOption">
					<option value="0" class="mytrend-font2">Choose...</option>
				</select>
				<div class="mytrend-font mt-3">SIZE</div>
				<select name="sizeOption" class="custom-select" id="sizeOption" disabled>
					<!-- color에 따른 size -->
					<option selected class="mytrend-font3">Choose the color first...</option>
				</select>
				<button id="productOptionUpdateBtn" class="review-btn2 p-2 mt-4">변경</button>
			</div>
		</div>
	</div>
</div>

<div class="modal" tabindex="-1" id="inquiryModal">
	<div class="modal-dialog modal-dialog-centered">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title mytrned-logo">문의하기</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body" id="modalBody">
				<input type="text" name="subject" placeholder="제목" class="form-control mytrend-font2">
				<textarea rows="5" id="content" placeholder="문의 내용" class="mt-3 form-control mytrend-font2"></textarea>
				<button type="button" id="inquiryBtn" class="review-btn2 p-2 mt-4" data-order-id="${orderDetail.order.id}">문의하기</button>
			</div>
		</div>
	</div>
</div>

<div class="modal" tabindex="-1" id="addressModal">
	<div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title mytrned-logo">배송지 정보 변경</h5>
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body" id="modalBody">
				<div id="newad" class="new-address">
				<div>
					<div class="mytrend-font2">이름</div>
					<div class="mytrend-input">
						<input type="text" id="name" value="${orderDetail.address.name}">
					</div>
				</div>
				<div class="mt-2">
					<div class="mytrend-font2">전화번호</div>
					<div class="mytrend-input">
						<input type="text" id="phoneNumber" placeholder="'-' 없이 숫자만 입력하세요." value="${orderDetail.address.phoneNumber}">
					</div>
				</div>
				<div class="mt-2">
					<div class="mytrend-font2">추가 연락처(선택)</div>
					<div class="mytrend-input">
						<input type="text" id="extraPhoneNumber" placeholder="'-' 없이 숫자만 입력하세요." value="${orderDetail.address.extraPhoneNumber}">
					</div>
				</div>
				<div class="mt-2">
					<div class="mytrend-font2">배송지 주소</div>
					<div class="address-input-div d-flex align-items-center">
						<input type="text" id="sample6_postcode" readonly="readonly" value="${orderDetail.address.postcode}">
						<button type="button" id="findAddressBtn" class="ml-2" onclick="sample6_execDaumPostcode()">찾 기</button>
					</div>
					<div class="address-input">
						<input type="text" id="sample6_address" readonly="readonly" value="${orderDetail.address.address}">
					</div>
					<div class="address-input">
						<input type="text" id="sample6_detailAddress" value="${orderDetail.address.detailedAddress}">
					</div>
				</div>
				<div class="mt-1">
					<input type="checkbox" id="defaultAddress">
					<label for="defaultAddress" class="mytrend-font3">기본 배송지 설정</label>
				</div>
				<div class="font9 mt-4">*변경된 배송지는 새로 추가 됩니다.</div>
				<div class="d-flex mt-2">
					<input type="button" id="addressUpdateBtn" value="변경" class="mytrend-btn" data-order-id="${orderDetail.order.id}">
				</div>
			</div>
			</div>
		</div>
	</div>
</div>

<div class="modal" tabindex="-1" id="orderDetailModal">
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

<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
function sample6_execDaumPostcode() {
    new daum.Postcode({
        oncomplete: function(data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 각 주소의 노출 규칙에 따라 주소를 조합한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var addr = ''; // 주소 변수
            var extraAddr = ''; // 참고항목 변수

            //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                addr = data.roadAddress;
            } else { // 사용자가 지번 주소를 선택했을 경우(J)
                addr = data.jibunAddress;
            }

            // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
            if(data.userSelectedType === 'R'){
                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if(data.buildingName !== '' && data.apartment === 'Y'){
                    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if(extraAddr !== ''){
                    extraAddr = ' (' + extraAddr + ')';
                }
            } else {
                document.getElementById("sample6_address").value = '';
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById('sample6_postcode').value = data.zonecode;
            document.getElementById("sample6_address").value = addr + extraAddr;
            // 커서를 상세주소 필드로 이동한다.
            document.getElementById("sample6_detailAddress").focus();
        }
    }).open();
}

$(document).ready( function() {
	function cancelPay(data){
		jQuery.ajax({
			// 예: http://www.myservice.com/payments/cancel
		    "url": "/order/cancel",
		    "type": "POST",
		    //"contentType": "application/json",
		    "data": {
		    "merchant_uid": data.orderId, // 예: ORD20180131-0000011
		    "cancel_request_amount": data.cancelPrice, // 환불금액
		    "reason": "주문 취소 및 환불" // 환불사유
		    // [가상계좌 환불시 필수입력] 환불 수령계좌 예금주
		    //"refund_holder": "홍길동", 
		    // [가상계좌 환불시 필수입력] 환불 수령계좌 은행코드(예: KG이니시스의 경우 신한은행은 88번)
		    //"refund_bank": "88" 
		    // [가상계좌 환불시 필수입력] 환불 수령계좌 번호
		    //"refund_account": "56211105948400" 
		 	}
		 	//"dataType": "json"
		}).done(function(result){
			$("#orderDetailModal").modal();
			$("#orderDetailModal #modalBody").text("주문 및 결제가 취소되었습니다.");
			
			$('#orderDetailModal').on('hidden.bs.modal', function (e) {
			     location.href="/order/order_deliver_view"
			})
		}).fail(function(error){
			$("#orderDetailModal").modal();
			$("#orderDetailModal #modalBody").text("주문은 취소 되었지만 결제는 취소에 실패했습니다.");
			
			$('#orderDetailModal').on('hidden.bs.modal', function (e) {
			     location.href="/order/order_deliver_view"
			})
		});
	}
	
	var totalProductPrice = 0;
	$(".order-product-detail").each(function() {
		var productPrice = parseInt($(this).find('.product-price').text());
		var productCount = parseInt($(this).find('.product-count').text());
		var subtotal = productPrice * productCount;
		totalProductPrice += subtotal;
	});

	$("#totalProductPrice").text(totalProductPrice);

	if (totalProductPrice > 300000) {
		$("#deliveryPay").text("0");
	} else {
		$("#deliveryPay").text("3000");
	}

	let deliveryPay = $("#deliveryPay").text();
	let totalPrice = $("#totalPrice").text();
	let point = parseInt(totalProductPrice) + parseInt(deliveryPay)- parseInt(totalPrice);
	$("#point").text(point);
	
	$(".option-update-btn").on("click", function(){
		let productId = $(this).data("product-id");
		let orderProductId = $(this).data("orderproduct-id");
		let originColor = $(this).data("origin-color");
		let originSize = $(this).data("origin-size");
		let originCount = $(this).data("origin-count");
		
		let colorList = $(this).data("color-list"); //[dark blue, black]
		colorList = colorList.replace("[", "");
		colorList = colorList.replace("]", "");
		colorList = colorList.split(", ");
		console.log(colorList);
		
		// color select option 초기화
		$("#colorOption option").remove();
		// color select option 추가
		$("#colorOption").append("<option value='0' selected class='mytrend-font3'>Choose...</option>");
		for(i = 0; i < colorList.length; i++){
			let color = colorList[i];
			$("#colorOption").append("<option value='" + color + "' class='mytrend-font3'>" + color + "</option>");
		}
		
		$("#colorOption").data("product-id", productId);
		$("#productOptionUpdateBtn").data("orderproduct-id", orderProductId);
		$("#productOptionUpdateBtn").data("origin-color", originColor);
		$("#productOptionUpdateBtn").data("origin-size", originSize);
		$("#productOptionUpdateBtn").data("origin-count", originCount);
	});
	
	$("#colorOption").change(function(){
		let productId = $(this).data("product-id");
		let color = $("#colorOption option:selected").val();
		
		$.ajax({
			type:"POST"
			, url:"/product/get_size"
			, data:{"productId":productId, "color":color}
		
			, success:function(data){
				if(data.code == 1){
					// disable 풀기
					$("#sizeOption").attr("disabled", false);
					// size select option 초기화
					$("#sizeOption option").remove();
					// size select option 추가
					$("#sizeOption").append("<option value='0' selected class='mytrend-font3'>Choose...</option>");
					for(i = 0; i < data.sizeList.length; i++){
						let size = data.sizeList[i];
						$("#sizeOption").append("<option value='" + size + "' class='mytrend-font3'>" + size + "</option>");
					}
				}
			}
			, error : function(request, status, error) {
				$("#orderDetailModal").modal();
				$("#orderDetailModal #modalBody").text("사이즈를 가져오는데 실패했습니다.");
				return;
			}
		})
	});
	
	$("#optionChangeModal #productOptionUpdateBtn").on("click", function(){
		let orderProductId = $(this).data("orderproduct-id");
		let color = $("#colorOption option:selected").val();
		let size = $("#sizeOption option:selected").val();
		
		// 원래 주문했던 option(color, size), count
		let originColor = $(this).data("origin-color");
		let originSize = $(this).data("origin-size");
		let originCount = $(this).data("origin-count");
		
		console.log(originColor);
		
		if(color == 0){
			$("#orderDetailModal").modal();
			$("#orderDetailModal #modalBody").text("색상을 선택해주세요.");
			return;
		}
		if(size == 0){
			$("#orderDetailModal").modal();
			$("#orderDetailModal #modalBody").text("사이즈를 선택해주세요.");
			return;
		}
		
		$.ajax({
			type:"POST"
			, url:"/order/order_product_update"
			, data:{"orderProductId":orderProductId, "color":color, "size":size,
				"originColor":originColor, "originSize":originSize, "originCount":originCount}
			
			, success:function(data){
				if(data.code == 1){
					// 옵션 변경 modal 닫기
					$("#optionChangeModal").modal("hide");
					
					$("#orderDetailModal").modal();
					$("#orderDetailModal #modalBody").text(data.result);
					
					$('#orderDetailModal').on('hidden.bs.modal', function (e) {
						location.reload();
					})
				}
			}
			, error : function(request, status, error) {
				$("#orderDetailModal").modal();
				$("#orderDetailModal #modalBody").text("옵션 변경하는데 실패했습니다.");
				return;
			}
		})
	});
	
	$("#inquiryBtn").on("click", function(){
		let orderId = $(this).data("order-id");
		let subject = $("input[name=subject]").val().trim();
		let content = $("#content").val();
		
		if(!subject) {
			$("#orderDetailModal").modal();
			$("#orderDetailModal #modalBody").text("제목을 입력해주세요.");
			return;
		}
		if(!content) {
			$("#orderDetailModal").modal();
			$("#orderDetailModal #modalBody").text("문의 내용을 입력해주세요.");
			return;
		}
		
		$.ajax({
			type:"POST"
			, url:"/inquiry/create"
			, data:{"orderId":orderId, "subject":subject, "content":content}
		
			, success:function(data){
				if(data.code == 1){
					// 옵션 변경 modal 닫기
					$("#inquiryModal").modal("hide");
					
					$("#orderDetailModal").modal();
					$("#orderDetailModal #modalBody").text(data.result);
					
					$('#orderDetailModal').on('hidden.bs.modal', function (e) {
						location.reload();
					})
				} else {
					$("#orderDetailModal").modal();
					$("#orderDetailModal #modalBody").text(data.errorMessage);
					return;
				}
			}
			, error : function(request, status, error) {
				$("#orderDetailModal").modal();
				$("#orderDetailModal #modalBody").text("문의하기에 실패했습니다.");
				return;
			}
		})
	});
	
	$("#addressUpdateBtn").on("click", function(){
		let orderId = $(this).data("order-id");
		let name = $("#name").val().trim();
		let phoneNumber = $("#phoneNumber").val().trim();
		let extraPhoneNumber = $("#extraPhoneNumber").val().trim();
		let postcode = $("#sample6_postcode").val();
		let address = $("#sample6_address").val();
		let detailedAddress = $("#sample6_detailAddress").val().trim();
		let defaultAddress = $('#defaultAddress').is(':checked'); // true or false
		
		if(!name) {
			$("#orderDetailModal").modal();
			$("#orderDetailModal #modalBody").text("이름을 입력해주세요.");
			return;
		}
		if(!phoneNumber) {
			$("#orderDetailModal").modal();
			$("#orderDetailModal #modalBody").text("전화번호를 입력해주세요.");
			return;
		}
		if(phoneNumber.includes("-")){
			$("#orderDetailModal").modal();
			$("#orderDetailModal #modalBody").text("-없이 숫자만 입력해주세요.");
			return;
		}
		if(extraPhoneNumber.includes("-")){
			$("#orderDetailModal").modal();
			$("#orderDetailModal #modalBody").text("-없이 숫자만 입력해주세요.");
			return;
		}
		if(!postcode) {
			$("#orderDetailModal").modal();
			$("#orderDetailModal #modalBody").text("우편번호를 입력해주세요.");
			return;
		}
		if(!detailedAddress) {
			$("#orderDetailModal").modal();
			$("#orderDetailModal #modalBody").text("상세 주소를 입력해주세요.");
			return;
		}
		
		$.ajax({
			type:"POST"
			, url:"/address/order_update_address_add"
			, data:{"orderId":orderId, "name":name, "phoneNumber":phoneNumber, "extraPhoneNumber":extraPhoneNumber,
				"postcode":postcode, "address":address, "detailedAddress":detailedAddress, "defaultAddress":defaultAddress}
		
			, success:function(data){
				if(data.code == 1){
					$("#addressModal").modal("hide");
					
					$("#orderDetailModal").modal();
					$("#orderDetailModal #modalBody").text(data.result);
					
					$('#orderDetailModal').on('hidden.bs.modal', function (e) {
					     location.reload();
					})
				} else {
					$("#orderDetailModal").modal();
					$("#orderDetailModal #modalBody").text(data.errorMessage);
					return;
				}
			}
			, error : function(request, status, error) {
				$("#orderDetailModal").modal();
				$("#orderDetailModal #modalBody").text("배송지 정보 변경에 실패했습니다.");
				return;
			}
		})
	});
	
	$("#orderCancelBtn").on("click", function(){
		let orderId = $(this).data("order-id");
		
		$.ajax({
			type:"POST"
			, url:"/order/delete"
			, data:{"orderId":orderId}
		
			, success:function(data){
				if(data.code == 1){
					cancelPay(data);
				} else {
					$("#orderDetailModal").modal();
					$("#orderDetailModal #modalBody").text(data.errorMessage);
					return;
				}
			}
			, error : function(request, status, error) {
				$("#orderDetailModal").modal();
				$("#orderDetailModal #modalBody").text("구매 취소에 실패했습니다.");
				return;
			}
		})
	});
});
</script>