package project_components;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This class models the bracket sub-component.
 *
 * @author Enrico Ferro
 */
public class Bracket {
    /**
     * @attributes
     * width, height -> bracket dimensions
     */
    @JsonProperty("w2")
    private int width;
    @JsonProperty("h2")
    private int height;

    /**
     * @methods
     * constructor and getters
     */
    public Bracket(int width, int height){
        this.width = width;
        this.height = height;
    }

    public int getWidth() {return width;}
    public int getHeight() {return height;}
}
