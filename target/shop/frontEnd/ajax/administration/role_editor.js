var roleId = window.location.href.split("?")[1].split("&")[1].split("=")[1];

document.addEventListener("DOMContentLoaded", function (){
    $.ajax({
        type: 'GET',
        url: 'api/roles/' + roleId,
        dataType: 'json',
        success : function(role) {
            console.log("SUCCESS ", role);
            $('#role').val(role.role);
        },
        error: function (error) {
            console.log("Error: " + error.status);
        }
    });
});

function update_role(){
    $.ajax({
        type: 'POST',
        url: 'api/roles/' + roleId,
        data: {'role':$('#role').val()},
        dataType: 'json',
        success : function(role) {
            window.location.href = "administration?pageName=roles";
        },
        error: function (error) {
            console.log("Error: " + error.status);
        }
    });
};

function delete_role() {
    $.ajax({
        type: 'DELETE',
        url: 'api/roles/' + roleId,
        success : function() {
            window.location.href = "administration?pageName=roles";
        },
        error: function (error) {
            console.log("Error: " + error.status);
        }
    });
};