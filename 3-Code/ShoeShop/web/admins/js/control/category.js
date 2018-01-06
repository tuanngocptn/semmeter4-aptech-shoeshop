var roles = [{ role: 'adm' }];
var categories = [];
var updateCode = '';
var cateUpdate = {};
$(document).ready(function () { 
    var param = 'params={"logo":"","name":""}';
    callAjax("GET", 'category', param, loadData);
});

function doSearch(){
    cateUpdate = {};
    cateUpdate.name = cateUpdate.logo = cateUpdate.status = $('.text-search').val();
    var param = 'params=' + JSON.stringify(cateUpdate);
    callAjax("GET", 'category', param, loadData);
}

function doUpdate(){
    cateUpdate.name =  $('.update-name').val();
    cateUpdate.logo =  $('.update-img-link').val();
    if($('.update-isActive').is(":checked")){
        cateUpdate.status = 'active';
    }else{
        cateUpdate.status = 'deactive';
    }
    callAjax("PUT", 'category', JSON.stringify(cateUpdate), locationReload);
}

function doAddNew(){
    cateUpdate.name = $('.add-new-name').val();
    cateUpdate.logo = $('.add-new-img').val();
    cateUpdate.status = 'active';
    callAjax("POST", 'category', JSON.stringify(cateUpdate), addNewResult);
}

function addNewResult(value){
    if(value){
        location.reload();
    }else{
        alert("This Name is existed !")
    }
}

function updateCate(button){
    updateCode = button.dataset.id;
    categories.forEach(element => {
        if(element.code == updateCode){
            cateUpdate = element;
            $('.update-name').val(element.name);
            $('.update-img-link').val(element.logo);
            if(element.status == 'active'){
                $('.update-isActive').prop('checked', true);
            }else{
                $('.update-isActive').prop('checked', false);
            }
        }
    });
}

function loadData(data){
    categories = data;
    $(".tbl-cate").empty();
    data.forEach(function(element,index,arr){
        $(".tbl-cate").append(getRow(element,index + 1));
    });
}

function deleteCate(button){
    updateCode = button.dataset.id;
    categories.forEach(element => {
        if(element.code == updateCode){
            element.status = 'delete'
            callAjax("PUT", 'category', JSON.stringify(element), locationReload);
        }
    });
}
function getRow(row,index){
    var color = row.status == 'active' ? 'green' : 'red';
    return "<tr>"+
                "<td>"+ index +"</td>"+
                "<td>"+ row.name +"</td>"+
                "<td class='text-center'><span class='badge bg-"+ color +"'>"+ row.status +"</span></td>"+
                "<td class='text-center'>" +
                    "<button class='btn btn-primary btn-update-cate' onclick='updateCate(this)' data-id='"+ row.code +"' data-toggle='modal' data-target='#modal-edit-cate'>"+
                        "<i class='fa fa-pencil' aria-hidden='true'></i>"+
                    "</button>-"+
                    "<button class='btn btn-danger btn-update-cate' onclick='deleteCate(this)' data-id='"+ row.code +"'>"+
                        "<i class='fa fa-trash' aria-hidden='true'></i>" +
                "</td>"+
            "</tr>";
}