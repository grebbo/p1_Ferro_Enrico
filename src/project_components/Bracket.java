package project_components;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This class models the bracket sub-component.
 *
 * @author Enrico Ferro
 */
public class Bracket {

    @JsonProperty("w2")
    private int width;
    @JsonProperty("h2")
    private int height;

    /**
     * Constructor
     */
    public Bracket(int width, int height){
        this.width = width;
        this.height = height;
    }

    public int getWidth() {return width;}
    public int getHeight() {return height;}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Bracket bracket = (Bracket) o;

        if (width != bracket.width) return false;
        return height == bracket.height;
    }
}
