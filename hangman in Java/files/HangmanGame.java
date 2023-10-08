package files;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class HangmanGame extends JFrame {
    private String[] wordList = { "apple", "banana", "cherry", "orange", "strawberry", "blueberry",
            "elephant", "giraffe", "zebra", "lion", "tiger", "cheetah",
            "computer", "keyboard", "mouse", "monitor", "printer",
            "mountain", "ocean", "desert", "forest", "river", "island",
            "football", "basketball", "soccer", "tennis", "volleyball", "baseball",
            "pizza", "hamburger", "spaghetti", "sushi", "sandwich", "pancake",
            "guitar", "piano", "violin", "trumpet", "drums", "flute",
            "sunflower", "rose", "tulip", "daisy", "lily", "carnation" };
    private String secretWord;
    private String displayWord;
    private int attempts = 6;
    private StringBuilder guessedLetters = new StringBuilder();

    private JLabel wordLabel;
    private JLabel attemptsLabel;
    private JLabel guessesLabel;
    private JTextField guessField;
    private JButton guessButton;

    public HangmanGame() {
        setTitle("Hangman Game");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 1));

        wordLabel = new JLabel();
        add(wordLabel);

        attemptsLabel = new JLabel();
        add(attemptsLabel);

        guessesLabel = new JLabel();
        add(guessesLabel);

        guessField = new JTextField();
        add(guessField);

        guessButton = new JButton("Guess");
        add(guessButton);

        guessButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String guess = guessField.getText().toLowerCase();
                if (guess.length() == 1 && guess.matches("[a-z]")) {
                    if (guessedLetters.indexOf(guess) == -1) {
                        guessedLetters.append(guess + ", ");
                        guessesLabel.setText("Guesses: " + guessedLetters.toString());
                        checkGuess(guess.charAt(0));
                    } else {
                        JOptionPane.showMessageDialog(null, "You've already guessed that letter.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter a single letter.");
                }
                guessField.setText("");
            }
        });

        newGame();
    }

    private void newGame() {
        secretWord = wordList[new Random().nextInt(wordList.length)];
        displayWord = "_".repeat(secretWord.length());
        attempts = 6;
        guessedLetters.setLength(0);
        guessesLabel.setText("Guesses: ");
        updateDisplay();
    }

    private void updateDisplay() {
        wordLabel.setText(displayWord);
        attemptsLabel.setText("Attempts left: " + attempts);
    }

    private void checkGuess(char guess) {
        boolean correctGuess = false;
        for (int i = 0; i < secretWord.length(); i++) {
            if (secretWord.charAt(i) == guess) {
                displayWord = displayWord.substring(0, i) + guess + displayWord.substring(i + 1);
                correctGuess = true;
            }
        }

        if (!correctGuess) {
            attempts--;
        }

        updateDisplay();

        if (displayWord.equals(secretWord)) {
            JOptionPane.showMessageDialog(null, "Congratulations! You guessed the word: " + secretWord);
            newGame();
        } else if (attempts == 0) {
            JOptionPane.showMessageDialog(null, "Sorry, you ran out of attempts. The word was: " + secretWord);
            newGame();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new HangmanGame().setVisible(true);
            }
        });
    }
}
