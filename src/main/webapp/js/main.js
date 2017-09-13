function validateDepartmentForm() {

}

function validateEmployeeForm() {

}


function sendRequest() {
    $.ajax({
        url: "demo_test.txt",
        success: function (result) {
            $("#div1").html(result);
        }
    });
}

function drowTable() {
    var content = "<table>"
    for(i=0; i<3; i++){
        content += '<tr><td>' + 'result ' +  i + '</td></tr>';
    }
    content += "</table>"

    $('#here_table').append(content);




    // var table = $('<table></table>').addClass('foo');
    // for(i=0; i<3; i++){
    //     var row = $('<tr></tr>').addClass('bar').text('result ' + i);
    //     table.append(row);
    // }
    // $('#here_table').append(table);
}

var controller = function (action) {
console.log(action + " 555");
    // sendRequest();

    $('#wrapper').innerHTML = "<h1>DASDAS</h1>";


}


