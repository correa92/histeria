package model.entity;

public class Score {
	private String playerName;
	private int points = 0;

	public Score() {}

	public Score(String playerName, int points) {
		super();
		this.playerName = playerName;
		this.points = points;
	}


	public void setPoints(int point) {
		this.points = point;
	}

	public void setPoints() {
		this.points++;
	};

	public int getPoints() {
		return this.points;
	}

	public String getPlayerName() {
		return this.playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	@Override
	public String toString() {
		return "Score [playerName=" + playerName + ", points=" + points + "]";
	}
}
