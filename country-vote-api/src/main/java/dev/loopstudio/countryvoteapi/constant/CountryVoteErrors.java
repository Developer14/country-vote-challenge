package dev.loopstudio.countryvoteapi.constant;

/**
 * The enum Country vote errors.
 * @author Victor Morales
 */
public enum CountryVoteErrors {

    UNEXPECTED_ERROR("UN001", "An unexpected error has ocurred."),
    USER_DUPLICATED_ERROR("U001", "User email already exists."),
    COUNTRY_NOT_VALID_ERROR("C001", "Country not valid."),

    COUNTRIES_API_RESOURCE_ERROR("CA001", "Cannot retrieve countries."),

    GLOBAL_VALIDATION_ERROR("V001", "Input validation error.");


    private final String code;
    private final String message;

    CountryVoteErrors(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return message;
    }
}
