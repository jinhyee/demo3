function printForm(){
    if ($("#username").val() == ""){
        alert("아이디를 입력하세요");
        login-form.id.focus();
        return;
    }

    if($("#password1").val() == ""){
        alert("비밀번호를 입력하세요.");
        login-form.password.focus();
        return;
    }

    login-form.submit();
}