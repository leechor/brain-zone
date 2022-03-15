package org.licho.brain.concrete;

import org.licho.brain.annotations.DefaultValue;
import org.licho.brain.annotations.Description;
import org.licho.brain.annotations.DisplayName;
import org.licho.brain.brainApi.IRateTableInterval;

/**
 *
 */
public class RateTableInterval implements INotifyPropertyChanged, IRateTableInterval {
    private double rate;

    @Override
    @Description("The rate for the specified time period.")
    @DisplayName("Rate (events per hour)")
    @DefaultValue(0.0)
    public double Rate() {
        return this.rate;
    }

    @Override
    public void Rate(double value) {
        this.rate = value;
        this.triggerPropertyChangeEvent("Rate");
    }

    private void triggerPropertyChangeEvent(String rate) {
    }
}
