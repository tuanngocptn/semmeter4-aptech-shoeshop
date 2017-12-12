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

