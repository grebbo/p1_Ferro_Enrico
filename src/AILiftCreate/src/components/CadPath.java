package components;

import java.awt.geom.Point2D;
import java.util.ArrayList;

/**
 * 
 * @author 	Stefano Demarchi
 * @version 1.0.0
 */
public class CadPath extends CadComponent {
	/**
	 * This static number is used to avoid conflicts in components name
	 * assigning an increasing number when a component is created
	 */
	private static int componentNumber;
	
	/**
	 * This is the list of nodes composing the path
	 */
	private ArrayList<Point2D> nodes;
	
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
			componentNumber++;
		}
	}
	
	/**
	 * Parametric constructor 
	 * 
	 * @param n 	Path's nodes
	 * @param t 	Path type
	 */
	public CadPath(ArrayList<Point2D> n, String t)
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
	public CadPath(ArrayList<Point2D> n, String t, CadComponent c, int rx, int ry)
	{
		init(n, t);
		int x_rel = c.getX() + rx;
		int y_rel = c.getY() + ry;
		
		for(Point2D nod:nodes)
			nod.setLocation(x_rel, y_rel);
		
		setPosition((int)nodes.get(0).getX(), (int)nodes.get(0).getY());
	}
	
	/**
	 * Init function used in parametric constructors
	 *
	 * @param n 	Path's nodes
	 * @param t 	Path type
	 */
	private void init(ArrayList<Point2D> n, String t)
	{
		this.nodes = new ArrayList<Point2D>(n);
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
		for(Point2D n:nodes)
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
		Point2D n = nodes.get(pointIdx);
		nodes.get(pointIdx).setLocation(n.getX() + x_shift, n.getY() + y_shift);
	}
	
	/**
	 * OpenJSCad code that creates a line
	 * 
	 * @return a jscad String describing a LINE
	 */
	private String jscadLine()
	{
		String jscadpath = "var csg" + componentName + " = new CSG.Path2D([";
		
		for(int i = 0; i < nodes.size(); i++)
		{
			Point2D n = nodes.get(i);
			jscadpath = jscadpath.concat("[" + (int)n.getX() + ", " + (int)n.getY() + "]");
			
			if(i != nodes.size() - 1)
				jscadpath = jscadpath.concat(", ");
		}
		
		jscadpath = jscadpath.concat("], false);\n");
		// Extrusion makes the path non exportable in DXF
		String extrude = "var " + componentName + " = csg" + componentName + 
						".rectangularExtrude(0.1, 0.1, " + this.resolution + ", false);";
		
		return jscadpath.concat(extrude);
	}
	
	/**
	 * OpenJSCad code that creates an arc
	 * 
	 * @return a jscad String describing an ARC
	 */
	private String jscadArc()
	{
		// Need center, radius and startAngle/endAngle
		return "";
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
	public Point2D getNode(int pointIdx)
	{
		return nodes.get(pointIdx);
	}
	
	@Override
	public void setPosition(int x, int y)
	{
		this.setPosition((int)nodes.get(0).getX(), (int)nodes.get(0).getY());
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