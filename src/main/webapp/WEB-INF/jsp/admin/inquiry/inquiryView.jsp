<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<h2 class="m-5">문의 사항</h2>
<div class="ml-5 mt-3">
	<!-- 문의 내역 -->	
	<c:forEach items="${inquiryViewList}" var="inquiryView">
	<c:if test="${empty inquiryView.answer}">
		<div class="text-danger mt-4"><b>답변 대기중</b></div>
	</c:if>
	<c:if test="${not empty inquiryView.answer}">
		<div class="text-warning mt-4"><b>답변 완료</b></div>
	</c:if>
	<div class="d-flex align-items-center inquiry-border justify-content-between p-2 col-6">
		<div class="address-input-div">
			<div class="address-input-div"><b>작성일</b> - 
			<fmt:formatDate value="${inquiryView.inquiry.createdAt}" pattern="yy.MM.dd hh:mm:ss"/>
			</div>
			<div class="address-input-div mt-3"><b>제목</b> - ${inquiryView.inquiry.subject}</div>
			<div class="address-input-div"><b>내용</b> - ${inquiryView.inquiry.content}</div>
		</div>
		<div class="address-input-div">
			<c:if test="${not empty inquiryView.answer}">
			<textarea rows="4" class="address-input-div">${inquiryView.answer.content}</textarea>
			<button type="button" class="inquiry-update-btn btn btn-dark" data-answer-id="${inquiryView.answer.id}">수정 하기</button>
			</c:if>
			<c:if test="${empty inquiryView.answer}">
			<textarea rows="4" class="address-input-div"></textarea>
			<button type="button" class="inquiry-btn btn btn-dark" data-inquiry-id="${inquiryView.inquiry.id}">답변 하기</button>
			</c:if>
		</div>
	</div>
	</c:forEach>
</div>

<script>
$(document).ready(function(){
	$(".inquiry-btn").on("click", function(){
		let inquiryId = $(this).data("inquiry-id");
		let content = $(this).prev().val();
		
		if(!content){
			alert("답변을 작성해주세요.");
			return;
		}
		
		$.ajax({
			type:"POST"
			, url:"/inquiry_admin/answer_create"
			, data:{"inquiryId":inquiryId, "content":content}
			
			, success:function(data){
				if(data.code == 1){
					alert(data.result);
					location.reload();
				} else {
					alert(data.errorMessage);
					return;
				}
			}
			, error : function(request, status, error) {
				alert("문의 답변 작성에 실패했습니다.");
				return;
			}
		})
	})
	
	$(".inquiry-update-btn").on("click", function(){
		let answerId = $(this).data("answer-id");
		let content = $(this).prev().val();
		
		if(!content){
			alert("답변을 작성해주세요.");
			return;
		}
		
		$.ajax({
			type:"POST"
			, url:"/inquiry_admin/answer_update"
			, data:{"answerId":answerId, "content":content}
			
			, success:function(data){
				if(data.code == 1){
					alert(data.result);
					location.reload();
				} else {
					alert(data.errorMessage);
					return;
				}
			}
			, error : function(request, status, error) {
				alert("문의 답변 수정에 실패했습니다.");
				return;
			}
		})
	})
})
</script>