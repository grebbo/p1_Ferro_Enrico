package project_components;

import drawing_entities.Rectangle;

/**
 * This class models the door landing-side
 *
 * @author Enrico Ferro
 */
public class FittedLandingDoor extends FittedDoor {
    /**
     * @methods
     * constructor
     */
    public FittedLandingDoor(String compoonentId, int totalWidth, int doorstep, int x, int y, String doorType, int nShutters) {
        super(compoonentId, totalWidth, doorstep, x, y, doorType, nShutters);
        int nGuides;
        int shutterWidth;
        int shutterHeight;
        if (doorType.equals("TEL")) {
            nGuides = nShutters;
            shutterWidth = totalWidth / (nShutters + 1);
            shutterHeight = doorstep/(nGuides + 1);
            int j = 0;
            for (int i = nGuides; i >= 1; i--) {
                getDrawingStructure().add(new Rectangle(x +(j+1)*(shutterWidth), y + i*(shutterHeight) - shutterHeight/2, shutterWidth, shutterHeight));
                j+=1;
            }
        } else if (doorType.equals("CEN")) {
            nGuides = nShutters / 2;
            shutterWidth = totalWidth / (nShutters + 2);
            shutterHeight = doorstep/(nGuides + 1);
            int j = 0;
            for (int i = nGuides; i >= 1; i--) {
                getDrawingStructure().add(new Rectangle( x + (j + 1)*(shutterWidth), y + i*(shutterHeight) - shutterHeight/2, shutterWidth, shutterHeight));
                getDrawingStructure().add(new Rectangle(x + (nShutters - j)*(shutterWidth), y + i*(shutterHeight) - shutterHeight/2, shutterWidth, shutterHeight));
                j+=1;
            }
        }
    }
}
