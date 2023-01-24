// Jan. 21, 2020
// Sonia Tinaz
// archeryGame, Score
// Code for displaying the user's score as text

package archeryGame;

import javafx.scene.text.Text;

public class Score {
	// declaring variables for the score
	String scoreString;
	Text scoreText;

	// constructor
	public Score() {
		super();
		// scoreString will contain the user's score as a string 
		this.scoreString = "";
		// scoreText is text that will display the user's score
		this.scoreText = new Text(10, 150, scoreString);
	}

	// getters and setters
	public String getScoreString() {
		return scoreString;
	}

	public void setScoreString(String scoreString) {
		this.scoreString = scoreString;
	}

	public Text getScoreText() {
		return scoreText;
	}

	public void setScoreText(Text scoreText) {
		this.scoreText = scoreText;
	}
	
	// displays the user's score as text, given the user's score
	public void displayScore(int score) {
		// converts the user's score to a string
		this.scoreString = Integer.toString(score);
		// using the user's score as a string, displays the user's score
		this.scoreText.setText("Score: " + scoreString);
	}

}
