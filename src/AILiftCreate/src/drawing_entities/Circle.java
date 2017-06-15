package drawing_entities;

import Stefano_package.components._2d.CadCircle;

/**
 * This class represents the Circle drawing entity. In this case the DrawingObject coordinates are the one of the center.
 *
 * @author Enrico Ferro
 */

public class Circle extends DrawingObject{
    /**
     * @attibutes
     * radius -> circle dimension
     */
    private int radius;

    /**
     * @methods
     * contructor, getters and setters
     */
    public Circle(int posX, int posY , int radius){
        super(posX, posY);
        this.radius = radius;
    }
    public int getRadius() {return radius;}
    public void setRadius(int radius) {this.radius = radius;}

    /**
     * It implements the rendering function, creating the corresponding rendering object (taken by Stefano's package)
     *
     * @return rectangle rendering object
     */
    public CadCircle render(){
        return new CadCircle(radius, this.getPositionX(), this.getPositionY());
    }
}
