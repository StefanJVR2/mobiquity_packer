package com.mobiquityinc.handlers;

import com.mobiquityinc.domain.models.BestFitRequest;
import com.mobiquityinc.exception.APIException;

/**
 * @author Stefan Janse van Rensburg
 * @version 1.0
 */
public interface Handler {
    /**
     * @implNote Using the chain-of-responsibility gang of four pattern, each handler must override a handle method
     * the handle method will be called in a stack down
     * @link https://sourcemaking.com/files/v2/content/patterns/Chain_of_responsibility1.png
     */
    void handle(BestFitRequest request) throws APIException;

    /**
     * @implNote The chain-of-responsibility pattern relies on the caller to set a successor
     * @link https://sourcemaking.com/files/v2/content/patterns/Chain_of_responsibility1.png
     */
    void setSuccessor(Handler handler);
}
