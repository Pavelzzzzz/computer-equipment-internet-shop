function post_logon() {
    $.ajax({
        type: 'POST',
        url: 'api/logon',
        data: {'login':$('#logonLogin').val(),
               'password':$('#logonPassword').val()},
        dataType: 'json',
        success : function(user) {
            localStorage.setItem("user", JSON.stringify(user));
            console.log("SUCCESS:", user);
            var url = 'api/users/' + user.userId + '/roles';
            $.ajax({
                type: 'GET',
                url: url,
                dataType: 'json',
                success : function(roles) {
                    alert("Success");
                    localStorage.setItem("roles", JSON.stringify(roles));
                    console.log("SUCCESS:", roles);
                },
                error : function(e) {
                    alert(url + " Error ", e);
                    localStorage.setItem("error", JSON.stringify(e));
                }
            });
            window.location.href = "index";
        },
        error : function(e) {
            alert("Error");
            console.log("Error: " + e.status);
            if (e.status == 404){
                $('#logonLogin')[0].style.color = "red";
            };
            if (e.status == 401){
                $('#logonPassword')[0].style.color = "red";
            };
        }
    });
}

function check_color(item) {
    if (item.style.color != "black"){
        item.style.color = "black";
    };
}