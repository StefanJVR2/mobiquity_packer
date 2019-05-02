package com.mobiquityinc.handlers;

import com.mobiquityinc.domain.models.BestFitRequest;
import com.mobiquityinc.exception.APIException;

import java.util.HashSet;

/**
 * @author Stefan Janse van Rensburg
 * @version 1.0
 */
public class KnapsackHandler implements Handler {
    private StringBuilder solution = new StringBuilder();
    private BestFitRequest request;
    private Handler successor;

    // A utility function that returns maximum of two integers
    public int maximum(int a, int b) {
        return (a > b) ? a : b;
    }

    // Returns the maximum value that can be put in a knapsack
    // of capacity capacity
    public int[][] calculatePackedItems() {
        int[][] table = new int[request.getThingArr().length + 1][request.getCapacity() + 1];

        // Build table table[][] in bottom up manner
        // For each item, while under or equal to capacity, add item to table
        for (int itemIndex = 0; itemIndex <= request.getThingArr().length; itemIndex++) {
            for (int weight = 0; weight <= request.getCapacity(); weight++) {
                if (itemIndex == 0 || weight == 0) {
                    table[itemIndex][weight] = 0;
                } else if (request.getWeightArr()[itemIndex - 1] <= weight) {
                    table[itemIndex][weight] = maximum(request.getCostArr()[itemIndex - 1] + table[itemIndex - 1]
                            [(weight - request.getWeightArr()[itemIndex - 1])], table[itemIndex - 1][weight]);
                } else {
                    table[itemIndex][weight] = table[itemIndex - 1][weight];
                }
            }
        }

        return table;
    }

    /*  This method retrieves the indexes of the items that were packed,
        it does this by iterating over the items (bottom, up) and removing them as it traverses the tree
    */
    private void findPackedItems(int[][] table) {

        // Stores the result of the above calculation (Total cost of Package)
        int result = table[request.getThingArr().length][request.getCapacity()];

        // Reset the weight
        int w = request.getCapacity();

        //Traverse bottom up i is equal to size of package subtract 1 etc
        HashSet<Integer> values = new HashSet<>();
        for (int i = request.getThingArr().length; i > 0 && result > 0; i--) {

            //Ignore the result of the solution
            if (!(result == table[i - 1][w])) {
                // This item is included.
                values.add(request.getIndexArr()[i - 1]);

                // Since this weight is included its
                // cost is deducted from the result
                result = result - request.getCostArr()[i - 1];
                // Weight is deducted from capacity
                w = w - request.getWeightArr()[i - 1];
            }
        }

        //Add item index to solution
        solution.append(values.toString());
        solution.append(System.lineSeparator());
    }

    @Override
    public void handle(BestFitRequest request) throws APIException {
        this.request = request;

        //Validate request to ensure its in the correct state for this Handler
        if (request.getThingArr() == null || request.getCostArr() == null || request.getWeightArr() == null || request.getCapacity() == 0 || request.getIndexArr() == null) {
            throw new APIException("Request not in the correct state to be handled by Knapsack handler", new IllegalArgumentException());
        }

        findPackedItems(calculatePackedItems());
        request.setSolution(solution);
    }

    @Override
    public void setSuccessor(Handler handler) {
        this.successor = handler;
    }

    public void setRequest(BestFitRequest request) {
        this.request = request;
    }
}
