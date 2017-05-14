import java.util.ArrayList;

/**
 * Created by Enrico on 13/05/2017.
 */
public abstract class Component {
    //attributes
    private String componentID;
    private ArrayList<Component> compatibleComponents;

    //method
    public Component(String componentID) {
        this.componentID = componentID;
        compatibleComponents = new ArrayList<>();
        //fill compatibility list depending on which component is considered
        switch (componentID) {
            default:
                break;
        }
    }

    public String getComponentID() {return componentID;}
    public ArrayList<Component> getCompatibleComponents() {return compatibleComponents;}

    public void render() {}
}
