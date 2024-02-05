//variavel global que armazena o valor totol
let fullValue = 0;

//Card de cada produto
let itensSale = document.querySelectorAll(".item-sale");

//span que possui o valor total
let spanValueFull = document.querySelector("#valueFull");

//capitura toda a area clicavel para marcar o input como checked
let checkedClickArea = document.querySelectorAll(".checked-click-area");


// pegandos os selects e checks
const allSelectsEndChecks = document.querySelectorAll('.form-check-input, .form-select');

// Inicializa um array para armazenar os objetos JSON
let saleConfirmJson = [];

/**
 * Verifica o evento de clique na area clicavel
 *chama a faunção que muda a visibilidade
 */

checkedClickArea.forEach((item) => {
    item.addEventListener("click", ()=> {
        var checkd = document.querySelector("#input" + item.dataset.productId)

        checkd.checked ? checkd.checked = false : checkd.checked = true;

        toggleSelect(checkd);
    });
});


//Alterando a visibilidade de um select de quantidade
function toggleSelect(checkbox)
{
    var productId = checkbox.id.replace("input", "");
    var selectContainer = document.getElementById("selectContainer" + productId);

    if (checkbox.checked)
    {
        selectContainer.style.display = "block";

    }
    else
    {
        selectContainer.style.display = "none";
        document.querySelector(`#selectQuantity${productId}`).value = 0;
    }
}



//verificando o evento de change nos inputs e selects
allSelectsEndChecks.forEach(element => element.addEventListener('change', setSpanValueFull));

function  setSpanValueFull() {
    spanValueFull.innerHTML =` Valor Total:  ${genaretedValue().toLocaleString('pt-BR', { style: 'currency',currency: 'BRL' })}`;

}

// varre todos os produtos selecionados e gera o valor total
function genaretedValue()
{
    fullValue = 0;
    itensSale.forEach(item=>
    {
        let input = document.querySelector(`#input${item.id}`);
        let selectQuantity = document.querySelector(`#selectQuantity${item.id}`)
        if(input.checked  && selectQuantity.value > 0 )
            fullValue += selectQuantity.value * item.dataset.valueun;
    })
    return fullValue
}

function confirmCleanProductsSelecteds(){
    Swal.fire({
        html: "Certeza que deseja limpar os produtos selecionados?",
        background: '#f1f1f1',
        showCancelButton: true,
        cancelButtonText: "Cancelar",
        showConfirmButton: true,
        confirmButtonText: "Limpar",
        confirmButtonColor: "#157347",
        cancelButtonColor: "rgba(0, 0, 0, 0.30)"

    }).then((result) => {
        if (result.isConfirmed)
            cleanProductsSelects();
    });
}

//Limpando os produtos selecionados
function cleanProductsSelects()
{
    allSelectsEndChecks.forEach(item => {
        if (item.tagName.toLowerCase() === 'select')
        {
            item.value = 0;
        }
        else if(item.tagName.toLowerCase() === 'input')
        {
            item.checked = false;
            toggleSelect(item);
        }
    })
    setSpanValueFull();
}


function generatedPostJson(){
    let salePostJson = [];
    itensSale.forEach(item => {
        if(document.querySelector(`#input${item.id}`).checked
            && document.querySelector(`#selectQuantity${item.id}`).value > 0 )
        {
            // Converte os data attributes e o id em um objeto JSON
            const jsonData = {
                productID: parseInt(item.id),
                quantity: parseInt(document.querySelector(`#selectQuantity${item.id}`).value)
            };

            // Adiciona o objeto JSON ao array
            salePostJson.push(jsonData);
        }
    });
    return salePostJson
}

