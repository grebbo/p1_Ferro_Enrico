package test;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.BeforeClass;
import org.junit.Test;

import AIMSLab_server_interaction.AIMSLabServer;
import AIMSLab_server_interaction.DrawingProject;
import AIMSLab_server_interaction.Parser;

/**
 * Testing for the saving process. It controls that the project is successfully saved in the server.
 */

public class TestSaveProject {
	static AIMSLabServer server;

	@BeforeClass
	public static void setUp() {
		server = AIMSLabServer.getInstance();
	}

	@Test
	public void testSaveProject() {
		Parser parser = Parser.getInstance();
		DrawingProject dp = parser.parseJson2DrawingProject(server.createProjectFromMeasures(null));

		server.saveProject(new File(parser.parseDrawingProject2Json(dp)), 0);
		
		assertTrue("Project not saved", server.getProjects().containsKey(0));
	}

}
