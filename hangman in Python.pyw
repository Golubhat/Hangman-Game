import tkinter as tk
from tkinter import messagebox
from PIL import ImageTk, Image
import random

class HangmanGame:
    def __init__(self, root):
        self.root = root
        self.root.title("Hangman Game")

        self.word_list = [
        "apple", "banana", "cherry", "orange", "strawberry", "blueberry",
        "elephant", "giraffe", "zebra", "lion", "tiger", "cheetah",
        "computer", "keyboard", "mouse", "monitor", "printer",
        "mountain", "ocean", "desert", "forest", "river", "island",
        "football", "basketball", "soccer", "tennis", "volleyball", "baseball",
        "pizza", "hamburger", "spaghetti", "sushi", "sandwich", "pancake",
        "guitar", "piano", "violin", "trumpet", "drums", "flute",
        "sunflower", "rose", "tulip", "daisy", "lily", "carnation"
        ]
        self.secret_word = ""
        self.display_word = ""
        self.attempts = 6
        self.guessed_letters = []

        self.word_label = tk.Label(root, text="", font=("Arial", 16, "bold"))
        self.word_label.pack()

        self.guess_label = tk.Label(root, text="Your Guess:", font=("Arial", 12))
        self.guess_label.pack()

        self.guess_entry = tk.Entry(root, font=("Arial", 12))
        self.guess_entry.pack()

        self.guess_button = tk.Button(root, text="Guess", command=self.check_guess, font=("Arial", 12))
        self.guess_button.pack()

        self.attempts_label = tk.Label(root, text="", font=("Arial", 12))
        self.attempts_label.pack()

        self.guesses_label = tk.Label(root, text="", font=("Arial", 12))
        self.guesses_label.pack()

        self.hangman_status = tk.Button(root)
        self.hangman_status.pack()

        self.new_game()

    def new_game(self):
        self.secret_word = random.choice(self.word_list)
        self.display_word = "_" * len(self.secret_word)
        self.attempts = 6
        self.guessed_letters = []
        self.update_display()

    def update_display(self):
        self.word_label.config(text=self.display_word)
        self.attempts_label.config(text=f"Attempts left: {self.attempts}")
        self.guesses_label.config(text=f"Guesses: {', '.join(self.guessed_letters)}")

        self.hangman_status.destroy()
        self.hangmanPicture = ImageTk.PhotoImage(file=r"Pictures\\"+str(6-self.attempts)+".png")
        self.hangman_status = tk.Button(root, image=self.hangmanPicture)
        self.hangman_status.pack()

    def check_guess(self):
        guess = self.guess_entry.get().lower()

        if len(guess) != 1 or not guess.isalpha():
            self.guess_entry.delete(0, len(guess))
            tk.messagebox.showerror("Invalid Guess", "Please enter a single letter.")
            return

        if guess in self.guessed_letters:
            self.guess_entry.delete(0, len(guess))
            tk.messagebox.showinfo("Already Guessed", "You've already guessed that letter.")
            return

        self.guessed_letters.append(guess)

        if guess in self.secret_word:
            for i in range(len(self.secret_word)):
                if self.secret_word[i] == guess:
                    self.display_word = self.display_word[:i] + guess + self.display_word[i+1:]
        else:
            self.attempts -= 1

        self.guess_entry.delete(0, len(guess))
        self.update_display()

        if self.display_word == self.secret_word:
            tk.messagebox.showinfo("Congratulations!", f"You guessed the word: {self.secret_word}")
            self.new_game()
        elif self.attempts == 0:
            tk.messagebox.showinfo("Game Over", f"Sorry, you ran out of attempts. The word was: {self.secret_word}")
            self.new_game()

if __name__ == "__main__":
    root = tk.Tk()
    app = HangmanGame(root)
    root.mainloop()
