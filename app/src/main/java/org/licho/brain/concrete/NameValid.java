package org.licho.brain.concrete;

import java.util.function.Predicate;

/**
 *
 */
public class NameValid {
    public static boolean isValid(String instanceName, StringBuffer error) {
        return false;
    }

    public static String generalValidName(String name, Predicate<String> containsFunc) {
        boolean flag = Character.isDigit(name.charAt(name.length() - 1));
        int num = 1;
        String text;
        do {
            if (flag) {
                text = name + "_" + num++;
            } else {
                text = name + num++;
            }
        }
        while (containsFunc.test(text));
        return text;
    }
}
