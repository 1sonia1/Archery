// Jan. 21, 2020
// Sonia Tinaz
// archeryGame, EndScreen
// Code for displaying and hiding the end screen

package archeryGame;

import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.paint.Color;

public class EndScreen {

	// declaring variables for end screen
	Rectangle endScreen;
	Text finalScoreText;
	Text endScreenInformation;

	// constructor 
	public EndScreen () {
		super();
		// sets 'endScreen' to a rectangle that fills the screen (the end screen background is a rectangle displayed behind text)
		this.endScreen = new Rectangle(0, 0, 1000, 700);
		// 'finalScoreText' is text that displays the user's score on the end screen
		this.finalScoreText = new Text(146, 150, "");
		// endScreenInformation text is text that says "You ran out of shots! Press 'R' to play again!" on the end screen
		this.endScreenInformation = new Text(150, 400, "You ran out of shots! \n" + "Press 'R' to play again!");
	}

	// hides the end screen by making the end screen's background and text transparent
	public void hideEndScreen() {
		this.endScreen.setFill(Color.TRANSPARENT);
		this.finalScoreText.setFill(Color.TRANSPARENT);
		this.endScreenInformation.setFill(Color.TRANSPARENT);
	}

	// displays the end screen
	public void displayEndScreen(int score) {
		
		// positions the end screen so that it is placed in front of the map
		this.endScreen.setTranslateZ(-456.99);
		this.finalScoreText.setTranslateZ(-456.99);
		this.endScreenInformation.setTranslateZ(-456.99);
		
		// makes the end screen's background maroon
		this.endScreen.setFill(Color.MAROON);
		// converts the user's score to a string so it can be used in text
		String finalScoreString = Integer.toString(score);
		// displays the user's score as white text
		this.finalScoreText.setText("Your score: " + finalScoreString);
		this.finalScoreText.setFill(Color.WHITE);
		// information for playing again gets displayed as light grey text
		this.endScreenInformation.setFill(Color.LIGHTGREY);
		
		// changes font and size of end screen text 
		this.finalScoreText.setFont(Font.font("Ravie", FontWeight.BOLD, 75));  
		this.endScreenInformation.setFont(Font.font("Forte", FontWeight.BOLD, 70));  
		
	}
}
