package project_components;

import com.fasterxml.jackson.annotation.*;
import drawing_entities.*;

/**
 * Created by enric on 23/05/2017.
 */
public class FittedCar extends FittedComponent {
    //attributes
    @JsonProperty("width")
    private int totalWidth;
    @JsonProperty("depth")
    private int totalDepth;


    //methods
    public FittedCar(String componentId, int totalWidth, int totalDepth, int x, int y) {
        super(componentId, totalWidth, totalDepth, x, y);
        this.totalWidth = totalWidth;
        this.totalDepth = totalDepth;
        Rectangle carFrameFrame = new Rectangle(x, y, getFrameWidth(), getFrameDepth());
        getDrawingStructure().add(carFrameFrame);
    }
}
