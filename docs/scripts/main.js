const canvas = document.getElementById("canvas");

window.addEventListener("resize", adjustCanvasSize);

adjustCanvasSize();

main();

function main() {

    const importObject = {

            env: {
            console_log: function(message) { console.log(message) }
            }

        };

        WebAssembly.instantiateStreaming(
            fetch("scripts/mineswept-prealpha.wasm"), importObject
        ).then((result) => {

            const print = result.instance.exports.print;

            print(1);

            draw();
        });
}

function adjustCanvasSize() {

    canvas.setAttribute("width", window.innerWidth);
    canvas.setAttribute("height", window.innerHeight);

}

function draw() {

    const ctx = canvas.getContext("2d");

    ctx.beginPath();
    ctx.rect(window.innerWidth / 2, window.innerHeight / 2, 10, 10);
    ctx.fillStyle = "000000";
    ctx.fill();
    ctx.closePath();

    requestAnimationFrame(draw);

}