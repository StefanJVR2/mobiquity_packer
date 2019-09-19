package com.mobiquityinc.domain.models;

public class SingleBestFitRequest {
    private int capacity;
    private int[] weightArr;
    private int[] costArr;
    private int[] indexArr;

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int[] getWeightArr() {
        return weightArr;
    }

    public void setWeightArr(int[] weightArr) {
        this.weightArr = weightArr;
    }

    public int[] getCostArr() {
        return costArr;
    }

    public void setCostArr(int[] costArr) {
        this.costArr = costArr;
    }

    public int[] getIndexArr() {
        return indexArr;
    }

    public void setIndexArr(int[] indexArr) {
        this.indexArr = indexArr;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(capacity + " : ");
        for (int i = 0; i < 3; i++) {
            result.append("(")
                    .append(indexArr[i])
                    .append(",")
                    .append(weightArr[i])
                    .append(",")
                    .append("€")
                    .append(costArr[i])
                    .append(") ");
        }
        return result.toString();
    }
}
