package Stefano_package.components._2d;

import java.awt.Point;

import Stefano_package.components.CadComponent;

/**
 * This concrete class represent a circle, with the base point being the center.
 * It has a static number that helps generating automatically non-colliding names and obviously
 * center and radius parameters.
 * 
 * @author 	Stefano Demarchi
 * @version 1.0.0
 */
public class CadCircle extends CadComponent {
	/**
	 * This static number is used to avoid conflicts in Stefano_package.components name
	 * assigning an increasing number when a component is created
	 */
	private static int componentNumber;
	
	/**
	 * This is the radius of the circle
	 */
	private int radius;
	
	/**
	 * This is the center of the circle
	 */
	private Point center;
	
	/**
	 * Default constructor (goniometric cfr)
	 */
	public CadCircle()
	{
		Point p = new Point(0, 0);
		init(1, p);
	}
	
	/**
	 * Copy constructor
	 * 
	 * @param c The circle to copy
	 */
	public CadCircle(CadCircle c)
	{
		if(c != null)
			init(c.getRadius(), c.getCenter());
	}
	
	/**
	 * Parametric constructor 1
	 * 
	 * @param radius The radius of the circle
	 * @param center The 2D center of the circle
	 */
	public CadCircle(int radius, Point center)
	{
		init(radius, center);
	}
	
	/**
	 * Parametric constructor 2
	 * 
	 * @param radius The radius of the circle
	 * @param cx	 The center's x
	 * @param cy	 The center's y
	 */
	public CadCircle(int radius, int cx, int cy)
	{
		Point p = new Point();
		p.setLocation(cx, cy);
		init(radius, p);
	}
	
	/**
	 * Relative constructor
	 * 
	 * @param radius The radius of the circle
	 * @param c 	 The component to be relative of
	 * @param rx	 The relative x-coord
	 * @param ry	 The relative y-coord
	 */
	public CadCircle(int radius, CadComponent c, int rx, int ry)
	{
		Point p = new Point();
		p.setLocation(c.getX() + rx, c.getY() + ry);
		init(radius, p);
	}
	
	/**
	 * Init function used in the constructors
	 * 
	 * @param r Radius
	 * @param c Center
	 */
	private void init(int r, Point c)
	{
		if(r < 0)
			this.radius = Math.abs(r);
		else
			this.radius = r;
		
		setPosition((int)c.getX(), (int)c.getY());
		this.center = c;
		setRes(70);
		this.componentName = "circle_" + componentNumber;
		componentNumber++;
	}
	
	/**
	 * Get method for radius
	 * 
	 * @return The radius
	 */
	public int getRadius()
	{
		return this.radius;
	}
	
	/**
	 * Get method for the center
	 * 
	 * @return The center point
	 */
	public Point getCenter()
	{
		return this.center;
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
		int points[] = new int[2];
		
		switch(ax)
		{
			case X_AXIS:
				points[0] = this.x_base - this.radius + getTolerance();
				points[1] = this.x_base + this.radius - getTolerance();
				break;
				
			case Y_AXIS:
				points[0] = this.y_base - this.radius + getTolerance();
				points[1] = this.y_base + this.radius - getTolerance();
				break;
				
			case Z_AXIS:
				break;
				
			default:
				break;
		}
		
		return points;
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
		String c = "var " + componentName + " = difference(CAG.circle({center: [" + x_base + ", " + Math.negateExact(y_base) + "], " +
						"radius: " + radius + ", resolution: " + getRes() + "}), " +
						"CAG.circle({center: [" + x_base + ", " + Math.negateExact(y_base) + "], " +
						"radius: " + (radius - 0.5) + ", resolution: " + getRes() + "}))";

		return c.concat(getExtrusionString());
	}
}