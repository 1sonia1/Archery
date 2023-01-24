// Jan. 21, 2020
// Sonia Tinaz
// archeryGame, ArcheryController
// Code that makes the archery game functional 
// displays the objects used in the game (i.e. the arrow)
// calls the appropriate methods to make the game work 
// (i.e. calls drawArrow to display the arrow during its' flight path)

package archeryGame;

import java.util.ArrayList;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Camera;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.PointLight;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.text.Font;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

public class ArcheryController {

	Scene gameScene;
	Stage stage;

	// declaring variables for the game
	Arrow arrow;
	Score score;
	Target target;
	EndScreen endScreen;
	StartScreen startScreen;
	InstructionsScreen instructionsScreen;

	Box backWall;
	Box ground;
	Box launchPad;
	Box safetyNet;
	Box ceiling;
	Camera camera;
	PointLight light;

	int points = 0;
	int throwCount = 0;

	boolean collectPoints = true;
	boolean restartShot = false;
	boolean playGame = false;

	boolean collisionInnerTarget = false;
	boolean collisionOuterTarget = false;
	boolean collisionMiddleTarget = false;

	boolean collisionBackWall = false;
	boolean collisionGround = false;
	boolean collisionSafetyNet = false;
	boolean collisionCeiling = false;

	public void getScene(Stage primaryStage) {

		// creates instance of arrow, score text, the target, start screen, instructions screen, and end screen
		arrow = new Arrow();
		score = new Score();
		target = new Target();
		endScreen = new EndScreen();
		startScreen = new StartScreen();
		instructionsScreen = new InstructionsScreen();

		// creating each part of the map by creating 3D boxes (each part of the map is a 3D box)
		// for instance, the ground is a 3D box with a width of 1100px, height of 1px, and depth of 1100px
		// stores each box in corresponding variable (i.e. box for ground gets stored in 'ground')
		ground = new Box(1100, 1, 1100);
		ceiling = new Box(1100, 10, 1500);
		backWall = new Box(1100, 750, 10);
		launchPad = new Box(1100, 1, 205);
		/*
		 *  safetyNet is a box below the map that makes the 
		 *  arrow go back to its' starting position upon the arrow colliding with it.
		 *  It ensures the arrow is unable to leave the game map.
		 */
		safetyNet = new Box(1100, 1, 1100);
		
		// creating a light to illuminate the map 
		light = new PointLight();

		// 'group' is a collection of objects. 
		// When making adjustments to 'group', you are adjusting all of the objects part of 'group'
		// in this game, group will be a collection of all the objects involved in the game
		// for instance, group will contain the 3D box for the arrow, target, and ground
		Group group = new Group();

		// adding all of the objects involved in the game to 'group' 
		// for example, adds the text for the arrow's initial velocity to 'group'
		group.getChildren().add(endScreen.endScreen);
		group.getChildren().add(endScreen.finalScoreText);
		group.getChildren().add(endScreen.endScreenInformation);

		group.getChildren().add(startScreen.startScreen);
		group.getChildren().add(startScreen.gameTitle);
		group.getChildren().add(startScreen.startContinueText);

		group.getChildren().add(instructionsScreen.instructionsScreen);
		group.getChildren().add(instructionsScreen.welcomeText);
		group.getChildren().add(instructionsScreen.instructionsText);
		group.getChildren().add(instructionsScreen.instructionsContinueText);

		group.getChildren().add(ceiling);
		group.getChildren().add(ground);
		group.getChildren().add(backWall);
		group.getChildren().add(launchPad);
		group.getChildren().add(safetyNet);

		group.getChildren().add(arrow.arrow);

		group.getChildren().add(target.innerTarget);
		group.getChildren().add(target.middleTarget);
		group.getChildren().add(target.outerTarget);

		group.getChildren().add(arrow.velocityText);
		group.getChildren().add(arrow.angleText);
		group.getChildren().add(score.scoreText);

		group.getChildren().add(arrow.powerBar);

		group.getChildren().add(light);

		// creating a stationary camera used to view the map
		// what the camera sees, the user will see
		camera = new PerspectiveCamera();
		// attaches all of the objects contained in 'group' to the game scene
		// this allows the game objects to be displayed when the scene is set  
		Scene scene = new Scene(group, 1000, 800, true);

		// sets the scene/background to a light grey colour
		scene.setFill(Color.LIGHTGREY);
		// attaches the camera to the scene, allowing the user to view the game map
		scene.setCamera(camera);

		// displays the scene (displays the game map)
		primaryStage.setScene(scene);
		primaryStage.show();
		gameScene = primaryStage.getScene();

	}

