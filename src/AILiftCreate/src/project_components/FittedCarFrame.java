package project_components;

import com.fasterxml.jackson.annotation.JsonProperty;
import drawing_entities.Rectangle;

/**
 * Created by enric on 23/05/2017.
 */
public class FittedCarFrame extends FittedComponent {
    //attributes
    @JsonProperty("dist_asse_guide_filomuro")
    private int wallDistance;
    @JsonProperty("dist_cabina_asse_guide")
    private int cabinDistance;
    @JsonProperty("ingtrasv")
    private int ingTrasv;

    //methods
    public FittedCarFrame() {
        super();
        wallDistance = 0;
        cabinDistance = 0;
        ingTrasv = 0;
    }

    public FittedCarFrame(String componentId, int wallDistance, int cabinDistance, int ingTrasv, int x, int y) {
        super(componentId, wallDistance+cabinDistance, ingTrasv, x, y);
        this.wallDistance = wallDistance;
        this.cabinDistance = cabinDistance;
        this.ingTrasv = ingTrasv;
        Rectangle carFrameFrame = new Rectangle(x, y, getFrameWidth(), getFrameDepth());
        getDrawingStructure().add(carFrameFrame);
    }

}
