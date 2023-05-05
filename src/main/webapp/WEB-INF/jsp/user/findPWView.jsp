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
		
		
	});
});
</script>