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
     * @attributes
     * id -> each component type has a id code
     * width, depth -> component external frame dimensions
     * compatibleComponents -> list of components compatible with the one used
     *                         (implemented, but not used in this version)
     */
    @JsonProperty("id")
    private String componentId;
    @JsonIgnore
    private ArrayList<Component> compatibleComponents;
    @JsonProperty("width")
    private int frameWidth;
    @JsonProperty("depth")
    private int frameDepth;

    /**
     * @methods
     * constructors (default and with params), getters and setters
     */
    public Component(){
        componentId = "";
        compatibleComponents = new ArrayList<>();
        frameWidth = 0;
        frameDepth = 0;
    }
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
