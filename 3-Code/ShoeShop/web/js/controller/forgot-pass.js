var roles = [{ role: '' }];

var accounts = [];
var params;
$(document).ready(function () {
    $('#form-login').submit(false);

    params = 'params={"code":""}';
    callAjax('GET', 'account', params, loadUsers);

    $('#btn-login').click(function () {
        var user = $('.user').val();
        var passLevel2 = $('.passLevel2').val();
        var pass = $('.pass').val();
        var boo = false;

        for (var i in accounts) {
            if ((user === accounts[i].user) && (passLevel2 === accounts[i].passLv2)) {
                accounts[i].pass = pass;
                boo = true;
                callAjax('PUT', 'account', JSON.stringify(accounts[i]), console.log);
            }
        }
        if (boo) {
            window.location.replace(constants.urlServer + 'login.html');
        } else {
            alert("wrong user or pass level 2");
        }
    })
});

function loadUsers(values) {
    accounts = values;
}