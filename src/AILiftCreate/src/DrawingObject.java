/**
 * Created by Enrico on 14/05/2017.
 */
public abstract class DrawingObject {
    //attributes
    private double[] position;

    //methods
    public DrawingObject(double[] position){
        this.position = position;
    }

    public void render(){}
}
