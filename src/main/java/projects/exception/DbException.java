package projects.exception;

/**
 * Custom exception class for handling database-related exceptions.
 */
public class DbException extends RuntimeException {

    /**
   * 
   */
  private static final long serialVersionUID = 1L;

    /**
     * Constructs a new database exception with the specified detail message.
     *
     * @param message The detail message (which is saved for later retrieval by the getMessage() method).
     */
    public DbException(String message) {
        super(message);
    }

    /**
     * Constructs a new database exception with the specified cause.
     *
     * @param cause The cause (which is saved for later retrieval by the getCause() method).
     */
    public DbException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructs a new database exception with the specified detail message and cause.
     *
     * @param message The detail message (which is saved for later retrieval by the getMessage() method).
     * @param cause   The cause (which is saved for later retrieval by the getCause() method).
     */
    public DbException(String message, Throwable cause) {
        super(message, cause);
    }
}
