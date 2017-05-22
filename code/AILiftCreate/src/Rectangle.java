/**
 * Created by enric on 22/05/2017.
 */
public class Rectangle extends DrawingObject {
    //attributes
    private int width;
    private int height;

    //methods
    public Rectangle(int[] position, int width, int height){
        super(position);
        this.width = width;
        this.height = height;
    }
    //getters
    public int getWidth() {return width;}
    public int getHeight() {return height;}

    public void render(){

    }
}
