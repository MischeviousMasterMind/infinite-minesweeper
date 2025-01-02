package minesweeper;

import java.math.BigInteger;

public class Game
{
	
	Minefield minefield;
	RevealedTiles revealedTiles;
	NumberTiles numberTiles;
	FlagField flagField;
	
	private int width, height, numberOfMines;
	
	public Game()
	{
		minefield = new Minefield();
		
		width = minefield.getWidth();
		height = minefield.getHeight();
		numberOfMines = minefield.getNumberOfMines();
		
	}
	
	public Game(int width, int height, int numberOfMines)
	{
		this.width = width;
		this.height = height;
		this.numberOfMines = numberOfMines;
		minefield = new Minefield(width, height, numberOfMines);
	}
	
	public void start()
	{
		do
		{
			minefield.regenerateWithoutSeed();
			numberTiles = new NumberTiles(minefield);
			minefield.printField();
			System.out.println("-----------------------------------");
			
		} while(numberTiles.tileState(width / 2, height / 2) > 0);
		
		revealedTiles = new RevealedTiles(minefield);
		flagField = new FlagField(minefield);
		
		revealedTiles.revealTile(width / 2, height / 2, numberTiles);
	}
	
	public void start(String seed)
	{
		minefield.generateWithSeed(seed);
		revealedTiles = new RevealedTiles(minefield);
		numberTiles = new NumberTiles(minefield);
		flagField = new FlagField(minefield);
	}
	
	public String sweep(int x, int y)
	{
		if(revealedTiles.tileState(x, y)) return "You've already revealed this tile!";
	
		if(minefield.tileState(x, y))
		{
			return "You hit a mine! GAME OVER...";
		}
		
		if(flagField.tileState(x, y)) return "Careful! You flagged this tile.";
		
		byte tile = revealedTiles.revealTile(x, y, numberTiles);
		
		if(revealedTiles.getTilesSweeped() >= width * height - numberOfMines) return "You found all the mines! YOU WIN!";
		
		if(numberTiles.tileState(x, y) > 0) return "You sweeped a(n) " + tile + " tile!";
		return "Wow! That was a big sweep!";
	}
	
	public String flag(int x, int y)
	{
		if(revealedTiles.tileState(x, y)) return "You already revealed that tile!";
		
		flagField.mark(x, y, revealedTiles);
		return "Flagged tile at (" + x + ", " + y + ")";
	}
	
	public void revealMines() {
		numberTiles.printField();
	}
	
	public void printBoard()
	{
		for(int i = 0; i < width; i++)
		{
			for(int ii = 0; ii < height; ii++)
			{
				if(flagField.tileState(i, ii))
				{
					System.out.print("[F]");
				}
				else if(!revealedTiles.tileState(i, ii)) 
				{
					System.out.print("[ ]");
				}
				else if(numberTiles.tileState(i, ii) > 0)
				{
					System.out.print(" " + numberTiles.tileState(i, ii) + " ");
				}
				else
				{
					System.out.print("   ");
				}
			}
			
			System.out.println();
		}
	}
}
