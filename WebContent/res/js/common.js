'use strict';

function insertConfirm(element, elementName){
	if(element.value == ''){
		alert(elementName + '은(는) 필수입력사항입니다.');
		element.focus();
		return true;
	}
	
	return false;
}