<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="sign-box">
	<div class="sign-text text-center mb-3">회원 가입</div>
	<div> 
		<div class="sign-div d-flex justify-content-center">
			<input type="text" placeholder="id" id="id">
		</div>
		<div class="sign-div d-flex justify-content-center">
			<input type="password" placeholder="password" id="password">
		</div>
		<div class="sign-div d-flex justify-content-center">
			<input type="password" placeholder="password check" id="passwordCheck">
		</div>
		<div class="sign-div d-flex justify-content-center">
			<input type="text" placeholder="name" id="name">
		</div>
		<div class="sign-div d-flex justify-content-center">
			<input type="email" placeholder="email" id="email">
		</div>
		<div class="sign-div d-flex justify-content-center">
			<input type="text" placeholder="phoneNumber (-없이 숫자만 입력하세요)" id="phoneNumber">
		</div>
		<div class="d-flex justify-content-center font9 mt-4">
			*id와 email은 회원 가입 후 변경 하실 수 없습니다.
		</div>
		<div class="d-flex justify-content-center font9">
			확인 후 회원 가입 부탁드립니다.
		</div>
		<div class="d-flex justify-content-center">
			<button type="button" id="signUpBtn" class="sign-btn">SIGN UP</button>
		</div>
	</div>
</div>

<div class="modal" tabindex="-1" id="signUpModal">
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
	$("#signUpBtn").on("click", function(e){
		e.preventDefault(); // 위로 올라가는 현상 방지
		
		let loginId = $("#id").val().trim();
		let password = $("#password").val();
		let passwordCheck = $("#passwordCheck").val();
		let name = $("#name").val().trim();
		let email = $("#email").val().trim();
		let phoneNumber = $("#phoneNumber").val().trim();
		let kakaoUser = false;
		
		if(!loginId){
			$("#signUpModal").modal();
			$("#modalBody").text("아이디를 입력해주세요.");
			return;
		}
		if(loginId.length < 4){
			$("#signUpModal").modal();
			$("#modalBody").text("아이디를 4자 이상 입력해주세요.");
			return;
		}
		if(!password || !passwordCheck){
			$("#signUpModal").modal();
			$("#modalBody").text("비밀번호를 입력해주세요.");
			return;
		}
		if(password != passwordCheck){
			$("#signUpModal").modal();
			$("#modalBody").text("비밀번호를 한 번 더 확인해주세요.");
			return;
		}
		if(!name){
			$("#signUpModal").modal();
			$("#modalBody").text("이름을 입력해주세요.");
			return;
		}
		if(!email){
			$("#signUpModal").modal();
			$("#modalBody").text("이메일을 입력해주세요.");
			return;
		}
		email_regex = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/i;
		if(!email_regex.test(email)){ 
			$("#signUpModal").modal();
			$("#modalBody").text("올바른 이메일을 입력해주세요.");
			return; 
		}
		if(!phoneNumber){
			$("#signUpModal").modal();
			$("#modalBody").text("전화번호을 입력해주세요.");
			return;
		}
		if(phoneNumber.includes("-")){
			$("#signUpModal").modal();
			$("#modalBody").text("-없이 숫자만 입력해주세요.");
			return;
		}
		
		$.ajax({
			type:"POST"
			, url:"/user/sign_up"
			, data:{"loginId":loginId, "password":password, "name":name,
				"email":email, "phoneNumber":phoneNumber, "kakaoUser":kakaoUser}
			
			, success:function(data){
				if(data.code == 300){
					$("#signUpModal").modal();
					$("#modalBody").text(data.errorMessage);
					return;
				}
				else if(data.code == 301){
					$("#signUpModal").modal();
					$("#modalBody").text(data.errorMessage);
					return;
				}
				else if(data.code == 1){
					$("#signUpModal").modal();
					$("#modalBody").text(data.result);
					
					$('#signUpModal').on('hidden.bs.modal', function (e) {
					     location.href="/user/sign_in_view";
					})
				}
			}
			, error : function(request, status, error) {
				$("#signUpModal").modal();
				$("#modalBody").text("회원가입에 실패했습니다.");
				return;
			}
		})

	});
});
</script>