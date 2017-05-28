package Stefano_package.cad_project;

import java.awt.Point;
import java.util.ArrayList;

import Stefano_package.components.CadCircle;
import Stefano_package.components.CadComponent;
import Stefano_package.components.CadPath;
import Stefano_package.components.CadRectangle;

/**
 * This main is only to test compilation
 * 
 * @author Stefano Demarchi
 * @version 1.0.0
 */
public class Main {

	public static void main(String[] args) {
		AiLiftProject p = new AiLiftProject();
		
		CadComponent r1 = new CadRectangle(10, -80, 50, 50);
		CadComponent r2 = new CadRectangle(-10, 100, 90, 60);
		CadComponent c1 = new CadCircle(5, 16, 19);
		ArrayList<Point> l = new ArrayList<Point>();
		Point c = new Point(0, 0);
		Point a = new Point(-15,0);
		Point b = new Point(0, 15);
		l.add(c);
		l.add(a);
		l.add(b);
		CadComponent p1 = new CadPath(l, "ARC");
		p.addComponent(r1, 0, 0);
		p.addComponent(r2, 0, 0);
		p.addComponent(c1, 10, 10);
		p.addComponent(p1, 0, 0);
		
		p.toFile_jscad("out");
	}
}
