package ru.ifmo.ctddev.gerasimov.webpagetester;


/**
 * Created with IntelliJ IDEA.
 * User: karlicos
 * Date: 9/1/12
 * Time: 4:10 AM
 */
public class InconsistencyException extends RuntimeException {
    public InconsistencyException() {
    }

    public InconsistencyException(String m1, String m2, String description) { //TODO use reflection and Methods?
        super(m1 + " is not consistent with " + m2 + " for " + description);
    }

    public InconsistencyException(String message) {
        super(message);
    }

    public InconsistencyException(String message, Throwable cause) {
        super(message, cause);
    }

    public InconsistencyException(Throwable cause) {
        super(cause);
    }
}
