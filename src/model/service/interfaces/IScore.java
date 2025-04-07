package model.service.interfaces;

import java.util.List;

import model.entity.Score;

public interface IScore {
	int getScore();
	void resetScoreAndHelp();
	List<Score> getListScore();
	int getQtyHelp();
}
