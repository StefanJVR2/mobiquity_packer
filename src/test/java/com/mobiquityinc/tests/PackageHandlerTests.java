package com.mobiquityinc.tests;

import com.mobiquityinc.domain.models.BestFitRequest;
import com.mobiquityinc.exception.APIException;
import com.mobiquityinc.handlers.PackageHandler;
import com.mobiquityinc.tests.utills.PackageHandlerUtill;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PackageHandlerTests {
    private PackageHandler handler;


    @BeforeAll
    private void setup() {
        handler = new PackageHandler();
    }

    @Test
    void packagedSuccessfully() throws APIException {
        //When
        BestFitRequest request = PackageHandlerUtill.validSixItemTestCase();

        //Process
        handler.handle(request);

        //Assert
        assertEquals(6, request.getIndexArr().length);
        assertEquals(6, request.getWeightArr().length);
        assertEquals(6, request.getCostArr().length);
        assertNotEquals(0, request.getCapacity());
    }

    @Test
    void testCasesMustBeSet() {
        //When
        BestFitRequest request = new BestFitRequest();

        //Assert
        //Expect test cases must be set
        assertThrows(APIException.class, () -> handler.handle(request));
    }

    //Constraint checks
    @Test
    void hundredAndOneCapacity() {
        //When
        BestFitRequest request = PackageHandlerUtill.hundredAndOneCapacity();

        //Assert
        //Expect capacity exception
        assertThrows(APIException.class, () -> handler.handle(request));
    }

    @Test
    void hundredAndOneWeight() {
        //When
        BestFitRequest request = PackageHandlerUtill.hundredAndOneWeight();

        //Assert
        //Expect weight exception
        assertThrows(APIException.class, () -> handler.handle(request));
    }


    @Test
    void hundredAndOneCost() {
        //When
        BestFitRequest request = PackageHandlerUtill.hundredAndOneCost();

        //Assert
        //Expect cost exception
        assertThrows(APIException.class, () -> handler.handle(request));
    }

    @Test
    void InvalidCost() {
        //When
        BestFitRequest request = PackageHandlerUtill.invalidCost();

        //Assert
        //Invalid cost exception
        assertThrows(APIException.class, () -> handler.handle(request));
    }
}
