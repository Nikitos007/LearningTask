package ua.com.wrappers;

import java.util.Map;


public class ValidateWrapper {

    public Map<String, String> errors;

    public ValidateWrapper(Map<String, String> errors) {
        this.errors = errors;
    }

    public ValidateWrapper() {
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, String> errors) {
        this.errors = errors;
    }
}
