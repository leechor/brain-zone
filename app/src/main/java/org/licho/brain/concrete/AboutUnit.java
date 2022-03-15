package org.licho.brain.concrete;

import org.licho.brain.enu.UnitType;

/**
 *
 */
public class AboutUnit {

    private static UnitInfo[] unitInfos = new AboutUnit.UnitInfo[]
            {
                    new AboutUnit.UnitInfo(UnitType.Time, 0, "Hours", 1.0),
                    new AboutUnit.UnitInfo(UnitType.Time, 1, "Minutes", 0.016666666666666666),
                    new AboutUnit.UnitInfo(UnitType.Time, 2, "Seconds", 0.0002777777777777778),
                    new AboutUnit.UnitInfo(UnitType.Time, 3, "Days", 24.0),
                    new AboutUnit.UnitInfo(UnitType.Time, 4, "Weeks", 168.0),
                    new AboutUnit.UnitInfo(UnitType.Length, 0, "Meters", 1.0),
                    new AboutUnit.UnitInfo(UnitType.Length, 1, "Kilometers", 1000.0),
                    new AboutUnit.UnitInfo(UnitType.Length, 2, "Centimeters", 0.01),
                    new AboutUnit.UnitInfo(UnitType.Length, 3, "Inches", 0.025400000000000002),
                    new AboutUnit.UnitInfo(UnitType.Length, 4, "Feet", 0.3048),
                    new AboutUnit.UnitInfo(UnitType.Length, 5, "Yards", 0.9144000000000001),
                    new AboutUnit.UnitInfo(UnitType.Length, 6, "Miles", 1609.344),
                    new AboutUnit.UnitInfo(UnitType.Length, 7, "Nautical Miles", 1852.0),
                    new AboutUnit.UnitInfo(UnitType.TravelRate, 0, "Meters per Hour", 1.0),
                    new AboutUnit.UnitInfo(UnitType.TravelRate, 1, "Meters per Second", 3600.0),
                    new AboutUnit.UnitInfo(UnitType.TravelRate, 2, "Kilometers per Hour", 1000.0),
                    new AboutUnit.UnitInfo(UnitType.TravelRate, 3, "Feet per Second", 1097.28),
                    new AboutUnit.UnitInfo(UnitType.TravelRate, 4, "Feet per Minute", 18.288),
                    new AboutUnit.UnitInfo(UnitType.TravelRate, 5, "Miles per Hour", 1609.344),
                    new AboutUnit.UnitInfo(UnitType.TravelRate, 6, "Meters per Minute", 60.0),
                    new AboutUnit.UnitInfo(UnitType.TravelRate, 7, "Feet per Hour", 0.3048),
                    new AboutUnit.UnitInfo(UnitType.TravelRate, 8, "Nautical Miles per Hour", 1852.0),
                    new AboutUnit.UnitInfo(UnitType.Volume, 0, "Cubic Meters", 1.0),
                    new AboutUnit.UnitInfo(UnitType.Volume, 1, "Cubic Centimeters", 1E-06),
                    new AboutUnit.UnitInfo(UnitType.Volume, 2, "Liters", 0.001),
                    new AboutUnit.UnitInfo(UnitType.Volume, 3, "Gallons", 0.00378541178),
                    new AboutUnit.UnitInfo(UnitType.Volume, 4, "Cubic Feet", 0.0283168),
                    new AboutUnit.UnitInfo(UnitType.Weight, 0, "Kilograms", 1.0),
                    new AboutUnit.UnitInfo(UnitType.Weight, 1, "Grams", 0.001),
                    new AboutUnit.UnitInfo(UnitType.Weight, 2, "Pounds", 0.45359237),
                    new AboutUnit.UnitInfo(UnitType.Weight, 3, "Tons", 907.18474),
                    new AboutUnit.UnitInfo(UnitType.Weight, 4, "Metric Tons", 1000.0)
            };

    public static double smethod_4(UnitType unitType, int subType, UnitConversions unitConversions, double value) {
        if (unitType == UnitType.Unspecified || subType == 0 || value == 0.0) {
            return value;
        }
        if (value > 5E-324 || value < -5E-324) {
            double[] num = new double[]{1.0};
            if (AboutUnit.smethod_3(unitType, subType, unitConversions, num)) {
                return value * num[0];
            }
        }
        return value;
    }

    public static double smethod_5(UnitType unitType, int subType, UnitConversions unitConversions, double value) {
        if (unitType == UnitType.Unspecified || subType == 0 || value == 0.0) {
            return value;
        }
        if (value > 5E-324 || value < -5E-324) {
            double[] num = new double[]{1.0};
            if (AboutUnit.smethod_3(unitType, subType, unitConversions, num)) {
                return value / num[0];
            }
        }
        return value;
    }

