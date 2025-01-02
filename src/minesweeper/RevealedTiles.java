package minesweeper;

public class RevealedTiles extends Field
{
	
	private int tilesSweeped = 0;
	
	public RevealedTiles(Field field) 
	{
		super(field.getHeight(), field.getWidth());
	}
	
	public byte revealTile(int x, int y, NumberTiles tiles)
	{
		if(field[x][y]) return -1;
		
		tilesSweeped++;
		field[x][y] = true;
		
		if(tiles.tileState(x, y) > 0) return tiles.tileState(x, y);
		
		for(int i = x - 1; i <= x + 1; i++)
			for(int ii = y - 1; ii <= y + 1; ii++)
			{
				try
				{
					revealTile(i, ii, tiles);
				} catch(ArrayIndexOutOfBoundsException e) {}
			}
		
		return 0;
	}
	
	public int getTilesSweeped() {
		return tilesSweeped;
	}
}
