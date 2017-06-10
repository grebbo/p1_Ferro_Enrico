package AIMSLab_server_interaction;

 /**
  * Singleton class, only one instance is needed in the system lifetime.
  * Parser used to convert the json files given by the AIMSLabServer of the projects to the objects that fits the
  * structure of the software, ready to be rendered.
  *
  * @author Enrico Ferro
  */

import com.fasterxml.jackson.databind.*;
import project_components.*;
import java.io.File;
import java.io.IOException;


public class Parser {
    /**
     * @attributes
     * jsonCursor -> used to browse through the json
     * instance -> singleton instance
     */
    private JsonNode jsonCursor;
    private static Parser instance;

    /**
     * @methods
     * Private constructor and getInstance used for the singleton structure
     */
    private void Parser(){jsonCursor = null;}
    public static Parser getInstance(){
        if(instance == null) {
            instance = new Parser();
        }
        return instance;
    }

    /**
     * This method uses the Jackson's library methods in order to browse and retrieve infos from the json file. For each
     * component the useful params are taken and used to create and populate che project class.
     *
     * @param jsonFile json file with the project infos
     * @return project class populated relatively to json content
     */
    public DrawingProject parseJson2DrawingProject(File jsonFile) {
        DrawingProject drawingProject = new DrawingProject();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            jsonCursor = objectMapper.readValue(jsonFile, JsonNode.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //about project
        drawingProject.setProjectID(jsonCursor.get("id").asText());
        //about installer user
        JsonNode userJson = jsonCursor.get("user");
        Installer installer = new Installer(userJson.get("name").asText(),
                                            userJson.get("lastname").asText(),
                                            userJson.get("address").asText(),
                                            userJson.get("username").asText(),
                                            userJson.get("password").asText(),
                                            userJson.get("id").asText());
        drawingProject.setCreator(installer);

        //about shaft
        JsonNode shaftJson = jsonCursor.get("shaft");
        Shaft shaft = new Shaft(shaftJson.get("id").asText() ,
                                shaftJson.get("width").asInt(),
                                shaftJson.get("depth").asInt(),
                                shaftJson.get("travel").asInt()/2,
                                0,
                                0);
        drawingProject.setShaft(shaft);


        //about carFrame and its sub-components
        JsonNode fittedCarFrameJson = jsonCursor.get("fittedCarFrameHydra");
        JsonNode carFrameJson = fittedCarFrameJson.get("carFrameHydra");
        JsonNode bracketJson = fittedCarFrameJson.get("carFrameHydraBracket");
        JsonNode carRailJson = fittedCarFrameJson.get("carRails");
        Bracket bracket = new Bracket(bracketJson.get("w2").asInt(),
                                      bracketJson.get("h2").asInt());
        CarRail carRail = new CarRail(carRailJson.get("p").asInt(),
                                      carRailJson.get("b1").asInt(),
                                      carRailJson.get("h1").asInt(),
                                      carRailJson.get("k").asInt());
        FittedCarFrame carFrame = new FittedCarFrame(carFrameJson.get("id").asText(),
                                                    carFrameJson.get("dist_asse_guide_filomuro").asInt(),
                                                    carFrameJson.get("dist_cabina_asse_guide").asInt(),
                                                    carFrameJson.get("dtg").asInt(),
                                                    fittedCarFrameJson.get("xbp").asInt(),
                                                    fittedCarFrameJson.get("ybp").asInt(),
                                                    bracket,
                                                    carRail);
        drawingProject.setCarFrame(carFrame);

        //about car
        JsonNode fittedCarJson = jsonCursor.get("fittedCar");
        JsonNode carJson = fittedCarJson.get("car");
        FittedCar car = new FittedCar(  carJson.get("id").asText(),
                                        carJson.get("width").asInt(),
                                        carJson.get("depth").asInt(),
                                        fittedCarJson.get("xBp").asInt(),
                                        fittedCarJson.get("yBp").asInt());
        drawingProject.setCar(car);

        //about carDoor
        JsonNode fittedCarDoorJson = jsonCursor.get("fittedCarDoorP");
        JsonNode carDoorJson = fittedCarDoorJson.get("carDoor");
        FittedCarDoor carDoor = new FittedCarDoor(  carDoorJson.get("id").asText(),
                                                    carDoorJson.get("totalWidth").asInt(),
                                                    carDoorJson.get("doorstep").asInt(),
                                                    fittedCarDoorJson.get("xbp").asInt(),
                                                    fittedCarDoorJson.get("ybp").asInt());
        drawingProject.setCarDoor(carDoor);

        //about landingDoor
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

    /**
     * This method uses the Jackson's library methods in order to parse the DrawingProject structure into a json file
     * that will be sent to the AIMSLabServer. The object mapper class scans the project class and sub-classes and,
     * thanks to the used tags, generate the json string.
     *
     * @param drawingProject project to be parsed
     * @return json file content
     */
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
