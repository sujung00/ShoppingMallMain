<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ShoppingMall</title>
</head>
<body>
<h2>쇼핑몰 테스트</h2>
<c:if test="${not empty userId}">
<div>${userId}</div>
<div>${userId}</div>
</c:if>
</body>
</html>