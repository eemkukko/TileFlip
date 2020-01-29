import java.util.ArrayList;
import java.util.Random;

public class TilesGame {
	
	private ArrayList <Tile> tiles;
	private ArrayList <Tile> staleTiles;
 	private int gridSize;
	private int moves;
	private int maxMoves;
	
	public TilesGame(int gridSize_par) {
		this.gridSize = gridSize_par;
		this.maxMoves = gridSize_par*(gridSize_par+1);
		initGrid();
	}
	
	public TilesGame(int gridSize_par, int maxMoves_par) {
		this.gridSize = gridSize_par;
		this.maxMoves=maxMoves_par;
		initGrid();
	}

	private void initGrid(){
		Random startPosition = new Random();
		this.tiles = new ArrayList<>();
		this.staleTiles = new ArrayList<>();

		for(int y = 0; y < this.gridSize; y++) {
			for (int x = 0; x < this.gridSize; x++) {
				tiles.add(new Tile(startPosition.nextBoolean(),x,y));
			}
		}
		this.tiles.get(0).setIsSelected(true);
		this.moves=0;
	}
	

	/*Moves the selected tile to direction specified by input */
	public void move(String input) {
		
		int selectedLocation = 999;
		int tileIndex = -999;
		
		for(Tile tile:tiles) {
			if(tile.getIsSelected()) {
				selectedLocation = tiles.indexOf(tile);
				break;
			}
		}
		
		if(selectedLocation<0) {
			System.out.println("Olet s�rkenyt pelin.");
			return;
		}
		
		switch(input) {

			case "u":
				if(selectedLocation < gridSize)
					return;
				tileIndex = selectedLocation - gridSize;
				break;

			case "d":
				if(selectedLocation >= (gridSize * gridSize - gridSize))
					return;
				tileIndex = selectedLocation + gridSize;
				break;

			case "l":
				if(selectedLocation % gridSize == 0)
					return;
				tileIndex = selectedLocation - 1;
				break;

			case "r":
				if((selectedLocation + 1) % gridSize == 0)
					return;
				tileIndex = selectedLocation + 1;
				break;

			default:
				System.out.println("DEBUG: Invalid input");
				break;
		}

		if(tileIndex != -999) {
			tiles.get(selectedLocation).setIsSelected(false);
			tiles.get(tileIndex).setIsSelected(true);
			tiles.get(tileIndex).setIsFlipped(!(tiles.get(tileIndex).getIsFlipped()));
			staleTiles.add(tiles.get(selectedLocation));
			staleTiles.add(tiles.get(tileIndex));
			moves++;
		}
	}
	/*gameOver() checks if the game is over and whether it has been won or not. Returns 0 when game is not over, 1 when lost, 2 when won*/
	
	public int gameOver() {
		boolean allFlip=true;
		boolean noFlip=true;
		
		for (Tile t:tiles) {
			if(t.getIsFlipped())
				noFlip=false;
			else allFlip=false;
		}
		if(noFlip||allFlip) 
			return 2;
		else if(moves>=maxMoves)
			return 1;
		else return 0;
	}
	public void setTiles(ArrayList<Tile> tiles) {
		this.tiles=tiles;
	}
	public ArrayList<Tile> getTiles(){
		return this.tiles;
	}
	public void setGridSize(int gridSize) {
		this.gridSize = gridSize;
	}
	public int getGridSize() {
		return gridSize;
	}
	public void setMoves(int moves) {
		this.moves=moves;
	}
	public int getMoves() {
		return this.moves;
	}

	public int getMaxMoves() {
		return maxMoves;
	}
	public void setMaxMoves(int maxMoves_par) {
		this.maxMoves = maxMoves_par;
	}
	public ArrayList<Tile> getStaleTiles() {
		return staleTiles;
	}
	public void setStaleTiles(ArrayList<Tile> staleTiles) {
		this.staleTiles = staleTiles;
	}

}
