package Stefano_package.components;

import java.awt.Point;
import java.util.ArrayList;

/**
 * This class represents a 2D path, as a sequence of points. This class is not working by now
 * as JSCAD does not render 2D paths but needs to extrude them in 3D. While trying to reach a solution, 
 * the class will be however used as it is, changing only the jscad render string when a solution
 * will be found.
 * 
 * Note that the "OTHER" path is not considered at all, for now, as it will be a future feature.
 * 
 * @author 	Stefano Demarchi
 * @version 1.0.0
 */
public class CadPath extends CadComponent {
	/**
	 * This static number is used to avoid conflicts in Stefano_package.components name
	 * assigning an increasing number when a component is created
	 */
	private static int componentNumber;
	
	/**
	 * This is the list of nodes composing the path
	 */
	private ArrayList<Point> nodes;
	
	/**
	 * This is the actual type
	 */
	private PathType type;
	
	/**
	 * Default constructor
	 */
	public CadPath()
	{
		this.type = PathType.LINE;
		this.x_base = 0;
		this.y_base = 0;
		this.componentName = setName();
		this.resolution = 70;
		componentNumber++;
	}
	
	/**
	 * Copy constructor
	 * 
	 * @param p 	The path to copy
	 */
	public CadPath(CadPath p)
	{
		if(p != null)
		{
			this.type = p.getType();
			this.x_base = p.getX();
			this.y_base = p.getY();
			this.componentName = setName();
			this.resolution = 70;
			componentNumber++;
		}
	}
	
	/**
	 * Parametric constructor 
	 * 
	 * @param n 	Path's nodes
	 * @param t 	Path type
	 */
	public CadPath(ArrayList<Point> n, String t)
	{
		if(!n.isEmpty())
		{
			init(n, t);
		}
	}
	
	/**
	 * Relative constructor
	 *
	 * @param n		Path's nodes
	 * @param t		Path type
	 * @param c 	Component to be relative of
	 * @param rx	Relative X-coord
	 * @param ry	Relative Y-coord
	 */
	public CadPath(ArrayList<Point> n, String t, CadComponent c, int rx, int ry)
	{
		init(n, t);
		int x_rel = c.getX() + rx;
		int y_rel = c.getY() + ry;
		
		for(Point nod:nodes)
			nod.setLocation(x_rel, y_rel);
		
		setPosition((int)nodes.get(0).getX(), (int)nodes.get(0).getY());
	}
	
	/**
	 * Init function used in parametric constructors
	 *
	 * @param n 	Path's nodes
	 * @param t 	Path type
	 */
	private void init(ArrayList<Point> n, String t)
	{
		this.nodes = new ArrayList<Point>(n);
		setPosition((int)nodes.get(0).getX(), (int)nodes.get(0).getY());
			
		switch(t)
		{
		case "LINE":
			this.type = PathType.LINE;
			break;
		case "ARC":
			this.type = PathType.ARC;
			break;
		case "OTHER":
			this.type = PathType.OTHER;
			break;
		default:
			// Signal error & exit
			break;
		}
		
		this.componentName = setName();
		this.resolution = 70;
		componentNumber++;
	}
	
	/**
	 * This method will move the whole path by a vector
	 * (x_shift; y_shift)
	 * 
	 * @param x_shift	x shift coordinate
	 * @param y_shift	y shift coordinate
	 */
	public void movePath(int x_shift, int y_shift)
	{
		for(Point n:nodes)
			n.setLocation(n.getX() + x_shift, n.getY() + y_shift);
		
		setPosition((int)nodes.get(0).getX(), (int)nodes.get(0).getY());
	}
	
	/**
	 * This method will move a single point, the indexed one,
	 * by a vector (x_shift; y_shift)
	 * 
	 * @param x_shift	x shift coordinate
	 * @param y_shift	y shift coordinate
	 * @param pointIdx	index of point to move
	 */
	public void movePoint(int x_shift, int y_shift, int pointIdx)
	{
		Point n = nodes.get(pointIdx);
		nodes.get(pointIdx).setLocation(n.getX() + x_shift, n.getY() + y_shift);
	}
	
	/**
	 * OpenJSCad code that creates a line
	 * 
	 * @return a jscad String describing a LINE
	 */
	private String jscadLine()
	{
		String line = "var csg" + componentName + " = new CSG.Path2D([";
		
		for(int i = 0; i < nodes.size(); i++)
		{
			Point n = nodes.get(i);
			line = line.concat("[" + n.getX() + ", " + n.getY() + "]");
			
			if(i != nodes.size() - 1)
				line = line.concat(", ");
		}
		
		line = line.concat("], false);\n");

		String extrude = "	var " + componentName + " = csg" + componentName + 
						".rectangularExtrude(0.5, 0.5, " + this.resolution + ", false);";
		
		return line.concat(extrude);
	}
	
	/**
	 * OpenJSCad code that creates an arc
	 * 
	 * @return a jscad String describing an ARC
	 */
	private String jscadArc()
	{
		// Three nodes: center, start & end. Further nodes will not be considered
		if(nodes.size() < 2)
			return "";
		else
		{
			int ox = (int)nodes.get(0).getX();
			int oy = (int)nodes.get(0).getY();
			int ax = (int)nodes.get(1).getX();
			int ay = (int)nodes.get(1).getY();
			int by = (int)nodes.get(2).getY();
			double radius = Math.hypot(ox - ax, oy - ay);
			// Angles are computed starting from points A & B
			int startAng = (int)Math.toDegrees(Math.asin((oy+ay)/radius));
			int endAng = (int)Math.toDegrees(Math.asin((oy+by)/radius));
			
			String arc = "var csg" + componentName + " = CSG.Path2D.arc({ center: [" + ox + ", " + oy + 
					"], radius: " + radius + ", startangle: " + startAng + ", endangle: " + endAng +
					", resolution: " + this.resolution + "});\n";
			String extrude = "	var " + componentName + " = csg" + componentName + 
					".rectangularExtrude(0.5, 0.5, " + this.getRes() + ", false);";
			
			return arc.concat(extrude);
		}
	}
	
	/**
	 * This method dynamically assigns a name to the component
	 * based on the value of componentNumber
	 * 
	 * @return 	the name of the component
	 */
	private String setName()
	{
		switch(this.type)
		{
		case LINE:
			return "path_line_" + componentNumber;
		case ARC:
			return "path_arc_" + componentNumber;
		case OTHER:
			return "path_other_" + componentNumber;
		default:
			return "";	
		}
	}
	
	/**
	 * Get method for type
	 * 
	 * @return	The type of the Path
	 */
	public PathType getType()
	{
		return this.type;
	}
	
	/**
	 * Get method for a single node
	 * 
	 * @param pointIdx	Index of the node
	 * 
	 * @return 			The point at pointIdx
	 */
	public Point getNode(int pointIdx)
	{
		return nodes.get(pointIdx);
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
		int points[] = { 0, 0 };
		
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
		switch(this.type)
		{
		case LINE:
			return jscadLine();
		case ARC:
			return jscadArc();
		case OTHER:
			return "";
		default:
			return "";
		}
	}
}