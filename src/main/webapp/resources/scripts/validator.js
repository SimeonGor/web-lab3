'use strict';

const DEBUG = true;
if (!DEBUG) {
    console.log = () => {};
}

document.addEventListener("DOMContentLoaded", main);

function main() {
    let yField = document.getElementById("coordinates-form:y-input");
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
    } else if (value >= 3 || value <= -5) {
        field.setCustomValidity("Вы вышли за диапазон (-5; 3)!");
        return false;
    } else {
        field.setCustomValidity("");
        return true;
    }
}