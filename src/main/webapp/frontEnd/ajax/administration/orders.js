document.addEventListener("DOMContentLoaded", get_orders());

function get_orders (){
    for (var i = 1; i < $("#tableOfOrders")[0].getElementsByTagName('tr').length; i++){
        $("#tableOfOrders")[0].deleteRow(i);
    }

    $.ajax({
        type: 'GET',
        url: 'api/orders',
        dataType: 'json',
        success : function(data) {
            console.log("SUCCESS: ", data.length);
            localStorage.setItem("orders", JSON.stringify(data));
            for (var i = 0; i < data.length; i++){
                $("#tableOfOrders")
                    .append(
                        '<tr onclick="go_to_order_editor(' + data[i].orderId + ')">' +
                        '<td>' + data[i].orderId + '</td>' +
                        '<td>' + get_productTitle_by_productId(data[i].productId) + '</td>' +
                        '<td>' + data[i].count + '</td>' +
                        '<td>' + get_login_by_userId(data[i].userId) + '</td>' +
                        '<td>' + data[i].phone + '</td>' +
                        '<td>' + data[i].address + '</td>' + '</tr>');
            };
        },
        error: function (error) {
            console.log("Error: " + error.status);
        }
    });
};

function get_login_by_userId(userId) {
    $.ajax({
        type: 'GET',
        url: 'api/users/' + userId,
        dataType: 'json',
        success : function(data) {
            console.log("SUCCESS: ", data);
            localStorage.setItem("login", data.login);
        },
        error: function (error) {
            console.log("Error: " + error.status);
            localStorage.setItem("login", "not found");
        }
    });
    return localStorage.getItem("login");
}

function get_productTitle_by_productId(productId) {
    $.ajax({
        type: 'GET',
        url: 'api/products/' + productId,
        dataType: 'json',
        success : function(data) {
            console.log("SUCCESS: ", data);
            localStorage.setItem("product title", data.title);
        },
        error: function (error) {
            console.log("Error: " + error.status);
            localStorage.setItem("product title", "not found");
        }
    });
    return localStorage.getItem("product title");
}

function go_to_order_editor(orderId) {
    document.location = "administration?pageName=order_editor&orderId=" + orderId;
}