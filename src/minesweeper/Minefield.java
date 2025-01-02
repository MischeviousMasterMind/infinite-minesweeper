package minesweeper;

import java.math.*;
import java.util.ArrayList;
import java.util.Random;

public class Minefield extends Field
{
	private static ArrayList<Integer> fieldSizes = new ArrayList<Integer>();
	private static ArrayList<BigInteger> listOfNumberOfConfigurations = new ArrayList<BigInteger>();
	
	private int[] mineLocations = null;
	
	private static Random random = new Random();
	
	private int numberOfMines = 16;
	private BigInteger seed = null;

	public Minefield()
	{
		super();
	}
	
	public Minefield(String seed)
	{
		super();
		
		this.seed = new BigInteger(seed).mod(numberOfConfigurations());
	}
	
	public Minefield(BigInteger seed)
	{
		super();
		
		this.seed = seed.mod(numberOfConfigurations());
	}
	
	public Minefield(int width, int height, int numberOfMines)
	{
		super(width, height);

		this.numberOfMines = numberOfMines;
	}
	
	public Minefield(int width, int height, int numberOfMines, String seed)
	{
		this(width, height, numberOfMines);
		
		this.seed = new BigInteger(seed).mod(numberOfConfigurations());
	}
	
	public Minefield(int width, int height, int mines, BigInteger seed)
	{
		this(width, height, mines);
		
		this.seed = seed.mod(numberOfConfigurations());
	}
	
	public Minefield(boolean[][] field)
	{
		super(field);
		
		numberOfMines = 0;
		for(boolean[] row : field)
		{
			for(boolean tile : row)
			{
				if(tile) numberOfMines++;
			}
		}
	}
	
	public int generateWithSeed()
	{
		
		if(mineLocations != null) return -1; // method will refuse to generate the minefield if it has already been generated
		
		mineLocations = new int[numberOfMines];
		
		if(seed == null) seed = new BigInteger(numberOfConfigurations().bitLength(), 0, random).mod(numberOfConfigurations());
		
		BigInteger subSeed = seed;
		
		int count = 0;
		
		for(int i = 0; i < width; i++)
		{
			for(int ii = 0; ii < height; ii++)
			{
				System.out.print("Comparing " + subSeed + " to: " + ExtendedMath.nCr(width * height - i * height - ii - 1, numberOfMines - 1 - count));
				
				if(subSeed.compareTo(ExtendedMath.nCr(width * height - i * height - ii - 1, numberOfMines - 1 - count)) >= 0 && !subSeed.equals(BigInteger.ZERO))
				{
					System.out.println(" SUBTRACTING!");
					subSeed = subSeed.subtract(ExtendedMath.nCr(width * height - i * height - ii - 1, numberOfMines - 1 - count));
				}
				else
				{
					System.out.println(" adding a mine!");
					field[i][ii] = true;
					mineLocations[count] = i * height + ii;
					count++;
					
					if(count == numberOfMines) {
						return 0;
					}
				}	
			}
		}
		
		return 0;
	}
	
	public int generateWithSeed(String seed)
	{
		this.seed = new BigInteger(seed).mod(numberOfConfigurations());
		return generateWithSeed();
	}
	
	/** Generates the minefield without using a seed; much more efficient than {@link generateWithSeed} since it doesn't have to invoke the {@link BigInteger} class
	 * <p>
	 * Seed has to be found after this Minefield has been generated using {@link getSeed} 
	 * 
	 * @see generateWithSeed
	 * @see getSeed
	 * @return 
	 * {@code 0} if the generation was successful
	 * <p>
	 * {@code -1} if the minefield has been already generated
	 * 
	 */
	public ArrayList<Integer> generateWithoutSeed() {
		
		if(mineLocations != null) return null; // method will refuse to generate the minefield if it has already been generated
		
		ArrayList<Integer> mines = new ArrayList<Integer>(0);
		
		for(int i = 0; i < numberOfMines; i++)
		{
			int newMine = (int)(Math.random() * width * height);
			
			while(mines.contains(newMine))
			{
				newMine = (int)(Math.random() * width * height);
				if(newMine >= width * height) newMine = 0;
			}
			
			mines.add(newMine);
		}
		
		int index = 0;
		
		mineLocations = new int [numberOfMines];
		
		for(int mine : mines)
		{
			field[mine / width][mine % width] = true;
			mineLocations[index] = mine;
			
			index++;
		}
		
		return mines;
	}
	
	/** Regenerates the minefield with a different random seed
	 * 
	 */
	public void regenerateWithSeed()
	{
		seed = null;
		mineLocations = null;
		generateWithSeed();
	}
	
	public void regenerateWithoutSeed()
	{
		seed = null;
		mineLocations = null;
		generateWithoutSeed();
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
		if(mineLocations != null)
		{
			telemetry = "[GENERATED] " + telemetry;
		}
		else
		{
			telemetry = "            " + telemetry;
		}
		return telemetry;
	}
	
	public static BigInteger findSeed(boolean[][] field)
	{
		BigInteger seed = BigInteger.ZERO;
		
		int index = 0;
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
				} else
				{
					magicNumbers.set(index, magicNumbers.get(index) + 1);
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

	public BigInteger getSeed()
	{
		if(seed == null && (mineLocations == null)) return null;
		if(seed == null) seed = findSeed(this.field);
		return seed;
	}
	
	public int[] getMineLocations()
	{
		return mineLocations;
	}

	public int getNumberOfMines()
	{
		return numberOfMines;
	}
}
