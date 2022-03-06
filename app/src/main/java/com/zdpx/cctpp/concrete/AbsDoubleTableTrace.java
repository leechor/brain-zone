package com.zdpx.cctpp.concrete;

import com.zdpx.cctpp.concrete.property.AbsBaseRunSpace;
import com.zdpx.cctpp.enu.UnitType;
import com.zdpx.cctpp.utils.simu.IUnit;

/**
 *
 */
public abstract class AbsDoubleTableTrace<T, TRAITS extends IExpressionConverter<T>> extends AbsBaseTableTrace<T, TRAITS> {
    public AbsDoubleTableTrace(BaseStateIGridItemPropertyObject baseSomeIGridItemProperties,
                               AbsBaseRunSpace absBaseRunSpace) {
        super(baseSomeIGridItemProperties, absBaseRunSpace);
    }

    protected abstract double getCoreDoubleValue();


    @Override
    protected Double GetStateSnapshotValue() {
        return this.getCoreDoubleValue();
    }

    @Override
    public Object TableValue(IUnit unit) {
        double num = this.getCoreDoubleValue();
        UnitType unitType = super.getBaseSomeIGridItemProperties().UnitType();
        if (unitType == UnitType.Unspecified && unit != null) {
            int unitsFor = unit.GetUnitsFor(unitType);
            num = AboutUnit.smethod_5(unitType, unitsFor, unit.UnitConversions(), num);
        }
        return num;
    }


}
