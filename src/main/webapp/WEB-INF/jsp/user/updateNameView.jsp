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
		<div class="mytrned-logo">이름 변경</div>
		<div class="mt-5">
			<div class="mytrend-font">현재 이름 : ${userName}</div>
			<div class="mytrend-input d-flex justify-content-center mt-3">
				<input type="text" placeholder="name" id="name">
			</div>
			<div class="d-flex justify-content-center mt-2">
				<button type="button" id="updateNameBtn" class="mytrend-btn mt-3">이름 변경</button>
			</div>
		</div>
	</div>
</div>

<div class="modal" tabindex="-1" id="updateNameModal">
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
	$("#updateNameBtn").on("click", function(){
		let name = $("#name").val().trim();
		
		if(!name){
			$("#updateNameModal").modal();
			$("#modalBody").text("이름을 입력해주세요.");
			return;
		}
		
		$.ajax({
			type:"POST"
			, url:"/user/update_name"
			, data:{"name":name}
		
			, success:function(data){
				if(data.code == 1){
					$("#updateNameModal").modal();
					$("#modalBody").text(data.result);
					
					$('#updateNameModal').on('hidden.bs.modal', function (e) {
					     location.href="/user/profile_view";
					})
				} else if(data.code == 300){
					$("#updateNameModal").modal();
					$("#modalBody").text(data.errorMessage);
					
					$('#updateNameModal').on('hidden.bs.modal', function (e) {
					     location.reload();
					})
				}
			}
			, error : function(request, status, error) {
				$("#updateNameModal").modal();
				$("#modalBody").text("이름 변경에 실패했습니다.");
				return;
			}
		});
	});
});
</script>