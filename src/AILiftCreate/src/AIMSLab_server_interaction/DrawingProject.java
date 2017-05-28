package AIMSLab_server_interaction;

import Stefano_package.cad_project.AiLiftProject;
import com.fasterxml.jackson.annotation.*;
import project_components.*;

import java.io.File;
import java.util.ArrayList;

/**this class models the project structure. Uses all the annotations in order to be parsed by
 * the jackson's library methods.
 * Created by Enrico on 13/05/2017.
 */

//tag that grants access to each field (private or public) during the parsing operation
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY )
public class DrawingProject {
    //attributes
    //each attribute has its tag in order to be recognised in the json and still mantaining name in line with
    //the OOP conventions
    @JsonProperty("id")
    private String projectID;
    @JsonProperty("user")
    private Installer creator;
    //component list that groups all the Stefano_package.components below, used for a better access to them
    @JsonIgnore
    private ArrayList<FittedComponent> componentList;
    @JsonProperty("shaft")
    private Shaft shaft;
    @JsonProperty("fittedCarFrame")
    private FittedCarFrame carFrame;
    @JsonProperty("fittedCar")
    private FittedCar car;
    @JsonProperty("fittedCarDoorP")
    private FittedCarDoor carDoor;
    @JsonProperty("fittedLandingDoor")
    private FittedLandingDoor landingDoor;
    //singletons
    @JsonIgnore
    public Parser parser;

    //methods
    public DrawingProject(){
        componentList = new ArrayList<>();
        parser = Parser.getInstance();
    }

    //useful getters and setters
    public ArrayList<FittedComponent> getComponentList(){return componentList;}
    public void setShaft(Shaft shaft) {
        componentList.add(shaft);
        this.shaft = shaft;
    }
    public void setCarFrame(FittedCarFrame carFrame) {
        componentList.add(carFrame);
        this.carFrame = carFrame;
    }
    public void setCar(FittedCar car) {
        componentList.add(car);
        this.car = car;
    }
    public void setCarDoor(FittedCarDoor carDoor) {
        componentList.add(carDoor);
        this.carDoor = carDoor;
    }
    public void setLandingDoor(FittedLandingDoor landingDoor) {
        componentList.add(landingDoor);
        this.landingDoor = landingDoor;
    }
    public void setProjectID(String projectID) {this.projectID = projectID;}
    public void setCreator(Installer creator) {this.creator = creator;}

    //render function: this is a first version of the render function.
    //This creates a file "render output" containing the code for the jscad web application,
    // available at " https://openjscad.org/ ". The content of the above mentioned file can be rendered
    //simply by drag-n-dropping it in the box in the bottom left.
    //In a second version of the software such operations will be automatic.
    public void render(){
        //create a render project object and writes to a file ".jscad" the strings with the commands
        AiLiftProject project2render = new AiLiftProject();
        for (FittedComponent c: componentList) {
            c.render(project2render);
        }
        project2render.toFile_jscad("render_output");
    }
}
