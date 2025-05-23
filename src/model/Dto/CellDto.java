package model.Dto;

public class CellDto {

	private int x;
	private int y;
	private String colorHex;

	/**
	 * @param id
	 * @param x
	 * @param y
	 * @param colorHex
	 */
	public CellDto( int x, int y, String colorHex) {
		this.x = x;
		this.y = y;
		this.colorHex = colorHex;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public String getColorHex() {
		return colorHex;
	}

	public void setColorHex(String colorHex) {
		this.colorHex = colorHex;
	}

}
