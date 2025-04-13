package controller;

import view.MainMenu;
import view.ScoreScreen;
import view.GameScreen;
import view.Instructions;
import view.DifficultyScreen;

import javax.swing.*;

import model.service.MatrixService;
import java.awt.*;

public class GameController {
	private JFrame frame;
	private CardLayout cardLayout;
	private JPanel mainPanel;
	private MainMenu mainMenu;
	private GameScreen gameScreen;
	private Instructions instrucciones;
	private ScoreScreen scoreScreen;
	private DifficultyScreen difficultyScreen;

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
		difficultyScreen = new DifficultyScreen();
		scoreScreen = new ScoreScreen();
		matrixService = new MatrixService(fixedGrid);
		instrucciones = new Instructions();

		mainPanel.add(mainMenu, "Menu");
		mainPanel.add(difficultyScreen,"Dificultad");
		mainPanel.add(gameScreen, "Juego");
		mainPanel.add(scoreScreen, "Puntajes");
		mainPanel.add(instrucciones, "Instrucciones");

		frame.add(mainPanel);
		frame.setVisible(true);

		setupListeners();
	}

	private void setupListeners() {
		mainMenu.addPlayListener(e -> {
			 cardLayout.show(mainPanel, "Dificultad");

		});
		
		mainMenu.addScoresListener(e -> {
			scoreScreen.updateScores(matrixService.getListScore());
			cardLayout.show(mainPanel, "Puntajes");
		});		
		mainMenu.addInstruccionesListener(e -> {
		    cardLayout.show(mainPanel, "Instrucciones");
		});

		
		mainMenu.addExitListener(e -> System.exit(0));

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

		scoreScreen.addBackListener(e -> {
			cardLayout.show(mainPanel, "Menu");
		});
		
		instrucciones.addBackListener(e -> {
		    cardLayout.show(mainPanel, "Menu");
		});
		
		difficultyScreen.addEasyListener(e -> startGameWithDifficulty(5));
		difficultyScreen.addMediumListener(e -> startGameWithDifficulty(7));
		difficultyScreen.addHardListener(e -> startGameWithDifficulty(10));

	
		difficultyScreen.addBackListener(e -> cardLayout.show(mainPanel, "Menu"));

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
			gameScreen.updateScore(matrixService.getScore());
			gameScreen.updateQtyHelp(matrixService.getQtyHelp());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void startGameWithDifficulty(int gridSize) {
		fixedGrid = gridSize;
		
		mainPanel.remove(gameScreen);
		
		matrixService = new MatrixService(fixedGrid);
		gameScreen = new GameScreen(fixedGrid);

		mainPanel.add(gameScreen, "Juego"); 
		
		matrixService.init();
		
		gameScreen.updateQtyHelp(matrixService.getQtyHelp());
		gameScreen.setButtonMatrix(matrixService.getMatrix());

		// Listeners de botones
		JButton[][] buttons = gameScreen.getMatrix();
		for (int i = 0; i < buttons.length; i++) {
			for (int j = 0; j < buttons[i].length; j++) {
				int buttonId = Integer.parseInt(buttons[i][j].getActionCommand());
				buttons[i][j].addActionListener(event -> handleButtonClick(buttonId));
			}
		}

		// Listener botón ayuda
		gameScreen.getButtonHelp().addActionListener(e -> {
			gameScreen.updateNextColor(matrixService.getNextColor());
			gameScreen.updateQtyHelp(matrixService.getQtyHelp());
			gameScreen.activeHelp();
		});

		// Listener botón volver
		gameScreen.addBackListener(e -> {
			matrixService.resetScoreAndHelp();
			gameScreen.updateScore(matrixService.getScore());
			cardLayout.show(mainPanel, "Menu");
		});

		cardLayout.show(mainPanel, "Juego");
	}

}
