<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="container pt-4">
	<div class="font2 my-3">주문서</div>
	<div class="d-flex">
		<!-- 주문서 -->
		<div class="order-div">
			<!-- 1. 주문 고객 정보 -->
			<div class="font1">1. 주문 고객 정보</div>
			<hr class="bg-dark">
			<div class="my-2 font5">이름</div>
			<div class="my-2 font5">전화번호</div>
			<div class="my-2 font5">이메일</div>

			<!-- 2. 배송지 정보 정보 -->
			<div class="font1 mt-5">2. 배송지 정보</div>
			<hr class="bg-dark">
			<div>
				<div class="d-flex">
					<div class="custom-control custom-radio">
						<input type="radio" id="addressList" name="addressType"
							class="form-check-input" value="list" checked> <label
							for="addressList" class="mytrend-font3">배송지 목록</label>
					</div>
					<div class="custom-control custom-radio ml-5">
						<input type="radio" id="newAddress" name="addressType"
							class="form-check-input" value="create"> <label
							for="newAddress" class="mytrend-font3">배송지 생성</label>
					</div>
				</div>


				<!-- 배송지 목록 -->
				<div id="adlist" class="address-list mt-2">
					<div class="font1 mb-2">기본 배송지</div>
					<c:forEach items="${defualAddress}" var="defualAddress">
						<c:if test="${not empty defualAddress}">
							<button type="submit" class="address-btn d-flex mb-3">
								<div class="address-table">
									<div>이름</div>
									<div class="mt-1">휴대폰 번호</div>
									<div class="mt-1">추가 연락처</div>
									<div class="mt-1">주소</div>
								</div>
								<div class="address-data">
									<div>${defualAddress.name}</div>
									<div class="mt-1">${defualAddress.phoneNumber}</div>
									<c:if test="${not empty defualAddress.extraPhoneNumber}">
										<div class="mt-1">${defualAddress.extraPhoneNumber}</div>
									</c:if>
									<c:if test="${empty defualAddress.extraPhoneNumber}">
										<div class="mt-1">추가 연락처가 없습니다.</div>
									</c:if>
									<div class="mt-1">${defualAddress.address}
										${defualAddress.detailedAddress}</div>
								</div>
							</button>
						</c:if>
					</c:forEach>

					<div class="mytrend-font2 mt-4 mb-2">배송지 목록</div>
					<c:forEach items="${addressList}" var="address">
						<form action="/address/address_update_view" method="post">
							<input type="hidden" name="addressId" value="${address.id}">
							<button type="submit" class="address-btn d-flex mb-3">
								<div class="address-table">
									<div>이름</div>
									<div class="mt-1">휴대폰 번호</div>
									<div class="mt-1">추가 연락처</div>
									<div class="mt-1">주소</div>
								</div>
								<div class="address-data">
									<div>${address.name }</div>
									<div class="mt-1">${address.phoneNumber }</div>
									<c:if test="${not empty address.extraPhoneNumber}">
										<div class="mt-1">${address.extraPhoneNumber}</div>
									</c:if>
									<c:if test="${empty address.extraPhoneNumber}">
										<div class="mt-1">추가 연락처가 없습니다.</div>
									</c:if>
									<div class="mt-1">${address.address}
										${address.detailedAddress}</div>
								</div>
							</button>
						</form>
					</c:forEach>
					<div class="d-flex mt-3">
						<input type="button" id="registerAddressBtn" value="확인"
							class="mytrend-btn">
					</div>
				</div>

				<!-- 배송지 새로 입력 -->
				<div id="newad" class="new-address mt-4 d-none">
					<div>
						<div class="mytrend-font2">이름</div>
						<div class="mytrend-input">
							<input type="text" id="name">
						</div>
					</div>
					<div class="mt-2">
						<div class="mytrend-font2">전화번호</div>
						<div class="mytrend-input">
							<input type="text" id="phoneNumber"
								placeholder="'-' 없이 숫자만 입력하세요.">
						</div>
					</div>
					<div class="mt-2">
						<div class="mytrend-font2">추가 연락처(선택)</div>
						<div class="mytrend-input">
							<input type="text" id="extraPhoneNumber"
								placeholder="'-' 없이 숫자만 입력하세요.">
						</div>
					</div>
					<div class="mt-2">
						<div class="mytrend-font2">배송지 주소</div>
						<div class="address-input d-flex align-items-center">
							<input type="text" id="sample6_postcode" readonly="readonly">
							<button type="button" id="findAddressBtn" class="ml-2"
								onclick="sample6_execDaumPostcode()">찾 기</button>
						</div>
						<div class="address-input2">
							<input type="text" id="sample6_address" readonly="readonly">
						</div>
						<div class="address-input2">
							<input type="text" id="sample6_detailAddress">
						</div>
					</div>
					<div class="mt-1">
						<input type="checkbox" id="defaultAddress"> <label
							for="defaultAddress" class="mytrend-font3">기본 배송지 설정</label>
					</div>
					<div class="d-flex mt-3">
						<input type="button" id="registerAddressBtn" value="확인"
							class="mytrend-btn">
					</div>
				</div>
			</div>
			<div class="mt-5">
				<div class="font1">배송 시 요청사항</div>
				<select name="colorOption" class="custom-select my-2"
					id="colorOption">
					<option value="0" selected class="mytrend-font3">Choose...</option>
					<option class="mytrend-font3">배송 전 연락 바랍니다.</option>
					<option class="mytrend-font3">부재 시, 휴대폰으로 연락주세요.</option>
					<option class="mytrend-font3">부재 시, 경비실에 맡겨주세요.</option>
				</select> <input type="text" placeholder="기타 내용을 입력해주세요."
					class="form-control">
				<div class="d-flex justify-content-end font6 text-dark">최대 30자</div>
				<div class="font6 text-dark mt-5">주문 시 입력한 배송지는 자동으로 배송지 목록에
					추가됩니다.</div>
			</div>

			<!-- 3. 포인트 -->
			<div class="font1 mt-5">3. 포인트 사용</div>
			<hr class="bg-dark">
			<div>
				<div class="font5">포인트</div>
				<div class="d-flex align-items-center">
					<input type="number" class="col-9 form-control"> <input
						type="button" class="text-btn font6" value="모두사용">
					<div class="point-text ml-1">3000원</div>
				</div>
			</div>

			<!-- 4. 결제-->
			<div class="font1 mt-5">4. 결제</div>
			<hr class="bg-dark">
			<div class="d-flex align-items-center">
				<input type="radio" name="payType" id="creditCard"> <label
					for="creditCard" class="font5 m-0 ml-1">신용카드</label>
			</div>
			<div class="d-flex align-items-center mt-1">
				<input type="radio" name="payType" id="withoutBankBook"> <label
					for="withoutBankBook" class="font5 m-0 ml-1">무통장 입금</label>
			</div>
			<div class="d-flex align-items-center mt-1">
				<input type="radio" name="payType" id="phonePayment"> <label
					for="phonePayment" class="font5 m-0 ml-1">휴대폰 결제</label>
			</div>
			<div class="d-flex align-items-center mt-1">
				<input type="radio" name="payType" id="naverPay"> <label
					for="naverPay" class="font5 m-0 ml-1">네이버 페이</label>
			</div>

			<div class="d-flex align-items-center mt-5 pt-5">
				<input type="checkbox" name="orderCheck" id="orderCheck"> <label
					for="orderCheck" class="font6 m-0 ml-1">주문하실 상품의 상품명, 가격,
					배송정보를 확인하였으며, 이에 동의합니다.</label>
			</div>

			<div class="d-flex mt-3">
				<input type="button" id="orderBtn" value="결제하기" class="mytrend-btn">
			</div>
		</div>
		<!-- 주문 상품 -->
		<div class="order-div"></div>
	</div>
