package ca.dal.comparify.framework.exception;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Harsh Shah
 */
public class MissingRequiredFieldException extends ApplicationRuntimeException {

    private final List<String> fields;

    public MissingRequiredFieldException(Integer status, Integer errorCode, List<String> fields) {
        super(status, errorCode);
        this.fields = fields;
    }

    @Override
    public String getMessage() {
        return "The following fields were declared as required but could not be resolved: " +
                fields.stream().collect(Collectors.joining(", ", "[", "]"));
    }
}
