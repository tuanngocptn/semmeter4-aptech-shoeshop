var roles = [{ role: '' }];

$(document).ready(function () {
    $('#form-register').submit(false);

    $('#btn-register').click(function () {
        if ($('#form-register').valid()) {
            var params = getFormData($('#form-register'))
            callAjax('POST', 'register', JSON.stringify(params), registerMethod);
        }
    });

    $('#form-register').validate({
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
            user: {
                required: true,
                minlength: 5,
                maxlength: 50
            },
            pass: {
                required: true,
                minlength: 5,
                maxlength: 50
            },
            repass: {
                equalTo: "#pass"
            },
            phone: {
                required: true,
                number: true,
                minlength: 9,
                maxlength: 15
            },
            address: {
                required: true,
                minlength: 5,
                maxlength: 255
            }
        },
        messages: {
            repass: {
                equalTo: "Please enter the same password value again."
            }
        }
    });
});

function registerMethod(response) {
    if(response == true){
        var url = constants.urlServer + 'login.html';
        $(location).attr('href', url);
    }else{
        $('.error-message').removeClass("hidden");
    }
}