<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="sign-box">
	<div class="sign-text text-center mb-3">아이디 찾기</div>
	<hr>
	<div> 
		<div class="sign-in-div d-flex justify-content-center">
			<input type="text" placeholder="name" id="name">
		</div>
		<div class="sign-div d-flex justify-content-center">
			<input type="text" placeholder="email" id="email">
		</div>
		<div class="d-flex justify-content-center">
			<button type="button" id="findIdBtn" class="sign-btn mt-3">아이디 찾기</button>
		</div>
	</div>
</div>

<div class="modal" tabindex="-1" id="findIdModal">
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
	$("#findIdBtn").on("click", function(){
		let name = $("#name").val().trim();
		let email = $("#email").val().trim();
		
		if(!name){
			$("#findIdModal").modal();
			$("#modalBody").text("이름을 입력해주세요.");
			
			return;
		}
		if(!email){
			$("#findIdModal").modal();
			$("#modalBody").text("이메일을 입력해주세요.");
			
			return;
		}
		
		$.ajax({
			type:"POST"
			, url:"/user/find_id"
			, data:{"name":name, "email":email}
		
			, success:function(data){
				if(data.code == 1){
					$("#modalBody").text("아이디 : " + data.loginId);
					$("#findIdModal").modal();
					
					$('#findIdModal').on('hidden.bs.modal', function (e) {
					     location.href="/user/sign_in_view";
					})
				} else {
					$("#findIdModal").modal();
					$("#modalBody").text(errorMessage);
					
					return;
				}
			}
			, error : function(request, status, error) {
				$("#findIdModal").modal();
				$("#modalBody").text("아이디 찾기에 실패했습니다.");
				return;
			}
		})
	});
});
</script>