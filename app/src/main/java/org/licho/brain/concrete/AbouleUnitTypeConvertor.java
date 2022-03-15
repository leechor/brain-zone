package org.licho.brain.concrete;

import org.licho.brain.enu.UnitType;
import org.licho.brain.utils.simu.IUnit;

/**
 *
 */
public class AbouleUnitTypeConvertor {

    public static String smethod_0(double value, IUnit param1, UnitType param2) {
        String text;
        if (param2 != UnitType.Unspecified) {
            int unitsFor = param1.GetUnitsFor(param2);
            text = Double.toString(AboutUnit.smethod_5(param2, unitsFor, param1.UnitConversions(), value));
            text = text + " " + AboutUnit.getUnitPerDescription(param2, unitsFor);
        } else {
            text = Double.toString(value);
        }
        return text;
    }

    public static UnitType smethod_1(UnitType unitType) {
        return switch (unitType) {
            case Length -> UnitType.TravelRate;
            case Currency -> UnitType.CurrencyPerTimeUnit;
            case Volume -> UnitType.VolumeFlowRate;
            case Weight -> UnitType.WeightFlowRate;
            default -> UnitType.Unspecified;
        };
    }

    public static UnitType smethod_2(UnitType unitType) {
        if (unitType == UnitType.Length) {
            return UnitType.TravelAcceleration;
        }
        return UnitType.Unspecified;
    }

}
