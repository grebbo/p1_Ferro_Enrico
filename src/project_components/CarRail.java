package project_components;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This class models the carRail sub-component.
 *
 * @author Enrico Ferro
 */
public class CarRail {

    @JsonProperty("p")
    private int p;
    @JsonProperty("b1")
    private int b1;
    @JsonProperty("h1")
    private int h1;
    @JsonProperty("k")
    private int k;

    /**
     * Constructor
     */
    public CarRail(int p, int b1, int h1, int k){
        this.p = p;
        this.b1 = b1;
        this.h1 = h1;
        this.k = k;
    }

    public int getP() {return p;}
    public int getB1() {return b1;}
    public int getH1() {return h1;}
    public int getK() {return k;}
}
