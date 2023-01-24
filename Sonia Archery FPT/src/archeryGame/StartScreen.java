// Jan. 21, 2020
// Sonia Tinaz
// archeryGame, StartScreen
// Code for displaying and hiding the start screen

package archeryGame;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class StartScreen {

	// declaring variables for start screen
	Rectangle startScreen;
	Text gameTitle;
	Text startContinueText;
	
	// constructor 
	public StartScreen() {
		super();
		// sets 'startScreen' to a rectangle that fills the screen 
		// (the start screen background is a rectangle displayed behind text)
		this.startScreen = new Rectangle(0, 0, 1000, 700);
		// gameTitle is text that displays 'Archery'
		this.gameTitle = new Text(300, 150, "Archery");
		// startContinueText is text describing how to access the instructions screen
		this.startContinueText = new Text(275, 400, "Press 'I' for instructions");
	}

	// hides the start screen by making the start screen's background and text transparent
	public void hideStartScreen() {
		this.startScreen.setFill(Color.TRANSPARENT);
		this.gameTitle.setFill(Color.TRANSPARENT);
		this.startContinueText.setFill(Color.TRANSPARENT);
	}

	// displays the start screen
	public void displayStartScreen() {
		
		// positions the start screen so that it is placed in front of the map
		this.startScreen.setTranslateZ(-456);
		this.gameTitle.setTranslateZ(-456.99);
		this.startContinueText.setTranslateZ(-456.99);
		
		// sets the start screen's background to maroon
		this.startScreen.setFill(Color.MAROON);
		
		// makes the text on the start screen white 
		this.gameTitle.setFill(Color.WHITE);
		this.startContinueText.setFill(Color.WHITE);
		
		// changes the font and size of the start screen text
		this.gameTitle.setFont(Font.font("Forte", FontWeight.BOLD, 130)); 
		this.startContinueText.setFont(Font.font("Elephant", FontWeight.BOLD, 40)); 
		
	}
}
