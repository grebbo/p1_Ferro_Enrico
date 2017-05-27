package AIMSLabServerInteraction;

import cad_project.AiLiftProject;
import com.fasterxml.jackson.annotation.*;
import project_components.*;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Enrico on 13/05/2017.
 */
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY )
public class DrawingProject {
    //attributes
    private String projectID;
    private Installer creator;
    @JsonIgnore
    private ArrayList<FittedComponent> componentList;
    private Shaft shaft;
    private FittedCarFrame carFrame;
    private FittedCar car;
    private FittedCarDoor carDoor;
    private FittedLandingDoor landingDoor;
    //singletons
    @JsonIgnore
    public Parser parser;

    //methods
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

    public void render(){
        AiLiftProject project2render = new AiLiftProject();
        for (FittedComponent c: componentList) {
            c.render(project2render);
        }
        project2render.toFile_jscad("render_output.txt");
    }



    //testing main
    public static void main(String [] args){
        String projectJsonPath = "1400_1700_CFSX";
        Parser parser = new Parser();
        File jsonFile = new File(projectJsonPath);
        DrawingProject drawingProject = new DrawingProject();
        parser.parseJsonProject2DrawingProject(projectJsonPath, drawingProject);
        drawingProject.render();

        System.out.println(parser.parseComponents2Project(drawingProject));
    }
}
