import java.util.ArrayList;

/**
 * Created by Enrico on 13/05/2017.
 */
public abstract class Component {
    //attributes
    private String componentID;
    private ArrayList<Component> compatibleComponents;
    private int frameWidth;
    private int frameDepth;

    //method
    public Component(){
        componentID = null;
        compatibleComponents = new ArrayList<>();
        frameWidth = 0; frameDepth = 0;
    }

    public Component(String componentID, int frameWidth, int frameDepth) {
        this.componentID = componentID;
        compatibleComponents = new ArrayList<>();
        this.frameWidth = frameWidth;
        this.frameDepth = frameDepth;
    }

    public String getComponentID() {return componentID;}
    public ArrayList<Component> getCompatibleComponents() {return compatibleComponents;}
    public int getFrameWidth() {return frameWidth;}
    public int getFrameDepth() {return frameDepth;}

    public void render() {}
}