function confirmSale()
{
    let jsonProducts = jsonGenated();
    //se houver arquivos selecionado
    if (jsonProducts.length > 0) {
        let tableRows = '';
        let fullValue = genaretedValue().toLocaleString('pt-BR', { style: 'currency',currency: 'BRL' });


        // Construa as linhas da tabela dinamicamente com base nos dados do JSON
        jsonProducts.forEach((product, index) => {
            tableRows += `<tr>
                              <th scope="row">${index + 1}</th>
                              <td>${product.productName}</td>
                              <td>${product.quantity}</td>
                          </tr>`;
        });

        Swal.fire({
            html: `<table class="table">
                      <thead>
                        <tr>
                          <th scope="col">#</th>
                          <th scope="col">Produto</th>
                          <th scope="col">Quantidade</th>
                        </tr>
                      </thead>
                      <tbody>
                        ${tableRows}
                      </tbody>
                    </table>
                    <div class="text-end">
                        <span class="font-monospace fs-4">Total: ${fullValue}</span>
                    </div>`,
            background: '#f1f1f1',
            showCancelButton: true,
            cancelButtonText: "Cancelar",
            showConfirmButton: true,
            confirmButtonText: "Confirmar venda",
            confirmButtonColor: "#157347",
            cancelButtonColor: "rgba(0, 0, 0, 0.30)"
        }).then((result) => {
            if (result.isConfirmed) {
                let saleData = {
                    "saleItens": generatedPostJson()
                };

                // Realiza a requisição POST
                fetch('/sale/create', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                    },
                    body: JSON.stringify(saleData),
                })
                    .then(data => {
                        cleanProductsSelects();
                        Swal.fire({
                            html: "<p class='fs-5'> Pedido Pronto!<br/> :) Retire no bar, com a ficha.<p>",
                            icon: "success",
                            background: '#f1f1f1',
                            showConfirmButton: true,
                            confirmButtonText: "Confirmar",
                            confirmButtonColor: "#157347",
                        }).then((result) => {
                            if (result.isConfirmed)
                                Swal.fire({
                                         icon: 'success',
                                        html: success,
                                    timer: 1400,
                                        timerProgressBar: true,
                                        background: '#f1f1f1',
                                        showConfirmButton: false,
                                    })

                                    setTimeout(() => {
                                        location.reload();
                                    }, "1200");
                        });


                    })
                    .catch((error) => {
                        Swal.fire({
                            icon: 'error',
                            html: error,
                            timerProgressBar: true,
                            background: '#f1f1f1 ',
                            backdrop: "rgba(0, 0, 0, 0)" ,
                        })
                        cleanProductsSelects();
                        setTimeout(() => {
                            location.reload();
                        }, "1700");
                    });
            }
        });
    //se não houver arquivos selecionado
    } else {
        Swal.fire({
            icon: 'warning',
            html: 'Nenhum item ou quantidade selecionado',
            timerProgressBar: true,
            background: '#f1f1f1',
            backdrop: "rgba(0, 0, 0, 0)",
            confirmButtonColor: "#157347",
        });
    }
}

/**
 * Gera um json com todos os produtos selecionado e as suas quantidades
 */
function jsonGenated()
{
    saleConfirmJson = [];
    itensSale.forEach(item => {
        if(document.querySelector(`#input${item.id}`).checked
            && document.querySelector(`#selectQuantity${item.id}`).value > 0 )
        {
            // Obtém todos os data attributes do elemento
            const dataAttributes = item.dataset;

            // Converte os data attributes e o id em um objeto JSON
            const jsonData = {
                id: item.id,
                quantity: document.querySelector(`#selectQuantity${item.id}`).value,
                ...dataAttributes
            };

            // Adiciona o objeto JSON ao array
            saleConfirmJson.push(jsonData);
        }
    });
    return saleConfirmJson;
}




const letreiro = document.getElementById('letreiro');

// function moveLetreiro() {
//     letreiro.style.transform = 'translateX(100%)';
//     letreiro.style.transition = 'none';
//
//     setTimeout(() => {
//         letreiro.style.transition = 'transform 10s linear';
//         letreiro.style.transform = 'translateX(-100%)';
//     }, 10);
//
//     setTimeout(() => {
//         letreiro.style.transition = 'none';
//         letreiro.style.transform = 'translateX(100%)';
//     }, 10010);
// }
//
// setInterval(moveLetreiro, 10);