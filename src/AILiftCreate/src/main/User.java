package main;

import cad_project.AiLiftProject;
import components.CadComponent;
import components.CadCircle;
import components.CadRectangle;

public class User 
{

	public static void main(String[] args)
	{
		// Parametric init
		CadRectangle r1 = new CadRectangle(3, 4, -10, 14);
		// Relative init
		CadComponent r2 = new CadRectangle(20, 20, 5, 9, r1);
		// Default init
		CadComponent r3 = new CadRectangle();
		// Circle
		CadComponent c1 = new CadCircle(4, r2, 5, -5);
		
		// Project
		AiLiftProject myProject = new AiLiftProject();
		myProject.addComponent(r1, 0, 0);
		myProject.addComponent(r2, 0, 0);
		myProject.addComponent(r3, 0, 10);
		myProject.addComponent(c1, 0, 0);
		myProject.toFile_jscad("outProject");
	}
}