package com.zdpx.cctpp.concrete;

import com.zdpx.cctpp.annotations.DefaultValue;
import com.zdpx.cctpp.annotations.Description;
import com.zdpx.cctpp.annotations.DisplayName;
import com.zdpx.cctpp.simuApi.IFunctionTableEntry;

/**
 *
 */
public class FunctionTableEntry implements IFunctionTableEntry, INotifyPropertyChanged {
    @Override
    public double X() {
        return 0;
    }

    @Override
    @DefaultValue(0.0)
    @Description("The independent value")
    @DisplayName("X")
    public void X(double x) {

    }

    @Override
    @DefaultValue(0.0)
    @Description("The independent value")
    @DisplayName("X")
    public double Fx() {
        return 0;
    }

    @Override
    public void Fx(double fx) {

    }
}
