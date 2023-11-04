namespace hangman
{
    public partial class HangmanForm : Form
    {
        private readonly string[] wordList = { "apple", "banana", "cherry", "orange", "strawberry", "blueberry",
    "elephant", "giraffe", "zebra", "lion", "tiger", "cheetah",
    "computer", "keyboard", "mouse", "monitor", "printer",
    "mountain", "ocean", "desert", "forest", "river", "island",
    "football", "basketball", "soccer", "tennis", "volleyball", "baseball",
    "pizza", "hamburger", "spaghetti", "sushi", "sandwich", "pancake",
    "guitar", "piano", "violin", "trumpet", "drums", "flute",
    "sunflower", "rose", "tulip", "daisy", "lily", "carnation" };
        private string secretWord;
        private string displayWord;
        private int attempts = 6;
        private string guessedLetters = "";

        public HangmanForm()
        {
            InitializeComponent();
            NewGame();
        }

        private void NewGame()
        {
            secretWord = wordList[new Random().Next(wordList.Length)];
            displayWord = new string('_', secretWord.Length);
            attempts = 6;
            guessedLetters = "";
            UpdateDisplay();
        }

        private void UpdateDisplay()
        {
            wordLabel.Text = displayWord;
            attemptsLabel.Text = $"Attempts left: {attempts}";
            guessesLabel.Text = $"Guesses: {guessedLetters}";
            hangmanPicture.ImageLocation = "Pictures/" + Convert.ToString(6 - attempts) + ".png";
        }

        private void CheckGuess(char guess)
        {
            if (secretWord.Contains(guess))
            {
                for (int i = 0; i < secretWord.Length; i++)
                {
                    if (secretWord[i] == guess)
                    {
                        displayWord = displayWord.Remove(i, 1).Insert(i, guess.ToString());
                    }
                }
            }
            else
                attempts--;
            guessedLetters += guess + ", ";

            UpdateDisplay();

            if (displayWord == secretWord)
            {
                MessageBox.Show($"Congratulations! You guessed the word: {secretWord}");
                NewGame();
            }
            else if (attempts == 0)
            {
                MessageBox.Show($"Sorry, you ran out of attempts. The word was: {secretWord}");
                NewGame();
            }
        }

        private void guessButton_Click(object sender, EventArgs e)
        {
            if (guessTextBox.Text.Length == 1)
            {
                char guess = guessTextBox.Text.ToLower()[0];
                if (!char.IsLetter(guess))
                {
                    MessageBox.Show("You must enter a letter.");
                }
                else if (guessedLetters.Contains(guess.ToString()))
                {
                    MessageBox.Show("You've already guessed that letter.");
                }
                else
                {
                    CheckGuess(guess);
                }
                guessTextBox.Text = "";
            }
            else
            {
                MessageBox.Show("Please enter a single letter.");
            }
        }
    }
}
