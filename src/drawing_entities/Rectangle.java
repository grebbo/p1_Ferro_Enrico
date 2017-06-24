package drawing_entities;

import Stefano_package.components._2d.CadRectangle;

/**
 * This class represents the Rectangle drawing entity. In this case the DrawingObject coordinates are the one of the
 * top-left corner.
 *
 * @author Enrico Ferro
 */
public class Rectangle extends DrawingObject {
    /**
     * Width of the rectangle
     */
    private int width;
    /**
     * Height of the rectangle
     */
    private int height;

    /**
     * Constructor
     */
    public Rectangle(int posX, int posY , int width, int height){
        super(posX, posY);
        this.width = width;
        this.height = height;
    }

    public int getWidth() {return width;}
    public int getHeight() {return height;}
    public void setWidth(int width) {this.width = width;}
    public void setHeight(int height) {this.height = height;}

    /**
     * It implements the rendering function, creating the corresponding rendering object (taken by Stefano's package)
     *
     * @return rectangle rendering object
     */
    public CadRectangle render(){
        return new CadRectangle(this.getPositionX(), this.getPositionY(), width, height);
    }
}
