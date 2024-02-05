function  confirmDelete(id)
{
    Swal.fire({
        html: `Certeza que deseja apagar a venda ${id.replace("product","")}`,
            background: '#f1f1f1',
        showCancelButton: true,
        cancelButtonText: "Cancelar",
        showConfirmButton: true,
        confirmButtonText: "Apagar",
    }).then((result) => {
        if (result.isConfirmed) {
            //retirando o product do ID
           id = id.replace("product","");
            fetch(`?id=${id}`, {
                method: 'DELETE',
            })
                .then(data => {
                    cleanProductsSelects();
                    Swal.fire({
                        icon: 'success',
                        html: "Venda apagada com sucesso!",
                        timer: 1400,
                        timerProgressBar: true,
                        background: '#f1f1f1',
                        showConfirmButton: false,
                    })

                })
                .catch((error) => {
                    cleanProductsSelects();
                    Swal.fire({
                        icon: 'error',
                        html: "Erro ao apagar a venda!",
                        timerProgressBar: true,
                        background: '#f1f1f1 ',
                        backdrop: "rgba(0, 0, 0, 0)" ,
                    })
                });

        }
        setTimeout(() => {
            location.reload();
        }, "1200");
    });
}