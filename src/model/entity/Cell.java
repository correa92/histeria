package model.entity;

public class Cell {
	private int row;
	private int col;
	private String colorHex;
	private boolean visited = false;

	public Cell(int row, int col, String colorHex) {
		this.row = row;
		this.col = col;
		this.colorHex = colorHex;
	}

	public boolean isVisited() {
		return this.visited;
	}
	public void setVisited(boolean value) {
		this.visited = value;
	}

	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}

	public String getColorHex() {
		return colorHex;
	}

	public void setColorHex(String colorHex) {
		this.colorHex = colorHex;
	}

	@Override
	public String toString() {
		return "Cell(" + row + "," + col + ") - " + colorHex;
	}
}
