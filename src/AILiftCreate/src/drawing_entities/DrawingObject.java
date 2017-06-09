package drawing_entities;

import Stefano_package.components.CadComponent;

/**
 * Created by Enrico on 14/05/2017.
 * This class represents the primitive drawing entity, that is better defined in the children classes. It is the one
 * that has the methods for rendering.
 */
public abstract class DrawingObject {
    /**
     * @attributes
     * positionX/Y -> coordinates of the object. Each of the children classes interprets this in a different way.
     */
    private int positionX;
    private int positionY;

    /**
     *  @methods
     *  constructor, getters and setters
     */
    public DrawingObject(int positionX, int positionY){
        this.positionX = positionX;
        this.positionY = positionY;
    }
    public int getPositionX() {return positionX;}
    public int getPositionY() {return positionY;}
    public void setPositionX(int positionX) {this.positionX = positionX;}
    public void setPositionY(int positionY) {this.positionY = positionY;}

    public CadComponent render(){return null;}
}
