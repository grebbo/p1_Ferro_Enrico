package project_components;

import com.fasterxml.jackson.annotation.JsonProperty;
import drawing_entities.*;

/**
 * Created by enric on 23/05/2017.
 */
public class FittedDoor extends FittedComponent {
    //attributes
    @JsonProperty("totalWidth")
    private int totalWidth;
    @JsonProperty("doorstep")
    private int doorstep;

    //methods
    public FittedDoor() {
        super();
        totalWidth = 0;
        doorstep = 0;
    }

    public FittedDoor(String componentId, int totalWidth, int doorstep, int x, int y) {
        super(componentId, totalWidth, doorstep, x, y);
        this.totalWidth = totalWidth;
        this.doorstep = doorstep;
        Rectangle carFrameFrame = new Rectangle(x, y, getFrameWidth(), getFrameDepth());
        getDrawingStructure().add(carFrameFrame);
    }
}
