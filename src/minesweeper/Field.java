package minesweeper;

public class Field
{
	protected boolean[][] field;
	
	protected int width = 16, height = 16;
	
	public Field()
	{
		field = new boolean[width][height];
	}
	
	public Field(int width, int height) {
		this.width = width;
		this.height = height;
		
		field = new boolean[width][height];
	}
	
	public Field(boolean[][] field) {
		this.field = field;
	}
	
	public void printField()
	{
		for(boolean[] row : field)
		{
			for(boolean tile : row)
			{
				if(tile)
				{
					System.out.print("[x]");
				} else
				{
					System.out.print("   ");
				}
			}
			System.out.println();
		}
	}
	
	public boolean tileState(int x, int y)
	{
		return field[x][y];
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
}
