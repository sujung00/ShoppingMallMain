<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container m-5">
	<h2>상품 수정</h2>
	
	<div>
		<div>상품명</div>
		<input type="text" id="name" class="form-control col-5" value="${product.name}">
	</div>
	<div>
		<div>상품 설명</div>
		<input type="text" id="information" class="form-control col-5" value="${product.information}">
	</div>
	<div>
		<div>가격</div>
		<input type="text" id="price" class="form-control col-5" value="${product.price}">
	</div>
	<div>
		<div>대표 이미지</div>
		<input type="file" id="mainImage" accept=".jpg, .jpeg, .png, .gif">
	</div>
	<div>
		<div>상세 설명</div>
		<textarea rows="5" cols="50" id="detailedInfo">${product.detailedInfo}</textarea>
	</div>
	<div>
		<div>성별</div>
		<c:if test="${product.gender eq 'man'}">
			<input type="radio" name="gender" id="man" value="man" checked>
			<label for="man">남성</label>
			<input type="radio" name="gender" id="woman" value="woman">
			<label for="woman">여성</label>
		</c:if>
		<c:if test="${product.gender eq 'woman'}">
			<input type="radio" name="gender" id="man" value="man">
			<label for="man">남성</label>
			<input type="radio" name="gender" id="woman" value="woman" checked>
			<label for="woman">여성</label>
		</c:if>
	</div>
	<button type="button" id="updateBtn" class="btn btn-dark">상품 수정</button>
</div>

<script>
$(document).ready(function(){
	$("#updateBtn").on("click", function(){
		let name = $("#name").val().trim();
		let information = $("#information").val().trim();
		let price = $("#price").val().trim();
		let mainImage = $("#mainImage").val();
		let detailedInfo = $("#detailedInfo").val();
		let gender = $("input[type=radio][name=gender]:checked").val();
		
		if(!name){
			alert("상품명을 입력해주세요.");
			return;
		}
		if(!information){
			alert("상품 설명을 입력해주세요.");
			return;
		}
		if(!price){
			alert("가격을 입력해주세요.");
			return;
		}
		if(!mainImage){
			alert("대표 이미지를 선택해주세요.");
			return;
		}
		if(mainImage){
			let ext = mainImage.split(".").pop().toLowerCase();
			if ($.inArray(ext, [ 'jpg', 'jpeg', 'png', 'gif' ]) == -1) {
				alert("이미지 파일만 업로드 할 수 있습니다.");
				$("#mainImage").val("");
				return;
			}
		}
		if(!detailedInfo){
			alert("상세 설명을 입력해주세요.");
			return;
		}
		if(!gender) {
			alert("성별을 선택해주세요.");
			return;
		}
		
		// 서버 AJAX
		// 이미지를 업로드 할 때는 form태그가 반드시 있어야 한다.
		// append함수는 폼태그의 name 속성과 같다.
		let formData = new FormData();
		formData.append("name", name);
		formData.append("information", information);
		formData.append("price", price);
		formData.append("mainImage", $('#mainImage')[0].files[0]);
		formData.append("detailedInfo", detailedInfo);
		formData.append("gender", gender);
		
		$.ajax({
			type:"POST"
			, url:"/product_admin/product_update"
			, data:formData
			, enctype:"multipart/form-data"  // 파일 업로드를 위한 필수 설정
			, processData:false // 파일 업로드를 위한 필수 설정
			, contentType:false // 파일 업로드를 위한 필수 설정		
			
			, success:function(data){
				if(data.code == 1){
					alert(data.result);
					location.href="/product/product_list_view";
				} else {
					alert(data.errorMessage);
					return;
				}
			}
			, error:function(request, status, error) {
				alert("상품 수정 실패");
			}
		});
	});
});
</script>