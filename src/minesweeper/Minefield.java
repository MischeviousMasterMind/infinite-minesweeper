package minesweeper;

import java.math.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.random.*;

public class Minefield
{
	private static ArrayList<Integer> fieldSizes = new ArrayList<Integer>();
	private static ArrayList<BigInteger> listOfNumberOfConfigurations = new ArrayList<BigInteger>();
	
	private static Random random = new Random();
	
	private boolean hasGenerated = false;
	private boolean[][] field;
	
	private int width = 16, height = 16, numberOfMines = 16;
	private BigInteger seed;

	public Minefield()
	{
		field = new boolean[width][height];
		
		seed = new BigInteger(numberOfConfigurations().bitLength(), 0, random).mod(numberOfConfigurations());
	}

	public Minefield(String seed)
	{
		field = new boolean[width][height];
		
		this.seed = new BigInteger(seed).mod(numberOfConfigurations());
	}
	
	public Minefield(BigInteger seed)
	{
		field = new boolean[width][height];
		
		this.seed = seed.mod(numberOfConfigurations());
	}
	
	public Minefield(int width, int height, int numberOfMines)
	{
		this.width = width;
		this.height = height;
		this.numberOfMines = numberOfMines;

		field = new boolean[width][height];
		
		seed = new BigInteger(numberOfConfigurations().bitLength(), 0, random).mod(numberOfConfigurations());
	}
	
	public Minefield(int width, int height, int mines, String seed)
	{
		this(width, height, mines);
		
		this.seed = new BigInteger(seed).mod(numberOfConfigurations());
	}
	
	public Minefield(int width, int height, int mines, BigInteger seed)
	{
		this(width, height, mines);
		
		this.seed = seed.mod(numberOfConfigurations());
	}
	
	public Minefield(boolean[][] field)
	{
		this.field = field;
		
		width = field.length;
		height = field[0].length;
		
		numberOfMines = 0;
		for(boolean[] row : field)
		{
			for(boolean tile : row)
			{
				if(tile) numberOfMines++;
			}
		}
		
		seed = Minefield.findSeed(field);
	}

	public int generate()
	{
		if(hasGenerated) return -1; // method will refuse to generate the minefield if it has already been generated
		
		hasGenerated = true;
		
		BigInteger seed = this.seed;
		
		int count = 0;
		
		for(int i = 0; i < width; i++)
		{
			for(int ii = 0; ii < height; ii++)
			{
				System.out.print("Comparing " + seed + " to: " + ExtendedMath.nCr(width * height - i * height - ii - 1, numberOfMines - 1 - count));
				
				if(seed.compareTo(ExtendedMath.nCr(width * height - i * height - ii - 1, numberOfMines - 1 - count)) >= 0 && !seed.equals(BigInteger.ZERO))
				{
					System.out.println(" SUBTRACTING!");
					seed = seed.subtract(ExtendedMath.nCr(width * height - i * height - ii - 1, numberOfMines - 1 - count));
				}
				else
				{
					System.out.println(" adding a mine!");
					field[i][ii] = true;
					count++;
					
					if(count == numberOfMines) {
						return 0;
					}
				}	
			}
		}
		
		return 0;
	}
	
	public void regenerate()
	{
		hasGenerated = false;
		generate();
	}
	
	public BigInteger numberOfConfigurations()
	{
		if(fieldSizes.contains(width * height)) return listOfNumberOfConfigurations.get(fieldSizes.indexOf(width * height));
		
		fieldSizes.add(0, width * height);
		listOfNumberOfConfigurations.add(0, ExtendedMath.nCr(width * height, numberOfMines));
		return listOfNumberOfConfigurations.get(0);
	}
	
	public String toString()
	{
		String telemetry = "Width: " + width + ", Height: " + height + ", Number of Mines: " + numberOfMines + ", Seed: " + seed.toByteArray().toString().substring(3);
		if(hasGenerated)
		{
			telemetry = "[GENERATED] " + telemetry;
		}
		else
		{
			telemetry = "            " + telemetry;
		}
		return telemetry;
	}
	
	public void printField()
	{
		for(boolean[] row : field)
		{
			System.out.print("{ ");
			for(boolean tile : row)
			{
				System.out.print(tile + " ");
			}
			System.out.print("}  ");
		}
	}
	
	public static BigInteger findSeed(boolean[][] field)
	{
		BigInteger seed = BigInteger.ZERO;
		
		int count = 0, index = 0;
		ArrayList<Integer> magicNumbers = new ArrayList<Integer>(0);
		magicNumbers.add(0);
		
		for(boolean[] row : field)
		{
			for(boolean tile : row)
			{
				if(tile)
				{
					index++;
					magicNumbers.add(0);
					count = 0;
				} else
				{
					magicNumbers.set(index, magicNumbers.get(index) + 1);
					count++;
				}
			}
		}
		
		boolean[] stream = new boolean[field.length * field[0].length];
		
		index = 0;
		for(boolean[] row : field)
		{
			for(boolean tile : row)
			{
				stream[index] = tile;
				index++;
			}
		}
		
		int minesSeen = 0, tilesSeen = 0;
		for(boolean tile : stream)
		{
			
			if(minesSeen == magicNumbers.size() - 1) break;
			
			tilesSeen++;
			
			if(tile)
			{
				minesSeen++;
			} else
			{
				seed = seed.add(ExtendedMath.nCr(stream.length - tilesSeen, magicNumbers.size() - 2 - minesSeen));
			}
		}
		
		return seed;
	}
	
	public boolean isGenerated()
	{
		return hasGenerated;
	}

	public BigInteger getSeed()
	{
		return seed;
	}

	public boolean[][] getField()
	{
		return field;
	}

	public int getWidth()
	{
		return width;
	}

	public int getHeight()
	{
		return height;
	}

	public int getNumberOfMines()
	{
		return numberOfMines;
	}
}
