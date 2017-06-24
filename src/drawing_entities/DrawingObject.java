package drawing_entities;

import Stefano_package.components.CadComponent;

/**
 * This class represents the primitive drawing entity, that is better defined in the children classes. It is the one
 * that has the methods for rendering.
 *
 * @author Enrico Ferro
 */
public abstract class DrawingObject {
    /**
     * X coordinate of the drawing starting point (for each object has different role, i.e. circle -> center)
     */
    private int positionX;
    /**
     * Y coordinate of the drawing starting point (for each object has different role, i.e. circle -> center)
     */
    private int positionY;

    /**
     * Constructor
     */
    public DrawingObject(int positionX, int positionY){
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public int getPositionX() {return positionX;}
    public int getPositionY() {return positionY;}
    public void setPositionX(int positionX) {this.positionX = positionX;}
    public void setPositionY(int positionY) {this.positionY = positionY;}

    /**
     * Render function. Each concrete class will specify this in detail.
     * @return the JsCad Component corresponding to the object
     */
    public CadComponent render(){return null;}
}
