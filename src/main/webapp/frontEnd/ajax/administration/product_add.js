document.addEventListener("DOMContentLoaded", function (){

    $.ajax({
        type: 'GET',
        url: 'api/category/',
        dataType: 'json',
        success : function(data) {
            console.log("SUCCESS: ", data.length);
            localStorage.setItem("category", JSON.stringify(data));
            for (var i = 0; i < data.length; i++){
                $("#select-category")
                    .append('<option>' + data[i].category + '</option>');
            };
        },
        error: function (error) {
            console.log("Error: " + error.status);
        }
    });
});

function add_product(){
    $.ajax({
        type: 'POST',
        url: 'api/products/',
        data: {'categoryId':$("#select-category")[0].selectedIndex + 1,
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
