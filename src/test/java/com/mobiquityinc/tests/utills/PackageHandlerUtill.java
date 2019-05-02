package com.mobiquityinc.tests.utills;

import com.mobiquityinc.domain.models.BestFitRequest;

public class PackageHandlerUtill {

    public static BestFitRequest validSixItemTestCase() {
        String[] testData = new String[1];
        testData[0] = "81 : (1,53.38,€45) (2,88.62,€98) (3,78.48,€3) (4,72.30,€76) (5,30.18,€9) (6,46.34,€48)";
        BestFitRequest request = new BestFitRequest();
        request.setTestCases(testData);

        return request;
    }

    public static BestFitRequest hundredAndOneCapacity() {
        String[] testData = new String[1];
        testData[0] = "101 : (1,53.38,€45) (2,88.62,€98) (3,78.48,€3) (4,72.30,€76) (5,30.18,€9) (6,46.34,€48)";
        BestFitRequest request = new BestFitRequest();
        request.setTestCases(testData);

        return request;
    }

    public static BestFitRequest hundredAndOneWeight() {
        String[] testData = new String[1];
        testData[0] = "100 : (1,53.38,€45) (2,88.62,€98) (3,100.5,€3) (4,72.30,€76) (5,30.18,€9) (6,46.34,€48)";
        BestFitRequest request = new BestFitRequest();
        request.setTestCases(testData);

        return request;
    }

    public static BestFitRequest hundredAndOneCost() {
        String[] testData = new String[1];
        testData[0] = "100 : (1,53.38,€101) (2,88.62,€98) (3,100.5,€3) (4,72.30,€76) (5,30.18,€9) (6,46.34,€48)";
        BestFitRequest request = new BestFitRequest();
        request.setTestCases(testData);

        return request;
    }

    public static BestFitRequest invalidCost() {
        String[] testData = new String[1];
        testData[0] = "100 : (1,53.38,€101) (2,88.62,€98) (3,100.5,€3) (4,72.30,€76) (5,30.18,€9) (6,46.34,48€)";
        BestFitRequest request = new BestFitRequest();
        request.setTestCases(testData);

        return request;
    }
}
