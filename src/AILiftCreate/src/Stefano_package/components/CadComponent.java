package Stefano_package.components;

/**
 * This class is the two dimensional main realization of the interface ICadComponent, still is abstract
 * as the concrete elements will derive from this one the main fields.
 * 
 * @author 	Stefano Demarchi
 * @version	1.0.0
 */
public abstract class CadComponent implements ICadComponent {	
	/**
	 * This is the name of the component
	 */
	protected String componentName;
	
	/**
	 * This is the x-coordinate of the component
	 */
	protected int x_base;
	
	/**
	 * This is the y-coordinate of the component
	 */
	protected int y_base;	
	
	/**
	 * This is the tolerance for collisions
	 * (default 0)
	 */
	private int collision_tolerance = 0;
	
	/**
	 * This is the additional string to extrude a component
	 * (";" if no extrusion is required)
	 */
	private String extrusion = ";\n";
	
	/**
	 * Resolution for curves
	 */
	private int resolution;
	
	/**
	 * Tolerance setter
	 */
	public void setTolerance(int newTol)
	{
		// conditions...?
		this.collision_tolerance = newTol;
	}
	
	/**
	 * Tolerance getter
	 */
	public int getTolerance()
	{
		return this.collision_tolerance;
	}
	
	/**
	 * Extrusion setter
	 */
	public void setExtrusion(double d)
	{
		if(d != 0)
			this.extrusion = ".extrude({offset: [0, 0, " + d + "]});\n";
		else
			this.extrusion = ";\n";
	}
	
	/**
	 * Extrusion getter
	 */
	public String getExtrusionString()
	{
		return this.extrusion;
	}
	
	/**
	 * Resolution setter
	 */
	public void setRes(int newRes)
	{
		if(newRes >= 0)
			this.resolution = newRes;
	}
	
	/**
	 * Resolution getter
	 */
	public int getRes()
	{
		return this.resolution;
	}
}