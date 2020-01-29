public class Tile {

	private boolean isFlipped;
	private int locationX;
	private int locationY;
	private boolean isSelected;
	
	public Tile (boolean isFlipped_par, int x_par, int y_par) {
		setIsFlipped(isFlipped_par);
		setLocationX(x_par);
		setLocationY(y_par);
		setIsSelected(false);
	}

	public boolean getIsFlipped() {
		return isFlipped;
	}

	public void setIsFlipped(boolean isFlipped) {
		this.isFlipped = isFlipped;
	}

	public int getLocationX() {
		return locationX;
	}

	public void setLocationX(int locationX) {
		this.locationX = locationX;
	}

	public int getLocationY() {
		return locationY;
	}

	public void setLocationY(int locationY) {
		this.locationY = locationY;
	}

	public boolean getIsSelected() {
		return isSelected;
	}

	public void setIsSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}
}
