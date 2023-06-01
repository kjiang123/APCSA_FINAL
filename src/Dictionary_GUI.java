import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Dictionary_GUI {
    private JFrame frame;
    private JPanel inputPanel;
    private JPanel searchPanel;
    private JTextField textField;
    private JTextArea textArea;
    private JButton submitButton;
    private JButton backButton;

    public Dictionary_GUI() {
        frame = new JFrame("User Input GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);
        frame.setLayout(new BorderLayout());

        // Create input panel
        inputPanel = new JPanel(new BorderLayout());

        JLabel label = new JLabel("Enter your Word:");
        inputPanel.add(label, BorderLayout.NORTH);

        textField = new JTextField();
        inputPanel.add(textField, BorderLayout.CENTER);

        submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String userInput = textField.getText();
                switchToSearchPanel(Dictionary.dictionaryDef(userInput));
            }
        });
        inputPanel.add(submitButton, BorderLayout.SOUTH);

        frame.add(inputPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private void switchToSearchPanel(String userInput) {
        frame.getContentPane().remove(inputPanel);

        // Create search panel
        searchPanel = new JPanel(new BorderLayout());
        searchPanel.setLayout(new BorderLayout());
        searchPanel.setPreferredSize(new Dimension(500, 500));

        JPanel searchFieldPanel = new JPanel();
        JTextField searchField = new JTextField();
        searchFieldPanel.add(searchField);
        searchPanel.add(searchFieldPanel, BorderLayout.NORTH);

        textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        DefaultCaret caret = (DefaultCaret) textArea.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        searchPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                switchToInputPanel();
            }
        });
        buttonPanel.add(backButton);
        searchPanel.add(buttonPanel, BorderLayout.SOUTH);

        frame.add(searchPanel, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();

        // Display the user input in the text area
        textArea.append(userInput + "\n");

        // Center the search panel on the screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        int xPos = (screenWidth - frame.getWidth()) / 2;
        int yPos = (screenHeight - frame.getHeight()) / 2;
        frame.setLocation(xPos, yPos);
    }
    private void switchToInputPanel() {
        frame.getContentPane().remove(searchPanel);
        frame.add(inputPanel, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
    }
}