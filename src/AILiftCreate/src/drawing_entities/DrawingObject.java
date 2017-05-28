package drawing_entities;

import Stefano_package.components.CadComponent;

/**
 * Created by Enrico on 14/05/2017.
 */
public abstract class DrawingObject {
    //attributes
    private int positionX;
    private int positionY;

    //methods
    public DrawingObject(int positionX, int positionY){
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public int getPositionX() {return positionX;}
    public int getPositionY() {return positionY;}

    public CadComponent render(){return null;}
}
