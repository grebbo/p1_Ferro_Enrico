package project_components;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import drawing_entities.Rectangle;

/**Class modeling the shaft (principal frame) of the elevator.
 * It inherits his attributes and methods from project_components.FittedComponent class
 * Created by enric on 23/05/2017.
 */
public class Shaft extends FittedComponent {
    //attributes

    //methods
    public Shaft(){
        super();
    }

    public Shaft(String componentId, int frameWidth, int frameDepth, int x, int y) {
        super(componentId, frameWidth, frameDepth, x, y);
        //adding drawing elements for the specific component, given the measures we have
        getDrawingStructure().add(new Rectangle(x - 5, y, 5 , getFrameDepth()));
        getDrawingStructure().add(new Rectangle(x , y - 5, getFrameWidth() , 5));
        getDrawingStructure().add(new Rectangle(x + getFrameWidth(), y, 5 , getFrameDepth()));
        getDrawingStructure().add(new Rectangle(x , y + getFrameDepth(), getFrameWidth() , 5));
    }
}
