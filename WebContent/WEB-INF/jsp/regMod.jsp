<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<h1>${data == null ? '글등록' : '글수정' }</h1>
<form action="/regMod" method="post" id="regModFrm" onsubmit="return check();">
	<input type="hidden" name="typ" value="${typ}">
	<input type="hidden" name="i_board" value="${data.i_board }">
	제목 : <input type="text" name="title" value="${data.title }"><br/>
	내용 : <textarea name="ctnt">${data.ctnt }</textarea>
	<input type="submit" value="${data == null ? '등록하기' : '수정하기' }">
</form>

<script>
	function check(){
		if(insertConfirm(regModFrm.title, '제목') || insertConfirm(regModFrm.ctnt, '내용')){
			return false;
		}
	}
	
	<c:if test="${err != null}">
		alert('${err}');
	</c:if>
</script>