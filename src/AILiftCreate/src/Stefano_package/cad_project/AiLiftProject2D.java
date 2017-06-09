package Stefano_package.cad_project;

import java.util.ArrayList;

import Stefano_package.components.CadComponent;
import Stefano_package.components.ICadComponent.Axis;
import Stefano_package.exceptions.CollisionException;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * This class represents a complete 2D CAD project: it has a list of Stefano_package.components that is dynamically updated
 * and, for each component insertion, it checks whether the component added will collide with all the others.
 * It gives also a method to create a file that will make the CAD project visualizable and editable with other programs.
 * 
 * @author Stefano Demarchi
 * @version 1.0.0
 */
public class AiLiftProject2D {
	/**
	 * All the Stefano_package.components that compose the project
	 */
	private ArrayList<CadComponent> components;
	
	/**
	 * Default constructor
	 */
	public AiLiftProject2D()
	{
		components = new ArrayList<CadComponent>();
	}
	
	public AiLiftProject2D(AiLiftProject3D project, Axis ax)
	{
		this.components = project.getComponentsFromOrtho(ax);
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
	protected void checkCollision(CadComponent cA, CadComponent cB) throws CollisionException
	{
		// Get bounding box
		int xA[] = cA.projection(Axis.X_AXIS);
		int xB[] = cB.projection(Axis.X_AXIS);
		int yA[] = cA.projection(Axis.Y_AXIS);
		int yB[] = cB.projection(Axis.Y_AXIS);
		
		if(((xA[0] >= xB[0] && xA[0] <= xB[1] || xA[1] >= xB[0] && xA[1] <= xB[1]) &&
		    (yA[0] >= yB[0] && yA[0] <= yB[1] || yA[1] >= yB[0] && yA[1] <= yB[1])) ||
				((xB[0] >= xA[0] && xB[0] <= xA[1] || xB[1] >= xA[0] && xB[1] <= xA[1]) &&
			     (yB[0] >= yA[0] && yB[0] <= yA[1] || yB[1] >= yA[0] && yB[1] <= yA[1])))
			throw new CollisionException(cA.getName(), cB.getName());
	}
	
	/**
	 * Method to add Stefano_package.components to the actual project
	 * -> dynamic creation and update
	 * 
	 * @param comp 				The component to add
	 * @param dx 				x-shift (0 to ignore)
	 * @param dy				y-shift (0 to ignore)
	 * @param tolerance 		The tolerance in collisions
	 * @param extrusionFactor	The required extrusion (0 to ignore)
	 * 
	 * @return 		true if component is added, false otherwise
	 */
	public boolean addComponent(CadComponent comp, int dx, int dy, int tolerance, int extrusionFactor)
	{
		if(dx != 0 || dy != 0)
			comp.setPosition(comp.getX() + dx, comp.getY() + dy);
		
		comp.setTolerance(tolerance);
		comp.setExtrusion(extrusionFactor);
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
	 * @param componentName 	The component to remove
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
	 * Method to create a .jscad file that describes the whole project
	 * 
	 * @param fileName The name to give the file
	 * @param toExtrude If true the project is rendered with the defined extrusion factor, otherwise it will be 2D only
	 * 
	 * @return The project as a .jscad file
	 */
	public PrintWriter toFile_jscad(String fileName, boolean toExtrude)
	{	
		if(!toExtrude)
		{
			// Delete extrusions if any
			for(CadComponent c:components)
				c.setExtrusion(0);
		}
		else
		{
			// Look for Stefano_package.components with extrusion = 0 and set 0.5 (invisible such that is rendered)
			for(CadComponent c:components)
			{
				if(c.getExtrusionString() == ";\n")
					c.setExtrusion(0.5);
			}
		}
			
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
		String end = "	return merge.scale(0.1);\n}";
		
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
