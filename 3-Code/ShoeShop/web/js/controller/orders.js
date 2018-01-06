var roles = [{ role: 'ctm' }];

var orders = [];
var updateCode = '';
var orderUpdate = {};
var products = [];

$(document).ready(function () {
    $('#form-login').submit(false);

    console.log(user);

    var param = 'params={"code":""}';
    callAjax("GET", 'order', param, loadData);
});

function loadData(data){
    orders = data;
    $(".tbl-order").empty();
    data.forEach(function(element,index,arr){
        if(element.accountCode == user.code){
            $(".tbl-order").append(getRow(element,index + 1));
        }
    });
    callAjax("GET", 'product', 'params={ "name":""}', getProduct);
}

function getProduct(data){
    products = data;
}

function viewOrder(element){
    var code = element.dataset.id;
    orders.forEach(order => {
        if(order.code == code){
            $('.update-info').empty();
            $('.update-info').append(getLi(order));
            loadProduct(order.lstOrderDetail);
        }
    });
}

function loadProduct(datas){
    $('.product-table').empty();
    var sum = 0;
    datas.forEach(data => {
        products.forEach(product => {
            if(product.code == data.productCode){
                product.quantity = data.quantity;
                $('.product-table').append(getTd(product));
                sum += product.quantity * product.price;
            }
        });
    });
    $('.sum-order').text('$' + sum);
}

function deleteOrder(element){
    var code = element.dataset.id;
    orders.forEach(order => {
        if(order.code == code){
            var result = order;
            result.status = "deactive";
            callAjax("PUT", 'order', JSON.stringify(result), locationReload);
        }
    });
}

function locationReload(boo){
    console.log(boo);
    window.location.reload();
}

function getTd(element){
    return  '<tr>'+
                '<td>'+ element.code +'</td>'+
                '<td>'+ element.name +'</td>'+
                '<td>'+ element.brandCode +'</td>'+
                '<td>'+ element.categoryCode +'</td>'+
                '<td>'+ element.quantity +'</td>'+
                '<td>$'+ element.price +'</td>'+
            '</tr>';
}

function getLi(order){
    return  '<tr><td>- Code: '+ order.code +'</td><td>&nbsp- Date: '+ miliSecondToDate(order.date) +'</td></tr>'+
            '<tr><td>- Customer Name: '+ order.name +'</td><td>&nbsp- Email: '+ order.email +'</td></tr>'+
            '<tr><td>- Phone: '+ order.phone +'</td><td>&nbsp- Address: '+ order.shipAddress +'</td></tr>'+
            '<tr><td>- Ship Date: '+ miliSecondToDate(order.shipDate) +'</td><td>&nbsp- Status: '+ order.status +'</td></tr>';
}

function getRow(row,index){
    var color = row.status == 'wait' ? 'red' : 'green';
    var status = row.status == 'wait' ? 'Wait for confirmation' : 'Confirmed';
    return "<tr>"+
                "<td>"+ index +"</td>"+
                "<td>"+ row.name +"</td>"+
                "<td>"+ row.email +"</td>"+
                "<td class='text-center'><span class='badge bg-"+ color +"'>"+ status +"</span></td>"+
                "<td class='text-center'>" +
                    "<button class='btn btn-info btn-view-order' onclick='viewOrder(this)' data-id='"+ row.code +"' data-toggle='modal' data-target='#modal-view-order'>"+
                        "View</i>"+
                    "</button>"+
                    (row.status == 'wait' ? 
                    "-<button class='btn btn-danger btn-delete-order' onclick='deleteOrder(this)' data-id='"+ row.code +"'>"+
                        "Delete</i>" : "" )+
                    "</button>"+
                "</td>"+
            "</tr>";
}