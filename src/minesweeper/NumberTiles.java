package minesweeper;

public class NumberTiles
{
	private byte[][] tiles;
	private int width, height;
	
	public NumberTiles(Minefield minefield)
	{
		int[] mineLocations = minefield.getMineLocations();
		
		width = minefield.getWidth();
		height = minefield.getHeight();
		
		tiles = new byte[width][height];
		
		for(int mine : mineLocations)
		{
			for(int i = mine / width - 1; i <= mine / width + 1; i++)
				for(int ii = mine % width - 1; ii <= mine % width + 1; ii++)
				{
					try
					{
						tiles[i][ii]++;
					} catch(ArrayIndexOutOfBoundsException e) {}
				}
		}
		
		for(int mine : mineLocations)
		{
			tiles[mine / width][mine % width] = 9;
		}
	}
	
	public void generateTiles(Minefield minefield)
	{
		int[] mineLocations = minefield.getMineLocations();
		
		for(int mine : mineLocations)
		{
			for(int i = mine / width - 1; i <= mine / width + 1; i++)
				for(int ii = mine % width - 1; ii <= mine % width + 1; ii++)
				{
					try
					{
						tiles[i][ii]++;
					} catch(ArrayIndexOutOfBoundsException e) {}
				}
			
			tiles[mine / width][mine % width] = 9;
		}
	}
	
	public byte tileState(int x, int y) {
		return tiles[x][y];
	}
	
	public void printField()
	{
		for(int i = 0; i < width; i++)
		{
			for(int ii = 0; ii < height; ii++)
			{
				if(tiles[i][ii] == 9)
				{
					System.out.print("[x]");
				} 
				else if(tiles[i][ii] == 0)
				{
					System.out.print("   ");
				}
				else
				{
					System.out.print(" " + tiles[i][ii] + " ");
				}
			}
			
			System.out.println();
		}
	}
	
}
