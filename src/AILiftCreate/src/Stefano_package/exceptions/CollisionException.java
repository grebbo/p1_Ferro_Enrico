package Stefano_package.exceptions;

/**
 * This class is a custom throwable exception handler in order
 * to use a try/catch block when adding a component to the project.
 * If a component collides with another this exception is thrown, signaling 
 * which component collided with the component that was added 
 *  
 * @author Stefano Demarchi
 * @version 1.0.0
 */
public class CollisionException extends Exception {
	/**
	 * Necessary field from Exception superclass
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * The message to return when exception is caught
	 */
	private String msg;
	
	/**
	 * Parametric constructor
	 * 
	 * @param name1 the existing component with which the component added collided
	 * @param name2 the component that is being added
	 */
	public CollisionException(String name1, String name2)
	{
		this.msg = "Objects " + name1 + " and " + name2 + " collided!";
	}
	
	/**
	 * This method prints the collision error
	 */
	public void printErrorMessage()
	{
		System.out.println(msg);
	}
}
