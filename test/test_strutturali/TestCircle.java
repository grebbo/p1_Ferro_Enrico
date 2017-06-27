package test;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import Stefano_package.components._2d.*;
import drawing_entities.*;

public class TestCircle {
	static Circle circle;
	
	@BeforeClass
	public static void setUp() {
		int radius = 5;
		circle = new Circle(0, 0, radius);
	}
	
	@Test
	public void testCircle() {
		CadCircle drawCircle = circle.render();
		assertTrue("Rendering error", circle.getPositionX() == drawCircle.getX() && 
									  circle.getPositionY() == drawCircle.getY() && 
									  circle.getRadius() == drawCircle.getRadius());
	}

}
