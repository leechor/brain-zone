package org.licho.brain.concrete;

import java.util.Locale;

/**
 *
 */
public class CurrencyWrapper {
    public static int unitType = (int) CurrencyWrapper.smethod_1("USD");
    public static int level = AboutUnit.getUnitTypeIndex(CurrencyWrapper.unitType, 0);

    private static short smethod_1(String code) {
        if (code.length() == 3) {
            code = code.toUpperCase(Locale.ROOT);

            if (code.chars().allMatch(c -> c >= 'A' && c <= 'Z')) {
                int num = (int) (code.charAt(0) - 'A');
                int num2 = (int) (code.charAt(1) - 'A');
                int num3 = (int) (code.charAt(2) - 'A');
                return (short) ((num & 31) << 10 | (num2 & 31) << 5 | (num3 & 31));
            }
        }
        return -1;
    }


    public static int getUnitLevel(String level) {
        // TODO: 2021/12/16 
        return 0;
    }

    public static String getUnitPerDescription(int level) {
        if (level == -1) {
            return "";
        }
        if (level == 0) {
            return "Default Currency";
        }
        return CurrencyWrapper.getUnitPerDescription((short) level);
    }

    public static boolean smethod_8(int subType, CurrencyConversions currencyConversions, double[] value) {
        return false;
    }
}
