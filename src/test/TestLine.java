package test;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import Stefano_package.components._2d.*;
import drawing_entities.*;

/**
 * Simple testing of the line class
 */

public class TestLine {
	static Line line;
	
	@BeforeClass
	public static void setUp() {
		int endX = 0;
		int endY = 5;
		line = new Line(0, 0, endX, endY);
	}
	
	@Test
	public void testLine() {
		CadLineOrtho drawLine = line.render();
		assertTrue("Rendering error", line.getPositionX() == drawLine.getX() &&
									  line.getPositionY() == drawLine.getY() &&
									  line.getEndPointX() == drawLine.getEndingPoint().x &&
									  line.getEndPointY() == drawLine.getEndingPoint().y);
	}

}
