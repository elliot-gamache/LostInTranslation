package translation;

import javax.swing.*;
import java.awt.event.*;

public class GUI {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Translator translator = new JSONTranslator();

            // Country Panel
            JPanel countryPanel = new JPanel();
            countryPanel.add(new JLabel("Country:"));

            JComboBox<String> countryComboBox = new JComboBox<>();
            for(String countryCode : translator.getCountryCodes()) {
                countryComboBox.addItem(countryCode);
            }
            countryPanel.add(countryComboBox);

            // Language Panel
            JPanel languagePanel = new JPanel();
            languagePanel.add(new JLabel("Language:"));

            JComboBox<String> languageComboBox = new JComboBox<>();
            for(String languageCode : translator.getLanguageCodes()) {
                languageComboBox.addItem(languageCode);
            }
            languagePanel.add(languageComboBox);

            // Button Panel
            JPanel buttonPanel = new JPanel();
            JButton submit = new JButton("Submit");
            buttonPanel.add(submit);

            JLabel resultLabelText = new JLabel("Translation:");
            buttonPanel.add(resultLabelText);
            JLabel resultLabel = new JLabel("\t\t\t\t\t\t\t");
            buttonPanel.add(resultLabel);

            // Submit Button Listener
            submit.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String country = (String) countryComboBox.getSelectedItem();
                    String language = (String) languageComboBox.getSelectedItem();

                    String result = translator.translate(country, language);
                    if (result == null) {
                        result = "no translation found!";
                    }
                    resultLabel.setText(result);
                }
            });

            // Main Panel
            JPanel mainPanel = new JPanel();
            mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
            mainPanel.add(countryPanel);
            mainPanel.add(languagePanel);
            mainPanel.add(buttonPanel);

            JFrame frame = new JFrame("Country Name Translator");
            frame.setContentPane(mainPanel);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setLocationRelativeTo(null); // place in centre of screen
            frame.setVisible(true);
        });
    }
}
