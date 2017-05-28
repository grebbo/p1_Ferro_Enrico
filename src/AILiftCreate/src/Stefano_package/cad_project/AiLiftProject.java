package Stefano_package.cad_project;

import java.util.ArrayList;

import Stefano_package.components.CadComponent;
import Stefano_package.components.ICadComponent.Axis;
import Stefano_package.exceptions.CollisionException;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * This class represents a complete CAD project: it has a list of Stefano_package.components that is dynamically updated
 * and, for each component insertion, it checks whether the component added will collide with all the others.
 * It gives also a method to create a file that will make the CAD project visualizable and editable with other programs.
 * 
 * @author Stefano Demarchi
 * @version 1.0.0
 */
public class AiLiftProject {
	/**
	 * All the Stefano_package.components that compose the project
	 */
	private ArrayList<CadComponent> components;
	
	/**
	 * Tolerance in collisions
	 */
	private int collisionTolerance;
	
	/**
	 * Default constructor
	 */
	public AiLiftProject()
	{
		components = new ArrayList<CadComponent>();
		collisionTolerance = 5;
	}
	
	/**
	 * This method is used to check if two Stefano_package.components collide
	 * If a collision occurs, a CollisionException is thrown
	 * 
	 * The algorithm used to check collisions is a raw linear projection on
	 * the two axes: it works as a charm on rectangles but must be improved for circles and paths
	 * 
	 * @param c1	the first component
	 * @param c2	the second component
	 * 
	 * @throws CollisionException
	 */
	private void checkCollision(CadComponent cA, CadComponent cB) throws CollisionException
	{
		// Get bounding box expanded by tolerance
		int xA[] = cA.linearProjection(Axis.X_AXIS);
		xA[0] += collisionTolerance;
		xA[1] -= collisionTolerance;
		int xB[] = cB.linearProjection(Axis.X_AXIS);
		xB[0] += collisionTolerance;
		xB[1] -= collisionTolerance;
		int yA[] = cA.linearProjection(Axis.Y_AXIS);
		yA[0] += collisionTolerance;
		yA[1] -= collisionTolerance;
		int yB[] = cB.linearProjection(Axis.Y_AXIS);
		yB[0] += collisionTolerance;
		yB[1] -= collisionTolerance;
		
		if((xA[0] > xB[0] && xA[0] < xB[1] || xA[1] > xB[0] && xA[1] < xB[1]) &&
		   (yA[0] > yB[0] && yA[0] < yB[1] || yA[1] > yB[0] && yA[1] < yB[1]))
			throw new CollisionException(cA.getName(), cB.getName());
	}
	
	/**
	 * Method to add Stefano_package.components to the actual project
	 * -> dynamic creation and update
	 * 
	 * @param comp 	The component to add
	 * @param dx 	x-shift (0 to ignore)
	 * @param dy	y-shift (0 to ignore)
	 * 
	 * @return 		true if component is added, false otherwise
	 */
	public boolean addComponent(CadComponent comp, int dx, int dy)
	{
		if(dx != 0 || dy != 0)
			comp.setPosition(comp.getX() + dx, comp.getY() + dy);
		
		boolean r = false;
		
		try
		{
			for(CadComponent c:components)
				checkCollision(comp, c);
			
			r = components.add(comp);
		}
		catch(CollisionException e)
		{
			e.printErrorMessage();
			return false;
		}
		
		return r;
	}
	
	/**
	 * Method to remove Stefano_package.components from the actual project
	 * -> dynamic deletion and update
	 * 
	 * @param comp 	The component to remove
	 * 
	 * @return 		true if component is removed, false otherwise
	 */
	public boolean rmComponent(String componentName)
	{
		if(components.isEmpty())
			return false;
		else
		{
			for(CadComponent c:components)
			{
				if(c.getName() == componentName)
				{
					components.remove(c);
					return true;
				}
			}
			
			return false;
		}
	}
	
	/**
	 * Get method for Stefano_package.components number
	 * 
	 * @return the total Stefano_package.components used in the project
	 */
	public int getTotalComponents()
	{
		return components.size();
	}
	
	/**
	 * Tolerance setter
	 */
	public void setTolerance(int tol)
	{
		if(tol > 0 /* && tol < max_tol */)
			collisionTolerance = tol;
	}
	
	/**
	 * Method to create a .jscad file that describes the whole project
	 * 
	 * @param fileName The name to give the file
	 * 
	 * @return The project as a .jscad file
	 */
	public PrintWriter toFile_jscad(String fileName)
	{
		// Header string: comments & main
		String head = "// AI Lift-Create :: Auto-generated code\n" +
						"// Total Stefano_package.components in project: " +
						this.getTotalComponents() + "\n\n" +
						"function main()\n{";
			
		String content = "";
			
		// Loop Stefano_package.components
		for(CadComponent c:components)
			content = content.concat("	" + c.toJsString() + "\n");
			
		// Union
		content = content.concat("\n	var merge = union(");
		for(CadComponent c:components)
			content = content.concat(c.getName() + ", ");
		// This is used to add another "fake" component to the union function, as it would terminate with ,);
		content = content.concat(components.get(components.size() - 1).getName() + ");\n");
			
		// Close main
		String end = "	return merge.scale(0.5);\n}";
		
		try 
		{
			// Create file Project.jscad
			PrintWriter writer = new PrintWriter(fileName + ".jscad", "UTF-8");
			writer.println(head);
			writer.println(content);
			writer.println(end);
			writer.close();
			
			return writer;
		}
		catch(IOException e)
		{
			System.out.println("Error creating or writing file!");
			e.printStackTrace();
			return null;
		}
	}
}