var userId = window.location.href.split("?")[1].split("&")[1].split("=")[1];

document.addEventListener("DOMContentLoaded", function (){
    $.ajax({
        type: 'GET',
        url: 'api/users/' + userId,
        dataType: 'json',
        success : function(user) {
            console.log("SUCCESS ", user);
            $('#userLogin').val(user.login);
            $('#userEmail').val(user.email);
            $('.select-activate').val(user.active? "Active" : "Blocked");
        },
        error: function (error) {
            console.log("Error: " + e.status);
        }
    });
});

function update_user(){
    $.ajax({
        type: 'POST',
        url: 'api/users/' + userId,
        data: {'login':$('#userLogin').val(),
            'email':$('#userEmail').val(),
            'active':($('.select-activate').val() == "Active")? "true" : "false"},
        dataType: 'json',
        success : function(user) {
            window.location.href = "administration?pageName=users";
        },
        error: function (error) {
            console.log("Error: " + error.status);
        }
    });
};

function delete_user() {
    $.ajax({
        type: 'DELETE',
        url: 'api/users/' + userId,
        success : function() {
            window.location.href = "administration?pageName=users";
        },
        error: function (error) {
            console.log("Error: " + error.status);
        }
    });
};