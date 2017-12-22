var productId = window.location.href.split("?")[1].split("=")[1];

document.addEventListener("DOMContentLoaded", function (){

    if (localStorage.getItem("user") != undefined){
        document.getElementById("login").innerHTML =
            JSON.parse(localStorage.getItem("user")).login;
    } else {
        alert("Please log in to your account")
        window.location.href = "logon";
    }

    $.ajax({
        type: 'GET',
        url: 'api/products/' + productId,
        dataType: 'json',
        success : function(data) {
            console.log("SUCCESS: ", data);
            document.getElementById("productTitle").innerHTML = data.title;
        },
        error: function (error) {
            console.log("Error: " + error.status);
        }
    });

});

function add_order(){
    $.ajax({
        type: 'POST',
        url: 'api/orders/',
        data: {'productId': productId,
            'userId': JSON.parse(localStorage.getItem("user")).userId,
            'phone':$('#phone').val(),
            'address':$('#address').val(),
            'count':$('#count').val()},
        dataType: 'json',
        success : function(product) {
            alert("The order is successfully issued");
            window.location.href = "index";
        },
        error: function (error) {
            console.log("Error: " + error.status);
        }
    });
};
