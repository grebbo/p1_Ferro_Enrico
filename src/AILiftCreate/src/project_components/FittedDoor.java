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
    @JsonProperty("doorType")
    private String doorType;
    @JsonProperty("numshutters")
    private int nShutters;

    /**
     * @methods
     * constructor
     */

    /**
     * It sets the door values and creates the related drawing structure.
     */
    public FittedDoor(String componentId, int totalWidth, int doorstep, int x, int y, String doorType, int nShutters) {
        super(componentId, totalWidth, doorstep, x, y);
        this.totalWidth = totalWidth;
        this.doorstep = doorstep;
        this.doorType = doorType;
        this.nShutters = nShutters;
        Rectangle carFrameFrame = new Rectangle(x, y, getFrameWidth(), getFrameDepth());
        getDrawingStructure().add(carFrameFrame);

        int nGuides = 0;
        int shutterHeight = 0;
        if (doorType.equals("TEL")) nGuides = nShutters;
        else if ( doorType.equals("CEN")) nGuides = nShutters / 2;

        shutterHeight = doorstep/(nGuides + 1);
        for(int i = 1 ; i <= nGuides; i++) {
            getDrawingStructure().add(new Line(x, y + i*(shutterHeight), x + getFrameWidth(), y + i*(shutterHeight)));
        }
    }
}
