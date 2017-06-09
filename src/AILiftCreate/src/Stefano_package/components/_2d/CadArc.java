package Stefano_package.components._2d;

import java.awt.Point;
import java.awt.geom.Point2D;

import Stefano_package.components.CadComponent;

public class CadArc extends CadComponent {
	/**
	 * This static number is used to avoid conflicts in Stefano_package.components name
	 * assigning an increasing number when a component is created
	 */
	private static int componentNumber;
	
	/**
	 * This is the center of the arc
	 */
	private Point center;
	
	/**
	 * This is the starting point of the arc
	 */
	private Point startPoint;
	
	/**
	 * This is the amplitude (degrees)
	 */
	private int angularAmplitude;
	
	/**
	 * Default constructor
	 */
	public CadArc()
	{
		Point zero = new Point();
		init(zero, zero, 0);
	}
	
	/**
	 * Copy constructor
	 * 
	 * @param a the arc to copy
	 */
	public CadArc(CadArc a)
	{
		if(a != null)
			init(a.getCenter(), a.getStartingPoint(), a.getAmpl());
	}
	
	/**
	 * Parametric constructor
	 * 
	 * @param c the center to give the arc
	 * @param s the starting point
	 * @param a the amplitude
	 */
	public CadArc(Point c, Point s, int a)
	{
		init(c, s, a);
	}
	
	/**
	 * Relative constructor
	 * 
	 * @param p1 the starting point of the base line
	 * @param p2 the ending point of the base line
	 * @param c the component to be relative of
	 * @param rx the x delta
	 * @param ry the y delta
	 */
	public CadArc(Point c, Point s, int a, CadComponent comp, int rx, int ry)
	{
		init(c, s, a);
		move(x_base + comp.getX() + rx, y_base + comp.getY() + ry, s.x + comp.getX() + rx, s.y + comp.getY() + ry);
	}
	
	/**
	 * Initialization function
	 * 
	 * @param c the center to give the arc
	 * @param s the starting point
	 * @param a the amplitude
	 */
	private void init(Point c, Point s, int a)
	{
		if(a > 360) // Max amplitude
			angularAmplitude = 360;
		else
			angularAmplitude = a;
		
		center = c;
		startPoint = s;
		setPosition(s.x, s.y);
		setRes(70);
		this.componentName = "arc_" + componentNumber;
		componentNumber++;
	}
	
	/**
	 * This method moves the arc by a vector (dx, dy)
	 * 
	 * @param dx The x offset
	 * @param dy The y offset
	 */
	private void move(int dx1, int dy1, int dx2, int dy2)
	{
		center.setLocation(this.getCenter().getX() + dx1, this.getCenter().getY() + dy1);
		startPoint.setLocation(x_base + dx2, y_base + dy2);
		setPosition(startPoint.x, startPoint.y);
	}
	
	/**
	 * Center point getter
	 * 
	 * @return the center point of the arc
	 */
	public Point getCenter()
	{
		return this.center;
	}
	
	/**
	 * Starting point getter
	 * 
	 * @return the starting point of the arc
	 */
	public Point getStartingPoint()
	{
		return this.startPoint;
	}
	
	/**
	 * Start angle computation
	 * 
	 * @return the starting angle in degrees
	 */
	public int getStartAngle()
	{
		int height = Math.abs(y_base - center.y);
		int fromZero = 0;
		int x_diff = startPoint.x - center.x;
		int y_diff = startPoint.y - center.y;
		
		// If height is 0, division is not possible
		if(height == 0)
		{
			if(x_diff > 0)
				return 0;
			else
				return 180;
		}
		else
			fromZero = (int)Math.toDegrees(Math.asin(height/getRadius()));
		
		if(x_diff >= 0)
		{
			if(y_diff >= 0) // First quadrant
				return fromZero;
			else 			// Fourth quadrant
				return 360 - fromZero;
		}
		else
		{
			if(y_diff >= 0) // Second quadrant
				return 180 - fromZero;
			else 			// Third quadrant
				return 180 + fromZero;
		}
	}
	
	/**
	 * Ending point computation
	 * 
	 * @return the ending point in double precision
	 */
	public Point2D getEndingPoint()
	{	
		// Find angle's sine and cosine then translate them by the base point
		double x_point = getRadius() * Math.cos((getStartAngle() + angularAmplitude) % 360); // Modular operation
		double y_point = getRadius() * Math.sin((getStartAngle() + angularAmplitude) % 360);
		
		return new Point2D.Double(x_point + x_base, y_point + y_base);
	}
	
	/**
	 * Amplitude getter
	 * 
	 * @return the angular amplitude of the arc
	 */
	public int getAmpl()
	{
		return this.angularAmplitude;
	}
	
	/**
	 * Radius computation
	 * 
	 * @return the radius in double precision
	 */
	public double getRadius()
	{
		return Math.sqrt(Math.pow(getCenter().y - y_base, 2) + Math.pow(getCenter().x - x_base, 2));
	}
	
	/**
	 * As the bounding box computation for an arc is
	 * a complex algorithm, there is a method only for this.
	 * 
	 * @return the 4 vertices of the bounding box in a Point array
	 * 			starting from the bottom-left one in counter clockwise order
	 */
	public Point[] getArcBoundingBox()
	{
		// TODO
		Point a = new Point();
		Point[] r = {a, a, a, a};
		return r;
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
		Point bound[] = getArcBoundingBox();
		int points[] = new int[2];
		
		switch(ax)
		{
			case X_AXIS:
				points[0] = bound[0].x + getTolerance();
				points[1] = bound[1].x - getTolerance();
				break;
				
			case Y_AXIS:
				points[0] = bound[0].y + getTolerance();
				points[1] = bound[3].y - getTolerance();
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
		String arc = "var csg" + componentName + " = new CSG.Path2D.arc({ center: [" +
						center.x + ", " + center.y + "], radius: " + getRadius() + ", startangle: " + 
						getStartAngle() + ", endangle: " + (angularAmplitude + getStartAngle()) + ", resolution: " + getRes() + "})";
		
		return arc.concat(getExtrusionString());
	}
}