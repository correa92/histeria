package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import model.entity.Score;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class ScoreScreen extends JPanel {

    private static final long serialVersionUID = 1L;
    private JTable scoreTable;
    private DefaultTableModel tableModel;
    private JButton btnBack;

    public ScoreScreen() {
        setBackground(new Color(245, 245, 245));
        setLayout(new BorderLayout(20, 20));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel title = new JLabel("Tabla de Puntajes");
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setForeground(new Color(60, 63, 65));
        add(title, BorderLayout.NORTH);

        String[] columnNames = {"Posición", "Jugador", "Puntos"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        scoreTable = new JTable(tableModel);
        scoreTable.setFont(new Font("Arial", Font.ITALIC, 16));
        scoreTable.setRowHeight(30);
        scoreTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));
        scoreTable.getTableHeader().setBackground(new Color(200, 220, 240));
        scoreTable.setBackground(new Color(230, 230, 250));
        scoreTable.setForeground(new Color(50, 50, 50));
        scoreTable.setGridColor(Color.GRAY);
        scoreTable.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(scoreTable);
        add(scrollPane, BorderLayout.CENTER);

        btnBack = new JButton("Volver al Menú");
        btnBack.setFont(new Font("Arial", Font.BOLD, 14));
        btnBack.setBackground(new Color(100, 149, 237));
        btnBack.setForeground(Color.WHITE);
        btnBack.setFocusPainted(false);
        btnBack.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        JPanel btnPanel = new JPanel();
        btnPanel.setBackground(getBackground());
        btnPanel.add(btnBack);
        add(btnPanel, BorderLayout.SOUTH);
    }

    public void addBackListener(ActionListener listener) {
        btnBack.addActionListener(listener);
    }

    public void updateScores(List<Score> scores) {
        tableModel.setRowCount(0);
        for (int i = 0; i < scores.size(); i++) {
            Score score = scores.get(i);
            Object[] row = {i + 1, score.getPlayerName(), score.getPoints()};
            tableModel.addRow(row);
        }
    }
}

