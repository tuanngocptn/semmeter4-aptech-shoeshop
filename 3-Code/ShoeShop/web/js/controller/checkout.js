var roles = [{ role: 'ctm' }, { role: '' }];

$(document).ready(function () { 
    if(simpleCart.items().length < 1){
        location.replace(constants.urlServer + "index.html")
    }
    callAjax("GET", 'product', 'params={ "name":""}', loadBag);
    if(typeof user == 'undefined'){
        $("#cb-get-info").prop('disabled', true);
    }
    $(".price-details > label > input").click(function(){
        loadInfo($('#cb-get-info').is(":checked"));
    })

    $('.order').click(function(){
        if ($('#form-order').valid()) {
            $(".input-disable").prop('disabled', false);
            var params = getFormData($('#form-order'))
            if(typeof user != 'undefined'){
                $(".input-disable").prop('disabled', true);
                params.accountCode = user.code;
            }
            var lstOrderDetail = [];
            simpleCart.each(function(item, index, _arr){
                var detail = {};
                detail.productCode = item.get('name');
                detail.quantity = item.get('quantity');
                lstOrderDetail.push(detail);
            });
            params.lstOrderDetail = lstOrderDetail;
            params.shipDate = dateToMiliSecond(params.shipDate);
            params.date = (new Date()).getTime();
            callAjax("POST", 'order',JSON.stringify(params), isSuccessOrder);
        };
    });

    $('#form-order').validate({
        rules: {
            name: {
                required: true,
                minlength: 5,
                maxlength: 50
            },
            email: {
                required: true,
                email: true,
                minlength: 8,
                maxlength: 100
            },
            phone: {
                required: true,
                number: true,
                minlength: 9,
                maxlength: 15
            },
            shipAddress: {
                required: true,
                minlength: 5,
                maxlength: 255
            },
            shipDate:{
                required: true,
                date: true
            }

        },
        messages: {
        }
    });

    
});

function isSuccessOrder(boo){
    if(boo){
        if(!alert('order success!')){
            simpleCart.empty();
            window.location.replace(constants.urlServer + 'orders.html');
        }
    }else{
        alert("order false!");
    }
}

function loadInfo(boo){
    if(boo){
        $(".input-disable").prop('disabled', true);
        $(".name").val(user.name);
        $(".email").val(user.email);
        $(".phone").val(user.phone);
        $(".shipAddress").val(user.address);
        var date = new Date();
        date = addDaysDstFail(date, 4);
        date = miliSecondToDate(date.getTime());
        $(".shipDate").val(date);
    };
}

function addDaysDstFail(date, days) {
    var dayms = (days * 24 * 60 * 60 * 1000);
    return new Date(date.getTime() + dayms);    
}

function loadBag(data){
    $('.cart-sub-items').empty();
    data.forEach(element => {
        simpleCart.each(function(item, index, _arr){
            if(item.get('name') == element.code){
                element.quantity = item.get('quantity');
                $('.cart-sub-items').append(getBagCard(element,index));
            }
        });
    });
}

function getBagCard(item, index){
    return  '<script>$(document).ready(function (c) {'+
                    '$(".close'+ index +'").on("click", function (c) {'+
                        '$(".cart-header'+ index +'").fadeOut("slow", function (c) {'+
                            '$(".cart-header'+ index +'").remove();'+
                            'simpleCart.each( function( findItem ){'+
                                'if ( findItem.get("name") == "'+ item.code +'" ){'+
                                    'findItem.remove();'+
                                '}'+
                            '});'+
                        '});'+
                    '});'+
                '});'+
            '</script>'+
            '<div class="cart-header cart-header'+ index +'">'+
                '<div class="close close'+ index +'">'+
                    '<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>'+
                '</div>'+
                '<div class="cart-sec simpleCart_shelfItem">'+
                    '<div class="cart-item cyc">'+
                        '<img src="'+ item.lstImages[1].image +'" class="img-responsive" alt="" />'+
                    '</div>'+
                    '<div class="cart-item-info">'+
                        '<ul class="qty">'+
                            '<li>'+
                                '<p>Qty : '+ item.quantity +'</p>'+
                            '</li>'+
                            '<li>'+
                                '<p>Price each : $'+ item.price +'</p>'+
                            '</li>'+
                        '</ul>'+
                        '<div class="delivery">'+
                            '<p>Service Charges : Rs.$'+ (item.quantity * item.price) +'</p>'+
                            '<span>Delivered in 2-3 bussiness days</span>'+
                            '<div class="clearfix"></div>'+
                        '</div>'+
                    '</div>'+
                    '<div class="clearfix"></div>'+

                '</div>'+
            '</div>';
}