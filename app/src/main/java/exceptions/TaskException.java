package exceptions;

public class TaskException extends AppException {
    public TaskException() {
        super();
    }

    public TaskException(String message) {
        super(message);
    }

    public TaskException(Throwable cause) {
        super(cause);
    }

    public TaskException(String message, Throwable cause) {
        super(message, cause);
    }
}
