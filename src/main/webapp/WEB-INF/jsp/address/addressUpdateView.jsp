<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<div class="d-flex">
	<div class="mytrend-nav">
		<nav class="nav flex-column">
		  <a class="nav-link" href="/user/profile_view">회원정보</a>
		  <a class="nav-link" href="#">주문/배송</a>
		  <a class="nav-link" href="#">문의내역</a>
		  <a class="nav-link" href="/address/address_view">배송지 관리</a>
		  <a class="nav-link" href="#">예치금</a>
		</nav>
	</div>
	
	<!-- content -->
	<div class="content">
		<div class="mytrned-logo">배송지 정보 수정</div>
		<div class="mt-5">
			<!-- 배송지 새로 입력 -->
			<div id="newad" class="new-address mt-4">
				<div>
					<div class="mytrend-font2">이름</div>
					<div class="mytrend-input">
						<input type="text" id="name" value="${address.name}">
					</div>
				</div>
				<div class="mt-2">
					<div class="mytrend-font2">전화번호</div>
					<div class="mytrend-input">
						<input type="text" id="phoneNumber" placeholder="'-' 없이 숫자만 입력하세요." value="${address.phoneNumber}">
					</div>
				</div>
				<div class="mt-2">
					<div class="mytrend-font2">추가 연락처(선택)</div>
					<div class="mytrend-input">
						<input type="text" id="extraPhoneNumber" placeholder="'-' 없이 숫자만 입력하세요." value="${address.extraPhoneNumber}">
					</div>
				</div>
				<div class="mt-2">
					<div class="mytrend-font2">배송지 주소</div>
					<div class="address-input d-flex align-items-center">
						<input type="text" id="sample6_postcode" value="${address.postcode}">
						<button type="button" id="findAddressBtn" class="ml-2" onclick="sample6_execDaumPostcode()">찾 기</button>
					</div>
					<div class="address-input">
						<input type="text" id="sample6_address" value="${address.address}">
					</div>
					<div class="address-input">
						<input type="text" id="sample6_detailAddress" value="${address.detailedAddress}">
					</div>
				</div>
				<div class="mt-1">
					<input type="checkbox" id="defaultAddress">
					<label for="defaultAddress" class="mytrend-font3">기본 배송지 설정</label>
				</div>
				<div class="d-flex mt-3">
					<input type="button" id="cancelBtn" value="취소" class="mytrend-btn">
					<input type="button" id="registerAddressBtn" value="수정 완료" class="mytrend-btn ml-2" data-address-id="${address.id}">
				</div>
			</div>
		</div>
	</div>
</div>

<div class="modal" tabindex="-1" id="addressUpdateModal">
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

<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
    function sample6_execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var addr = ''; // 주소 변수
                var extraAddr = ''; // 참고항목 변수

                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }

                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                if(data.userSelectedType === 'R'){
                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있고, 공동주택일 경우 추가한다.
                    if(data.buildingName !== '' && data.apartment === 'Y'){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                    if(extraAddr !== ''){
                        extraAddr = ' (' + extraAddr + ')';
                    }
                } else {
                    document.getElementById("sample6_address").value = '';
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('sample6_postcode').value = data.zonecode;
                document.getElementById("sample6_address").value = addr + extraAddr;
                // 커서를 상세주소 필드로 이동한다.
                document.getElementById("sample6_detailAddress").focus();
            }
        }).open();
    }
    
    $(document).ready(function(){
    	$("#registerAddressBtn").on("click", function(){
    		let addressId = $(this).data("address-id");
    		let name = $("#name").val().trim();
			let phoneNumber = $("#phoneNumber").val().trim();
			let extraPhoneNumber = $("#extraPhoneNumber").val().trim();
			let postcode = $("#sample6_postcode").val();
			let address = $("#sample6_address").val();
			let detailedAddress = $("#sample6_detailAddress").val().trim();
			let defaultAddress = $('#defaultAddress').is(':checked'); // true or false
			
			if(!name) {
				$("#addressUpdateModal").modal();
				$("#modalBody").text("이름을 입력해주세요.");
				return;
			}
			if(!phoneNumber) {
				$("#addressUpdateModal").modal();
				$("#modalBody").text("전화번호를 입력해주세요.");
				return;
			}
			if(phoneNumber.includes("-")){
				$("#addressUpdateModal").modal();
				$("#modalBody").text("-없이 숫자만 입력해주세요.");
				return;
			}
			if(extraPhoneNumber.includes("-")){
				$("#addressUpdateModal").modal();
				$("#modalBody").text("-없이 숫자만 입력해주세요.");
				return;
			}
			if(!postcode) {
				$("#addressUpdateModal").modal();
				$("#modalBody").text("우편번호를 입력해주세요.");
				return;
			}
			if(!detailedAddress) {
				$("#addressUpdateModal").modal();
				$("#modalBody").text("상세 주소를 입력해주세요.");
				return;
			}
			
			$.ajax({
				type:"POST"
				, url:"/address/address_update"
				, data:{"addressId":addressId, "name":name, "phoneNumber":phoneNumber, "extraPhoneNumber":extraPhoneNumber,
					"postcode":postcode, "address":address, "detailedAddress":detailedAddress, "defaultAddress":defaultAddress}
				
				, success:function(data) {
					if(data.code == 1){
						$("#addressUpdateModal").modal();
						$("#modalBody").text(data.result);
						
						$('#addressUpdateModal').on('hidden.bs.modal', function (e) {
						     location.href="/address/address_view";
						})
					} else {
						$("#addressUpdateModal").modal();
						$("#modalBody").text(data.errorMessage);
						
						$('#addressUpdateModal').on('hidden.bs.modal', function (e) {
						     location.reload();
						})
					}
				}
				, error : function(request, status, error) {
					$("#addressModal").modal();
					$("#modalBody").text("배송지 생성에 실패했습니다.");
					return;
				}
			})
    	});
    });
</script>