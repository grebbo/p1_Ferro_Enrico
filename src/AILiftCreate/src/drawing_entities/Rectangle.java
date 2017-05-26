package drawing_entities;

import components.CadRectangle;

/**
 * Created by enric on 22/05/2017.
 */
public class Rectangle extends DrawingObject {
    //attributes
    private int width;
    private int height;

    //methods
    public Rectangle(int posX, int posY , int width, int height){
        super(posX, posY);
        this.width = width;
        this.height = height;
    }

    public int getWidth() {return width;}
    public int getHeight() {return height;}

    public CadRectangle render(){
        //use method from other project in order to make strings and render
        return new CadRectangle(this.getPositionX(), this.getPositionY(), width, height);
    }
}
