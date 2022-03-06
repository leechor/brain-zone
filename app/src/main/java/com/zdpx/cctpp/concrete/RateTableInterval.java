package com.zdpx.cctpp.concrete;

import com.zdpx.cctpp.annotations.DefaultValue;
import com.zdpx.cctpp.annotations.Description;
import com.zdpx.cctpp.annotations.DisplayName;
import com.zdpx.cctpp.simuApi.IRateTableInterval;

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
