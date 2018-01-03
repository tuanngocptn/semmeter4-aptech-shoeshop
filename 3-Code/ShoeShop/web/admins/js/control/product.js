var roles = [{ role: 'adm' }];
var products = [];
var updateCode = '';
var productUpdate = {};
var images = [];
var categories = [];
var brands = [];
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
    var param = 'params={"status":"","logo":"","name":""}';
    callAjax("GET", 'category', param, loadCate);
}

function loadCate(value){
    categories = value;
    $('.add-category-code, .edit-category-code').empty();
    value.forEach(element => {
        $('.add-category-code, .edit-category-code').append('<option value="'+ element.code +'">'+ element.code +'</option>')
    });
    var param = 'params={"status":"","logo":"","name":""}';
    callAjax("GET", 'brand', param, loadBrand);
}

function loadBrand(value){
    brands = loadBrand
    $('.add-brand-code, .edit-brand-code').empty();
    value.forEach(element => {
        $('.add-brand-code, .edit-brand-code').append('<option value="'+ element.code +'">'+ element.code +'</option>')
    });
}

function upload(){
    var url = $('.img-edit-0').attr('src');
    callAjax("POST", 'upload', url, console.log);
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
    var images = [];
    images.push(lstImages[0].image);
    images.push(lstImages[1].image);
    images.push(lstImages[2].image);
    images.push(lstImages[3].image);

    if (images[0].split("/")[0] =="images")
    {
        images[0] = '../' + images[0];
    } 
    if (images[1].split("/")[0] =="images")
    {
        images[1] = '../' + images[1];
    } 
    if (images[2].split("/")[0] =="images")
    {
        images[2] = '../' + images[2];
    } 
    if (images[3].split("/")[0] =="images")
    {
        images[3] = '../' + images[3];
    } 
    return  '<tr>' +
                '<td><img src="'+ images[0] +'" style="width: 124px; height: auto;"/></td>' +
                '<td><img src="'+ images[1] +'" style="width: 124px; height: auto;"/></td>' +
                '<td><img src="'+ images[2] +'" style="width: 124px; height: auto;"/></td>' +
                '<td><img src="'+ images[3] +'" style="width: 124px; height: auto;"/></td>' +
            '</tr>';
}

function updateProduct(element){
    var code = element.dataset.id;
    products.forEach(product => {
        if(product.code == code){
            getLiEdit(product);
            loadImageEdit(product.lstImages);
        }
    });
}

function doUpdate(){
    var code = $(".edit-code").val();
    products.forEach(product => {
        if(product.code == code){
            product.name = $(".edit-name").val();
            product.brandCode = $(".edit-brand-code").val();
            product.categoryCode = $(".edit-category-code").val();
            product.description = $(".edit-description").val();
            product.price = $(".edit-price").val();
            product.isHot = $(".edit-isHot").val();
            product.quantity = $(".edit-quantity").val();
            product.status = $(".edit-status").val();
            product.lstImages[0].image = $('.img-edit-0').val();
            product.lstImages[1].image = $('.img-edit-1').val();
            product.lstImages[2].image = $('.img-edit-2').val();
            product.lstImages[3].image = $('.img-edit-3').val();
            callAjax("PUT", 'product', JSON.stringify(product), locationReload);
        }
    });
}

function getLiEdit(product){
    $(".edit-code").val(product.code);
    $(".edit-name").val(product.name);
    $(".edit-brand-code").val(product.brandCode);
    $(".edit-category-code").val(product.categoryCode );
    $(".edit-description").val(product.description);
    $(".edit-price").val(product.price);
    if(product.isHot){
        $(".edit-isHot").val('true');
    }else{
        $(".edit-isHot").val('false');
    }
    $(".edit-quantity").val(product.quantity);
    $(".edit-status").val(product.status);
}

function deleteProduct(element){
    var code = element.dataset.id;
    products.forEach(product => {
        if(product.code == code){
            product.status = "deactive";
            callAjax("PUT", 'product', JSON.stringify(product), locationReload);
        }
    });
}

function activeProduct(element){
    var code = element.dataset.id;
    products.forEach(product => {
        if(product.code == code){
            product.status = "active";
            callAjax("PUT", 'product', JSON.stringify(product), locationReload);
        }
    });
}

function loadImageEdit(lstImages){
    $('.img-edit-0').val(lstImages[0].image);
    $('.img-edit-1').val(lstImages[1].image);
    $('.img-edit-2').val(lstImages[2].image);
    $('.img-edit-3').val(lstImages[3].image);
}

function doAddProduct(){
    var product = {};
    product.name = $(".add-name").val();
    product.brandCode = $(".add-brand-code").val();
    product.categoryCode = $(".add-category-code").val();
    product.description = $(".add-description").val();
    product.price = $(".add-price").val();
    product.isHot = $(".add-isHot").val();
    product.quantity = $(".add-quantity").val();
    product.status = $(".add-status").val();
    var lstImages = [];
    var image = {};
    image.image = $('.img-add-0').val();
    lstImages.push(image);
    image.image = $('.img-add-1').val();
    lstImages.push(image);
    image.image = $('.img-add-2').val();
    lstImages.push(image);
    image.image = $('.img-add-3').val();
    lstImages.push(image);
    product.lstImages = lstImages;
    callAjax("POST", 'product', JSON.stringify(product), locationReload);
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
                    "</button>-"+
                    (row.status == "active" ?
                    "<button class='btn btn-danger btn-update-product' onclick='deleteProduct(this)' data-id='"+ row.code +"'>"+
                        "<i class='fa fa-trash' aria-hidden='true'></i>" :
                    "<button class='btn btn-success btn-update-product' onclick='activeProduct(this)' data-id='"+ row.code +"'>"+
                        "<i class='fa fa-check' aria-hidden='true'></i>" )+
                    "</button>"+
                "</td>"+
            "</tr>";
}