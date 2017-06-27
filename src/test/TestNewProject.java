package test;

import org.junit.BeforeClass;
import org.junit.Test;

import AIMSLab_server_interaction.*;


public class TestNewProject {
	static AIMSLabServer server;

	@BeforeClass
	public static void setUp() {
		server = AIMSLabServer.getInstance();
	}

	@Test
	public void testNewProject() {
		Parser parser = Parser.getInstance();
		DrawingProject dp = parser.parseJson2DrawingProject(server.createProjectFromMeasures(null));
		dp.render("render_output");
	}
}
