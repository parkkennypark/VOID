/**
 * Thrown when a comment cannot be found in the database.
 *
 * @author Kenny Park
 * @version April 28, 2021
 */
public class CommentNotFoundException extends Exception {
    public CommentNotFoundException() {
        super();
    }

    public CommentNotFoundException(String message) {
        super(message);
    }
}
