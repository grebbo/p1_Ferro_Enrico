package Stefano_package.components;

/**
 * This concrete class represent a rectangle, with the base point at the upper left.
 * It has a static number that helps generating automatically non-colliding names and obviously
 * width and height parameters.
 * 
 * @author 	Stefano Demarchi
 * @version 1.0.0
 */
public class CadRectangle extends CadComponent {
	/**
	 * This static number is used to avoid conflicts in Stefano_package.components name
	 * assigning an increasing number when a component is created
	 */
	private static int componentNumber;
	
	/**
	 * This is the width of the rectangle
	 */
	private int r_width;
	
	/**
	 * This is the height of the rectangle
	 */
	private int r_height;
	
	/**
	 * Default constructor (1x1 square)
	 */
	public CadRectangle()
	{
		init(0, 0, 2, 2);
	}
	
	/**
	 * Copy constructor
	 * 
	 * @param r 	The rectangle to copy
	 */
	public CadRectangle(CadRectangle r)
	{
		if(r != null)
			init(r.getX(), r.getY(), r.getW(), r.getH());
	}
	
	/**
	 * Parametric constructor 
	 * 
	 * @param x		X-coord
	 * @param y		Y-coord
	 * @param w 	Width
	 * @param h 	Height
	 */
	public CadRectangle(int x, int y, int w, int h)
	{
		init(x, y, w, h);
	}
	
	/**
	 * Relative constructor
	 *
	 * @param rx	Relative X-coord
	 * @param ry	Relative Y-coord
	 * @param w 	Width
	 * @param h 	Height
	 * @param c 	Component to be relative of
	 */
	public CadRectangle(int rx, int ry, int w, int h, CadComponent c)
	{
		int x_rel = c.getX() + rx;
		int y_rel = c.getY() + ry;
		
		init(x_rel, y_rel, w, h);
	}
	
	/**
	 * Init function used in all constructors
	 * 
	 * @param x		X-coord
	 * @param y		Y-coord
	 * @param w 	Width
	 * @param h 	Height
	 */
	private void init(int x, int y, int w, int h)
	{		
		if(w > 0)
			this.r_width = w;
		else
			this.r_width = 0 - w;
		if(h > 0)
			this.r_height = h;
		else
			this.r_height = 0 - h;
		
		setPosition(x, y);
		this.resolution = 70;
		this.componentName = "rectangle_" + componentNumber;
		componentNumber++;
	}
	
	/**
	 * Get method for width
	 * 
	 * @return 	Rectangle's width
	 */
	public int getW()
	{
		return this.r_width;
	}
	
	/**
	 * Get method for height
	 * 
	 * @return	Rectangle's height
	 */
	public int getH()
	{
		return this.r_height;
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
				points[0] = this.x_base;
				points[1] = this.x_base + this.r_width;
				break;
				
			case Y_AXIS:
				points[0] = this.y_base;
				points[1] = this.y_base + this.r_height;
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
		String r = "var csg" + componentName + " = new CSG.Path2D([[" +
				x_base + ", " + y_base + "], [" + (x_base + r_width) + ", " + y_base + "], [" +
				(x_base + r_width) + ", " + (y_base + r_height) + "], [" + x_base + ", " + (y_base + r_height) + "]], true);\n";
		String extrude = "	var " + componentName + " = csg" + componentName + 
				".rectangularExtrude(0.5, 0.5, " + this.getRes() + ", false);";
		
		return r.concat(extrude);
	}
}