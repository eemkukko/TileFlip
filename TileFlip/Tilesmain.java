public class Tilesmain {

	private static TilesGUI defaultGUI;
	
	public static void main (String [] args) {
		newGame();
	}
	public static void newGame() {
		
		int selection = GridSizeReader.readGridSize();

		if(selection==0)
			System.exit(0);

		TilesGame defaultGame = new TilesGame(selection);

		if (defaultGUI==null) {
			defaultGUI = new TilesGUI(defaultGame);
		}
		else defaultGUI.newGameGUI(defaultGame);
	}
}
