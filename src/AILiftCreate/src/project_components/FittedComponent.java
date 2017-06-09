package project_components;

import Stefano_package.cad_project.AiLiftProject2D;
import Stefano_package.components.CadComponent;
import com.fasterxml.jackson.annotation.*;
import drawing_entities.*;

import java.util.ArrayList;

/**
 * Created by Enrico on 13/05/2017.
 * This class models a component once is assigned to a project, in order to be drawn. For this reason it extends
 * the Component class with drawing-related attributes
 */
public class FittedComponent extends Component {
    /**
     * @attributes
     * drawingStructure -> drawing objects container, used for rendering
     * drawingStartingPoint_x/y -> drawing coordinates for the rectangular frame (top-left corner)
     */
    @JsonIgnore
    private ArrayList<DrawingObject> drawingStructure;
    @JsonProperty("xbp")
    private int drawingStartingPoint_x;
    @JsonProperty("ybp")
    private int drawingStartingPoint_y;

    /**
     * @methods
     * constructors (default and with params), getters and setters
     */
    public FittedComponent(){
        super();
        drawingStructure = new ArrayList<>();
        drawingStartingPoint_x = 0;
        drawingStartingPoint_y = 0;
    }
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
     * @param myProject -> rendering project to be populated with rendering elements
     * Primitive method to "render" (that is create the rendering string for the ".jscad" file) the FittedComponent.
     * It creates a rendering object for each element in drawingStructure and adds it to the project.
     */
    public void render(AiLiftProject2D myProject) {
        for (DrawingObject dObject: drawingStructure ) {
            CadComponent cadComponent = dObject.render();
            myProject.addComponent(cadComponent, 0, 0, 5000, 1);
        }
    }
}
