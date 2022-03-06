package com.zdpx.cctpp.concrete;

/**
 *
 */
public class OperationActivity {
    private int batchSize;
    private int batchNumber;
    private int numberBatchesRequired;

    public double getBatchTime() {
        // TODO: 2021/11/29
        return Double.NaN;
    }

    public int getBatchSize() {
        return this.batchSize;
    }

    public int getBatchNumber() {
        return this.batchNumber;

    }

	public int getNumberBatchesRequired()
	{
		return this.numberBatchesRequired;
	}
}
