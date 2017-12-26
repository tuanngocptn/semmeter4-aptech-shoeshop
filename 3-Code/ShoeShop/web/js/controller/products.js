var roles = [{ role: 'adm' }, { role: 'ctm' }, { role: '' }];
var isFirstLoad = true;

$(document).ready(function () {
    callAjax("GET", 'product', 'params={"top":12, "name":""}', loadData);
});

function loadData(data){
    console.log(data);
    $('.grid-gallery').empty();
    data.forEach(element => {
        $('.grid-gallery').append(getProductCard(element));
    });
    if(isFirstLoad){
        callAjax("GET", 'category', 'params={"status":"active"}', loadCate);
        isFirstLoad = false;
    }
};

function radioChange(){
    var cate = $('input[name=category]:checked').val();
    var brand = $('input[name=brand]:checked').val();

    cate = typeof cate != 'undefined' ? ',"categoryCode":"'+ cate +'"' : '';

    brand = typeof brand != 'undefined' ? ',"brandCode":"'+ brand +'"' : '';

    if(typeof brand == 'undefined'){
        brand = '';
    }
    var param = 'params={"status":"active","typeSearch":"AND"'+ cate + brand +'}'
    callAjax("GET", 'product', param, loadData);
}


function quickView(element){
    var params = 'params={"code":"'+ $(element).data("id") +'"}'
    callAjax("GET", 'product', params, setSingle);
}

function setSingle(value){
    localStorage.single = JSON.stringify(value[0]);
    window.location.replace(constants.urlServer + "single.html");
}

function loadCate(data){
    $('.cate-checkbox').empty();
    data.forEach(element => {
        $('.cate-checkbox').append(getCheckBox(element,'category'));
    });
    callAjax("GET", 'brand', 'params={"status":"active"}', loadBrand);
}

function loadBrand(data){
    $('.brand-checkbox').empty();
    data.forEach(element => {
        $('.brand-checkbox').append(getCheckBox(element,'brand'));
    });
}

function getCheckBox(value, name){
    return  '<label class="checkbox">'+
                '<input type="radio" name="'+ name +'" value="'+ value.code +'" onchange="radioChange()"><i></i>'+ value.name +
            '</label>';
}

function getProductCard(value){
    return  '<div class="col-md-4 grid-stn simpleCart_shelfItem">'+
                '<!-- normal -->'+
                '<div class="ih-item square effect3 bottom_to_top">'+
                    '<div class="bottom-2-top">'+
                        '<div class="img">'+
                            '<img src="'+ value.lstImages[0].image +'" alt="/" class="img-responsive gri-wid">'+
                        '</div>'+
                        '<div class="info">'+
                            '<div class="pull-left styl-hdn">'+
                                '<h3>'+ value.name +'</h3>'+
                            '</div>'+
                            '<div class="pull-right styl-price">'+
                                '<p>'+
                                    '<a href="#" class="item_add">'+
                                        '<span class="glyphicon glyphicon-shopping-cart grid-cart" aria-hidden="true"></span>'+
                                        '<span class=" item_price">$'+ value.price +'</span>'+
                                        '<span class="item_name hidden">'+ value.code +'</span>'+
                                    '</a>'+
                                '</p>'+
                            '</div>'+
                            '<div class="clearfix"></div>'+
                        '</div>'+
                    '</div>'+
                '</div>'+
                '<!-- end normal -->'+
                '<div class="quick-view">'+
                    '<a href="#" data-id="'+ value.code +'" onclick="quickView(this)">Quick view</a>'+
                '</div>'+
            '</div>';
}