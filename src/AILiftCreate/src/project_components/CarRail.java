package project_components;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by enric on 01/06/2017.
 */
public class CarRail {
    //attributes
    //ortho rectangle
    @JsonProperty("p")
    private int p;
    @JsonProperty("b1")
    private int b1;

    //par rectangle
    @JsonProperty("h1")
    private int h1;
    @JsonProperty("k")
    private int k;

    //methods
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
