'use strict';

document.addEventListener("DOMContentLoaded", main);

function main() {
    let yField = document.getElementById("y-input");
    yField.onchange = validConstraint;
}

function validConstraint(event) {
    let field = event.target;
    console.log(field);
    let value = field.value.replace(",", ".");
    console.log(value);
    if (value.trim() === "") {
        field.setCustomValidity("Заполните поле");
        return false;
    } else if (!isFinite(value)) {
        field.setCustomValidity("Должно быть число!");
        return false;
    } else if (value >= 5 || value <= -3) {
        field.setCustomValidity("Вы вышли за диапазон (-3; 5)!");
        return false;
    } else {
        field.setCustomValidity("");
        return true;
    }
}