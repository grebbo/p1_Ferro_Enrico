package drawing_entities;

import Stefano_package.components._2d.CadLineOrtho;

import java.awt.*;

/**
 * This class represents the Line drawing entity. In this case the DrawingObject coordinates are the one of the starting point.
 *
 * @author Enrico Ferro
 */
public class Line extends DrawingObject{
    /**
     * X coordinate of the ending point of the line
     */
    private int endPointX;
    /**
     * Y coordinate of the ending point of the line
     */
    private int endPointY;

    /**
     * Constructor
     */
    public Line(int posX, int posY , int endPointX, int endPointY){
        super(posX, posY);
        this.endPointX = endPointX;
        this.endPointY = endPointY;
    }

    public int getEndPointX() {return endPointX;}
    public void setEndPointX(int endPointX) {this.endPointX = endPointX;}
    public int getEndPointY() {return endPointY;}
    public void setEndPointY(int endPointY) {this.endPointY = endPointY;}

    /**
     * It implements the rendering function, creating the corresponding rendering object (taken by Stefano's package)
     *
     * @return JsCad line rendering object
     */
    public CadLineOrtho render(){
        return new CadLineOrtho( new Point(getPositionX(), getPositionY()), new Point(endPointX, endPointY));
    }
}
