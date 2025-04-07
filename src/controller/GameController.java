package controller;

import view.MainMenu;
import view.ScoreScreen;
import view.GameScreen;

import javax.swing.*;

import model.service.MatrixService;

import java.awt.*;

public class GameController {
	private JFrame frame;
	private CardLayout cardLayout;
	private JPanel mainPanel;
	private MainMenu mainMenu;
	private GameScreen gameScreen;
	private ScoreScreen scoreScreen;
	private MatrixService matrixService;
	private int fixedGrid = 5;

	public GameController() {
		frame = new JFrame("Juego en Java");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600, 600);
		frame.setLocationRelativeTo(null);

		cardLayout = new CardLayout();
		mainPanel = new JPanel(cardLayout);

		mainMenu = new MainMenu();
		gameScreen = new GameScreen(fixedGrid);
		scoreScreen = new ScoreScreen();
		matrixService = new MatrixService(fixedGrid);

		mainPanel.add(mainMenu, "Menu");
		mainPanel.add(gameScreen, "Juego");
		mainPanel.add(scoreScreen, "Puntajes");

		frame.add(mainPanel);
		frame.setVisible(true);

		setupListeners();
	}

	private void setupListeners() {

		mainMenu.addPlayListener(e -> {
			matrixService.init();

			gameScreen.setButtonMatrix(matrixService.getMatrix());
			cardLayout.show(mainPanel, "Juego");

			JButton[][] buttons = gameScreen.getMatrix();

			for (int i = 0; i < buttons.length; i++) {
				for (int j = 0; j < buttons[i].length; j++) {

					int buttonId = Integer.parseInt(buttons[i][j].getActionCommand());
					buttons[i][j].addActionListener(event -> handleButtonClick(buttonId));
				}
			}

		});
		
		mainMenu.addScoresListener(e -> {
			scoreScreen.updateScores(matrixService.getListScore());
			cardLayout.show(mainPanel, "Puntajes");
		});

		mainMenu.addExitListener(e -> System.exit(0));
		
		gameScreen.getButtonHelp().addActionListener(e-> { 
			gameScreen.activeHelp();
			});

		gameScreen.addBackListener(e -> {
			matrixService.resetScore();
			gameScreen.updateScore(matrixService.getScore());
			cardLayout.show(mainPanel, "Menu");
		});

		scoreScreen.addBackListener(e -> {
			cardLayout.show(mainPanel, "Menu");
		});
	}

	private void handleButtonClick(int buttonId) {
		try {
			gameScreen.updateMatrix(matrixService.getButtonAndAdjancents(buttonId));

			if (matrixService.isWinner()) {
				gameScreen.isWinner();
				matrixService.endGame(gameScreen.getNamePlayer());
				cardLayout.show(mainPanel, "Menu");
			}
			;

			gameScreen.updateNextColor(matrixService.getNextColor());
			gameScreen.updateScore(matrixService.getScore());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
