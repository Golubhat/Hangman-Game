#include <stdio.h>
#include <string.h>
#include <ctype.h>
#include <stdlib.h>
#include <time.h>

#define MAX_WORD_LENGTH 20
#define MAX_TRIES 6

// Array of words to guess
const char *words[] = {
    "apple", "banana", "cherry", "orange", "strawberry", "blueberry",
    "elephant", "giraffe", "zebra", "lion", "tiger", "cheetah",
    "computer", "keyboard", "mouse", "monitor", "printer",
    "mountain", "ocean", "desert", "forest", "river", "island",
    "football", "basketball", "soccer", "tennis", "volleyball", "baseball",
    "pizza", "hamburger", "spaghetti", "sushi", "sandwich", "pancake",
    "guitar", "piano", "violin", "trumpet", "drums", "flute",
    "sunflower", "rose", "tulip", "daisy", "lily", "carnation"};

// Function to choose a random word from the array
const char *chooseRandomWord()
{
    int numWords = sizeof(words) / sizeof(words[0]);
    int randomIndex = rand() % numWords;
    return words[randomIndex];
}

int main()
{
    srand(time(NULL));

    const char *wordToGuess = chooseRandomWord();
    int wordLength = strlen(wordToGuess);

    char guessedWord[MAX_WORD_LENGTH];
    memset(guessedWord, '_', wordLength);
    guessedWord[wordLength] = '\0';

    int incorrectGuesses = 0;

    printf("Welcome to Hangman!\n");

    char *guess, letters[20];
    int lettersUsed = 0;

    while (incorrectGuesses < MAX_TRIES)
    {
        guess = (char *)malloc(100 * sizeof(char));
        int approval;
        do
        {
            approval = 1;
            printf("\nWord to guess: %s\n", guessedWord);
            printf("Letters Used: ");
            for (int i = 0; i < lettersUsed; i++)
                printf("%c, ", letters[i]);
            printf("\nIncorrect guesses remaining: %d\n", MAX_TRIES - incorrectGuesses);

            printf("Enter a letter: ");
            scanf(" %[^\n]s", guess);

            guess[0] = tolower(guess[0]);
            if (strlen(guess) != 1 || !(guess[0] >= 'a' && guess[0] <= 'z'))
            {
                printf("\nEnter a single letter only!\n");
                approval = 0;
                continue;
            }

            for (int i = 0; i < lettersUsed; i++)
            {
                if (guess[0] == letters[i])
                {
                    printf("\nLetter is already guessed!\n");
                    approval = 0;
                    break;
                }
            }
        } while (!approval);

        letters[lettersUsed++] = guess[0];
        int found = 0;

        for (int i = 0; i < wordLength; i++)
        {
            if (wordToGuess[i] == *guess)
            {
                guessedWord[i] = *guess;
                found = 1;
            }
        }

        if (!found)
            incorrectGuesses++;

        // Check if the player has guessed the entire word
        if (strcmp(guessedWord, wordToGuess) == 0)
        {
            printf("\nWord to guess: %s\n", guessedWord);
            printf("Letters Used: ");
            for (int i = 0; i < lettersUsed; i++)
                printf("%c, ", letters[i]);
            printf("\nIncorrect guesses remaining: %d\n", MAX_TRIES - incorrectGuesses);

            printf("\nCongratulations! You've guessed the word: %s\n", wordToGuess);
            break;
        }
        free(guess);
    }

    if (incorrectGuesses == MAX_TRIES)
    {
        printf("\nWord to guess: %s\n", guessedWord);
        printf("Letters Used: ");
        for (int i = 0; i < lettersUsed; i++)
            printf("%c, ", letters[i]);
        printf("\nIncorrect guesses remaining: %d\n", MAX_TRIES - incorrectGuesses);

        printf("\nYou've run out of guesses. The word was: %s\n", wordToGuess);
    }

    system("pause");
}
