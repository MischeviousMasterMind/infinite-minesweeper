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
		
		seed = new BigInteger(numberOfConfigurations().bitLength(), random).mod(numberOfConfigurations());
	}

	public Minefield(String seed)
	{
		field = new boolean[width][height];
		
		this.seed = new BigInteger(seed).mod(numberOfConfigurations());
	}
	
	public Minefield(int width, int height, int numberOfMines)
	{
		this.width = width;
		this.height = height;
		this.numberOfMines = numberOfMines;

		field = new boolean[width][height];
	}
	
	public Minefield(int width, int height, int mines, String seed)
	{
		this(width, height, mines);
		
		this.seed = new BigInteger(seed).mod(numberOfConfigurations());
	}

	public int generate()
	{
		if(hasGenerated) return -1; // method will refuse to generate the minefield if it has already been generated
		
		int[] seedNumbers = new int[numberOfMines];
		
		for(int i = 0; i < seedNumbers.length; i++)
		{
			
		}
		
		hasGenerated = true;
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
		String telemetry = "Width: " + width + ", Height: " + height + ", Number of Mines: " + numberOfMines + ", Seed: " + seed;
		if(hasGenerated) telemetry = "[GENERATED] " + telemetry;
		return telemetry;
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
