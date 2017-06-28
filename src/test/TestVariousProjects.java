package test;

import static org.junit.Assert.*;

import java.io.File;
import java.nio.file.Files;

import org.junit.BeforeClass;
import org.junit.Test;

import AIMSLab_server_interaction.*;

/**
 * Tests various types of project and creates for each one the corresponding output file.
 * The result can be evaluated visually putting each output in the online service "jscad.org".
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
	public void testCreateProjects() {
		File folder = new File(jsonFolder);
		for (File file : folder.listFiles()) {
			server.setProjectJsonPath(jsonFolder + file.getName());
			dp = parser.parseJson2DrawingProject(server.createProjectFromMeasures(null));
			dp.render(file.getName() + "_output");
		}
	}

}
