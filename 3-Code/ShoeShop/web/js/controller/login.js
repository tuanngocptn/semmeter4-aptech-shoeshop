$( document ).ready(function() {
    $('#form-login').submit(false);

    $('#btn-login').click(function(){
        var params = $('#form-login').serialize();
        callAjax('POST','login',params,loginMethod);
    })
});

function loginMethod(user){
    if(JSON.stringify(user) !== JSON.stringify({})){
        localStorage.setItem("user", JSON.stringify(user));
        location.reload();
    }else{
        localStorage.removeItem("user");
    }
}