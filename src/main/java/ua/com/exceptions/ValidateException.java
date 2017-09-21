package ua.com.exceptions;

import java.util.Map;

/**
 * Created on 21.09.17.
 */
public class ValidateException extends Exception {

    Map<String, String> errorsMap;

    public ValidateException(Map<String, String> errorsMap) {
        this.errorsMap = errorsMap;
    }

    public Map<String, String> getErrorsMap() {
        return errorsMap;
    }
}
