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
	function asyns(data){
		var ele = document.getElementById("target");
		ele.innerHTML=data;
	}
	function aa(data){
		alert('HEHE')
	}
</script>
<style type="text/css">
	#test:before{
		content: "wo shi yige content";
		color:red
	}
</style>
</head>
<body>
	<div class="container">
		<div class="c_west">
			<ul>
				<li><a href="#" onclick='MyApp.asynrequest("get","${pageContext.request.contextPath }/test",asyns)'>a</a></li>
				<li><a href="#">a1</a></li>
				<li><a href="#">a2</a></li>
				<li><a href="#">a3</a></li>
				<li><a href="#">a3</a></li>
			</ul>
		</div>
		<div class="c_east" id="target">
			<%-- <jsp:include page="main.jsp">
				<jsp:param value="tangyinbo" name="name"/>
			</jsp:include> --%>
			<Video ></Video>
		</div>
	</div>
</body>
</html>