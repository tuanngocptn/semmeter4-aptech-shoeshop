var roles = [{ role: 'adm' }];
var startDate = 0;
var endDate = 0;
var date = new Date();
$(document).ready(function () {
    loadData();
    $('input[type="date"]').change(function(){
        search();
    });
});

function loadData() {
    endDate = date.getTime();
    startDate = endDate - 86400000;
    var param = 'params={"startDate":' + startDate + ',"endDate":' + endDate + '}';
    callAjax("GET", 'report', param, loadDay);
}

function loadDay(value) {
    $('.today-revernue').text('$' + value.revenue);
    $('.today-product').text(value.totalProduct)
    $('.today-order').text(value.totalOrder)
    
    startDate = endDate - 604800000;
    var param = 'params={"startDate":' + startDate + ',"endDate":' + endDate + '}';
    callAjax("GET", 'report', param, loadWeek);
}

function loadWeek(value){
    $('.weekly-revernue').text('$' + value.revenue);
    $('.weekly-product').text(value.totalProduct)
    $('.weekly-order').text(value.totalOrder)

    startDate = endDate - 2592000000;
    var param = 'params={"startDate":' + startDate + ',"endDate":' + endDate + '}';
    callAjax("GET", 'report', param, loadMonth);
}

function loadMonth(value){
    $('.monthly-revernue').text('$' + value.revenue);
    $('.monthly-product').text(value.totalProduct)
    $('.monthly-order').text(value.totalOrder)
    callAjax("POST", 'report', "", load5Product);
}

function load5Product(products){
    $('.load5product').empty();
    products.forEach(element => {
        $('.load5product').append(getProduct(element));
    });
}

function getProduct(element){
    return  '<tr>' +
                '<td>'+ element.code +'</td>' +
                '<td>'+ element.name +'</td>' +
                '<td>'+ element.price +'</td>' +
            '</tr>';
}

function search(){
    var fromDate = document.getElementById("from-date").value;
    var toDate =  document.getElementById("to-date").value; 
    if(fromDate == ''){
        fromDate = 'null';
    }else{
        fromDate = new Date(fromDate);
        fromDate = fromDate.getTime();
    }
    if(toDate == ''){
        toDate = 'null';
    }else{
        toDate = new Date(toDate);
        toDate = toDate.getTime();
    }
    var param = 'params={"startDate":' + fromDate + ',"endDate":' + toDate + '}';
    callAjax("GET", 'report', param, loadSearch);
}

function loadSearch(value){
    $('.search-revernue').text('$' + value.revenue);
    $('.search-product').text(value.totalProduct)
    $('.search-order').text(value.totalOrder)
}