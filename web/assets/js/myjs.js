function addItem(id) {
    var form = document.getElementById(id + '-form');
    var action = document.createElement('input');
    action.setAttribute('name', "txtAction");
    action.setAttribute('value', "Add");
    action.setAttribute('type', "hidden");
    form.appendChild(action);
    console.log('add ne');
    form.submit();
}



function removeItem(id, name) {
    var form = document.getElementById(id + '-form');
    var quantity = document.getElementById(id + '-quantity').value;
    console.log(quantity);
    if (quantity > '1') {
        var action = document.createElement('input');
        action.setAttribute('name', "txtAction");
        action.setAttribute('value', "Remove");
        action.setAttribute('type', "hidden");
        form.appendChild(action);
        form.submit();
    } else {
        confirmDelete(name, form);
    }

}


function confirmDelete(name, form) {
    Swal.fire({
        title: 'Confirm to delete ' + name + ' ?',
        text: "You won't be able to undo this!",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: 'Yes'
    }).then(function (result) {
        if (result.value) {
            var action = document.createElement('input');
            action.setAttribute('name', "txtAction");
            action.setAttribute('value', "Remove");
            action.setAttribute('type', "hidden");
            form.appendChild(action);
            form.submit();
        } else {
            console.log('fasle');
            return;
        }
    })
}


$(function () {
    $('#dateStart').datetimepicker({
        format: 'DD/MM/YYYY',
        defaultDate: new Date(),
        maxDate: new Date()
    });

    $('#dateEnd').datetimepicker({
        format: 'DD/MM/YYYY',
        showTodayButton: true,
        defaultDate: new Date(),
        maxDate: new Date()
    });

    $("#dateStart").on("dp.change", function (e) {
        $('#dateEnd').data("DateTimePicker").minDate(e.date);
    });
    $("#dateEnd").on("dp.change", function (e) {
        $('#dateStart').data("DateTimePicker").maxDate(e.date);
    });
});


(function ($) {
    "use strict";

    /*==================================================================
     [ Validate ]*/
    var input = $('.validate-input .my-input');

    $('.validate-form').on('submit', function () {
        var check = true;
        for (var i = 0; i < input.length; i++) {
            if (validate(input[i]) == false) {
                showValidate(input[i]);
                check = false;
            }
        }

        return check;
    });


    $('.validate-form .my-input').each(function () {
        $(this).focus(function () {
            hideValidate(this);
        });
    });

    function validate(input) {
        if ($(input).attr('type') == 'email' || $(input).attr('name') == 'txtEmail') {
            if ($(input).val().trim().match(/^([a-zA-Z0-9_\-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([a-zA-Z0-9\-]+\.)+))([a-zA-Z]{1,5}|[0-9]{1,3})(\]?)$/) == null) {
                return false;
            }
        }
        else {
            if ($(input).val().trim() == '') {
                return false;
            }
        }

        if ($(input).attr('name') == 'txtName') {
            if ($(input).val().trim().match(/[A-Za-z0-9 ]{2,50}/) == null) {
                return false;
            }
        }
        else {
            if ($(input).val().trim() == '') {
                return false;
            }
        }

        if ($(input).attr('name') == 'txtPassword') {
            if ($(input).val().trim().match(/^.{6,24}$/) == null) {
                return false;
            }
        }
        else {
            if ($(input).val().trim() == '') {
                return false;
            }
        }

        var password = $('#password').val();
        if ($(input).attr('name') == 'txtConfirm') {
            if ($(input).val().trim().match(password) == null) {
                return false;
            }
        }
        else {
            if ($(input).val().trim() == '') {
                return false;
            }
        }

        if ($(input).attr('name') == 'txtDescription') {
            if ($(input).val().trim().match(/[A-Za-z0-9 ]{2,50}/) == null) {
                return false;
            }
        }
        else {
            if ($(input).val().trim() == '') {
                return false;
            }
        }

        if ($(input).attr('name') == 'txtPrice') {
            if ($(input).val().trim().match(/^-?\d*[.,]?\d{0,2}$/) == null) {
                return false;
            }
        }
        else {
            if ($(input).val().trim() == '') {
                return false;
            }
        }
        
          if ($(input).attr('name') == 'txtDiscount') {
            if ($(input).val().trim().match(/^-?\d*[.,]?\d{0,2}$/) == null) {
                return false;
            }
        }
        else {
            if ($(input).val().trim() == '') {
                return false;
            }
        }

        if ($(input).attr('name') == 'txtQuantity') {
            if ($(input).val().trim().match(/^\d+$/) == null) {
                return false;
            }
        }
        else {
            if ($(input).val().trim() == '') {
                return false;
            }
        }

        if ($(input).attr('name') == 'image') {
            if ($(input).val().trim() == null) {
                return false;
            }
        }
        else {
            if ($(input).val().trim() == '') {
                return false;
            }
        }

    }

    function showValidate(input) {
        var thisAlert = $(input).parent();

        $(thisAlert).addClass('alert-validate');
    }

    function hideValidate(input) {
        var thisAlert = $(input).parent();

        $(thisAlert).removeClass('alert-validate');
    }


})(jQuery);

