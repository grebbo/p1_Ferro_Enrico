package test;

import org.junit.BeforeClass;
import org.junit.Test;

import AIMSLab_server_interaction.*;
import exceptions.OverlappingException;

/**
 * Rendering project testing class. It can be evaluated only visually, putting the output file in the online service "jscad.org".
 */

public class TestRendering {
	static AIMSLabServer server;
	static Parser parser;
	static DrawingProject dp;
	
	@BeforeClass
	public static void setUp() throws OverlappingException {
		parser = Parser.getInstance();
		server = AIMSLabServer.getInstance();
		dp = parser.parseJson2DrawingProject(server.createProjectFromMeasures(null));
	}

	@Test
	public void testRendering() {
		dp.render("output");
	}

}
