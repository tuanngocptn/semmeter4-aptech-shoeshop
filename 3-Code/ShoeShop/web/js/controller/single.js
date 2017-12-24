$(document).ready(function () {
    var product = JSON.parse(localStorage.single);
    loadSingle(product);
    $(".rating-stars ul").click(function(event){
        var vote = getEventTarget(event).getAttribute("data-vote");
        var param = '{"productCode": "'+ product.code +'","accountCode": "'+ user.code +'","rate": '+ vote +'}';
        callAjax("POST", 'rate', param, reload);
    })
});

function getEventTarget(e) {
    e = e || window.event;
    return e.target || e.srcElement; 
}

function loadSingle(product) {
    $(".shoe-name h3").text(product.name);
    $(".shoe-name p").text(product.categoryCode);
    $(".shoe-name h4").html('&#36;' + product.price);
    $(".item_price").html('&#36;' + product.price);
    $(".item_name").html(product.code);
    $(".showcase-last ul li").text(product.description);

    loadRate(product.code);
    loadSlides(product);
}

function reload(boo){
    if(boo){
        location.reload();
    }
}

function loadSlides(product) {
    var images = product.lstImages;
    var param = 'params={"categoryCode":"' + product.categoryCode + '"}';
    callAjax("GET", 'product', param, loadData);
    $(".slides").empty();
    images.forEach(element => {
        $(".slides").append(getImageCard(element.image));
    });
}

function loadData(data) {
    $('.content-product').empty();
    var i = 0;
    var codeCurentProduct = (JSON.parse(localStorage.single)).code;
    data.forEach(function(element, index, _arr) {
        if(i < 3 && codeCurentProduct != element.code){
            $('.content-product').append(getProductCard(element));
            i++;
        }
    });
};

function loadRate(code) {
    var param = 'params={"productCode":"' + code + '"}'
    callAjax("GET", 'rate', param, showRate);
}

function showRate(data){
    $(".rating-stars ul").empty();
    for (var index = 0; index < 5; index++) {
        $(".rating-stars ul").append(getStarLi(index < data.rate, index + 1));
    }

}

function getImageCard(url) {
    return '<li data-thumb="' + url + '">'
                + '<div class="thumb-image">'
                + '<img src="' + url + '" data-imagezoom="true" class="img-responsive"> </div>'
            + '</li>';
}

function quickView(element) {
    var params = 'params={"code":"' + $(element).data("id") + '"}'
    callAjax("GET", 'product', params, setSingle);
}

function setSingle(value) {
    localStorage.single = JSON.stringify(value[0]);
    window.location.replace(constants.urlServer + "single.html");
}

function getStarLi(isActive, index){
    return '<li>'
        +'<a href="#" class="'+ (isActive ? 'active' : '') +'">'
            +'<span data-vote="'+ index +'" class="glyphicon glyphicon-star star-stn" aria-hidden="true"></span>'
        +'</a>'
    +'</li>';
}

function getProductCard(value){
    return '<div class="col-md-4 grid-stn simpleCart_shelfItem">'+
                '<!-- normal -->'+
                '<div class="ih-item square effect3 bottom_to_top">'+
                    '<div class="bottom-2-top">'+
                        '<div class="img">'+
                            '<img src="'+ value.lstImages[0].image +'" alt="/" class="img-responsive gri-wid">'+
                        '</div>'+
                        '<div class="info">'+
                            '<div class="pull-left style-hdn">'+
                                '<h3>'+ value.name +'</h3>'+
                            '</div>'+
                            '<div class="pull-right style-price">'+
                                '<p>'+
                                    '<a href="#" class="item_add">'+
                                        '<span class="glyphicon glyphicon-shopping-cart grid-cart" aria-hidden="true"></span>'+
                                        '<span class="item_price">$'+ value.price +'</span>'+
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