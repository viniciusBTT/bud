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
            if(checkd.checked == true)
                checkd.checked = false;
            else
                checkd.checked = true

        toggleSelect(checkd);
    });
});


//Alterando a visibilidade de um select de quantidade
function toggleSelect(checkbox)
{
    var productId = checkbox.id.replace("input", "");
    var selectContainer = document.getElementById("selectContainer" + productId);

    if (checkbox.checked)
        selectContainer.style.display = "block";
    else
        selectContainer.style.display = "none";
}



//verificando o evento de change nos inputs e selects
allSelectsEndChecks.forEach(element => element.addEventListener('change', genaretedValue));


// varre todos os produtos selecionados e gera o valor total
function genaretedValue()
{
    fullValue = 0;
    itensSale.forEach(item=>
    {
        if(document.querySelector(`#input${item.id}`).checked
            && document.querySelector(`#selectQuantity${item.id}`).value > 0 )
            fullValue += document.querySelector(`#selectQuantity${item.id}`).value * item.dataset.valueun;
    })
    spanValueFull.innerHTML = fullValue.toLocaleString('pt-BR', { style: 'currency',currency: 'BRL' });
}

//Limpando os produtos selecionados
function cleanProductsSelectes()
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
    genaretedValue()
}
function confirmSale(){
    let jsonProducts = jsonGenated();
    if(!jsonProducts.length > 0)
    {
        Swal.fire({
            icon: 'success',
            html: jsonProducts,
            timer: 1700,
            timerProgressBar: true,
            background: '#f1f1f1',
            showConfirmButton: false,
        })
    }
    else
    {

    }

    console.log(jsonGenated());
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


