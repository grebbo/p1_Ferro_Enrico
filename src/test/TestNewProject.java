package test;

import org.junit.BeforeClass;
import org.junit.Test;

import AIMSLab_server_interaction.*;
import exceptions.OverlappingException;

/**
 * Testing for the project creation process. It can be evaluated only visually, putting the output file in the online service "jscad.org"
 */

public class TestNewProject {
	static AIMSLabServer server;

	@BeforeClass
	public static void setUp() {
		server = AIMSLabServer.getInstance();
	}

	@Test
	public void testNewProject() throws OverlappingException {
		Parser parser = Parser.getInstance();
		DrawingProject dp = parser.parseJson2DrawingProject(server.createProjectFromMeasures(null));
		dp.render("render_output");
	}
}
