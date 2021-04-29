/**
 * Thrown when a post cannot be found in the database.
 *
 * @author Kenny Park
 * @version April 24, 2021
 */
public class PostNotFoundException extends Exception{
    public PostNotFoundException() {
        super();
    }

    public PostNotFoundException(String message) {
        super(message);
    }
}
