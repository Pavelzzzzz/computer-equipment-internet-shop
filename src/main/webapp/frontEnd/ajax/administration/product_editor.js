var productId = window.location.href.split("?")[1].split("&")[1].split("=")[1];

document.addEventListener("DOMContentLoaded", function (){
    $.ajax({
        type: 'GET',
        url: 'api/products/' + productId,
        dataType: 'json',
        success : function(product) {
            console.log("SUCCESS ", product);
            $('#title').val(product.title);
            $('#costInteger').val(product.costInteger);
            $('#costFractional').val(product.costFractional);
            $('#text').val(product.text);
        },
        error: function (error) {
            console.log("Error: " + e.status);
        }
    });
});

function update_product(){
    $.ajax({
        type: 'POST',
        url: 'api/products/' + productId,
        //////////////////////////////////////////////////////////////////////////////////////////
        data: {'categoryId':1,
            'title':$('#title').val(),
            'costInteger':$('#costInteger').val(),
            'costFractional':$('#costFractional').val(),
            'text':$('#text').val()},
        dataType: 'json',
        success : function(product) {
            window.location.href = "administration?pageName=products";
        },
        error: function (error) {
            console.log("Error: " + error.status);
        }
    });
};

function delete_products() {
    $.ajax({
        type: 'DELETE',
        url: 'api/products/' + productId,
        success : function() {
            window.location.href = "administration?pageName=products";
        },
        error: function (error) {
            console.log("Error: " + error.status);
        }
    });
};