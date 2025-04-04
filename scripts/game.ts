/**
 * A n-by-n minesweeper field that contains mines. Generate one using the #Field()
 * @author MindFlares
 */
class Field {

    readonly width: number;
    readonly height: number;
    #mines: Boolean[];

    /**
     * Generates an empty minefield with the dimensions specified by the width and height.
     * @param width the width of the minefield
     * @param height the height of the minefield
     */
    constructor(width: number, height: number);

    /**
     * Generates an minefield using the seed parameter with the dimensiosn specified by the width and height.
     * @param width the width of the minefield
     * @param height the height of the minefield
     * @param seed the seed used to generate the minefield
     */
    constructor(width: number, height: number, seed?: bigint) {

        this.#mines = new Boolean[width * height];

        if (seed == undefined) return;

        let subSeed = seed;
        let count = 0;

        for (let i = 0; i < width; i++) {

            for (let ii = 0; ii < height; ii++) {

                if(subSeed >= seed && subSeed !== BigInt(0)) {

                    subSeed = subSeed.substract(this.#nCr(0, 0, 0));

                } else {

                }

            }


        }

    }

    #nCr(numOfElements: number, sizeOfSet: number) {

        let numOfCombinations = 1;

        for (let i = 1; i <= numOfElements; i++)
            numOfCombinations *= i;

        for (let i = 1; i <= sizeOfSet; i++)
            numOfCombinations /= i;

        for (let i = 1; i <= numOfElements - sizeOfSet; i++)
            numOfCombinations /= i;

        return numOfCombinations;
    }
}