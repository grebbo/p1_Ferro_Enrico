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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CarRail carRail = (CarRail) o;

        if (p != carRail.p) return false;
        if (b1 != carRail.b1) return false;
        if (h1 != carRail.h1) return false;
        return k == carRail.k;
    }
}
