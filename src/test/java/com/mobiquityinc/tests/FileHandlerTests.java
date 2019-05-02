package com.mobiquityinc.tests;

import com.mobiquityinc.domain.models.BestFitRequest;
import com.mobiquityinc.exception.APIException;
import com.mobiquityinc.handlers.FileHandler;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class FileHandlerTests {
    private FileHandler handler;

    @BeforeAll
    private void setup() {
        handler = new FileHandler();
    }

    @Test
    void relativePathFails() {
        //When
        BestFitRequest request = new BestFitRequest();
        request.setFilepath("../../relatively/relative");

        assertThrows(APIException.class, () -> handler.handle(request));
    }

    @Test
    void fileMustBeSet() {
        //When
        BestFitRequest request = new BestFitRequest();

        assertThrows(APIException.class, () -> handler.handle(request));
    }

    @Test
    void fileMustBeReadable() {
        //When
        BestFitRequest request = new BestFitRequest();
        request.setFilepath("C:\\Users\\stefa\\IdeaProjects\\packer\\src\\test\\resources");

        //Expect could not read file
        assertThrows(APIException.class, () -> handler.handle(request));
    }

    @Test
    void filePathMustBeValid() {
        //When
        BestFitRequest request = new BestFitRequest();
        request.setFilepath("          C:\\Users\\stefa\\IdeaProjects\\packer\\src\\test\\resources...");

        //Expect invalid file path
        assertThrows(APIException.class, () -> handler.handle(request));
    }

    //To run this test please copy input-data.txt from the test resources directory to your C:\
    @Disabled
    @Test
    void correctPath() throws APIException {
        //When
        BestFitRequest request = new BestFitRequest();
        request.setFilepath("C:\\input-data.txt");

        //Expect valid file parsed
        handler.handle(request);

        assertEquals(4, request.getTestCases().length);
    }
}
