/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {
    $('#example').DataTable();
});

$(document).ready(function () {
    $('#login-id').validate({ // initialize the plugin
        rules: {
            fieldText: {
                required: true,
                minlength : 4
            }
        },
        submitHandler: function (form) { // for demo
            alert('valid form submitted'); // for demo
            return false; // for demo
        }
    });

});