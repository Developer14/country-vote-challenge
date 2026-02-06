package dev.loopstudio.countryvoteapi.exception;

import dev.loopstudio.countryvoteapi.constant.AppConstants;
import dev.loopstudio.countryvoteapi.constant.CountryVoteErrors;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.Instant;
import java.util.List;

import static dev.loopstudio.countryvoteapi.constant.AppConstants.FIELD_ERROR_FORMAT_STR;

/**
 * The type Custom exception handler.
 * @author Victor Morales
 */
@ControllerAdvice
public class CustomExceptionHandler {

    /**
     * Handle business errors.
     *
     * @param exception  the exception
     * @param webRequest the web request
     * @return the response entity
     */
    @ExceptionHandler(value = BaseException.class)
    public ResponseEntity<ExceptionResponse> handleErrors(CountryVoteException exception,
                                                          WebRequest webRequest) {

        HttpStatus httpStatus = AppConstants.BUSINESS_ERROR.equals(exception.getTitle())
                ? HttpStatus.BAD_REQUEST
                : HttpStatus.INTERNAL_SERVER_ERROR;

        return new ResponseEntity<>(
                new ExceptionResponse(
                        Instant.now(),
                        exception.getTitle(),
                        httpStatus.getReasonPhrase(),
                        exception.getCode(),
                        exception.getMessage(),
                        null
                ), httpStatus);
    }

    /**
     * Handle validation errors.
     *
     * @param exception     the exception
     * @return the response entity
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> handleValidationErrors(MethodArgumentNotValidException exception) {

        return new ResponseEntity<>(
                new ExceptionResponse(
                        Instant.now(),
                        AppConstants.VALIDATION_ERROR,
                        HttpStatus.BAD_REQUEST.getReasonPhrase(),
                        CountryVoteErrors.GLOBAL_VALIDATION_ERROR.getCode(),
                        CountryVoteErrors.GLOBAL_VALIDATION_ERROR.getMsg(),
                        parseErrors(exception.getBindingResult().getFieldErrors())
                ),
                HttpStatus.BAD_REQUEST);
    }

    private static String[] parseErrors(List<FieldError> fieldErrorList) {
        return fieldErrorList.stream()
                .map(fieldError -> String.format(FIELD_ERROR_FORMAT_STR, fieldError.getField(),
                        fieldError.getDefaultMessage()))
                .toList()
                .toArray(new String[0]);
    }

}

