package model.service.interfaces;

import java.util.List;

import model.entity.Score;

public interface IScore {
	int getScore();
	void resetScore();
	List<Score> getListScore();
}
