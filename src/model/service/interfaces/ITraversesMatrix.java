package model.service.interfaces;

import java.awt.Color;
import java.util.List;

import model.entity.Cell;

public interface ITraversesMatrix {

	Cell[][] getMatrix();

	List<Cell> getButtonAndAdjancents(int id) throws Exception;
	
	String getNextColor();

}
