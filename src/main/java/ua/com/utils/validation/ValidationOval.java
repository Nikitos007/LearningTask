package ua.com.utils.validation;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;
import net.sf.oval.context.FieldContext;
import ua.com.exception.ValidFieldException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ValidationOval {

    public static void fieldsValidation(Object object) throws ValidFieldException {
        Validator validator = new Validator();
        List<ConstraintViolation> violations = validator.validate(object);
        if (violations.size() > 0) {
            Map<String, String> errorsMap;
            errorsMap = getFieldErrors(violations);
            throw new ValidFieldException(errorsMap);
        }
    }

    private static Map<String, String> getFieldErrors(List<ConstraintViolation> violations) {
        Map<String, String> errorsMap = new HashMap<>();
        for (ConstraintViolation error : violations) {
            FieldContext ctx = (FieldContext) error.getContext();
            String fieldName = ctx.getField().getName();
            String errorMessage = error.getMessage();
            errorsMap.put(fieldName, errorMessage);
        }
        return errorsMap;
    }


}
