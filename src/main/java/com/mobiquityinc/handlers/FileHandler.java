package com.mobiquityinc.handlers;

import com.mobiquityinc.domain.models.BestFitRequest;
import com.mobiquityinc.exception.APIException;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.util.Arrays;

/**
 * @author Stefan Janse van Rensburg
 * @version 1.0
 */
public class FileHandler implements Handler {
    private Handler successor;
    private BestFitRequest request;

    /**
     * @implNote Ensures the incoming file is valid and splits the file into a string array
     */
    private void processFile() throws APIException {

        if (StringUtils.isEmpty(request.getFilepath())) {
            throw new APIException("File not set on handler", new IllegalArgumentException());
        }

        try {
            Paths.get(request.getFilepath());
        } catch (InvalidPathException ipe) {
            throw new APIException("File path not valid", new IllegalArgumentException());
        }

        if (!Paths.get(request.getFilepath()).isAbsolute()) {
            throw new APIException("You\\'re file path should be absolute", new IllegalArgumentException());
        }

        String[] testCases;
        try {
            testCases = Files.readString(Paths.get(request.getFilepath())).split("\\r?\\n");
        } catch (IOException e) {
            throw new APIException("Could not read file", new IOException());
        }

        if (Arrays.stream(testCases).noneMatch(line -> line.contains(":"))) {
            throw new APIException("Invalid input file given, line must contain :", new IllegalArgumentException());
        }

        //Line must contain at least one : to indicate the package weigh limit
        if (Arrays.stream(testCases).noneMatch(line -> line.contains(":"))) {
            throw new APIException("Invalid input file given, line must contain :", new IllegalArgumentException());
        }

        //Processing done for File Handler, set test cases and continue
        request.setTestCases(testCases);

    }

    @Override
    public void handle(BestFitRequest request) throws APIException {
        //Set the request
        this.request = request;

        //Process the incoming file
        processFile();

        //If successor set, then call it
        if (successor != null) {
            successor.handle(request);
        }
    }

    @Override
    public void setSuccessor(Handler handler) {
        this.successor = handler;
    }
}
