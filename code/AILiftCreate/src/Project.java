import java.util.ArrayList;

/**
 * Created by Enrico on 13/05/2017.
 */
public class Project {
    //attributes
    private String projectID;
    private Installer creator;
    private ArrayList<Component> componentList;
    private Shaft shaft;
    private FittedCarFrame carFrame;
    private FittedCar car;
    private FittedCarDoor carDoor;
    private FittedLandingDoor landingDoor;

    //singletons
    public Parser parser;
    public Renderer renderer;
    public Generator generator;
    public Database datebase;

    //methods
    public Project(){

    }

    public Project(String projectID, Installer creator, Parser parser, Renderer renderer, Generator generator, Database database){
        this.projectID = projectID;
        this.creator = creator;

        this.parser = parser;
        this.renderer = renderer;
        this.generator = generator;
        this.datebase = database;

        componentList = new ArrayList<>();
        componentList.add(shaft);
        componentList.add(carFrame);
        componentList.add(car);
        componentList.add(carDoor);
        componentList.add(landingDoor);
    }

    public String getProjectID() {return projectID;}
    public Installer getCreator() {return creator;}

    public void addComponent(Component component) {
        this.componentList.add(component);
    }

    public void render(){
        for (Component c: componentList) {
            c.render();
        }
    }
}
