package Stefano_package.components._3d;

import Stefano_package.components.CadComponent;
import Stefano_package.components.CadComponent3D;
import Stefano_package.components._2d.*;

/**
 * This concrete class represent a parallelepiped, with the base point at the lower left vertex of the base.
 * It has a static number that helps generating automatically non-colliding names and obviously
 * three dimension parameters.
 * 
 * @author 	Stefano Demarchi
 * @version 1.0.0
 */
public class CadParallelepiped extends CadComponent3D {
	/**
	 * This static number is used to avoid conflicts in Stefano_package.components name
	 * assigning an increasing number when a component is created
	 */
	private static int componentNumber;
	
	/**
	 * This is the x axis dimension
	 */
	private int x_dimension;
	
	/**
	 * This is the y axis dimension
	 */
	private int y_dimension;
	
	/**
	 * This is the z axis dimension
	 */
	private int z_dimension;
	
	/**
	 * Default constructor
	 */
	public CadParallelepiped()
	{
		init(0, 0, 0, 0, 0, 0);
	}
	
	/**
	 * Copy constructor
	 * 
	 * @param pp The parallelepiped to copy
	 */
	public CadParallelepiped(CadParallelepiped pp)
	{
		init(pp.x_base, pp.y_base, pp.z_base, pp.getDim("x"), pp.getDim("y"), pp.getDim("z"));
	}
	
	/**
	 * Parametric constructor
	 * 
	 * @param x		The x-coordinate
	 * @param y		The y-coordinate
	 * @param z		The z-coordinate
	 * @param x_d	The x dimension
	 * @param y_d	The y dimension
	 * @param z_d	The z dimension
	 */
	public CadParallelepiped(int x, int y, int z, int x_d, int y_d, int z_d)
	{
		init(x, y, z, x_d, y_d, z_d);
	}
	
	/**
	 * Relative constructor
	 * 
	 * @param dx	The x-delta
	 * @param dy	The y-delta
	 * @param dz	The z-delta
	 * @param x_d	The x dimension
	 * @param y_d	The y dimension
	 * @param z_d	The z dimension
	 * @param c		The component to be relative of
	 */
	public CadParallelepiped(int dx, int dy, int dz, int x_d, int y_d, int z_d, CadComponent3D c)
	{
		init(c.getX() + dx, c.getY() + dy, c.getZ() + dz, x_d, y_d, z_d);
	}
	
	/**
	 * Initialization function, used in all constructors
	 * 
	 * @param x		The x-coordinate
	 * @param y		The y-coordinate
	 * @param z		The z-coordinate
	 * @param x_d	The x dimension
	 * @param y_d	The y dimension
	 * @param z_d	The z dimension
	 */
	private void init(int x, int y, int z, int x_d, int y_d, int z_d)
	{
		setPosition(x, y, z);
		if(x_d > 0)
			this.x_dimension = x_d;
		else // As for rectangles, negative dimensions shift base point
		{
			this.x_dimension = Math.abs(x_d);
			this.x_base -= x_dimension;
		}
		if(y_d > 0)
			this.y_dimension = y_d;
		else // As for rectangles, negative dimensions shift base point
		{
			this.y_dimension = Math.abs(y_d);
			this.y_base -= y_dimension;
		}
		if(z_d > 0)
			this.z_dimension = z_d;
		else // As for rectangles, negative dimensions shift base point
		{
			this.z_dimension = Math.abs(z_d);
			this.z_base -= z_dimension;
		}
		setRes(0); // No need for resolution
		this.componentName = "parallelepiped_" + componentNumber;
		componentNumber++;
	}
	
	/**
	 * Dimension getter
	 * 
	 * @param dim the dimension to get
	 * 
	 * @return the dimension in the dim dimension
	 */
	public int getDim(String dim)
	{
		dim.toLowerCase();
		switch(dim)
		{
			case "x":
				return this.x_dimension;
			case "y":
				return this.y_dimension;
			case "z":
				return this.z_dimension;
			default:
				// Signal error
				return 0;
		}
	}
	
	@Override
	public void setPosition(int x, int y) 
	{
		this.x_base = x;
		this.y_base = y;
	}

	@Override
	public int[] projection(Axis ax) 
	{
		// Improvement of the 2D projection using the Z axis as well
		int points[] = new int[2];
		
		switch(ax)
		{
			case X_AXIS:
				points[0] = this.x_base;
				points[1] = this.x_base + getDim("x");
				break;
				
			case Y_AXIS:
				points[0] = this.y_base;
				points[1] = this.y_base + getDim("y");
				break;
				
			case Z_AXIS:
				points[0] = this.z_base;
				points[1] = this.z_base + getDim("z");
				break;
				
			default:
				break;
		}
		points[0] += getTolerance();
		points[1] -= getTolerance();
		return points;
	}

	@Override
	public CadComponent getProjected2DFigure(Axis ax)
	{
		switch(ax)
		{
			case X_AXIS:
				return new CadRectangle(y_base, z_base, getDim("y"), getDim("z"));
				
			case Y_AXIS:
				return new CadRectangle(x_base, z_base, getDim("x"), getDim("z"));
				
			case Z_AXIS:
				return new CadRectangle(x_base, y_base, getDim("x"), getDim("y"));
				
			default: // Error?
				return null;
		}
	}

	@Override
	public int getX() 
	{
		return this.x_base;
	}

	@Override
	public int getY() 
	{
		return this.y_base;
	}

	@Override
	public String getName() 
	{
		return this.componentName;
	}

	@Override
	public String toJsString() 
	{
		return "var " + componentName + " = CSG.cube({corner1: [" + x_base + ", " + y_base + ", " + z_base + 
					"], corner2: [" + (x_base + getDim("x")) + ", " + (y_base + getDim("y")) + ", " + (z_base + getDim("z")) +
					"]});";
	}

}
