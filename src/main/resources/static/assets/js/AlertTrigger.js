if(success)
    alertSuccess();

if(error)
    alertError();


function alertSuccess(){
    Swal.fire({
        icon: 'success',
        html: success,
        timer: 1700,
        timerProgressBar: true,
        background: '#f1f1f1',
        showConfirmButton: false,
    })
}

function alertError(){
    Swal.fire({
        icon: 'error',
        html: error,
        timerProgressBar: true,
        background: '#f1f1f1 ',
        backdrop: "rgba(0, 0, 0, 0)" ,
    })
}
