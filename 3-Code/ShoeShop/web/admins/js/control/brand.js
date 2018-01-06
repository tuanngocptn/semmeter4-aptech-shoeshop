var roles = [{ role: 'adm' }];
var brands = [];
var updateCode = '';
var brandUpdate = {};
$(document).ready(function () { 
    var param = 'params={"status":"","logo":"","name":""}';
    callAjax("GET", 'brand', param, loadData);
});

function doSearch(){
    brandUpdate = {};
    brandUpdate.name = brandUpdate.logo = brandUpdate.status = $('.text-search').val();
    var param = 'params=' + JSON.stringify(brandUpdate);
    callAjax("GET", 'brand', param, loadData);
}

function doUpdate(){
    brandUpdate.name =  $('.update-name').val();
    brandUpdate.logo =  $('.update-img-link').val();
    if($('.update-isActive').is(":checked")){
        brandUpdate.status = 'active';
    }else{
        brandUpdate.status = 'deactive';
    }
    callAjax("PUT", 'brand', JSON.stringify(brandUpdate), locationReload);
}

function doAddNew(){
    brandUpdate.name = $('.add-new-name').val();
    brandUpdate.logo = $('.add-new-img').val();
    brandUpdate.status = 'active';
    callAjax("POST", 'brand', JSON.stringify(brandUpdate), addNewResult);
}

function addNewResult(value){
    if(value){
        location.reload();
    }else{
        alert("This Name is existed !")
    }
}

function updateBrand(button){
    updateCode = button.dataset.id;
    brands.forEach(element => {
        if(element.code == updateCode){
            brandUpdate = element;
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
    console.log(data);
    brands = data;
    $(".tbl-brand").empty();
    data.forEach(function(element,index,arr){
        $(".tbl-brand").append(getRow(element,index + 1));
    });
}

function deleteBrand(button){
    updateCode = button.dataset.id;
    brands.forEach(element => {
        if(element.code == updateCode){
            element.status = 'delete'
            callAjax("PUT", 'brand', JSON.stringify(element), locationReload);
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
                    "<button class='btn btn-primary btn-update-brand' onclick='updateBrand(this)' data-id='"+ row.code +"' data-toggle='modal' data-target='#modal-edit-brand'>"+
                        "<i class='fa fa-pencil' aria-hidden='true'></i>"+
                    "</button>-" +
                    "<button class='btn btn-danger btn-update-brand' onclick='deleteBrand(this)' data-id='"+ row.code +"'>"+
                        "<i class='fa fa-trash' aria-hidden='true'></i>" +
                "</td>"+
            "</tr>";
}