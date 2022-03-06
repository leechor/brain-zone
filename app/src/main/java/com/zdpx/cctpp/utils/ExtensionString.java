package com.zdpx.cctpp.utils;

import com.google.common.base.Strings;

/**
 *
 */
public abstract class ExtensionString {

    public static String smethod_0(String s) {
        return s;
    }


    public static boolean compareIgnoreCase(String s1, String s2) {
        return s1.equalsIgnoreCase(s2);
    }

    public static String removeSpace(String value) {
        if (Strings.isNullOrEmpty(value)) {
            return null;
        }

        return value.replaceAll(" ", "");
    }
}
