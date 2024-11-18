'use strict';

const DEBUG = true;
if (!DEBUG) {
    console.log = () => {};
}

document.addEventListener("DOMContentLoaded", () => {
    let clock = document.getElementById("clock");
    clock.innerHTML = new Date().toLocaleTimeString();
    setInterval(() => {
        clock.innerHTML = new Date().toLocaleTimeString();
    }, 12 * 1000)
});