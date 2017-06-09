package Stefano_package.components._3d;

import Stefano_package.components.CadComponent;
import Stefano_package.components.CadComponent3D;
import Stefano_package.components._2d.CadCircle;
import Stefano_package.components._2d.CadRectangle;
import javafx.geometry.Point3D;

/**
 * This concrete class represent a cylinder, with the base point being the center of the base.
 * It has a static number that helps generating automatically non-colliding names and obviously
 * height and radius parameters.
 * 
 * @author 	Stefano Demarchi
 * @version 1.0.0
 */
public class CadCylinder extends CadComponent3D {
	/**
	 * This static number is used to avoid conflicts in Stefano_package.components name
	 * assigning an increasing number when a component is created
	 */
	private static int componentNumber;

	/**
	 * This is the orientation of the cylinder's axis
	 */
	private Axis direction;
	
	/**
	 * This is the z-coordinate of the component
	 */
	private int z_base;
	
	/**
	 * This is the base radius
	 */
	private int radius;
	
	/**
	 * This is the height of the cylinder
	 */
	private int height;
	
	/**
	 * Default constructor
	 */
	public CadCylinder()
	{
		Point3D zero = new Point3D(0, 0, 0);
		init(zero, 0, 0, Axis.Z_AXIS);
	}
	
	/**
	 * Copy constructor
	 * 
	 * @param cy The cylinder to copy
	 */
	public CadCylinder(CadCylinder cy)
	{
		Point3D cyBase = new Point3D(cy.getX(), cy.getY(), cy.getZ());
		init(cyBase, cy.getR(), cy.getH(), cy.getDir());
	}
	
	/**
	 * Parametric constructor
	 * 
	 * @param center	The center of the base cfr
	 * @param radius	The radius of the base cfr
	 * @param height	The height of the cylinder
	 * @param dir		The direction of the cylinder
	 */
	public CadCylinder(Point3D center, int radius, int height, Axis dir)
	{
		init(center, radius, height, dir);
	}
	
	/**
	 * Relative constructor
	 * 
	 * @param dx	 The x-delta
	 * @param dy	 The y-delta
	 * @param dz	 The z-delta
	 * @param radius The radius of the base cfr
	 * @param height The height of the cylinder
	 * @param dir 	 The direction of the cylinder
	 * @param c		 The component to be relative of
	 */
	public CadCylinder(int dx, int dy, int dz, int radius, int height, Axis dir, CadComponent3D c)
	{
		Point3D rel = new Point3D(c.getX() + dx, c.getY() + dy, c.getZ() + dz);
		init(rel, radius, height, dir);
	}
	
	/**
	 * Initialization function, used in all constructors
	 * 
	 * @param center	The center of the base cfr
	 * @param radius	The radius of the base cfr
	 * @param height	The height of the cylinder
	 * @param dir		The direction of the cylinder
	 */
	private void init(Point3D center, int radius, int height, Axis dir)
	{
		setPosition((int)center.getX(), (int)center.getY(), (int)center.getZ());
		this.radius = radius >= 0 ? radius : 0;
		if(height >= 0)
			this.height = height;
		else
		{
			this.height = Math.abs(height);
			switch(dir) // Move base point accordingly
			{
				case X_AXIS:
					this.x_base -= this.height;
					break;
					
				case Y_AXIS:
					this.y_base -= this.height;
					break;
					
				case Z_AXIS:
					this.z_base -= this.height;
					break;
					
				default:
					break;
			}
		}
		setRes(70);
		this.direction = dir;
		this.componentName = "cylinder_" + componentNumber;
		componentNumber++;
	}
	
	/**
	 * z-coordinate getter
	 */
	public int getZ()
	{
		return this.z_base;
	}
	
	/**
	 * Radius getter
	 */
	public int getR()
	{
		return this.radius;
	}
	
	/**
	 * Height getter
	 */
	public int getH()
	{
		return this.height;
	}
	
	/**
	 * Direction getter
	 */
	public Axis getDir()
	{
		return this.direction;
	}
	
	@Override
	public void setPosition(int x, int y) {
		this.x_base = x;
		this.y_base = y;
	}

	@Override
	public int[] projection(Axis ax) {
		// Improvement of the 2D projection using the Z axis as well
		int points[] = new int[2];
				
		switch(ax)
		{
			case X_AXIS:
				if(this.direction == Axis.X_AXIS)
				{
					points[0] = this.x_base;
					points[1] = this.x_base + this.height;
				}
				else if(this.direction == Axis.Y_AXIS || this.direction == Axis.Z_AXIS)
				{
					points[0] = this.x_base - this.radius;
					points[1] = this.x_base + this.radius;
				}
				break;
						
			case Y_AXIS:
				if(this.direction == Axis.X_AXIS || this.direction == Axis.Z_AXIS)
				{
					points[0] = this.y_base - this.radius;
					points[1] = this.y_base + this.radius;
				}
				else if(this.direction == Axis.Y_AXIS)
				{
					points[0] = this.y_base;
					points[1] = this.y_base + this.height;
				}
				break;
						
			case Z_AXIS:
				if(this.direction == Axis.X_AXIS || this.direction == Axis.Y_AXIS)
				{
					points[0] = this.z_base - this.radius;
					points[1] = this.z_base + this.radius;
				}
				else if(this.direction == Axis.Z_AXIS)
				{
					points[0] = this.z_base;
					points[1] = this.z_base + this.height;
				}
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
				if(this.direction == Axis.X_AXIS)
					return new CadCircle(radius, y_base, z_base);
				else if(this.direction == Axis.Y_AXIS)
					return new CadRectangle(y_base, z_base - radius, height, 2 * radius);
				else
					return new CadRectangle(y_base - radius, z_base, 2 * radius, height);
				
			case Y_AXIS:
				if(this.direction == Axis.X_AXIS)
					return new CadRectangle(x_base, z_base - radius, height, 2 * radius);
				else if(this.direction == Axis.Y_AXIS)
					return new CadCircle(radius, x_base, z_base);
				else
					return new CadRectangle(x_base - radius, z_base, 2 * radius, height);
				
			case Z_AXIS:
				if(this.direction == Axis.X_AXIS)
					return new CadRectangle(x_base, y_base - radius, height, 2 * radius);
				else if(this.direction == Axis.Y_AXIS)
					return new CadRectangle(x_base - radius, y_base, 2 * radius, height);
				else
					return new CadCircle(radius, x_base, y_base);
				
			default: // Error?
				return null;
		}
	}

	@Override
	public int getX() {
		return this.x_base;
	}

	@Override
	public int getY() {
		return this.y_base;
	}

	@Override
	public String getName() {
		return this.componentName;
	}

	@Override
	public String toJsString() {
		
		String cy = "var " + componentName + " = CSG.cylinder({ start: [" +
				x_base + ", " + y_base + ", " + z_base + "], end: [";
		
		switch(this.direction)
		{
		case X_AXIS:
			cy = cy.concat((x_base + height) + ", " + y_base + ", " + z_base);
			break;
			
		case Y_AXIS:
			cy = cy.concat(x_base + ", " + (y_base + height) + ", " + z_base);
			break;
			
		case Z_AXIS:
			cy = cy.concat(x_base + ", " + y_base + ", " + (z_base + height));
			break;
			
		default:
			break;
		}
		
		return cy.concat("], radius: " + radius + ", resolution: " + getRes() + "});\n");
	}

}
