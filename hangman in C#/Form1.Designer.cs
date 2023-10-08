namespace hangman;
partial class HangmanForm
{
    private System.ComponentModel.IContainer components = null;

    protected override void Dispose(bool disposing)
    {
        if (disposing && (components != null))
        {
            components.Dispose();
        }
        base.Dispose(disposing);
    }

    private void InitializeComponent()
    {
        this.wordLabel = new System.Windows.Forms.Label();
        this.guessLabel = new System.Windows.Forms.Label();
        this.guessTextBox = new System.Windows.Forms.TextBox();
        this.guessButton = new System.Windows.Forms.Button();
        this.attemptsLabel = new System.Windows.Forms.Label();
        this.guessesLabel = new System.Windows.Forms.Label();
        this.SuspendLayout();
        // 
        // wordLabel
        // 
        this.wordLabel.AutoSize = true;
        this.wordLabel.Font = new System.Drawing.Font("Arial", 16F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
        this.wordLabel.Location = new System.Drawing.Point(12, 20);
        this.wordLabel.Name = "wordLabel";
        this.wordLabel.Size = new System.Drawing.Size(0, 26);
        this.wordLabel.TabIndex = 0;
        // 
        // guessLabel
        // 
        this.guessLabel.AutoSize = true;
        this.guessLabel.Location = new System.Drawing.Point(12, 60);
        this.guessLabel.Name = "guessLabel";
        this.guessLabel.Size = new System.Drawing.Size(67, 13);
        this.guessLabel.TabIndex = 1;
        this.guessLabel.Text = "Your Guess:";
        // 
        // guessTextBox
        // 
        this.guessTextBox.Location = new System.Drawing.Point(85, 57);
        this.guessTextBox.MaxLength = 1;
        this.guessTextBox.Name = "guessTextBox";
        this.guessTextBox.Size = new System.Drawing.Size(30, 20);
        this.guessTextBox.TabIndex = 2;
        // 
        // guessButton
        // 
        this.guessButton.Location = new System.Drawing.Point(121, 55);
        this.guessButton.Name = "guessButton";
        this.guessButton.Size = new System.Drawing.Size(75, 23);
        this.guessButton.TabIndex = 3;
        this.guessButton.Text = "Guess";
        this.guessButton.UseVisualStyleBackColor = true;
        this.guessButton.Click += new System.EventHandler(this.guessButton_Click);
        // 
        // attemptsLabel
        // 
        this.attemptsLabel.AutoSize = true;
        this.attemptsLabel.Location = new System.Drawing.Point(12, 100);
        this.attemptsLabel.Name = "attemptsLabel";
        this.attemptsLabel.Size = new System.Drawing.Size(0, 13);
        this.attemptsLabel.TabIndex = 4;
        // 
        // guessesLabel
        // 
        this.guessesLabel.AutoSize = true;
        this.guessesLabel.Location = new System.Drawing.Point(12, 130);
        this.guessesLabel.Name = "guessesLabel";
        this.guessesLabel.Size = new System.Drawing.Size(0, 13);
        this.guessesLabel.TabIndex = 5;
        // 
        // HangmanForm
        // 
        this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
        this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
        this.ClientSize = new System.Drawing.Size(300, 200);
        this.Controls.Add(this.guessesLabel);
        this.Controls.Add(this.attemptsLabel);
        this.Controls.Add(this.guessButton);
        this.Controls.Add(this.guessTextBox);
        this.Controls.Add(this.guessLabel);
        this.Controls.Add(this.wordLabel);
        this.Name = "HangmanForm";
        this.Text = "Hangman Game";
        this.ResumeLayout(false);
        this.PerformLayout();
    }

    private System.Windows.Forms.Label wordLabel;
    private System.Windows.Forms.Label guessLabel;
    private System.Windows.Forms.TextBox guessTextBox;
    private System.Windows.Forms.Button guessButton;
    private System.Windows.Forms.Label attemptsLabel;
    private System.Windows.Forms.Label guessesLabel;
}
