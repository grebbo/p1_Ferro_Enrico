package exceptions;

/**
 * Custom exception map. Used when two components overlap with each other.
 *
 * @Author Enrico Ferro
 */
public class OverlappingException extends Exception {
    /**
     * Necessary field from Exception superclass
     */
    private static final long serialVersionUID = 1L;

    /**
     * The message to return when exception is caught
     */
    private String message;

    /**
     * Constructor
     */
    public OverlappingException()
    {
        this.message = "Components overlapping. Invalid project!";
    }

    /**
     * This method prints the collision error
     */
    public void printErrorMessage()
    {
        System.out.println(message);
    }
}
