package com.mobiquityinc.handlers;

import com.mobiquityinc.domain.models.BestFitRequest;
import com.mobiquityinc.exception.APIException;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

/**
 * @author Stefan Janse van Rensburg
 * @version 1.0
 */
public class PackageHandler implements Handler {
    private static final int BASE = 100;
    private Handler successor;
    private BestFitRequest request;

    private void processItems() throws APIException {

        for (String testCase : request.getTestCases()) {
            String weightLimit = testCase.substring(0, testCase.indexOf(":")).trim();

            if (Integer.parseInt(weightLimit) > 100) {
                throw new APIException("Capacity received that is higher than 100", new IllegalArgumentException());
            }

            //Remove capacity from string
            testCase = testCase.substring(testCase.indexOf(":") + 1).trim();

            String[] things = testCase.split(" ");

            int[] indexArr = new int[things.length];
            int[] weightArr = new int[things.length];
            int[] costArr = new int[things.length];

            for (int i = 0; i < things.length; i++) {
                things[i] = things[i].replaceAll("[()]", "");

                String[] items = things[i].split(",");

                indexArr[i] = Integer.parseInt(items[0]);
                weightArr[i] = (int) (Double.parseDouble(items[1]) * BASE);

                if (Double.parseDouble(items[1]) > 100) {
                    throw new APIException("Item weight received that is higher than 100", new IllegalArgumentException());
                }

                Number number;
                try {
                    //Apparently Ireland uses the euro sign before the value
                    number = NumberFormat.getCurrencyInstance(new Locale("en", "IE")).parse(items[2]);

                    if (number.intValue() > 100) {
                        throw new APIException("Item cost received that is higher than 100", new IllegalArgumentException());
                    }
                } catch (ParseException e) {
                    throw new APIException("Cost not in a valid format", e);
                }

                costArr[i] = number.intValue();
            }

            //Have to multiply by the base to remove the decimals, capacity is adjusted to capacity * base, this slows the program down
            //However, it ensures accuracy by taking into account the decimals
            request.setCapacity(Integer.parseInt(weightLimit) * BASE);
            request.setIndexArr(indexArr);
            request.setWeightArr(weightArr);
            request.setCostArr(costArr);
            request.setThingArr(things);

            //If successor set, then call it
            if (successor != null) {
                // Each request will have to be handled
                successor.handle(request);
            }
        }
    }

    @Override
    public void handle(BestFitRequest request) throws APIException {
        this.request = request;

        //Ensure request is in the correct state to be processed
        if (request.getTestCases() == null || request.getTestCases().length == 0) {
            throw new APIException("Package handler requires test cases to be set", new IllegalArgumentException());
        }

        //Items exist, process
        processItems();
    }

    @Override
    public void setSuccessor(Handler handler) {
        this.successor = handler;
    }
}
