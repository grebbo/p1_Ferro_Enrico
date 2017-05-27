package project_components;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

/**
 * Created by Enrico on 13/05/2017.
 */
public abstract class Component {
    //attributes
    @JsonProperty("id")
    private String componentId;
    @JsonIgnore
    private ArrayList<Component> compatibleComponents;
    @JsonProperty("width")
    private int frameWidth;
    @JsonProperty("depth")
    private int frameDepth;

    //method
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

    public ArrayList<Component> getCompatibleComponents() {return compatibleComponents;}
    public int getFrameWidth() {return frameWidth;}
    public int getFrameDepth() {return frameDepth;}
    public void setFrameWidth(int frameWidth) {this.frameWidth = frameWidth;}
    public void setFrameDepth(int frameDepth) {this.frameDepth = frameDepth;}
}
