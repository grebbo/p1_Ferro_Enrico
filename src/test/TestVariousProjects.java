package test;

import static org.junit.Assert.*;

import java.io.File;
import java.nio.file.Files;

import org.junit.BeforeClass;
import org.junit.Test;

import AIMSLab_server_interaction.*;
import exceptions.OverlappingException;

/**
 * Tests various types of project and creates for each one the corresponding output file.
 * The result can be evaluated visually putting each output in the online service "jscad.org".
 * Using an invalid project (error put in the json file) is also tested the overlapping algorithm used, 
 * throwing a custom exception that notifies about the fail of the process.
 */

public class TestVariousProjects {
	String jsonFolder = "./project_jsons/";
	static Parser parser;
	static AIMSLabServer server;
	DrawingProject dp;
			
	@BeforeClass
	public static void setUp() {
		parser = Parser.getInstance();
		server = AIMSLabServer.getInstance();
	}
	
	@Test
	public void testCreateProjects() throws OverlappingException {
		File folder = new File(jsonFolder);
		for (File file : folder.listFiles()) {
			if(!file.getName().equals("overlap_json")) {
				server.setProjectJsonPath(jsonFolder + file.getName());
				dp = parser.parseJson2DrawingProject(server.createProjectFromMeasures(null));
				dp.render(file.getName() + "_output");
			}
		}
	}
	
	@Test
	public void testOverlapping() throws OverlappingException {
		parser = Parser.getInstance();
		server = AIMSLabServer.getInstance();
		server.setProjectJsonPath("./project_jsons/overlap_json");
		dp = parser.parseJson2DrawingProject(server.createProjectFromMeasures(null));
		dp.render("output");
	}

}
