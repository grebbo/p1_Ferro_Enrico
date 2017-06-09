package project_components;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by enric on 01/06/2017.
 */
public class Bracket {
    //attributes
    @JsonProperty("w2")
    private int width;
    @JsonProperty("h2")
    private int height;

    //methods
    public Bracket(int width, int height){
        this.width = width;
        this.height = height;
    }

    public int getWidth() {return width;}
    public int getHeight() {return height;}
}
