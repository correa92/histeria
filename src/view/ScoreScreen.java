package view;

import javax.swing.*;

import model.entity.Score;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class ScoreScreen extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextArea scoreArea;
    private JButton btnBack;

    public ScoreScreen() {
        setLayout(new BorderLayout());

        scoreArea = new JTextArea();
        scoreArea.setEditable(false);

        btnBack = new JButton("Volver al Menú");

        add(new JScrollPane(scoreArea), BorderLayout.CENTER);
        add(btnBack, BorderLayout.SOUTH);
    }

    public void addBackListener(ActionListener listener) {
        btnBack.addActionListener(listener);
    }

    public void updateScores(List<Score> scores) {
        scoreArea.setText("");
        
        for (int i = 0; i < scores.size(); i++) {
        	scoreArea.append((i+1)+" - " + scores.get(i).getPlayerName() + ": " + scores.get(i).getPoints() + " puntos\n");			
		}
        
        for (Score score : scores) {
        }
    }
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(600, 500); 
    }
}
