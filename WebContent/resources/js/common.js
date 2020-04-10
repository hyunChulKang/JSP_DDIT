function OpenWindow(UrlStr, WinTitle, WinWidth, WinHeight){
	winleft =(screen.width - WinWidth) /2;
	wintop = (screen.height - WinHeight) /2;
	var win = window.open(UrlStr, WinTitle, "scrollbars=yes, width=" + WinWidth +", " +"height="+ WinHeight +", top=" +wintop
							+", left=" +winleft +", resizable=yes, status=yes");
	win.focus();
}

function CloseWindow(){
	window.opener.location.reload(true);
	window.close();
}

function SubmitMemberRegist(formRole){
	alert(formRole+" submit !");
	var uploadCheck = $('input[name="checkUpload"]').val();
	if(!(uploadCheck>0)){
		alert("업로드한 사진이 없습니다.")
		//$('input[name="pictureFile"]').click();
		return;
}

	var form = $('form[role="'+formRole+'"]');
	form.submit();
}

function SubmitModify(form){
	alert(form +"submit!!");
	var forms = $('form[role="'+form+'"]');
	forms.submit();
}