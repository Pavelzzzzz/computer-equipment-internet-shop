document.addEventListener("DOMContentLoaded", function (){
    $.ajax({
        type: 'GET',
        url: 'api/products',
        dataType: 'json',
        success : function(data) {
            console.log("SUCCESS: ", data.length);
            localStorage.setItem("news", JSON.stringify(data));
            for (var i = 0; i < data.length; i++){
                $("#tableOfNews")
                    .append('<tr onclick="go_to_product_editor(' + data[i].productId + ')">' +
                        '<td>' + data[i].productId + '</td>' +
                        '<td>' + data[i].categoryId + '</td>' +
                        '<td>' + data[i].costInteger + '.' + data[i].costFractional + '</td>' +
                        '<td>' + data[i].title + '</td>' + '</tr>');
            };
        },
        error: function (error) {
            console.log("Error: " + e.status);
        }
    });
});

function go_to_product_editor(productId) {
    document.location = "administration?pageName=product_editor&productId=" + productId;
}

function go_to_add_new_product(){
    document.location = "administration?pageName=product_add";
}