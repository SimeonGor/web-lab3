'use strict';

const DEBUG = false;

if (!DEBUG) {
    console.log = () => {};
}

document.addEventListener("DOMContentLoaded", () => {
    let svg = document.querySelector("svg.graph");
    let area = new Area(svg);
    let radius;
    let radioList = document.querySelectorAll("#coordinates-form input[type=radio][name=r]");
    for (let radio of radioList) {
        radio.addEventListener("change", () => {
            area.setR(radio.value);
            radius = radio.value;
            console.log(radius);
        });
    }

    svg.addEventListener("click", (e) => {
        let point = area.getPoint(e);
        let url = document.querySelector("#coordinates-form").getAttribute("action");
        window.location.href = `${url}?x=${point.x}&y=${point.y}&r=${radius}`;
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
                if (i.matches(".default")) {
                    i.setAttribute("visibility", "hidden");
                }
                if (i.classList.contains("scaled")) {
                    i.setAttribute("visibility", "visible");
                }
            }
        }
        let transform = this.#path.getAttribute("transform");
        let new_transform = transform.replace(new RegExp(/scale(.*, .*)/), `scale(${r}, ${r})`);
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
        let x = point.x * 6 / width - 3;
        let y = -(point.y * 6 / height - 3);

        return {x, y};
    }
}





