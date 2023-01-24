// Jan. 21, 2020
// Sonia Tinaz
// archeryGame, Target
// Code for creating the target
package archeryGame;

import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Cylinder;
import javafx.scene.transform.Rotate;

public class Target {

	// declaring variables for the target
	Cylinder outerTarget;
	Cylinder middleTarget;
	Cylinder innerTarget;

	double x;
	double y;
	double outerTargetZ;
	double middleTargetZ;
	double innerTargetZ;

	// constructors -----------------------------------------------
	public Target() {
		super();
		// 'outerTarget' gets set as a cylinder with a radius of 80px, and height of 20px
		// this cylinder will be the outer ring of the target in the game
		this.outerTarget = new Cylinder(80, 20);
		// 'middleTarget' gets set as a cylinder with a radius of 50px, and height of 20px
		// this cylinder will be the middle ring of the target in the game
		this.middleTarget = new Cylinder(50, 20);
		// 'innerTarget' gets set as a cylinder with a radius of 20px, and height of 20px
		// this cylinder will be the innermost ring of the target in the game
		this.innerTarget = new Cylinder(20, 20);
		// sets the target's rings' x and y coordinate to 500 and 400
		this.x = 500;
		this.y = 400;
		// sets the z coordinates of the target's rings
		// (i.e. the outer ring will have a z coordinate of 800)
		this.outerTargetZ = 800;
		this.middleTargetZ = 799.9;
		this.innerTargetZ = 799.8;
	}

	public Target(Cylinder outerTarget, Cylinder middleTarget, Cylinder innerTarget, double x, double y,
			double outerTargetZ, double middleTargetZ, double innerTargetZ) {
		super();
		// sets target variables to chosen values
		this.outerTarget = outerTarget;
		this.middleTarget = middleTarget;
		this.innerTarget = innerTarget;
		this.x = x;
		this.y = y;
		this.outerTargetZ = outerTargetZ;
		this.middleTargetZ = middleTargetZ;
		this.innerTargetZ = innerTargetZ;
	}

	// getters and setters for target variables ----------------------------------
	
	/* 
	 * 'getters' allows someone to access a certain value stored in a variable, upon calling a particular 
	 *  'get[Variable]' method (it will return the specified variable). 'setters' redefines/changes 
	 *  the value stored in a particular variable, upon calling a 'set[Variable]' method.
	 */
	public Cylinder getOuterTarget() {
		return outerTarget;
	}

	public void setOuterTarget(Cylinder outerTarget) {
		this.outerTarget = outerTarget;
	}

	public Cylinder getMiddleTarget() {
		return middleTarget;
	}

	public void setMiddleTarget(Cylinder middleTarget) {
		this.middleTarget = middleTarget;
	}

	public Cylinder getInnerTarget() {
		return innerTarget;
	}

	public void setInnerTarget(Cylinder innerTarget) {
		this.innerTarget = innerTarget;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getOuterTargetZ() {
		return outerTargetZ;
	}

	public void setOuterTargetZ(double outerTargetZ) {
		this.outerTargetZ = outerTargetZ;
	}

	public double getMiddleTargetZ() {
		return middleTargetZ;
	}

	public void setMiddleTargetZ(double middleTargetZ) {
		this.middleTargetZ = middleTargetZ;
	}

	public double getInnerTargetZ() {
		return innerTargetZ;
	}

	public void setInnerTargetZ(double innerTargetZ) {
		this.innerTargetZ = innerTargetZ;
	}

	// method for displaying a colourful target that's been properly rotated/positioned
	public void drawTarget() {

		// changes the colour of the outer ring to dark red
		PhongMaterial outerTargetColour = new PhongMaterial();
		outerTargetColour.setDiffuseColor(Color.DARKRED);
		this.outerTarget.setMaterial(outerTargetColour);

		// changes the colour of the middle ring to white
		PhongMaterial middleTargetColour = new PhongMaterial();
		middleTargetColour.setDiffuseColor(Color.WHITE);
		this.middleTarget.setMaterial(middleTargetColour);

		// changes the colour of the inner ring to black
		PhongMaterial innerTargetColour = new PhongMaterial();
		innerTargetColour.setDiffuseColor(Color.BLACK);
		this.innerTarget.setMaterial(innerTargetColour);

		// the cylinders used to form the rings of the target are initially standing up-right
		// rotates all 3 cylinders by 90 degrees so their circle face is facing towards the user 
		this.outerTarget.setRotationAxis(Rotate.X_AXIS);
		this.outerTarget.setRotate(90);

		this.middleTarget.setRotationAxis(Rotate.X_AXIS);
		this.middleTarget.setRotate(90);

		this.innerTarget.setRotationAxis(Rotate.X_AXIS);
		this.innerTarget.setRotate(90);

		// aligns cylinders so they collectively look like a target (cylinders surround each other to form rings)
		// positions the target rings at the x, y,and z coordinate specified in the console
		this.outerTarget.setTranslateX(this.x);
		this.outerTarget.setTranslateY(this.y);
		this.outerTarget.setTranslateZ(this.outerTargetZ);

		this.middleTarget.setTranslateX(this.x);
		this.middleTarget.setTranslateY(this.y);
		this.middleTarget.setTranslateZ(this.middleTargetZ);

		this.innerTarget.setTranslateX(this.x);
		this.innerTarget.setTranslateY(this.y);
		this.innerTarget.setTranslateZ(this.innerTargetZ);
	}

}
