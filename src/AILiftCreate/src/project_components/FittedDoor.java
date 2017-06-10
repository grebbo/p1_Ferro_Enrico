package project_components;

import com.fasterxml.jackson.annotation.JsonProperty;
import drawing_entities.*;

/**
 * This class models the door component of the project.
 *
 * @author Enrico Ferro
 */
public class FittedDoor extends FittedComponent {
    /**
     * @attributes
     * componenti dimensions
     */
    @JsonProperty("totalWidth")
    private int totalWidth;
    @JsonProperty("doorstep")
    private int doorstep;

    /**
     * @methods
     * constructor
     */

    /**
     * It sets the door values and creates the related drawing structure.
     */
    public FittedDoor(String componentId, int totalWidth, int doorstep, int x, int y) {
        super(componentId, totalWidth, doorstep, x, y);
        this.totalWidth = totalWidth;
        this.doorstep = doorstep;
        Rectangle carFrameFrame = new Rectangle(x, y, getFrameWidth(), getFrameDepth());
        getDrawingStructure().add(carFrameFrame);
    }
}
