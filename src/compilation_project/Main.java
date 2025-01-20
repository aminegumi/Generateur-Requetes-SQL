package compilation_project;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.StringReader;
import java.util.List;
import java.util.ArrayList;

public class Main {
    private static JFrame frame;
    private static JTextArea inputArea;
    private static JTextArea outputArea;
    private static JButton generateButton;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    private static void createAndShowGUI() {
        frame = new JFrame("SQL Generator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        inputArea = new JTextArea(20, 40);
        outputArea = new JTextArea(20, 40);
        outputArea.setEditable(false);
        generateButton = new JButton("Générer SQL");

      
        inputArea.setText(
            "table utilisateurs:\n" +
            "- id (entier, clé primaire, auto)\n" +
            "- nom (texte, requis)\n" +
            "- email (texte, unique)\n" +
            "- date_creation (date, par défaut)\n\n" +
            "table articles:\n" +
            "- id (entier, clé primaire, auto)\n" +
            "- titre (texte, requis)\n" +
            "- contenu (texte)\n" +
            "- auteur_id (entier, requis, référence: utilisateurs)\n"
        );

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JSplitPane splitPane = new JSplitPane(
            JSplitPane.HORIZONTAL_SPLIT,
            new JScrollPane(inputArea),
            new JScrollPane(outputArea)
        );
        splitPane.setResizeWeight(0.5);

        mainPanel.add(splitPane, BorderLayout.CENTER);
        mainPanel.add(generateButton, BorderLayout.SOUTH);

        frame.add(mainPanel);

        generateButton.addActionListener(e -> generateSQL());

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static void generateSQL() {
        try {
            SQLGenerator parser = new SQLGenerator(new StringReader(inputArea.getText()));
            parser.Parse();
            outputArea.setText(parser.getGeneratedSQL());
        } catch (ParseException e) {
            outputArea.setText("Parsing error: " + e.getMessage());
        }
    }


}