package duke.Exception;

/**
 * Class that handles the exception.
 */
public class DukeException extends Exception {
    private String message;

    /**
     * The constructor for DukeException.
     * @param message String.
     */
    public DukeException(String message) {
        super(message);
        this.message = message;
    }

    /**
     * Returns the message of the DukeException.
     * @return String message.
     */
    public String getMessage() {
        return this.message;
    }

}
