import javax.swing.*;
public interface GridSizeReader{

	static int readGridSize() {
		
		Object[] sizes = {"Very small (5x5 tiles)", "Small (6x6 tiles)","Medium (7x7 tiles)","Large (8x8 tiles)", "Huge (9x9 tiles)"};
		Object input = JOptionPane.showInputDialog(null,"Please select the size of your game.","Size selection",JOptionPane.PLAIN_MESSAGE,null,sizes,"Medium (7x7 tiles)");
		
		if ((input == null))
		    return 0;
		
		for(int i=0;i<sizes.length;i++) {
			if(input==sizes[i]) {
				return (i+5);
			}
		}
		return 0;
	}
}
