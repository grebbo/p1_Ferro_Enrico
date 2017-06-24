package project_components;

import drawing_entities.Line;
import drawing_entities.Rectangle;

/**
 * This class models the door, car side.
 *
 * @author Enrico Ferro
 */
public class FittedCarDoor extends FittedDoor {

    /**
     * Constructor. Depending on the door type (CEN or TEL) shutters are generated in a different way
     */
    public FittedCarDoor(String componentId, int totalWidth, int doorstep, int x, int y, String doorType, int nShutters) {
        super(componentId, totalWidth, doorstep, x, y, doorType, nShutters);
        int nGuides;
        int shutterWidth;
        int shutterHeight;
        if (doorType.equals("TEL")) {
            nGuides = nShutters;
            shutterWidth = totalWidth / (nShutters + 1);
            shutterHeight = doorstep/(nGuides + 1);
            int j = 0;
            for (int i = 1; i <= nGuides; i++) {
                getDrawingStructure().add(new Rectangle(x +(j+1)*(shutterWidth), y + i*(shutterHeight) - shutterHeight/2, shutterWidth, shutterHeight));
                j+=1;
            }
        } else if (doorType.equals("CEN")) {
            nGuides = nShutters / 2;
            shutterWidth = totalWidth / (nShutters + 2);
            shutterHeight = doorstep/(nGuides + 1);
            int j = 0;
            for (int i = 1; i <= nGuides; i++) {
                getDrawingStructure().add(new Rectangle( x + (j + 1)*(shutterWidth), y + i*(shutterHeight) - shutterHeight/2, shutterWidth, shutterHeight));
                getDrawingStructure().add(new Rectangle(x + (nShutters - j)*(shutterWidth), y + i*(shutterHeight) - shutterHeight/2, shutterWidth, shutterHeight));
                j+=1;
            }
        }
    }
}
