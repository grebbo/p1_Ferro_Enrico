package test;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import Stefano_package.components._2d.*;
import drawing_entities.*;

public class TestRectangle {
	static Rectangle rectangle;
	
	@BeforeClass
	public static void setUp() {
		int width = 5;
		int height = 4;
		rectangle = new Rectangle(0, 0, width, height);
	}
	
	@Test
	public void testRectangle() {
		CadRectangle drawRectangle = rectangle.render();
		assertTrue("Rendering error", rectangle.getPositionX() == drawRectangle.getX() && 
									  rectangle.getPositionY() == drawRectangle.getY() && 
									  rectangle.getWidth() == drawRectangle.getW() &&
									  rectangle.getHeight() == drawRectangle.getH());
	}

}
