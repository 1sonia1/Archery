// Jan. 21, 2020
// Sonia Tinaz
// archeryGame, Arrow
// Code for creating and displaying a functional arrow using projectile motion physics

package archeryGame;

import java.util.ArrayList;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Cylinder;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;

public class Arrow {

	// declaring variables for the arrow
	double launchAngle = 0;
	double initialVelocity = 0;
	double horizontalVelocity;
	double verticalVelocity;

	Box arrow;

	int powerBarLength = 0;
	Rectangle powerBar = new Rectangle(powerBarLength, 20);

	double z;
	double y;
	double x;

	double elapsedTime = 0;
	double startTime;

	boolean firedArrow = false;

	String up = "UP";
	String down = "DOWN";
	String space = "SPACE";
	String enter = "ENTER";

	ArrayList<String> input;

	// converts the arrow's initial velocity into a string so it can be used in text
	String initialVelocityString = Integer.toString((int) this.initialVelocity);
	// 'velocityText' is text that says the initial velocity of the arrow
	// it will be displayed at the top left corner of the screen
	Text velocityText = new Text(10, 50, initialVelocityString + " m/s");

	// converts the arrow's launch angle into a string so it can be used in text
	String launchAngleString = Integer.toString((int) this.launchAngle);
	// 'angleText' is text that says the launch angle of the arrow
	// it will be displayed at the top left corner of the screen
	Text angleText = new Text(10, 100, launchAngleString + "°");

	
	// constructors -----------------------------------------------
	public Arrow() {
		super();
		// 'arrow' gets set as a 3D box with a width of 10px, height of 1px, and depth of 150px
		// this 3D box will be the physical arrow used in the game
		this.arrow = new Box(10, 1, 150);
		// sets the arrow's z, y, and x coordinate to -370, 499.9, and 502.5 respectively 
		this.z = -370;
		this.y = 499.9;
		this.x = 502.5;

	}

	public Arrow(int powerBarLength, double launchAngle, double initialVelocity, double horizontalVelocity,
			double verticalVelocity, Box arrow, Rectangle powerBar, double z, double y, double x, double elapsedTime,
			double startTime, boolean firedArrow, String up, String down, String space, String enter,
			ArrayList<String> input) {
		super();
		// sets arrow variables to chosen values 
		this.powerBarLength = powerBarLength;
		this.launchAngle = launchAngle;
		this.initialVelocity = initialVelocity;
		this.horizontalVelocity = horizontalVelocity;
		this.verticalVelocity = verticalVelocity;
		this.arrow = arrow;
		this.powerBar = powerBar;
		this.z = z;
		this.y = y;
		this.x = x;
		this.elapsedTime = elapsedTime;
		this.startTime = startTime;
		this.firedArrow = firedArrow;
		this.up = up;
		this.down = down;
		this.space = space;
		this.enter = enter;
		this.input = input;

	}

	// getters and setters for arrow variables ----------------------------------
	
	/* 
	 * 'getters' allows someone to access a certain value stored in a variable, upon calling a particular 
	 *  'get[Variable]' method (it will return the specified variable). 'setters' redefines/changes 
	 *  the value stored in a particular variable, upon calling a 'set[Variable]' method.
	 */
	public int getPowerBarLength() {
		return powerBarLength;
	}

	public void setPowerBarLength(int powerBarLength) {
		this.powerBarLength = powerBarLength;
	}

	public double getLaunchAngle() {
		return launchAngle;
	}

	public void setLaunchAngle(double launchAngle) {
		this.launchAngle = launchAngle;
	}

	public double getInitialVelocity() {
		return initialVelocity;
	}

	public void setInitialVelocity(double initialVelocity) {
		this.initialVelocity = initialVelocity;
	}

	public double getHorizontalVelocity() {
		return horizontalVelocity;
	}

	public void setHorizontalVelocity(double horizontalVelocity) {
		this.horizontalVelocity = horizontalVelocity;
	}

	public double getVerticalVelocity() {
		return verticalVelocity;
	}

	public void setVerticalVelocity(double verticalVelocity) {
		this.verticalVelocity = verticalVelocity;
	}

	public Box getArrow() {
		return arrow;
	}

	public void setArrow(Box arrow) {
		this.arrow = arrow;
	}

	public Rectangle getPowerBar() {
		return powerBar;
	}

	public void setPowerBar(Rectangle powerBar) {
		this.powerBar = powerBar;
	}

	public double getZ() {
		return z;
	}

