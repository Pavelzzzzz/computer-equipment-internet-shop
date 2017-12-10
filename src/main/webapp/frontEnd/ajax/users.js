document.addEventListener("DOMContentLoaded", function (){
    $.ajax({
        type: 'GET',
        url: '../api/users',
        dataType: 'json',
        success : function(data) {
            console.log("SUCCESS: ", data.length);
            // localStorage.setItem("users", JSON.stringify(data));
            for (var i = 0; i < data.length; i++){
                $("#tableOfUsers")
                    .append('<tr onclick="go_to_user_editor(' + data[i].userId + ')">' +
                        '<td>' + data[i].userId + '</td>' +
                        '<td>' + data[i].login + '</td>' +
                        '<td>' + data[i].email + '</td>' +
                        '<td>' + data[i].active + '</td>' +
                        '<td>' + data[i].credentialsNonExpired + '</td>' + '</tr>');
            };
        },
        error: function (error) {
            console.log("Error: " + error.status);
        }
    });
});

function go_to_user_editor(userId) {
    document.location = "user_editor.jsp?userId=" + userId;
}