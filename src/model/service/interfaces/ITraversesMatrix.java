package model.service.interfaces;

import model.Dto.CellDto;

public interface ITraversesMatrix {

	CellDto[][] getMatrix();
	void updateToDefaultColorIfSameAdjacent(int x, int y);
	String getNextColor();
	String getNextColorModeTest();
	int getQtyHelp();
}
