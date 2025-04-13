package view;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class DifficultyScreen extends JPanel {
	 private JButton btnEasy, btnMedium, btnHard, btnBack;

	    public DifficultyScreen() {

	        btnEasy = new JButton("Fácil (5x5)");
	        btnMedium = new JButton("Medio (7x7)");
	        btnHard = new JButton("Difícil (10x10)");
	        btnBack = new JButton("Volver");
	        btnBack.setBackground(new Color(255, 0, 0));

	        setLayout(new GridLayout(4, 1, 0, 20));
	        setBorder(BorderFactory.createEmptyBorder(50, 100, 50, 100));

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
