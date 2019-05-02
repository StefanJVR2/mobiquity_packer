package com.mobiquityinc.packer;

import com.mobiquityinc.domain.models.BestFitRequest;
import com.mobiquityinc.exception.APIException;
import com.mobiquityinc.handlers.FileHandler;
import com.mobiquityinc.handlers.Handler;
import com.mobiquityinc.handlers.KnapsackHandler;
import com.mobiquityinc.handlers.PackageHandler;

/**
 * @author Stefan Janse van Rensburg
 * @version 1.0
 */
public class Packer {

    /**
     * @param filePath Takes in an absolute path to a file
     * @return Returns the indexes of the items that were selected to be included in the package
     * @throws APIException Thrown when any input parameters do not conform to requirements
     * @implNote This application uses the chain-of-responsibility gang of four pattern, each handler must override a "handle" method
     * and set a successive handler, if the next handler is set the program will continue down the chain
     * @link https://sourcemaking.com/files/v2/content/patterns/Chain_of_responsibility1.png
     */
    public static String pack(String filePath) throws APIException {

        //Create initial request
        BestFitRequest request = new BestFitRequest();
        request.setFilepath(filePath);

        //Instantiate handlers
        Handler fileHandler = new FileHandler();
        Handler packageHandler = new PackageHandler();
        Handler knapsackHandler = new KnapsackHandler();

        //Set successors
        fileHandler.setSuccessor(packageHandler);
        packageHandler.setSuccessor(knapsackHandler);

        //Process
        fileHandler.handle(request);

        System.out.print(request.getSolution().toString());

        return request.getSolution().toString();
    }
}
