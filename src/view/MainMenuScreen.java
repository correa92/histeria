package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainMenuScreen extends JPanel {

	private static final long serialVersionUID = 1L;
	private JButton btnPlay, btnScores, btnExit, btnInstrucciones;

	public MainMenuScreen() {

        setLayout(new GridLayout(4, 1, 0, 20));
        setBorder(BorderFactory.createEmptyBorder(60, 150, 60, 150));

        btnPlay = new JButton("Jugar");

        btnScores = new JButton("Historial de Puntajes");

        btnInstrucciones = new JButton("Instrucciones");

        btnExit = new JButton("Salir");
        btnExit.setBackground(new Color(255, 0, 0));
        btnExit.setForeground(Color.BLACK);

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

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(600, 500);
	}

}
