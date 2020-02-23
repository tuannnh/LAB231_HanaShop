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