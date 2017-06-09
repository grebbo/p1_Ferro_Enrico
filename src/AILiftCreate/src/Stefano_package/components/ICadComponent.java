package Stefano_package.components;

/**
 * This interface gives the main rules to create Stefano_package.components: get & set necessary methods and
 * the core of the application: a JS writer that will draw CAD elements.
 * 
 * @author 	Stefano Demarchi
 * @version 1.0.0
 */
public interface ICadComponent {	
	/**
	 * Enumerator for axes
	 */
	public enum Axis
	{
		X_AXIS,
		Y_AXIS,
		Z_AXIS
	};
	
	/**
	 * This method sets a new position for the component
	 * 
	 * @param x 	New component's x-coord
	 * @param y 	New component's y-coord
	 */
	public void setPosition(int x, int y);
	
	/**
	 * This method will be used in order to detect collisions:
	 * it projects the component on the selected axis (2D component)
	 * or on the plane where the axis is the normal (3D component)
	 * 
	 * @param ax the axis to project on
	 * 
	 * @return the x1 and x2 of the segment in a 2-element array
	 */
	public int[] projection(Axis ax);
	
	/**
	 * Get method for x
	 * 
	 * @return base point's x
	 */
	public int getX();
	
	/**
	 * Get method for y
	 * 
	 * @return base point's y
	 */
	public int getY();
	
	/**
	 * Get method for the name
	 * 
	 * @return component's name
	 */
	public String getName();
	
	/** 
	 * This method is the core of the application:
	 * it will generate a JS String 
	 * that will describe the component and 
	 * draw it on the framework.
	 * 
	 * @return The JS string describing the component
	 */
	public String toJsString();
}