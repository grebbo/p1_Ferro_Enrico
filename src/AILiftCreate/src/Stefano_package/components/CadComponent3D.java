package Stefano_package.components;

/**
 * This class is the abstract base class for 3D Stefano_package.components. It extends the CadComponent class
 * adding the z coordinate, overloading the setPosition method and providing a prototype
 * to project the 3D figure onto a 2D plane
 * 
 * @author Stefano Demarchi
 * @version 1.0.0
 */
public abstract class CadComponent3D extends CadComponent {
	/**
	 * This is the z-coordinate of the component
	 */
	protected int z_base;
	
	/**
	 * z-coordinate getter
	 */
	public int getZ()
	{
		return this.z_base;
	}
	
	/**
	 * This method sets a new position for the component
	 * (3D overload with z coordinate)
	 * 
	 * @param x	New component's x coord
	 * @param y	New component's y coord
	 * @param z	New component's z coord
	 */
	public void setPosition(int x, int y, int z)
	{
		this.setPosition(x, y);
		this.z_base = z;
	}
	
	/**
	 * Prototype to be used in order to project on a 2D plane
	 * the 3D component
	 * 
	 * @param ax the axis to project from
	 * 
	 * @return the 2D component projected
	 */
	public abstract CadComponent getProjected2DFigure(Axis ax);
}
