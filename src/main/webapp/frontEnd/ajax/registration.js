function post_registration() {

    if ($('#registrationLogin').val() != "" &
        $('#registrationEmail').val() != "" &
        $('#registrationPassword1').val() != "" &
        $('#registrationPassword1').val() == $('#registrationPassword2').val()){
        $.ajax({
            type: 'POST',
            url: '../api/registration',
            data: {'login':$('#registrationLogin').val(),
                'email':$('#registrationEmail').val(),
                'password':$('#registrationPassword1').val()},
            dataType: 'json',
            success : function(data) {
                alert("Success");
                localStorage.setItem("user", JSON.stringify(data));
                window.location.href = "index.jsp";
                console.log("Success: ", data);
            },
            error : function(e) {
                alert("Error");
                console.log("Error: " + e.status);
                if (e.status == 409){
                    $('#registrationLogin')[0].style.color = "red";
                };
            }
        });
    }
}

function check_color(item) {
    if (item.style.color != "black"){
        item.style.color = "black";
    };
}

function check_matching_passwords(){
    if ($('#registrationPassword1').val() ==
            $('#registrationPassword2').val()){

        $('#registrationPassword2')[0].style.color = "green";
    }else {
        $('#registrationPassword2')[0].style.color = "red";
    };
}