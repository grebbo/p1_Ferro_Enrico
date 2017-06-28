package test;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.BeforeClass;
import org.junit.Test;

import AIMSLab_server_interaction.AIMSLabServer;
import AIMSLab_server_interaction.DrawingProject;
import AIMSLab_server_interaction.Parser;

/**
 * Testing for the loading use case. Fails if it's impossible to retrieve the project just saved, otherwise it's successful.
 */

public class TestLoadProject {
	static AIMSLabServer server;

	@BeforeClass
	public static void setUp() {
		server = AIMSLabServer.getInstance();
	}

	@Test
	public void testLoadProject() {
		Parser parser = Parser.getInstance();
		DrawingProject dp = parser.parseJson2DrawingProject(server.createProjectFromMeasures(null));

		server.saveProject(new File(parser.parseDrawingProject2Json(dp)), 0);
		
		try {
			dp = parser.parseJson2DrawingProject(server.retrieveProjectByID(0));
		} catch (Exception e) {
			fail("Fail");
			e.printStackTrace();
		}
		dp.render("render_output");
	}
}
