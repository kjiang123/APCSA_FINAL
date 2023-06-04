import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DictionaryGUI extends JFrame {
    private JPanel searchPanel;
    private JPanel resultPanel;
    private JLabel titleLabel;
    private JTextField inputField;
    private JButton submitButton;
    private JButton backButton;
    private JTextArea displayArea;

    public DictionaryGUI() {
        initializeComponents();
        setupLayout();
        setupListeners();
    }

    private void initializeComponents() {
        searchPanel = new JPanel();
        resultPanel = new JPanel();
        titleLabel = new JLabel("Dictionary");
        inputField = new JTextField(20);
        submitButton = new JButton("Submit");
        backButton = new JButton("Back");
        displayArea = new JTextArea(10, 30);
        displayArea.setLineWrap(true);
        displayArea.setWrapStyleWord(true);
        displayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayArea);

        searchPanel.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(10, 0, 10, 0);
        searchPanel.add(titleLabel, constraints);

        constraints.gridy = 1;
        searchPanel.add(inputField, constraints);

        constraints.gridy = 2;
        searchPanel.add(submitButton, constraints);

        resultPanel.setLayout(new BorderLayout());
        JPanel resultContentPanel = new JPanel(new BorderLayout());
        resultContentPanel.add(scrollPane, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(backButton);
        resultContentPanel.add(buttonPanel, BorderLayout.SOUTH);
        resultPanel.add(resultContentPanel, BorderLayout.CENTER);
    }

    private void setupLayout() {
        setContentPane(searchPanel);
        setTitle("Dictionary");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void setupListeners() {
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = inputField.getText();
                displayArea.append(input + "\n");
                inputField.setText("");
                setContentPane(resultPanel);
                revalidate();
                repaint();
                inputField.requestFocus();
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setContentPane(searchPanel);
                revalidate();
                repaint();
                inputField.requestFocus();
            }
        });
    }
}