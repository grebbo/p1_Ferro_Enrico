import java.util.ArrayList;

/**
 * Created by Enrico on 13/05/2017.
 */
public class FittedComponent extends Component {
    //attributes
    private String projectComponentID;
    private ArrayList<DrawingObject> drawingStructure;
    private int xbp;
    private int ybp;

    //methods

    public FittedComponent(){
        super();
        projectComponentID = null;
        drawingStructure = new ArrayList<>();
        xbp = ybp = 0;
    }

    public FittedComponent(String componentID, String projectComponentID, int frameWidth, int frameDepth) {
        super(componentID, frameWidth, frameDepth);
        this.projectComponentID = projectComponentID;
        this.drawingStructure = new ArrayList<>();
        xbp = ybp = 0;
    }

    public void render() {
        super.render();
        for (DrawingObject dObject: drawingStructure ) {
            dObject.render();
        }
    }
}
