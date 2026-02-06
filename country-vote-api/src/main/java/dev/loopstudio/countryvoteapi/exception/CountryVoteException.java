package dev.loopstudio.countryvoteapi.exception;

/**
 * The Country vote exception.
 * @author Victor Morales
 */
public class CountryVoteException extends BaseException {

    public CountryVoteException(String code, String message, String errorType, Throwable cause) {
        super(errorType, code, message, cause);
    }

    public CountryVoteException(String code, String message, String errorType) {
        super(errorType, code, message, new Exception());
    }
}