</div>

<script
	src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
	function sample6_execDaumPostcode() {
		new daum.Postcode(
				{
					oncomplete : function(data) {
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
						if (data.userSelectedType === 'R') {
							// 법정동명이 있을 경우 추가한다. (법정리는 제외)
							// 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
							if (data.bname !== ''
									&& /[동|로|가]$/g.test(data.bname)) {
								extraAddr += data.bname;
							}
							// 건물명이 있고, 공동주택일 경우 추가한다.
							if (data.buildingName !== ''
									&& data.apartment === 'Y') {
								extraAddr += (extraAddr !== '' ? ', '
										+ data.buildingName : data.buildingName);
							}
							// 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
							if (extraAddr !== '') {
								extraAddr = ' (' + extraAddr + ')';
							}
						} else {
							document.getElementById("sample6_address").value = '';
						}

						// 우편번호와 주소 정보를 해당 필드에 넣는다.
						document.getElementById('sample6_postcode').value = data.zonecode;
						document.getElementById("sample6_address").value = addr
								+ extraAddr;
						// 커서를 상세주소 필드로 이동한다.
						document.getElementById("sample6_detailAddress")
								.focus();
					}
				}).open();
	}

	$(document).ready(function() {
		$('input[type=radio][name=addressType]').change(function() {
			var value = $("input[type=radio][name=addressType]:checked").val();
			if (value == "list") {
				$("#adlist").removeClass("d-none");
				$("#newad").addClass("d-none");
			} else if (value == "create") {
				$("#newad").removeClass("d-none");
				$("#adlist").addClass("d-none");
			}
		});

		$("#cancelBtn").on("click", function() {
			location.href = "/address/address_view";
		});

		$("#registerAddressBtn").on("click", function() {
			let name = $("#name").val().trim();
			let phoneNumber = $("#phoneNumber").val().trim();
			let extraPhoneNumber = $("#extraPhoneNumber").val().trim();
			let postcode = $("#sample6_postcode").val();
			let address = $("#sample6_address").val();
			let detailedAddress = $("#sample6_detailAddress").val().trim();
			let defaultAddress = $('#defaultAddress').is(':checked'); // true or false

			if (!name) {
				$("#addressModal").modal();
				$("#modalBody").text("이름을 입력해주세요.");
				return;
			}
			if (!phoneNumber) {
				$("#addressModal").modal();
				$("#modalBody").text("전화번호를 입력해주세요.");
				return;
			}
			if (phoneNumber.includes("-")) {
				$("#addressModal").modal();
				$("#modalBody").text("-없이 숫자만 입력해주세요.");
				return;
			}
			if (extraPhoneNumber.includes("-")) {
				$("#addressModal").modal();
				$("#modalBody").text("-없이 숫자만 입력해주세요.");
				return;
			}
			if (!postcode) {
				$("#addressModal").modal();
				$("#modalBody").text("우편번호를 입력해주세요.");
				return;
			}
			if (!detailedAddress) {
				$("#addressModal").modal();
				$("#modalBody").text("상세 주소를 입력해주세요.");
				return;
			}

			$.ajax({
				type : "POST",
				url : "/address/address_create",
				data : {
					"name" : name,
					"phoneNumber" : phoneNumber,
					"extraPhoneNumber" : extraPhoneNumber,
					"postcode" : postcode,
					"address" : address,
					"detailedAddress" : detailedAddress,
					"defaultAddress" : defaultAddress
				}

				,
				success : function(data) {
					if (data.code == 1) {
						$("#addressModal").modal();
						$("#modalBody").text(data.result);

						$('#addressModal').on('hidden.bs.modal', function(e) {
							location.href = "/address/address_view";
						})
					} else {
						$("#addressModal").modal();
						$("#modalBody").text(data.errorMessage);

						$('#addressModal').on('hidden.bs.modal', function(e) {
							location.reload();
						})
					}
				},
				error : function(request, status, error) {
					$("#addressModal").modal();
					$("#modalBody").text("배송지 생성에 실패했습니다.");
					return;
				}
			});

		});
	});
</script>