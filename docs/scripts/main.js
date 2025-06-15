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

        });

}