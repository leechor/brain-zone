package org.licho.brain.concrete;

import org.licho.brain.annotations.DefaultValue;
import org.licho.brain.annotations.Description;
import org.licho.brain.annotations.DisplayName;
import org.licho.brain.simuApi.IFunctionTableEntry;

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
