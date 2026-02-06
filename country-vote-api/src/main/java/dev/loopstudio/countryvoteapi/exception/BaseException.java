package dev.loopstudio.countryvoteapi.exception;

/**
 * The Base exception.
 * @author Victor Morales
 */
public abstract class BaseException extends RuntimeException {

    private final String title;
    private final String code;
    private final String message;

    public BaseException(String title, String code, String message, Throwable cause) {
        super(message, cause);
        this.title = title;
        this.code = code;
        this.message = message;
    }

    public String getTitle() {
        return title;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
