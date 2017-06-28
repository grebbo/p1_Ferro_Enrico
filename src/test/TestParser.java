package test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.BeforeClass;
import org.junit.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import AIMSLab_server_interaction.*;
import AIMSLab_server_interaction.DrawingProject.ComponentType;
import exceptions.OverlappingException;
import project_components.Bracket;
import project_components.CarRail;
import project_components.FittedCar;
import project_components.FittedCarDoor;
import project_components.FittedCarFrame;
import project_components.FittedLandingDoor;
import project_components.Shaft;

/**
 * Testing of the parser class. It test if a reverse parsing (from json to project and from project to json gives us the same result)
 * For each component parsed controls that the class created matches.
 */

public class TestParser {
	static Parser parser;
	static String jsonPath = "./project_jsons/symm_1200_1200";
	static DrawingProject dp;
	static JsonNode jsonCursor;
	
	@BeforeClass
	public static void setUp() throws OverlappingException {
		parser = Parser.getInstance();
		dp = parser.parseJson2DrawingProject(new File(jsonPath));
		String outputFilePath = parser.parseDrawingProject2Json(dp);
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			jsonCursor = objectMapper.readValue(new File(outputFilePath), JsonNode.class);
        } catch (IOException e) {
            e.printStackTrace(); 
        }
	}
	
	@Test
	public void testParsingUser() {
		JsonNode userJson = jsonCursor.get("user");
		Installer installer = new Installer(userJson.get("name").asText(),
                userJson.get("lastname").asText(),
                userJson.get("address").asText(),
                userJson.get("username").asText(),
                userJson.get("password").asText(),
                userJson.get("id").asText());
		
		assertEquals("Fail on installer parsing", installer, dp.getCreator());
	}
	
	@Test
	public void testParsingShaft(){
		JsonNode shaftJson = jsonCursor.get("shaft");
        Shaft shaft = new Shaft(shaftJson.get("id").asText() ,
                                shaftJson.get("width").asInt(),
                                shaftJson.get("depth").asInt(),
                                shaftJson.get("travel").asInt(),
                                shaftJson.get("xbp").asInt(),
                                shaftJson.get("ybp").asInt());
		
		assertEquals("Fail on shaft parsing", shaft, dp.getComponentList().get(ComponentType.SHAFT));
	}
	
	@Test
	public void testParsingCarFrame(){
		JsonNode fittedCarFrameJson = jsonCursor.get("fittedCarFrame");
        JsonNode bracketJson = fittedCarFrameJson.get("carFrameHydraBracket");
        JsonNode carRailJson = fittedCarFrameJson.get("carRails");
        Bracket bracket = new Bracket(bracketJson.get("w2").asInt(),
                                      bracketJson.get("h2").asInt());
        CarRail carRail = new CarRail(carRailJson.get("p").asInt(),
                                      carRailJson.get("b1").asInt(),
                                      carRailJson.get("h1").asInt(),
                                      carRailJson.get("k").asInt());
        FittedCarFrame carFrame = new FittedCarFrame(fittedCarFrameJson.get("id").asText(),
        		fittedCarFrameJson.get("dist_asse_guide_filomuro").asInt(),
        		fittedCarFrameJson.get("dist_cabina_asse_guide").asInt(),
        		fittedCarFrameJson.get("dtg").asInt(),
                fittedCarFrameJson.get("xbp").asInt(),
                fittedCarFrameJson.get("ybp").asInt(),
                bracket,
                carRail,
                fittedCarFrameJson.get("dist_pistone_guida_SX").asInt());
        
		assertEquals("Fail on carFrame parsing", carFrame, dp.getComponentList().get(ComponentType.CARFRAME));
	}
	
	@Test
	public void testParsingCar(){
		JsonNode fittedCarJson = jsonCursor.get("fittedCar");
        FittedCar car = new FittedCar(  fittedCarJson.get("id").asText(),
        		fittedCarJson.get("width").asInt(),
        		fittedCarJson.get("depth").asInt(),
                fittedCarJson.get("xbp").asInt(),
                fittedCarJson.get("ybp").asInt());
		
		assertEquals("Fail on car parsing", car, dp.getComponentList().get(ComponentType.CAR));
	}
	
	@Test
	public void testParsingLandingDoor(){
		JsonNode fittedLandingDoorJson = jsonCursor.get("fittedLandingDoor");
        FittedLandingDoor landingDoor = new FittedLandingDoor(  fittedLandingDoorJson.get("id").asText(),
        		fittedLandingDoorJson.get("totalWidth").asInt(),
        		fittedLandingDoorJson.get("doorstep").asInt(),
        		fittedLandingDoorJson.get("xbp").asInt(),
        		fittedLandingDoorJson.get("ybp").asInt(),
        		fittedLandingDoorJson.get("doorType").asText(),
        		fittedLandingDoorJson.get("numshutters").asInt());
		
		assertEquals("Fail on landingDoor parsing", landingDoor, dp.getComponentList().get(ComponentType.LANDINGDOOR));
	}
	
	@Test
	public void testParsingCarDoor(){
		 JsonNode fittedCarDoorJson = jsonCursor.get("fittedCarDoorP");
	        FittedCarDoor carDoor = new FittedCarDoor(  fittedCarDoorJson.get("id").asText(),
	        		fittedCarDoorJson.get("totalWidth").asInt(),
	        		fittedCarDoorJson.get("doorstep").asInt(),
	                fittedCarDoorJson.get("xbp").asInt(),
	                fittedCarDoorJson.get("ybp").asInt(),
	              	fittedCarDoorJson.get("doorType").asText(),
	                fittedCarDoorJson.get("numshutters").asInt());
		
		assertEquals("Fail on carDoor parsing", carDoor, dp.getComponentList().get(ComponentType.CARDOOR));
	}
}
