package model.Dto;

public class CellDto {

	private int id;
	private int x;
	private int y;
	private String colorHex;

	/**
	 * @param id
	 * @param x
	 * @param y
	 * @param colorHex
	 */
	public CellDto(int id, int x, int y, String colorHex) {
		this.id = id;
		this.x = x;
		this.y = y;
		this.colorHex = colorHex;
	}

	public int getId() {
		return id;
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
