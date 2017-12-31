var roles = [{ role: 'adm' }];
var products = [];
var updateCode = '';
var productUpdate = {};
var images = [];
$(document).ready(function () { 
    var param = 'params={"code":""}';
    callAjax("GET", 'product', param, loadData);
});

function loadData(data){
    products = data;
    $(".tbl-product").empty();
    data.forEach(function(element,index,arr){
        $(".tbl-product").append(getRow(element,index + 1));
    });
}

function viewProduct(element){
    var code = element.dataset.id;
    products.forEach(product => {
        if(product.code == code){
            $('.update-info').empty();
            $('.update-info').append(getLi(product));
            $('.product-table').empty();
            $('.product-table').append(loadImage(product.lstImages));
        }
    });
}

function getLi(product){
    return  '<tr><td>- Code: '+ product.code +'</td><td>&nbsp- Brand Code: '+ product.brandCode +'</td></tr>'+
            '<tr><td>- Customer Name: '+ product.name +'</td><td>&nbsp- Category Code: '+ product.categoryCode +'</td></tr>'+
            '<tr><td>- Price: '+ product.price +'</td><td>&nbsp- Slide: '+ product.isHot +'</td></tr>'+
            '<tr><td>- Quantity: '+ product.quantity +'</td><td>&nbsp- Status: '+ product.status +'</td></tr>'+
            '<td colspan="2">- Description: '+ product.description +'</td>';
}

function loadImage(lstImages){
    return  '<tr>' +
                '<td><img src="../'+ lstImages[0].image +'" style="width: 124px; height: auto;"/></td>' +
                '<td><img src="../'+ lstImages[1].image +'" style="width: 124px; height: auto;"/></td>' +
                '<td><img src="../'+ lstImages[2].image +'" style="width: 124px; height: auto;"/></td>' +
                '<td><img src="../'+ lstImages[3].image +'" style="width: 124px; height: auto;"/></td>' +
            '</tr>';
}

function updateProduct(element){
    var code = element.dataset.id;
    products.forEach(product => {
        if(product.code == code){
            $('.update-info').empty();
            $('.update-info').append(getLiEdit(product));
            $('.product-table').empty();
            $('.product-table').append(loadImageEdit(product.lstImages));
        }
    });
}

function getLiEdit(product){
    return  '<tr><td> <input class="form-control" type="text" value="'+ product.code +'" placeholder="Code" /></td><td> <input class="form-control" type="text" value="'+ product.brandCode +'" placeholder="Brand Code" /></td></tr>'+
            '<tr><td> <input class="form-control" type="text" value="'+ product.name +'" placeholder="Customer Name" /></td><td><input class="form-control" type="text" value="'+ product.categoryCode +'" placeholder="Category Code" /></td></tr>'+
            '<tr><td><input class="form-control" type="text" value="'+ product.price +'" placeholder="Price" /></td><td><input class="form-control" type="text" value="'+ product.isHot +'" placeholder="Slide" /></td></tr>'+
            '<tr><td> <input class="form-control" type="text" value="'+ product.quantity +'" placeholder="Quantity" /> </td><td><input class="form-control" type="text" value="'+ product.status +'" placeholder="Status" /></td></tr>'+
            '<td colspan="2"><input class="form-control" type="area" value="'+ product.description +'" placeholder="Description"/></td>';
}

function loadImageEdit(lstImages){
    return  '<tr>' +
                '<td><img src="../'+ lstImages[0].image +'" style="width: 124px; height: auto;"/></td>' +
                '<td><img src="../'+ lstImages[1].image +'" style="width: 124px; height: auto;"/></td>' +
                '<td><img src="../'+ lstImages[2].image +'" style="width: 124px; height: auto;"/></td>' +
                '<td><img src="../'+ lstImages[3].image +'" style="width: 124px; height: auto;"/></td>' +
            '</tr>';
}

function getRow(row,index){
    var color = row.status == 'active' ? 'green' : 'red';
    return "<tr>"+
                "<td>"+ index +"</td>"+
                "<td>"+ row.name +"</td>"+
                "<td>"+ row.quantity +"</td>"+
                "<td>$"+ row.price +"</td>"+
                "<td class='text-center'><span class='badge bg-"+ color +"'>"+ row.status +"</span></td>"+
                "<td class='text-center'>" +
                    "<button class='btn btn-info btn-view-product' onclick='viewProduct(this)' data-id='"+ row.code +"' data-toggle='modal' data-target='#modal-view-product'>"+
                        "<i class='fa fa-eye' aria-hidden='true'></i>"+
                    "</button>-"+
                    "<button class='btn btn-primary btn-update-product' onclick='updateProduct(this)' data-id='"+ row.code +"' data-toggle='modal' data-target='#modal-update-product'>"+
                        "<i class='fa fa-pencil' aria-hidden='true'></i>"+
                    "</button>"+
                "</td>"+
            "</tr>";
}