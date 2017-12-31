var roles = [{ role: 'adm' }];

var members;
$(document).ready(function () { 
    var param = 'params={"code":""}';
    callAjax("GET", 'account', param, loadData);
});

function loadData(data){
    members = data;
    $(".tbl-member").empty();
    data.forEach(function(element,index,arr){
        $(".tbl-member").append(getRow(element,index + 1));
    });
}

function viewMember(element){
    var code = element.dataset.id;
    members.forEach(member => {
        if(member.code == code){
            $('.member-table').empty();
            $('.member-table').append(getLi(member));
        }
    });
}

function getLi(member){
    return  '<tr><td>- Code: '+ member.code +'</td><td>&nbsp- Role: '+ member.roleCode +'</td></tr>'+
            '<tr><td>- Customer User: '+ member.user +'</td><td>&nbsp- Pass: '+ member.pass +'</td></tr>'+
            '<tr><td>- Name: '+ member.name +'</td><td>&nbsp- Address: '+ member.address +'</td></tr>'+
            '<tr><td>- Phone: '+ member.phone +'</td><td>&nbsp- Email: '+ member.email +'</td></tr>'+
            '<tr><td></td><td>&nbsp- Status: '+ member.status +'</td></tr>';
}

function updateMember(element){
    var code = element.dataset.id;
    members.forEach(member => {
        if(member.code == code){
            $('.update-code').val(member.code);
            $('.update-role').val(member.roleCode);
            $('.update-user').val(member.user);
            $('.update-pass').val(member.pass);
            $('.update-name').val(member.name);
            $('.update-address').val(member.address);
            $('.update-phone').val(member.phone);
            $('.update-email').val(member.email);
            if(member.status == 'active'){
                $('.update-isActive').prop('checked', true);
            }else{
                $('.update-isActive').prop('checked', false);
            }
        }
    });
}

function doUpdate(){
    var member = {};
    member.code = $('.update-code').val();
    member.roleCode = $('.update-role').val();
    member.user = $('.update-user').val();
    member.pass = $('.update-pass').val();
    member.name = $('.update-name').val();
    member.address = $('.update-address').val();
    member.phone = $('.update-phone').val();
    member.email = $('.update-email').val();
    if($('.update-isActive').is(":checked")){
        member.status = 'active';
    }else{
        member.status = 'deactive';
    }
    callAjax("PUT", 'account', JSON.stringify(member), locationReload);
}

function getRow(row,index){
    var color = row.status == 'active' ? 'green' : 'red';
    return "<tr>"+
                "<td>"+ index +"</td>"+
                "<td>"+ row.roleCode +"</td>"+
                "<td>"+ row.user +"</td>"+
                "<td>"+ row.name +"</td>"+
                "<td class='text-center'><span class='badge bg-"+ color +"'>"+ row.status +"</span></td>"+
                "<td class='text-center'>" +
                    "<button class='btn btn-info btn-view-order' onclick='viewMember(this)' data-id='"+ row.code +"' data-toggle='modal' data-target='#modal-view-member'>"+
                        "<i class='fa fa-eye' aria-hidden='true'></i>"+
                    "</button>-"+
                    "<button class='btn btn-primary btn-update-order' onclick='updateMember(this)' data-id='"+ row.code +"' data-toggle='modal' data-target='#modal-update-member'>"+
                        "<i class='fa fa-pencil' aria-hidden='true'></i>"+
                    "</button>"+
                "</td>"+
            "</tr>";
}