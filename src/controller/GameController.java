package controller;

import view.MainMenu;
import view.ScoreScreen;
import view.DifficultyScreen;
import view.GameScreen;
import view.Instrucciones;
import model.service.MatrixService;

import javax.swing.*;

import model.service.MatrixService;
import java.awt.*;

public class GameController {
	private JFrame frame;
	private CardLayout cardLayout;
	private JPanel mainPanel;
	private MainMenu mainMenu;
	private GameScreen gameScreen;
	private Instrucciones instrucciones;
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
		instrucciones = new Instrucciones();

		mainPanel.add(mainMenu, "Menu");
		mainPanel.add(difficultyScreen, "Dificultad");
		mainPanel.add(scoreScreen, "Puntajes");
		mainPanel.add(instrucciones, "Instrucciones");

		frame.add(mainPanel);
		frame.setVisible(true);

		setupListeners();
	}

	private void setupListeners() {
		// Mostrar pantalla de selección de dificultad al presionar "Jugar"
		mainMenu.addPlayListener(e -> cardLayout.show(mainPanel, "Dificultad"));

		// Listeners para cada nivel de dificultad
		difficultyScreen.addEasyListener(e -> startGame(5, 5));
		difficultyScreen.addMediumListener(e -> startGame(7, 7));
		difficultyScreen.addHardListener(e -> startGame(10, 10));
		difficultyScreen.addBackListener(e -> cardLayout.show(mainPanel, "Menu"));
			gameScreen.updateQtyHelp(matrixService.getQtyHelp());
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

		scoreScreen.addBackListener(e -> cardLayout.show(mainPanel, "Menu"));
	}

	private void startGame(int rows, int cols) {
		// Crear nueva matriz y pantalla de juego con el tamaño seleccionado
		matrixService = new MatrixService(rows, cols);
		gameScreen = new GameScreen(rows, cols);

		// Agregar el nuevo gameScreen al panel principal
		mainPanel.add(gameScreen, "Juego");
		cardLayout.show(mainPanel, "Juego");

		// Inicializar la matriz
		matrixService.init();
		gameScreen.setButtonMatrix(matrixService.getMatrix());

		// Asignar listeners a los botones
		JButton[][] buttons = gameScreen.getMatrix();
		for (int i = 0; i < buttons.length; i++) {
			for (int j = 0; j < buttons[i].length; j++) {
				int buttonId = Integer.parseInt(buttons[i][j].getActionCommand());
				buttons[i][j].addActionListener(event -> handleButtonClick(buttonId));
			}
		}

		// Listener para volver al menú desde el juego
		gameScreen.addBackListener(e -> {
			matrixService.resetScoreAndHelp();
			gameScreen.updateScore(matrixService.getScore());
			cardLayout.show(mainPanel, "Menu");
		});
		
		instrucciones.addBackListener(e -> {
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
			gameScreen.updateScore(matrixService.getScore());
			gameScreen.updateQtyHelp(matrixService.getQtyHelp());

			gameScreen.updateNextColor(matrixService.getNextColor());
			gameScreen.updateScore(matrixService.getScore());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
