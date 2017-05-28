package project_components;

import Stefano_package.cad_project.AiLiftProject;
import Stefano_package.components.CadComponent;
import com.fasterxml.jackson.annotation.*;
import drawing_entities.*;

import java.util.ArrayList;

/**
 * Created by Enrico on 13/05/2017.
 */
//once a component is assigned to a project, it is prepared to be rendered, so drawing coordinates are needed
//a fitted component is a generic component plus all the parameters useful to this aim
public class FittedComponent extends Component {
    //attributes
    //structure of drawing objects(rectangle, circle, etc.) that are the primitive entities for rendering
    @JsonIgnore
    private ArrayList<DrawingObject> drawingStructure;
    @JsonProperty("xbp")
    private int drawingStartingPoint_x;
    @JsonProperty("ybp")
    private int drawingStartingPoint_y;

    //methods

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

    //for each drawing entity creates the related rendering string and add the component to the cadProject, in order to be rendered
    public void render(AiLiftProject myProject) {
        for (DrawingObject dObject: drawingStructure ) {
            CadComponent cadComponent = dObject.render();
            myProject.addComponent(cadComponent, 0, 0);
        }
    }
}
