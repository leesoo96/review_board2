<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${title}</title>
<link rel="stylesheet" href="/res/css/common.css">
</head>
<body>
<div id="container">
	<header>
		<ul>
			<li><a href="/List?typ=1">목록1</a></li>
			<li><a href="/List?typ=2">목록2</a></li>
			<li><a href="/List?typ=3">목록3</a></li>
		</ul>
	</header>
	
	<section>
		<jsp:include page="${page}"/>
	</section>
	
	<footer>
	
	</footer>
</div>
</body>
</html>