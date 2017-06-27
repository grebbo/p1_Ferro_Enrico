package project_components;

import com.fasterxml.jackson.annotation.*;
import drawing_entities.*;

/**
 * It models the carFrame(lateral lift moving guide). It has some sub-Stefano_package.components that defines the structure in a more
 * detailed way than the mere frame.
 *
 * @author Enrico Ferro
 */
public class FittedCarFrame extends FittedComponent {

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
    @JsonProperty("dist_pistone_guida_SX")
    private int pistDistance;

    /**
     * Constructor. It also defines the sub-component structure (CarRails and Brackets).
     */
    public FittedCarFrame(String componentId, int wallDistance, int cabinDistance, int dtg, int x, int y, Bracket bracket, CarRail carRail, int pistDistance) {
        super(componentId, wallDistance+cabinDistance, dtg + 2*(bracket.getHeight() + carRail.getP() + carRail.getH1()), x, y);
        this.wallDistance = wallDistance;
        this.cabinDistance = cabinDistance;
        this.bracket = bracket;
        this.carRail = carRail;
        this.dtg = dtg;
        this.pistDistance = pistDistance;
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
        //add meccanic frame
        getDrawingStructure().add(new Rectangle(x + wallDistance - carRail.getB1()/2,
                                                y + bracket.getHeight() + carRail.getP()+ carRail.getH1(),
                                                carRail.getB1(), dtg));
        //add piston comp
        getDrawingStructure().add(new Circle(x + wallDistance,
                                             y + bracket.getHeight() + carRail.getP()+ carRail.getH1() + pistDistance,
                                             carRail.getB1()/2));

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FittedCarFrame that = (FittedCarFrame) o;

        if (wallDistance != that.wallDistance) return false;
        if (cabinDistance != that.cabinDistance) return false;
        if (dtg != that.dtg) return false;
        if (pistDistance != that.pistDistance) return false;
        if (bracket != null ? !bracket.equals(that.bracket) : that.bracket != null) return false;
        return carRail != null ? carRail.equals(that.carRail) : that.carRail == null;
    }
}
