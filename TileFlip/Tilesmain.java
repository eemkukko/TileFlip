public class Tilesmain {
	
	private static TilesGame defaultpeli;
	private static TilesGUI defaultgui;
	
	public static void main (String [] args) {
		
		newGame();
		
	}
	public static void newGame() {
		
		int selection = GridsizeReader.readGridsize();
		if(selection==0)
			System.exit(0);
        
		defaultpeli=new TilesGame(selection);
		
		if (defaultgui==null) {
			defaultgui=new TilesGUI(defaultpeli);
		}
		else defaultgui.newGameGUI(defaultpeli);
	}
	
}

		
