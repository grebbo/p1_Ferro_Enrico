package project_components;

import com.fasterxml.jackson.annotation.*;
import drawing_entities.*;

/**
 * This class models the lift car(cabin).
 *
 * @author Enrico Ferro
 */
public class FittedCar extends FittedComponent {
    /**
     * @attributes
     * component dimensions
     */
    @JsonProperty("width")
    private int totalWidth;
    @JsonProperty("depth")
    private int totalDepth;


    /**
     * @methods
     * constuctor
     */

    /**
     * It sets the car values and creates the related drawing structure.
     */
    public FittedCar(String componentId, int totalWidth, int totalDepth, int x, int y) {
        super(componentId, totalWidth, totalDepth, x, y);
        this.totalWidth = totalWidth;
        this.totalDepth = totalDepth;
        Rectangle carFrameFrame = new Rectangle(x, y, getFrameWidth(), getFrameDepth());
        getDrawingStructure().add(carFrameFrame);
    }
}
