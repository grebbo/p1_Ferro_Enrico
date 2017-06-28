package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import AIMSLab_server_interaction.*;
import AIMSLab_server_interaction.DrawingProject.ComponentType;
import project_components.*;

/**
 * Testing class for the drawingProject. It tests the populating process of the class.
 * 
 * @author Enrico Ferro
 *
 */

public class TestDrawingProject {
	DrawingProject drawingProject;
	Shaft shaft;
	FittedCar car;
	FittedCarDoor carDoor;
	FittedCarFrame carFrame;
	FittedLandingDoor landingDoor;
	String projectID = "try";
	Installer creator;

	@Before
	public void setUp(){
		drawingProject = new DrawingProject();
		shaft = new Shaft("0", 1000, 1000, 1000, 0, 0);
		car = new FittedCar("1", 600, 600, 400, 0);
		carDoor = new FittedCarDoor("2", 100, 100, 10, 10, "CEN", 4);
		carFrame = new FittedCarFrame("3", 300, 0, 0, 0, 0, new Bracket(0, 0) , new CarRail(0, 0, 0, 0), 0);
		landingDoor = new FittedLandingDoor("4", 100, 100, 10, 0, "CEN", 4);
		creator = new Installer("Mario", "Bianchi", "Via di qui", "42", "M4r10", "none");
	}
	
	@Test
	public void testPopulateProject() {
		drawingProject.setShaft(shaft);
		assertSame("Not same shaft object", shaft, drawingProject.getComponentList().get(ComponentType.SHAFT));
		drawingProject.setCar(car);
		assertSame("Not same car object", car, drawingProject.getComponentList().get(ComponentType.CAR));
		drawingProject.setCarDoor(carDoor);
		assertSame("Not same car door object", carDoor, drawingProject.getComponentList().get(ComponentType.CARDOOR));
		drawingProject.setCarFrame(carFrame);
		assertSame("Not same car frame object", carFrame, drawingProject.getComponentList().get(ComponentType.CARFRAME));
		drawingProject.setLandingDoor(landingDoor);
		assertSame("Not same landing door object", landingDoor, drawingProject.getComponentList().get(ComponentType.LANDINGDOOR));
		
		drawingProject.setProjectID(projectID);
		assertEquals("Not same ID", projectID, drawingProject.getProjectID());
		drawingProject.setCreator(creator);
		assertSame("Not same creator", creator, drawingProject.getCreator());
	
	}
}