	public void setZ(double z) {
		this.z = z;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getElapsedTime() {
		return elapsedTime;
	}

	public void setElapsedTime(double elapsedTime) {
		this.elapsedTime = elapsedTime;
	}

	public double getStartTime() {
		return startTime;
	}

	public void setStartTime(double startTime) {
		this.startTime = startTime;
	}

	public boolean isFiredArrow() {
		return firedArrow;
	}

	public void setFiredArrow(boolean firedArrow) {
		this.firedArrow = firedArrow;
	}

	public String getUp() {
		return up;
	}

	public void setUp(String up) {
		this.up = up;
	}

	public String getDown() {
		return down;
	}

	public void setDown(String down) {
		this.down = down;
	}

	public String getSpace() {
		return space;
	}

	public void setSpace(String space) {
		this.space = space;
	}

	public String getEnter() {
		return enter;
	}

	public void setEnter(String enter) {
		this.enter = enter;
	}

	// returns the arrow's calculated z position at a certain moment in time considering the arrow's launch angle and initial velocity
	// Uses projectile motion equations to calculate the z position/coordinate
	public double calculateZPosition() {
		/*
		 * 	Calculates the arrow's horizontal velocity. Does this by multiplying cosine of the
		 *  launch angle by the initial velocity. Since the cosine method only accepts angles given in radians,
		 *  the launch angle (given in degrees) is first converted to radians.
		 */
		this.horizontalVelocity = this.initialVelocity * ((java.lang.Math.cos(Math.toRadians(this.launchAngle))));
		// multiplies the calculated horizontal velocity by the current time in the arrow's flight path 
		// Doing so calculates the horizontal distance from the arrow's starting position at that moment in time
		// This horizontal distance is equivalent to the z position the arrow will be at in its' flight path at the specified time
		// Returns this value
		return ((this.horizontalVelocity * this.elapsedTime));
	}

	// returns the arrow's calculated y position at a certain moment in time considering the arrow's launch angle and initial velocity
	// Uses projectile motion equations to calculate the y position/coordinate
	public double calculateYPosition() {
		/*
		 * 	Calculates the arrow's vertical velocity. Does this by multiplying sine of the
		 *  launch angle by the initial velocity. Since the sine method only accepts angles given in radians,
		 *  the launch angle is first converted to radians.
		 */
		this.verticalVelocity = this.initialVelocity * (java.lang.Math.sin(Math.toRadians(this.launchAngle)));
		/* 
		 * Plugs the arrow's vertical velocity and current time in the arrow's flight path into
		 * a projectile motion equation that determines the arrow's vertical distance from the ground at that 
		 * moment in time. This vertical distance is equivalent to the y coordinate the arrow would have 
		 * at the given moment in time. Program then returns the calculated y coordinate/position.
		 */
		return ((this.verticalVelocity * this.elapsedTime) + ((0.50 * 9.80) * (this.elapsedTime * this.elapsedTime)));
	}

	// Method for resetting the arrow after it was fired
	// Puts the arrow back to its' starting position, and resets the launch angle and initial velocity to 0.
	public void resetArrow() {
		// Moves the arrow to its' starting position
		// As specified in the constructor, this starting position would be (502.5, 499.9, -370).
		this.arrow.setTranslateZ(this.z);
		this.arrow.setTranslateY(this.y);
		this.arrow.setTranslateX(this.x);

		// resets the arrow's launch angle and initial velocity to 0
		this.launchAngle = 0;
		this.initialVelocity = 0;

		// Changes the velocity text to read '0 m/s'
		initialVelocityString = Integer.toString((int) this.initialVelocity);
		velocityText.setText(initialVelocityString + " m/s");
		// Changes the angle text to read '0°'
		launchAngleString = Integer.toString((int) this.launchAngle);
		angleText.setText(launchAngleString + "°");

		// resets the power bar at the top of the screen
		// Sets the power bar's width back to 0
		// This ensures the power bar is not displayed until the user increases the velocity again by holding space
		this.powerBarLength = 0;
		this.powerBar.setWidth(powerBarLength);

		// sets 'firedArrow' to false, allowing the user to change the arrow's launch angle and velocity once again
		this.firedArrow = false;
	}

	// method for controlling the launch angle of the arrow with up and down arrow keys
	public void rotateArrow() {

		// if the user presses the up arrow key, the arrow will rotate one degree up
		// 'launchAngle' will get set to the new launch angle the user chose with the arrow keys, by adding 1 to 'launchAngle'
		if (this.input.contains(this.up)) {
			this.arrow.setRotationAxis(Rotate.X_AXIS);
			this.launchAngle = this.launchAngle + 1;
			this.arrow.setRotate(this.launchAngle);

		// if the user presses the down arrow key, the arrow will rotate one degree down
		// 'launchAngle' will get set to the new angle the user chose with the arrow keys, by subtracting 1 from 'launchAngle'
		} else if (this.input.contains(this.down)) {
			this.arrow.setRotationAxis(Rotate.X_AXIS);
			this.launchAngle = this.launchAngle - 1;
			this.arrow.setRotate(this.launchAngle);

		}
		// ensures the user can only select an angle between 0 and 90
		
		// if the user chose an angle below 0 (i.e. -1), the launch angle will get set back to 0
		if (this.launchAngle < 0) {
			this.launchAngle = 0;
			this.arrow.setRotate(this.launchAngle);
		// if the user chose an angle above 90, the launch angle will get set back to 90
		} else if (this.launchAngle > 90) {
			this.launchAngle = 90;
			this.arrow.setRotate(this.launchAngle);
		}

		// text for the launch angle now reads the new launch angle the user chose
		launchAngleString = Integer.toString((int) this.launchAngle);
		angleText.setText(launchAngleString + "°");

	}

	// method for controlling the initial velocity of the arrow with the space bar
	public void setArrowVelocity() {

		// In this game, the initial velocity is negative, as negative y coordinates is up in javafx
		
		// User is only allowed to select initial velocity from 0 to -200
		
		/* 
		 *  If the initial velocity is more than -200, and the user presses the space bar, the velocity
		 *  will increase by subtracting 1 from the initial velocity (bigger negative number is a faster speed). 
		 *  The power bar at the top of the screen that acts as a visual representation of the velocity will also
		 *  increase by 5pxl in length. 
		 */
		if (this.initialVelocity > -200) {
			this.powerBarLength = this.powerBarLength + 5;
			this.powerBar.setWidth(this.powerBarLength);
			this.initialVelocity = this.initialVelocity - 1;
			// text for showing the initial velocity will change to say the new velocity as a positive number
			initialVelocityString = Integer.toString((int) this.initialVelocity * -1);
			velocityText.setText(initialVelocityString + " m/s");

			// if the velocity as a positive number is more than 150, the power bar will be green
			if (this.initialVelocity * -1 > 150) {
				this.powerBar.setFill(Color.GREEN);

			// if the velocity as a positive number is more than 80 (but less than 151), the power bar will be yellow
			} else if (this.initialVelocity * -1 > 80) {
				this.powerBar.setFill(Color.YELLOW);
			} else {
			// if the velocity as a positive number is less than 81, the power bar will be red
				this.powerBar.setFill(Color.RED);
			}
		}

	}

	// method for displaying the arrow at a chosen angle prior to launch
	// Also displays arrow at its' calculated z and y coordinate in the arrow's flight path, after launch
	public void drawArrow(ArrayList<String> input) {

		// 'input' is an array that contains the key the user is pressing
		this.input = input;
		
		// changes the arrow's colour to blue
		// (diffuse colour is the colour the arrow will be under white light)
		PhongMaterial arrowColour = new PhongMaterial();
		arrowColour.setDiffuseColor(Color.LIGHTSTEELBLUE);
		this.arrow.setMaterial(arrowColour);

		// if the user has not fired the arrow yet, the user can change the arrow's launch angle and velocity
		if (this.firedArrow == false) {
			// calls the rotateArrow method to change the arrow's launch angle if the user presses the up or down arrow key
			if (this.input.contains(this.up) || this.input.contains(this.down)) {
				rotateArrow();
			// calls the setArrowVelocity method to change the arrow's initial velocity if the user presses the space bar
			} else if (this.input.contains(this.space)) {
				setArrowVelocity();
			}

		}

		// if the user presses enter, the arrow gets fired by setting 'firedArrow' to true
		if (this.input.contains(this.enter)) {
			this.firedArrow = true;
			// the arrow is rotated back to its' starting position (0 degrees) 
			// this ensures the arrow is not at a weird angle throughout its' flight animation
			this.arrow.setRotationAxis(Rotate.X_AXIS);
			this.arrow.setRotate(0);
			// startTime contains the time (in seconds) when the arrow was fired 
			this.startTime = System.currentTimeMillis() / 1000.0;

		}

		// if firedArrow is true, the arrow is launched
		if (this.firedArrow == true) {
			// subtracts 'startTime' from the current time to get the time that has passed since the arrow was fired
			// stores this calculated time in 'elapsedTime' to be used in calculating the arrow's z and y coordinates in its' flight path
			this.elapsedTime = ((System.currentTimeMillis() / 1000.0) - this.startTime);
			// calls the calculateZPosition method to get the z coordinate the arrow should be at in its' flight path (at the time stored in 'elapsedTime') 
			// Sets the arrow's z coordinate to the arrow's z starting position minus the value returned from the calculateZPosition method
			// Overtime, the arrow will move forward horizontally from its' starting z position
			this.arrow.setTranslateZ(this.z - calculateZPosition());
			// calls the calculateYPosition method to get the y coordinate the arrow should be at in its' flight path (at the time stored in 'elapsedTime') 
			// Sets the arrow's y coordinate to the value returned from the calculateYPosition method plus the arrow's starting y position
			// Overtime, the arrow will move up and then back down to its' starting y position
			this.arrow.setTranslateY(this.y + calculateYPosition());
		}

	}

	// method for checking for collision between the target and the arrow
	// if the arrow is colliding with the target, 'collide' will get set to true
	// if not, collide will get set to false
	// returns the boolean 'collide'
	public boolean collisionTarget(Cylinder target) {
		boolean collide = target.getBoundsInParent().intersects(this.arrow.getBoundsInParent());
		return collide;
	}

	// method for checking for collision between the target and a part of the map (i.e. the ground)
	// if the arrow is colliding with the map, 'collide' will get set to true
	// if not, collide will get set to false
	// returns the boolean 'collide'
	public boolean collisionMap(Box mapPart) {
		boolean collide = mapPart.getBoundsInParent().intersects(this.arrow.getBoundsInParent());
		return collide;
	}

}
