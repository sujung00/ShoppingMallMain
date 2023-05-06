<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="d-flex">
	<div class="mytrend-nav">
		<nav class="nav flex-column">
		  <a class="nav-link" href="/user/profile_view">회원정보</a>
		  <a class="nav-link" href="#">주문/배송</a>
		  <a class="nav-link" href="#">문의내역</a>
		  <a class="nav-link" href="#">배송지 관리</a>
		  <a class="nav-link" href="#">예치금</a>
		</nav>
	</div>
	
	<!-- content -->
	<div class="content">
		<div class="mytrned-logo">휴대폰 번호 변경</div>
		<div class="mt-5">
			<div class="mytrend-font">현재 휴대폰 번호 : ${userPhoneNumber}</div>
			<div class="mytrend-input d-flex justify-content-center mt-3">
				<input type="text" placeholder="phone number" id="phoneNumber">
			</div>
			<div class="d-flex justify-content-center mt-2">
				<button type="button" id="updatePhoneNumberBtn" class="mytrend-btn mt-3">휴대폰 번호 변경</button>
			</div>
		</div>
	</div>
</div>

<div class="modal" tabindex="-1" id="updatePhoneNumberModal">
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
	$("#updatePhoneNumberBtn").on("click", function(){
		let phoneNumber = $("#phoneNumber").val().trim();
		
		if(!phoneNumber){
			$("#updatePhoneNumberModal").modal();
			$("#modalBody").text("휴대폰 번호를 입력해주세요.");
			return;
		}
		if(phoneNumber.includes("-")){
			$("#updatePhoneNumberModal").modal();
			$("#modalBody").text("-없이 숫자만 입력해주세요.");
			return;
		}
		
		$.ajax({
			type:"POST"
			, url:"/user/update_phonenumber"
			, data:{"phoneNumber":phoneNumber}
		
			, success:function(data){
				if(data.code == 1){
					$("#updatePhoneNumberModal").modal();
					$("#modalBody").text(data.result);
					
					$('#updatePhoneNumberModal').on('hidden.bs.modal', function (e) {
					     location.href="/user/profile_view";
					})
				} else {
					$("#updatePhoneNumberModal").modal();
					$("#modalBody").text(data.errorMessage);
					
					$('#updatePhoneNumberModal').on('hidden.bs.modal', function (e) {
					     location.reload();
					})
				}
			}
			, error : function(request, status, error) {
				$("#updatePhoneNumberModal").modal();
				$("#modalBody").text("휴대폰 번호 변경에 실패했습니다.");
				return;
			}
		});
	});
});
</script>