import java.util.ArrayList;
import java.util.Random;

public class TilesGame {
	
	private ArrayList <Tile> tiles;
	private int gridsize;
	private int moves;
	private int maxMoves;
	
	public TilesGame(int gridsize_par) {
		
		gridsize=gridsize_par;
		Random startstatus=new Random();
		tiles=new ArrayList<Tile>();
		
		for(int y=0;y<gridsize;y++) {
			for (int x=0;x<gridsize;x++) {
				tiles.add(new Tile(startstatus.nextBoolean(),x,y));
			}
		}
		tiles.get(0).setIsSelected(true);
		moves=0;
		maxMoves=gridsize_par*(gridsize_par+1);
	}
	
	public TilesGame(int gridsize_par, int maxMoves_par) {
		
		gridsize=gridsize_par;
		Random startstatus=new Random();
		tiles=new ArrayList<Tile>();
		
		for(int y=0;y<gridsize;y++) {
			for (int x=0;x<gridsize;x++) {
				tiles.add(new Tile(startstatus.nextBoolean(),x,y));
			}
		}
		
		tiles.get(0).setIsSelected(true);
		moves=0;
		maxMoves=maxMoves_par;
	}
	
	public void setTiles(ArrayList<Tile> tiles) {
		this.tiles=tiles;
	}
	public ArrayList<Tile> getTiles(){
		return this.tiles;
	}
	public void setGridsize(int gridsize) {
		this.gridsize=gridsize;
	}
	public int getGridsize() {
		return gridsize;
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
	
	/*Moves the selected tile to direction specified by input */
	
	public void move(String input) {
		
		int selectedLocation = 999;
		
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
			if(selectedLocation<gridsize) {
				break;
			}
			tiles.get(selectedLocation-gridsize).setIsSelected(true);
			tiles.get(selectedLocation).setIsSelected(false);
			if(tiles.get(selectedLocation-gridsize).getIsFlipped())
				tiles.get(selectedLocation-gridsize).setIsFlipped(false);
			else 
				tiles.get(selectedLocation-gridsize).setIsFlipped(true);
			moves++;
			
			break;
			
		case "d":
			if(selectedLocation>=(gridsize*gridsize-gridsize)) {
				break;
				}
			tiles.get(selectedLocation).setIsSelected(false);
			tiles.get(selectedLocation+gridsize).setIsSelected(true);
			if(tiles.get(selectedLocation+gridsize).getIsFlipped())
				tiles.get(selectedLocation+gridsize).setIsFlipped(false);
			else 
				tiles.get(selectedLocation+gridsize).setIsFlipped(true);
			moves++;
			break;
			
		case "l":
			if(selectedLocation%gridsize==0)
				break;
			
			tiles.get(selectedLocation).setIsSelected(false);
			tiles.get(selectedLocation-1).setIsSelected(true);
			if(tiles.get(selectedLocation-1).getIsFlipped())
				tiles.get(selectedLocation-1).setIsFlipped(false);
			else 
				tiles.get(selectedLocation-1).setIsFlipped(true);
			moves++;
			break;
			
		case "r":
			if(selectedLocation==0);
			else if((selectedLocation+1)%gridsize==0)
				break;
			tiles.get(selectedLocation).setIsSelected(false);
			tiles.get(selectedLocation+1).setIsSelected(true);
			if(tiles.get(selectedLocation+1).getIsFlipped())
				tiles.get(selectedLocation+1).setIsFlipped(false);
			else 
				tiles.get(selectedLocation+1).setIsFlipped(true);
			moves++;
			
			break;
			
		default:
			System.out.println("Invalid input");
			break;
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

}