    private static boolean smethod_3(UnitType unitType, int subType, UnitConversions unitConversions, double[] value) {
        value[0] = 1.0;
        if (unitType == UnitType.Currency) {
            return CurrencyWrapper.smethod_8(subType, (unitConversions != null) ?
                    unitConversions.CurrencyConversions() : null, value);
        }
        if (unitType == UnitType.CurrencyPerTimeUnit) {
            return AboutUnit.smethod_14(UnitType.Currency, subType, unitConversions, value, false);
        }
        if (unitType == UnitType.VolumeFlowRate) {
            return AboutUnit.smethod_14(UnitType.Volume, subType, unitConversions, value, false);
        }
        if (unitType == UnitType.WeightFlowRate) {
            return AboutUnit.smethod_14(UnitType.Weight, subType, unitConversions, value, false);
        }
        if (unitType == UnitType.TravelAcceleration) {
            return AboutUnit.smethod_14(UnitType.Length, subType, unitConversions, value, true);
        }
        int num = 0;
        switch (unitType) {
            case Unspecified:
                return false;
            case Time:
                num = 0;
                break;
            case TravelRate:
                num = 13;
                break;
            case Length:
                num = 5;
                break;
            case Volume:
                num = 22;
                break;
            case Weight:
                num = 27;
                break;
        }
        num += subType;
        if (num >= 0 && num < AboutUnit.unitInfos.length) {
            value[0] = AboutUnit.unitInfos[num].Carry;
            return true;
        }
        return false;
    }

    public static boolean smethod_14(UnitType unitType, int subType, UnitConversions unitConversions, double[] value,
                                     boolean b) {
        value[0] = 1.0;
        int param3 = subType & 65535;
        int param4 = subType >> 16;
        double num = 1.0;
        if (AboutUnit.smethod_3(unitType, param4, unitConversions, value)) {
            double num2 = 1.0;
            if (AboutUnit.smethod_3(UnitType.Time, param3, null, value)) {
                value[0] = num / num2;
                if (b) {
                    value[0] /= num2;
                }
                return true;
            }
        }
        return false;
    }

    public static int getUnitLevel(UnitType unitType, String level) {
        if (unitType == UnitType.Currency) {
            return CurrencyWrapper.getUnitLevel(level);
        }
        if (unitType == UnitType.CurrencyPerTimeUnit) {
            return AboutUnit.getUnitLevelB(level, UnitType.Currency, false);
        }
        if (unitType == UnitType.VolumeFlowRate) {
            return AboutUnit.getUnitLevelB(level, UnitType.Volume, false);
        }
        if (unitType == UnitType.WeightFlowRate) {
            return AboutUnit.getUnitLevelB(level, UnitType.Weight, false);
        }
        if (unitType == UnitType.TravelAcceleration) {
            return AboutUnit.getUnitLevelB(level, UnitType.Length, true);
        }
        for (AboutUnit.UnitInfo unitInfo : AboutUnit.unitInfos) {
            if (unitInfo.unitType == unitType && unitInfo.Name.equals(level)) {
                return unitInfo.Level;
            }
        }
        return -1;
    }

    private static int getUnitLevelB(String level, UnitType currency, boolean b) {
        // TODO: 2021/12/16
        return 0;
    }

    public static String getUnitPerDescription(UnitType ut, int level) {
        if (ut == UnitType.Currency) {
            return CurrencyWrapper.getUnitPerDescription(level);
        }
        if (ut == UnitType.CurrencyPerTimeUnit) {
            return AboutUnit.getUnitPerDescriptionB(level, UnitType.Currency, false);
        }
        if (ut == UnitType.VolumeFlowRate) {
            return AboutUnit.getUnitPerDescriptionB(level, UnitType.Volume, false);
        }
        if (ut == UnitType.WeightFlowRate) {
            return AboutUnit.getUnitPerDescriptionB(level, UnitType.Weight, false);
        }
        if (ut == UnitType.TravelAcceleration) {
            return AboutUnit.getUnitPerDescriptionB(level, UnitType.Length, true);
        }
        for (AboutUnit.UnitInfo unitInfo : AboutUnit.unitInfos) {
            if (unitInfo.unitType == ut && unitInfo.Level == level) {
                return unitInfo.Name;
            }
        }
        return "";
    }

    private static String getUnitPerDescriptionB(int level, UnitType currency, boolean b) {
        return "";
    }

    public static Double getInitValue(UnitType unitType, int level, UnitConversions unitConversions) {
        double[] num = new double[]{1.0};
        if (AboutUnit.smethod_3(unitType, level, unitConversions, num)) {
            return 1.0 / num[0];
        }
        return 1.0;
    }

    public static int getUnitTypeIndex(int unitTypeIndex, int level) {
        if (unitTypeIndex != -1 && level != -1) {
            return unitTypeIndex << 16 | level;
        }
        return -1;
    }

    public static class UnitInfo {
        UnitType unitType;
        int Level;
        String Name;
        double Carry;

        UnitInfo(UnitType unitType, int level, String name, double carry) {
            this.unitType = unitType;
            this.Level = level;
            this.Name = name;
            this.Carry = carry;
        }
    }
}
