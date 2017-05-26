package project_components;

import drawing_entities.Rectangle;

/**
 * Created by enric on 23/05/2017.
 */
public class FittedCar extends FittedComponent {
    //attributes
    private int totalWidth;
    private int totalDepth;

    //methods
    public FittedCar() {
        super();
        totalWidth = 0;
        totalDepth = 0;
    }

    public FittedCar(String componentId, int totalWidth, int totalDepth, int x, int y) {
        super(componentId, totalWidth, totalDepth, x, y);
        this.totalWidth = totalWidth;
        this.totalDepth = totalDepth;
        Rectangle carFrameFrame = new Rectangle(x, y, getFrameWidth(), getFrameDepth());
        getDrawingStructure().add(carFrameFrame);
    }
}