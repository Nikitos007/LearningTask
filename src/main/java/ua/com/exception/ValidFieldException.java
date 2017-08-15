package ua.com.exception;

import java.util.Map;


public class ValidFieldException extends Exception {

    Map<String, String> errorsMap;
    Object object;

    public ValidFieldException(Map<String, String> errorsMap, Object object) {
        this.errorsMap = errorsMap;
        this.object = object;
    }

    public Map<String, String> getErrorsMap() {
        return errorsMap;
    }

    public Object getObject() {
        return object;
    }
}
