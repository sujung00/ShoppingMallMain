<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="sign-box">
	<div class="sign-text text-center mb-3">비밀번호 찾기</div>
	<hr>
	<div> 
		<div class="sign-div d-flex justify-content-center">
			<input type="text" placeholder="id" id="loginId">
		</div>
		<div class="sign-div d-flex justify-content-center">
			<input type="text" placeholder="name" id="name">
		</div>
		<div class="sign-div d-flex justify-content-center">
			<input type="text" placeholder="email" id="email">
		</div>
		<div class="d-flex justify-content-center">
			<button type="button" id="findPWBtn" class="sign-btn mt-3">비밀번호 찾기</button>
		</div>
	</div>
</div>

<div class="modal" tabindex="-1" id="findPWModal">
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
	$("#findPWBtn").on("click", function(){
		$("#findPw").modal();
		
		let loginId = $("#loginId").val().trim();
		let name = $("#name").val().trim();
		let email = $("#email").val().trim();
		
		if(!loginId){
			$("#findPWModal").modal();
			$("#modalBody").text("아이디를 입력해주세요.");
			
			return;
		}
		if(!name){
			$("#findPWModal").modal();
			$("#modalBody").text("이름을 입력해주세요.");
			
			return;
		}
		if(!email){
			$("#findPWModal").modal();
			$("#modalBody").text("이메일을 입력해주세요.");
			
			return;
		}
		
		//존재하는 계정이면 => 메일 보냄
		//존재하지 않는 계정이면 => modal로 존재하지 않는 계정이라고 띄움
		
		$.ajax({
			type:"POST"
			, url:"/user/find_pw"
			, data:{"loginId":loginId, "name":name, "email":email}
		
			, success:function(data){
				if(data.code == 1){
					//계정 있음
					$("#findPWModal").modal();
					$("#modalBody").text(data.result);
					
					$('#findPWModal').on('hidden.bs.modal', function (e) {
					     location.href="/user/sign_in_view";
					})
				} else if(data.code == 300){
					//계정 없음
					$("#findPWModal").modal();
					$("#modalBody").text(data.errorMessage);
					
					$('#findPWModal').on('hidden.bs.modal', function (e) {
					     location.reload();
					})
				} else if(data.code == 301){
					//카카오 계정
					$("#findPWModal").modal();
					$("#modalBody").text(data.errorMessage);
					
					$('#findPWModal').on('hidden.bs.modal', function (e) {
					     location.href="/user/sign_in_view";
					})
				}
			}
			, error : function(request, status, error) {
				$("#findPWModal").modal();
				$("#modalBody").text("비밀번호 찾기에 실패했습니다.");
				return;
			}
		})
	});
});
</script>