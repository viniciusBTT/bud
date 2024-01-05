document.addEventListener('DOMContentLoaded', function () {
    let table = new DataTable('#table', {
        language: {
            decimal: ',',
            thousands: '.',
            emptyTable: "Nenhum registro localizado",
            search: "Pesquisar",
            loadingRecords: "Carregando",
            zeroRecords: "Nenhum registro localizado",
            lengthMenu: "Mostrar _MENU_ registros",
            info: "Mostrando de _START_ a _END_ de _TOTAL_ resultados",
            infoEmpty: "Mostrando 0 a 0 de 0 registros",
            infoFiltered: "Filtrado de _MAX_ entradas totais",
            paginate: {
                firts: "Primeiro",
                last: "Ultimo",
                next: "Proximo",
                previous: "Anterior"
            }
        },
        responsive: true
    });
});