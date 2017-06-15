package AIMSLab_server_interaction;

import Stefano_package.cad_project.AiLiftProject2D;
import com.fasterxml.jackson.annotation.*;
import project_components.*;

import java.util.ArrayList;

/**
 * This class models the project infos (id, installer) and structure (Stefano_package.components).
 *
 * @author Enrico Ferro
 */

/**
 * Tag that grants access to each field (private or public) during the parsing operation
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY )
public class DrawingProject {
    /**
     * @attributes
     * Tags are used to map attributes to json fields, mantaining name in line with the OOP conventions.
     * id -> project identifier
     * user -> project creator
     * componentList -> container of FittedComponents, used for better fruition
     * lift Stefano_package.components -> each type of component that forms the lift structure
     * parser -> singleton class, used to parse the json fil containing the project structure and populate the class
     */
    @JsonProperty("id")
    private String projectID;
    @JsonProperty("user")
    private Installer creator;
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
    @JsonIgnore
    public Parser parser;

    /**
     * @methods
     * constructor, getters and setters
     */
    public DrawingProject(){
        componentList = new ArrayList<>();
        parser = Parser.getInstance();
    }
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

    /**
     * First version of the render function. It is not an effective rendering, it instead generate a file to be used as
     * input in the jscad web app ("https://openjscad.org/"). See readme.md for instructions about this. In a second
     * version of the software such operations will be automatic.
     *
     * @param project2render -> project rendering file
     */
    public void render(AiLiftProject2D project2render){
        for (FittedComponent c: componentList) {
            c.render(project2render);
        }
        project2render.toFile_jscad("render_output", true);
    }
}
