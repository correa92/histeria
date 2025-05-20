package model.entity;

import java.util.HashSet;
import java.util.Set;

public class Cell {

	private int id;
	private String colorHex;
	private Pair par;
	private Set<Integer> adjacents = new HashSet<>();

	/**
	 * @param id
	 * @param color
	 * @param par
	 */
	public Cell(int id, String color, Pair par) {
		super();
		this.id = id;
		this.colorHex = color;
		this.par = par;
	}

	public Pair getPair() {
		return par;
	}

	public int getId() {
		return id;
	}

	public String getColor() {
		return colorHex;
	}

	public void setColor(String color) {
		this.colorHex = color;
	}

	public void setAdjacents(Set<Integer> list) {
		this.adjacents.addAll(list);
	}

	public Set<Integer> getAdjacents() {
		return adjacents;
	}

	@Override
	public String toString() {
		return "Button [id=" + id + ", color=" + colorHex + " par=" + par + ", adjacents=" + adjacents + "]";
	}
}

