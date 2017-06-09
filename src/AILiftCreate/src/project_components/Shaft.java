package project_components;

import Stefano_package.cad_project.AiLiftProject2D;
import Stefano_package.components.CadComponent;
import com.fasterxml.jackson.annotation.JsonProperty;
import drawing_entities.*;

/**
 * Created by enric on 23/05/2017.
 * Class modeling the shaft (general frame) of the lift.
 * It inherits his attributes and methods from FittedComponent class
 */
public class Shaft extends FittedComponent {
    /**
     * @attributes
     * travel -> height of the lift shaft
     */
    @JsonProperty("travel")
    private int travel;

    /**
     * @methods
     * constructor, getters and setters
     */
    public Shaft(String componentId, int frameWidth, int frameDepth, int travel, int x, int y) {
        super(componentId, frameWidth, frameDepth, x, y);
        this.travel = travel;
        /**
         * For project aims the frame is represented as a box (each wall is a single entity)
         */
        getDrawingStructure().add(new Rectangle(x - 5, y, 5 , getFrameDepth()));
        getDrawingStructure().add(new Rectangle(x , y - 5, getFrameWidth() , 5));
        getDrawingStructure().add(new Rectangle(x + getFrameWidth(), y, 5 , getFrameDepth()));
        getDrawingStructure().add(new Rectangle(x , y + getFrameDepth(), getFrameWidth() , 5));
    }
    public int getTravel() {return travel;}
    public void setTravel(int travel) {this.travel = travel;}

    /**
     * @param project2render -> project file
     * Override of the FittedComponent method. This is the only component we want to be extruded in this version of the
     * software, so the extrusionFactor is set else than zero.
     */
    public void render(AiLiftProject2D project2render){
        for (DrawingObject dObject: getDrawingStructure() ) {
            CadComponent cadComponent = dObject.render();
            project2render.addComponent(cadComponent, 0, 0, 5000, travel/10);
        }
    }
}
