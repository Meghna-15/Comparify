package ca.dal.comparify.user.exception.authentication;

import ca.dal.comparify.framework.exception.ApplicationRuntimeException;

/**
 * @author Harsh Shah
 */
public class UserAuthenticationException extends ApplicationRuntimeException {

    /**
     * @param message
     * @param status
     * @param errorCode
     *
     * @author Harsh Shah
     */
    public UserAuthenticationException(String message, Integer status, Integer errorCode){
        super(message, status, errorCode);
    }

}
