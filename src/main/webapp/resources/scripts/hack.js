'use strict';

document.addEventListener("DOMContentLoaded", () => {
    document.querySelectorAll("input:not([type=submit]):not([type=hidden])")
        .forEach(input => {
        let [form, name] = input.name.split(":");
        input.name =  `${form}:invalid-${name}`;
    });
});