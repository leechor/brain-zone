package com.zdpx.cctpp.concrete;

import java.util.function.Function;

/**
 *
 */
public abstract class MemberExpressionUtils<T> {
    public static <T, U> String getMemberExpressionMemberName(Function<T, U> expression) {
        // TODO: 2021/12/24 java not support
        return null;
    }

    public static String getMemberExpressionMemberName(String functionName) {
        return functionName;
    }
}
