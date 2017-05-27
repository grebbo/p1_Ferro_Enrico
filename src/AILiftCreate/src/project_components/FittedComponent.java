package project_components;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import drawing_entities.DrawingObject;
import cad_project.AiLiftProject;
import components.CadComponent;

import java.util.ArrayList;

/**
 * Created by Enrico on 13/05/2017.
 */
public class FittedComponent extends Component {
    //attributes
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

    public void render(AiLiftProject myProject) {
        for (DrawingObject dObject: drawingStructure ) {
            CadComponent cadComponent = dObject.render();
            myProject.addComponent(cadComponent, 0, 0);
        }
    }
}
