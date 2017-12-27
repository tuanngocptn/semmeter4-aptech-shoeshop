var roles = [{ role: 'adm' }, { role: 'ctm' }, { role: '' }];

$(document).ready(function () {
    localStorage.removeItem('productType');
    if (typeof localStorage.productType === 'undefined') {
        localStorage.productType = '';
    } else {
        console.log(localStorage.productType);
    };
    callAjax("GET", 'product', 'params={"isHot": true}', loadCarousel);
});

function loadCarousel(data){
    $('.carousel-indicators').empty();
    $('.carousel-inner').empty();
    var i = 0;
    data.forEach(element => {
        if(element.isHot){
            $('.carousel-indicators').append(getIndicatorCard(i));
            $('.carousel-inner').append(getInner(element,i));
            i++;
        }
    });
    callAjax("GET", 'product', 'params={"top":9, "name":""}', loadData);
}

function loadData(data){
    $('.content-product').empty();
    
    data.forEach(element => {
        $('.content-product').append(getProductCard(element));
    });
};

function quickView(element){
    var params = 'params={"code":"'+ $(element).data("id") +'"}'
    callAjax("GET", 'product', params, setSingle);
}

function setSingle(value){
    localStorage.single = JSON.stringify(value[0]);
    window.location.replace(constants.urlServer + "single.html");
}

function getIndicatorCard(index){
    if(index == 0){
        return '<li data-target="#myCarousel" data-slide-to="0" class="active"></li>';
    }
    return '<li data-target="#myCarousel" data-slide-to="'+ index +'"></li>';
}

function getInner(value,index){
    if(index == 0){
        return '<div class="item active">'+
                    '<img src="'+ value.lstImages[0].image +'" alt="...">'+
                    '<div class="carousel-caption car-re-posn">'+
                        '<h3>'+ value.name +'</h3>'+
                        '<span class="color-bar"></span>'+
                    '</div>'+
                '</div>';
    }
    return '<div class="item">'+
                '<img src="'+ value.lstImages[0].image +'" alt="...">'+
                '<div class="carousel-caption car-re-posn">'+
                    '<h3>'+ value.name +'</h3>'+
                    '<span class="color-bar"></span>'+
                '</div>'+
            '</div>';
}

function getProductCard(value){
return '<div class="col-md-4 grid-stn simpleCart_shelfItem">'+
            '<!-- normal -->'+
            '<div class="ih-item square effect3 bottom_to_top">'+
                '<div class="bottom-2-top">'+
                    '<div class="img">'+
                        '<img src="'+ value.lstImages[1].image +'" alt="/" class="img-responsive gri-wid">'+
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
