document.addEventListener("DOMContentLoaded", on_boot());

function on_boot() {
    $.ajax({
        type: 'GET',
        url: '../api/category/',
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
    get_products_by_category();
};

function get_products_by_category(){
    for (var i = 1; i < $("#tableOfProducts")[0].getElementsByTagName('tr').length; i++){
        $("#tableOfProducts")[0].deleteRow(i);
    }
    var selectedCaterogyId;
    if ($("#select-category")[0].selectedIndex == undefined) {
        selectedCaterogyId = JSON.parse(localStorage.getItem("category"))[0].categoryId;
    } else {
        selectedCaterogyId = JSON.parse(localStorage.getItem("category"))[$("#select-category")[0].selectedIndex].categoryId;
    }
    $.ajax({
        type: 'GET',
        url: '../api/category/' + selectedCaterogyId+ '/products',
        dataType: 'json',
        success: function (data) {
            console.log("SUCCESS: ", data.length);
            localStorage.setItem("products", JSON.stringify(data));
            for (var i = 0; i < data.length; i++) {
                $("#tableOfProducts")
                    .append('<tr onclick="go_to_ordering_products(' + data[i].productId + ')">' +
                        '<td>' + data[i].title + '</td>' +
                        '<td>' + data[i].costInteger + '.' + data[i].costFractional + '</td>' +
                        '<td>' + data[i].text + '</td>' + '</tr>');
            }
            ;
        },
        error: function (error) {
            console.log("Error: " + error.status);
        }
    });
};

function go_to_role_editor(roleId) {
    document.location = "role_editor.jsp?roleId=" + roleId;
}