package exceptions;

public class CollisionException extends Exception {
	private static final long serialVersionUID = 1L;
	
	private String msg;
	
	public CollisionException(String m)
	{
		this.msg = m;
	}
	
	public void printErrorMessage()
	{
		System.out.println(msg);
	}
}
