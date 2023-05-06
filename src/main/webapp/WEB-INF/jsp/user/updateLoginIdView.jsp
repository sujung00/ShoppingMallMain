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
		<div class="mytrned-logo">아이디 변경</div>
		<div class="mt-5">
			<div class="mytrend-font">현재 아이디 : ${userLoginId}</div>
			<div class="mytrend-input d-flex justify-content-center mt-3">
				<input type="text" placeholder="id" id="id">
			</div>
			<div class="d-flex justify-content-center mt-2">
				<button type="button" id="updateLoginIdBtn" class="mytrend-btn mt-3">아이디 변경</button>
			</div>
		</div>
	</div>
</div>

<div class="modal" tabindex="-1" id="updateLoginIdModal">
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
	$("#updateLoginIdBtn").on("click", function(){
		let loginId = $("#id").val().trim();
		
		if(!loginId){
			$("#updateLoginIdModal").modal();
			$("#modalBody").text("아이디를 입력해주세요.");
			return;
		}
		
		$.ajax({
			type:"POST"
			, url:"/user/update_loginid"
			, data:{"loginId":loginId}
		
			, success:function(data){
				if(data.code == 1){
					$("#updateLoginIdModal").modal();
					$("#modalBody").text(data.result);
					
					$('#updateLoginIdModal').on('hidden.bs.modal', function (e) {
					     location.href="/user/profile_view";
					})
				} else if(data.code == 300){
					$("#updateLoginIdModal").modal();
					$("#modalBody").text(data.errorMessage);
					
					$('#updateLoginIdModal').on('hidden.bs.modal', function (e) {
					     location.reload();
					})
				} else if(data.code == 500){
					$("#updateLoginIdModal").modal();
					$("#modalBody").text(data.errorMessage);
					
					$('#updateLoginIdModal').on('hidden.bs.modal', function (e) {
					     location.reload();
					})
				}
			}
			, error : function(request, status, error) {
				$("#signUpModal").modal();
				$("#modalBody").text("아이디 변경에 실패했습니다.");
				return;
			}
		});
	});
});
</script>