package com.zdpx.cctpp.concrete;

import com.zdpx.cctpp.enu.NumericDataType;
import com.zdpx.cctpp.enu.UnitType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 */
public class AbsStatePropertyObject extends BaseStatePropertyObject {
    List<Double> initValues;
    private final NumericDataType numericDataType;
    private Parameter[] parameters;

    public AbsStatePropertyObject(String name, boolean isReadOnly, boolean isPrivate) {
        super(name, isReadOnly, isPrivate);
        this.initValues = new ArrayList<>(2);
        this.numericDataType = NumericDataType.Real;
    }

    public BaseStateIGridItemPropertyObject CreateInstance(StateIGridItemPropertyObjectList stateIGridItemPropertyObjectList) {
        return new PhysicalCharacteristicsGridItemPropertyObject(this, stateIGridItemPropertyObjectList);
    }

    public void addParameter(String name, double initValue, String value) {
        List<Parameter> list = new ArrayList<>();
        if (this.parameters != null) {
            Collections.addAll(list, this.parameters);
        }
        list.add(new Parameter(name, value));
        this.parameters = list.toArray(new Parameter[0]);
        this.initValues.add(initValue);
    }

    public void updateParameter(int index, String value) {
        this.parameters[index] = new Parameter(this.parameters[index].name, value);
    }

    @Override
    public Parameter[] Parameters() {
			return this.parameters;
    }
}
