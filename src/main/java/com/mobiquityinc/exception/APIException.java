package com.mobiquityinc.exception;

/**
 * @author Stefan Janse van Rensburg
 * @version 1.0
 */
public class APIException extends Exception {

    /**
     * @param errorMessage Error message that will be displayed to the user
     * @param err          The underlying exception that was thrown
     */
    public APIException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}
