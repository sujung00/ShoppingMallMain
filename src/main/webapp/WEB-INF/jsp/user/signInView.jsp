<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="sign-box">
	<div class="sign-text text-center mb-3">로그인</div>
	<div> 
		<div class="sign-div d-flex justify-content-center">
			<input type="text" placeholder="id" id="id">
		</div>
		<div class="sign-div d-flex justify-content-center">
			<input type="password" placeholder="password" id="password">
		</div>
		<div class="d-flex justify-content-center">
			<div class="d-flex find-id-pw">
				<a href="/user/find_id_view" class="mx-2">아이디 찾기</a>
				<a href="/user/find_pw_view" class="ml-2">비밀번호 찾기</a>
			</div>
		</div>
		<div class="d-flex justify-content-center">
			<button type="button" id="signInBtn" class="sign-btn mt-3">SIGN IN</button>
		</div>
	</div>
</div>

<div class="modal" tabindex="-1" id="signInModal">
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
	$("#signInBtn").on("click", function(){
		let loginId = $("#id").val().trim();
		let password = $("#password").val();
		
		if(!loginId) {
			$("#signInModal").modal();
			$("#modalBody").text("아이디를 입력해주세요.");
			return;
		}
		if(!password) {
			$("#signInModal").modal();
			$("#modalBody").text("비밀번호를 입력해주세요.");
			return;
		}
		
		$.ajax({
			type:"POST"
			, url:"/user/sign_in"
			, data:{"loginId":loginId, "password":password}
		
			, success:function(data){
				if(data.code == 1){
					location.href="/main/main_view";
				} else if (data.code == 300) {
					$("#signInModal").modal();
					$("#modalBody").text(data.errorMessage);
					
					$('#signInModal').on('hidden.bs.modal', function (e) {
					     location.reload();
					})
				}
			}
			, error : function(request, status, error) {
				$("#signInModal").modal();
				$("#modalBody").text("로그인에 실패했습니다.");
				return;
			}
		})
	});
});
</script>