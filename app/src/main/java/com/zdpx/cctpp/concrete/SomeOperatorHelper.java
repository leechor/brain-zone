package com.zdpx.cctpp.concrete;

import java.util.function.Predicate;

/**
 *
 */
public class SomeOperatorHelper {

    @SuppressWarnings("unchecked")
    public static <T> boolean funcProcess(Class<?> t, Object target, Predicate<T> test) {
        if (t.isAssignableFrom(target.getClass())) {
            return test.test((T)t);
        }
        return false;
    }
}
