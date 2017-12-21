var orderId = window.location.href.split("?")[1].split("&")[1].split("=")[1];

document.addEventListener("DOMContentLoaded", function (){

    $.ajax({
        type: 'GET',
        url: 'api/products/',
        dataType: 'json',
        success : function(data) {
            console.log("SUCCESS: ", data.length);
            localStorage.setItem("products", JSON.stringify(data));
            for (var i = 0; i < data.length; i++){
                $("#select-product")
                    .append('<option>' + data[i].title + '</option>');
            };

        },
        error: function (error) {
            console.log("Error: " + error.status);
        }
    });

    $.ajax({
        type: 'GET',
        url: 'api/users/',
        dataType: 'json',
        success : function(data) {
            console.log("SUCCESS: ", data.length);
            localStorage.setItem("users", JSON.stringify(data));
            for (var i = 0; i < data.length; i++){
                $("#select-user")
                    .append('<option>' + data[i].login + '</option>');
            };

        },
        error: function (error) {
            console.log("Error: " + error.status);
        }
    });

    $.ajax({
        type: 'GET',
        url: 'api/orders/' + orderId,
        dataType: 'json',
        success : function(order) {
            console.log("SUCCESS ", order);
            $("#select-product")[0].selectedIndex = order.productId - 1;
            $('#count').val(order.count);
            $("#select-user")[0].selectedIndex = order.userId - 1;
            $('#phone').val(order.phone);
            $('#address').val(order.address);
        },
        error: function (error) {
            console.log("Error: " + e.status);
        }
    });
});

function update_order(){
    $.ajax({
        type: 'POST',
        url: 'api/orders/' + orderId,
        data: {'productId':$("#select-product")[0].selectedIndex + 1,
            'userId':$("#select-user")[0].selectedIndex + 1,
            'phone':$('#phone').val(),
            'address':$('#address').val(),
            'count':$('#count').val()},
        dataType: 'json',
        success : function(order) {
            window.location.href = "administration?pageName=orders";
        },
        error: function (error) {
            console.log("Error: " + error.status);
        }
    });
};

function delete_orders() {
    $.ajax({
        type: 'DELETE',
        url: 'api/orders/' + orderId,
        success : function() {
            window.location.href = "administration?pageName=orders";
        },
        error: function (error) {
            console.log("Error: " + error.status);
        }
    });
};