package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainMenu extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton btnPlay, btnScores, btnExit, btnInstrucciones;
//	private Image backgroundImage;

	public MainMenu() {

//		backgroundImage = new ImageIcon(getClass().getResource("/images/fondo_juego.jpg")).getImage();

		setLayout(null);

		btnPlay = new JButton("Jugar");
		btnPlay.setBounds(152, 100, 268, 44);

		btnScores = new JButton("Historial de Puntajes");
		btnScores.setBounds(152, 170, 268, 44);

		btnInstrucciones = new JButton("Instrucciones");
		btnInstrucciones.setBounds(152, 240, 268, 44);

		btnExit = new JButton("Salir");
		btnExit.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnExit.setBackground(new Color(255, 0, 0));
		btnExit.setForeground(new Color(0, 0, 0));
		btnExit.setBounds(213, 397, 135, 29);

		add(btnPlay);
		add(btnScores);
		add(btnInstrucciones);
		add(btnExit);

	}

	public void addPlayListener(ActionListener listener) {
		btnPlay.addActionListener(listener);
	}

	public void addScoresListener(ActionListener listener) {
		btnScores.addActionListener(listener);
	}

	public void addInstruccionesListener(ActionListener listener) {
		btnInstrucciones.addActionListener(listener);
	}

	public void addExitListener(ActionListener listener) {
		btnExit.addActionListener(listener);
	}
//
//	@Override
//	protected void paintComponent(Graphics g) {
//		super.paintComponent(g);
//		if (backgroundImage != null) {
//			g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
//		}
//	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(600, 500);
	}

}
