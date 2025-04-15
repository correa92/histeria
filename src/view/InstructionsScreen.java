package view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import javax.swing.JScrollPane;
import java.awt.Color;

public class InstructionsScreen extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel titulo;
	private JTextArea txtIntrucciones;
	private JButton btnVolverMenu;
	private JScrollPane scrollPane;

	public InstructionsScreen() {
		setLayout(null);

		titulo = new JLabel("Histeria");
		titulo.setFont(new Font("Arial", Font.BOLD, 24));
		titulo.setBounds(231, 11, 222, 36);
		add(titulo);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(99, 58, 416, 267);
		add(scrollPane);

		txtIntrucciones = new JTextArea(10, 30);
		scrollPane.setViewportView(txtIntrucciones);
		txtIntrucciones.setLineWrap(true);
		txtIntrucciones.setWrapStyleWord(true);
		txtIntrucciones.setEditable(false);
		txtIntrucciones.setText(
				"El Juego se trata de colorear todo el tablero, cada vez que clickees un cuadro se pintara de un color entre 5 que hay.\r\nEl nombre del juego, reside a que si este nuevo color coincide con los cuadros vecinos, el cuadro pulsado y todos los vecinos se despintaran, esto puede resultar bastante exasperante cuando se logra pintar gran parte del tablero. \r\n\r\nCuantas menos toques des a los cuadros, mas arriba estaras del ranking.\r\nTendras un apartado ayuda para saber cual color sugiere el Juego en el proximo movimiento, solo tienes 3 posibilidades de ayuda para no equivocarte y se \"despinte\" el cuadro. \r\n\r\nEl juego se divide en 3 niveles de dificultad: \r\n-Facil (MATRIZ 5X5)\r\n-Medio (MATRIZ 7X7)\r\n-Dificil (MATRIZ 10X10)\r\n\r\nSi ganas, le juego te pedira tu nombre y guardara tus records en un ranking. \r\n\r\nEsto es HISTERIA, ¿Estas listo para desafiar tu Cordura?");

		btnVolverMenu = new JButton("Volver al Menú");
		btnVolverMenu.setBackground(Color.RED);
		btnVolverMenu.setBounds(227, 382, 150, 30);
		add(btnVolverMenu);
	}

	public void addBackListener(ActionListener listener) {
		btnVolverMenu.addActionListener(listener);
	}
}