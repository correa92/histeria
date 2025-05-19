package view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionListener;

import model.entity.Cell;

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

	public GameScreen( boolean modeTest, int fixedGrid) {

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

	public void setButtonMatrix(Cell[][] buttons) {
		panelGrid.removeAll();

		for (int i = 0; i < buttons.length; i++) {
			for (int j = 0; j < buttons[i].length; j++) {
				model.entity.Cell currentButton = buttons[j][i];
				btnAux = new JButton();

				int buttonId = currentButton.getId();
				btnAux.setActionCommand(Integer.toString(buttonId));
				btnAux.setBackground(currentButton.getColor());
//				btnAux.setText("<html>Id: " + buttonId + "<br>" + currentButton.getPair().toString() + "</html>");

				btnsMatrix[j][i] = btnAux;
				panelGrid.add(btnAux);
			}
		}

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
		if (this.modeTest) btnTest.setBackground(this.nextColor);
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

	public void updateMatrix(java.util.List<Cell> list) {
		btnHelp.setBackground(colorDefault);
		
		for (Cell button : list) {
			int buttonId = button.getId();
			int x = button.getPair().getX();
			int y = button.getPair().getY();

			btnsMatrix[x][y].setActionCommand(Integer.toString(buttonId));
			btnsMatrix[x][y].setBackground(button.getColor());
		}

		panelGrid.revalidate();
		panelGrid.repaint();
	}
}
