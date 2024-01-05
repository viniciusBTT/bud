let itemSale = document.querySelectorAll(".checked-click-area");

itemSale.forEach((item) => {
    item.addEventListener("click", ()=> {

        var checkd = document.querySelector("#input" + item.id)
            if(checkd.checked == true)
                checkd.checked = false;
            else
                checkd.checked = true

        toggleSelect(checkd);
    });
});


function toggleSelect(checkbox) {
    var productId = checkbox.id.replace("input", "");
    var selectContainer = document.getElementById("selectContainer" + productId);

    if (checkbox.checked) {
        selectContainer.style.display = "block"; // Mostrar o seletor de quantidade quando o checkbox é marcado
    } else {
        selectContainer.style.display = "none";  // Ocultar o seletor de quantidade quando o checkbox é desmarcado
    }
}
let itensSale = document.querySelectorAll(".item-sale");
document.querySelector("#valueFull")

