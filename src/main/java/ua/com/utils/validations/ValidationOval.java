package ua.com.utils.validations;

import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;
import net.sf.oval.configuration.annotation.AnnotationsConfigurer;
import net.sf.oval.context.FieldContext;
import net.sf.oval.integration.spring.SpringCheckInitializationListener;
import org.springframework.stereotype.Component;
import ua.com.exceptions.ValidateException;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ValidationOval {

    private static Validator validator = initValidator();

    private static Validator initValidator() {
        AnnotationsConfigurer annotationsConfigurer = new AnnotationsConfigurer();
        annotationsConfigurer.addCheckInitializationListener(SpringCheckInitializationListener.INSTANCE);
        return new Validator(annotationsConfigurer);
    }


    public static void fieldsValidation(Object object) throws ValidateException {
        List<ConstraintViolation> violations = validator.validate(object);
        if (violations.size() > 0) {
            Map<String, String> errorsMap;
            errorsMap = getFieldErrors(violations);
            throw new ValidateException(errorsMap);
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