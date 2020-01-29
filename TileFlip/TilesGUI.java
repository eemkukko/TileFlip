import java.util.*;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
@SuppressWarnings("serial")

public class TilesGUI extends JFrame implements KeyListener{

	private JFrame pFrame;
	private JPanel pPanel;
	private TilesGame defaultGame;
	private ArrayList<JLabel> tileLabels;
	private JLabel movesCount;

	public TilesGUI(TilesGame defaultGame_par){
		this.defaultGame = defaultGame_par;
		pFrame = new JFrame("Game");
		int gridSize = defaultGame.getGridSize();
		pFrame.setSize(gridSize * 100,gridSize * 100);
		pFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		pFrame.setFocusable(true);
		tileLabels = new ArrayList<>();
		initMap(defaultGame_par);
		pFrame.addKeyListener(this);
	}

	public void initMap(TilesGame game) {
		
		this.pPanel = new JPanel(new GridBagLayout());
		ArrayList<Tile> tiles = game.getTiles();
		GridBagConstraints con = new GridBagConstraints();
		GridBagConstraints movesC = new GridBagConstraints();
		movesCount = new JLabel();
		movesCount.setFont(new Font("Serif", Font.BOLD, 20));
		
		for (Tile t : tiles) {
			con.gridx = t.getLocationX();
			con.gridy = t.getLocationY();
			JLabel tileLabel = new JLabel(updateTileIcon(t));
			this.pPanel.add(tileLabel, con);
			this.tileLabels.add(tileLabel);
		}

		movesC.gridy = (con.gridy+1);
		movesC.gridwidth = game.getGridSize();
		movesC.insets = new Insets(10,0,0,0);
		movesCount.setText("Moves left: " + (game.getMaxMoves()-game.getMoves()));
		this.pPanel.add(movesCount,movesC);
		this.pFrame.add(pPanel);
		this.pFrame.setVisible(true);
	}
	/*
	  Updates the icons of each tile on the map.
	*/

	public void updateMovesCount(){
		movesCount.setText("Moves left: " + (defaultGame.getMaxMoves()-defaultGame.getMoves()));
	}

	/*
		updateTileIcon
		Replaces the icon of a tile with a new one based on the list of tiles stored in defaultGame.
		Updates the icon in tileIcons if it exists.
		Params:
			Tile tile: The tile to be updated
		Returns:
			The newly created JLabel
	*/
	public ImageIcon updateTileIcon(Tile tile){

		int tileIndex = this.defaultGame.getTiles().indexOf(tile);

		ImageIcon icon;

		if(tile.getIsFlipped()) {
			if (tile.getIsSelected()) {
				icon = new ImageIcon(getClass().getResource("icons/selectIsFlipped.png"));
			}
			else {
				icon = new ImageIcon(getClass().getResource("icons/isFlipped.png"));
			}
		}

		else if(tile.getIsSelected()) {
			icon = new ImageIcon(getClass().getResource("icons/selectNotFlipped.png"));
		}
		else {
			icon = new ImageIcon(getClass().getResource("icons/notFlipped.png"));
		}
		if (tileLabels.size() > tileIndex) {
			tileLabels.get(tileIndex).setIcon(icon);
		}

		return icon;
	}

	public void gameEndScreen(int result) {
		
		if (result==0)
			return;
		String [] options = {"New game", "Quit"};
		int selection;
		if(result==1) {
			selection=JOptionPane.showOptionDialog(pFrame,"Game Over! You ran out of moves.","Game Over Man",JOptionPane.YES_NO_OPTION,JOptionPane.PLAIN_MESSAGE,null,options,options[1]);
			}
		else if (result==2){ 
			selection=JOptionPane.showOptionDialog(pFrame,"You won the game in " + defaultGame.getMoves() +" moves.","Glorious victory",JOptionPane.YES_NO_OPTION,JOptionPane.PLAIN_MESSAGE,null,options,options[1]);
		}
		else {
			selection=JOptionPane.showConfirmDialog(pFrame,"This message should not be possible.", "What", JOptionPane.DEFAULT_OPTION,JOptionPane.ERROR_MESSAGE);
		}

		if(selection==JOptionPane.YES_OPTION) {
			Tilesmain.newGame();
		}
		else if(selection==JOptionPane.NO_OPTION) {
			System.exit(0);
		}
		else System.exit(0);
		
	}
	
	public void newGameGUI(TilesGame defaultGame_par) {
		pFrame.getContentPane().removeAll();
		pFrame.repaint();
		pFrame.revalidate();
		this.defaultGame = defaultGame_par;
		int gridSize = defaultGame.getGridSize();
		pFrame.setSize(gridSize * 100,gridSize * 100);
		tileLabels.clear();
		initMap(defaultGame_par);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		int keyCode=e.getKeyCode();
		
		switch(keyCode) {
		
		case KeyEvent.VK_UP:
			defaultGame.move("u");
			break;
		case KeyEvent.VK_DOWN:
            defaultGame.move("d");
            break;
        case KeyEvent.VK_LEFT:
            defaultGame.move("l");
            break;
        case KeyEvent.VK_RIGHT :
            defaultGame.move("r");
            break;
		default:
			break;
		}
		for (Tile t : defaultGame.getStaleTiles()) {
			updateTileIcon(t);
		}
		defaultGame.getStaleTiles().clear();
		updateMovesCount();
		gameEndScreen(defaultGame.gameOver());
	}

	@Override
	public void keyPressed(KeyEvent arg0) { }

	@Override
	public void keyTyped(KeyEvent arg0) { }
	
}
