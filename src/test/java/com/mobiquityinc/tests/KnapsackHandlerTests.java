package com.mobiquityinc.tests;


import com.mobiquityinc.domain.models.BestFitRequest;
import com.mobiquityinc.exception.APIException;
import com.mobiquityinc.handlers.KnapsackHandler;
import com.mobiquityinc.handlers.PackageHandler;
import com.mobiquityinc.tests.utills.PackageHandlerUtill;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class KnapsackHandlerTests {
    private KnapsackHandler handler;

    @BeforeAll
    private void setup() {
        handler = new KnapsackHandler();
    }

    @Test
    void maximumMustReturnMaximum() {
        //When
        int result = handler.maximum(1,2);

        //Assert
        assertEquals(2, result);
    }

    @Test
    void packedItemsMustHaveMaximumCost() throws APIException {
        BestFitRequest request = PackageHandlerUtill.validSixItemTestCase();
        PackageHandler packageHandler = new PackageHandler();

        //Handle valid test case to setup for knapsack test
        packageHandler.handle(request);

        //Set request
        handler.setRequest(request);

        //Test calculation
        int result = handler.calculatePackedItems()[request.getThingArr().length][request.getCapacity()];

        assertEquals(76, result);
    }

    @Test
    void findItemMustFindCorrectItem() throws APIException {
        BestFitRequest request = PackageHandlerUtill.validSixItemTestCase();
        PackageHandler packageHandler = new PackageHandler();

        //Handle valid test case to setup for knapsack test
        packageHandler.handle(request);

        //Set request
        packageHandler.setSuccessor(new KnapsackHandler());

        //Test calculation
        handler.handle(request);

        assertEquals("[4]", request.getSolution().toString().trim());
    }
}
