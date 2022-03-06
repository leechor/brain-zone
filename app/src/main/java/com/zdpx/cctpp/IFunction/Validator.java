package com.zdpx.cctpp.IFunction;

/**
 *
 */
@FunctionalInterface
public interface Validator<T> {
    boolean apply(T proposedValue, StringBuffer failureReason);
}
