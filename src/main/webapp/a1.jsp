<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath }/style/main.css"/>
<script type="text/javascript" src="${pageContext.request.contextPath }/script/MyApp.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/script/Ajax.js"></script>
<script type="text/javascript">

</script>
</head>
<body>
${name }
<br/>
	a1.jsp
	<br/>
	<a href="#" onclick='MyApp.asynrequest("post","${pageContext.request.contextPath }/test",aa,"sex=male")'>cick me</a>
	<br/>
	<a href="#" onclick='aa()'>cick me2</a>
</body>
</html>