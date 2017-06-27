package project_components;

import com.fasterxml.jackson.annotation.JsonProperty;
import drawing_entities.*;

/**
 * This class models the door component of the project.
 *
 * @author Enrico Ferro
 */
public class FittedDoor extends FittedComponent {

    @JsonProperty("totalWidth")
    private int totalWidth;
    @JsonProperty("doorstep")
    private int doorstep;
    @JsonProperty("doorType")
    private String doorType;
    @JsonProperty("numshutters")
    private int nShutters;

    /**
     * Constructor. Depending on the door type (CEN or TEL) shutters are generated in a different way
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
            getDrawingStructure().add(new Line(x, y + i*(shutterHeight), x + getFrameWidth() - 1, y + i*(shutterHeight)));
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FittedDoor that = (FittedDoor) o;

        if (totalWidth != that.totalWidth) return false;
        if (doorstep != that.doorstep) return false;
        if (nShutters != that.nShutters) return false;
        return doorType != null ? doorType.equals(that.doorType) : that.doorType == null;
    }

}
