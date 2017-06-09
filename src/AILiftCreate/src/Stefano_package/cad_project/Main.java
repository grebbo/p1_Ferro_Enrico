package Stefano_package.cad_project;

import Stefano_package.components.*;
import Stefano_package.components.ICadComponent.Axis;
import Stefano_package.components._3d.*;
import javafx.geometry.Point3D;

public class Main {
	/**
	 * Testing purpose
	 * 
	 * @param args
	 */
	public static void main(String[] args) 
	{
		AiLiftProject3D p = new AiLiftProject3D();
		
		CadComponent3D pp1 = new CadParallelepiped(90, 60, 90, 100, 500, 100);
		CadComponent3D pp2 = new CadParallelepiped(-1, -1, -1, -10, -10, -10);
		CadComponent3D pp3 = new CadParallelepiped(20, 20, 20, 60, 30, 50);
		Point3D point = new Point3D(-10, -50, 0);
		CadComponent3D cy1 = new CadCylinder(point, 38, 140, Axis.X_AXIS);

		p.addComponent(cy1, 0, 0, 0, 0);
		p.addComponent(pp1, 0, 0, 0, 0);
		p.addComponent(pp2, 0, 0, 0, 0);
		p.addComponent(pp3, 0, 0, 0, 0);

		p.toFile_jscad("3d");

		AiLiftProject2D p2Dx = new AiLiftProject2D(p, Axis.X_AXIS);
		p2Dx.toFile_jscad("2Dx", false);
		AiLiftProject2D p2Dy = new AiLiftProject2D(p, Axis.Y_AXIS);
		p2Dy.toFile_jscad("2Dy", false);
		AiLiftProject2D p2Dz = new AiLiftProject2D(p, Axis.Z_AXIS);
		p2Dz.toFile_jscad("2Dz", false);
	}
}
