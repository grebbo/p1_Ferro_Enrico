package project_components;

import Stefano_package.cad_project.AiLiftProject2D;
import Stefano_package.components.CadComponent;
import com.fasterxml.jackson.annotation.*;
import drawing_entities.*;

import java.util.ArrayList;

/**
 * This class models a component once is assigned to a project, in order to be drawn. For this reason it extends
 * the Component class with drawing-related attributes
 *
 * @author Enrico Ferro
 */
public class FittedComponent extends Component {

    /**
     * List of the drawingObjects used to define the component during rendering
     */
    @JsonIgnore
    private ArrayList<DrawingObject> drawingStructure;
    /**
     * X coordinate of the component on the drawing plane
     */
    @JsonProperty("xbp")
    private int drawingStartingPoint_x;
    /**
     * Y coordinate of the component on the drawing plane
     */
    @JsonProperty("ybp")
    private int drawingStartingPoint_y;

    /**
     * Standard Constructor
     */
    public FittedComponent(){
        super();
        drawingStructure = new ArrayList<>();
        drawingStartingPoint_x = 0;
        drawingStartingPoint_y = 0;
    }

    /**
     * Constructor with Params
     */
    public FittedComponent(String componenId, int frameWidth, int frameDepth, int x, int y) {
        super(componenId, frameWidth, frameDepth);
        this.drawingStructure = new ArrayList<>();
        drawingStartingPoint_x = x;
        drawingStartingPoint_y = y;
    }

    public ArrayList<DrawingObject> getDrawingStructure(){return drawingStructure;}
    public int getDrawingStartingPoint_x() {return drawingStartingPoint_x;}
    public void setDrawingStartingPoint_x(int drawingStartingPoint_x) {this.drawingStartingPoint_x = drawingStartingPoint_x;}
    public int getDrawingStartingPoint_y() {return drawingStartingPoint_y;}
    public void setDrawingStartingPoint_y(int drawingStartingPoint_y) {this.drawingStartingPoint_y = drawingStartingPoint_y;}

    /**
     * Primitive method to "render" (that is create the rendering string for the ".jscad" file) the FittedComponent.
     * It creates a rendering object for each element in drawingStructure and adds it to the project.
     * The extrusion factor, used to give a 3D effect to the project drawing, is set to 1 by default. It's changed
     * overriding the function in children classes if needed.
     *
     * @param myProject rendering project to be populated with rendering elements
     */
    public void render(AiLiftProject2D myProject) {
        for (DrawingObject dObject: drawingStructure ) {
            CadComponent cadComponent = dObject.render();
            myProject.addComponent(cadComponent, 0, 0, 5000, 1);
        }
    }
}
