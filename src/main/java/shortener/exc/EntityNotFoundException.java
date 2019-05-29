package shortener.exc;

/**
 * Exceptions which will be thrown when entity was not found.
 *
 * @author yaroslav.shymkiv
 */
public class EntityNotFoundException extends Exception {

    public EntityNotFoundException(String str) {
        super(str);
    }

    public EntityNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public EntityNotFoundException(Throwable cause) {
        super(cause);
    }
}