	// method for setting up the map
	// properly aligns/positions each part of the map and changes the appearance of each box involved in the map
	// displays the start screen after the map is set up
	public void startGame() {

		// Moves the ground down and ceiling up so that the ceiling is above the ground
		ground.setTranslateX(500);
		ground.setTranslateY(500);
		ground.setTranslateZ(300);

		ceiling.setTranslateX(500);
		ceiling.setTranslateY(-150);
		ceiling.setTranslateZ(100);

		// moves the launch pad so that it is placed in front of the ground
		launchPad.setTranslateX(500);
		launchPad.setTranslateY(500);
		launchPad.setTranslateZ(-353);

		// moves the safety net so that it is underneath the ground
		safetyNet.setTranslateX(1000 / 2);
		safetyNet.setTranslateY(600);
		safetyNet.setTranslateZ(100);

		// places the light above the ground to properly illuminate the game map
		light.setTranslateX(200);
		light.setTranslateY(-100);
		light.setTranslateZ(100);
		
		// moves the back wall so that is behind the ground (at the far end of the map)
		backWall.setTranslateX(1000 / 2);
		backWall.setTranslateY(200);
		backWall.setTranslateZ(800);

		// formats text that displays the initial velocity, launch angle and user's score
		// angle, velocity, and score text is now 30px and in the font 'elephant'
		arrow.velocityText.setFont(Font.font("Elephant", 30));
		arrow.angleText.setFont(Font.font("Elephant", 30));
		score.scoreText.setFont(Font.font("Elephant", 30));

		// Makes the ground look like grass by setting its' faces to a picture of grass
		PhongMaterial grass = new PhongMaterial();
		grass.setDiffuseMap(new Image("images/grass.png"));
		ground.setMaterial(grass);

		// Gives the launch pad a white concrete texture by setting its' faces to a picture of white concrete
		PhongMaterial launchPadTexture = new PhongMaterial();
		launchPadTexture.setDiffuseMap(new Image("images/launchpad.jpg"));
		launchPad.setMaterial(launchPadTexture);

		// Gives the back wall a light grey concrete texture by setting its' faces to a picture of grey concrete
		PhongMaterial wallTexture = new PhongMaterial();
		wallTexture.setDiffuseMap(new Image("images/concrete.jpg"));
		backWall.setMaterial(wallTexture);

		// solely displays the start screen by calling the displayStartScreen method
		startScreen.displayStartScreen();
		endScreen.hideEndScreen();
		instructionsScreen.hideInstructionsScreen();

		// moves the camera back so the user can see the entirety of the start screen
		camera.setTranslateZ(-400);
		
		
		// The target is initially a grey cylinder standing right side up.
		// 'drawTarget' method rotates the target so that the circle face of the target is towards the user
		// this method also changes the colours of the target.
		
		// calls 'drawTarget' to display the colourful target in its' proper position 
		target.drawTarget();
		// calls 'resetArrow' to position the arrow at its' starting/initial coordinates, and to set the launch angle and velocity to zero
		arrow.resetArrow();

	}

