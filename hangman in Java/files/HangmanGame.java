package files;

import javax.swing.*;
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
    private JLabel imageLabel;

    public HangmanGame() {
        setTitle("Hangman Game");
        setSize(600, 900);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        wordLabel = new JLabel();
        wordLabel.setBounds(250, 10, 200, 20);
        add(wordLabel);

        attemptsLabel = new JLabel();
        attemptsLabel.setBounds(250, 50, 200, 20);
        add(attemptsLabel);

        guessesLabel = new JLabel();
        guessesLabel.setBounds(250, 90, 200, 20);
        add(guessesLabel);

        guessField = new JTextField();
        guessField.setBounds(250, 130, 100, 20);
        add(guessField);

        guessButton = new JButton("Guess");
        guessButton.setBounds(250, 170, 100, 20);
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

        imageLabel = new JLabel();
        add(imageLabel);

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

        remove(imageLabel);
        imageLabel.setIcon(new ImageIcon("Pictures\\" + Integer.toString(6 - attempts) + ".png"));
        imageLabel.setBounds(20, 210, 557, 605);
        add(imageLabel);

        int appWidth = getWidth(), appHeight = getHeight();
        setSize(1000, 1000);    // Arbitary values for the sake of changed size, in order to update image
        setSize(appWidth, appHeight);
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
