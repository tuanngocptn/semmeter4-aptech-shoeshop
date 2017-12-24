
var user = JSON.parse(localStorage.user);

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