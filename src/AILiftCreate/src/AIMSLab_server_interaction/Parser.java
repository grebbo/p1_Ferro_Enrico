package AIMSLab_server_interaction;

 /**
  * Created by Enrico on 14/05/2017.
  * Parser used to convert the json files given by the AIMSLabServer of the projects to
  * the objects that fit the structure of the software, in order to be rendered
  */

import com.fasterxml.jackson.databind.*;
import project_components.*;
import java.io.File;
import java.io.IOException;


public class Parser {
    //attributes
    //used for json reading and to model the structure of the json as a class
    private JsonNode jsonCursor;
    //this class is a singleton, that is there can be only one instance at time (static for this reason)
    private static Parser instance;

    //methods
    //as this class is a singleton the constructor method is in a private scope, as can only be
    //called by the getInstace() below
    private void Parser(){jsonCursor = null;}
    //returns the current (and only) instance of the class or creates one if there isn't any
    public static Parser getInstance(){
        if(instance == null) {
            instance = new Parser();
        }
        return instance;
    }

    //method to parse from json file to drawingProject class structure
    //takes the json path as a parameter and returns the project created from it
    public DrawingProject parseJson2DrawingProject(String projectJsonPath) {
        DrawingProject drawingProject = new DrawingProject();
        ObjectMapper objectMapper = new ObjectMapper();
        File jsonFile = new File(projectJsonPath);
        try {
            jsonCursor = objectMapper.readValue(jsonFile, JsonNode.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //grab generico infos of the project (creator and id)
        JsonNode userJson = jsonCursor.get("user");
        Installer installer = new Installer(userJson.get("name").asText(),
                                            userJson.get("lastname").asText(),
                                            userJson.get("address").asText(),
                                            userJson.get("username").asText(),
                                            userJson.get("password").asText(),
                                            userJson.get("id").asText());
        drawingProject.setCreator(installer);

        drawingProject.setProjectID(jsonCursor.get("id").asText());

        //NOTE: x and y defined by default. They're the postion of the drawing starting point (top-left)
        //grab shaft parameters and add to project
        JsonNode shaftJson = jsonCursor.get("shaft");
        Shaft shaft = new Shaft(shaftJson.get("id").asText() ,
                                shaftJson.get("width").asInt(),
                                shaftJson.get("depth").asInt(),
                                0,
                                0);
        drawingProject.setShaft(shaft);


        //grab carFrame parameters and add to project
        JsonNode fittedCarFrameJson = jsonCursor.get("fittedCarFrameHydra");
        JsonNode carFrameJson = fittedCarFrameJson.get("carFrameHydra");
        FittedCarFrame carFrame = new FittedCarFrame(carFrameJson.get("id").asText(),
                                                    carFrameJson.get("dist_asse_guide_filomuro").asInt(),
                                                    carFrameJson.get("dist_cabina_asse_guide").asInt(),
                                                    carFrameJson.get("ingtrasv").asInt(),
                                                    fittedCarFrameJson.get("xbp").asInt(),
                                                    fittedCarFrameJson.get("ybp").asInt());
        drawingProject.setCarFrame(carFrame);

        //grab car parameters and add to project
        JsonNode fittedCarJson = jsonCursor.get("fittedCar");
        JsonNode carJson = fittedCarJson.get("car");
        FittedCar car = new FittedCar(  carJson.get("id").asText(),
                                        carJson.get("totalWidth").asInt(),
                                        carJson.get("totalDepth").asInt(),
                                        fittedCarJson.get("xBp").asInt(),
                                        fittedCarJson.get("yBp").asInt());
        drawingProject.setCar(car);

        //grab carDoor parameters and add to project
        JsonNode fittedCarDoorJson = jsonCursor.get("fittedCarDoorP");
        JsonNode carDoorJson = fittedCarDoorJson.get("carDoor");
        FittedCarDoor carDoor = new FittedCarDoor(  carDoorJson.get("id").asText(),
                                                    carDoorJson.get("totalWidth").asInt(),
                                                    carDoorJson.get("doorstep").asInt(),
                                                    fittedCarDoorJson.get("xbp").asInt(),
                                                    fittedCarDoorJson.get("ybp").asInt());
        drawingProject.setCarDoor(carDoor);

        //grab landingDoor parameters and add to project
        JsonNode fittedLandingDoorJson = jsonCursor.get("fittedLandingDoor");
        JsonNode landingDoorJson = fittedLandingDoorJson.get("landingDoor");
        FittedLandingDoor landingDoor = new FittedLandingDoor(  landingDoorJson.get("id").asText(),
                                                                landingDoorJson.get("totalWidth").asInt(),
                                                                landingDoorJson.get("doorstep").asInt(),
                                                                fittedLandingDoorJson.get("xbp").asInt(),
                                                                fittedLandingDoorJson.get("ybp").asInt());
        drawingProject.setLandingDoor(landingDoor);
        return drawingProject;
    }

    //from given AIMSLab_server_interaction.DrawingProject to json, in order to be exported
    //creates a the json string of the project
    public String parseDrawingProject2Json(DrawingProject drawingProject){
        String projectJson = "";
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            projectJson = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(drawingProject);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return projectJson;
    }

}
