/**
 * A n-by-n minesweeper field that contains mines. Generate one using the #Field()
 * @author MindFlares
 */
class Field {

    readonly width: number;
    readonly height: number;
    readonly mines: Boolean[];

    /**
     * Generates an minefield using the seed parameter with the dimensiosn specified by the width and height.
     * @param width the width of the minefield
     * @param height the height of the minefield
     * @param seed the seed used to generate the minefield
     */
    constructor(width: number, height: number, numOfMines = 0, seed?: bigint) {

        this.mines = new Boolean[width * height];

        if (seed === undefined) return;

        let subSeed = seed;
        let count = 0;

        for (let i = 0; i < width; i++) {

            for (let ii = 0; ii < height; ii++) {

                let diff = this.#nCr(width * height - i * height - ii - 1, numOfMines - 1 - count);

                if (subSeed >= seed && subSeed >= 0n) {

                    subSeed -= diff;

                } else {

                    this.mines[i * width + ii] = true;
                    count++;

                    if (count === numOfMines)
                        return;
                }

            }

        }

    }

    #nCr(numOfElements: number, sizeOfSet: number) {  
        let numOfCombinations: bigint = 1n;

        for (let i = 1; i <= numOfElements; i++)
            numOfCombinations *= BigInt(i);

        for (let i = 1; i <= sizeOfSet; i++)
            numOfCombinations /= BigInt(i);

        for (let i = 1; i <= numOfElements - sizeOfSet; i++)
            numOfCombinations /= BigInt(i);

        return numOfCombinations;
    }
}