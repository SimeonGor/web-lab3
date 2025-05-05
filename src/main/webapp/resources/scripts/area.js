'use strict';

const DEBUG = true;
if (!DEBUG) {
    console.log = () => {};
}

let rValue;
function handleSlider(event, ui) {
    let customEvent = new Event("change")
    rValue.value = ui.value;
    rValue.dispatchEvent(customEvent);
}

document.addEventListener("DOMContentLoaded", () => {
    let svg = document.querySelector("svg.graph");
    let area = new Area(svg);
    rValue = document.getElementById("coordinates-form:r-output");
    rValue.addEventListener("change", () => {
        let hiddenR = document.getElementById("hidden-form:hidden-r");
        hiddenR.value = rValue.value;
        area.setR(rValue.value);
        console.log(rValue.value);
    });

    svg.addEventListener("click", (e) => {
        let point = area.getPoint(e);
        let submitButton = document.getElementById("hidden-form:hidden-button");
        let hiddenX = document.getElementById("hidden-form:hidden-x");
        let hiddenY = document.getElementById("hidden-form:hidden-y");
        hiddenX.value = point.x;
        hiddenY.value = point.y;

        submitButton.click();
    });
});

class Area {
    #element;
    #path;
    #isSettingRadius = false;
    constructor(element) {
        this.#element = element;

        for (let i of this.#element.children) {
            if (i.matches("path")) {
                this.#path = i;
            }
        }
    }

    setR(r) {
        if (!this.#isSettingRadius) {
            this.#isSettingRadius = true;
            for (let i of this.#element.children) {
                if (i.classList.contains("default")) {
                    i.setAttribute("visibility", "hidden");
                }
                if (i.classList.contains("scaled")) {
                    i.setAttribute("visibility", "visible");
                }
            }
        }
        for (let i of this.#element.children) {
            if (i.classList.contains("point")) {

                if (i.getAttribute("hit_radius") == r) {
                    i.setAttribute("visibility", "visible");
                }
                else {
                    i.setAttribute("visibility", "hidden");
                }
            }
        }
        let transform = this.#path.getAttribute("transform");
        let new_transform = transform.replace(new RegExp(/scale(.*, .*)/), `scale(${r/2}, ${r/2})`);
        this.#path.setAttribute("transform", new_transform);
    }

    getPoint(event) {
        if (!this.#isSettingRadius) {
            alert("Choose radius value");
            return;
        }
        let point = this.#element.createSVGPoint();
        point.x = event.clientX;
        point.y = event.clientY;
        point = point.matrixTransform(this.#element.getScreenCTM().inverse());
        let width = this.#element.getBoundingClientRect().width;
        let height = this.#element.getBoundingClientRect().height;
        let x = point.x * 12 / width - 6;
        let y = -(point.y * 12 / height - 6);

        return {x, y};
    }
}





