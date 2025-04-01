package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.entity.Button;

public class GameScreen extends JPanel {

	private static final long serialVersionUID = 1L;
	private JButton btnBack, btnAux, btnColor;
	private JLabel lblScore;
	private JPanel panelGrid;
	private JButton[][] btnsMatrix;
	private int score = 0;
	private JFrame _frame;

	public GameScreen(int row, int column, JFrame frame) {

		_frame = frame;
		btnsMatrix = new JButton[row][column];
		setLayout(new BorderLayout());

		lblScore = new JLabel("Puntos: " + score, SwingConstants.CENTER);
		lblScore.setFont(new Font("Arial", Font.BOLD, 20));

		btnBack = new JButton("Volver al Menú");

		btnColor = new JButton("Ayuda");
		btnColor.setBounds(50, 50, 50, 50);

		JPanel panelTop = new JPanel();
		panelTop.setLayout(new BorderLayout());
		panelTop.add(btnColor, BorderLayout.EAST);
		panelTop.add(lblScore, BorderLayout.WEST);

		add(panelTop, BorderLayout.NORTH);
		add(btnBack, BorderLayout.SOUTH);

		panelGrid = new JPanel();
		panelGrid.setLayout(new GridLayout(row, column, 0, 0));

		add(panelGrid, BorderLayout.CENTER);
	}

	public void addBackListener(ActionListener listener) {
		btnBack.addActionListener(listener);
	}

	public void setButtonMatrix(Button[][] buttons) {
		panelGrid.removeAll();

		for (int i = 0; i < buttons.length; i++) {
			for (int j = 0; j < buttons[i].length; j++) {
				model.entity.Button currentButton = buttons[j][i];
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

	public void updateNextColor(Color nextColor) {
		btnColor.setBackground(nextColor);
	}

	public void isWinner(boolean value) {
		if (value) {
			String name = JOptionPane.showInputDialog("¡Felicitaciones, has ganado!\nIngresa tu nombre:");

			while (name.trim().isEmpty()) {
				name = JOptionPane.showInputDialog("Por favor, debe ingresar su nombre:");
			}
		}
	}

	public void updateMatrix(java.util.List<Button> list) {

		for (Button button : list) {
			int buttonId = button.getId();
			int x = button.getPair().getX();
			int y = button.getPair().getY();

			btnsMatrix[x][y].setActionCommand(Integer.toString(buttonId));
			btnsMatrix[x][y].setBackground(button.getColor());
//			btnsMatrix[x][y].setText("<html>Id: " + buttonId + "<br>" + button.getPair().toString() + "</html>");

			btnsMatrix[x][y].setBackground(button.getColor());

		}

		panelGrid.revalidate();
		panelGrid.repaint();
	}
}
