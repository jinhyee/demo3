function passwordConfirm(){
    var pw1=document.getElementById('password1').value;
    var pw2=document.getElementById('password2').value;

    if(pw1!=pw2){
        alert('비밀번호를 다시 확인하세요');
        signup-form.password2.focus();
        return;
    }
}






