package org.licho.brain.concrete;

/**
 *
 */
public class QueueGridItemTraceInfo<T> {

    private double averageCostWaiting;
    private double numberInQueue;
    private double minimumNumberWaiting;
    private double maximumNumberWaiting;
    private double averageNumberWaiting;
    private double minimumTimeWaiting;
    private double maximumTimeWaiting;
    private double averageTimeWaiting;
    private double minimumCostWaiting;
    private double maximumCostWaiting;

    public double NumberInQueue() {
        return this.numberInQueue;
    }

    public double MinimumNumberWaiting() {
        return this.minimumNumberWaiting;
    }

    public double MaximumNumberWaiting() {
        return this.maximumNumberWaiting;
    }

    public double AverageNumberWaiting() {
        return this.averageNumberWaiting;
    }

    public double MinimumTimeWaiting() {
        return this.minimumTimeWaiting;
    }

    public double MaximumTimeWaiting() {
        return this.maximumTimeWaiting;
    }

    public double AverageTimeWaiting() {
        return this.averageTimeWaiting;
    }

    public double MinimumCostWaiting() {
        return this.minimumCostWaiting;
    }

    public double MaximumCostWaiting() {
        return this.maximumCostWaiting;
    }

    public double AverageCostWaiting() {
        return this.averageCostWaiting;
    }
}
