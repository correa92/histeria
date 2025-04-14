package controller;

import view.MainMenuScreen;
import view.ScoreScreen;
import view.GameScreen;
import view.InstructionsScreen;
import view.DifficultyScreen;
import javax.swing.*;
import model.service.MatrixService;
import model.service.ScoreService;

import java.awt.*;

public class GameController {
	private JFrame frame;
	private CardLayout cardLayout;
	private JPanel mainPanel;
	private MainMenuScreen mainMenu;
	private GameScreen gameScreen;
	private InstructionsScreen instructionScreen;
	private ScoreScreen scoreScreen;
	private DifficultyScreen difficultyScreen;
	private ScoreService scoreService;
	private MatrixService matrixService;

	private boolean modeTest = true;

	public GameController() {
		frame = new JFrame("Juego en Java");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600, 600);
		frame.setLocationRelativeTo(null);

		cardLayout = new CardLayout();
		mainPanel = new JPanel(cardLayout);

		mainMenu = new MainMenuScreen();
		difficultyScreen = new DifficultyScreen();
		scoreScreen = new ScoreScreen();
		instructionScreen = new InstructionsScreen();
		scoreService = new ScoreService();

		mainPanel.add(mainMenu, "Menu");
		mainPanel.add(difficultyScreen, "Dificultad");
		mainPanel.add(scoreScreen, "Puntajes");
		mainPanel.add(instructionScreen, "Instrucciones");

		frame.add(mainPanel);
		frame.setVisible(true);

		setupListeners();
	}

	private void setupListeners() {
		mainMenu.addPlayListener(e -> cardLayout.show(mainPanel, "Dificultad"));

		mainMenu.addScoresListener(e -> {
			scoreScreen.updateScores(scoreService.getScores());
			cardLayout.show(mainPanel, "Puntajes");
		});

		mainMenu.addInstruccionesListener(e -> cardLayout.show(mainPanel, "Instrucciones"));

		difficultyScreen.addEasyListener(e -> {
			selectLevel(5);
			startGame();
		});

		difficultyScreen.addMediumListener(e -> {
			selectLevel(7);
			startGame();
		});

		difficultyScreen.addHardListener(e -> {
			selectLevel(10);
			startGame();
		});

		mainMenu.addExitListener(e -> System.exit(0));

		scoreScreen.addBackListener(e -> cardLayout.show(mainPanel, "Menu"));
		instructionScreen.addBackListener(e -> cardLayout.show(mainPanel, "Menu"));
		difficultyScreen.addBackListener(e -> cardLayout.show(mainPanel, "Menu"));
	}

	private void selectLevel(int gridSize) {
		if (gameScreen != null)
			mainPanel.remove(gameScreen);

		matrixService = new MatrixService(gridSize);
		gameScreen = new GameScreen(modeTest, gridSize);

		mainPanel.add(gameScreen, "Juego");
	}

	private void startGame() {
		matrixService.init();

		gameScreen.updateQtyHelp(matrixService.getQtyHelp());
		gameScreen.setButtonMatrix(matrixService.getMatrix());

		JButton[][] buttons = gameScreen.getMatrix();
		for (int i = 0; i < buttons.length; i++) {
			for (int j = 0; j < buttons[i].length; j++) {
				int buttonId = Integer.parseInt(buttons[i][j].getActionCommand());
				buttons[i][j].addActionListener(event -> handleButtonClick(buttonId));
			}
		}

		gameScreen.getButtonHelp().addActionListener(e -> {
			gameScreen.updateNextColor(matrixService.getNextColor());
			gameScreen.updateQtyHelp(matrixService.getQtyHelp());
			gameScreen.activeHelp();
		});

		gameScreen.addBackListener(e -> {
			matrixService.resetScoreAndHelp();
			gameScreen.updateScore(matrixService.getScore());
			cardLayout.show(mainPanel, "Menu");
		});

		cardLayout.show(mainPanel, "Juego");
	}

	private void handleButtonClick(int buttonId) {
		try {
			gameScreen.updateMatrix(matrixService.getButtonAndAdjancents(buttonId));

			if (matrixService.isWinner()) {
				gameScreen.isWinner();
				matrixService.endGame(gameScreen.getNamePlayer());
				cardLayout.show(mainPanel, "Menu");
			}
			
			if (modeTest) gameScreen.updateNextColor(matrixService.getNextColorModeTest());

			gameScreen.updateScore(matrixService.getScore());
			gameScreen.updateQtyHelp(matrixService.getQtyHelp());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
