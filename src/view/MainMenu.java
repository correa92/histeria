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
	private Image backgroundImage;
	private JButton btnEasy, btnMedium, btnHard;
	

	public MainMenu() {

		backgroundImage = new ImageIcon(getClass().getResource("/images/fondo_juego.jpg")).getImage();
        
        btnEasy = new JButton("Nivel Fácil: 5x5");
        btnMedium = new JButton("Nivel Medio: 7x7");
        btnHard = new JButton("Nivel Difícil: 10x0");
   
        // Ocultar botones de dificultad al inicio
        btnEasy.setVisible(false);
        btnMedium.setVisible(false);
        btnHard.setVisible(false);
      
		setLayout(null);
    
    // Método para mostrar los botones de dificultad cuando el usuario presiona "Jugar"
    public void showDifficultyButtons() {
        btnEasy.setVisible(true);
        btnMedium.setVisible(true);
        btnHard.setVisible(true);
    }

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

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (backgroundImage != null) {
			g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
		}
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(600, 500);
	}

    public void addDifficultyListeners(ActionListener easy, ActionListener medium, ActionListener hard) {
        btnEasy.addActionListener(easy);
        btnMedium.addActionListener(medium);
        btnHard.addActionListener(hard);
    }
}


