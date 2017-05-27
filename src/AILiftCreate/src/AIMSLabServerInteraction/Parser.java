package AIMSLabServerInteraction;

 /**AIMSLabServerInteraction.Parser class
 * Created by Enrico on 14/05/2017.
 * Gives all the methods of the Jackson json parser library to the AIMSLabServerInteraction.DrawingProject class
 */

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.*;
import project_components.*;
import java.io.File;
import java.io.IOException;

public class Parser {
    //attributes
    private JsonNode jsonCursor;
    private static Parser instance;

    //methods
    private void Parser(){jsonCursor = null;}

    public static Parser getInstance(){
        if(instance == null) {
            instance = new Parser();
        }
        return instance;
    }

    //from json file to AIMSLabServerInteraction.DrawingProject class structure
    public void parseJsonProject2DrawingProject(String projectJsonPath, DrawingProject drawingProject) {
        ObjectMapper objectMapper = new ObjectMapper();
        File jsonFile = new File(projectJsonPath);
        try {
            jsonCursor = objectMapper.readValue(jsonFile, JsonNode.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

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

    }

    //from given AIMSLabServerInteraction.DrawingProject to json, in order to be exported
    //to be completed
    public String parseComponents2Project(DrawingProject drawingProject){
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
