import java.util.ArrayList;

/**
 * Created by Enrico on 13/05/2017.
 */
public class FittedComponent extends Component {
    //attributes
    private String projectComponentID;
    private ArrayList<DrawingObject> drawingStructure;

    //methods
    public FittedComponent(String componentID, String projectComponentID) {
        super(componentID);
        this.projectComponentID = projectComponentID;
        this.drawingStructure = new ArrayList<>();
    }

    public String getProjectComponentID() {return projectComponentID;}

    public void render() {
        super.render();
        for (DrawingObject dObject: drawingStructure ) {
            dObject.render();
        }
    }
}
