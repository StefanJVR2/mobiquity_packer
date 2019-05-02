package com.mobiquityinc.domain.models;

/**
 * @author Stefan Janse van Rensburg
 * @version 1.0
 * @link https://www.codeproject.com/KB/architecture/594974/simple_chain_of_resp.png
 */
public class BestFitRequest {
    private String filepath;
    private String[] testCases;
    private int capacity;
    private int[] weightArr;
    private int[] costArr;
    private int[] indexArr;
    private String[] thingArr;
    private StringBuilder solution;

    public BestFitRequest() {
    }

    public String[] getThingArr() {
        return thingArr;
    }

    public void setThingArr(String[] thingArr) {
        this.thingArr = thingArr;
    }

    public int[] getIndexArr() {
        return indexArr;
    }

    public void setIndexArr(int[] indexArr) {
        this.indexArr = indexArr;
    }

    public int[] getCostArr() {
        return costArr;
    }

    public void setCostArr(int[] costArr) {
        this.costArr = costArr;
    }

    public int[] getWeightArr() {
        return weightArr;
    }

    public void setWeightArr(int[] weightArr) {
        this.weightArr = weightArr;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String[] getTestCases() {
        return testCases;
    }

    public void setTestCases(String[] testCases) {
        this.testCases = testCases;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public StringBuilder getSolution() {
        return solution;
    }

    public void setSolution(StringBuilder solution) {
        this.solution = solution;
    }
}
