package components;

/**
 * This class is the main realisation of the interface ICadComponent, still is abstract
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
	 * Resolution for curves
	 */
	protected int resolution;
	
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