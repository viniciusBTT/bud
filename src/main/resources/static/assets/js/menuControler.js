
let itensMenu = document.querySelectorAll(".js-menu-item");

function setActive(number)
{
    itensMenu[number].classList.remove("text-black");
    itensMenu[number].classList.remove("fs-4");
    itensMenu[number].classList.add("fs-2");
    itensMenu[number].classList.add("text-primary");
}