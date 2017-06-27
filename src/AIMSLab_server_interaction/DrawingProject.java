package AIMSLab_server_interaction;

import Stefano_package.cad_project.AiLiftProject2D;
import com.fasterxml.jackson.annotation.*;
import project_components.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * This class models the project infos (id, installer) and structure (Stefano_package.components).
 *
 * @author Enrico Ferro
 */

/*
 * Tag that grants access to each field (private or public) during the parsing operation
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY )
public class DrawingProject {

    /**
     * Component types in the project. One of each type.
     */
    public enum ComponentType {
        SHAFT,
        CAR,
        CARDOOR,
        LANDINGDOOR,
        CARFRAME
    }

    /*
     * Tags are used to map attributes to json fields, mantaining name in line with the OOP conventions.
     */

    /**
     * ID of the project
     */
    @JsonProperty("id")
    private String projectID;
    /**
     * User who created the project
     */
    @JsonProperty("user")
    private Installer creator;
    /**
     * List of the components that makes the project
     */
    @JsonIgnore
    private Map<ComponentType,FittedComponent> componentList;

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
    /**
     * Parser instance, used to populate the class
     */
    @JsonIgnore
    public Parser parser;

    /**
     * Constructor
     */
    public DrawingProject(){
        componentList = new HashMap<>();
        parser = Parser.getInstance();
    }

    public Map<ComponentType,FittedComponent> getComponentList(){return componentList;}
    public String getProjectID() {return projectID;}
    public Installer getCreator() {return creator;}

    public void setShaft(Shaft shaft) {
        componentList.put(ComponentType.SHAFT, shaft);
        this.shaft = shaft;
    }
    public void setCarFrame(FittedCarFrame carFrame) {
        componentList.put(ComponentType.CARFRAME, carFrame);
        this.carFrame = carFrame;
    }
    public void setCar(FittedCar car) {
        componentList.put(ComponentType.CAR, car);
        this.car = car;
    }
    public void setCarDoor(FittedCarDoor carDoor) {
        componentList.put(ComponentType.CARDOOR, carDoor);
        this.carDoor = carDoor;
    }
    public void setLandingDoor(FittedLandingDoor landingDoor) {
        componentList.put(ComponentType.LANDINGDOOR, landingDoor);
        this.landingDoor = landingDoor;
    }
    public void setProjectID(String projectID) {this.projectID = projectID;}
    public void setCreator(Installer creator) {this.creator = creator;}

    /**
     * First version of the render function. It is not an effective rendering, it instead generate a file to be used as
     * input in the jscad web app ("https://openjscad.org/"). See readme.md for instructions about this. In a second
     * version of the software such operations will be automatic.
     *
     * @param fileOutputName name of the output file
     */
    public void render(String fileOutputName){
        AiLiftProject2D project2render = new AiLiftProject2D();
        for (FittedComponent c: componentList.values()) {
            c.render(project2render);
        }
        project2render.toFile_jscad(fileOutputName, true);
    }
}
