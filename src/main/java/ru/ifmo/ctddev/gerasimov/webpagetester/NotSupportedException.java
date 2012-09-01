package ru.ifmo.ctddev.gerasimov.webpagetester;

/**
 * Created with IntelliJ IDEA.
 * User: karlicos
 * Date: 9/1/12
 * Time: 4:17 AM
 * To change this template use File | Settings | File Templates.
 */
public class NotSupportedException extends RuntimeException {
    public NotSupportedException() {
    }

    public NotSupportedException(Throwable cause) {
        super(cause);
    }

    public NotSupportedException(String m, String description) {
        super("You didn't provide " + m + " for " + description);
    }

    public NotSupportedException(String message, Throwable cause) {

        super(message, cause);
    }

    public NotSupportedException(String message) {

        super(message);
    }
}
