document.addEventListener("DOMContentLoaded", get_category());

function get_category (){
    for (var i = 1; i < $("#tableOfCategory")[0].getElementsByTagName('tr').length; i++){
        $("#tableOfCategory")[0].deleteRow(i);
    }

    $.ajax({
        type: 'GET',
        url: '../api/category',
        dataType: 'json',
        success : function(data) {
            console.log("SUCCESS: ", data.length);
            localStorage.setItem("category", JSON.stringify(data));
            for (var i = 0; i < data.length; i++){
                $("#tableOfCategory")
                    .append('<tr onclick="go_to_category_editor(' + data[i].categoryId + ')">' +
                        '<td>' + data[i].categoryId + '</td>' +
                        '<td>' + data[i].category + '</td>' + '</tr>');
            };
        },
        error: function (error) {
            console.log("Error: " + error.status);
        }
    });
};

function go_to_category_editor(categoryId) {
    document.location = "category_editor.jsp?categoryId=" + categoryId;
}

function check_color(item) {
    if (item.style.color != "black"){
        item.style.color = "black";
    };
}

function add_category() {
    $.ajax({
        type: 'POST',
        url: '../api/category',
        dataType: 'json',
        data: {'categoryName':$('#newCategory').val()},
        success : function(data) {
            console.log("SUCCESS: ", data);
            $('#newCategory').val("");
        },
        error: function (error) {
            console.log("Error: " + error.status);
            if (error.status == 400){
                $('#newCategory')[0].style.color = "red";
            };
        }
    });
}