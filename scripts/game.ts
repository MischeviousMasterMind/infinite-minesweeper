
class Game {

    readonly flags: Field;
    readonly sweeped: Field;

    numberedTiles: number[][];
    
    readonly mines: Field;

    constructor(mines: Field) {

        this.mines = mines;

    }

}

/**
 * A m-by-n minesweeper field that can contain mines, flags, or sweeped tiles.
 * Values are stored in a 1-dimensional boolean array, but are treated as a rectangular array.
 * @author MindFlares
 */
class Field {

    readonly width: number;
    readonly height: number;
    readonly numOfElements: number;
    #field: Boolean[];

    /**
     * Generates a field using the seed parameter with the dimensions specified by the width and height.
     * 
     * 
     * 
     * @param width the width of the minefield
     * @param height the height of the minefield
     * @param seed the seed used to generate the minefield
     */
    constructor(width: number, height: number, numOfElements = 0, seed?: bigint) {

        this.#field = new Boolean[width * height];
        this.numOfElements = numOfElements;

        if (numOfElements === 0) return;

        if (seed === undefined)
            seed = Field.generateRandomSeed(width * height, numOfElements);

        let subSeed = seed;
        let count = 0;

        for (let i = 0; i < width; i++) {

            for (let ii = 0; ii < height; ii++) {

                let diff = Field.#nCr(width * height - i * height - ii - 1, numOfElements - 1 - count);

                if (subSeed >= seed && subSeed >= 0n) {

                    subSeed -= diff;

                } else {

                    this.#field[i * width + ii] = true;
                    count++;

                    if (count === numOfElements)
                        return;
                }
            }
        }
    }

    /**
     * Generates a random seed for the field of the specified size and number of elements.
     * @param size the total area of the field (width multiplied by height)
     * @param numOfElments the number of elements in the field
     * @returns random seeed with the specified size and number of elements
     */
    static generateRandomSeed(size: number, numOfElements: number) {

        return BigInt(Math.random()) * Field.#nCr(size, numOfElements);
    }

    /**
     * Returns the value of the cell at the specified row and column of the field
     * @param row the row in the field
     * @param col the column in the field
     * @returns values inside of the cell at the specified row and column
     */
    get(row: number, col: number) {

        return this.#field[row * this.width + col];
    }

    /**
     * Sets the value of the cell at the specified row and column of the field to the value specified
     * @param row the row in the field
     * @param col the column in the field
     * @param value the value to set at the cell
     */
    set(row: number, col: number, value: boolean) {

        this.#field[row * this.width + col] = value;
    }

    static #nCr(numOfElements: number, sizeOfSet: number) {

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