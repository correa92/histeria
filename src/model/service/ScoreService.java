package model.service;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import model.entity.Score;

public class ScoreService {

	private List<Score> scoreList = new ArrayList<>();
	private Score currentUser = new Score();
	private String pathRelative = "recursos";

	public void addPoint() {
		currentUser.setPoints();
	}

	public int getScore() {
		return currentUser.getPoints();
	}

	public List<Score> getScores() {
		try {
			loadScores();
			scoreList.sort(Comparator.comparingInt(Score::getPoints));

		} catch (IOException e) {
			e.printStackTrace();
		}
		return scoreList.size() > 10 ? scoreList.subList(0, 10) : scoreList;
	}

	public void resetScore() {
		currentUser.setPoints(0);
		scoreList.clear();
	}

	private String playerRandom() {
		String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		StringBuilder nameRandom = new StringBuilder();
		Random random = new Random();

		for (int i = 0; i < 6; i++) {
			int index = random.nextInt(chars.length());
			nameRandom.append(chars.charAt(index));
		}

		return nameRandom.toString();
	}

	public void saveUserAndScore(String namePlayer) {
		try {
			currentUser.setPlayerName(namePlayer);
			saveScores();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void saveScores() throws IOException {

		validatesDir();

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(pathRelative + "/scores.txt", true))) {
			writer.write(currentUser.getPlayerName() + "," + currentUser.getPoints() + "\n");
		}

	}

	public void loadScores() throws IOException {
		scoreList.clear();

		try (BufferedReader reader = new BufferedReader(new FileReader(pathRelative + "/scores.txt"))) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] parts = line.split(",");
				String namePlayer = parts[0];
				int score = Integer.parseInt(parts[1]);

				scoreList.add(new Score(namePlayer, score));
			}
		}
	}

	private void validatesDir() {
		File dir = new File(pathRelative);
		if (!dir.exists()) {
			dir.mkdirs();
		}
	}
}
