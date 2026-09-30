import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Map;

public class Main {

    // Extracted so the UI and the data/validation logic can be tested separately.
    private static final ResultManager resultManager = new ResultManager();
    private static final MedalStandings medalStandings = new MedalStandings();

    static {
        medalStandings.addCountry("USA", 12);
        medalStandings.addCountry("China", 10);
        medalStandings.addCountry("Kenya", 8);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Sports Federation Manager");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(900, 600);
            frame.setLayout(new BorderLayout());

            try {
                frame.setIconImage(new ImageIcon("emblem.png").getImage());
            } catch (Exception e) {
                System.out.println("Icon not found");
            }

            JLabel heading = new JLabel("National Sports Championship 2025", JLabel.CENTER);
            heading.setFont(new Font("Arial", Font.BOLD, 20));
            heading.setOpaque(true);
            heading.setBackground(new Color(0, 102, 204));
            heading.setForeground(Color.WHITE);
            heading.setPreferredSize(new Dimension(900, 50));
            frame.add(heading, BorderLayout.NORTH);

            JPanel nav = new JPanel(new GridLayout(5, 1, 5, 5));
            nav.setBorder(BorderFactory.createTitledBorder("Categories"));
            nav.setPreferredSize(new Dimension(180, 0));
            String[] cats = {"Athletics", "Swimming", "Cycling", "Football", "Basketball"};
            for (String c : cats) nav.add(new JButton(c));
            frame.add(nav, BorderLayout.WEST);

            JPanel medals = new JPanel(new GridLayout(0, 2, 5, 5));
            medals.setBorder(BorderFactory.createTitledBorder("Medal Standings"));
            medals.setPreferredSize(new Dimension(200, 0));
            medals.add(new JLabel("Country"));
            medals.add(new JLabel("Gold"));
            for (Map.Entry<String, Integer> entry : medalStandings.getSortedStandings()) {
                medals.add(new JLabel(entry.getKey()));
                medals.add(new JLabel(String.valueOf(entry.getValue())));
            }
            frame.add(medals, BorderLayout.EAST);

            JPanel center = new JPanel(new GridBagLayout());
            center.setBorder(BorderFactory.createTitledBorder("Enter Results"));
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(5, 5, 5, 5);
            gbc.fill = GridBagConstraints.HORIZONTAL;

            gbc.gridx = 0; gbc.gridy = 0;
            center.add(new JLabel("Athlete:"), gbc);
            gbc.gridx = 1;
            JTextField athleteField = new JTextField(15);
            center.add(athleteField, gbc);

            gbc.gridx = 0; gbc.gridy = 1;
            center.add(new JLabel("Event:"), gbc);
            gbc.gridx = 1;
            JComboBox<String> eventBox = new JComboBox<>(
                    ResultManager.VALID_EVENTS.toArray(new String[0]));
            center.add(eventBox, gbc);

            gbc.gridx = 0; gbc.gridy = 2;
            center.add(new JLabel("Result:"), gbc);
            gbc.gridx = 1;
            JTextField resultField = new JTextField(15);
            center.add(resultField, gbc);

            gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
            JButton submitButton = new JButton("Submit Result");
            center.add(submitButton, gbc);

            frame.add(center, BorderLayout.CENTER);

            JLabel footer = new JLabel("System ready. All services operational.", JLabel.LEFT);
            footer.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
            footer.setOpaque(true);
            footer.setBackground(new Color(230, 230, 230));
            frame.add(footer, BorderLayout.SOUTH);

            submitButton.addActionListener(e -> {
                try {
                    resultManager.addResult(
                            athleteField.getText(),
                            (String) eventBox.getSelectedItem(),
                            resultField.getText());
                    JOptionPane.showMessageDialog(frame, "Result submitted.");
                    athleteField.setText("");
                    resultField.setText("");
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(frame, ex.getMessage(),
                            "Invalid entry", JOptionPane.ERROR_MESSAGE);
                }
            });

            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
