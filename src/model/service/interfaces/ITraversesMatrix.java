package model.service.interfaces;

import java.awt.Color;
import java.util.List;

import model.entity.Button;

public interface ITraversesMatrix {

	Button[][] getMatrix();

	List<Button> getButtonAndAdjancents(int id) throws Exception;
	
	Color getNextColor();

}
