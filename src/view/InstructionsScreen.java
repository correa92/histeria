package view;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JButton;

import java.awt.event.ActionListener;

public class InstructionsScreen extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel titulo;
	private JTextArea txtIntrucciones;
	private JButton btnVolverMenu;

	public InstructionsScreen() {
		setLayout(null);

		titulo = new JLabel("Histeria");
		titulo.setFont(new Font("Arial", Font.BOLD, 24));
		titulo.setBounds(196, 11, 222, 36);
		add(titulo);

		txtIntrucciones = new JTextArea(10, 30);
		txtIntrucciones.setLineWrap(true);
		txtIntrucciones.setWrapStyleWord(true);
		txtIntrucciones.setEditable(false);
		txtIntrucciones.setText(
				"El Juego se trata de colorear todo el tablero, cada vez que clickees un cuadro se pintara de un color entre 5 que hay.\r\nEl nombre del juego, reside a que si este nuevo color coincide con los cuadros vecinos, el cuadro pulsado y todos los vecinos se despintaran, esto puede resultar bastante exasperante cuando se logra pintar gran parte del tablero. \r\n\r\nCuantas menos toques des a los cuadros, mas arriba estaras del ranking.\r\nTendras un apartado ayuda para saber cual color sugiere el Juego en el proximo movimiento. ");
		txtIntrucciones.setBounds(55, 58, 416, 267);
		add(txtIntrucciones);

		btnVolverMenu = new JButton("Volver al Menú");
		btnVolverMenu.setBounds(180, 340, 150, 30);
		add(btnVolverMenu);
	}

	public void addBackListener(ActionListener listener) {
		btnVolverMenu.addActionListener(listener);
	}
}