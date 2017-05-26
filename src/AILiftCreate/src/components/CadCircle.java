package components;

import java.awt.geom.Point2D;

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
	 * This static number is used to avoid conflicts in components name
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
	private Point2D center;
	
	/**
	 * Default constructor (goniometric cfr)
	 */
	public CadCircle()
	{
		Point2D p = new Point2D.Double();
		p.setLocation(0, 0);
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
	public CadCircle(int radius, Point2D center)
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
		Point2D p = new Point2D.Double();
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
		Point2D p = new Point2D.Double();
		p.setLocation(c.getX() + rx, c.getY() + ry);
		init(radius, p);
	}
	
	/**
	 * Init function used in the constructors
	 * 
	 * @param r Radius
	 * @param c Center
	 */
	private void init(int r, Point2D c)
	{
		if(r >= 0)
			this.radius = r;
		else
			this.radius = 1; // Add error message?

		setPosition((int)c.getX(), (int)c.getY());
		this.center = c;
		this.resolution = 70;
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
	public Point2D getCenter()
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
	public int[] linearProjection(Axis ax)
	{
		int points[] = new int[2];
		
		switch(ax)
		{
			case X_AXIS:
				points[0] = this.x_base - this.radius;
				points[1] = this.x_base + this.radius;
				break;
				
			case Y_AXIS:
				points[0] = this.y_base - this.radius;
				points[1] = this.y_base + this.radius;
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
		return "var " + componentName + " = CAG.circle({center: [" +
				x_base + ", " + y_base + "], radius: " +
				this.getRadius() + ", resolution: "+ this.getRes() + "});";
	}
}