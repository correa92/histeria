package model.service;

import java.util.Random;

import model.Dto.CellDto;
import model.entity.*;
import model.service.interfaces.ICondition;
import model.service.interfaces.IScore;
import model.service.interfaces.ITraversesMatrix;

public class MatrixService implements ITraversesMatrix, IScore, ICondition {
	private int row, column, qtyHelp = 3;
	private Cell[][] matrix;
	private String nextColor, colorDefault = "#C8C8C8";
	private ScoreService _scoreService = new ScoreService();

	private String[] listColorsDefault = new String[] { "#C56CF0", "#32FF7E", "#FF3838", "#FF9F1A", "#FFF200",
			"#18DCFF" };

	/**
	 * @param fixedGrid
	 */
	public MatrixService(int fixedGrid) {
		super();
		this.row = fixedGrid;
		this.column = fixedGrid;
		matrix = new Cell[fixedGrid][fixedGrid];
		nextColor = colorRandom();
	}

	public void init() {

		try {
			createMatrix();

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
	}

	public int getScore() {
		return _scoreService.getScore();
	}

	public int getQtyHelp() {
		return this.qtyHelp;
	}

	public String getNextColor() {
		if (qtyHelp > 0) {
			qtyHelp--;
			return nextColor;
		}

		return colorDefault;
	}

	public String getNextColorModeTest() {
		return nextColor;
	}

	public void resetScoreAndHelp() {
		_scoreService.resetScore();
		qtyHelp = 3;
	}

	public boolean isWinner() {
		boolean isWinner = true;

		int rows = matrix.length;
		int cols = matrix[0].length;

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				isWinner = isWinner && matrix[i][j].isVisited();
			}
		}
		return isWinner;
	}

	public void endGame(String namePlayer) {
		_scoreService.saveUserAndScore(namePlayer);
		_scoreService.resetScore();
		resetData();
		resetMatrix();
	}

	private void createMatrix() {
		for (int i = 0; i < column; i++) {
			for (int j = 0; j < row; j++) {
				matrix[j][i] = new Cell(j, i, colorDefault);
			}
		}
	}

	public CellDto[][] getMatrix() {
		int rows = matrix.length;
		int cols = matrix[0].length;
		CellDto[][] dtoMatrix = new CellDto[rows][cols];

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				dtoMatrix[i][j] = new CellDto(i, j, matrix[i][j].getColorHex());
			}
		}

		return dtoMatrix;
	}

	public void printMatrix() {
		CellDto[][] dtoMatrix = getMatrix();

		for (int i = 0; i < dtoMatrix.length; i++) {
			for (int j = 0; j < dtoMatrix[i].length; j++) {
				CellDto cell = dtoMatrix[j][i];
				System.out.print("[" + cell.getX() + "," + cell.getY() + "," + cell.getColorHex() + "] ");
			}
			System.out.println();
		}
	}

	public void updateToDefaultColorIfSameAdjacent(int x, int y) {
		Cell current = matrix[x][y];
		current.setColorHex(nextColor);
		_scoreService.addPoint();
		if (!hasSameColorAdjacent(x, y)) {
			current.setVisited(true);
			this.nextColor = colorRandom();
			return;
		}

		for (int directRow = -1; directRow <= 1; directRow++) {
			for (int directColumn = -1; directColumn <= 1; directColumn++) {
				int newRow = x + directRow;
				int newCol = y + directColumn;

				if (directRow == 0 && directColumn == 0)
					continue;

				if (isInBounds(newRow, newCol)) {
					Cell adjacent = matrix[newRow][newCol];
					adjacent.setColorHex(colorDefault);
					adjacent.setVisited(false);
				}
			}
		}

		current.setColorHex(colorDefault);
		current.setVisited(false);
	}

	private boolean hasSameColorAdjacent(int row, int col) {
		int rows = matrix.length;
		int cols = matrix[0].length;
		String color = matrix[row][col].getColorHex();

		for (int directRow = -1; directRow <= 1; directRow++) {
			for (int directColumn = -1; directColumn <= 1; directColumn++) {
				int newRow = row + directRow;
				int newCol = col + directColumn;

				if (directRow == 0 && directColumn == 0)
					continue;

				if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols) {
					Cell adjacent = matrix[newRow][newCol];
					if (adjacent.getColorHex().equals(color)) {
						return true;
					}
				}
			}
		}
		return false;
	}

	private boolean isInBounds(int row, int col) {
		return row >= 0 && row < matrix.length && col >= 0 && col < matrix[0].length;
	}

	private String colorRandom() {

		int min = 0, max = listColorsDefault.length - 1;
		int randomNumber;
		Random random = new Random();
		randomNumber = random.nextInt(max - min + 1) + min;
		return listColorsDefault[randomNumber];
	}

	private void resetMatrix() {
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				matrix[j][i].setColorHex(colorDefault);
			}
		}
	}

	private void resetData() {
		this.qtyHelp = 3;
	}

}
