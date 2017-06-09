package Stefano_package.components._2d;

import java.awt.Point;

import Stefano_package.components.CadComponent;

public class CadLineOrtho extends CadComponent {
	/**
	 * This static number is used to avoid conflicts in Stefano_package.components name
	 * assigning an increasing number when a component is created
	 */
	private static int componentNumber;
	
	/**
	 * This is the starting point of the line
	 */
	private Point pointA;
	
	/**
	 * This is the ending point of the line
	 */
	private Point pointB;
	
	/**
	 * Default constructor
	 */
	public CadLineOrtho()
	{
		Point zero = new Point();
		init(zero, zero);
	}
	
	/**
	 * Copy constructor
	 * 
	 * @param l the line to copy
	 */
	public CadLineOrtho(CadLineOrtho l)
	{
		if(l != null)
			init(l.getStartingPoint(), l.getEndingPoint());
	}
	
	/**
	 * Parametric constructor
	 * 
	 * @param p1 the starting point
	 * @param p2 the ending point
	 */
	public CadLineOrtho(Point p1, Point p2)
	{
		init(p1, p2);
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
	public CadLineOrtho(Point p1, Point p2, CadComponent c, int rx, int ry)
	{
		init(p1, p2);
		move(p1.x + c.getX() + rx, p1.y + c.getY() + ry, p2.x + c.getX() + rx, p2.y + c.getY() + ry);
	}
	
	/**
	 * Initialization function. In this version a line can only be
	 * parallel to x and y axes so it is made a check
	 * 
	 * @param p1 the first line point
	 * @param p2 the ending line point
	 */
	private void init(Point p1, Point p2)
	{
		// As this is a orthogonal line it must be parallel to axes
		if(p1.x != p2.x)
		{
			if(p1.y != p2.y)
				p2.setLocation(p2.x, p1.y);
		}
		pointA = p1;
		pointB = p2;
		setPosition(p1.x, p1.y);
		setRes(0);
		this.componentName = "line_" + componentNumber;
		componentNumber++;
	}
	
	/**
	 * This method moves the line by a vector (dx, dy)
	 * 
	 * @param dx The x offset
	 * @param dy The y offset
	 */
	private void move(int dx1, int dy1, int dx2, int dy2)
	{
		pointA.setLocation(dx1, dy1);
		pointB.setLocation(dx2, dy2);
		setPosition(pointA.x, pointA.y);
	}
	
	/**
	 * Length computation
	 */
	public double getLength()
	{
		return Math.sqrt(Math.pow(pointB.y - pointA.y, 2) + Math.pow(pointB.x - pointA.x, 2));
	}
	
	/**
	 * Starting point getter
	 * 
	 * @return the starting point of the line
	 */
	public Point getStartingPoint()
	{
		return this.pointA;
	}
	
	/**
	 * Ending point getter
	 * 
	 * @return the ending point of the line
	 */
	public Point getEndingPoint()
	{
		return this.pointB;
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
				if(pointA.x < pointB.x)
				{
					points[0] = pointA.x + getTolerance();
					points[1] = pointB.x - getTolerance();
				}
				else
				{
					points[0] = pointB.x + getTolerance();
					points[1] = pointA.x - getTolerance();
				}
				break;
				
			case Y_AXIS:
				if(pointA.y < pointB.y)
				{
					points[0] = pointA.y + getTolerance();
					points[1] = pointB.y - getTolerance();
				}
				else
				{
					points[0] = pointB.y + getTolerance();
					points[1] = pointA.y - getTolerance();
				}
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
		double radius_x = 0.25;
		double radius_y = 0.25;
		double center_x = 0;
		double center_y = 0;
		
		if(pointA.x == pointB.x)
		{
			radius_y = getLength() / 2;
			center_x = x_base;
			center_y = y_base + radius_y;
		}
		else
		{
			radius_x = getLength() / 2;
			center_x = x_base + radius_x;
			center_y = y_base;
		}
		
		String line = "var " + componentName + " = CAG.rectangle({center: [" +
						center_x + ", " + center_y + "], radius: [" + 
						radius_x + ", " + radius_y + "]})";

		return line.concat(getExtrusionString());
	}
}