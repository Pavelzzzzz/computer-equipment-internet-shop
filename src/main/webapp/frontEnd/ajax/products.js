document.addEventListener("DOMContentLoaded", function (){
    $.ajax({
        type: 'GET',
        url: '../api/products',
        dataType: 'json',
        success : function(data) {
            console.log("SUCCESS: ", data.length);
            localStorage.setItem("news", JSON.stringify(data));
            for (var i = 0; i < data.length; i++){
                $("#tableOfNews")
                    .append('<tr>' +
                    // .append('<tr onclick="go_to_user_editor(' + data[i].userId + ')">' +
                        '<td>' + data[i].newsId + '</td>' +
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

// function go_to_user_editor(userId) {
//     document.location = "user_editor.jsp?userId=" + userId;
// }