package project_components;

import com.fasterxml.jackson.annotation.*;
import java.util.ArrayList;

/**
 * Primitive entity of the project. It has no features drawing-related
 * and is only described with the essential attributes.
 *
 * @author Enrico Ferro
 */

public abstract class Component {

    /**
     * Id of the component
     */
    @JsonProperty("id")
    private String componentId;
    /**
     * List of the components compatible with this component
     */
    @JsonIgnore
    private ArrayList<Component> compatibleComponents;
    /**
     * Width of the frame containing the component
     */
    @JsonProperty("width")
    private int frameWidth;
    /**
     * Height of the frame containing the component
     */
    @JsonProperty("depth")
    private int frameDepth;

    /**
     * Standard Constructor
     */
    public Component(){
        componentId = "";
        compatibleComponents = new ArrayList<>();
        frameWidth = 0;
        frameDepth = 0;
    }

    /**
     * Constructor with Params
     */
    public Component(String componentId, int frameWidth, int frameDepth) {
        this.componentId = componentId;
        compatibleComponents = new ArrayList<>();
        this.frameWidth = frameWidth;
        this.frameDepth = frameDepth;
    }

    public int getFrameWidth() {return frameWidth;}
    public int getFrameDepth() {return frameDepth;}
    public void setFrameWidth(int frameWidth) {this.frameWidth = frameWidth;}
    public void setFrameDepth(int frameDepth) {this.frameDepth = frameDepth;}

    /**
     * Adds a component to the compatibility list.
     *
     * @param c component to be added to compatibility list
     */
    public void addCompatibleComponent(Component c){
        compatibleComponents.add(c);
    }
}
