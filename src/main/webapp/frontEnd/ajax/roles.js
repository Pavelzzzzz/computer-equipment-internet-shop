document.addEventListener("DOMContentLoaded", get_roles());

function get_roles (){


    var rows = $("#tableOfRoles")[0].getElementsByTagName('tr').length;
    alert(rows);
    for (var i = 1; i < rows; i++){
        alert(i);
        $("#tableOfRoles")[0].deleteRow(1);
    }

    $.ajax({
        type: 'GET',
        url: '../api/roles',
        dataType: 'json',
        success : function(data) {
            console.log("SUCCESS: ", data.length);
            localStorage.setItem("roles", JSON.stringify(data));
            for (var i = 0; i < data.length; i++){
                $("#tableOfRoles")
                    .append('<tr onclick="go_to_role_editor(' + data[i].roleId + ')">' +
                        '<td>' + data[i].roleId + '</td>' +
                        '<td>' + data[i].role + '</td>' + '</tr>');
            };
        },
        error: function (error) {
            console.log("Error: " + error.status);
        }
    });
};

function go_to_role_editor(roleId) {
    document.location = "role_editor.jsp?roleId=" + roleId;
}

function check_color(item) {
    if (item.style.color != "black"){
        item.style.color = "black";
    };
}

function add_role() {
    $.ajax({
        type: 'POST',
        url: '../api/roles',
        dataType: 'json',
        data: {'roleName':$('#newRole').val()},
        success : function(data) {
            console.log("SUCCESS: ", data);
            $('#newRole').val("");
        },
        error: function (error) {
            console.log("Error: " + error.status);
            if (error.status == 400){
                $('#newRole')[0].style.color = "red";
            };
        }
    });
}