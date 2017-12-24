var user = localStorage.user;
if(typeof user != "undefined"){
    user = JSON.parse(user);
}
$(document).ready(function () {
    checkRole();
    if(typeof user == "undefined") {
        $(".login-status-area").html(getLoginArea())
    }else{
        $(".login-status-area").html(getLogoutArea())
    }
    
});
function callAjax(method,url,params,callback) {
    $.ajax({
        url: constants.urlServer + url,
        type: method,
        data: params,
        dataType: 'json',
        success: function (response) {
            callback(response);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            console.log(textStatus, errorThrown);
        }
    });
}

var constants = {
    urlServer : 'http://localhost:8080/ShoeShop/',
}

function getFormData($form){
    var unindexed_array = $form.serializeArray();
    var indexed_array = {};

    $.map(unindexed_array, function(n, i){
        indexed_array[n['name']] = n['value'];
    });

    return indexed_array;
}

function dateToMiliSecond(value){
    var date = new Date(value);
    return date.getTime();
}

function miliSecondToDate(mili){
    date = new Date(mili);
    date = (date.getMonth() + 1) + '/' + date.getDate() + '/' + date.getFullYear();
    return date;
}

function getLoginArea(){
    $(".btn-register").show();
    $(".btn-login").show();
}

function getLogoutArea(){
    $(".btn-logout").show();
}

function checkRole(){
    if(typeof roles == 'undefined'){
        window.location.replace(constants.urlServer + 'index.html');
    }
    var userRole = '';
    if(typeof user != 'undefined'){
        userRole = user.roleCode;
    }
    var boo = true;
    roles.forEach(role => {
        if(role.role == userRole){
            boo = false
        }
    });
    if(boo){
        window.location.replace(constants.urlServer + 'index.html');
    }
}