<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<link rel="stylesheet" href="res/css/List.css">

<div>
	<table>
		<tr>
			<td>번호</td>
			<td>제목</td>
			<td>작성날짜</td>
			<td>조회수</td>
		</tr>
		
		<c:forEach items="${lists}" var="lists">
			<tr class="pointer" onclick="clickItem(${typ}, ${lists.i_board});">
				<td>${lists.i_board }</td>
				<td>${lists.title }</td>
				<td>${lists.r_dt }</td>
				<td>${lists.hits }</td>
			</tr>
		</c:forEach>
	</table>
	
	<div class="pageContainer">
		<c:forEach begin="1" end="${pageCnt}" var="i">
			<span class="page">
				<a href="/List?typ=${typ}&page=${i}">${i}</a>
			</span>
		</c:forEach>
	</div>
	
	<div>
		<a href="/regMod?typ=${typ}"><button>글쓰기</button></a>
	</div>
</div>

<script>
	function clickItem(typ, i_board){
		var url = `/Read?typ=\${typ}&i_board=\${i_board}`;
		/* == '/Read?typ=' + typ + '&i_board=' + i_board */
				
		location.href= url;
	}
</script>