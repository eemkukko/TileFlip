import java.util.*;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
@SuppressWarnings("serial")

public class TilesGUI extends JFrame implements KeyListener{

	private JFrame pFrame;
	private TilesGame defaultpeli;
	
	
	public TilesGUI(TilesGame defaultpeli_par){
		pFrame = new JFrame("Game");
		pFrame.setSize(1000,1000);
		pFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		pFrame.setFocusable(true);
		this.defaultpeli=defaultpeli_par;
		updateMap(defaultpeli_par);
		pFrame.addKeyListener(this);

	}

	public void updateMap(TilesGame peli) {
		
		JPanel pPanel = new JPanel(new GridBagLayout());
		ArrayList<Tile> tiles = peli.getTiles();
		GridBagConstraints con=new GridBagConstraints();
		GridBagConstraints movesC=new GridBagConstraints();
		JLabel movesCount=new JLabel();
		movesCount.setFont(new Font("Serif", Font.BOLD, 20));
		
		for (Tile t : tiles) {
			
			con.gridx=t.getLocationX();
			con.gridy=t.getLocationY();
			
			
			if(t.getIsFlipped()) {
				if (t.getIsSelected()) {
					pPanel.add(new JLabel(new ImageIcon(getClass().getResource("icons/selectIsFlipped.png"))),con);
					
				}
				else {
					pPanel.add(new JLabel(new ImageIcon(getClass().getResource("icons/isFlipped.png"))),con);
				}
				
			}
			
			else if(t.getIsSelected()) {
				pPanel.add(new JLabel(new ImageIcon(getClass().getResource("icons/selectNotFlipped.png"))),con);
			}
			else {pPanel.add(new JLabel(new ImageIcon(getClass().getResource("icons/notFlipped.png"))),con);
			}
		}
		
		movesC.gridy=(con.gridy+1);
		movesC.gridwidth=peli.getGridsize();
		movesC.insets=new Insets(10,0,0,0);
		movesCount.setText("Moves left: " + (peli.getMaxMoves()-peli.getMoves()));
		pPanel.add(movesCount,movesC);
		pFrame.add(pPanel);
		pFrame.setVisible(true);

		
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
			selection=JOptionPane.showOptionDialog(pFrame,"You won the game in " + defaultpeli.getMoves() +" moves.","Glorious victory",JOptionPane.YES_NO_OPTION,JOptionPane.PLAIN_MESSAGE,null,options,options[1]);
		}
		else {
			selection=JOptionPane.showConfirmDialog(pFrame,"This message should not be possible.", "What", JOptionPane.OK_OPTION,JOptionPane.ERROR_MESSAGE);
		}

		if(selection==JOptionPane.YES_OPTION) {
			Tilesmain.newGame();
		}
		else if(selection==JOptionPane.NO_OPTION) {
			System.exit(0);
		}
		else System.exit(0);
		
	}
	
	public void newGameGUI(TilesGame defaultpeli_par) {
		pFrame.getContentPane().removeAll();
		pFrame.repaint();
		pFrame.revalidate();
		this.defaultpeli=defaultpeli_par;
		updateMap(defaultpeli_par);
	}


	@Override
	public void keyReleased(KeyEvent e) {
		
		int keyCode=e.getKeyCode();
		
		switch(keyCode) {
		
		case KeyEvent.VK_UP:
			defaultpeli.move("u");
			break;
		case KeyEvent.VK_DOWN:
            defaultpeli.move("d");
            break;
        case KeyEvent.VK_LEFT:
            defaultpeli.move("l");
            break;
        case KeyEvent.VK_RIGHT :
            defaultpeli.move("r");
            break;
		default:
			break;
		}
		
		this.updateMap(defaultpeli);
		gameEndScreen(defaultpeli.gameOver());
		
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
