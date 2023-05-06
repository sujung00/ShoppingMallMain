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
		<div class="mytrned-logo">비밀번호 변경</div>
		<div class="mt-5">
			<div class="mytrend-input d-flex justify-content-center">
				<input type="password" placeholder="현재 비밀번호" id="nowPassword">
			</div>
			<div class="mytrend-input d-flex justify-content-center mt-3">
				<input type="password" placeholder="새 비밀번호" id="password">
			</div>
			<div class="mytrend-input d-flex justify-content-center mt-3">
				<input type="password" placeholder="한 번 더 입력해주세요" id="passwordCheck">
			</div>
			<div class="d-flex justify-content-center mt-2">
				<button type="button" id="updatePWBtn" class="mytrend-btn mt-3">비밀번호 변경</button>
			</div>
		</div>
	</div>
</div>

<div class="modal" tabindex="-1" id="updatePWModal">
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
	$("#updatePWBtn").on("click", function(){
		let nowPassword = $("#nowPassword").val();
		let password = $("#password").val();
		let passwordCheck = $("#passwordCheck").val();
		
		if(!nowPassword){
			$("#updatePWModal").modal();
			$("#modalBody").text("현재 비밀번호를 입력해주세요.");
			return;
		}
		if(!password){
			$("#updatePWModal").modal();
			$("#modalBody").text("새 비밀번호를 입력해주세요.");
			return;
		}
		if(!passwordCheck){
			$("#updatePWModal").modal();
			$("#modalBody").text("비밀번호를 한 번 더 입력해주세요.");
			return;
		}
		if(nowPassword == password){
			$("#updatePWModal").modal();
			$("#modalBody").text("새로운 비밀번호를 입력해주세요.");
			return;
		}
		if(password != passwordCheck){
			$("#updatePWModal").modal();
			$("#modalBody").text("비밀번호가 일치하지 않습니다.");
			return;
		}
		
		$.ajax({
			type:"POST"
			, url:"/user/update_password"
			, data:{"nowPassword":nowPassword, "password":password}
		
			, success:function(data){
				if(data.code == 1){
					$("#updatePWModal").modal();
					$("#modalBody").text(data.result);
					
					$('#updatePWModal').on('hidden.bs.modal', function (e) {
					     location.href="/user/profile_view";
					})
				} else {
					$("#updatePWModal").modal();
					$("#modalBody").text(data.errorMessage);
					
					$('#updatePWModal').on('hidden.bs.modal', function (e) {
					     location.reload();
					})
				}
			}
			, error : function(request, status, error) {
				$("#updatePWModal").modal();
				$("#modalBody").text("비밀번호 변경에 실패했습니다.");
				return;
			}
		});
	});
});
</script>