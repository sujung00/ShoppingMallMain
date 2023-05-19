<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="container pt-5">
	<div class="d-flex justify-content-around">
		<!-- 제품 이미지 -->
		<div>
			<img alt="상품 대표 이미지" src="${reviewView.product.mainImagePath}" width="300px">
		</div>
		<!-- 리뷰 쓸 제품 정보 -->
		<div class="product-detail mt-4">
			<div class="font2">${reviewView.product.name}</div>
			<div class="mytrend-font3 my-4">${reviewView.product.price}원</div>
			<div class="my-4">
				<div class="font1">COLOR</div>
				<div class="mytrend-font3">${reviewView.productOption.color}</div>
			</div>
			<div class="my-4">
				<div class="font1">SIZE</div>
				<div class="mytrend-font3">${reviewView.productOption.size}</div>
			</div>
		</div>
		<!-- 리뷰 작성 -->
		<div class="product-detail">
			<div>
				<input type="text" name="subject" placeholder="제목" class="form-control">
			</div>
			<div class="mt-3">
				<textarea rows="10" id="content" placeholder="리뷰 내용을 작성해주세요." class="form-control"></textarea>
			</div>
			<div class="d-flex justify-content-end mt-2">
				<%-- file 태그는 숨겨두고 이미지를 클릭하면 file 태그를 클릭한 것처럼 이벤트를 줄 것이다. --%>
				<input type="file" id="file" class="d-none" accept=".gif, .jpg, .png, .jpeg">
				<%-- 이미지에 마우스 올리면 마우스커서가 링크 커서가 변하도록 a 태그 사용 --%>
				<a href="#" id="fileUploadBtn" class="ml-2">
					<img width="35" src="https://cdn4.iconfinder.com/data/icons/ionicons/512/icon-image-512.png">
				</a>
				<%-- 업로드 된 임시 파일 이름 저장될 곳 --%>
				<div id="fileName" class="ml-2 d-flex align-items-center"></div>	
			</div>
			<div class="mt-4">
				<button type="button" id="reviewBtn" class="review-btn2" data-user-id="${reviewView.userId}"
				 data-order-product-id="${reviewView.orderProduct.id}" data-order-id="${reviewView.orderProduct.orderId}">작성 완료</button>
			</div>
		</div>
	</div>
</div>

<div class="modal" tabindex="-1" id="reviewModal">
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
	// 파일 업로드 이미지 클릭 = > 숨겨져있는 file 태그를 동작시킨다.
	$("#fileUploadBtn").on('click', function(e) {
		e.preventDefault(); // a 태그의 스크롤이 올라가는 현상 방지

		// input file을 클릭한 것과 같은 효과
		$("#file").click();
	});

	// 사용자가 이미지를 선택했을 때 유효성 확인 및 업로드 된 파일명 노출
	$("#file").on('change', function(e) {
		let fileName = e.target.files[0].name; // ballon.jpg
		console.log(fileName);

		// 확장자 유효성 확인
		let ext = fileName.split(".").pop().toLowerCase();
		if (ext != "jpg" && ext != "png" && ext != "jpeg" && ext != "gif") {
			alert("이미지 파일만 업로드 할 수 있습니다.");
			// 파일 비우기
			$(this).val(""); // 파일 태그에 파일 제거
			$("#fileName").text(""); // 파일 이름 비우기
			return;
		}

		// 유효성 통과한 이미지는 상자에 업로드 된 파일 이름 노출
		$("#fileName").text(fileName);
	});
	
	$("#reviewBtn").on("click", function(){
		let userId = $(this).data("user-id");
		let orderProductId = $(this).data("order-product-id");
		let orderId = $(this).data("order-id");
		let subject = $('input[name="subject"]').val();
		let content = $("#content").val();
		let file = $("#file").val();
		
		if(!subject) {
			$("#reviewModal").modal();
			$("#modalBody").text("제목을 입력해주세요.");
			return;
		}
		if(!content) {
			$("#reviewModal").modal();
			$("#modalBody").text("리뷰 내용을 입력해주세요.");
			return;
		}
		if(file){
			let ext = file.split(".").pop().toLowerCase();
			if ($.inArray(ext, [ 'jpg', 'jpeg', 'png', 'gif' ]) == -1) {
				alert("이미지 파일만 업로드 할 수 있습니다.");
				$("#file").val("");
				return;
			}
		}
		
		// formdata
		let formData = new FormData();
		formData.append("userId", userId);
		formData.append("orderProductId", orderProductId);
		formData.append("orderId", orderId);
		formData.append("subject", subject);
		formData.append("content", content);
		formData.append("file", $('#file')[0].files[0]);
		
		$.ajax({
			type:"POST"
			, url:"/review/create"
			, data:formData
			, enctype : "multipart/form-data"
			, processData : false
			, contentType : false
			
			, success:function(data){
				if(data.code == 1){
					$("#reviewModal").modal();
					$("#modalBody").text(data.result);
					
					$('#reviewModal').on('hidden.bs.modal', function(e) {
						location.href="/review/review_view";
					})
				}
			}
			, error : function(request, status, error) {
				$("#reviewModal").modal();
				$("#modalBody").text("리뷰를 업로드하는데 실패했습니다.");
				return;
			}
		})
	})
});
</script>