(function ($) {
    $.fn.inputFilter = function (inputFilter) {
        return this.on("input keydown keyup mousedown mouseup select contextmenu drop", function () {
            if (inputFilter(this.value)) {
                this.oldValue = this.value;
                this.oldSelectionStart = this.selectionStart;
                this.oldSelectionEnd = this.selectionEnd;
            } else if (this.hasOwnProperty("oldValue")) {
                this.value = this.oldValue;
                this.setSelectionRange(this.oldSelectionStart, this.oldSelectionEnd);
            } else {
                this.value = "";
            }
        });
    };
}(jQuery));

$("#priceMin").inputFilter(function (value) {
    return /^-?\d*[.,]?\d{0,2}$/.test(value) && (value === "" || (parseInt(value) >= 1 && parseInt(value) <= parseInt($("#priceMax").val())));
    ;
});

$("#priceMax").inputFilter(function (value) {
    return /^-?\d*[.,]?\d{0,2}$/.test(value) && (value === "" || (parseFloat()(value) <= 5000 && parseFloat()(value) >= parseFloat(($("#priceMin").val()))));
    ;
});


var file = document.getElementById("input-image");
function validateFileType() {
    var fileName = file.value,
            idxDot = fileName.lastIndexOf(".") + 1,
            extFile = fileName.substr(idxDot, fileName.length).toLowerCase();
    if (extFile == "jpg" || extFile == "jpeg" || extFile == "png") {
        //TO DO
        console.log(" valid filetype");
    } else {
//        console.log("Not invalid filetype");
//        alert("Only jpg/jpeg and png files are allowed!");
        file.value = "";  // Reset the input so no files are uploaded
    }
}

var mySlider = document.getElementById('my-slider');
var priceMin = document.getElementById('priceMin');
var priceMax = document.getElementById('priceMax');


if (priceMin.value === "") {
    priceMin.value = 1;

}
if (priceMax.value === "") {
    priceMax.value = 5000.00;
}

noUiSlider.create(mySlider, {
    start: [priceMin.value, priceMax.value],
    connect: true,
    range: {
        'min': 1,
        'max': 5000
    }
});



mySlider.noUiSlider.on('update', function (values, handle) {

    var value = values[handle];

    if (handle) {
        priceMax.value = value;
    } else {
        priceMin.value = value;
    }
});

priceMin.addEventListener('change', function () {
    if (this.value === "") {
        this.value === 1;
    }
    mySlider.noUiSlider.set([this.value, null]);
});

priceMax.addEventListener('change', function () {
    if (this.value === "") {
        this.value === 5000.00;
    }
    mySlider.noUiSlider.set([null, this.value]);
});

