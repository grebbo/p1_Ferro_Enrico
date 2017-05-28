package project_components;

import com.fasterxml.jackson.annotation.*;
import drawing_entities.*;

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
    public FittedCarFrame(String componentId, int wallDistance, int cabinDistance, int ingTrasv, int x, int y) {
        super(componentId, wallDistance+cabinDistance, ingTrasv, x, y);
        this.wallDistance = wallDistance;
        this.cabinDistance = cabinDistance;
        this.ingTrasv = ingTrasv;
        Rectangle carFrameFrame = new Rectangle(x, y, getFrameWidth(), getFrameDepth());
        getDrawingStructure().add(carFrameFrame);
    }

}
