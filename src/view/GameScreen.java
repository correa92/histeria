package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import model.Dto.CellDto;

public class GameScreen extends JPanel {

	private static final long serialVersionUID = 1L;
	private JButton btnBack, btnAux, btnHelp, btnTest;
	private JLabel lblScore;
	private JPanel panelGrid, panelTop, btnPanel;
	private JButton[][] btnsMatrix;
	private int score = 0;
	private String namePlayer;
	private Color nextColor, colorDefault = new Color(220, 220, 220);

	private boolean modeTest = false;

	public GameScreen(boolean modeTest, int fixedGrid) {

		this.modeTest = modeTest;

		btnsMatrix = new JButton[fixedGrid][fixedGrid];
		setLayout(new BorderLayout());

		lblScore = new JLabel("Puntos: " + score, SwingConstants.CENTER);
		lblScore.setFont(new Font("Arial", Font.BOLD, 20));
		lblScore.setBorder(new EmptyBorder(10, 20, 10, 20));

		btnBack = new JButton("Volver al Menú");
		btnBack.setBackground(new Color(255, 0, 0));

		btnHelp = new JButton("?");
		btnHelp.setBackground(colorDefault);
		btnHelp.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

		JPanel rightButtonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 5));
		rightButtonsPanel.setOpaque(false);

		rightButtonsPanel.add(btnHelp);

		if (this.modeTest) {
			btnTest = new JButton("Test");
			btnTest.setBackground(colorDefault);
			btnTest.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
			rightButtonsPanel.add(btnTest);
		}

		panelTop = new JPanel(new BorderLayout());
		panelTop.add(lblScore, BorderLayout.WEST);
		panelTop.add(rightButtonsPanel, BorderLayout.EAST);
		add(panelTop, BorderLayout.NORTH);

		panelGrid = new JPanel(new GridLayout(fixedGrid, fixedGrid, 0, 0));
		add(panelGrid, BorderLayout.CENTER);

		btnPanel = new JPanel();
		btnPanel.setBackground(getBackground());
		btnPanel.add(btnBack);
		add(btnPanel, BorderLayout.SOUTH);
	}

	public void addBackListener(ActionListener listener) {
		btnBack.addActionListener(listener);
	}

	public void setButtonMatrix(CellDto[][] buttons) {
		panelGrid.removeAll();

		for (int i = 0; i < buttons.length; i++) {
			for (int j = 0; j < buttons[i].length; j++) {
				CellDto currentButton = buttons[j][i];
				btnAux = new JButton();
				btnAux.setBackground(Color.decode(currentButton.getColorHex()));
				btnAux.setFont(new Font("Arial", Font.BOLD, 15));

//				if (modeTest)
//					btnAux.setText("<html>" + currentButton.getColorHex() + "<br> (" + currentButton.getX() + ","
//							+ currentButton.getY() + ")</html>");

				btnsMatrix[j][i] = btnAux;
				panelGrid.add(btnAux);
			}
		}
		refresh();
	}

	public void refresh() {
		panelGrid.revalidate();
		panelGrid.repaint();
	}

	public JButton[][] getMatrix() {
		return btnsMatrix;
	}

	public void updateScore(int score) {
		lblScore.setText("Puntos: " + score);
	}

	public void updateQtyHelp(int qtyHelp) {
		btnHelp.setText("Ayuda: " + qtyHelp);
	}

	public void updateNextColor(Color nextColor) {
		this.nextColor = nextColor;
		if (this.modeTest)
			btnTest.setBackground(this.nextColor);
	}

	public JButton getButtonHelp() {
		return btnHelp;
	}

	public void activeHelp() {
		btnHelp.setBackground(nextColor);
	}

	public void isWinner() {
		namePlayer = JOptionPane.showInputDialog("¡Felicitaciones, has ganado!\nIngresa tu nombre:");

		while (namePlayer.trim().isEmpty()) {
			namePlayer = JOptionPane.showInputDialog("Por favor, debe ingresar su nombre:");
		}
	}

	public String getNamePlayer() {
		return namePlayer;
	}

	public void updateMatrix(CellDto[][] newMatrix) {
		btnHelp.setBackground(colorDefault);

		for (int i = 0; i < newMatrix.length; i++) {
			for (int j = 0; j < newMatrix[i].length; j++) {
				CellDto cell = newMatrix[i][j];
				JButton button = btnsMatrix[i][j];

				if (cell != null && cell.getColorHex() != null) {
					button.setBackground(Color.decode(cell.getColorHex()));
					button.setFont(new Font("Arial", Font.BOLD, 15));

//					if (modeTest)
//						button.setText("<html>" + cell.getColorHex() + "<br> (" + cell.getX() + "," + cell.getY()
//								+ ")</html>");

				} else {
					button.setBackground(colorDefault);
				}
			}
		}

		refresh();
	}

}