	// method for resetting the player's point of view of the map
	// once the arrow is launched, the map will be slightly tilted
	// 'resetCameraPerspective' resets the user's point of view so that the map no longer looks tilted
	public void resetCameraPerspective() {
		
		// moves the camera, angle, velocity and score text, as well as the power bar backwards
		// (everything was moved forward once the arrow was launched)
		camera.setTranslateZ(315);
		arrow.velocityText.setTranslateZ(315);
		arrow.angleText.setTranslateZ(315);
		score.scoreText.setTranslateZ(315);
		arrow.powerBar.setTranslateZ(315);
		arrow.powerBar.setTranslateX(5);

		// changes the camera's angle back to 0, so the map no longer looks tilted
		camera.setRotationAxis(Rotate.Y_AXIS);
		camera.setRotate(0);

		// rotates the angle, velocity, and score text back to 0 degrees so the text no longer looks slanted/titled.
		arrow.velocityText.setRotationAxis(Rotate.Y_AXIS);
		arrow.velocityText.setRotate(0);

		arrow.angleText.setRotationAxis(Rotate.Y_AXIS);
		arrow.angleText.setRotate(0);

		score.scoreText.setRotationAxis(Rotate.Y_AXIS);
		score.scoreText.setRotate(0);

		arrow.powerBar.setRotationAxis(Rotate.Y_AXIS);
		arrow.powerBar.setRotate(0);
	}

