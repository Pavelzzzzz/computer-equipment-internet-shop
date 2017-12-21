var categoryId = window.location.href.split("?")[1].split("&")[1].split("=")[1];

document.addEventListener("DOMContentLoaded", function (){
    $.ajax({
        type: 'GET',
        url: 'api/category/' + categoryId,
        dataType: 'json',
        success : function(category) {
            console.log("SUCCESS ", category);
            $('#category').val(category.category);
        },
        error: function (error) {
            console.log("Error: " + error.status);
        }
    });
});

function update_category(){
    $.ajax({
        type: 'POST',
        url: 'api/category/' + categoryId,
        data: {'category':$('#category').val()},
        dataType: 'json',
        success : function(category) {
            window.location.href = "administration?pageName=category";
        },
        error: function (error) {
            console.log("Error: " + error.status);
        }
    });
};

function delete_category() {
    $.ajax({
        type: 'DELETE',
        url: 'api/category/' + categoryId,
        success : function() {
            window.location.href = "administration?pageName=category";
        },
        error: function (error) {
            console.log("Error: " + error.status);
        }
    });
};