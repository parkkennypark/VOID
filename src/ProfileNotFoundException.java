/**
 * Thrown if a profile cannot be found in the database.
 *
 * @author Kenny Park
 * @version April 29, 2021
 */
public class ProfileNotFoundException extends Exception {
    public ProfileNotFoundException() {
        super();
    }

    public ProfileNotFoundException(String message) {
        super(message);
    }
}
