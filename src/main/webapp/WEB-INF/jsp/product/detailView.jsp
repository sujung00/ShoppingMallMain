<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container pt-5">
	<div class="d-flex justify-content-between">
		<!-- 상품 대표 이미지 -->
		<div>
			<img alt="상품 대표 이미지" src="${product.mainImagePath}" width="300px">
		</div>
		<!-- 상품 설명 및 상세 정보 -->
		<div class="product-detail mt-3">
			<!-- 상품 설명 -->
			<div>
				<div class="font1">상품 설명</div>
				<div class="mytrend-font3 mt-1">${product.information}</div>
			</div>
			<!-- 상세 설명 -->
			<div class="mt-4">
				<div class="font1">상세 설명</div>
				<div class="mytrend-font3 mt-1">${product.detailedInfo}​​</div>
			</div>
		</div>
		<!-- 상품 옵션, 장바구니, 리뷰쓰기 -->
		<div class="product-detail mt-3">
			<div class="font2">${product.name}</div>
			<div class="mytrend-font3 my-3">${product.price}원</div>
			<div class="input-group my-4">
				<div class="input-group-prepend">
					<label class="input-group-text" for="colorOption">COLOR</label>
				</div>
				<select name="colorOption" class="custom-select" id="colorOption" data-product-id="${product.id}">
					<option value="0" selected class="mytrend-font3">Choose...</option>
					<c:forEach items="${colorList}" var="color">
						<option value="${color}" class="mytrend-font3">${color}</option>
					</c:forEach>
				</select>
			</div>

			<div class="input-group my-4">
				<div class="input-group-prepend">
					<label class="input-group-text" for="sizeOption">SIZE</label>
				</div>
				<select name="sizeOption" class="custom-select" id="sizeOption" disabled>
					<!-- color에 따른 size -->
					<option selected class="mytrend-font3">Choose the color first...</option>
				</select>
			</div>
			
			<div class="input-group my-4">
				<div class="input-group-prepend">
					<label class="input-group-text" for="count">COUNT</label>
				</div>
				<input type="number" id="count" value="1" class="form-control">
			</div>
			
			<button type="button" id="basketBtn" class="btn w-100 sign-up-btn my-3" data-product-id="${product.id}" data-user-id="${userId}">장바구니 담기</button>
		</div>
	</div>

	<!-- 제품 상세 이미지 -->
	<div class="mt-5">
		<c:forEach items="${productImageList}" var="productImage">
			<div class="d-flex justify-content-center my-3">
				<img alt="상품 상세 이미지" src="${productImage.imagePath}" width="300px">
			</div>
		</c:forEach>
	</div>

	<!-- review -->
	<div class="mt-5">
		<div class="font4 m-4 ml-5">REVIEW</div>

		<div class="ml-5 review p-3 d-flex justify-content-between">
			<div class="d-flex">
				<div>
					<img alt="리뷰 이미지" src="/static/img/dressMain.jpg" width="70px" height="70px">
				</div>
				<div class="d-flex align-items-center ml-3">
					<div>
						<div class="font1 mb-1">리뷰 제목</div>
						<div class="mytrend-font3">리뷰 내용~~~~</div>
					</div>
				</div>
			</div>
			<div class="d-flex align-items-center">
				<button type="button" id="reviewMoreBtn" data-toggle="modal" data-target="#reviewModal" class="more-btn">
					<img alt="리뷰 이미지" src="/static/img/more-icon.png" width="20px" height="20px">
				</button>
			</div>
		</div>
	</div>
</div>

<div class="modal" tabindex="-1" id="reviewModal">
  <div class="modal-dialog modal-dialog-centered modal-sm">
  	<div class="modal-content">
      <div class="modal-body d-flex justify-content-around">
      	<button type="button" id="reviewUpdateBtn" class="btn font1">수정 하기</button>
      	<button type="button" id="reviewDeleteBtn" class="btn font1">삭제 하기</button>
      </div>
    </div>
   </div>
</div>

<div class="modal" tabindex="-1" id="detailModal">
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
				$("#detailModal").modal();
				$("#modalBody").text("사이즈를 가져오는데 실패했습니다.");
				return;
			}
		})
	});
	
	$("#basketBtn").on("click", function(){
		let userId = $(this).data("user-id");
		let productId = $(this).data("product-id");
		let color = $("#colorOption option:selected").val();
		let size = $("#sizeOption option:selected").val();
		let count = $("#count").val();
		
		if(!userId){
			$("#detailModal").modal();
			$("#modalBody").text("로그인 후 이용 가능합니다.");
			return;
		}
		if(color == 0){
			$("#detailModal").modal();
			$("#modalBody").text("색상을 선택해주세요.");
			return;
		}
		if(size == 0){
			$("#detailModal").modal();
			$("#modalBody").text("사이즈를 선택해주세요.");
			return;
		}
		if(count == 0){
			$("#detailModal").modal();
			$("#modalBody").text("수량을 선택해주세요.");
			return;
		}
		
		$.ajax({
			type:"POST"
			, url:"/basket/add_basket"
			, data:{"userId":userId, "productId":productId, "color":color, "size":size, "count":count}
			
			, success:function(data){
				if(data.code == 1){
					$("#detailModal").modal();
					$("#modalBody").text(data.result);
					
					$('#detailModal').on('hidden.bs.modal', function (e) {
						location.href="/basket/basket_view";
					})
				}
			}
			, error : function(request, status, error) {
				$("#detailModal").modal();
				$("#modalBody").text("장바구니 담기에 실패했습니다.");
				return;
			}
		})
	});
});
</script>