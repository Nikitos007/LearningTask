package ua.com.exception;

import java.util.Map;


public class ValidFieldException extends Exception {

    Map<String, String> errorsMap;
    String location;

    public ValidFieldException(Map<String, String> errorsMap) {
        this.errorsMap = errorsMap;
    }

    public Map<String, String> getErrorsMap() {
        return errorsMap;
    }

}
