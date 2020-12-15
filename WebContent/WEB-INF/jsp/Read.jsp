<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div>
	<a href="/List?typ=${data.typ }">리스트로 돌아가기</a>
	<form action = "/Read" method="post" onsubmit="delConfirm(e)">
		<input type="hidden" name="typ" value="${data.typ }">
		<input type="hidden" name="i_board" value="${data.i_board }">
		<input type="submit" value="삭제하기">
	</form>
	
	<a href="/regMod?typ=${data.typ }&i_board=${data.i_board}">
		<button>수정하기</button>
	</a>
	
	<div>
		번호 : ${data.i_board } / 조회수 : ${data.hits }<br/>
		제목 : ${data.title } <br/>
		<div>
			내용 : ${data.ctnt }
		</div>
		작성날짜 : ${data.r_dt }
	</div>
	
	<div style="margin-top:10px;">
		<form action="/cmt" method="post">
			<input type="hidden" name="typ" value="${data.typ }">
			<input type="hidden" name="i_board" value="${data.i_board }">
			댓글 : <input type="text" name="cmt_ctnt">
			<input type="submit" value="댓글쓰기">
		</form>
	</div>
	<div style="margin-top:5px;">
		<table>
			<tr>
				<th>댓글 목록</th>
			</tr>
			<c:forEach items="${cmtCtnt }" var="item">
				<tr>
					<td>${item.ctnt }</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</div>

<script>
	function delConFirm(e){
		var result = confirm('정말 삭제하시겠습니까?');
		if(!result){
			e.preventDefault();
		}
	}
	
	<c:if test="${msg != null}">
		alert('${msg}');
	</c:if>
</script>