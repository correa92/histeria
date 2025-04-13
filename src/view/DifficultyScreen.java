package view;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class DifficultyScreen extends JPanel {
	 private JButton btnEasy, btnMedium, btnHard, btnBack;

	    public DifficultyScreen() {

	        btnEasy = new JButton("Fácil (5x5)");
	        btnEasy.setBounds(40, 44, 338, 23);
	        btnMedium = new JButton("Medio (7x7)");
	        btnMedium.setBounds(70, 97, 308, 42);
	        btnHard = new JButton("Difícil (10x10)");
	        btnHard.setBounds(80, 175, 276, 23);
	        btnBack = new JButton("Volver");
	        btnBack.setBounds(40, 222, 360, 67);
	        setLayout(null);

	        add(btnEasy);
	        add(btnMedium);
	        add(btnHard);
	        add(btnBack);
	    }

	    public void addEasyListener(ActionListener listener) {
	        btnEasy.addActionListener(listener);
	    }

	    public void addMediumListener(ActionListener listener) {
	        btnMedium.addActionListener(listener);
	    }

	    public void addHardListener(ActionListener listener) {
	        btnHard.addActionListener(listener);
	    }

	    public void addBackListener(ActionListener listener) {
	        btnBack.addActionListener(listener);
	    }

}
