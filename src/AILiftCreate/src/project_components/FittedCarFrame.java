package project_components;

import com.fasterxml.jackson.annotation.*;
import drawing_entities.*;

/**
 * It models the carFrame(lateral lift moving guide). It has some sub-components that defines the structure in a more
 * detailed way than the mere frame.
 * @author Enrico Ferro
 */
public class FittedCarFrame extends FittedComponent {
    /**
     * @attributes
     * component dimensions and sub components
     */
    @JsonProperty("dist_asse_guide_filomuro")
    private int wallDistance;
    @JsonProperty("dist_cabina_asse_guide")
    private int cabinDistance;
    @JsonProperty("dtg")
    private int dtg;
    @JsonProperty("carFrameHydraBracket")
    private Bracket bracket;
    @JsonProperty("carRails")
    private CarRail carRail;

    /**
     * @methods
     * constructor
     */

    /**
     * Constructor that sets all values and sub-components of the carframe. Then it defines the drawing structure
     * for each of the entities (general frame, brackets, carrails).
     */
    public FittedCarFrame(String componentId, int wallDistance, int cabinDistance, int dtg, int x, int y, Bracket bracket, CarRail carRail) {
        super(componentId, wallDistance+cabinDistance, dtg + 2*(bracket.getHeight() + carRail.getP() + carRail.getH1()), x, y);
        this.wallDistance = wallDistance;
        this.cabinDistance = cabinDistance;
        this.bracket = bracket;
        this.carRail = carRail;
        this.dtg = dtg;
        //add carFrame frame to drawing structure
        getDrawingStructure().add(new Rectangle(x, y, getFrameWidth(), getFrameDepth()));
        //add brackets drawing entities to drawing structure
        getDrawingStructure().add(new Rectangle( x, y, bracket.getWidth(), bracket.getHeight()));
        getDrawingStructure().add(new Rectangle( x, y + getFrameDepth() - bracket.getHeight(),
                                                bracket.getWidth(), bracket.getHeight()));
        //add carails to structure
        getDrawingStructure().add(new Rectangle(x + wallDistance - carRail.getB1()/2,
                                                y + bracket.getHeight(),
                                                carRail.getB1(), carRail.getP()));
        getDrawingStructure().add(new Rectangle(x + wallDistance - carRail.getK()/2,
                                                y + bracket.getHeight() + carRail.getP(),
                                                carRail.getK(), carRail.getH1()));
        getDrawingStructure().add(new Rectangle(x + wallDistance - carRail.getB1()/2,
                                                y + getFrameDepth() - (bracket.getHeight() + carRail.getP()),
                                                carRail.getB1(), carRail.getP()));
        getDrawingStructure().add(new Rectangle(x + wallDistance - carRail.getK()/2,
                                                y + getFrameDepth() - (bracket.getHeight() + carRail.getP() + carRail.getH1()),
                                                carRail.getK(), carRail.getH1()));

    }



}