	public void gameLoop() {
		// calls the startGame method to set up the map and display the start screen
		startGame();

		// creates array list ('input') that stores the user's keyboard input
		// 'input' is used to control the arrow's angle and velocity by pressing arrow keys and the space bar
		ArrayList<String> input = new ArrayList<String>();
		
		// adds the key the user is pressing to 'input' array list as a string
		gameScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent e) {
				String code = e.getCode().toString();
				if (!input.contains(code))
					input.add(code);
			}
		});

		// removes the pressed key from 'input' once the user stops pressing it
		gameScene.setOnKeyReleased(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent e) {
				String code = e.getCode().toString();
				if (input.contains(code))
					input.remove(code);
			}

		});

		new AnimationTimer() {
			@Override
			public void handle(long currentNanoTime) {

				// 'playGame' will be false if the game has not started yet
				// this ensures the instructions screen won't be displayed during the game if the user presses 'I'
				if (playGame == false) {
					// if the game has not started yet, an instruction screen will be displayed once the user presses 'I'
					if (input.contains("I")) {
						// stops showing the start screen by calling the 'hideStartScreen' method
						// displays the instructions screen by calling the 'displayInstructionsScreen' method
						startScreen.hideStartScreen();
						instructionsScreen.displayInstructionsScreen();
					}

					// Starts the game by hiding the instructions screen once user presses "c"
					if (input.contains("C")) {
						// calls the resetCameraPerspective to properly adjust the player's point of view of the map
						resetCameraPerspective();
						// hides the instructions screen by calling the 'hideInstructionsScreen' method
						instructionsScreen.hideInstructionsScreen();
						// sets 'playGame' to true as the game has now started
						playGame = true;
					}
				}

				// variables for arrow colliding with various parts of the map
				// if these collision variables are true, it means the arrow is touching the specified map part
			
				// for instance, 'collisionInnerTarget' tells us if the arrow is colliding with the bullseye of the target
				// generates a boolean value for 'collisionInnerTarget' by calling the 'collisionTarget' method, and passing the inner target cylinder as a parameter
				// 'collisionTarget' will return true if the arrow is touching the bullseye
				collisionInnerTarget = arrow.collisionTarget(target.innerTarget);
				collisionMiddleTarget = arrow.collisionTarget(target.middleTarget);
				collisionOuterTarget = arrow.collisionTarget(target.outerTarget);
				collisionBackWall = arrow.collisionMap(backWall);
				collisionGround = arrow.collisionMap(ground);
				collisionSafetyNet = arrow.collisionMap(safetyNet);
				collisionCeiling = arrow.collisionMap(ceiling);

				// If playGame is true (the game has started), the game will function
				// the user will be able to control and fire the arrow, as well as earn points 
				if (playGame == true) {
					/*
					 *  collectPoints will only be true before the arrow touches the target and the user earns points. This 
					 *  ensures the user cannot earn points more than once while the arrow is colliding with the target, 
					 *  as collectPoints will get set to false once the user has gained points for that shot.
					 */
					if (collectPoints == true) {
						// if 'collectPoints' is true...
						
						// the user will earn 3 points for getting a bullseye 
						if (collisionInnerTarget == true) {
							points = points + 3;
							collectPoints = false;
						// the user will earn 2 points for hitting the middle ring of the target
						} else if (collisionMiddleTarget == true) {
							points = points + 2;
							collectPoints = false;
						// the user will earn 1 point for hitting the outermost ring of the target
						} else if (collisionOuterTarget == true) {
							points = points + 1;
							collectPoints = false;
						}

					}

					// once the user fires the arrow by pressing the enter key, the user's point of view of the map changes
					// the map will look slightly tilted so the user can get a better view of the arrow's flight path
					if (input.contains("ENTER")) {
						
						// the camera, angle, velocity and score text, as well as the power bar moves forward 
						camera.setTranslateZ(260);
						arrow.velocityText.setTranslateZ(260);
						arrow.angleText.setTranslateZ(260);
						score.scoreText.setTranslateZ(260);
						arrow.powerBar.setTranslateZ(260);
						arrow.powerBar.setTranslateX(5);

						// rotates the camera left 15 degrees
						camera.setRotationAxis(Rotate.Y_AXIS);
						camera.setRotate(-15);

						// rotates the angle, velocity, and score text left 15 degrees
						arrow.velocityText.setRotationAxis(Rotate.Y_AXIS);
						arrow.velocityText.setRotate(-15);

						arrow.angleText.setRotationAxis(Rotate.Y_AXIS);
						arrow.angleText.setRotate(-15);

						score.scoreText.setRotationAxis(Rotate.Y_AXIS);
						score.scoreText.setRotate(-15);

						arrow.powerBar.setRotationAxis(Rotate.Y_AXIS);
						arrow.powerBar.setRotate(-15);

					}

					/*
					 *  puts arrow back to its' starting position and resets the user's point of view of the map 
					 *  if the arrow lands (touches ground), hits the back wall, touches a boundary of the map (i.e. the ceiling or safety net) 
					 *  or hits the target.
					 */
	
					if (collisionBackWall == true || collisionGround == true || collisionSafetyNet == true
							|| collisionCeiling == true || collisionInnerTarget == true || collisionOuterTarget == true || collisionMiddleTarget == true ) {
						// user no longer sees the map on an angle by calling the 'resetCameraPerspective' method
						resetCameraPerspective();
						// sets 'restartShot' to true so the arrow can be fired again
						restartShot = true;
						// throwCount keeps track of how many shots the user has taken
						// adds 1 to throwCount as the user just completed a shot
						throwCount = throwCount + 1;
					}

					/*
					 *  if restartShot equals true (the arrow just finished its' flight path),
					 *  the arrow will be reset to its' starting position, and the initial velocity and launch angle 
					 *  of the arrow will be set to 0 by calling the resetArrow method
					 */
					if (restartShot == true) {
						arrow.resetArrow();
						// collectPoints will get set back to true so the user can earn points from their next shot
						collectPoints = true;
						restartShot = false;
					}

					// displays the user's score as text by calling the displayScore method
					score.displayScore(points);
					// displays the arrow in its' flight path, or at a chosen angle prior to launch, by calling the drawArrow method
					// passes on the keyboard input so the arrow can be adjusted based on what key the user is pressing 
					// (i.e. arrow will rotate up if user presses up arrow key)
					arrow.drawArrow(input);
				}

				// displays end screen and resets game after user has taken 3 shots
				if (throwCount == 3) {
					// camera moves back so the entirety of the end screen can be displayed 
					camera.setTranslateZ(-400);
					// displays the end screen by calling the displayEndScreen method
					endScreen.displayEndScreen(points);
					// sets 'playGame' to false as user is no longer in-game
					// this prevents user from being able to launch and adjust arrow + earn points (game has stopped)
					playGame = false;
					// restarts game once user presses 'r'
					if (input.contains("R")) {
						// camera moves back in
						camera.setTranslateZ(315);
						// playGame gets set to true to allow the game to run again
						playGame = true;
						// restartShot gets set to true 
						// arrow gets set back to its' starting position, initial velocity and angle will be 0
						restartShot = true;
						// user's points and number of throws they've taken get set back to 0
						throwCount = 0;
						points = 0;
						// end screen disappears 
						endScreen.hideEndScreen();

					}
				}

			}

		}.start();

	}
}
