// Jan. 21, 2020
// Sonia Tinaz
// archeryGame, InstructionsScreen
// Code for displaying and hiding the instructions screen

package archeryGame;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class InstructionsScreen {

	// declaring variables for instructions screen
	Rectangle instructionsScreen;

	Text welcomeText;
	Text instructionsText;
	Text instructionsContinueText;
	
	// constructor
	public InstructionsScreen() {
		super();
		// sets 'instructionsScreen' to a rectangle that fills the screen 
		// (the instructions screen background is a rectangle displayed behind text)
		this.instructionsScreen = new Rectangle(0, 0, 1000, 700);
		// welcomeText is text that reads 'welcome!'
		this.welcomeText = new Text(230, 150, "Welcome!");
		// instructionsText is text describing how to play the game
		this.instructionsText = new Text(57, 210, "\n You have 3 shots to try and hit the target! \n"
				+ " Hitting the outer ring gives you 1 point, \n 2 points for the middle ring, and 3 for a bullseye! \n"
				+ " Use the up and down arrow keys to control the launch angle. \n"
				+ " Hold the space bar to increase the arrow's initial velocity. \n"
				+ " Press the enter key to launch the arrow.");
		// instructionsContinueText is text describing how to start the game
		this.instructionsContinueText =  new Text(370, 630, "Press 'C' to continue");
	}


	// hides the instructions screen by making the instructions screen's background and text transparent
	public void hideInstructionsScreen() {
		this.instructionsScreen.setFill(Color.TRANSPARENT);
		this.welcomeText.setFill(Color.TRANSPARENT);
		this.instructionsText.setFill(Color.TRANSPARENT);
		this.instructionsContinueText.setFill(Color.TRANSPARENT);
	}

	// displays the instructions screen
	public void displayInstructionsScreen() {
	
		// positions the instructions screen so that it is placed in front of the map
		this.instructionsScreen.setTranslateZ(-456);
		this.welcomeText.setTranslateZ(-456.99);
		this.instructionsText.setTranslateZ(-456.99);
		this.instructionsContinueText.setTranslateZ(-456.99);
		
		// sets the instructions screen's background to a light grey colour
		this.instructionsScreen.setFill(Color.LIGHTGRAY);
		
		// changes the text on the instruction screen to shades of red 
		this.welcomeText.setFill(Color.MAROON);
		this.instructionsText.setFill(Color.FIREBRICK);
		this.instructionsContinueText.setFill(Color.MAROON);
		 
		// changes the font and size of the text on the instructions screen
		this.welcomeText.setFont(Font.font("Broadway", 110));  
		this.instructionsText.setFont(Font.font("Bauhaus 93", FontWeight.BOLD, 34));  
		this.instructionsContinueText.setFont(Font.font("Forte", FontWeight.BOLD, 60));  
		 
	}
	
}
