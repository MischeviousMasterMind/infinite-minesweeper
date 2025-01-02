package minesweeper;

public class FlagField extends Field
{
	public FlagField()
	{
		super();
	}
	
	public FlagField(Field minefield)
	{
		width = minefield.getWidth();
		height = minefield.getHeight();
	}
	
	public boolean mark(int x, int y, Field revealedTiles) {
		
		if(!revealedTiles.tileState(x, y)) field[x][y] = !field[x][y];
		
		return field[x][y];
	}
}
