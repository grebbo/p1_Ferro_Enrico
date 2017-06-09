package drawing_entities;

import Stefano_package.components._2d.CadRectangle;

/**
 * Created by enric on 22/05/2017.
 * This class represents the Rectangle drawing entity. In this case the DrawingObject coordinates are the one of the
 * top-left corner.
 */
public class Rectangle extends DrawingObject {
    /**
     * @attibutes
     * width, height -> rectangle dimensions
     */
    private int width;
    private int height;

    /**
     * @methods
     * contructor, getters and setters
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
     * @return CadRectangle -> rectangle rendering object
     */
    public CadRectangle render(){
        return new CadRectangle(this.getPositionX(), this.getPositionY(), width, height);
    }
}
